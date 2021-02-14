package model;

public class Transaction {
    private static int nextTransactionNumber = 1;
    private final int number;
    private final boolean isExpense;
    private final double amount;
    private final String category;
    private final String description;

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

    public static void resetNextTransactionNumber() {
        nextTransactionNumber = 1;
    }
}
