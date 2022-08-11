package budget.enums;

/**
 * Enumerate type that represent all the actions the user can perform from the sorting menu
 */
public enum SortingActions {
    SORTALL("Sort all purchases"),
    SORTBYTYPE("Sort by type"),
    SORTCERTAINTYPE("Sort certain type"),
    BACK("Back");

    public final String value;


    SortingActions(String value) {
        this.value = value;
    }
}
