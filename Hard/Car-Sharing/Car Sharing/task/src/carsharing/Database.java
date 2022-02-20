package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private String JDBC_DRIVER = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:file:../task/src/carsharing/db/";

    public void createCompanyTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL + "carsharing");
            connection.setAutoCommit(true);
            statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS company " +
                    "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(255) UNIQUE NOT NULL" +
                    ")";

            statement.executeUpdate(sql);

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCarTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL + "carsharing");
            connection.setAutoCommit(true);
            statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS car " +
                    "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(255) UNIQUE NOT NULL, " +
                    " company_id INT NOT NULL, " +
                    " FOREIGN KEY (company_id) REFERENCES company(id)" +
                    ");";

            statement.executeUpdate(sql);

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String table) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL + "carsharing");
            connection.setAutoCommit(true);
            statement = connection.createStatement();

            String sql = "DROP TABLE " + table;

            statement.executeUpdate(sql);

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
