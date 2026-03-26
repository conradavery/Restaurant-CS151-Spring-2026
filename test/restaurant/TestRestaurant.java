package restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import menuAndFoodItems.Menu;
import menuAndFoodItems.FoodItem;
import order.Order;
import users.Customer;
import users.Staff;
import users.Manager;
import ratings.Rating;
import utilities.exceptions.MaxInstancesException;
import utilities.exceptions.OrderNotFoundException;
import utilities.exceptions.StaffNotFoundException;


@DisplayName("Restaurant Tests")
class TestRestaurant {
    private Restaurant restaurant;
    
    @BeforeEach
    void setUp() throws MaxInstancesException {
        Restaurant.restaurantCount = 0;
        restaurant = new Restaurant("Test Restaurant", "123 Main St", "555-1234");
    }

    @Test
    @DisplayName("New Restaurant initializes with correct values and empty lists")
    void testConstructor_ValidInput() {
        assertNotNull(restaurant);
        assertEquals("Test Restaurant", restaurant.getName());
        assertEquals("123 Main St", restaurant.getAddress());
        assertEquals("555-1234", restaurant.getPhoneNumber());
        assertEquals(0.0, restaurant.getRevenue());
        assertTrue(restaurant.getStaffList().isEmpty());
        assertTrue(restaurant.getCustomerList().isEmpty());
        assertTrue(restaurant.getOrders().isEmpty());
        assertTrue(restaurant.getRatings().isEmpty());
    }
    
    
    private void exceedMaxInstances() throws MaxInstancesException {
        for (int i = 0; i < 101; i++) {
            new Restaurant("Restaurant " + i, "Address", "555-0000");
        }
    }
    @Test
    @DisplayName("Constructor throws MaxInstancesException when limit exceeded")
    void testConstructor_ExceedsMaxInstances() {
        assertThrows(MaxInstancesException.class, () -> exceedMaxInstances());
    }
    
    
    @Test
    @DisplayName("processOrder increases revenue and adds order to list")
    void testProcessOrder() throws MaxInstancesException {
        Menu menu = new Menu();
        restaurant.setMenu(menu);
        FoodItem item = new FoodItem("Burger", 500, 10.99);
        restaurant.addItemToMenu(item);
        
        Order order = new Order();
        order.addItemToOrder(item);
        double orderTotal = order.calculateTotal();
        
        int initialOrderCount = restaurant.getOrders().size();
        restaurant.processOrder(order);
        
        assertEquals(orderTotal, restaurant.getRevenue());
        assertEquals(initialOrderCount + 1, restaurant.getOrders().size());
        assertTrue(restaurant.getOrders().contains(order));
    }
    
    @Test
    @DisplayName("addToRevenue accumulates correctly across multiple calls")
    void testAddToRevenue_MultipleCalls() {
        restaurant.addToRevenue(100.00);
        restaurant.addToRevenue(50.50);
        restaurant.addToRevenue(25.25);
        assertEquals(175.75, restaurant.getRevenue(), 0.01);
    }
    
    @Test
    @DisplayName("addToRevenue increases total correctly")
    void testAddToRevenue_SingleCall() {
        restaurant.addToRevenue(100.50);
        assertEquals(100.50, restaurant.getRevenue());
    }
    
    @Test
    @DisplayName("findOrder returns order when found")
    void testFindOrder_Exists() throws MaxInstancesException, OrderNotFoundException {
        Order order = new Order();
        restaurant.addOrder(order);
        Order found = restaurant.findOrder(order.getOrderNumber());
        assertEquals(order, found);
    }
    
    @Test
    @DisplayName("findOrder throws OrderNotFoundException when not found")
    void testFindOrder_NotExists() {
        OrderNotFoundException exception = assertThrows(
            OrderNotFoundException.class,
            () -> restaurant.findOrder(999)
        );
        assertTrue(exception.getMessage().contains("999"));
    }
    
    @Test
    @DisplayName("findStaff returns staff when found")
    void testFindStaff_Exists() throws MaxInstancesException, StaffNotFoundException {
        Manager manager = new Manager("John", "Manager", 50000, restaurant, "M001");
        restaurant.hireEmployee(manager);
        
        Staff found = restaurant.findStaff("M001");
        assertEquals("John", found.getName());
        assertEquals("Manager", found.getRole());
    }
    
    @Test
    @DisplayName("findStaff throws StaffNotFoundException when not found")
    void testFindStaff_NotExists() {
        StaffNotFoundException exception = assertThrows(
            StaffNotFoundException.class,
            () -> restaurant.findStaff("NONEXISTENT")
        );
        assertTrue(exception.getMessage().contains("NONEXISTENT"));
    }
    
    @Test
    @DisplayName("findCustomer returns customer when found by phone")
    void testFindCustomer_Exists() throws MaxInstancesException {
        Customer customer = new Customer("Alice", "555-5678", restaurant);
        restaurant.addCustomer(customer);
        
        Customer found = restaurant.findCustomer("555-5678");
        assertEquals("Alice", found.getName());
    }
    
    @Test
    @DisplayName("findCustomer returns null when not found")
    void testFindCustomer_NotExists() {
        Customer found = restaurant.findCustomer("999-9999");
        assertNull(found);
    }
    
    @Test
    @DisplayName("checkStaff returns true when staff exists")
    void testCheckStaff_Exists() throws MaxInstancesException {
        Manager manager = new Manager("John", "Manager", 50000, restaurant, "M001");
        restaurant.hireEmployee(manager);
        assertTrue(restaurant.checkStaff("M001"));
    }
    
    @Test
    @DisplayName("checkStaff returns false when staff does not exist")
    void testCheckStaff_NotExists() {
        assertFalse(restaurant.checkStaff("NONEXISTENT"));
    }
    
    @Test
    @DisplayName("hireEmployee adds staff to staff list")
    void testHireEmployee() throws MaxInstancesException {
        int initialSize = restaurant.getStaffList().size();
        Manager manager = new Manager("John", "Manager", 50000, restaurant, "M001");
        restaurant.hireEmployee(manager);
        assertEquals(initialSize + 1, restaurant.getStaffList().size());
        assertTrue(restaurant.getStaffList().contains(manager));
    }
    
    @Test
    @DisplayName("fireStaff removes staff from staff list")
    void testFireStaff() throws MaxInstancesException {
        Manager manager = new Manager("John", "Manager", 50000, restaurant, "M001");
        restaurant.hireEmployee(manager);
        int sizeAfterHire = restaurant.getStaffList().size();
        
        restaurant.fireStaff(manager);
        assertEquals(sizeAfterHire - 1, restaurant.getStaffList().size());
        assertFalse(restaurant.getStaffList().contains(manager));
    }
    
    @Test
    @DisplayName("addCustomer adds customer to customer list")
    void testAddCustomer() throws MaxInstancesException {
        int initialSize = restaurant.getCustomerList().size();
        Customer customer = new Customer("Alice", "555-5678", restaurant);
        restaurant.addCustomer(customer);
        assertEquals(initialSize + 1, restaurant.getCustomerList().size());
        assertTrue(restaurant.getCustomerList().contains(customer));
    }
    
    @Test
    @DisplayName("addOrder adds order to orders list")
    void testAddOrder() throws MaxInstancesException {
        int initialSize = restaurant.getOrders().size();
        Order order = new Order();
        restaurant.addOrder(order);
        assertEquals(initialSize + 1, restaurant.getOrders().size());
        assertTrue(restaurant.getOrders().contains(order));
    }
    
    @Test
    @DisplayName("addRating adds rating to ratings list")
    void testAddRating() throws MaxInstancesException {
        int initialSize = restaurant.getRatings().size();
        Rating rating = new Rating("Alice", 5, "Great!");
        restaurant.addRating(rating);
        assertEquals(initialSize + 1, restaurant.getRatings().size());
        assertTrue(restaurant.getRatings().contains(rating));
    }
    
    @Test
    @DisplayName("removeRating removes rating from ratings list")
    void testRemoveRating() throws MaxInstancesException {
        Rating rating = new Rating("Alice", 5, "Great!");
        restaurant.addRating(rating);
        int sizeAfterAdd = restaurant.getRatings().size();
        
        restaurant.removeRating(rating);
        assertEquals(sizeAfterAdd - 1, restaurant.getRatings().size());
        assertFalse(restaurant.getRatings().contains(rating));
    }
    
    @Test
    @DisplayName("addItemToMenu adds item to menu")
    void testAddItemToMenu() throws MaxInstancesException {
        Menu menu = new Menu();
        restaurant.setMenu(menu);
        int initialSize = menu.getItems().size();
        
        FoodItem item = new FoodItem("Burger", 500, 10.99);
        restaurant.addItemToMenu(item);
        assertEquals(initialSize + 1, menu.getItems().size());
    }
    
    @Test
    @DisplayName("removeItem removes item from menu")
    void testRemoveItem() throws MaxInstancesException {
        Menu menu = new Menu();
        restaurant.setMenu(menu);
        FoodItem item = new FoodItem("Burger", 500, 10.99);
        restaurant.addItemToMenu(item);
        int sizeAfterAdd = menu.getItems().size();
        
        restaurant.removeItem("Burger");
        assertEquals(sizeAfterAdd - 1, menu.getItems().size());
    }
    
    @Test
    @DisplayName("removeItem handles item not found gracefully")
    void testRemoveItem_NotFound() throws MaxInstancesException {
        Menu menu = new Menu();
        restaurant.setMenu(menu);
        FoodItem item = new FoodItem("Burger", 500, 10.99);
        restaurant.addItemToMenu(item);
        int sizeAfterAdd = menu.getItems().size();

        restaurant.removeItem("NonExistent");
        assertEquals(sizeAfterAdd, menu.getItems().size());
    }
    
    @Test
    @DisplayName("changePrice updates item price")
    void testChangePrice() throws MaxInstancesException {
        Menu menu = new Menu();
        restaurant.setMenu(menu);
        FoodItem item = new FoodItem("Burger", 500, 10.99);
        restaurant.addItemToMenu(item);
        
        restaurant.changePrice("Burger", 15.99);
        assertEquals(15.99, item.getPrice(), 0.01);
    }
    
    @Test
    @DisplayName("getStaffList returns defensive copy")
    void testGetStaffList_DefensiveCopy() throws MaxInstancesException {
        Manager manager = new Manager("John", "Manager", 50000, restaurant, "M001");
        restaurant.hireEmployee(manager);
        
        var staffList = restaurant.getStaffList();
        staffList.clear();
        assertEquals(1, restaurant.getStaffList().size());
    }
    
    @Test
    @DisplayName("getCustomerList returns defensive copy")
    void testGetCustomerList_DefensiveCopy() throws MaxInstancesException {
        Customer customer = new Customer("Alice", "555-5678", restaurant);
        restaurant.addCustomer(customer);
        
        var customerList = restaurant.getCustomerList();
        customerList.clear();
        assertEquals(1, restaurant.getCustomerList().size());
    }
    
    @Test
    @DisplayName("getOrders returns defensive copy")
    void testGetOrders_DefensiveCopy() throws MaxInstancesException {
        Order order = new Order();
        restaurant.addOrder(order);
        
        var orders = restaurant.getOrders();
        orders.clear();
        assertEquals(1, restaurant.getOrders().size());
    }
    
    @Test
    @DisplayName("getRatings returns defensive copy")
    void testGetRatings_DefensiveCopy() throws MaxInstancesException {
        Rating rating = new Rating("Alice", 5, "Great!");
        restaurant.addRating(rating);
        
        var ratings = restaurant.getRatings();
        ratings.clear();
        assertEquals(1, restaurant.getRatings().size());
    }
    
    @Test
    @DisplayName("Setters update values correctly")
    void testSetters() {
        restaurant.setName("New Name");
        restaurant.setAddress("456 New St");
        restaurant.setPhoneNumber("555-9999");
        
        assertEquals("New Name", restaurant.getName());
        assertEquals("456 New St", restaurant.getAddress());
        assertEquals("555-9999", restaurant.getPhoneNumber());
    }
}
