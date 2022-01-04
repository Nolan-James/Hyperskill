package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Account {
    private String ccNumber;
    private int pin;
    private double balance;
    private boolean isLoggedIn;

    public Account() {
        this.balance = 0;
        this.isLoggedIn = false;
    }

    public Account(String ccNumber, int pin, double balance) {
        this.ccNumber = ccNumber;
        this.pin = pin;
        this.balance = balance;
        this.isLoggedIn = false;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public int generatePin() {
        Random random = new Random();
        int digit1 = random.nextInt(10);
        int digit2 = random.nextInt(10);
        int digit3 = random.nextInt(10);
        int digit4 = random.nextInt(10);

        if (digit1 == 0) {
            digit1 = random.nextInt(10);
        }

        String tempPin = digit1 + "" + digit2 + "" + digit3 + "" + digit4;
        return Integer.parseInt(tempPin);
    }

    public String generateCardNumber(int pin, Database database) {
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

        database.insert(creditCardNumber, pin);

        return creditCardNumber;
    }

    private int checkLuhnAlgo(String creditCardNumber) {
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

    public Account createAccount(int pin, String cardNumber) {
        this.setPin(pin);
        this.setCcNumber(cardNumber);

        System.out.println("\nYour card has been created");
        System.out.println("Your card number:");
        System.out.println(this.getCcNumber());
        System.out.println("Your card PIN:");
        System.out.println(this.getPin() + "\n");

        return this;
    }

    public void checkBalance(Database database) {
        int amount = database.checkBalance(this.getCcNumber());
        System.out.println("Balance: " + amount);
    }

    public void addIncome(Database database, int amountToAdd) {
        int currentBalance = getBalanceFromDb(database, this.getCcNumber());
        int newBalance = currentBalance + amountToAdd;
        database.setBalance(newBalance, this.getCcNumber());
        System.out.println("Income was added!");
    }

    private int getBalanceFromDb(Database database, String cardNumber) {
        return database.checkBalance(cardNumber);

    }

    public boolean checkCardNumberToTransferTo(String cardNumber) {
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

    public boolean checkCardExists(String cardNumber, Database database) {
        boolean exists = database.cardExists(cardNumber);

        if (exists) {
            return true;
        } else {
            System.out.println("Such a card does not exist");
            return false;
        }
    }

    public void transferToAccount(Database database, int amountToTransfer, String cardNumber) {
        if (database.checkBalance(this.getCcNumber()) < amountToTransfer) {
            System.out.println("Not enough money!");
        } else {
            System.out.println("Success!");
            int previousBalance = this.getBalanceFromDb(database, cardNumber);
            int newBalance = previousBalance + amountToTransfer;
            database.setBalance(newBalance, cardNumber);

            int thisCurrentBalance = this.getBalanceFromDb(database, this.getCcNumber());
            int thisNewBalance = thisCurrentBalance - amountToTransfer;
            database.setBalance(thisNewBalance, this.getCcNumber());
        }
    }

    public Account closeAccount(Database database) {
        database.deleteCard(this.getCcNumber());
        System.out.println("The account has been closed!");
        return new Account();
    }

    @Override
    public String toString() {
        return "Pin number: " + this.getPin() + "\n Card number: " + this.getCcNumber() + "\n Balance: " + this.getBalance();
    }
}
