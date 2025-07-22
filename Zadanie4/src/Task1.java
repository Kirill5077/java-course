import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        char[] keyboard = {
                'q','w','e','r','t','y','u','i','o','p',
                'a','s','d','f','g','h','j','k','l',
                'z','x','c','v','b','n','m'
        };
        Scanner scanner = new Scanner(System.in);
        char ch = scanner.nextLine().charAt(0);

        int pos = -1;
        for (int i = 0; i < keyboard.length; i++) {
            if (keyboard[i] == ch) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            System.out.println("Некорректный ввод");
        } else {
            int leftPos = (pos == 0) ? keyboard.length - 1 : pos - 1;
            System.out.println(keyboard[leftPos]);
        }
    }
}
