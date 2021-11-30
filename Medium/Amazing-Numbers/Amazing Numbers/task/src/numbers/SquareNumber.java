package numbers;

public class SquareNumber extends Number{

    boolean isSquare(long number) {
        double square_root = Math.sqrt(number);

        return ((square_root - Math.floor(square_root)) == 0);
    }
}
