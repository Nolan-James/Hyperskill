package banking;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:" + args[1];

        Database database = new Database();
        database.connect(url);
        database.createTable(url);

        Scanner scanner = new Scanner(System.in);
        Map<String, String> ccNumbers = new HashMap<>();
        boolean loggedIn = false;

        while (true) {
            if (!loggedIn) {
                String choice = printMenu(scanner);
                if (choice.equals("0")) {
                    database.selectAll(url);
                    break;
                }
                switch (choice) {
                    case "1":
                        String pin = generatePin();
                        String cc = generateCardNumber(ccNumbers, pin, database, url);
                        createAccount(cc, pin);
                        break;
                    case "2":
                        loggedIn = logIn(scanner, ccNumbers, database, url);
                        break;
                }
            } else {
                String choice = printLoggedInMenu(scanner);
                if (choice.equals("0")) {
                    break;
                }
                switch (choice) {
                    case "1":
                        checkBalance(database);
                        break;
                    case "2":
                        loggedIn = false;
                        break;

                }
            }

        }
    }

    private static void checkBalance(Database database) {
        int balance = database.checkBalance();
    }

    private static String printLoggedInMenu(Scanner scanner) {
        System.out.println("1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");

        return scanner.nextLine();
    }

    private static boolean logIn(Scanner scanner, Map<String, String> ccNumbers, Database database, String url) {
        boolean result = false;
        System.out.println("Enter your card number:");
        String cardNumberInput = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pin = scanner.nextLine();

        String ccNumberFromDatabase = database.selectCard(url, Integer.parseInt(pin));

        if (ccNumberFromDatabase != null) {
            if (ccNumberFromDatabase.equals(cardNumberInput)) {
                System.out.println("You have successfully logged in!");
                result = true;
            } else {
                System.out.println("Wrong card number or PIN!");
                result = false;
            }
        }
        return result;
    }

    private static String generatePin() {
        Random random = new Random();
        int digit1 = random.nextInt(10);
        int digit2 = random.nextInt(10);
        int digit3 = random.nextInt(10);
        int digit4 = random.nextInt(10);
        return digit1 + "" + digit2 + "" + digit3 + "" + digit4;
    }

    private static void createAccount(String cc, String pin) {
        System.out.println("\nYour card has been created");
        System.out.println("Your card number:");
        System.out.println(cc);
        System.out.println("Your card PIN:");
        System.out.println(pin + "\n");
    }

    private static String generateCardNumber(Map<String, String> ccNumbers, String pin, Database database, String url) {
        int result = 0;
        int bin = 400000;

        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            stringBuilder.append(random.nextInt(10));
        }


        int accNum = Integer.parseInt(String.valueOf(stringBuilder));
        String creditCardNumber = bin + "" + accNum;

        if (creditCardNumber.length() != 15) {
            stringBuilder = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                stringBuilder.append(random.nextInt(10));
            }
            accNum = Integer.parseInt(String.valueOf(stringBuilder));
            creditCardNumber = bin + "" + accNum;
        }

        result = checkLuhnAlgo(creditCardNumber);
        int checksum = 0;
        for (int i = 0; i < 10; i++) {
            if ((result + i) % 10 == 0) {
                checksum = i;
                break;
            }
        }
        creditCardNumber = bin + "" + accNum + "" + checksum;

        if (creditCardNumber.length() != 16) {
            for (int i = 0; i < 10; i++) {
                if ((result + i) % 10 == 0) {
                    checksum = i;
                    break;
                }
            }
            creditCardNumber = bin + "" + accNum + "" + checksum;
        }

        int pinInt = Integer.parseInt(pin);
        database.insert(creditCardNumber, pinInt, url);

        return creditCardNumber;
    }

    private static int checkLuhnAlgo(String creditCardNumber) {
        List<Integer> values = new ArrayList<>();
        List<Integer> valuesBy2 = new ArrayList<>();
        List<Integer> valuesMinus9 = new ArrayList<>();


        for (String letter : creditCardNumber.split("")) {
            values.add(Integer.parseInt(letter));
        }
        int sum = 0;

        for (int i = 1; i < values.size() + 1; i++) {
            if (i % 2 != 0) {
                int newSum = values.get(i - 1) * 2;
                valuesBy2.add(i - 1, newSum);
            } else {
                valuesBy2.add(values.get(i - 1));
            }
        }

        for (int i = 0; i < valuesBy2.size(); i++) {
            if (valuesBy2.get(i) > 9) {
                int newSum = valuesBy2.get(i) - 9;
                valuesMinus9.add(i, newSum);
            } else {
                valuesMinus9.add(i, valuesBy2.get(i));
            }
        }

        for (Integer value : valuesMinus9) {
            sum += value;
        }

        return sum;

    }

    private static String printMenu(Scanner scanner) {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");

        return scanner.nextLine();
    }
}