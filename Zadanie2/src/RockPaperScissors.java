import java.util.Random;

public class RockPaperScissors {
    public static void main(String[] args) {
        Random random = new Random();

        // 0 - камень, 1 - ножницы, 2 - бумага
        int vasya = random.nextInt(3);
        int petya = random.nextInt(3);

        System.out.println("Вася выбрал: " + choiceName(vasya));
        System.out.println("Петя выбрал: " + choiceName(petya));

        int result = determineWinner(vasya, petya);

        if (result == 0) {
            System.out.println("Ничья!");
        } else if (result == 1) {
            System.out.println("Победил Вася!");
        } else {
            System.out.println("Победил Петя!");
        }
    }

    // Метод для определения победителя
    // Возвращает: 0 - ничья, 1 - выиграл Вася, 2 - выиграл Петя
    public static int determineWinner(int vasya, int petya) {
        if (vasya == petya) {
            return 0; // ничья
        }
        if ((vasya == 0 && petya == 1) || // камень бьёт ножницы
                (vasya == 1 && petya == 2) || // ножницы бьют бумагу
                (vasya == 2 && petya == 0)) { // бумага бьёт камень
            return 1; // выиграл Вася
        } else {
            return 2; // выиграл Петя
        }
    }

    // Метод для преобразования числа в название
    public static String choiceName(int choice) {
        switch (choice) {
            case 0: return "Камень";
            case 1: return "Ножницы";
            case 2: return "Бумага";
            default: return "Неизвестно";
        }
    }
}
