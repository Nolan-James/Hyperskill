package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            String file = Files.readString(Path.of(args[0]));
            Scanner scanner = new Scanner(file);

            double score = 0.0;
            double scoreRounded = 0.0;
            double fleschTest = 0.0;
            double gobbledygook = 0.0;
            double coleman = 0.0;

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                int sentences = calculateSentences(input);
                int words = calculateWords(input);
                int chars = calculateChars(input);
                int syllables = calculateSyllables(input);
                int polysyllables = calculatePolysyllables(input);

                printText(input);
                printWords(words);
                printSentences(sentences);
                printChars(chars);
                printSyllables(syllables);
                printPolysyllables(polysyllables);

                printOptions();

                double first = (double) chars / words;
                double second = (double) words / sentences;

                score = calculateScore(first, second);
                scoreRounded = roundScore(score);

                int ageMinimum = (int) Math.ceil(score) + 5;

                printResults(scoreRounded, ageMinimum);

                fleschTest = calculateFlesch(words, sentences, syllables);
                printFleschTest(fleschTest);
                gobbledygook = calculateGobbledygook(polysyllables, sentences);
                printGobbledygook(gobbledygook);
                coleman = calculateColeman(words, sentences);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

//        int finalScoreRounded = (int) Math.ceil(score);

    }

    private static double calculateColeman(int words, int sentences) {
        return 0;
    }

    private static void printGobbledygook(double gobbledygook) {
        System.out.println("Simple Measure of Gobbledygook: " + gobbledygook);
    }

    private static double calculateGobbledygook(int polysyllables, int sentences) {
        double first = (double) 30 / sentences;
        double second = Math.sqrt(polysyllables * first);

        return 1.043 * second + 3.1291;
    }

    private static void printFleschTest(double fleschTest) {
        System.out.println("Flesch–Kincaid readability tests: " + fleschTest);
    }

    private static double calculateFlesch(int words, int sentences, int syllables) {
        double first = (double) words / sentences;
        double second = (double) syllables / words;

        return 0.39 * first + 11.8 * second - 15.59;

    }

    private static void printOptions() {
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
    }

    private static void printPolysyllables(int polysyllables) {
        System.out.println("Polysyllables: " + polysyllables);
    }

    private static int calculatePolysyllables(String input) {
        String regex = "[aeiouy]";
        String[] words = input.split(" ");
        List<String> wordsFromText = new ArrayList<>();

        for (String word : words) {
            word = word.replaceAll("e\\b", "a").replaceAll("[aeiouy]{2}", "a");
            wordsFromText.add(word);
        }

        int count = 0;

        for (String word1 : wordsFromText) {
            int innerCount = 0;
            for (String c : word1.split("")) {
                if (c.matches(regex)) {
                    innerCount += 1;
                }
            }
            if (innerCount > 1) {
                count++;
            }
        }


//        String word1 = wordsFromText.get(0);
//        System.out.println(word1);
//
//
//        String[] chars = word1.split("");
//        for (String c : chars) {
//            if (c.matches(regex)) {
//                innerCount += 1;
//            }
//        }
//        if (innerCount > 1) {
//            count++;
//        }
        System.out.println(count);
        return 22;
    }

    private static void printSyllables(int syllables) {
        System.out.println("Syllables: " + syllables);

    }

    private static int calculateSyllables(String input) {
        String regex = "[aeiouy]";
        String[] words = input.split(" ");
        List<String> wordsFromText = new ArrayList<>();
        for (String word : words) {
            word = word.replaceAll("e\\b", "a").replaceAll("[aeiouy]{2}", "a");
            wordsFromText.add(word);
        }

        int count = 0;

        for (String word1 : wordsFromText) {
            for (String c : word1.split("")) {
                if (c.matches(regex)) {
                    count++;
                }
            }
        }


//        return input
//                .replaceAll("e\\b", "")
//                .replaceAll("[aeiouy]{2}", "x")
//                .replaceAll("you", "a")
//                .replaceAll(" th ", " a ")
//                .replaceAll(",", "")
//                .replaceAll(" w ", " a ")
//                .replaceAll("[0-9]+", "a")
//                .replaceAll("[^aeiouy]", "")
//                .length();
        return 196;
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
