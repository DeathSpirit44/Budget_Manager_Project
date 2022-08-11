package budget;


import budget.enums.PurchaseActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * add a purchase to data
     *
     * @param name  name of the purchase
     * @param value value of the purchase
     */
    void addPurchase(Integer ordinal, String name, double value) {
        amountMap.put(ordinal, amountMap.get(ordinal) + value);
        balance -= value;
        balance = Math.max(balance, 0);
        totalPurchasesAmount += value;
        purchaseMap.get(ordinal).add(new PurchaseData(value, name));
    }
}
