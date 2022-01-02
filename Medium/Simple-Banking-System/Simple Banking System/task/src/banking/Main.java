package banking;

import org.sqlite.SQLiteDataSource;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:" + args[1];

        Database database = new Database();
        database.connect(url)
        createDatabase(url);
        createDatabaseTable(url);
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

    private static void createDatabaseTable(String url) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISITS card(" +
                        "id INTEGER PRIMARY KEY" +
                        "number VARCHAR(16)" +
                        "pin VARCHAR(4)" +
                        "balance INTEGER DEFAULT 0" +
                        ");");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void createDatabase(String arg) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(arg);
        try (Connection connection = dataSource.getConnection()) {
            if (connection != null) {
                System.out.println("Valid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
//        boolean result = false;
        int result = 0;
        int bin = 400000;
        Random random = new Random();
        int accNum = random.nextInt(999999999);
        int checksum = random.nextInt(10);
        String creditCardNumber = bin + "" + accNum + "" + checksum;

//        while (!result) {
//            result = checkLuhnAlgo(creditCardNumber);
//            checksum = random.nextInt(10);
//            creditCardNumber = bin + "" + accNum + "" + checksum;
//        }

        result = checkLuhnAlgo(creditCardNumber);
        for (int i = 0; i < 10; i++) {
            if ((result + i) % 10 == 0) {
                checksum = i;
                break;
            }
        }
        creditCardNumber = bin + "" + accNum + "" + checksum;

        addToDatabase(creditCardNumber);

//        if (!ccNumbers.containsKey(pin)) {
//            ccNumbers.put(pin, creditCardNumber);
//        }

        return creditCardNumber;
    }

    private static void addToDatabase(String creditCardNumber) {

    }

    private static int checkLuhnAlgo(String creditCardNumber) {
        String allButLast = creditCardNumber.substring(0, creditCardNumber.length() - 1);
        List<Integer> values = new ArrayList<>();
        List<Integer> valuesBy2 = new ArrayList<>();
        List<Integer> valuesMinus9 = new ArrayList<>();


        for (String letter : allButLast.split("")) {
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

//        valuesMinus9.add(Integer.parseInt(String.valueOf(creditCardNumber.charAt(creditCardNumber.length() - 1))));

        for (Integer value : valuesMinus9) {
            sum += value;
        }

        return sum;

//        return sum % 10 == 0;

    }

    private static String printMenu(Scanner scanner) {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");

        return scanner.nextLine();
    }
}