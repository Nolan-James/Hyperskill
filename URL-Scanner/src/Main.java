import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String url = scanner.nextLine();
        String[] data = url.split("[?=&]");

        if (data[1].equals("pass")) {
            System.out.println("pass : " + data[2]);
            for (int i = 3; i < data.length - 1; i++) {
                if (data[i + 1].equals("")) {
                    System.out.println(data[i] + " : " + "not found");
                    i++;
                } else {
                    System.out.println(data[i] + " : " + data[i + 1]);
                    i++;
                }

            }
            System.out.println("password : " + data[2]);
        } else {
            boolean passExists = false;
            String passValue = "";
            for (int i = 1; i < data.length - 1; i++) {
                if (data[i].equals("pass")) {
                    passExists = true;
                    passValue = data[i + 1];
                }
                if (data[i + 1].equals("")) {
                    System.out.println(data[i] + " : " + "not found");
                    i++;
                } else {
                    System.out.println(data[i] + " : " + data[i + 1]);
                    i++;
                }
            }
            if (passExists) {
                System.out.println("password : " + passValue);
            }
        }

    }
}