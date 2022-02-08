import java.util.*;
import java.util.stream.Collectors;

class ProcessNumbers {

    public static List<Integer> processNumbers(Collection<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n >= 10)
                .sorted()
                .collect(Collectors.toList());
    }







    public static void main(String[] args) {
        List<Integer> fibonacci = List.of(0, 1, 1, 2, 3, 5, 8, 13);
        List<Integer> result = fibonacci.stream()
                .dropWhile(n -> n > 5)
                .collect(Collectors.toList());
        System.out.println(result);
//        Scanner scanner = new Scanner(System.in);
//
//        Collection<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
//                .map(Integer::parseInt)
//                .collect(Collectors.toCollection(HashSet::new));
//
//        String result = processNumbers(numbers).stream()
//                .map(String::valueOf)
//                .collect(Collectors.joining(" "));
//
//        System.out.println(result);
    }
}