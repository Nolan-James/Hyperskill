package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Machine coffeeMachine = new Machine();

        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (true) {
            if (command.equals("exit")) {
                break;
            }
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            command = scanner.nextLine();

            switch (command) {
                case "remaining":
                    coffeeMachine.printContents();
                    break;
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                    int drink = -1;
                    String back = scanner.nextLine();
                    if (back.equals("back")) {
                        break;
                    } else {
                        drink = Integer.parseInt(back);
                    }


                    if (drink == 1) {
                        Espresso espresso = new Espresso();
                        coffeeMachine.checkContentsOfEspresso(espresso);
                        break;
                    } else if (drink == 2) {
                        Latte latte = new Latte();
                        coffeeMachine.checkContentsOfLatte(latte);
                        break;
                    } else if (drink == 3) {
                        Cappuccino cappuccino = new Cappuccino();
                        coffeeMachine.checkContentsOfCappuccino(cappuccino);
                        break;
                    }
                case "fill":
                    coffeeMachine.fill(scanner);
                    break;
                case "take":
                    coffeeMachine.take();
                    break;
                case "exit":
                    break;
            }

        }

    }
}
