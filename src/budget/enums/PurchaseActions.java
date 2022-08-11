package budget.enums;


public enum PurchaseActions {
    FOOD("Food"),
    CLOTHES("Clothes"),
    ENTERTAINMENT("Entertainment"),
    OTHER("Other");
    public final String value;

    PurchaseActions(String value) {
        this.value = value;
    }


}
