package menuAndFoodItems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import utilities.exceptions.MaxInstancesException;

@DisplayName("FoodItem Tests")
class TestFoodItem {
    private FoodItem foodItem;
    
    @BeforeEach
    void setUp() throws MaxInstancesException {
        FoodItem.FoodItemCount = 0;
        foodItem = new FoodItem("Burger", 500, 10.99);
    }
    
    @Test
    @DisplayName("Constructor creates FoodItem with correct values")
    void testConstructor_ValidInput() {
        assertNotNull(foodItem);
        assertEquals("Burger", foodItem.getName());
        assertEquals(500, foodItem.getCalories());
        assertEquals(10.99, foodItem.getPrice());
    }
    
    @Test
    @DisplayName("Constructor throws MaxInstancesException when limit exceeded")
    void testConstructor_ExceedsMaxInstances() {
        assertThrows(MaxInstancesException.class, () -> exceedMaxInstances());
    }
    
    private void exceedMaxInstances() throws MaxInstancesException {
        for (int i = 0; i < 101; i++) {
            new FoodItem("Item " + i, 100, 5.00);
        }
    }
    
    
    @Test
    @DisplayName("Change price updates correctly")
    void testChangePrice() {
        foodItem.setPrice(12.99);
        assertEquals(12.99, foodItem.getPrice());
    }
    
    @Test
    @DisplayName("Setters update values correctly")
    void testSetters() {
        foodItem.setName("Cheeseburger");
        foodItem.setCalories(600);
        foodItem.setPrice(11.99);
        
        assertAll("After setter updates",
            () -> assertEquals("Cheeseburger", foodItem.getName()),
            () -> assertEquals(600, foodItem.getCalories()),
            () -> assertEquals(11.99, foodItem.getPrice())
        );
    }
}
