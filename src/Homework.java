import java.util.*;

// Основной класс с методами для задания 1 и 2
public class Homework {

    // Задание 1: метод, который возвращает набор уникальных элементов
    public static <T> Set<T> getUniqueElements(ArrayList<T> list) {
        return new HashSet<>(list);
    }

    // Задание 2: проверка, являются ли строки анаграммами
    public static boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false;

        // Убираем пробелы и приводим к нижнему регистру
        s = s.replaceAll("\\s+", "").toLowerCase();
        t = t.replaceAll("\\s+", "").toLowerCase();

        if (s.length() != t.length()) return false;

        // Преобразуем строки в массивы символов и сортируем
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        Arrays.sort(sArray);
        Arrays.sort(tArray);

        return Arrays.equals(sArray, tArray);
    }

    // Тесты для задания 1 и 2
    public static void main(String[] args) {
        // --- Задание 1 ---
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 4, 5));
        Set<Integer> uniqueNumbers = getUniqueElements(numbers);
        System.out.println("Уникальные элементы: " + uniqueNumbers);

        // --- Задание 2 ---
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите первую строку: ");
        String s = scanner.nextLine();
        System.out.print("Введите вторую строку: ");
        String t = scanner.nextLine();

        System.out.println("Являются анаграммами? " + isAnagram(s, t));
    }
}

// Задание 3: класс PowerfulSet
class PowerfulSet {

    // Пересечение множеств
    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    // Объединение множеств
    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    // Разность множеств (относительное дополнение)
    public static <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }

    // Тесты для задания 3
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(0, 1, 2, 4));

        System.out.println("Пересечение: " + intersection(set1, set2));   // {1, 2}
        System.out.println("Объединение: " + union(set1, set2));          // {0, 1, 2, 3, 4}
        System.out.println("Разность: " + relativeComplement(set1, set2)); // {3}
    }
}
