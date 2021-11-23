package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        int waterRequired = 200;
        int milkRequired = 50;
        int beansRequired = 15;

        int cupsMade = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water the coffee machine has:");
        int water = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has: ");
        int milk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int coffeeBeans = scanner.nextInt();

        System.out.println("Write how many cups of coffee you will need:");
        int amount = scanner.nextInt();

        for (int i = 1; i <= amount; i++) {
            if (water > waterRequired) {
                water -= waterRequired;
                if (milk > milkRequired) {
                    milk -= milkRequired;
                    if (coffeeBeans > beansRequired) {
                        coffeeBeans -= beansRequired;

                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
            cupsMade += 1;
        }

        if (cupsMade == 0) {
            System.out.println("No, I can make only " + cupsMade + " cup(s) of coffee");
        }

        if (cupsMade < amount) {
            System.out.println("No, I can make only " + cupsMade + " cup(s) of coffee");
        }

        if (cupsMade >= amount) {
            int waterOver = (int) Math.floor(water / waterRequired);
            int milkOver = (int) Math.floor(milk / milkRequired);
            int beansOver = (int) Math.floor(coffeeBeans / beansRequired);

            int cupsOver = 0;

            if (waterOver <= milkOver && waterOver <= beansOver) {
                cupsOver = waterOver;
            } else if (milkOver <= beansOver && milkOver <= waterOver) {
                cupsOver = milkOver;
            } else {
                cupsOver = beansOver;
            }

            if (cupsOver == 0) {
                System.out.println("Yes, I can make that amount of coffee");
            } else {
                System.out.println("Yes, I can make that amount of coffee (and even " + cupsOver + " more than that)");
            }
        }


        // Task 2/6
//        System.out.println("For " + amount + " cups of coffee you will need:");
//
//        System.out.println(amount * water + " ml of water");
//        System.out.println(amount * milk + " ml of milk");
//        System.out.println(amount * coffeeBeans + " g of coffee beans");

        // Task 1/6
//        System.out.println("Starting to make a coffee");
//        System.out.println("Grinding coffee beans");
//        System.out.println("Boiling water");
//        System.out.println("Mixing boiled water with crushed coffee beans");
//        System.out.println("Pouring coffee into the cup");
//        System.out.println("Pouring some milk into the cup");
//        System.out.println("Coffee is ready!");
    }
}
