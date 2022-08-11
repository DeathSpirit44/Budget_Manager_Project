package budget.sort_strategy_pattern;

import budget.Data;
import budget.PurchaseData;
import budget.enums.PurchaseActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Concrete strategy for sorting all purchases
 */
public class ConcreteSortAll implements SortingStrategy {
    @Override
    public List<PurchaseData> sort(Data data, int ordinal) {
        List<PurchaseData> bigList = new ArrayList<>();
        for (var PA :
                PurchaseActions.values()) {
            bigList.addAll(data.getPurchaseMap().get(PA.ordinal()));
        }
        bigList.sort(Collections.reverseOrder());
        return bigList;
    }
}
