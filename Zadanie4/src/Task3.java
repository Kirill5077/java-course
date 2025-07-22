import java.util.Arrays;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] words = line.split(" ");

        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toLowerCase().toCharArray();
            Arrays.sort(chars);
            words[i] = new String(chars);
        }

        System.out.println(String.join(" ", words));
    }
}
