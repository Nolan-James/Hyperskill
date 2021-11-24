package machine;

public class Cappuccino {
    private int capWater;
    private int capeMilk;
    private int capBeans;
    private int capCost;

    public Cappuccino() {
        this.capWater = 200;
        this.capeMilk = 100;
        this.capBeans = 12;
        this.capCost = 6;
    }

    public int getCapWater() {
        return capWater;
    }

    public void setCapWater(int capWater) {
        this.capWater = capWater;
    }

    public int getCapeMilk() {
        return capeMilk;
    }

    public void setCapeMilk(int capeMilk) {
        this.capeMilk = capeMilk;
    }

    public int getCapBeans() {
        return capBeans;
    }

    public void setCapBeans(int capBeans) {
        this.capBeans = capBeans;
    }

    public int getCapCost() {
        return capCost;
    }

    public void setCapCost(int capCost) {
        this.capCost = capCost;
    }
}
