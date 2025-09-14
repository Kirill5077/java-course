package test;

import model.Car;
import repository.CarsRepository;
import repository.CarsRepositoryImpl;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String filePath = "data/cars.txt";
        CarsRepository repo = new CarsRepositoryImpl();

        List<Car> cars = repo.loadCars(filePath);

        System.out.println("Автомобили в базе:");
        System.out.println("Number Model Color Mileage Cost");
        cars.forEach(System.out::println);

        // Параметры поиска (из задания)
        String colorToFind = "Black";
        long mileageToFind = 0L;
        long n = 700_000L, m = 900_000L;
        String modelToFind = "Toyota";
        String modelToFind2 = "Volvo";

        // 1) Номера по цвету или пробегу
        String numbers = cars.stream()
                .filter(car -> car.getColor().equalsIgnoreCase(colorToFind)
                        || car.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .collect(Collectors.joining(" "));
        System.out.println("Номера автомобилей по цвету или пробегу: " + numbers);

        // 2) Уникальные модели в диапазоне цены
        long uniqueModels = cars.stream()
                .filter(car -> car.getCost() >= n && car.getCost() <= m)
                .map(Car::getModel)
                .distinct()
                .count();
        System.out.println("Уникальные автомобили: " + uniqueModels + " шт.");

        // 3) Цвет автомобиля с минимальной ценой
        cars.stream()
                .min(Comparator.comparingLong(Car::getCost))
                .ifPresent(car -> System.out.println("Цвет автомобиля с минимальной стоимостью: " + car.getColor()));

        // 4) Средняя стоимость модели
        double avgToyota = cars.stream()
                .filter(car -> car.getModel().equalsIgnoreCase(modelToFind))
                .mapToLong(Car::getCost)
                .average().orElse(0.0);
        System.out.printf("Средняя стоимость модели %s: %,.2f%n", modelToFind, avgToyota);

        double avgVolvo = cars.stream()
                .filter(car -> car.getModel().equalsIgnoreCase(modelToFind2))
                .mapToLong(Car::getCost)
                .average().orElse(0.0);
        System.out.printf("Средняя стоимость модели %s: %,.2f%n", modelToFind2, avgVolvo);
    }
}
