# Restaurant-CS151-Spring-2026

## Overview
  This project is a Restaurant Management System built for CS151 Spring 2026. The system allows customers to browse a menu, place orders, and make payments using a digital wallet. It also provides staff roles (Manager and KitchenStaff) to manage restaurant operations.

  Key features include:
  - **Menu Management**: Browse food items organized by category
  - **Order System**: Create and track customer orders with order items
  - **Payment Processing**: Secure payments using wallet-based transactions
  - **User Roles**: Different access levels for Customers, Staff, Managers, and KitchenStaff

  The project demonstrates object-oriented design principles including inheritance, encapsulation, and composition.

   ## Design

  ### Class Structure
  | Package | Classes | Description |
  |---------|---------|-------------|
  | `main` | Main | Entry point of the application |
  | `restaurant` | Restaurant | Central class managing restaurant state |
  | `users` | Customer, Staff, Manager, KitchenStaff | User hierarchy with inheritance |
  | `menuAndFoodItems` | Menu, FoodItem | Menu management and food items |
  | `order` | Order, OrderItems | Order processing logic |
  | `payment` | Payment, PaymentManager, Wallet | Payment handling |

  ### Design Patterns & Principles
  - **Inheritance**: `Staff` is a base class for `Manager` and `KitchenStaff`; shared
  attributes and methods are defined in the parent class
  - **Composition**: `Order` contains `OrderItems`, `Restaurant` contains `Menu` and
  manages orders
  - **Encapsulation**: All classes use private fields with public getters/setters to
  control access
  - **Single Responsibility**: Each class has a focused purpose (e.g., `PaymentManager` 
  only handles transactions)

   ## Installation Instructions

  ### Prerequisites
  - Java JDK 17 or higher
  - Git (for cloning the repository)

  ### Steps

  1. **Clone the repository**
     ```bash
     git clone <project url>
     cd Restaurant-CS151-Spring-2026

  2. Compile the project
     javac main/Main.java
    
  3. Run the application
     java main.Main

  Using an IDE (IntelliJ, Eclipse, VS Code)

  1. Open the project folder in your IDE
  2. Set the source root to the project directory
  3. Run main.Main class

 ## Usage

  ### Main Menu
  When you run the application, you'll see:

  OUT AND IN Address: 1357 Newhall Drive Phone number: (123) - 456 - 789

  --- MAIN MENU ---

  1. Customer
  2. Employee Login
  3. View Ratings
  4. Quit Selection:


  ### For Customers
  1. Select `1` for Customer
  2. Enter your phone number (new customers will create an account)
  3. From the Customer Menu, you can:
     - **1) Create new order** - Browse menu, add/remove items, pay with card or cash
     - **2) View previous orders** - See your order history
     - **3) Leave rating** - Rate the restaurant (1-5 stars) with an optional message
     - **4) Go back to main menu**

  ### For Employees
  Select `2` for Employee Login, then enter your staff ID.

  **Manager** (try staff ID: `333`)
  - **Manage Menu**: Add items, remove items, change prices, view menu
  - **Manage Staff**: View staff, hire new staff, fire staff, increase salaries
  - **Manage Restaurant**: Change phone number, address, view revenue

  **KitchenStaff** (try staff ID: `111`)
  - View pending orders
  - Mark orders as "preparing"
  - Mark orders as "complete"

  ### Sample Test Data
  The application comes pre-loaded with:
  - **Menu Items**: Hamburger ($3.50), Cheese Burger ($5.50), Fries ($3.00), Soda ($1
  .00), Milkshake ($2.00)
  - **Manager**: Bob (Staff ID: `333`)
  - **KitchenStaff**: Steve (Staff ID: `111`)
  - **Sample Rating**: From "Conrad" with 5 stars
