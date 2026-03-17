package order;

public class OrderItems {
    
    private String foodItem;
    private int quantity;

    public OrderItems(String foodItem, int quantity){
        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public String getFoodItem() {
        return foodItem;
    }   

    public int getQuantity() {
        return quantity;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
