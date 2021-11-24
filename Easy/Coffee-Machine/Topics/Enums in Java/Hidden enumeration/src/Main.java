public class Main {

    public static void main(String[] args) {
        Secret[] secrets = Secret.values();
        int count = 0;
        for (Secret secret : secrets) {
            String word = secret.toString();
            if (word.startsWith("STAR")) {
                count++;
            }
        }
        System.out.println(count);
    }
}

/* sample enum for inspiration */
//enum Secret {
//    STAR, CRASH, START
//}
