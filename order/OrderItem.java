package order;

public class OrderItem {
    
    private String foodItem;
    private int quantity;
    private double price;

    public OrderItem(String foodItem, int quantity, double price) {
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = price;
    }

    public String getFoodItem() {
        return foodItem;
    }   

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
