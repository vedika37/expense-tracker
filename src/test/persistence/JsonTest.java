package persistence;

import model.Transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

// source: JsonSerializationDemo
public class JsonTest {
    protected void checkTransaction(boolean isExpense, double amount,
                                    String category, String description, Transaction transaction) {
        assertEquals(isExpense, transaction.getIsExpense());
        assertEquals(amount, transaction.getAmount());
        assertEquals(category, transaction.getCategory());
        assertEquals(description, transaction.getDescription());
    }
}
