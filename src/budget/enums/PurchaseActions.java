package budget.enums;


/**
 * Enumerate type that represent the categories of purchases.
 * The storing of the purchases associated rely on a hashmap where the ordinal is the key
 */
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
