import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();

        for (int i = 0; i < word.length(); i++) {
            int middle = word.length() / 2;

            if (word.length() % 2 == 0) {
                if (i == middle - 1 || i == middle) {
                    continue;
                } else {
                    System.out.print(word.charAt(i));
                }
            } else {
                if (i == middle) {
                    continue;
                } else {
                    System.out.print(word.charAt(i));

                }
            }
        }
    }
}