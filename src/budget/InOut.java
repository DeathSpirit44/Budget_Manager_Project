package budget;


import budget.enums.Actions;
import budget.enums.PurchaseActions;
import budget.enums.SortingActions;
import budget.sort_strategy_pattern.*;

import java.util.List;
import java.util.Scanner;


/**
 * Static class that handles all interactions between the program and the user
 */
final class InOut {

    /**
     * Play the menu and collects info that are stored in data
     * @param data Data object
     */
    static void playMenu(Data data) {
        boolean check = true;
        do {
            printMenu();
            int input = inputInt(0, Actions.values().length + 1);
            System.out.println();
            switch (input) {
                case 1 -> inputIncome(data);
                case 2 -> playMenuAddPurchase(data);
                case 3 -> playMenuShowPurchase(data);
                case 4 -> printBalance(data);
                case 5 -> {
                    FileHandling.save(data, "purchases.txt");
                    System.out.println("Purchases were saved!");
                }
                case 6 -> {
                    FileHandling.load(data, "purchases.txt");
                    System.out.println("Purchases were loaded!");
                }
                case 7 -> playSortingMenu(data);
                case 0 -> {
                    System.out.println("Bye!");
                    check = false;
                }
            }
            System.out.println();
        }
        while (check);
    }

    /**
     * Play the menu that shows the purchases
     * @param data data object
     */
    static private void playMenuShowPurchase(Data data) {
        boolean check = true;
        do {
            printShowPurchaseMenu();
            int ordinal = inputInt(1, PurchaseActions.values().length + 3) - 1;
            System.out.println();
            if (ordinal == PurchaseActions.values().length + 1) { // ALL option
                check = false;
                break;
            } else if (ordinal == PurchaseActions.values().length) { // BACK option
                printAllPurchases(data);
            } else {
                printListOfPurchases(data, ordinal);
            }
            System.out.println();
        } while (check);
    }

    /**
     * play the menu that add the purchases to certain categories
     * @param data data object
     */
    static private void playMenuAddPurchase(Data data) {
        boolean check = true;
        do {
            printAddPurchaseMenu();
            int ordinal = inputInt(1, PurchaseActions.values().length + 2) - 1;
            System.out.println();
            if (ordinal == PurchaseActions.values().length) { //Enum BACK
                check = false;
                break;
            } else {
                inputPurchase(data, ordinal);
            }
            System.out.println();
        } while (check);
    }

    /**
     * Print the main menu
     */
    private static void printMenu() {
        System.out.println("Choose your action :");
        for (Actions actions :
                Actions.values()) {
            System.out.printf("%d) %s\n", actions.actionNumber, actions.txt);
        }
    }

    /**
     * Ask user for an input until input is higher or equal than lower and lower dans upper
     * @return a valid positive integer
     */
    static private int inputInt(int lower, int upper) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int nextInt = scanner.nextInt();
            if (nextInt >= lower && nextInt < upper) {
                return nextInt;
            } else {
                System.out.println("Enter an integer between " + lower + " and " + (upper - 1));
                return inputInt(lower, upper);
            }
        } else {
            System.out.println("Enter an integer between " + lower + " and " + (upper - 1));
            return inputInt(lower, upper);
        }
    }

    /**
     * Ask the user to input as positive double
     * @return return valid positive double
     */
    static private double inputPosDouble() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextDouble()) {
            double nextInt = scanner.nextDouble();
            if (nextInt >= 0) {
                return nextInt;
            } else {
                System.out.println("Enter an positive value!");
                return inputPosDouble();
            }
        } else {
            System.out.println("Enter an positive value!");
            return inputPosDouble();
        }
    }

    /**
     * Ask user for an input income and add this income into data
     * @param data Data object representing the data of the budget manager
     */
    static private void inputIncome(Data data) {
        System.out.println("Enter income:");
        double income = inputPosDouble();
        data.setBalance(data.getBalance() + income);
        System.out.println("Income was added!");
    }

    /**
     * Print the balance
     * @param data Data object representing the data of the budget manager
     */
    static private void printBalance(Data data) {
        System.out.println("Balance: $" + data.getBalance());
    }

    /**
     * Print all the purchases of a categorie
     * @param data data object
     * @param ordinal ordinal of the categorie
     */
    static private void printListOfPurchases(Data data, Integer ordinal) {
        System.out.println(PurchaseActions.values()[ordinal].value +":");
        if (data.getPurchaseMap().get(ordinal).isEmpty()) {
            System.out.println("The purchase list is empty");
        } else {
            data.getPurchaseMap().get(ordinal).forEach((PD) -> System.out.println(PD.toString()));
            System.out.println("Total sum: $" + data.getAmountMap().get(ordinal));
        }
    }

    /**
     * print all the purchases
     * @param data data object
     */
    static private void printAllPurchases(Data data) {
        if (data.getTotalPurchasesAmount() == 0) {
            System.out.println("The purchase list is empty");
        } else {
            for (var entry :
                    data.getPurchaseMap().entrySet()) {
                data.getPurchaseMap().get(entry.getKey()).forEach((PD) -> System.out.println(PD.toString()));
            }
            System.out.println("Total sum $" + data.getTotalPurchasesAmount());
        }

    }

    /**
     * Get purchase info from user and it to data
     * @param data Data object
     */
    static private void inputPurchase(Data data, int ordinal) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter purchase name:");
        String name = scanner.nextLine();
        System.out.println("Enter its price");
        data.addPurchase(ordinal, name, inputPosDouble());
        System.out.println("Purchase was added!");
    }

    /**
     * Print the add purchase menu
     */
    static private void printAddPurchaseMenu() {
        System.out.println("Choose the type of purchases");
        for (var value :
                PurchaseActions.values()) {
            System.out.println((value.ordinal() + 1) + ") " + value.value);
        }
        System.out.println((PurchaseActions.values().length + 1) + ") Back");
    }

    /**
     * Print the categorie purchase menu
     */
    static private void printCategoriePurchaseMenu() {
        System.out.println("Choose the type of purchase");
        for (var value :
                PurchaseActions.values()) {
            System.out.println((value.ordinal() + 1) + ") " + value.value);
        }
        System.out.println();
    }

    /**
     * print the show purchase menu
     */
    static private void printShowPurchaseMenu() {
        System.out.println("Choose the type of purchases");
        for (var value :
                PurchaseActions.values()) {
            System.out.println((value.ordinal() + 1) + ") " + value.value);
        }
        System.out.println((PurchaseActions.values().length + 1) + ") All");
        System.out.println((PurchaseActions.values().length + 2) + ") Back");
    }

    /**
     * print the sorting menu
     */
    static private void printSortingMenu() {
        System.out.println("How do you want to sort?");
        for (SortingActions SA :
                SortingActions.values()) {
            System.out.println((SA.ordinal() + 1) +") "+ SA.value);
        }
    }

    /**
     * Play the sorting menu
     * @param data data object
     */
    static private void playSortingMenu(Data data) {
        boolean check = true;
        while (check) {
            printSortingMenu();
            System.out.println();
            int input = inputInt(1, SortingActions.values().length + 1);
            Context context = new Context();
            List<PurchaseData> list;
            switch (input) {
                case 1 -> {
                    context.setSortingStrategy(new ConcreteSortAll());
                    list = context.sort(data);
                    if (list.isEmpty()) {
                        System.out.println("The purchase list is empty");
                    } else {
                        list.forEach((PD) -> System.out.println(PD.toString()));
                    }
                }
                case 2 -> {
                    context.setSortingStrategy(new ConcreteSortByType());
                    list = context.sort(data);
                    System.out.println("Types:");
                    list.forEach((PD) -> System.out.println(PD.label() + " - $" + String.format("%.2f", PD.amount())));
                }
                case 3 -> {
                    printCategoriePurchaseMenu();
                    int ordinal = inputInt(1, PurchaseActions.values().length + 2) - 1;
                    context.setSortingStrategy(new ConcreteSortCertainType());
                    context.setOrdinal(ordinal);
                    list = context.sort(data);
                    if (list.isEmpty()) {
                        System.out.println("The purchase list is empty");
                    } else {
                        list.forEach((PD) -> System.out.println(PD.toString()));
                    }
                }
                default -> check = false;
            }
            System.out.println();
        }

    }
}
