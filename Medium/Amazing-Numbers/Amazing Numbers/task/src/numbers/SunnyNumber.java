package numbers;

public class SunnyNumber extends Number {

    boolean isSunny(long number) {
        double square_root = Math.sqrt(number + 1);

        return ((square_root - Math.floor(square_root)) == 0);
    }
}
