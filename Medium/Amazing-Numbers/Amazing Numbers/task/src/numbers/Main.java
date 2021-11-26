package numbers;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isEvenOrOdd = false;
        boolean isBuzz = false;
        boolean isDuck = false;
        String num = "";
        System.out.println("Enter a natural number:");


        int number = scanner.nextInt();

        if (number > 10) {
            num = Objects.toString(number);
        }

        boolean isNatural = getIsNatural(number);

        if (isNatural) {
            isEvenOrOdd = getIsEvenOrOdd(number);
            isBuzz = getIsBuzz(number, num);
            isDuck = getIsDuck(number);
            System.out.println("Properties of " + number);
            System.out.println("even: " + isEvenOrOdd);
            System.out.println("odd: " + !isEvenOrOdd);
            System.out.println("buzz: " + isBuzz);
            System.out.println("duck: " + isDuck);
        } else {
            System.out.println("This number is not natural!");
        }

    }

    private static boolean getIsDuck(int number) {
        String stringValue = String.valueOf(number);

        int[] digits = new int[stringValue.length()];

        for (int i = 0; i < digits.length; i++) {
            digits[i] = Integer.parseInt(String.valueOf(stringValue.charAt(i)));
        }

        boolean result = false;
        for (int i = 1; i < digits.length; i++) {

            if (digits[0] == 0) {
                if (digits[i] == 0) {
                    result = true;
                    break;
                } else {
                    result = false;
                }
            } else if (digits[i] == 0) {
                result = true;
                break;
            } else {
                result = false;
            }
        }
        return result;
    }

    private static boolean getIsBuzz(int number, String num) {
        if (number == 7) {
            return true;
        }

        if (number > 10) {
            num = String.valueOf(num.charAt(num.length() - 1));
            if (number % 7 == 0) {
                return true;
            } else if (Integer.parseInt(num) == 7) {
                return true;
            } else {
                return false;
            }
        } else if (number % 7 == 0) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean getIsEvenOrOdd(int number) {
        return number % 2 == 0;
    }

    private static boolean getIsNatural(int number) {
        return number > 0;
    }
}
