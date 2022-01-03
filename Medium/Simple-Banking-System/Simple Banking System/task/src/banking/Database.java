package banking;

import java.sql.*;

public class Database {

    public Connection connect(String url) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connected");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public void createTable(String url) {
        String sql = "CREATE TABLE IF NOT EXISTS card (\n"
                + "     id integer PRIMARY KEY,\n"
                + "     number text NOT NULL,\n"
                + "     pin text NOT NULL,\n"
                + "     balance integer DEFAULT 0\n"
                + ");";

        try (Connection connection = this.connect(url); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insert(String ccNumber, int pin, String url) {
        String sql = "INSERT INTO card (number, pin) VALUES(?,?)";

        try (Connection connection = this.connect(url); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ccNumber);
            statement.setInt(2, pin);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String selectCard(String url, int pin) {
        String sql = "SELECT * FROM card WHERE pin = " + pin;

        try (Connection connection = this.connect(url);
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

    public void selectAll(String url) {
        String sql = "SELECT * FROM card";

        try (Connection connection = this.connect(url);
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
