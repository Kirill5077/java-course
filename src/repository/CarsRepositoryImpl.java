package repository;

import model.Car;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class CarsRepositoryImpl implements CarsRepository {

    @Override
    public List<Car> loadCars(String filePath) {
        try {
            return Files.lines(Paths.get(filePath))
                    .filter(line -> !line.isBlank())
                    .map(line -> {
                        String[] parts = line.split("\\|");
                        return new Car(
                                parts[0].trim(),
                                parts[1].trim(),
                                parts[2].trim(),
                                Long.parseLong(parts[3].trim()),
                                Long.parseLong(parts[4].trim())
                        );
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке данных: " + e.getMessage(), e);
        }
    }

    @Override
    public void saveCars(String filePath, List<Car> cars) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            for (Car car : cars) {
                writer.write(String.format("%s|%s|%s|%d|%d",
                        car.getNumber(),
                        car.getModel(),
                        car.getColor(),
                        car.getMileage(),
                        car.getCost()));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении данных", e);
        }
    }
}
