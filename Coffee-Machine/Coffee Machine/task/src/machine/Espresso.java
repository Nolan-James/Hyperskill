package machine;

public class Espresso {
    private int espressoWater;
    private int espressoBeans;
    private int espressoCost;

    public Espresso() {
        this.espressoWater = 250;
        this.espressoBeans = 16;
        this.espressoCost = 4;
    }

    public int getEspressoWater() {
        return espressoWater;
    }

    public void setEspressoWater(int espressoWater) {
        this.espressoWater = espressoWater;
    }

    public int getEspressoBeans() {
        return espressoBeans;
    }

    public void setEspressoBeans(int espressoBeans) {
        this.espressoBeans = espressoBeans;
    }

    public int getEspressoCost() {
        return espressoCost;
    }

    public void setEspressoCost(int espressoCost) {
        this.espressoCost = espressoCost;
    }
}
