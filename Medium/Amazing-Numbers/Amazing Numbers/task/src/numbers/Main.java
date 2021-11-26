package numbers;

import org.w3c.dom.ls.LSOutput;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String num = "";
        System.out.println("Enter a natural number:");


        int number = scanner.nextInt();

        if (number > 10) {
            num = Objects.toString(number);
            System.out.println(num.charAt(num.length() - 1));
        }


        // Is natural
        if (!(number > 0)) {
            System.out.println("This number is not natural!");
        }

        // Is Even or Odd
        if (number % 2 == 0) {
            System.out.println("This number is Even.");
        } else {
            System.out.println("This number is Odd.");
        }

        // Is Buzz
        if (number == 7) {
            System.out.println("It is a Buzz number.");
            System.out.println("Explanation:");
            System.out.println(number + " is divisible by 7 and ends with 7.");
        }

        if (number > 10) {
            num = String.valueOf(num.charAt(num.length() - 1));
            if (number % 7 == 0) {
                System.out.println("It is a Buzz number.");
            } else if (Integer.parseInt(num) == 7) {
                System.out.println("It is a Buzz number.");
            } else {
                System.out.println("It is not a Buzz number.");
            }
        } else if (number % 7 == 0) {
            System.out.println("It is a Buzz number.");
        } else {
            System.out.println("It is not a Buzz number.");
        }

        if (number > 10) {
            System.out.println("Explanation");
            if (number % 7 == 0 && num.charAt(num.length() - 1) == 7) {
                System.out.println(number + " is divisible by 7 and ends with 7.");
            } else if (number % 7 == 0) {
                System.out.println(number + " is divisible by 7.");
            } else if (Integer.parseInt(num) == 7) {
                System.out.println(number + " ends with 7.");
            } else {
                System.out.println(number + " is neither divisible by 7 nor does it end with 7.");
            }
        } else {
            System.out.println(number + " is neither divisible by 7 nor does it end with 7.");
        }
    }
}
