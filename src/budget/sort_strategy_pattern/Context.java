package budget.sort_strategy_pattern;

import budget.Data;
import budget.PurchaseData;

import java.util.List;

public class Context {
    private final SortingStrategy sortingStrategy;
    private int ordinal;

    public Context(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public Context(SortingStrategy sortingStrategy, int ordinal) {
        this(sortingStrategy);
        this.ordinal = ordinal;
    }

    List<PurchaseData> sort(Data data) {
       return sortingStrategy.sort(data, ordinal);
    }

}
