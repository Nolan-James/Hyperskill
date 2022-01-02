package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public Connection connect(String url) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public void insert(String ccNumber, int pin) {
        try (Connection connection = this.connect();)
    }
}
