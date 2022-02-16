package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:file:../task/src/carsharing/db/";

    List<Company> companies;

    public CompanyDaoImpl() {
        this.companies = new ArrayList<>();
    }


    @Override
    public List<Company> getCompanies() {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL + "carsharing");
            connection.setAutoCommit(true);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM company;");

            while (rs.next()) {
                Company company = new Company();
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                companies.add(company);
            }

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return companies;
    }

    @Override
    public void createCompany(String companyName) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL + "carsharing");
            connection.setAutoCommit(true);
            statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO company (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, companyName);
            preparedStatement.executeUpdate();


            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
