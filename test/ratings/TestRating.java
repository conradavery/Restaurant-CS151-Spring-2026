package ratings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import utilities.exceptions.MaxInstancesException;


@DisplayName("Rating Tests")
class TestRating {
    private Rating rating;
    
    @BeforeEach
    void setUp() throws MaxInstancesException {
        Rating.ratingCount = 0;
        rating = new Rating("Alice", 5, "Great food!");
    }
    
    @Test
    @DisplayName("Constructor creates Rating with correct values")
    void testConstructor_ValidInput() {
        assertNotNull(rating);
        assertEquals("Alice", rating.getName());
        assertEquals(5, rating.getStars());
        assertEquals("Great food!", rating.getMessage());
    }
    
    @Test
    @DisplayName("Constructor throws MaxInstancesException when limit exceeded")
    void testConstructor_ExceedsMaxInstances() {
        assertThrows(MaxInstancesException.class, () -> exceedMaxInstances());
    }
    
    private void exceedMaxInstances() throws MaxInstancesException {
        for (int i = 0; i < 101; i++) {
            new Rating("User", 3, "Review");
        }
    }
    
    @Test
    @DisplayName("Getters return correct values")
    void testGetters_ReturnCorrectValues() {
        assertAll("Rating getters",
            () -> assertEquals("Alice", rating.getName()),
            () -> assertEquals(5, rating.getStars()),
            () -> assertEquals("Great food!", rating.getMessage())
        );
    }
    
    @Test
    @DisplayName("Change rating updates stars correctly")
    void testChangeRating() {
        rating.changeRating(4);
        assertEquals(4, rating.getStars());
    }
    
    @Test
    @DisplayName("Change message updates correctly")
    void testChangeMessage() {
        rating.changeMessage("Amazing service!");
        assertEquals("Amazing service!", rating.getMessage());
    }
    
    @Test
    @DisplayName("toString returns properly formatted string")
    void testToString_Format() {
        String result = rating.toString();
        assertTrue(result.contains("Alice"));
        assertTrue(result.contains("5/5"));
        assertTrue(result.contains("Great food!"));
    }
    
    @Test
    @DisplayName("Setters update values correctly")
    void testSetters() {
        rating.setName("Bob");
        rating.setStars(4);
        rating.setMessage("Good experience");
        
        assertAll("After setter updates",
            () -> assertEquals("Bob", rating.getName()),
            () -> assertEquals(4, rating.getStars()),
            () -> assertEquals("Good experience", rating.getMessage())
        );
    }
}