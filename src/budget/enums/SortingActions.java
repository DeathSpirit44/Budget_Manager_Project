package budget.enums;

public enum SortingActions {
    SORTALL("Sort all purchases"),
    SORTBYTYPE("Sort by type"),
    SORTCERTAINTYPE("Sort certain type"),
    BACK("Back");

    public String value;


    SortingActions(String value) {
        this.value = value;
    }
}
