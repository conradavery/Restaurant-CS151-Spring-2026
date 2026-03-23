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
