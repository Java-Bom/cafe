package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bill {
    private final Map<Menu, Long> bills;

    public Bill(final Map<Menu, Long> bills) {
        this.bills = bills;
    }

    public List<BillTemplate> getBillTemplates() {
        List<BillTemplate> billTemplates = new ArrayList<>();
        for (Map.Entry<Menu, Long> bill : bills.entrySet()) {
            billTemplates.add(new BillTemplate(bill.getKey().getName(), bill.getValue(), bill.getKey().getPrice() * bill.getValue()));
        }
        return billTemplates;
    }

    public int count() {
        return bills.size();
    }

    public static class BillTemplate {
        private final String name;
        private final Long count;
        private final Long prices;

        public BillTemplate(final String name, final Long count, final Long prices) {
            this.name = name;
            this.count = count;
            this.prices = prices;
        }

        public String getName() {
            return name;
        }

        public Long getCount() {
            return count;
        }

        public Long getPrice() {
            return prices;
        }
    }
}
