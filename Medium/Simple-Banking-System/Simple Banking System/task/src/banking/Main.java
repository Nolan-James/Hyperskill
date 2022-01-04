package banking;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:" + args[1];
        List<User> users = new ArrayList<>();
        User user = new User();

        Database database = new Database();
        database.connect(url);
        database.createTable(url);

        Scanner scanner = new Scanner(System.in);
        Map<String, String> ccNumbers = new HashMap<>();
        boolean loggedIn = false;

        while (true) {
            if (!user.isLoggedIn()) {
                String choice = printMenu(scanner);
                if (choice.equals("0")) {
//                    database.selectAll(url);
                    break;
                }
                switch (choice) {
                    case "1":
                        String pin = generatePin();
                        String cc = generateCardNumber(ccNumbers, pin, database, url);
                        user = new User();
                        createAccount(cc, pin, user, users);
                        break;
                    case "2":
                        user = logIn(scanner, ccNumbers, database, url, user, users);
                        break;
                }
            } else {
                String choice = printLoggedInMenu(scanner);
                if (choice.equals("0")) {
                    break;
                }
                switch (choice) {
                    case "1":
                        checkBalance(database, user, url);
                        break;
                    case "2":
                        addIncome(database, user, url, scanner);
                        break;
                    case "3":
                        String cardNumber = getCardNumber(scanner);
                        System.out.println("To Transfer to: " + cardNumber);
                        System.out.println("User: " + user.getAccount().getCcNumber());
                        if (cardNumber.equals(user.getAccount().getCcNumber())) {
                            System.out.println("You can't transfer money to the same account!");
                            break;
                        }
                        boolean isValid = checkCardNumberToTransferTo(cardNumber);
                        if (isValid) {
                            boolean exists = checkCardExists(cardNumber, database, url);
                            if (exists) {
                                transferToAnotherAccount(scanner, user, database, url, cardNumber);
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                        break;
                    case "4":
                        user = closeAccount(database, url, user);
                    case "5":
                        loggedIn = false;
                        user.setLoggedIn(false);
                        break;

                }
            }

        }
    }

    private static User closeAccount(Database database, String url, User user) {
        database.deleteCard(url, user.getAccount().getCcNumber());
        user = new User();
        System.out.println("The account has been closed!");
        return user;
    }

    private static boolean checkCardExists(String cardNumber, Database database, String url) {
        boolean exists = database.cardExists(url, cardNumber);

        if (exists) {
            return true;
        } else {
            System.out.println("Such a card does not exist");
            return false;
        }
    }

    private static void transferToAnotherAccount(Scanner scanner, User user, Database database, String url, String cardNumber) {
        System.out.println("Enter how much money you want to transfer:");
        String amount = scanner.nextLine();

        if (user.getAccount().getBalance() < Integer.parseInt(amount)) {
            System.out.println("Not enough money!");
        } else {
            System.out.println("Success!");
            database.setBalance(url, Integer.parseInt(amount), cardNumber);

            int amountToSubtract = (int) (user.getAccount().getBalance() - Integer.parseInt(amount));
            System.out.println("Amount to subtract from user: " + amountToSubtract);
            database.setBalance(url, amountToSubtract, user.getAccount().getCcNumber());
            user.getAccount().setBalance(user.getAccount().getBalance() - Integer.parseInt(amount));
        }
    }

    private static boolean checkCardNumberToTransferTo(String cardNumber) {
        String ccSubstring = cardNumber.substring(0, cardNumber.length() - 1);
        int lastNumber = Integer.parseInt(String.valueOf(cardNumber.charAt(cardNumber.length() - 1)));
        int result = checkLuhnAlgo(ccSubstring);

        int complete = result + lastNumber;

        if (complete % 10 == 0) {
            return true;
        } else {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
            return false;
        }
    }

    private static String getCardNumber(Scanner scanner) {
        System.out.println("Transfer");
        System.out.println("Enter card number:");
        return scanner.nextLine();
    }


    private static void addIncome(Database database, User user, String url, Scanner scanner) {
        System.out.println("Enter income:");
        String amount = scanner.nextLine();

        database.setBalance(url, Integer.parseInt(amount), user.getAccount().getCcNumber());
        user.getAccount().setBalance(user.getAccount().getBalance() + Integer.parseInt(amount));
    }

    private static void checkBalance(Database database, User user, String url) {
        int amount = database.checkBalance(url, user.getAccount().getCcNumber());
        System.out.println("Amount from database for user: " + amount);
//        System.out.println("Balance: " + user.getAccount().getBalance());
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

    private static User logIn(Scanner scanner, Map<String, String> ccNumbers, Database database, String url, User user, List<User> users) {
        boolean result = false;

        System.out.println("Enter your card number:");
        String cardNumberInput = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pin = scanner.nextLine();

        String ccNumberFromDatabase = database.selectCard(url, Integer.parseInt(pin));

        for (User userInList : users) {
            if ((userInList.getAccount().getCcNumber().equals(ccNumberFromDatabase))) {
                user = userInList;
            }
        }

        if (ccNumberFromDatabase != null) {
            if (ccNumberFromDatabase.equals(cardNumberInput)) {
                user.setLoggedIn(true);
                System.out.println("You have successfully logged in!");
                result = true;
            } else {
                System.out.println("Wrong card number or PIN!");
                result = false;
            }
        }
        return user;
    }

    private static String generatePin() {
        Random random = new Random();
        int digit1 = random.nextInt(10);
        int digit2 = random.nextInt(10);
        int digit3 = random.nextInt(10);
        int digit4 = random.nextInt(10);
        return digit1 + "" + digit2 + "" + digit3 + "" + digit4;
    }

    private static User createAccount(String cc, String pin, User user, List<User> users) {
        Account account = new Account(cc, Integer.parseInt(pin), 0);
        user.setAccount(account);
        users.add(user);

        System.out.println("\nYour card has been created");
        System.out.println("Your card number:");
        System.out.println(cc);
        System.out.println("Your card PIN:");
        System.out.println(pin + "\n");

        return user;
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