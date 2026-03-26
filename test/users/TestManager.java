package users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import restaurant.Restaurant;
import utilities.exceptions.MaxInstancesException;

@DisplayName("Manager Tests")
class TestManager {
    private Manager manager;
    private Restaurant restaurant;
    
    @BeforeEach
    void setUp() throws MaxInstancesException {
        Manager.managerCount = 0;
        Restaurant.restaurantCount = 0;
        restaurant = new Restaurant("Test Restaurant", "123 Main St", "555-1234");
        manager = new Manager("John", "Manager", 50000, restaurant, "M001");
    }
    
    @Test
    @DisplayName("Constructor creates Manager with correct values")
    void testConstructor_ValidInput() {
        assertNotNull(manager);
        assertEquals("John", manager.getName());
        assertEquals("Manager", manager.getRole());
        assertEquals(50000, manager.getSalary());
        assertEquals("M001", manager.getStaffID());
        assertEquals(restaurant, manager.getRestaurant());
    }
    
    @Test
    @DisplayName("Constructor throws MaxInstancesException when limit exceeded")
    void testConstructor_ExceedsMaxInstances() {
        assertThrows(MaxInstancesException.class, this::exceedMaxInstances);
    }
    
    private void exceedMaxInstances() throws MaxInstancesException {
        for (int i = 0; i < 101; i++) {
            new Manager("Manager " + i, "Manager", 50000, restaurant, "M" + i);
        }
    }
}