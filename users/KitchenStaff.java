package users;

import restaurant.Restaurant;
import utilities.Input;
import utilities.SystemLimits;
import utilities.UI;
import utilities.exceptions.MaxInstancesException;
import utilities.exceptions.OrderNotFoundException;
import order.Order;

public class KitchenStaff extends Staff {

    private static int kitchenStaffCount = 0;

    public KitchenStaff(String name, String role, double salary, Restaurant restaurant, String staffID) throws MaxInstancesException {
        super(name, role, salary, restaurant, staffID);
        if(kitchenStaffCount >= SystemLimits.MAXIMUM_INSTANCES){
            throw new MaxInstancesException("More than 100 Kitchen Staff have been created");
        }
        kitchenStaffCount ++;
    }

    @Override
    public void performDuties() {
        String choice = "";
        while (!choice.equals("5")) {
            UI.printHeader("KITCHEN STAFF MENU");
            System.out.println("1) View all orders");
            System.out.println("2) View a specific order's details");
            System.out.println("3) Mark an order as preparing");
            System.out.println("4) Mark an order as complete");
            System.out.println("5) To go back");
            System.out.println("Type the number of what task you want to perform");
            System.out.println();
            System.out.print("Choice: ");
            choice = Input.getString();
            System.out.println();
            switch (choice) {
                case "1":
                    viewPendingOrders();
                    break;
                case "2":
                    viewOrderDetails();
                    break;
                case "3":
                    markOrderAsPreparing();
                    break;
                case "4":
                    markOrderAsComplete();
                case "5":
                    break;
                default:
                    UI.error("Invalid Choice");
                    break;
            }
        }
    }

    private void viewPendingOrders() {
        try {
            restaurant.viewOrders();
        } catch (OrderNotFoundException e) {
            UI.info(e.getMessage());
        }
    }
    private void viewOrderDetails(){
        System.out.print("Enter the order number: ");
        try {
            int orderID = Input.getInt();
            Order order = restaurant.findOrder(orderID);
            UI.info("Order #" + orderID + " Details");
            System.out.println(order);
        } catch (OrderNotFoundException e) {
            UI.error(e.getMessage());
        } catch (NumberFormatException e) {
            UI.error("Incorrect order number format.");
        }
    }

    private void markOrderAsPreparing() {
        UI.printSection("PREPARING ORDER");
        System.out.print("Enter the order number: ");
        try {
            int orderID = Input.getInt();
            Order order = restaurant.findOrder(orderID);
            UI.success("Changing order to preparing");
            order.setStatusPreparing();
        } catch (OrderNotFoundException e) {
            UI.error(e.getMessage());
        } catch (NumberFormatException e) {
            UI.error("Incorrect order number format.");
        }

    }

    private void markOrderAsComplete() {
        UI.printSection("COMPLETING ORDER");
        System.out.print("Enter the order number: ");
        try {
            int orderID = Input.getInt();
            Order order = restaurant.findOrder(orderID);
            UI.success("Changing order to complete");
            order.setStatusComplete();
        } catch (OrderNotFoundException e) {
            UI.error(e.getMessage());
        } catch (NumberFormatException e) {
            UI.error("Incorrect order number format");
        }

    }

}
