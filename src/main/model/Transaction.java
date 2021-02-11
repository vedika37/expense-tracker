package model;

public class Transaction {

    double amount;
    String category;
    String date;
    String description;

    public Transaction(double amount, String category, String date, String description) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    double getAmount() {
        return this.amount;
    }

    String getDate() {
        return this.date;
    }

    String getDescription() {

        return this.description;
    }

    void addTransaction(){

    }
}
