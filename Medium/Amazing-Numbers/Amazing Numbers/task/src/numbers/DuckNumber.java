package numbers;

import java.util.Objects;

public class DuckNumber extends Number {

    public boolean isDuck(long number) {
        String num = Objects.toString(number);
        int[] digits = new int[num.length()];
        boolean result = false;

        for (int i = 0; i < digits.length; i++) {
            digits[i] = Integer.parseInt(String.valueOf(num.charAt(i)));
        }

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
}
