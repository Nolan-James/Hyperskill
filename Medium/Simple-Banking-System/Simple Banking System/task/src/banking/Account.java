package banking;

public class Account {
    private String ccNumber;
    private int pin;
    private double balance;

    public Account(String ccNumber, int pin, double balance) {
        this.ccNumber = ccNumber;
        this.pin = pin;
        this.balance = balance;
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
}
