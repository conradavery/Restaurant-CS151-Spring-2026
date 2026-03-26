package users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import restaurant.Restaurant;
import utilities.exceptions.MaxInstancesException;

@DisplayName("Staff Tests")
class TestStaff {
    private Manager manager;
    private Restaurant restaurant;

    
    @BeforeEach
    void setUp() throws MaxInstancesException {
        Restaurant.restaurantCount = 0;
        Manager.managerCount = 0;
        restaurant = new Restaurant("Test Restaurant", "123 Main St", "555-1234");
        manager = new Manager("John", "Manager", 50000, restaurant, "M001");
    }
    
    @Test
    @DisplayName("Set salary with valid positive value")
    void testSetSalary_Valid() {
        manager.setSalary(60000);
        assertEquals(60000, manager.getSalary());
    }
    
    @Test
    @DisplayName("Set salary with negative value should not update")
    void testSetSalary_Invalid() {
        manager.setSalary(-1000);
        assertEquals(50000, manager.getSalary());
    }
    
    @Test
    @DisplayName("Getters return correct values")
    void testGetters_ReturnCorrectValues() {
        assertAll("Staff getters",
            () -> assertEquals("John", manager.getName()),
            () -> assertEquals("Manager", manager.getRole()),
            () -> assertEquals(50000, manager.getSalary()),
            () -> assertEquals("M001", manager.getStaffID()),
            () -> assertEquals(restaurant, manager.getRestaurant())
        );
    }
    
    @Test
    @DisplayName("Setters update values correctly")
    void testSetters() {
        manager.setName("Jane");
        manager.setRole("Senior Manager");
        manager.setStaffID("SM001");
        
        assertAll("After setter updates",
            () -> assertEquals("Jane", manager.getName()),
            () -> assertEquals("Senior Manager", manager.getRole()),
            () -> assertEquals("SM001", manager.getStaffID())
        );
    }
}