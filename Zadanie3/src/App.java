import java.util.Scanner;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        Television tv1 = new Television("Samsung", 55);
        Television tv2 = new Television("LG", 65);

        tv1.turnOn();
        tv2.turnOff();

        tv1.displayInfo();
        tv2.displayInfo();

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведите марку телевизора: ");
        String brand = scanner.nextLine();
        System.out.print("Введите размер телевизора: ");
        int size = scanner.nextInt();

        Television tv3 = new Television(brand, size);
        tv3.turnOn();
        tv3.displayInfo();

        Random rand = new Random();
        String[] brands = {"Sony", "Panasonic", "Philips", "Toshiba"};
        String randomBrand = brands[rand.nextInt(brands.length)];
        int randomSize = 32 + rand.nextInt(40);

        Television tv4 = new Television(randomBrand, randomSize);
        tv4.turnOff();
        tv4.displayInfo();
    }
}
