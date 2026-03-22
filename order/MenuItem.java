package order;

import utilities.SystemLimits;

public class MenuItem {

    private static int instanceCounter = 0;
    
    private String name;
    private int calories;
    private double price;
    private String menuItemId; 
    private int quantity;

    public MenuItem(String name, int calories, double price, String menuItemId, int quantity) {
        this.name = name;
        this.calories = calories;
        this.price = price;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    public static MenuItem createMenuItem(String name, int calories, double price, String menuItemId, int quantity) {
        if (instanceCounter >= SystemLimits.MAXIMUM_INSTANCES) {
            throw new IllegalStateException("Maximum number of MenuItem instances reached.");
        }
        instanceCounter++;
        return new MenuItem(name, calories, price, menuItemId, quantity);
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    //Getters and Setters

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public double getPrice() {
        return price;
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
