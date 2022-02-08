class Predicate {
    public static final TernaryIntPredicate ALL_DIFFERENT = ((x, y, z) -> x != y && x != z && y != z);

    @FunctionalInterface
    public interface TernaryIntPredicate {
        public boolean test(int x, int y, int z);
    }
}