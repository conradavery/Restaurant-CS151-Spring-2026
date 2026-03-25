package payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import utilities.exceptions.MaxInstancesException;
import utilities.exceptions.InvalidCreditInfoException;

@DisplayName("CardPayment Tests")
class TestCardPayment {
    private CardPayment cardPayment;
    
    @BeforeEach
    void setUp() throws MaxInstancesException {
        CardPayment.cardPaymentCount = 0;
        cardPayment = new CardPayment();
    }
    
    @Test
    @DisplayName("Constructor throws MaxInstancesException when limit exceeded")
    void testConstructor_ExceedsMaxInstances() {
        assertThrows(MaxInstancesException.class, this::exceedMaxInstances);
    }
    
    private void exceedMaxInstances() throws MaxInstancesException {
        for (int i = 0; i < 101; i++) {
            new CardPayment();
        }
    }
    
    @Test
    @DisplayName("Process credit info with valid data - no exception")
    void testProcessCreditInfo_ValidData() throws MaxInstancesException, InvalidCreditInfoException {
        cardPayment.setCardNumber("1234567890123456");
        cardPayment.setCardHolder("John Doe");
        cardPayment.setExpiryDate("12/25");
        cardPayment.setCVV("123");
        cardPayment.processCreditInfo();
    }
    
    @Test
    @DisplayName("Process credit info throws exception when card number is null")
    void testProcessCreditInfo_NullCardNumber() throws MaxInstancesException {
        cardPayment.setCardNumber(null);
        cardPayment.setCardHolder("John Doe");
        cardPayment.setExpiryDate("12/25");
        cardPayment.setCVV("123");
        assertThrows(InvalidCreditInfoException.class, () -> {
            cardPayment.processCreditInfo();
        });
    }
    @DisplayName("Process credit info throws exception when card number is null")
    void testProcessCreditInfo_NullCardHolder() throws MaxInstancesException {
        cardPayment.setCardNumber("1234567890123456");
        cardPayment.setCardHolder(null);
        cardPayment.setExpiryDate("12/25");
        cardPayment.setCVV("123");
        assertThrows(InvalidCreditInfoException.class, () -> {
            cardPayment.processCreditInfo();
        });
    }
    @DisplayName("Process credit info throws exception when card number is null")
    void testProcessCreditInfo_NullExpiryDate() throws MaxInstancesException {
        cardPayment.setCardNumber("1234567890123456");
        cardPayment.setCardHolder("John Smith");
        cardPayment.setExpiryDate(null);
        cardPayment.setCVV("123");
        assertThrows(InvalidCreditInfoException.class, () -> {
            cardPayment.processCreditInfo();
        });
    }
    @DisplayName("Process credit info throws exception when card number is null")
    void testProcessCreditInfo_NullCVV() throws MaxInstancesException {
        cardPayment.setCardNumber("1234567890123456");
        cardPayment.setCardHolder("John Smith");
        cardPayment.setExpiryDate("12/25");
        cardPayment.setCVV(null);
        assertThrows(InvalidCreditInfoException.class, () -> {
            cardPayment.processCreditInfo();
        });
    }
    
    @Test
    @DisplayName("Process credit info throws exception when card number length is not 16")
    void testProcessCreditInfo_InvalidCardLength() throws MaxInstancesException {
        cardPayment.setCardNumber("12345678");
        cardPayment.setCardHolder("John Doe");
        cardPayment.setExpiryDate("12/25");
        cardPayment.setCVV("123");
        assertThrows(InvalidCreditInfoException.class, () -> {
            cardPayment.processCreditInfo();
        });
    }
    
    @Test
    @DisplayName("Process credit info throws exception when CVV is not 3 digits")
    void testProcessCreditInfo_InvalidCVV() throws MaxInstancesException {
        cardPayment.setCardNumber("1234567890123456");
        cardPayment.setCardHolder("John Doe");
        cardPayment.setExpiryDate("12/25");
        cardPayment.setCVV("12");
        assertThrows(InvalidCreditInfoException.class, () -> {
            cardPayment.processCreditInfo();
        });
    }
    
    @Test
    @DisplayName("Process credit info throws exception when expiry date is invalid")
    void testProcessCreditInfo_InvalidExpiry() throws MaxInstancesException {
        cardPayment.setCardNumber("1234567890123456");
        cardPayment.setCardHolder("John Doe");
        cardPayment.setExpiryDate("invalid");
        cardPayment.setCVV("123");
        assertThrows(InvalidCreditInfoException.class, () -> {
            cardPayment.processCreditInfo();
        });
    }
    
    @Test
    @DisplayName("Getters and setters work correctly")
    void testGettersAndSetters() {
        cardPayment.setCardNumber("1234567890123456");
        cardPayment.setCardHolder("John Doe");
        cardPayment.setExpiryDate("12/25");
        cardPayment.setCVV("123");
        
        assertAll("CardPayment getters",
            () -> assertEquals("1234567890123456", cardPayment.getCardNumber()),
            () -> assertEquals("John Doe", cardPayment.getCardHolder()),
            () -> assertEquals("12/25", cardPayment.getExpiryDate()),
            () -> assertEquals("123", cardPayment.getCVV())
        );
    }
}