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
        boolean isSpy = false;
        long number = 0;
        String num = "";

        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");


        while (true) {
            System.out.println("Enter a request:");
            String numberAsString = scanner.nextLine();
            String[] parts = numberAsString.split(" ");

            if (parts.length < 2) {
                number = Long.parseLong(numberAsString);
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
                        if (numberAsString.length() >= 3) {
                            isGapful = getIsGapful(number);
                        } else {
                            isGapful = false;
                        }
                        isSpy = getIsSpy(number, num);

                        System.out.println("Properties of " + number);
                        System.out.println("even: " + isEvenOrOdd);
                        System.out.println("odd: " + !isEvenOrOdd);
                        System.out.println("buzz: " + isBuzz);
                        System.out.println("duck: " + isDuck);
                        System.out.println("palindromic: " + isPalindromic);
                        System.out.println("gapful: " + isGapful);
                        System.out.println("spy: " + isSpy);
                    } else {
                        System.out.println("The first parameter should be a natural number or zero.");
                    }
                }
            } else if (parts.length == 3) {
                number = Long.parseLong(parts[0]);
                int iterations = Integer.parseInt(parts[1]);
                String property = parts[2].toLowerCase();

                if (property.equals("buzz") || property.equals("duck") || property.equals("palindromic")
                        || property.equals("gapful") || property.equals("spy")
                        || property.equals("even") || property.equals("odd")) {
                    if (getIsNatural(iterations)) {
                        int count = 0;
                        while (count != iterations) {
                            for (long i = 0; i < iterations; i++) {
                                if (count == iterations) {
                                    break;
                                }
                                num = Objects.toString(number);
                                parts[0] = String.valueOf(number);
                                ArrayList<String> results = new ArrayList<>();
                                isEvenOrOdd = getIsEvenOrOdd(number);
                                isBuzz = getIsBuzz(number, num);
                                isDuck = getIsDuck(number);
                                isPalindromic = getIsPalindromic(number);
                                if (parts[0].length() >= 3) {
                                    isGapful = getIsGapful(number);
                                } else {
                                    isGapful = false;
                                }
                                isSpy = getIsSpy(number, num);

                                if (isBuzz) results.add("buzz");
                                if (isDuck) results.add("duck");
                                if (isPalindromic) results.add("palindromic");
                                if (isGapful) results.add("gapful");
                                if (isEvenOrOdd) {
                                    results.add("even");
                                } else {
                                    results.add("odd");
                                }
                                if (isSpy) results.add("spy");

                                if (results.contains(property)) {
                                    System.out.print(number + " is ");
                                    for (int j = 0; j < results.size(); j++) {
                                        if (j == results.size() - 1) {
                                            System.out.print(results.get(j));
                                        } else {
                                            System.out.print(results.get(j) + ", ");
                                        }

                                    }
                                    System.out.println("");
                                    count++;
                                    number++;
                                } else {
                                    number++;
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("The property [" + property + "] is wrong.");
                    System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]");
                }

            } else {
                number = Long.parseLong(parts[0]);
                int iterations = Integer.parseInt(parts[1]);
                if (getIsNatural(iterations)) {


                    for (long i = 0; i < iterations; i++) {
                        num = Objects.toString(number);
                        ArrayList<String> results = new ArrayList<>();
                        isEvenOrOdd = getIsEvenOrOdd(number);
                        isBuzz = getIsBuzz(number, num);
                        isDuck = getIsDuck(number);
                        isPalindromic = getIsPalindromic(number);
                        if (parts[0].length() >= 3) {
                            isGapful = getIsGapful(number);
                        } else {
                            isGapful = false;
                        }
                        isSpy = getIsSpy(number, num);

                        if (isBuzz) results.add("buzz");
                        if (isDuck) results.add("duck");
                        if (isPalindromic) results.add("palindromic");
                        if (isGapful) results.add("gapful");
                        if (isEvenOrOdd) {
                            results.add("even");
                        } else {
                            results.add("odd");
                        }
                        if (isSpy) results.add("spy");

                        System.out.print(number + " is ");
                        for (int j = 0; j < results.size(); j++) {
                            if (j == results.size() - 1) {
                                System.out.print(results.get(j));
                            } else {
                                System.out.print(results.get(j) + ", ");
                            }

                        }
                        System.out.println("");
                        number++;
                    }
                } else {
                    System.out.println("second parameter should be a natural number.");
                }
            }
        }
    }

    private static boolean getIsSpy(long number, String num) {
        int sum = 0;
        int product = 1;

        int[] digits = getDigitArray(number);

        for (int i = 0; i < digits.length; i++) {
            sum += digits[i];
            product *= digits[i];
        }

        return sum == product;
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
            } else if (Long.parseLong(num) == 7) {
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
