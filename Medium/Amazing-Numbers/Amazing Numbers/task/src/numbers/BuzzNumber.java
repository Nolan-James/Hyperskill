package numbers;

import java.util.Objects;

public class BuzzNumber extends Number {

    public boolean isBuzz(long number) {
        String num = Objects.toString(number);
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
}
