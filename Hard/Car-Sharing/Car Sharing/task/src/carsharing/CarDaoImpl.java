package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:file:../task/src/carsharing/db/";

    List<Car> cars;

    public CarDaoImpl() {
        this.cars = new ArrayList<>();
    }

    @Override
    public List<Car> listCars(int companyId) {
        this.cars = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL + "carsharing");
            connection.setAutoCommit(true);
            statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM car WHERE company_id = ?;");
            preparedStatement.setInt(1, companyId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setName(rs.getString("name"));
                cars.add(car);
            }

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    @Override
    public void addCar(String car, int companyId) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL + "carsharing");
            connection.setAutoCommit(true);
            statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO car (name, company_id) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, car);
            preparedStatement.setInt(2, companyId);
            preparedStatement.executeUpdate();

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
