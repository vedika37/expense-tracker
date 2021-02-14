package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    Transaction transaction1;
    Transaction transaction2;
    Transaction transaction3;

    @BeforeEach
    public void setUp(){
        transaction1 = new Transaction(false, 200, null, "tutoring");
        transaction2 = new Transaction(true, 11.50, "FOOD", "lunch");
        transaction3 = new Transaction(true, 109.90, "EDUCATION", "PSYC textbook");
    }

  /*  @Test
    public void testGetNumber(){
        assertEquals(1, transaction1.getNumber());
        assertEquals(2, transaction2.getNumber());
        assertEquals(3, transaction3.getNumber());
    }
*/
    @Test
    public void testIsExpense(){
        assertFalse(transaction1.getIsExpense());
        assertTrue(transaction2.getIsExpense());
        assertTrue(transaction3.getIsExpense());
    }

    @Test
    public void testGetCategory(){
        assertNull(transaction1.getCategory());
        assertEquals("FOOD", transaction2.getCategory());
        assertEquals("EDUCATION", transaction3.getCategory());
    }

    @Test
    public void testGetDescription(){
        assertEquals("tutoring", transaction1.getDescription());
        assertEquals("lunch", transaction2.getDescription());
        assertEquals("PSYC textbook", transaction3.getDescription());
    }
}