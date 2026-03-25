package order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import menuAndFoodItems.FoodItem;
import utilities.exceptions.MaxInstancesException;

@DisplayName("Order Tests")
class TestOrder {
    private Order order;
    
    @BeforeEach
    void setUp() throws MaxInstancesException {
        Order.instanceCounter = 0;
        order = new Order();
    }
    
    @Test
    @DisplayName("Constructor assigns unique order number")
    void testConstructor_AssignsOrderNumber() throws MaxInstancesException {
        Order order1 = new Order();
        Order order2 = new Order();
        assertNotEquals(order1.getOrderNumber(), order2.getOrderNumber());
        assertTrue(order2.getOrderNumber() > order1.getOrderNumber());
    }
    
    @Test
    @DisplayName("Constructor throws MaxInstancesException when limit exceeded")
    void testConstructor_ExceedsMaxInstances() {
        assertThrows(MaxInstancesException.class, this::exceedMaxInstances);
    }
    
    private void exceedMaxInstances() throws MaxInstancesException {
        for (int i = 0; i < 101; i++) {
            new Order();
        }
    }
    
    @Test
    @DisplayName("Add item to order")
    void testAddItemToOrder() throws MaxInstancesException {
        FoodItem item = new FoodItem("Burger", 500, 10.99);
        order.addItemToOrder(item);
        assertEquals(1, order.getOrderLength());
        assertTrue(order.getItems().contains(item));
    }
    
    @Test
    @DisplayName("Remove item by name when item exists")
    void testRemoveItemByName_Exists() throws MaxInstancesException {
        FoodItem item = new FoodItem("Burger", 500, 10.99);
        order.addItemToOrder(item);
        assertEquals(1, order.getOrderLength());
        order.removeItemByName("burger");
        assertEquals(0, order.getOrderLength());
    }
    
    @Test
    @DisplayName("Remove item by name when item does not exist")
    void testRemoveItemByName_NotExists() throws MaxInstancesException {
        FoodItem item = new FoodItem("Burger", 500, 10.99);
        order.addItemToOrder(item);
        order.removeItemByName("pizza");
        assertEquals(1, order.getOrderLength());
    }
    
    @Test
    @DisplayName("Calculate total for single item")
    void testCalculateTotal_SingleItem() throws MaxInstancesException {
        FoodItem item = new FoodItem("Burger", 500, 10.99);
        order.addItemToOrder(item);
        assertEquals(10.99, order.calculateTotal());
    }
    
    @Test
    @DisplayName("Calculate total for multiple items")
    void testCalculateTotal_MultipleItems() throws MaxInstancesException {
        FoodItem burger = new FoodItem("Burger", 500, 10.99);
        FoodItem fries = new FoodItem("Fries", 300, 3.99);
        order.addItemToOrder(burger);
        order.addItemToOrder(fries);
        assertEquals(14.98, order.calculateTotal(), 0.01);
    }
    
    @Test
    @DisplayName("Calculate total for empty order")
    void testCalculateTotal_EmptyOrder() {
        assertEquals(0.0, order.calculateTotal());
    }
    
    @Test
    @DisplayName("Set and get all status values")
    void testSetStatus_AllStatuses() {
        order.setStatusInProgress();
        assertEquals("IN PROGRESS", order.getStatus());
        
        order.setStatusPreparing();
        assertEquals("PREPARING", order.getStatus());
        
        order.setStatusComplete();
        assertEquals("COMPLETE", order.getStatus());
        
        order.setStatusPaid();
        assertEquals("PAID", order.getStatus());
        
        order.setStatusPaymentDenied();
        assertEquals("PAYMENT DENIED", order.getStatus());
        
        order.setStatusCancelled();
        assertEquals("CANCELLED", order.getStatus());
    }
    
    @Test
    @DisplayName("Get order length returns correct item count")
    void testGetOrderLength() throws MaxInstancesException {
        assertEquals(0, order.getOrderLength());
        FoodItem item = new FoodItem("Burger", 500, 10.99);
        order.addItemToOrder(item);
        assertEquals(1, order.getOrderLength());
    }
}
