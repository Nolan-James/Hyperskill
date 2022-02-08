import java.util.function.LongUnaryOperator;

class Operator {

    public static LongUnaryOperator unaryOperator = (value) -> {
        value = value + 1;
        while (true) {
            if (value % 2 == 0) {
                return value;
            } else {
                value++;
            }
        }
    };

}