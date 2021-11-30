package numbers;

public class JumpingNumber extends Number {

    boolean isJumping(long number) {
        String numberString = String.valueOf(number);
        int before = 0;
        int next = 0;
        int numberToCompare = 0;
        boolean result = false;

        for (int i = 0; i < numberString.length() - 1; i++) {
            numberToCompare = Integer.parseInt(String.valueOf(numberString.charAt(i)));
            before = numberToCompare - 1;
            next = numberToCompare + 1;

            int nextNumber = Integer.parseInt(String.valueOf(numberString.charAt(i + 1)));
            if (nextNumber == before ||
                    nextNumber == next ||
                    nextNumber == numberToCompare) {
                result = true;
            } else {
                result = false;
                break;
            }
        }

        return result;
    }
}
