package budget;


import java.util.Objects;

/**
 * Record to store the data of a purchase
 * @param amount amount of a purchase
 * @param label label of a purchase
 */
public record PurchaseData(double amount, String label) implements Comparable<PurchaseData> {
    public PurchaseData(double amount, String label) {
        this.amount = amount;
        this.label = Objects.requireNonNullElse(label, "");
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", this.label, this.amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        PurchaseData PD = (PurchaseData) obj;

        if (this.amount != PD.amount) {
            return false;
        }

        if (!this.label.equals(PD.label)) {
            return false;
        }

        return true;
    }

    @Override
    public int compareTo(PurchaseData o) {
        Double it = this.amount;
        Double their = o.amount;
        return it.compareTo(their);
    }
}

