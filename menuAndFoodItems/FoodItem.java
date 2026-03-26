package menuAndFoodItems;

import utilities.SystemLimits;
import utilities.UI;
import utilities.exceptions.MaxInstancesException;

public class FoodItem {

    private String name;
    private int calories;
    private double price;
    public static int FoodItemCount = 0;

    public FoodItem(String name, int calories, double price) throws MaxInstancesException {
        this.name = name;
        this.calories = calories;
        this.price = price;
        if(FoodItemCount >= SystemLimits.MAXIMUM_INSTANCES){
            throw new MaxInstancesException("More than 100 Food Items have been created");
        }
        FoodItemCount++;
    }

    public String getName() {
        return this.name;
    }

    public int getCalories() {
        return this.calories;
    }

    public double getPrice() {
        return this.price;
    }

    public void changePrice(double price) {
        this.price = price;
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

    @Override
    public String toString() {
        return String.format("%-20s %10s   %s%n", getName(), UI.money(getPrice()), "(" + getCalories() + " cal)");
    }
}
