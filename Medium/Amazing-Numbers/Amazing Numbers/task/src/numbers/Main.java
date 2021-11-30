package numbers;

import org.w3c.dom.ls.LSOutput;

import java.util.*;


public class Main {
    enum PropertyTerms {
        BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY, JUMPING
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isEvenOrOdd = false;
        boolean isBuzz = false;
        boolean isDuck = false;
        boolean isPalindromic = false;
        boolean isGapful = false;
        boolean isSpy = false;
        boolean isSunny = false;
        boolean isSquare = false;
        boolean isJumping = false;
        long number = 0;
        String num = "";

        welcome();

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
                        isSquare = getIsSquare(number);
                        isSunny = getIsSquare(number + 1);
                        isJumping = getIsJumping(number);

                        System.out.println("Properties of " + number);
                        System.out.println("even: " + isEvenOrOdd);
                        System.out.println("odd: " + !isEvenOrOdd);
                        System.out.println("buzz: " + isBuzz);
                        System.out.println("duck: " + isDuck);
                        System.out.println("palindromic: " + isPalindromic);
                        System.out.println("gapful: " + isGapful);
                        System.out.println("spy: " + isSpy);
                        System.out.println("square: " + isSquare);
                        System.out.println("sunny: " + isSunny);
                        System.out.println("jumping: " + isJumping);
                    } else {
                        System.out.println("The first parameter should be a natural number or zero.");
                    }
                }
            } else if (parts.length == 3) {
                number = Long.parseLong(parts[0]);
                int iterations = Integer.parseInt(parts[1]);
                String property = parts[2].toLowerCase();
                boolean isProperty = false;

                for (PropertyTerms propertyFromEnum : PropertyTerms.values()) {
                    if (property.toLowerCase().equals(propertyFromEnum.toString().toLowerCase())) {
                        isProperty = true;
                    }
                }
                if (isProperty) {
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
                                isSquare = getIsSquare(number);
                                isSunny = getIsSquare(number + 1);


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
                                if (isSquare) results.add("square");
                                if (isSunny) results.add("sunny");
                                if (isJumping) results.add("jumping");

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
                    System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY]");

                }
            } else if (parts.length == 4) {
                number = Long.parseLong(parts[0]);
                int iterations = Integer.parseInt(parts[1]);
                String property = parts[2].toLowerCase();
                String property2 = parts[3].toLowerCase();
                boolean isProperty = false;
                boolean isProperty2 = false;

                for (PropertyTerms propertyFromEnum : PropertyTerms.values()) {
                    if (property.toLowerCase().equals(propertyFromEnum.toString().toLowerCase())) {
                        isProperty = true;
                    }
                    if (property2.toLowerCase().equals(propertyFromEnum.toString().toLowerCase())) {
                        isProperty2 = true;
                    }
                }

                if (isProperty && isProperty2) {
                    if ((property.equals("sunny") && property2.equals("square") || (property.equals("square") && property2.equals("sunny")))) {
                        System.out.println("The request contains mutually exclusive properties: [SQUARE, SUNNY]");
                        System.out.println("There are no numbers with these properties.");
                    } else if ((property.equals("odd") && property2.equals("even") || (property.equals("even") && property2.equals("odd")))) {
                        System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]");
                        System.out.println("There are no numbers with these properties.");
                    } else if ((property.equals("spy") && property2.equals("duck") || (property.equals("duck") && property2.equals("spy")))) {
                        System.out.println("The request contains mutually exclusive properties: [SPY, DUCK]");
                        System.out.println("There are no numbers with these properties.");
                    } else {
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
                                    isSquare = getIsSquare(number);
                                    isSunny = getIsSquare(number + 1);

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
                                    if (isSquare) results.add("square");
                                    if (isSunny) results.add("sunny");

                                    if (results.contains(property) && results.contains(property2)) {
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
                    }
                } else {
//                    System.out.println("The properties [" + property + ", " + property2 + "]  is wrong.");
                    if ((!isProperty) && (!isProperty2)) {
                        System.out.println("The properties [" + property + ", " + property2 + "] are wrong.");
                    } else if (!isProperty) {
                        System.out.println("The property [" + property + "] is wrong.");
                    } else {
                        System.out.println("The property [" + property + "] is wrong.");
                    }
                    System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY]");

                }

            } else if (parts.length > 4) {
                number = Long.parseLong(parts[0]);
                int iterations = Integer.parseInt(parts[1]);
                HashMap<String, Boolean> properties = new HashMap<>();
                for (int i = 2; i < parts.length; i++) {
                    properties.put(parts[i].toLowerCase(), false);
                }

                for (String property : properties.keySet()) {
                    for (PropertyTerms propertyTerms : PropertyTerms.values()) {
                        if (property.equalsIgnoreCase(propertyTerms.toString())) {
                            properties.put(String.valueOf(properties.get(property)), true);
                        }
                    }
                }


//                for (Boolean value : properties.values()) {
//                    System.out.println(value);
//                }

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
                        isSquare = getIsSquare(number);
                        isSunny = getIsSquare(number + 1);

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
                        if (isSquare) results.add("square");
                        if (isSunny) results.add("sunny");

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
                    System.out.println("The second parameter should be a natural number.");
                }
            }
        }

    }

    private static boolean getIsJumping(long number) {
        String numberString = String.valueOf(number);
        int before = 0;
        int next = 0;
        int numberToCompare = 0;
        boolean result = false;

        for (int i = 0; i < numberString.length() - 1; i++) {
            numberToCompare = Integer.parseInt(String.valueOf(numberString.charAt(i)));
            before = numberToCompare - 1;
            next = numberToCompare + 1;

            if (Integer.parseInt(String.valueOf(numberString.charAt(i + 1))) == before ||
                    Integer.parseInt(String.valueOf(numberString.charAt(i + 1))) == next ||
                    Integer.parseInt(String.valueOf(numberString.charAt(i + 1))) == numberToCompare) {
                result = true;
            } else {
                result = false;
                break;
            }
        }

        return result;

    }

    private static boolean getIsSquare(long number) {
        double square_root = Math.sqrt(number);

        return ((square_root - Math.floor(square_root)) == 0);
    }

    private static void welcome() {
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
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
