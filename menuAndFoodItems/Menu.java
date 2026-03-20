package menuAndFoodItems;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<FoodItem> items;

    public Menu(){
        this.items = new ArrayList<>();
    }

    public void addItem(FoodItem foodItem){
        items.add(foodItem);
    }
    public void removeItem(String name){
        for (FoodItem f: items){
            if (f.getName().equals(name)){
                items.remove(f);
                break;
            }
        }
    }
    public void changeItemPrice(String name, Double price){
        for (FoodItem f: items){
            if (f.getName().equals(name)){
                f.changePrice(price);
            }
        }
    }
    public void printMenu(){
        for (FoodItem f: items){
            System.out.println(f.getName() + " price: " + f.getPrice() + " calories: " + f.getCalories());
        }
    }
    
}
