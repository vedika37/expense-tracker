package persistence;

import model.Transaction;
import model.TransactionList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads transactions from JSON data stored in file
// source: TODO
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads transaction list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TransactionList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTransactionList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses transaction list from JSON object and returns it
    private TransactionList parseTransactionList(JSONObject jsonObject) {
        String userName = jsonObject.getString("user name");
        TransactionList tl = new TransactionList(userName);
        addTransactions(tl, jsonObject);
        return tl;
    }

    // MODIFIES: tl
    // EFFECTS: parses transactions from JSON object and adds them to transaction list
    private void addTransactions(TransactionList tl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("transactions");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addTransaction(tl, nextThingy);
        }
    }

    // MODIFIES: tl
    // EFFECTS: parses transaction from JSON object and adds it to transaction list
    private void addTransaction(TransactionList tl, JSONObject jsonObject) {

        boolean isExpense = jsonObject.getBoolean("is it an expense?");
        double amount = jsonObject.getDouble("amount");
        String category = null;
        if (isExpense) {
            category = jsonObject.getString("category");
        }
        String description = jsonObject.getString("description");

        Transaction t = new Transaction(isExpense, amount, category, description);

        tl.addTransaction(t);
    }
}
