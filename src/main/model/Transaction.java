package model;

public class Transaction {

    private static int nextTransactionNumber = 1;
    private int number;
    private boolean isExpense;
    private double amount;
    private String category;
    private String description;

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

}
