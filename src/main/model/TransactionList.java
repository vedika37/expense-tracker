package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

/* Represents the user's transactions,
 * including a list of transactions and the account balance
 */

public class TransactionList implements Writable {

    private String userName;
    private ArrayList<Transaction> transactions;
    private double balance;

    // EFFECTS: creates an empty transaction list with balance set to zero
    public TransactionList(String userName) {
        this.userName = userName;
        transactions = new ArrayList<>();
        balance = 0.0;
    }

    // MODIFIES: this
    // EFFECTS: adds a transaction to the transaction list and joins its amount to the balance
    public void addTransaction(Transaction t) {
        transactions.add(t);

        if (t.getIsExpense()) {
            balance -= t.getAmount();
        } else {
            balance += t.getAmount();
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a transaction to the transaction list and deducts its amount from the balance
    public void removeTransaction(Transaction t) {

        changeNumber(t.getNumber());

        transactions.remove(t);

        if (t.getIsExpense()) {
            balance += t.getAmount();
        } else {
            balance -= t.getAmount();
        }
    }

    // MODIFIES : this
    // EFFECTS: reduces transaction numbers by 1 in the list after a particular index
    public void changeNumber(int index) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getNumber() > index) {
                transactions.get(i).setNumber(i);
            }
        }
        Transaction.resetNextTransactionNumber(transactions.size());
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public double getBalance() {
        return Math.round(balance * 100) / 100.;
    }

    public int getNumber() {
        return transactions.size();
    }

    // source: JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("user name", userName);
        json.put("transactions", transactionListToJson());
        return json;
    }

    // EFFECTS: returns transactions in this transaction list as a JSON array
    // source: JsonSerializationDemo
    private JSONArray transactionListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Transaction t : this.transactions) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}
