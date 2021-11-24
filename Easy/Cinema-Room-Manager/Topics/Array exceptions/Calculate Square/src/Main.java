class FixingExceptions {

    public static void calculateSquare(int[] array, int index) {
        try {
            int squared = array[index] * array[index];
            System.out.println(squared);
        } catch (Exception e) {
            System.out.println("Exception!");
        }

    }
}