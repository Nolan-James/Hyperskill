import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();
        String[] parts = word.split("");

        for (String letter : parts) {
            System.out.print(letter + "" + letter);
        }
    }
}