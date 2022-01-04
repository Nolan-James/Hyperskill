package banking;

public class User {
    private Account account;
    private boolean isLoggedIn;

    public User() {
        this.isLoggedIn = false;
    }

    public User(Account account, boolean isLoggedIn) {
        this.account = account;
        this.isLoggedIn = isLoggedIn;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
