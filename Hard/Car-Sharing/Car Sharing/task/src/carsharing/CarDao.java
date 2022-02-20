package carsharing;

import java.util.List;

public interface CarDao {
    public List<Car> listCars(int companyId);
    public void addCar(String car, int companyId);
}
