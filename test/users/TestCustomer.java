package users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import restaurant.Restaurant;
import order.Order;
import ratings.Rating;
import utilities.exceptions.MaxInstancesException;

@DisplayName("Customer Tests")
class TestCustomer {
    private Customer customer;
    private Restaurant restaurant;
    
    @BeforeEach
    void setUp() throws MaxInstancesException {
        Restaurant.restaurantCount = 0;
        Customer.customerCount = 0;
        restaurant = new Restaurant("Test Restaurant", "123 Main St", "555-1234");
        customer = new Customer("Alice", "555-5678", restaurant);
    }
    
    @Test
    @DisplayName("Constructor creates Customer with correct values")
    void testConstructor_ValidInput() {
        assertNotNull(customer);
        assertEquals("Alice", customer.getName());
        assertEquals("555-5678", customer.getPhoneNumber());
        assertEquals(restaurant, customer.getRestaurant());
    }
    
    @Test
    @DisplayName("Constructor throws MaxInstancesException when limit exceeded")
    void testConstructor_ExceedsMaxInstances() {
        assertThrows(MaxInstancesException.class, () -> exceedMaxInstances());
    }
    
    private void exceedMaxInstances() throws MaxInstancesException {
        for (int i = 0; i < 101; i++) {
            new Customer("User " + i, "555-000" + i, restaurant);
        }
    }
    
    @Test
    @DisplayName("Set and get rating")
    void testSetAndGetRating() throws MaxInstancesException {
        Rating rating = new Rating("Alice", 5, "Great!");
        customer.setRating(rating);
        assertEquals(rating, customer.getRating());
    }
    
    @Test
    @DisplayName("Set and get past orders")
    void testSetAndGetPastOrders() throws MaxInstancesException {
        Order order = new Order();
        java.util.ArrayList<Order> orders = new java.util.ArrayList<>();
        orders.add(order);
        customer.setPastOrders(orders);
        assertEquals(1, customer.getPastOrders().size());
    }
    
    @Test
    @DisplayName("Get phone number returns correct value")
    void testGetPhoneNumber() {
        assertEquals("555-5678", customer.getPhoneNumber());
    }
    
    @Test
    @DisplayName("Get restaurant returns correct restaurant")
    void testGetRestaurant() {
        assertEquals(restaurant, customer.getRestaurant());
    }
    
    @Test
    @DisplayName("Set name updates correctly")
    void testSetAndGetName() {
        customer.setName("Bob");
        assertEquals("Bob", customer.getName());
    }
}