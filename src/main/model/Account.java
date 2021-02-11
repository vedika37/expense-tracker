package model;

import java.util.ArrayList;
import java.util.Collection;

public class Account {

    String name;
    double income;
    double balance;
    ArrayList<Transaction> transactions;
    // ArrayList<String> categories;
    // ArrayList<Double> percentages;

    public Account(String name, double income) {

        this.name = name;
        this.income = income;
        this.balance = income;
        this.transactions = new ArrayList<Transaction>();
        //this.percentages = new ArrayList<Double>();
    }

    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }


}
