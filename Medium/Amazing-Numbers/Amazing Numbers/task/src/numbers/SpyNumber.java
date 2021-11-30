package numbers;

public class SpyNumber extends Number {

    boolean isSpy(long number) {
        int sum = 0;
        int product = 1;

        int[] digits = getDigitArray(number);

        for (int i = 0; i < digits.length; i++) {
            sum += digits[i];
            product *= digits[i];
        }

        return sum == product;
    }
}
