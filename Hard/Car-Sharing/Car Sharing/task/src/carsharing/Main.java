package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Main {
    final static CompanyDao companyDao = new CompanyDaoImpl();
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:file:../task/src/carsharing/db/";

    public static void main(String[] args) {
        boolean isRunning = true;
        boolean loggedIn = false;

        createDatabase();
//        dropDatabase();

        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            int result = showMenu(scanner);
            switch (result) {
                case 0:
                    isRunning = false;
                    break;
                case 1:
                    loggedIn = true;
                    while (loggedIn) {
                        int managerResult = logInMenu(scanner);

                        switch (managerResult) {
                            case 1:
                                printCompanies();
                                break;
                            case 2:
                                createCompany(scanner);
                                break;
                            case 0:
                                loggedIn = false;
                                break;
                        }
                    }
            }
        }

    }

    private static void dropDatabase() {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL + "carsharing");
            connection.setAutoCommit(true);
            statement = connection.createStatement();

            String sql = "DROP TABLE company";

            statement.executeUpdate(sql);

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createDatabase() {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL + "carsharing");
            connection.setAutoCommit(true);
            statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS COMPANY " +
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

    private static void createCompany(Scanner scanner) {
        System.out.println("Enter the company name:");
        String companyName = scanner.nextLine();
        companyDao.createCompany(companyName);
    }

    private static void printCompanies() {
        List<Company> companies = companyDao.getCompanies();

        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            System.out.println("Company Size: " + companies.size());
            companies.forEach(System.out::println);
        }
        System.out.println();
    }

    private static int logInMenu(Scanner scanner) {
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");

        String stringResult = scanner.nextLine();

        return Integer.parseInt(stringResult);
    }

    private static int showMenu(Scanner scanner) {
        System.out.println("1. Log in as a manager");
        System.out.println("0. Exit");

        String stringResult = scanner.nextLine();

        return Integer.parseInt(stringResult);
    }
}