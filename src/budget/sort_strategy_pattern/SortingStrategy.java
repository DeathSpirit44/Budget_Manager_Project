package budget.sort_strategy_pattern;

import budget.Data;
import budget.PurchaseData;

import java.util.List;

/**
 * Interface representing the strategy for sorting in Strategy pattern
 */
public interface SortingStrategy {
    /**
     *
     * @param data data to be sorted
     * @param ordinal ordinaal of the categorie to be sorted. Take -1 if not used
     * @return
     */
    List<PurchaseData> sort(Data data, int ordinal);
}
