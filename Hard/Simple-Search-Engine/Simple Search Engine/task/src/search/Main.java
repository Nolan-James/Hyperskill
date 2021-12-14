package search;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        HashMap<String, String> listOfPeopleFromFile = new LinkedHashMap<>();
        List<String> people = new ArrayList<>();
        Map<String, List<Integer>> listOfPeople = new HashMap<>();

        if (args[0].equals("--data")) {
//            listOfPeopleFromFile = generateListOfPeopleFromFile(args[1]);
            people = generateListOfPeopleStatic(args[1]);
            listOfPeople = generateListOfPeople(args[1]);
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
                    String strategy = selectStrategy(scanner);
                    findPersonFromList(scanner, listOfPeople, people, strategy);
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

    private static List<String> generateListOfPeopleStatic(String arg) {
        Scanner scanner = null;
        List<String> tempList = new ArrayList<>();
        File file = new File(arg);

        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                tempList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }

        return tempList;

    }

    private static Map<String, List<Integer>> generateListOfPeople(String arg) {
        Map<String, List<Integer>> tempList = new HashMap<>();
        Scanner scanner = null;
        File file = new File(arg);
        String line;
        int index = 0;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] parts = line.split(" ");
                int finalIndex = index;
                Arrays.stream(parts)
                        .forEach(word -> {
                            if (!tempList.containsKey(word)) {
                                List<Integer> values = new ArrayList<>();
                                values.add(finalIndex);
                                tempList.put(word.toLowerCase(), values);
                            } else {
                                List<Integer> values = tempList.get(word);
                                values.add(finalIndex);
                                tempList.put(word.toLowerCase(), values);
                            }
                        });
                index++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        for (String temp : tempList.keySet()) {
            System.out.println(temp + " : " + tempList.get(temp));
        }
        return tempList;
    }

    private static String selectStrategy(Scanner scanner) {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        return scanner.nextLine();
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
//            file.delete();

        }


        return tempList;
    }

    private static void showMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }

    private static void findPersonFromList(Scanner scanner, Map<String, List<Integer>> listOfPeople, List<String> people, String strategy) {
        System.out.println("Enter a name or email to search all suitable people.");
        String searchTerm = scanner.nextLine();

        switch (strategy) {
            case "ALL":
                SearchSender searchSenderAll = new SearchSender();
                searchSenderAll.setSearchMethod(new FindAll());
                searchSenderAll.search(listOfPeople, people, searchTerm);
                break;
            case "ANY":
                SearchSender searchSenderAny = new SearchSender();
                searchSenderAny.setSearchMethod(new FindAny());
                searchSenderAny.search(listOfPeople, people, searchTerm);
                break;
            case "NONE":
                SearchSender searchSenderNone = new SearchSender();
                searchSenderNone.setSearchMethod(new FindNone());
                searchSenderNone.search(listOfPeople, people, searchTerm);
                break;
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

    interface SearchMethod {
        void find(Map<String, List<Integer>> listOfPeople, List<String> people, String searchTerm);
    }

    static class FindAll implements SearchMethod {

        @Override
        public void find(Map<String, List<Integer>> listOfPeople, List<String> people, String searchTerm) {
            searchTerm = searchTerm.toLowerCase().trim();
            String[] terms = searchTerm.split(" ");
            Set<String> peopleFound = new HashSet<>();
            List<List<Integer>> values = new ArrayList<>();

            for (String term : terms) {
                for (String key : listOfPeople.keySet()) {
                    if (key.contains(term)) {
                        values.add(listOfPeople.get(key));
//                        System.out.println(listOfPeople.get(key));
//                        for (Integer value : listOfPeople.get(term)) {
//                            peopleFound.add(people.get(value));
//                            System.out.println(people.get(value));
//                        }
                    }
                }

            }

            values.stream()
                    .forEach(System.out::println);

            for (String person : people) {
                if (values.contains(person)) {
                    System.out.println(person);
                }
            }

            System.out.println(peopleFound.size() + " persons found:");
            for (String person : peopleFound) {
                System.out.println(person);
            }


//            int wrongCount = 0;
//            int correctCount = 0;
//            Set<String> names = new HashSet<>();
//            int num = 0;
//
//            for (String name : listOfPeople.keySet()) {
//                for (String word : name.split(" ")) {
//                    if (searchTerm.toLowerCase().contains(word.toLowerCase())) {
//                        if (listOfPeople.get(name).equals("")) {
//                            names.add(name.trim());
//                            num++;
//                        } else {
//                            names.add(name.trim() + " " + listOfPeople.get(name).trim());
//                            num++;
//                        }
//                        correctCount++;
//                    } else {
//                        wrongCount++;
//                    }
//                }
//            }
//
//            if (wrongCount > 0 && correctCount == 0) {
//                System.out.println("No matching people found.");
//            } else {
//                System.out.println(num + " persons found");
//                for (String name : names) {
//                    System.out.println(name);
//                }
//            }
        }
    }

    static class FindAny implements SearchMethod {

        @Override
        public void find(Map<String, List<Integer>> listOfPeople, List<String> people, String searchTerm) {
            System.out.println("ANY");
        }
    }

    static class FindNone implements SearchMethod {

        @Override
        public void find(Map<String, List<Integer>> listOfPeople, List<String> people, String searchTerm) {
            System.out.println("NONE");
        }
    }

    static class SearchSender {
        private SearchMethod searchMethod;

        public void setSearchMethod(SearchMethod searchMethod) {
            this.searchMethod = searchMethod;
        }

        public void search(Map<String, List<Integer>> listOfPeople, List<String> people, String searchTerm) {
            this.searchMethod.find(listOfPeople, people, searchTerm);
        }
    }


}
