public class OrderItem {
    private String name;
    private int quantity;
    private double price;

    public OrderItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // For debugging or display
    @Override
    public String toString() {
        return name + " x" + quantity + " ($" + String.format("%.2f", price) + " each)";
    }
}
