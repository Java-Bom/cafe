package cafe.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Bill {
    private final Map<Menu, Long> bills;

    public Bill(final Map<cafe.domain.Menu, Long> bills) {
        this.bills = bills;
    }

    public List<BillTemplate> getBillTemplates() {
        List<BillTemplate> billTemplates = new ArrayList<>();
        for (Map.Entry<cafe.domain.Menu, Long> bill : bills.entrySet()) {
            billTemplates.add(new BillTemplate(bill.getKey().getName(), bill.getValue(), bill.getKey().getPrice() * bill.getValue()));
        }
        return billTemplates;
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

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final BillTemplate that = (BillTemplate) o;
            return Objects.equals(name, that.name) &&
                    Objects.equals(count, that.count) &&
                    Objects.equals(prices, that.prices);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, count, prices);
        }
    }
}
