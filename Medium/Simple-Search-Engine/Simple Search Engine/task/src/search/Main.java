package search;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        int amountOfPeople = getAmountOfPeople(scanner);

        HashMap<String, String> listOfPeople = generateListOfPeople(amountOfPeople, scanner);

        while (true) {
            showMenu();
            String choice = scanner.nextLine();
            switch (Integer.parseInt(choice)) {
                case 0:
                    break;
                case 1:
                    findPersonFromList(scanner, listOfPeople);
                    break;
                case 2:
                    printAllPeople(listOfPeople);
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
            if (Integer.parseInt(choice) == 0) {
                break;
            }
        }

    }

    private static int getAmountOfPeople(Scanner scanner) {
        System.out.println("Enter the number of people:");
        String line = scanner.nextLine();
        return Integer.parseInt(line);

    }

    private static HashMap<String, String> generateListOfPeople(int amountOfPeople, Scanner scanner) {
        HashMap<String, String> tempList = new HashMap<>();
        int start = 0;
        String line;
        System.out.println("Enter all people:");

        while (true) {
            if (start == amountOfPeople) {
                break;
            }
            line = scanner.nextLine();

            if (line.equals(" ")) {
                break;
            } else {
                String[] parts = line.split(" ");
                if (parts.length == 1) {
                    tempList.put(parts[0], "");
                } else if (parts.length == 2) {
                    tempList.put(parts[0], parts[1]);
                } else {
                    String fullName = parts[0] + " " + parts[1];
                    tempList.put(fullName, parts[2]);
                }

            }
            start++;
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
                System.out.println(name.trim() + " " + listOfPeople.get(name).trim());
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
            System.out.println(person + " " + people.get(person));
        }
    }


}
