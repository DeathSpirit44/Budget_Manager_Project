package budget.enums;

public enum Actions {
    ADD_INCOME("Add income", 1),
    ADD_PURCHASE("Add purchase", 2),
    SHOW_LIST_PURCHASE("Show list of purchases", 3),
    BALANCE("Balance", 4),
    SAVE("Save", 5),
    LOAD("Load", 6),
    ANALYZE("Analyze (Sort)", 7),
    EXIT("Exit", 0);
    public final String txt;
    public int actionNumber;

    Actions(String txt, int actionNumber) {
        this.txt = txt;
        this.actionNumber = actionNumber;
    }
}
