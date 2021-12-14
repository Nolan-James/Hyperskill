import java.util.*;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SortedMap<Integer, String> map = new TreeMap<>();
        String line1 = scanner.nextLine();
        String[] parts = line1.split(" ");

        int start = Integer.parseInt(parts[0]);
        int end = Integer.parseInt(parts[1]);

        int amount = Integer.parseInt(scanner.nextLine());
        int count = 0;

        while (count != amount) {
            String line = scanner.nextLine();
            String[] bits = line.split(" ");

            map.put(Integer.parseInt(bits[0]), bits[1]);

            count++;
        }

        SortedMap<Integer, String> rangedMap = map.subMap(start, end + 1);

        rangedMap.forEach((k, v) -> {
            System.out.println(k + " " + v);
        });

    }
}