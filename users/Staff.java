package users;

public class Staff {
    private String name;
    private String role;
    private double salary;
    private Object order; 
    private boolean isActive;


    public Staff(String name, String role, double salary) {
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.order = null;
        this.isActive = false;
    }

    public void prepareOrder(Object order) {
        if (isActive) {
            System.out.println("LOG: " + name + " is preparing the items.");
        }
    }

    public void assignOrder(Object order) {
        this.order = order;
        System.out.println("SUCCESS: Order assigned to " + name);
    }

    public void markOrderStatus() {
        System.out.println("UPDATE: Staff " + name + " marked order as READY.");
    }

    public void clockIn() {
        this.isActive = true;
        System.out.println("SYSTEM: " + name + " is now on shift.");
    }

    public void clockOut() {
        this.isActive = false;
        System.out.println("SYSTEM: " + name + " has finished their shift.");
    }

         public String getName(){
         return name; 
        }
   
        public void setName(String name){
         this.name = name; 
        }
  
        public String getRole(){
             return role; 
            }
  
        public void setRole(String role){
             this.role = role; 
        }
  
        public double getSalary(){
             return salary; 
            }
  
        public void setSalary(double salary){
             if(salary > 0){
                this.salary = salary; 
             } 
            }
}
