package menuAndFoodItems;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<FoodItem> items;

    public Menu(){
        this.items = new ArrayList<>();
    }

    public void addItem(){
        System.out.println("What is the food item's name: ");
        String name = scanner.nextLine();
        System.out.println("How many calories is the food item: ");
        int calories = scanner.nextInt();
        scanner.nextLine();
        System.out.println("What is the food item's price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        FoodItem item = new FoodItem(name, calories, price);
        items.add(item);
    }
    public void removeItem(String name){
        for (FoodItem f: items){
            if (f.getName().equals(name)){
                items.remove(f);
                break;
            }
        }
    }
    public void printMenu(){
        for (FoodItem f: items){
            System.out.println(f.getName() + " price: " + f.getPrice() + " calories: " + f.getCalories());
        }
    }
    
}
