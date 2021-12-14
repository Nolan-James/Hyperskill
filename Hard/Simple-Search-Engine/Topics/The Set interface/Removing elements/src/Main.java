import java.util.*;

class SetUtils {

    public static Set<Integer> getSetFromString(String str) {
        String[] parts = str.split(" ");
        Set<Integer> tempSet = new HashSet<>();

        for (String part : parts) {
            tempSet.add(Integer.parseInt(part));
        }
        return tempSet;
    }

    public static void removeAllNumbersGreaterThan10(Set<Integer> set) {
        set.removeIf(s -> s > 10);
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numbers = scanner.nextLine();
        Set<Integer> set = SetUtils.getSetFromString(numbers);
        SetUtils.removeAllNumbersGreaterThan10(set);
        set.forEach(e -> System.out.print(e + " "));
    }
}