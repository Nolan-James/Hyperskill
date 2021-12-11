package search;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        HashMap<String, String> listOfPeopleFromFile = new LinkedHashMap<>();

        if (args[0].equals("--data")) {
            listOfPeopleFromFile = generateListOfPeopleFromFile(args[1]);
        } else {
            System.out.println("Error");
        }

        while (true) {
            showMenu();
            String choice = scanner.nextLine();
            switch (Integer.parseInt(choice)) {
                case 0:
                    break;
                case 1:
                    findPersonFromList(scanner, listOfPeopleFromFile);
                    break;
                case 2:
                    printAllPeople(listOfPeopleFromFile);
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
            if (Integer.parseInt(choice) == 0) {
                break;
            }
        }

    }

    private static HashMap<String, String> generateListOfPeopleFromFile(String arg) {
        Scanner scanner = null;
        HashMap<String, String> tempList = new LinkedHashMap<>();
        String line;
        File file = new File(arg);
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] parts = line.split(" ");
                if (parts.length == 1) {
                    tempList.put(parts[0].trim(), "".trim());
                } else if (parts.length == 2) {
                    tempList.put(parts[0].trim() + " " + parts[1].trim(), "".trim());
                } else {
                    String fullName = parts[0].trim() + " " + parts[1].trim();
                    tempList.put(fullName, parts[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
            file.delete();

        }


        return tempList;
    }

    private static void showMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }

    private static void findPersonFromList(Scanner scanner, HashMap<String, String> listOfPeople) {
        System.out.println("Enter a name or email to search all suitable people.");
        String searchTerm = scanner.nextLine();

        int wrongCount = 0;
        int correctCount = 0;

        for (String name : listOfPeople.keySet()) {
            if (name.toLowerCase().contains(searchTerm.toLowerCase()) || listOfPeople.get(name).toLowerCase().contains(searchTerm.toLowerCase())) {
                if (listOfPeople.get(name).equals("")) {
                    System.out.println(name.trim());
                } else {
                    System.out.println(name.trim() + " " + listOfPeople.get(name).trim());
                }
                correctCount++;
            } else {
                wrongCount++;
            }
        }
        if (wrongCount > 0 && correctCount == 0) {
            System.out.println("No matching people found.");
        }
    }

    private static void printAllPeople(HashMap<String, String> people) {
        System.out.println("=== List of people ===");
        for (String person : people.keySet()) {
            if (people.get(person).equals("")) {
                System.out.println(person);
            } else {
                System.out.println(person + " " + people.get(person));
            }
        }
    }


}
