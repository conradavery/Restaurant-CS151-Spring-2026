package menuAndFoodItems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import utilities.exceptions.MaxInstancesException;
import utilities.exceptions.MenuItemNotFoundException;
@DisplayName("Menu Tests")
class TestMenu {
    private Menu menu;
    
    @BeforeEach
    void setUp() throws MaxInstancesException {
        Menu.menuCount = 0;
        menu = new Menu();
    }
    
    @Test
    @DisplayName("Constructor creates empty menu")
    void testConstructor_ValidInput() {
        assertNotNull(menu);
        assertTrue(menu.getItems().isEmpty());
    }
    
    @Test
    @DisplayName("Constructor throws MaxInstancesException when limit exceeded")
    void testConstructor_ExceedsMaxInstances() {
        assertThrows(MaxInstancesException.class, () -> exceedMaxInstances());
    }
    
    private void exceedMaxInstances() throws MaxInstancesException {
        for (int i = 0; i < 101; i++) {
            new Menu();
        }
    }
    
    @Test
    @DisplayName("Add item to menu")
    void testAddItem() throws MaxInstancesException {
        FoodItem item = new FoodItem("Pizza", 800, 15.99);
        menu.addItem(item);
        assertEquals(1, menu.getItems().size());
    }
    
    @Test
    @DisplayName("Get item by valid index returns correct item")
    void testGetItem_ValidIndex() throws MaxInstancesException, MenuItemNotFoundException {
        FoodItem item = new FoodItem("Pizza", 800, 15.99);
        menu.addItem(item);
        FoodItem found = menu.getItem(1);
        assertNotNull(found);
        assertEquals("Pizza", found.getName());
    }
    
    @Test
    @DisplayName("Get item by invalid index throws MenuItemNotFoundException")
    void testGetItem_InvalidIndex() {
        assertThrows(MenuItemNotFoundException.class, () -> {
            menu.getItem(1);
        });
    }
    
    @Test
    @DisplayName("Find item by name when exists")
    void testFindItemByName_Exists() throws MaxInstancesException, MenuItemNotFoundException {
        FoodItem item = new FoodItem("Pizza", 800, 15.99);
        menu.addItem(item);
        FoodItem found = menu.findItemByName("pizza");
        assertNotNull(found);
        assertEquals("Pizza", found.getName());
    }
    
    @Test
    @DisplayName("Find item by name throws exception when not found")
    void testFindItemByName_NotExists() {
        assertThrows(MenuItemNotFoundException.class, () -> {
            menu.findItemByName("NonExistent");
        });
    }
    
    @Test
    @DisplayName("Remove item from menu")
    void testRemoveItem() throws MaxInstancesException {
        FoodItem item = new FoodItem("Pizza", 800, 15.99);
        menu.addItem(item);
        assertEquals(1, menu.getItems().size());
        menu.removeItem(item);
        assertEquals(0, menu.getItems().size());
    }
    
    @Test
    @DisplayName("Change item price")
    void testChangeItemPrice() throws MaxInstancesException {
        FoodItem item = new FoodItem("Pizza", 800, 15.99);
        menu.addItem(item);
        menu.changeItemPrice(item, 17.99);
        assertEquals(17.99, item.getPrice());
    }
}
