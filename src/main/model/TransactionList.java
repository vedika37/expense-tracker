package model;

import java.util.ArrayList;

/* Represents the user's transactions,
 * including a list of transactions and the account balance
 */

public class TransactionList {

    private ArrayList<Transaction> transactions;
    private double balance;

    // EFFECTS: creates an empty transaction list with balance set to zero
    public TransactionList() {
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

    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public double getBalance() {
        return balance;
    }
}
