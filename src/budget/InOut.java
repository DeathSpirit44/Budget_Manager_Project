package budget;


import budget.enums.Actions;
import budget.enums.PurchaseActions;
import budget.enums.SortingActions;
import budget.sort_strategy_pattern.ConcreteSortAll;
import budget.sort_strategy_pattern.ConcreteSortByType;
import budget.sort_strategy_pattern.ConcreteSortCertainType;
import budget.sort_strategy_pattern.SortingStrategy;

import java.io.File;
import java.util.List;
import java.util.Scanner;



final class InOut {

    /**
     * Play the menu and collects info that are stored in data
     *
     * @param data Data object
     */
    static void playMenu(Data data) {
        boolean check = true;
        do {
            printMenu();
            int input = inputInt(0, Actions.values().length + 1);
            System.out.println();
            switch (input) {
                case 1:
                    inputIncome(data);
                    break;
                case 2:
                    playMenuAddPurchase(data);
                    break;
                case 3:
                    playMenuShowPurchase(data);
                    break;
                case 4:
                    printBalance(data);
                    break;
                case 5:
                    FileHandling.save(data, "purchases.txt");
                    System.out.println("Purchases were saved!");
                    break;
                case 6:
                    FileHandling.load(data, "purchases.txt");
                    System.out.println("Purchases were loaded!");
                    break;
                case 7:
                    playSortingMenu(data);
                    break;
                case 0:
                    System.out.println("Bye!");
                    check = false;
                    break;
            }
            System.out.println();
        }
        while (check);
    }

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
     * Print the budgetmanager menu
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
     *
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
     *
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
     *
     * @param data Data object representing the data of the budget manager
     */
    static private void printBalance(Data data) {
        System.out.println("Balance: $" + data.getBalance());
    }

    static private void printListOfPurchases(Data data, Integer ordinal) {
        System.out.println(PurchaseActions.values()[ordinal].value +":");
        if (data.getPurchaseMap().get(ordinal).isEmpty()) {
            System.out.println("The purchase list is empty");
        } else {
            data.getPurchaseMap().get(ordinal).forEach((PD) -> System.out.println(PD.toString()));
            System.out.println("Total sum: $" + data.getAmountMap().get(ordinal));
        }
    }

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
     *
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

    static private void printAddPurchaseMenu() {
        System.out.println("Choose the type of purchases");
        for (var value :
                PurchaseActions.values()) {
            System.out.println((value.ordinal() + 1) + ") " + value.value);
        }
        System.out.println((PurchaseActions.values().length + 1) + ") Back");
    }

    static private void printCategoriePurchaseMenu() {
        System.out.println("Choose the type of purchase");
        for (var value :
                PurchaseActions.values()) {
            System.out.println((value.ordinal() + 1) + ") " + value.value);
        }
        System.out.println();
    }

    static private void printShowPurchaseMenu() {
        System.out.println("Choose the type of purchases");
        for (var value :
                PurchaseActions.values()) {
            System.out.println((value.ordinal() + 1) + ") " + value.value);
        }
        System.out.println((PurchaseActions.values().length + 1) + ") All");
        System.out.println((PurchaseActions.values().length + 2) + ") Back");
    }

    static private void printSortingMenu() {
        System.out.println("How do you want to sort?");
        for (SortingActions SA :
                SortingActions.values()) {
            System.out.println((SA.ordinal() + 1) +") "+ SA.value);
        }
    }

    static private void playSortingMenu(Data data) {
        boolean check = true;
        while (check) {
            printSortingMenu();
            System.out.println();
            int input = inputInt(1, SortingActions.values().length + 1);
            SortingStrategy sortingStrategy;
            List<PurchaseData> list;
            switch (input) {
                case 1:
                    sortingStrategy = new ConcreteSortAll();
                    list = sortingStrategy.sort(data, -1);
                    if (list.isEmpty()) {
                        System.out.println("The purchase list is empty");
                    } else {
                        list.forEach((PD) -> System.out.println(PD.toString()));
                    }
                    break;
                case 2:
                    sortingStrategy = new ConcreteSortByType();
                    list = sortingStrategy.sort(data, -1);
                    System.out.println("Types:");
                    list.forEach((PD) -> System.out.println(PD.getLabel() + " - $" + String.format("%.2f", PD.getAmount() )));
                    break;
                case 3:
                    printCategoriePurchaseMenu();
                    int ordinal = inputInt(1, PurchaseActions.values().length + 2) - 1;
                    sortingStrategy = new ConcreteSortCertainType();
                    list = sortingStrategy.sort(data, ordinal);
                    if (list.isEmpty()) {
                        System.out.println("The purchase list is empty");
                    } else {
                        list.forEach((PD) -> System.out.println(PD.toString()));
                    }
                    break;
                default:
                    check = false;

            }
            System.out.println();
        }

    }
}
