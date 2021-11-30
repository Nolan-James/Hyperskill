package numbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

class Number {

    long number;

    public long getNumber() {
        return this.number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    String numberAsString(long number) {
        return Objects.toString(number);
    }

    boolean isNatural(long number) {
        return number > 0;
    }

    void setResults(long number) {
        EvenNumber evenNumber = new EvenNumber();
        PropertyTerms.EVEN.setResult(evenNumber.isEven(number));
        OddNumber oddNumber = new OddNumber();
        PropertyTerms.ODD.setResult(oddNumber.isOdd(number));
        BuzzNumber buzzNumber = new BuzzNumber();
        PropertyTerms.BUZZ.setResult(buzzNumber.isBuzz(number));
        DuckNumber duckNumber = new DuckNumber();
        PropertyTerms.DUCK.setResult(duckNumber.isDuck(number));
        PalindromicNumber palindromicNumber = new PalindromicNumber();
        PropertyTerms.PALINDROMIC.setResult(palindromicNumber.isPalindromic(number));
        GapfulNumber gapfulNumber = new GapfulNumber();
        PropertyTerms.GAPFUL.setResult(gapfulNumber.isGapful(number));
        SpyNumber spyNumber = new SpyNumber();
        PropertyTerms.SPY.setResult(spyNumber.isSpy(number));
        SquareNumber squareNumber = new SquareNumber();
        PropertyTerms.SQUARE.setResult(squareNumber.isSquare(number));
        SunnyNumber sunnyNumber = new SunnyNumber();
        PropertyTerms.SUNNY.setResult(sunnyNumber.isSunny(number));
        JumpingNumber jumpingNumber = new JumpingNumber();
        PropertyTerms.JUMPING.setResult(jumpingNumber.isJumping(number));
    }

    ArrayList<String> setResultsArray(long number) {
        ArrayList<String> temp = new ArrayList<>();
        setResults(number);

        for (PropertyTerms propertyTerms : PropertyTerms.values()) {
            if (propertyTerms.result) {
                temp.add(propertyTerms.name().toLowerCase());
            }
        }
        return temp;
    }

    void printResults() {
        System.out.println("Properties of " + number);
        System.out.println("even: " + PropertyTerms.EVEN.result);
        System.out.println("odd: " + PropertyTerms.ODD.result);
        System.out.println("buzz: " + PropertyTerms.BUZZ.result);
        System.out.println("duck: " + PropertyTerms.DUCK.result);
        System.out.println("palindromic: " + PropertyTerms.PALINDROMIC.result);
        System.out.println("gapful: " + PropertyTerms.GAPFUL.result);
        System.out.println("spy: " + PropertyTerms.SPY.result);
        System.out.println("square: " + PropertyTerms.SQUARE.result);
        System.out.println("sunny: " + PropertyTerms.SUNNY.result);
        System.out.println("jumping: " + PropertyTerms.JUMPING.result);
    }

    int[] getDigitArray(long number) {
        String stringValue = String.valueOf(number);

        int[] digits = new int[stringValue.length()];

        for (int i = 0; i < digits.length; i++) {
            digits[i] = Integer.parseInt(String.valueOf(stringValue.charAt(i)));
        }
        return digits;
    }

}
