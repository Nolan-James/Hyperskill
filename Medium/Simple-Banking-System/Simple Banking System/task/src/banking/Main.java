package banking;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> ccNumbers = new HashMap<>();
        boolean loggedIn = false;

        while (true) {
            if (!loggedIn) {
                String choice = printMenu(scanner);
                if (choice.equals("0")) {
                    break;
                }
                switch (choice) {
                    case "1":
                        String pin = generatePin();
                        String cc = generateCardNumber(ccNumbers, pin);
                        createAccount(cc, pin);
                        break;
                    case "2":
                        loggedIn = logIn(scanner, ccNumbers);
                        break;
                }
            } else {
                String choice = printLoggedInMenu(scanner);
                if (choice.equals("0")) {
                    break;
                }
                switch (choice) {
                    case "1":
                        System.out.println("Balance: 0");
                        break;
                    case "2":
                        loggedIn = false;
                        break;

                }
            }

        }
    }

    private static String printLoggedInMenu(Scanner scanner) {
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");

        return scanner.nextLine();
    }

    private static boolean logIn(Scanner scanner, Map<String, String> ccNumbers) {
        boolean result = false;
        System.out.println("Enter your card number:");
        String cardNumberInput = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pin = scanner.nextLine();

        if (ccNumbers.containsKey(pin)) {
            if (ccNumbers.get(pin).equals(cardNumberInput)) {
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

    private static String generateCardNumber(Map<String, String> ccNumbers, String pin) {
        int bin = 400000;
        Random random = new Random();
        int accNum = random.nextInt(999999999);
        int checksum = random.nextInt(10);
        String creditCardNumber = bin + "" + accNum + "" + checksum;

        if (!ccNumbers.containsKey(pin)) {
            ccNumbers.put(pin, creditCardNumber);
        }

        return creditCardNumber;
    }

    private static String printMenu(Scanner scanner) {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");

        return scanner.nextLine();
    }
}