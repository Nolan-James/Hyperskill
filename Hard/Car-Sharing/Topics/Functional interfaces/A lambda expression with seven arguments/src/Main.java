class Seven {
    public static SeptenaryStringFunction fun = new SeptenaryStringFunction() {
        @Override
        public String apply(String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
            return s1.toUpperCase() + s2.toUpperCase() + s3.toUpperCase() + s4.toUpperCase() +
                    s5.toUpperCase() + s6.toUpperCase() + s7.toUpperCase();
        }
    };
}

@FunctionalInterface
interface SeptenaryStringFunction {
    String apply(String s1, String s2, String s3, String s4, String s5, String s6, String s7);
}