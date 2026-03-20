package users;

import restaurant.Restaurant;

public abstract class Staff {
    protected String name;
    protected String role;
    protected double salary;
    // private Object order; 
    // private boolean isActive;
    protected Restaurant restaurant;
    protected String staffID;


    public Staff(String name, String role, double salary,Restaurant restaurant, String staffID) {
        this.name = name;
        this.role = role;
        this.salary = salary;
        // this.order = null;
        // this.isActive = false;
        this.restaurant = restaurant;
        this.staffID = staffID;
    }

    public abstract void performDuties();

    public String getStaffID(){
        return this.staffID;
    }
    // public void prepareOrder(Object order) {
    //     if (isActive) {
    //         System.out.println("LOG: " + name + " is preparing the items.");
    //     }
    // }

    // public void assignOrder(Object order) {
    //     this.order = order;
    //     System.out.println("SUCCESS: Order assigned to " + name);
    // }

    // public void markOrderStatus() {
    //     System.out.println("UPDATE: Staff " + name + " marked order as READY.");
    // }

    // public void clockIn() {
    //     this.isActive = true;
    //     System.out.println("SYSTEM: " + name + " is now on shift.");
    // }

    // public void clockOut() {
    //     this.isActive = false;
    //     System.out.println("SYSTEM: " + name + " has finished their shift.");
    // }

    //      public String getName(){
    //      return name; 
    //     }
   
    //     public void setName(String name){
    //      this.name = name; 
    //     }
  
    //     public String getRole(){
    //          return role; 
    //         }
  
    //     public void setRole(String role){
    //          this.role = role; 
    //     }
  
    //     public double getSalary(){
    //          return salary; 
    //         }
  
    //     public void setSalary(double salary){
    //          if(salary > 0){
    //             this.salary = salary; 
    //          } 
    //         }
}
