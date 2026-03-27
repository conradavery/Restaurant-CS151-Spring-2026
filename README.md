# Restaurant Management System CS151 Spring 2026

## Overview

A terminal based Restaurant Management System built for CS151 Spring 2026. The system supports two portals: a customer portal for browsing the menu, placing orders, and leaving ratings, and an employee portal with role-based access for Managers and Kitchen Staff.

Key features:
- **Menu Management**: Add, remove, and reprice food items
- **Order System**: Create, track, and update customer orders in real time
- **Payment Processing**: Support for both card and cash payments
- **User Roles**: Separate workflows for Customers, Kitchen Staff, and Managers
- **Ratings**: Customers can leave, view, and update star ratings with optional messages

---

## Design

### Package Structure

| Package | Classes | Description |
|---------|---------|-------------|
| `main` | `Main` | Entry point of the application |
| `restaurant` | `Restaurant` | Central class managing all restaurant state |
| `users` | `Customer`, `Staff`, `Manager`, `KitchenStaff` | User hierarchy built with inheritance |
| `menuAndFoodItems` | `Menu`, `FoodItem` | Menu and item management |
| `order` | `Order` | Order creation and status tracking |
| `payment` | `CardPayment`, `CashPayment`, `Payable` | Payment processing via strategy pattern |
| `ratings` | `Rating` | Customer rating logic |
| `test` | `Test packages` | Testing for the entire project |
| `utilities` | `Input`, `UI`, `SystemLimits` | Shared helpers and constants |
| `utilities.exceptions` | Various | Custom exception classes |

### Design Patterns & Principles

- **Inheritance**: `Staff` is the abstract base class for `Manager` and `KitchenStaff`, defining shared fields and enforcing `performDuties()` via abstraction
- **Polymorphism**: `Payable` interface allows `CardPayment` and `CashPayment` to be used interchangeably and Staff's abstract performDuties() method produces different behavior at runtime depending on the subclass.
- **Composition**: `Restaurant` owns a `Menu` and manages lists of `Order`, `Staff`, `Customer`, and `Rating` objects
- **Encapsulation**: All fields are private with controlled access through getters/setters
- **Single Responsibility**: Each class has a focused purpose — `KitchenStaff` manages order status, `Manager` handles restaurant operations, etc.

---

## Installation Instructions

### Prerequisites
- Java JDK 17 or higher
- Git

### Command Line

```bash
# 1. Open Terminal and make a folder for the project
mkdir RestaurantProjectCS151
cd RestaurantProjectCS151

#2. Clone the repo
git clone https://github.com/conradavery/Restaurant-CS151-Spring-2026
cd Restaurant-CS151-Spring-2026

# 3. Compile
javac main/Main.java

# 4. Run
java main.Main
```

---

## Usage

Video Link: https://youtu.be/2mwXi1lFdBE
### Main Menu

```
OUT AND IN
Address: 1357 Newhall Drive  |  Phone: (123)-456-789

----------- MAIN MENU -----------
1) Customer
2) Employee Login
3) View Ratings
4) Quit
Selection:
```

### Customer Portal

1. Select `1` from the main menu
2. Enter your phone number — new users will be prompted to create an account
3. From the Customer Menu:
   - **Create new order** — browse the menu, add/remove items, pay with card or cash
   - **View previous orders** — see your full order history
   - **Leave a rating** — rate the restaurant 1–5 stars with an optional message
   - **Go back** — return to the main menu

### Employee Portal

Select `2` from the main menu and enter your Staff ID.

**Manager** (default Staff ID: `333`)

| Section | Actions |
|---------|---------|
| Manage Menu | Add items, remove items, change prices, view menu |
| Manage Staff | View all staff, hire, fire, increase salaries |
| Manage Restaurant | Update phone number, address, view revenue |

**Kitchen Staff** (default Staff ID: `111`)

| Action | Description |
|--------|-------------|
| View all orders | See every order placed |
| View order details | Inspect a specific order by ID |
| Mark as preparing | Update order status to `PREPARING` |
| Mark as complete | Update order status to `COMPLETE` |

---

## Sample Test Data

The application launches pre-loaded with the following data:

| Type | Details |
|------|---------|
| **Menu** | Hamburger ($3.50), Cheese Burger ($5.50), Fries ($3.00), Soda ($1.00), Milkshake ($2.00) |
| **Manager** | Bob — Staff ID: `333` |
| **Kitchen Staff** | Steve — Staff ID: `111` |
| **Sample Rating** | Conrad — 5 stars |

### Type 'EXIT' at any input option to exit the system
## Contributions

  **Conrad**
  - Initial project setup and repository structure
  - `Restaurant`, `Menu`, `FoodItem` classes
  - `Main` class and application startup logic
  - `UI` helper class for formatted console output
  - `Input` class with EXIT handling
  - Customer order flow and payment integration
  - `Rating` class and rating functionality
  - `MaxInstancesException` and system limits integration
  - Code review and merge management

  **Don**
  - `Customer` class with order creation and history
  - `Staff` abstract base class
  - `Manager` class with menu and staff management
  - `KitchenStaff` class with order status updates
  - README documentation (Overview, Design, Installation, Usage, Contributions)

  **Trung**
  - `Order` class
  - `SystemLimits` class for maximum instance handling
  - `Payable` interface design
  - `CardPayment` and `CashPayment` classes
  - `OrderNotFoundException` and `StaffNotFoundException` implementation
  - UML diagram creation and maintenance
  - Updated classes to match UML specifications

  **Gerard**
  - `Payment` base class implementation
  - `CardPayment` getters/setters and receipt generation
  - Initial payment processing logic
  - Unit tests for payment and restaurant classes
