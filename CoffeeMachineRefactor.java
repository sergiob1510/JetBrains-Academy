package machine;

import java.util.Scanner;

public class CoffeeMachine {
    final static Scanner scanner = new Scanner(System.in);
    final static Machine coffeeMachine = new Machine();

    public static void main(String[] args) {
        mainLoop();
    }
    public static void mainLoop() {
        boolean use_machine = true;
        while (use_machine) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.nextLine().trim().toLowerCase();
            switch (action) {
                case "buy" -> buyMenu();
                case "fill" -> fillMenu();
                case "take" -> coffeeMachine.takeMoney();
                case "remaining" -> coffeeMachine.printMachineStatus();
                case "exit" -> use_machine = false;
                default -> System.out.println("Sorry, unknown action");
            }
        }
    }
    public static void buyMenu() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String option = scanner.nextLine().toLowerCase();
        switch (option) {
            case "1" -> coffeeMachine.makeCoffee(coffeeMachine.espresso);
            case "2" -> coffeeMachine.makeCoffee(coffeeMachine.latte);
            case "3" -> coffeeMachine.makeCoffee(coffeeMachine.cappuccino);
            case "back" -> {}
            default -> System.out.println("Sorry, unknown command");
        }
    }
    public static void fillMenu() {
        System.out.println("Write how many ml of water you want to add:");
        int waterToAdd = scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        int milkToAdd = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        int coffeeToAdd = scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        int cupsToAdd = scanner.nextInt();
        coffeeMachine.fillMachine(waterToAdd, milkToAdd, coffeeToAdd, cupsToAdd);
    }
}

class Machine {
    int moneyInMachine = 550;
    int waterInMachine = 400;
    int milkInMachine = 540;
    int coffeeInMachine = 120;
    int cupsInMachine = 9;

    public record Recipe(int water, int milk, int coffee, int money) {}

    Recipe espresso = new Recipe(250,0,16,4);
    Recipe latte = new Recipe(350, 75, 20, 7);
    Recipe cappuccino = new Recipe(200, 100, 12,6);


    public void printMachineStatus() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water \n", waterInMachine);
        System.out.printf("%d ml of milk \n", milkInMachine);
        System.out.printf("%d g of coffee beans \n", coffeeInMachine);
        System.out.printf("%d disposable cups \n", cupsInMachine);
        System.out.printf("$%d of money \n", moneyInMachine);
    }
    public void makeCoffee(Recipe recipe) {
        if (checkMachineResources(recipe.water, recipe.milk, recipe.coffee )) {
            waterInMachine -= recipe.water;
            milkInMachine -= recipe.milk;
            coffeeInMachine -= recipe.coffee;
            cupsInMachine -= 1;
            moneyInMachine += recipe.money;
            System.out.println("I have enough resources, making you a coffee!");
        }
    }
    public boolean checkMachineResources(int water, int milk, int coffee) {
        if (waterInMachine < water) { System.out.println("Sorry, not enough water!"); return false; }
        if (milkInMachine < milk) { System.out.println("Sorry, not enough milk!"); return false; }
        if (coffeeInMachine < coffee) { System.out.println("Sorry, not enough coffee beans!"); return false; }
        if (cupsInMachine < 1) { System.out.println("Sorry, not enough cups!"); return false; }
        return true;
    }
    public void fillMachine(int waterRefill, int milkRefill, int coffeeRefill, int cupsRefill) {
        waterInMachine += waterRefill;
        milkInMachine += milkRefill;
        coffeeInMachine += coffeeRefill;
        cupsInMachine += cupsRefill;
    }
    public void takeMoney() {
        System.out.printf("I gave you $%d \n", moneyInMachine);
        moneyInMachine = 0;
    }
}
