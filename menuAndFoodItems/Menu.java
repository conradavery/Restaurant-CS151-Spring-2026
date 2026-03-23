package menuAndFoodItems;

import java.util.ArrayList;
import java.util.Scanner;
import utilities.UI;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<FoodItem> items;

    public Menu() {
        this.items = new ArrayList<>();
    }

    public void addItem(FoodItem foodItem) {
        items.add(foodItem);
    }

    public FoodItem getItem(int index) {
        return items.get(index - 1);
    }

    public void removeItem(FoodItem item) {
        items.remove(item);
    }

    public FoodItem findItemByName(String name) {
        for (FoodItem f : items) {
            if (f.getName().equals(name)) {
                return f;
            }
        }
        return null;
    }

    public void changeItemPrice(FoodItem item, Double price) {
        item.changePrice(price);
    }

    public void printMenu() {
        for (int i = 0; i < items.size(); i++) {
            FoodItem item = items.get(i);
            System.out.printf("%-3s %-20s %10s   %s%n", (i + 1) + ")", item.getName(), UI.money(item.getPrice()),
                    "(" + item.getCalories() + " cal)");
        }
        System.out.println("----------------------------------------");
    }

}
