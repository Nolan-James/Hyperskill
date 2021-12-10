import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

class Problem {
    public static void main(String[] args) {
        HashMap<String, String> values = new HashMap<>();

        values.put(args[0], args[1]);
        values.put(args[2], args[3]);

        System.out.println(values.getOrDefault("mode", "default"));

    }
}