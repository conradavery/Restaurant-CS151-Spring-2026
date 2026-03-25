package menuAndFoodItems;

import java.util.ArrayList;

import utilities.SystemLimits;
import utilities.exceptions.MaxInstancesException;
import utilities.exceptions.MenuItemNotFoundException;;

public class Menu {
    private ArrayList<FoodItem> items;
    private static int menuCount = 0;

    public Menu() throws MaxInstancesException {
        this.items = new ArrayList<>();
        if(menuCount > SystemLimits.MAXIMUM_INSTANCES){
            throw new MaxInstancesException("More than 100 menus have been created");
        }
        menuCount++;
    }

    public void addItem(FoodItem foodItem) {
        items.add(foodItem);
    }

    public FoodItem getItem(int index) throws MenuItemNotFoundException { //needs exception handling in here or in add order prob add order tbh
        try{
            return items.get(index - 1);
        } catch (IndexOutOfBoundsException e){
            throw new MenuItemNotFoundException("No menu item located at that index");
        }
    }

    public void removeItem(FoodItem item) {
        items.remove(item);
    }

    public FoodItem findItemByName(String name) throws MenuItemNotFoundException{
        for (FoodItem f : items) {
            if (f.getName().equalsIgnoreCase(name)) {
                return f;
            }
        }
        throw new MenuItemNotFoundException("Menu item not found");
    }

    public void changeItemPrice(FoodItem item, Double price) {
        item.changePrice(price);
    }

    @Override
    public String toString() {
        String r = "";
        for (int i = 0; i < items.size(); i++) {
            FoodItem item = items.get(i);
            r += ""+(i+1) + ") " + item;
        }
        return r;
    }

}
