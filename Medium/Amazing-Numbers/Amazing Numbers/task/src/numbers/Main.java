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
        boolean isPalindromic = false;
        boolean isGapful = false;
        String num = "";

        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");


        while (true) {
            System.out.println("Enter a request:");
            long number = scanner.nextLong();
            if (number == 0) {
                break;
            } else {
                if (number > 10) {
                    num = Objects.toString(number);
                }

                boolean isNatural = getIsNatural(number);

                if (isNatural) {
                    isEvenOrOdd = getIsEvenOrOdd(number);
                    isBuzz = getIsBuzz(number, num);
                    isDuck = getIsDuck(number);
                    isPalindromic = getIsPalindromic(number);
                    isGapful = getIsGapful(number);

                    System.out.println("Properties of " + number);
                    System.out.println("even: " + isEvenOrOdd);
                    System.out.println("odd: " + !isEvenOrOdd);
                    System.out.println("buzz: " + isBuzz);
                    System.out.println("duck: " + isDuck);
                    System.out.println("palindromic: " + isPalindromic);
                    System.out.println("gapful: " + isGapful);
                } else {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            }
        }


    }

    private static boolean getIsGapful(long number) {
        String numberString = Objects.toString(number);
        String firstDigit = (String.valueOf(numberString.charAt(0)));
        String lastDigit = (String.valueOf(numberString.charAt(numberString.length() - 1)));
        String concat = firstDigit + lastDigit;
        int modifier = Integer.parseInt(concat);

        return number % modifier == 0;
    }

    private static boolean getIsPalindromic(long number) {
        int trueCount = 0;
        int falseCount = 0;
        int[] digits = getDigitArray(number);
        for (int i = 0, j = digits.length - 1; i < digits.length; i++, j--) {
            if (digits[i] == digits[j]) {
                trueCount++;
            } else {
                falseCount++;
            }
        }
        return trueCount == digits.length;
    }

    private static int[] getDigitArray(long number) {
        String stringValue = String.valueOf(number);

        int[] digits = new int[stringValue.length()];

        for (int i = 0; i < digits.length; i++) {
            digits[i] = Integer.parseInt(String.valueOf(stringValue.charAt(i)));
        }
        return digits;
    }

    private static boolean getIsDuck(long number) {
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

    private static boolean getIsBuzz(long number, String num) {
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

    private static boolean getIsEvenOrOdd(long number) {
        return number % 2 == 0;
    }

    private static boolean getIsNatural(long number) {
        return number > 0;
    }
}
