package numbers;

public class PalindromicNumber extends Number {

    public boolean isPalindromic(long number) {
        int trueCount = 0;
        int falseCount = 0;
        int[] digits = this.getDigitArray(number);
        for (int i = 0, j = digits.length - 1; i < digits.length; i++, j--) {
            if (digits[i] == digits[j]) {
                trueCount++;
            } else {
                falseCount++;
            }
        }
        return trueCount == digits.length;
    }
}
