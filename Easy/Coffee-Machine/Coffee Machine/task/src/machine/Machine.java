package machine;

import java.util.Scanner;

public class Machine {
    private int waterRequired;
    private int milkRequired;
    private int beansRequired;
    private int disposableCups;
    private int money;

    public Machine() {
        this.waterRequired = 400;
        this.milkRequired = 540;
        this.beansRequired = 120;
        this.disposableCups = 9;
        this.money = 550;
    }

    public int getWaterRequired() {
        return waterRequired;
    }

    public void setWaterRequired(int waterRequired) {
        this.waterRequired = waterRequired;
    }

    public int getMilkRequired() {
        return milkRequired;
    }

    public void setMilkRequired(int milkRequired) {
        this.milkRequired = milkRequired;
    }

    public int getBeansRequired() {
        return beansRequired;
    }

    public void setBeansRequired(int beansRequired) {
        this.beansRequired = beansRequired;
    }

    public int getDisposableCups() {
        return disposableCups;
    }

    public void setDisposableCups(int disposableCups) {
        this.disposableCups = disposableCups;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void printContents() {
        System.out.println("The coffee machine has:");
        System.out.println(this.waterRequired + " ml of water");
        System.out.println(this.milkRequired + " ml of milk");
        System.out.println(this.beansRequired + " g of coffee beans");
        System.out.println(this.disposableCups + " disposable cups");
        System.out.println("$" + this.money + " of money");
    }

    public void checkContentsOfEspresso(Espresso espresso) {
        if (this.waterRequired < espresso.getEspressoWater()) {
            System.out.println("Sorry, not enough water!");
        }

        if (this.beansRequired < espresso.getEspressoBeans()) {
            System.out.println("Sorry, not enough coffee beans!");
        }

        if (this.waterRequired > espresso.getEspressoWater() && this.beansRequired > espresso.getEspressoBeans()) {
            System.out.println("I have enough resources, making you a coffee!");
            this.waterRequired -= espresso.getEspressoWater();
            this.beansRequired -= espresso.getEspressoBeans();
            this.money += espresso.getEspressoCost();
            this.disposableCups--;
        }
    }

    public void checkContentsOfLatte(Latte latte) {
        if (this.waterRequired < latte.getLatteWater()) {
            System.out.println("Sorry, not enough water!");
        }

        if (this.milkRequired < latte.getLatteMilk()) {
            System.out.println("Sorry, not enough milk!");
        }

        if (this.beansRequired < latte.getLatteBeans()) {
            System.out.println("Sorry, not enough coffee beans!");
        }

        if (this.waterRequired >= latte.getLatteWater()
                && this.beansRequired >= latte.getLatteBeans() && this.milkRequired >= latte.getLatteMilk()) {
            System.out.println("I have enough resources, making you a coffee!");
            this.waterRequired -= latte.getLatteWater();
            this.milkRequired -= latte.getLatteMilk();
            this.beansRequired -= latte.getLatteBeans();
            this.money += latte.getLatteCost();
            this.disposableCups--;
        }
    }

    public void checkContentsOfCappuccino(Cappuccino cappuccino) {
        if (this.waterRequired < cappuccino.getCapWater()) {
            System.out.println("Sorry, not enough water!");
        }

        if (this.milkRequired < cappuccino.getCapeMilk()) {
            System.out.println("Sorry, not enough milk!");
        }

        if (this.beansRequired < cappuccino.getCapBeans()) {
            System.out.println("Sorry, not enough coffee beans!");
        }

        if (this.waterRequired > cappuccino.getCapWater()
                && this.beansRequired > cappuccino.getCapBeans() && this.milkRequired > cappuccino.getCapeMilk()) {
            System.out.println("I have enough resources, making you a coffee!");
            this.waterRequired -= cappuccino.getCapWater();
            this.milkRequired -= cappuccino.getCapeMilk();
            this.beansRequired -= cappuccino.getCapBeans();
            this.money += cappuccino.getCapCost();
            this.disposableCups--;
        }
    }

    public void fill(Scanner scanner) {
        System.out.println("Write how many ml of water you want to add:");
        this.waterRequired += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        this.milkRequired += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        this.beansRequired += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        this.disposableCups += scanner.nextInt();
    }

    public void take() {
        this.money = 0;
    }
}
