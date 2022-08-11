package budget.sort_strategy_pattern;

import budget.Data;
import budget.PurchaseData;
import budget.enums.PurchaseActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConcreteSortByType implements SortingStrategy{
    @Override
    public List<PurchaseData> sort(Data data, int ordinal) {
       List<PurchaseData> list = new ArrayList<>();
        for (var PA :
                PurchaseActions.values()) {
            PurchaseData PD = new PurchaseData(data.getAmountMap().get(PA.ordinal()), PA.value);
            list.add(PD);
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
}
