package payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import menuAndFoodItems.FoodItem;
import order.Order;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
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
        assertThrows(MaxInstancesException.class, () -> exceedMaxInstances());
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
    void testCorrectChange() throws MaxInstancesException {
        // need to walk through orderign process
        FoodItem burger = new FoodItem("burger", 500, 24.99);
        FoodItem fries = new FoodItem("fries", 300, 5.99);
        Order o = new Order();
        o.addItemToOrder(fries);
        o.addItemToOrder(burger);

        assertEquals(o.getTotalPrice(), 30.98, 0.01);

        String cash = "40.00";
        ByteArrayInputStream fakeInput = new ByteArrayInputStream(cash.getBytes());
        System.setIn(fakeInput);
        assertTrue(cashPayment.validatePayment(o));
        assertEquals(9.02, cashPayment.getChange(), 0.01);
    }
    
    @Test
    @DisplayName("Not enough money")
    void testChangeNotEnough() throws MaxInstancesException {
        // need to walk through orderign process
        FoodItem burger = new FoodItem("burger", 500, 24.99);
        FoodItem fries = new FoodItem("fries", 300, 5.99);
        Order o = new Order();
        o.addItemToOrder(fries);
        o.addItemToOrder(burger);

        assertEquals(o.getTotalPrice(), 30.98, 0.01);

        String cash = "20.00\n30.00\n40\n40.00";
        ByteArrayInputStream fakeInput = new ByteArrayInputStream(cash.getBytes());
        System.setIn(fakeInput);
        assertTrue(cashPayment.validatePayment(o));
        assertEquals(9.02, cashPayment.getChange(), 0.01);
    }
}