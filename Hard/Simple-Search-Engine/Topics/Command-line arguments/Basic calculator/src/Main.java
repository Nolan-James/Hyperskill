class Problem {
    public static void main(String[] args) {
        String operator = args[0];
        int first = Integer.parseInt(args[1]);
        int second = Integer.parseInt(args[2]);

        switch (operator) {
            case "+":
                int sum = first + second;
                System.out.println(sum);
                break;
            case "-":
                int sumMinus = first - second;
                System.out.println(sumMinus);
                break;
            case "*":
                int sumTimes = first * second;
                System.out.println(sumTimes);
                break;
            default:
                System.out.println("Unknown operator");

        }

    }
}