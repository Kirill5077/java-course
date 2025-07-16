import java.util.Scanner;

public class HelloUser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // создаём объект Scanner для ввода с консоли

        System.out.print("Введите ваше имя: "); // просим пользователя ввести имя
        String name = scanner.nextLine(); // считываем введённое имя

        System.out.println("Привет, " + name); // выводим приветствие

        scanner.close(); // закрываем Scanner
    }
}
