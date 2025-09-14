package repository;

import model.Car;
import java.util.List;

public interface CarsRepository {
    List<Car> loadCars(String filePath);
    void saveCars(String filePath, List<Car> cars);
}
