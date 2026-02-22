package refactored;

public class Order {

    private final String id;
    private final String customer;
    private final String product;
    private final double total;
    private String status;

    public Order(String id, String customer, String product, double total) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.total = total;
        this.status = "PENDING";
    }

    public String getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public String getProduct() {
        return product;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
