package users;

import restaurant.Restaurant;

public abstract class Staff {
    protected String name;
    protected String role;
    protected double salary;
    protected Restaurant restaurant;
    protected String staffID;

    public Staff(String name, String role, double salary, Restaurant restaurant, String staffID) {
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.restaurant = restaurant;
        this.staffID = staffID;
    }

    public abstract void performDuties();

    public String getStaffID() {
        return this.staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary > 0) {
            this.salary = salary;
        }
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}