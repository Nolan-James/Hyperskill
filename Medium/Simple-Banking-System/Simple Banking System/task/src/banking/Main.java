package banking;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:" + args[1];
        Account account = new Account();

        Database database = new Database(url);
        database.connect();
        database.createTable();

        Scanner scanner = new Scanner(System.in);
        boolean isLoggedIn = false;

        while (true) {

            if (!isLoggedIn) {
                String choice = printMenu(scanner);
                if (choice.equals("0")) {
                    break;
                }

                switch (choice) {
                    case "1":
                        int pin = account.generatePin();
                        String cardNumber = account.generateCardNumber(pin, database);
                        account.createAccount(pin, cardNumber);
                        break;
                    case "2":
                        account = logIn(scanner, database);
                        if (account.isLoggedIn()) isLoggedIn = true;
                        break;
                }
            } else {
                String choice = printLoggedInMenu(scanner);
                if (choice.equals("0")) {
                    break;
                }

                switch (choice) {
                    case "1":
                        account.checkBalance(database);
                        break;
                    case "2":
                        int amountToAdd = getAmountToAdd(scanner);
                        account.addIncome(database, amountToAdd);
                        break;
                    case "3":
                        String cardNumber = getCardNumber(scanner);
                        if (cardNumber.equals(account.getCcNumber())) {
                            System.out.println("You can't transfer money to the same account!");
                            break;
                        }
                        boolean isValid = account.checkCardNumberToTransferTo(cardNumber);
                        if (isValid) {
                            boolean cardExists = account.checkCardExists(cardNumber, database);
                            if (cardExists) {
                                int amountToTransfer = getAmountToTransfer(scanner);

                                account.transferToAccount(database, amountToTransfer, cardNumber);
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                        break;
                    case "4":
                        account = account.closeAccount(database);
                        isLoggedIn = false;
                        break;
                    case "5":
                        account.setLoggedIn(false);
                        isLoggedIn = false;
                        break;
                }
            }
        }
    }

    private static int getAmountToTransfer(Scanner scanner) {
        System.out.println("Enter how much money you want to transfer:");
        return Integer.parseInt(scanner.nextLine());
    }

    private static int getAmountToAdd(Scanner scanner) {
        System.out.println("Enter income:");
        return Integer.parseInt(scanner.nextLine());
    }

    private static String getCardNumber(Scanner scanner) {
        System.out.println("Transfer");
        System.out.println("Enter card number:");
        return scanner.nextLine();
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

    private static Account logIn(Scanner scanner, Database database) {
        Account account = new Account();

        System.out.println("Enter your card number:");
        String cardNumberInput = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pin = scanner.nextLine();

        String ccNumberFromDatabase = database.selectCard(Integer.parseInt(pin));

        if (ccNumberFromDatabase != null) {
            if (ccNumberFromDatabase.equals(cardNumberInput)) {
                account.setPin(Integer.parseInt(pin));
                account.setCcNumber(ccNumberFromDatabase);
                account.setBalance(account.getBalance());
                account.setLoggedIn(true);
                System.out.println("You have successfully logged in!");
            } else {
                System.out.println("Wrong card number or PIN!");
            }
        } else {
            System.out.println("Wrong card number or PIN!");
        }
        return account;
    }

    private static String printMenu(Scanner scanner) {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");

        return scanner.nextLine();
    }
}