package banking;

import java.sql.*;

public class Database {

    private String url;

    public Database(String url) {
        this.url = url;
    }

    public Connection connect() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(this.url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS card (\n"
                + "     id integer PRIMARY KEY,\n"
                + "     number text NOT NULL,\n"
                + "     pin text NOT NULL,\n"
                + "     balance integer DEFAULT 0\n"
                + ");";

        try (Connection connection = this.connect(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insert(String ccNumber, int pin) {
        String sql = "INSERT INTO card (number, pin) VALUES(?,?)";

        try (Connection connection = this.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ccNumber);
            statement.setInt(2, pin);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String selectCard(int pin) {
        String sql = "SELECT * FROM card WHERE pin = " + pin;

        try (Connection connection = this.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                return resultSet.getString("number");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public int checkBalance(String ccNumber) {
        String sql = "SELECT balance FROM card WHERE number = " + ccNumber;
        int balance = 0;

        try (Connection connection = this.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                balance = resultSet.getInt("balance");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return balance;
    }

    public void setBalance(int amount, String ccNumber) {
        String sql = "UPDATE card SET balance = ? "
                + "WHERE number = ?";

        try (Connection connection = this.connect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, amount);
            statement.setString(2, ccNumber);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean cardExists(String ccNumber) {
        String sql = "SELECT * FROM card WHERE number = ? ";
        boolean exists = false;

        try (Connection connection = this.connect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ccNumber);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return exists;
    }

    public void deleteCard(String ccNumber) {
        String sql = "DELETE FROM card WHERE number = ?";

        try (Connection connection = this.connect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ccNumber);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void selectAll(String url) {
        String sql = "SELECT * FROM card";

        try (Connection connection = this.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                System.out.println(resultSet.getString("number"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
