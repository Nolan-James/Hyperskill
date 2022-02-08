package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:file:../task/src/carsharing/db/";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL + args[1]);
            connection.setAutoCommit(true);
            statement = connection.createStatement();

            String sql = "CREATE TABLE COMPANY " +
                    "(id INTEGER not NULL, " +
                    " name VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";

            statement.executeUpdate(sql);

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}