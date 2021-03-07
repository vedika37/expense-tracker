package persistence;

import model.Transaction;
import model.TransactionList;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/* TODO */
// source: TODO
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            TransactionList tl = new TransactionList("Vedika");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            TransactionList tl = new TransactionList("Vedika");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTransactionList.json");
            writer.open();
            writer.write(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTransactionList.json");
            tl = reader.read();
            assertEquals("Vedika", tl.getUserName());
            assertEquals(0, tl.getNumber());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            TransactionList tl = new TransactionList("Vedika");
            tl.addTransaction(new Transaction(true, 150, "EDUCATION", "BIOL textbook"));
            tl.addTransaction(new Transaction(false, 100,"", "pet sitting"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTransactionList.json");
            writer.open();
            writer.write(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTransactionList.json");
            tl = reader.read();
            assertEquals("Vedika", tl.getUserName());
            List<Transaction> transactions = tl.getTransactions();
            assertEquals(2, transactions.size());
            checkTransaction(true, 150, "EDUCATION", "BIOL textbook", transactions.get(0));
            checkTransaction(false, 100,null, "pet sitting", transactions.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}