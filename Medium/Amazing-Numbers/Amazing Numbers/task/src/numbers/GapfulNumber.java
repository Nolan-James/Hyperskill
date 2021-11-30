package numbers;

import java.util.Objects;

public class GapfulNumber extends Number {

    boolean isGapful(long number) {
        if (Objects.toString(number).length() >= 3) {
            String numberString = Objects.toString(number);
            String firstDigit = (String.valueOf(numberString.charAt(0)));
            String lastDigit = (String.valueOf(numberString.charAt(numberString.length() - 1)));
            String concat = firstDigit + lastDigit;
            int modifier = Integer.parseInt(concat);

            return number % modifier == 0;
        } else {
            return false;
        }

    }
}
