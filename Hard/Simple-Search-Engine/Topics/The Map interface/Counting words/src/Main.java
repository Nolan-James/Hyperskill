import java.util.*;

class MapUtils {

    public static SortedMap<String, Integer> wordCount(String[] strings) {
        TreeMap<String, Integer> map = new TreeMap<>();
        for (String string : strings) {
            if (map.containsKey(string)) {
                int value = map.get(string) + 1;
                map.put(string, value);
            } else {
                map.put(string, 1);
            }
        }
        return map;
    }

    public static void printMap(Map<String, Integer> map) {
        for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        MapUtils.printMap(MapUtils.wordCount(words));
    }
}