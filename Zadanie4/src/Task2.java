import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String arrow1 = ">>-->";
        String arrow2 = "<--<<";

        int count = 0;
        for (int i = 0; i <= input.length() - arrow1.length(); i++) {
            String sub = input.substring(i, i + arrow1.length());
            if (sub.equals(arrow1) || sub.equals(arrow2)) {
                count++;
            }
        }

        System.out.println(count);
    }
}
