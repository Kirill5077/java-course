import java.time.LocalDate;

public class DiscountProduct extends Product {
    private double discount; // размер скидки в %
    private LocalDate expiryDate; // срок действия скидки

    public DiscountProduct(String name, double cost, double discount, LocalDate expiryDate) {
        super(name, cost);
        setDiscount(discount);
        this.expiryDate = expiryDate;
    }

    @Override
    public double getCost() {
        if (expiryDate != null && LocalDate.now().isBefore(expiryDate)) {
            return super.getCost() * (1 - discount / 100.0);
        }
        return super.getCost();
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        if (discount < 0 || discount > 90) {
            throw new IllegalArgumentException("Скидка должна быть в пределах 0–90%");
        }
        this.discount = discount;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return super.getName() + " (со скидкой " + discount + "%, цена: " + getCost() + ")";
    }
}
