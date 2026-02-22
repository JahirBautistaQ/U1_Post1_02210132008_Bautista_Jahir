package refactored;

public class VIPDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(double total) {
        return total * 0.80; // 20% siempre
    }
}