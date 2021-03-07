package persistence;

import model.Transaction;
import model.TransactionList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * Unit tests for the JsonReader class.
 */
// source: JsonSerializationDemo

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TransactionList tl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTransactionList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTransactionList.json");
        try {
            TransactionList tl = reader.read();
            assertEquals("Vedika", tl.getUserName());
            assertEquals(0, tl.getNumber());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTransactionList.json");
        try {
            TransactionList tl = reader.read();
            assertEquals("Vedika", tl.getUserName());
            List<Transaction> transactions = tl.getTransactions();
            assertEquals(2, transactions.size());
            checkTransaction(true, 150, "EDUCATION", "BIOL textbook", transactions.get(0));
            checkTransaction(false, 100,null, "pet sitting", transactions.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}