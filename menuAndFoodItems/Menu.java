package menuAndFoodItems;

import java.util.ArrayList;

import utilities.SystemLimits;
import utilities.UI;
import utilities.exceptions.MaxInstancesException;

public class Menu {
    private ArrayList<FoodItem> items;
    private static int menuCount = 0;

    public Menu() throws MaxInstancesException {
        this.items = new ArrayList<>();
        if(menuCount >= SystemLimits.MAXIMUM_INSTANCES){
            throw new MaxInstancesException("More than 100 menus have been created");
        }
        menuCount++;
    }

    public void addItem(FoodItem foodItem) {
        items.add(foodItem);
    }

    public FoodItem getItem(int index) { //needs exception handling in here or in add order prob add order tbh
        return items.get(index - 1);
    }

    public void removeItem(FoodItem item) {
        items.remove(item);
    }

    public FoodItem findItemByName(String name) {
        for (FoodItem f : items) {
            if (f.getName().equalsIgnoreCase(name)) {
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
        System.out.println("-----------------------------------------------");
    }

}
