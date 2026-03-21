package users;

  import java.util.Scanner;
  import restaurant.Restaurant;
  import menuAndFoodItems.FoodItem;

  public class Manager extends Staff {

      private Scanner scanner;

      public Manager(String name, String role, double salary, Restaurant restaurant,
  String staffID) {
          super(name, role, salary, restaurant, staffID);
          this.scanner = new Scanner(System.in);
      }

      @Override
      public void performDuties() {
          System.out.println();
          System.out.println("==== MANAGER MENU ====");
          System.out.println("1: Manage Menu");
          System.out.println("2: Manage Staff");
          System.out.println("3: Go back");
          System.out.println();

          String choice = "";
          while (!choice.equals("3")) {
              System.out.print("Choice: ");
              choice = scanner.nextLine();
              System.out.println();

              switch (choice) {
                  case "1":
                      manageMenu();
                      break;
                  case "2":
                      manageStaff();
                      break;
                  case "3":
                      break;
                  default:
                      System.out.println("Invalid choice");
                      break;
              }
          }
      }

      private void manageMenu() {
          System.out.println("==== MENU MANAGEMENT ====");
          System.out.println("1: Add item to menu");
          System.out.println("2: Remove item from menu");
          System.out.println("3: Change item price");
          System.out.println("4: View menu");
          System.out.println("5: Go back");
          System.out.println();

          String choice = "";
          while (!choice.equals("5")) {
              System.out.print("Choice: ");
              choice = scanner.nextLine();
              System.out.println();

              switch (choice) {
                  case "1":
                      addItemToMenu();
                      break;
                  case "2":
                      removeItemFromMenu();
                      break;
                  case "3":
                      changeItemPrice();
                      break;
                  case "4":
                      viewMenu();
                      break;
                  case "5":
                      break;
                  default:
                      System.out.println("Invalid choice");
                      break;
              }
          }
      }

      private void manageStaff() {
          System.out.println("==== STAFF MANAGEMENT ====");
          System.out.println("1: Hire new employee");
          System.out.println("2: View all staff");
          System.out.println("3: Go back");
          System.out.println();

          String choice = "";
          while (!choice.equals("3")) {
              System.out.print("Choice: ");
              choice = scanner.nextLine();
              System.out.println();

              switch (choice) {
                  case "1":
                      hireEmployee();
                      break;
                  case "2":
                      viewAllStaff();
                      break;
                  case "3":
                      break;
                  default:
                      System.out.println("Invalid choice");
                      break;
              }
          }
      }

      // ========== MENU MANAGEMENT METHODS (NOW IMPLEMENTED) ==========

      private void addItemToMenu() {
          System.out.print("Enter item name: ");
          String name = scanner.nextLine();

          System.out.print("Enter calories: ");
          int calories;
          try {
              calories = Integer.parseInt(scanner.nextLine());
              if (calories < 0) {
                  System.out.println("Calories cannot be negative.");
                  return;
              }
          } catch (NumberFormatException e) {
              System.out.println("Invalid number format.");
              return;
          }

          System.out.print("Enter price: ");
          double price;
          try {
              price = Double.parseDouble(scanner.nextLine());
              if (price <= 0) {
                  System.out.println("Price must be greater than 0.");
                  return;
              }
          } catch (NumberFormatException e) {
              System.out.println("Invalid price format.");
              return;
          }

          FoodItem newItem = new FoodItem(name, calories, price);
          restaurant.addItemToMenu(newItem);
          System.out.println(name + " added to menu successfully!");
      }

      private void removeItemFromMenu() {
          System.out.print("Enter item name to remove: ");
          String name = scanner.nextLine();
          restaurant.removeItem(name);
          System.out.println(name + " removed from menu (if it existed).");
      }

      private void changeItemPrice() {
          System.out.print("Enter item name: ");
          String name = scanner.nextLine();

          System.out.print("Enter new price: ");
          double newPrice;
          try {
              newPrice = Double.parseDouble(scanner.nextLine());
              if (newPrice <= 0) {
                  System.out.println("Price must be greater than 0.");
                  return;
              }
          } catch (NumberFormatException e) {
              System.out.println("Invalid price format.");
              return;
          }

          restaurant.getMenu().changeItemPrice(name, newPrice);
          System.out.println("Price updated successfully!");
      }

      private void viewMenu() {
          restaurant.showMenu();
      }

      // ========== STAFF MANAGEMENT METHODS (PLACEHOLDERS) ==========

      private void hireEmployee() {
          System.out.println("Hire employee - implementation coming next");
      }

      private void viewAllStaff() {
          System.out.println("View staff - implementation coming next");
      }
  }