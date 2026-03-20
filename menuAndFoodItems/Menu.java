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
    public FoodItem getItem(int index){
        return items.get(index -1);
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
        for (int i=0; i<items.size();i++){
            System.out.println((i+1) + ": " + items.get(i).getName() + " $" + items.get(i).getPrice() + ", calories: " + items.get(i).getCalories());
        }
    }
    
}
