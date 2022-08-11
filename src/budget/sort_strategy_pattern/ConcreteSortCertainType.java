package budget.sort_strategy_pattern;

import budget.Data;
import budget.PurchaseData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConcreteSortCertainType implements SortingStrategy {
    @Override
    public List<PurchaseData> sort(Data data, int ordinal) {
        List<PurchaseData> listType = new ArrayList<>(data.getPurchaseMap().get(ordinal));
        Collections.sort(listType, Collections.reverseOrder());
        return listType;
    }
}
