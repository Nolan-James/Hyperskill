package search;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> people = new LinkedHashMap<>();

        System.out.println("Enter the number of people:");
        String line = scanner.nextLine();
        int amount = Integer.parseInt(line);
        int start = 0;

        System.out.println("Enter all people:");

        while (true) {
            if (start == amount) {
                break;
            }
            line = scanner.nextLine();

            if (line.equals(" ")) {
                break;
            } else {
                String[] parts = line.split(" ");
                if (parts.length == 1) {
                    people.put(parts[0], "");
                } else if (parts.length == 2) {
                    people.put(parts[0], parts[1]);
                } else {
                    String fullName = parts[0] + " " + parts[1];
                    people.put(fullName, parts[2]);
                }

            }
            start++;
        }

        System.out.println("Enter the number of search queries:");
        int amountQueries = Integer.parseInt(scanner.nextLine());
        int queryCount = 0;

        while (true) {
            if (queryCount == amountQueries) {
                break;
            }

            System.out.println("Enter data to search people:");
            String searchTerm = scanner.nextLine().toLowerCase();
            int wrongCount = 0;
            int correctCount = 0;

            for (String name : people.keySet()) {
                if (name.contains(searchTerm.toLowerCase()) || people.get(name).contains(searchTerm.toLowerCase())) {
                    System.out.println(name.trim() + " " + people.get(name).trim());
                    correctCount++;
                } else {
                    wrongCount++;
                }
            }

            if (wrongCount > 0 && correctCount == 0) {
                System.out.println("No matching people found.");
            }

            queryCount++;
        }


    }
    

}
