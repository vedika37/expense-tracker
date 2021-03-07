package model;

/* Represents a transaction
 */

import org.json.JSONObject;
import persistence.Writable;

public class Transaction implements Writable {
    private static int nextTransactionNumber = 1;
    private int number;
    private boolean isExpense;
    private double amount;
    private String category;
    private String description;

    // Constructs a transaction
    // EFFECTS: initializes a transaction as an expense/income with given, amount, category and description
    public Transaction(boolean isExpense, double amount, String category, String description) {
        this.number = nextTransactionNumber++;
        this.isExpense = isExpense;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }


    public int getNumber() {
        return this.number;
    }

    public boolean getIsExpense() {
        return this.isExpense;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }

    public void setNumber(int n) {
        this.number = n;
    }

    // MODIFIES: this
    // EFFECTS: resets the next transaction number
    public static void resetNextTransactionNumber(int n) {
        nextTransactionNumber = n;
    }


    // source: JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("number", number);
        json.put("is it an expense?", isExpense);
        json.put("amount", amount);
        json.put("category", category);
        json.put("description", description);
        return json;
    }
}
