package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Unit tests for the TransactionList class.
 */

class TransactionListTest {

    TransactionList transactionList;
    Transaction transaction1;
    Transaction transaction2;
    Transaction transaction3;
    Transaction transaction4;

    @BeforeEach
    public void setUp() {
        transactionList = new TransactionList("Vedika");
        transaction1 = new Transaction(false, 200, null, "tutoring");
        transaction2 = new Transaction(true, 11.50, "FOOD", "lunch");
        transaction3 = new Transaction(true, 109.90, "EDUCATION", "PSYC textbook");
        transaction4 = new Transaction(false, 100, null, "pet sitting");
    }

    @Test
    public void testGetTransactionsEmptyList() {
        assertEquals(0, transactionList.getTransactions().size());
    }

    @Test
    public void testGetBalanceEmptyList() {
        assertEquals(0.0, transactionList.getBalance());
    }

    @Test
    public void testAddTransactionExpense() {
        transactionList.addTransaction(transaction2);
        assertEquals(1, transactionList.getTransactions().size());
        assertEquals(-11.50, transactionList.getBalance());
    }

    @Test
    public void testAddTransactionIncome() {
        transactionList.addTransaction(transaction1);
        assertEquals(1, transactionList.getTransactions().size());
        assertEquals(200.00, transactionList.getBalance());
    }

    @Test
    public void testAddTransactionMultipleTransactions(){
        transactionList.addTransaction(transaction1);
        transactionList.addTransaction(transaction2);
        transactionList.addTransaction(transaction3);
        transactionList.addTransaction(transaction4);

        assertEquals(4, transactionList.getTransactions().size());
        assertEquals(178.60, transactionList.getBalance());
    }

    @Test
    public void testRemoveTransactionExpense() {
        transactionList.addTransaction(transaction2);
        transactionList.addTransaction(transaction3);

        transactionList.removeTransaction(transaction3);
        assertEquals(1, transactionList.getTransactions().size());
        assertEquals(-11.50, transactionList.getBalance());

    }

    @Test
    public void testRemoveTransactionIncome() {
        transactionList.addTransaction(transaction1);
        transactionList.addTransaction(transaction4);

        transactionList.removeTransaction(transaction4);
        assertEquals(1, transactionList.getTransactions().size());
        assertEquals(200, transactionList.getBalance());
    }

    @Test
    public void testRemoveTransactionMultipleTransactions(){
        transactionList.addTransaction(transaction1);
        transactionList.addTransaction(transaction2);
        transactionList.addTransaction(transaction3);
        transactionList.addTransaction(transaction4);

        transactionList.removeTransaction(transaction1);
        transactionList.removeTransaction(transaction3);

        assertEquals(2, transactionList.getTransactions().size());
        assertEquals(88.50, transactionList.getBalance());
    }

    @Test
    public void testRemoveTransactionAllTransactions(){
        transactionList.addTransaction(transaction1);
        transactionList.addTransaction(transaction2);
        transactionList.addTransaction(transaction3);
        transactionList.addTransaction(transaction4);

        transactionList.removeTransaction(transaction1);
        transactionList.removeTransaction(transaction2);
        transactionList.removeTransaction(transaction3);
        transactionList.removeTransaction(transaction4);

        assertEquals(0, transactionList.getTransactions().size());
        assertEquals(0, transactionList.getBalance());
    }

    @Test
    public void testAddTransactionAfterRemoving(){
        transactionList.addTransaction(transaction1);
        transactionList.addTransaction(transaction2);
        transactionList.addTransaction(transaction3);

        transactionList.removeTransaction(transaction1);
        transactionList.removeTransaction(transaction2);

        transactionList.addTransaction(transaction4);

        assertEquals(2, transactionList.getTransactions().size());
        assertEquals(-9.90, transactionList.getBalance());

    }
}
