package users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import restaurant.Restaurant;
import utilities.exceptions.MaxInstancesException;

@DisplayName("KitchenStaff Tests")
class TestKitchenStaff {
    private KitchenStaff kitchenStaff;
    private Restaurant restaurant;
    
    @BeforeEach
    void setUp() throws MaxInstancesException {
        Restaurant.restaurantCount = 0;
        KitchenStaff.kitchenStaffCount = 0;
        restaurant = new Restaurant("Test Restaurant", "123 Main St", "555-1234");
        kitchenStaff = new KitchenStaff("Mike", "KitchenStaff", 35000, restaurant, "K001");
    }
    
    @Test
    @DisplayName("Constructor creates KitchenStaff with correct values")
    void testConstructor_ValidInput() {
        assertNotNull(kitchenStaff);
        assertEquals("Mike", kitchenStaff.getName());
        assertEquals("KitchenStaff", kitchenStaff.getRole());
        assertEquals(35000, kitchenStaff.getSalary());
        assertEquals("K001", kitchenStaff.getStaffID());
        assertEquals(restaurant, kitchenStaff.getRestaurant());
    }
    
    @Test
    @DisplayName("Constructor throws MaxInstancesException when limit exceeded")
    void testConstructor_ExceedsMaxInstances() {
        assertThrows(MaxInstancesException.class, this::exceedMaxInstances);
    }
    
    private void exceedMaxInstances() throws MaxInstancesException {
        for (int i = 0; i < 101; i++) {
            new KitchenStaff("Staff " + i, "KitchenStaff", 35000, restaurant, "K" + i);
        }
    }
}