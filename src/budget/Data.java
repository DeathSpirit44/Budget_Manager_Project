package budget;


import budget.enums.PurchaseActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing all the data associated to the budget manager and therefore purchases, income, etc...
 */
public class Data {
    private final Map<Integer, Double> amountMap = new HashMap<>(); // Les clés sont hachés par l'ordinal de l'énum purchase actions
    private final Map<Integer, List<PurchaseData>> purchaseMap = new HashMap<>();
    private double balance;
    private double totalPurchasesAmount;

    public Data() {
        this.balance = 0;
        for (var value :
                PurchaseActions.values()) {
            purchaseMap.put(value.ordinal(), new ArrayList<>());
            amountMap.put(value.ordinal(), 0D);
            this.totalPurchasesAmount = 0;
        }
    }

    public double getTotalPurchasesAmount() {
        return totalPurchasesAmount;
    }

    public void setTotalPurchasesAmount(double totalPurchasesAmount) {
        this.totalPurchasesAmount = totalPurchasesAmount;
    }

    public Map<Integer, Double> getAmountMap() {
        return amountMap;
    }

    public Map<Integer, List<PurchaseData>> getPurchaseMap() {
        return purchaseMap;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = Math.max(balance, 0);
    }

    /**
     * method that add a purchase in the system
     * @param ordinal the ordinal of the categorie from PurchaseAction enum
     * @param name the name of the purchase
     * @param value the amount of the purchase
     */
    void addPurchase(Integer ordinal, String name, double value) {
        amountMap.put(ordinal, amountMap.get(ordinal) + value);
        balance -= value;
        balance = Math.max(balance, 0);
        totalPurchasesAmount += value;
        purchaseMap.get(ordinal).add(new PurchaseData(value, name));
    }
}
