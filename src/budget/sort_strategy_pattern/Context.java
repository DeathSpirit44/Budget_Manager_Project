package budget.sort_strategy_pattern;

import budget.Data;
import budget.PurchaseData;

import java.util.List;

/**
 * Class represention the Context for the sorting Strategy pattern
 */
public class Context {
    private SortingStrategy sortingStrategy;
    private int ordinal;

    public void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public List<PurchaseData> sort(Data data) {
        return sortingStrategy.sort(data, ordinal);
    }

}
