package phonebook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> namesAndNumbersList = new ArrayList<>();
        ArrayList<String> namesToFindList = new ArrayList<>();
        int match = 0;
        long startTime = 0;
        long endTime = 0;

        try {
            Scanner find = new Scanner(Paths.get("D:\\Tutorials\\Hyperskill-Backend-Developer\\Medium\\Phone-Book\\find.txt"));
            Scanner list = new Scanner(Paths.get("D:\\Tutorials\\Hyperskill-Backend-Developer\\Medium\\Phone-Book\\directory.txt"));

            while (list.hasNextLine()) {
                namesAndNumbersList.add(list.nextLine());
            }

            while (find.hasNextLine()) {
                namesToFindList.add(find.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        startTime = System.currentTimeMillis();
        for (int i = 0; i < namesAndNumbersList.size(); i++) {
            for (int j = 0; j < namesToFindList.size(); j++) {
                if (namesAndNumbersList.get(i).substring(namesAndNumbersList.get(i).indexOf(" ") + 1).equals(namesToFindList.get(j))) {
                    match++;
                }
            }
        }
        endTime = System.currentTimeMillis();

        long timeDifference = endTime - startTime;
        long minutes = (timeDifference / 1000) / 60;
        long seconds = (timeDifference / 1000) % 60;
        long ms = timeDifference - ((minutes * 60000) + (seconds * 1000));

        System.out.println("Start searching...");
        System.out.printf("Found %d / %d entries.  Time taken %d min. %d sec. %d ms.", match, match, minutes, seconds, ms);
    }
}
