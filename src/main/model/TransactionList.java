package model;

import java.util.ArrayList;

public class TransactionList {

    private ArrayList<Transaction> transactions;
    private double balance;

    public TransactionList() {
        transactions = new ArrayList<>();
        balance = 0.0;
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);

        if (t.getIsExpense()) {
            balance -= t.getAmount();
        } else {
            balance += t.getAmount();
        }
    }


    public void removeTransaction(Transaction t) {
        transactions.remove(t);

        if (t.getIsExpense()) {
            balance += t.getAmount();
        } else {
            balance -= t.getAmount();
        }
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public double getBalance() {
        return balance;
    }
}
