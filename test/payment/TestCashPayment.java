package payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;

import org.junit.jupiter.api.AfterEach;

import utilities.exceptions.MaxInstancesException;

@DisplayName("CashPayment Tests")
class TestCashPayment {
    private CashPayment cashPayment;
    private final InputStream originalSystemIn = System.in;

    
    @BeforeEach
    void setUp() throws MaxInstancesException {
        CashPayment.cashPaymentCount = 0;
        cashPayment = new CashPayment();
    }
    @AfterEach
    public void tearDown() {
        System.setIn(originalSystemIn);
    }
    
    @Test
    @DisplayName("Constructor throws MaxInstancesException when limit exceeded")
    void testConstructor_ExceedsMaxInstances() {
        assertThrows(MaxInstancesException.class, this::exceedMaxInstances);
    }
    
    private void exceedMaxInstances() throws MaxInstancesException {
        for (int i = 0; i < 101; i++) {
            new CashPayment();
        }
    }
    
    @Test
    @DisplayName("Getters and setters work correctly")
    void testSettersAndGetters() {
        cashPayment.setBillTotal(25.50);
        cashPayment.setCashPaid(30.00);
        cashPayment.setChange(4.50);
        
        assertAll("CashPayment getters",
            () -> assertEquals(25.50, cashPayment.getBillTotal()),
            () -> assertEquals(30.00, cashPayment.getCashPaid()),
            () -> assertEquals(4.50, cashPayment.getChange())
        );
    }

    @Test
    @DisplayName("Generates correct change")
    void testCorrectChange() {
        // need to walk through orderign process

        cashPayment.setBillTotal(24.90);
        cashPayment.setCashPaid(30.00);
        cashPayment.calculateChange();
        assertEquals(5.1, cashPayment.getChange(), 0.01);
    }
    
    @Test
    @DisplayName("Generates correct change")
    void testChangeNotEnough() {
        cashPayment.setBillTotal(24.90);
        cashPayment.setCashPaid(20.00);
        cashPayment.calculateChange();
        assertEquals(0.0, cashPayment.getChange());
    }
}