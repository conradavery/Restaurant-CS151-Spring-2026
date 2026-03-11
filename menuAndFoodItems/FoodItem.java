package menuAndFoodItems;

public class FoodItem {
    
    private String name;
    private int calories;
    private double price;

    public FoodItem(String name, int calories, double price){
        this.name = name;
        this.calories = calories;
        this.price =price;
    }
    public String getName(){
        return this.name;
    }
    public int getCalories(){
        return this.calories;
    }
    public double getPrice(){
        return this.price;
    }
}
