import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();

        if (word.length() <= 1) {
            System.out.println(true);
        } else if (word.matches("vwxyz|abc|xy|pqrst")) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}