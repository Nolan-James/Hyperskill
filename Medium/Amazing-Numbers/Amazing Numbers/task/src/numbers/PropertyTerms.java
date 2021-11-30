package numbers;

enum PropertyTerms {
    BUZZ(false),
    DUCK(false),
    PALINDROMIC(false),
    GAPFUL(false),
    SPY(false),
    EVEN(false),
    ODD(false),
    SQUARE(false),
    SUNNY(false),
    JUMPING(false);

    boolean result;

    PropertyTerms(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isProperty(String name) {
        return name.equalsIgnoreCase(this.name());
    }
}
