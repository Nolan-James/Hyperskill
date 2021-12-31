package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            String file = Files.readString(Path.of(args[0]));
            Scanner scanner = new Scanner(file);

            double score = 0.0;
            double scoreRounded = 0.0;

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                int sentences = calculateSentences(input);
                int words = calculateWords(input);
                int chars = calculateChars(input);
                int syllables = calculateSyllables(input);

                printText(input);
                printWords(words);
                printSentences(sentences);
                printChars(chars);
                printSyllables(syllables);

                double first = (double) chars / words;
                double second = (double) words / sentences;

                score = calculateScore(first, second);
                scoreRounded = roundScore(score);

                int ageMinimum = (int) Math.ceil(score) + 5;

                printResults(scoreRounded, ageMinimum);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

//        int finalScoreRounded = (int) Math.ceil(score);

    }

    private static void printSyllables(int syllables) {
        System.out.println("Syllables: " + syllables);

    }

    private static int calculateSyllables(String input) {
        String regex = "[aeiouy]{2}";
        return input.replaceAll("e", "z").replaceAll(regex, "").length();
    }

    private static void printResults(double scoreRounded, int ageMinimum) {
        System.out.println("The score is: " + scoreRounded);
        if (ageMinimum == 18) {
            System.out.println("This text should be understood by " + ageMinimum + "-24-year-olds.");
        } else {
            System.out.println("This text should be understood by " + ageMinimum + "-" + (ageMinimum + 1) + "-year-olds.");
        }
    }

    private static double roundScore(double score) {
        return Math.round(score * 100.0) / 100.0;
    }

    private static double calculateScore(double first, double second) {
        return 4.71 * first + 0.5 * second - 21.43;
    }

    private static void printChars(int chars) {
        System.out.println("Characters: " + chars);
    }

    private static void printSentences(int sentences) {
        System.out.println("Sentences: " + sentences);
    }

    private static void printWords(int words) {
        System.out.println("Words: " + words);
    }

    private static void printText(String input) {
        System.out.println("The text is: ");
        System.out.println(input + "\n");
    }

    private static int calculateChars(String input) {
        return input.replaceAll("\\s", "").length();
    }

    private static int calculateWords(String input) {
        return input.split(" ").length;
    }

    private static int calculateSentences(String input) {
        String regex = "[.!?]";
        return input.split(regex).length;
    }
}
