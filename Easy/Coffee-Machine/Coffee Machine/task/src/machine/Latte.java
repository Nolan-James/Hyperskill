package machine;

public class Latte {
    private int latteWater;
    private int latteMilk;
    private int latteBeans;
    private int latteCost;

    public Latte() {
        this.latteWater = 350;
        this.latteMilk = 75;
        this.latteBeans = 20;
        this.latteCost = 7;
    }

    public int getLatteWater() {
        return latteWater;
    }

    public void setLatteWater(int latteWater) {
        this.latteWater = latteWater;
    }

    public int getLatteMilk() {
        return latteMilk;
    }

    public void setLatteMilk(int latteMilk) {
        this.latteMilk = latteMilk;
    }

    public int getLatteBeans() {
        return latteBeans;
    }

    public void setLatteBeans(int latteBeans) {
        this.latteBeans = latteBeans;
    }

    public int getLatteCost() {
        return latteCost;
    }

    public void setLatteCost(int latteCost) {
        this.latteCost = latteCost;
    }
}
