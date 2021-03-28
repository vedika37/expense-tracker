package ui;

import model.Transaction;
import model.TransactionList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Represents an expense tracker application
 */

public class ExpenseTracker {

    private static final String JSON_STORE = "./data/transactionlist.json";
    private TransactionList tl;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Constructs an expense tracker account
    // EFFECTS: creates an expense tracker account with a user name and an empty list of transactions
    // source: JsonSerializationDemo
    public ExpenseTracker(String userName) {
        tl = new TransactionList(userName);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        //runAccount();
    }

    public TransactionList getTl() {
        return tl;
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // source: TellerApp project
    void runAccount() {
        Scanner sc = new Scanner(System.in);

        boolean keepGoing = true;
        String command;


        while (keepGoing) {
            displayMenu();
            command = sc.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            createTransaction();
        } else if (command.equals("2")) {
            removeTransaction();
        } else if (command.equals("3")) {
            viewTransactions();
        } else if (command.equals("4")) {
            viewBalance();
        } else if (command.equals("5")) {
            saveTransactionList();
        } else if (command.equals("6")) {
            loadTransactionList();
        } else {
            System.out.println("Selection not valid!");
        }

    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> add transaction");
        System.out.println("\t2 -> remove transaction");
        System.out.println("\t3 -> view transactions");
        System.out.println("\t4 -> view balance");
        System.out.println("\t5 -> save transactions to file");
        System.out.println("\t6 -> load transactions from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES : this
    // EFFECTS : creates a transaction by accepting data from the user and adds it to the list
    private void createTransaction() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Is it an expense or an income? Enter 'e' for expense or 'i' for income: ");
        String s = sc.next().toLowerCase();

        boolean isExpense = false;

        if (s.equals("e")) {
            isExpense = true;
        } else if (!(s.equals("i"))) {
            System.out.println("Invalid input!");
            runAccount();
        }

        System.out.print("Enter amount: $");
        double amount = sc.nextDouble();

        String category = null;
        if (isExpense) {
            displayCategories();
            sc.nextLine();
            category = sc.next().toUpperCase();
        }

        System.out.print("Enter a description: ");
        sc.nextLine();
        String description = sc.nextLine();

        Transaction t = new Transaction(isExpense, amount, category, description);

        addTransactionToList(t);
    }

    // MODIFIES: this
    // EFFECTS: removes a chosen transaction from the list
    private void removeTransaction() {

        int flag = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter transaction number to be removed:");
        int n = sc.nextInt();

        for (Transaction t : tl.getTransactions()) {
            if (t.getNumber() == n) {
                tl.removeTransaction(t);
                flag++;
                break;
            }
        }

        if (flag == 0) {
            System.out.println("That transaction does not exist!");
        } else {
            System.out.println("Transaction removed!");
        }
    }

    // EFFECTS: processes user command
    private void viewTransactions() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nSelect which transactions to view");
        System.out.println("\ta -> all");
        System.out.println("\te -> expenses");
        System.out.println("\ti -> income");
        String s = sc.next().toLowerCase();

        if (s.equals("a")) {
            viewAllTransactions();
        } else if (s.equals("e")) {
            System.out.println("Enter 'a' to view all expenses and 'c' to view them by category");
            String expenseChoice = sc.next();
            if (expenseChoice.equals("a")) {
                viewAllExpenses();
            } else {
                viewExpensesByCategory();
            }
        } else if (s.equals("i")) {
            viewIncome();
        } else {
            System.out.println("Invalid input!");
        }
    }


    public ArrayList<String> getTransactionDescriptions() {
        ArrayList<String> transactionDescriptions = new ArrayList<>();

        if (tl.getTransactions().isEmpty()) {
            transactionDescriptions.add("No transactions yet!");
        }

        String transactionDescription = "";
        String type = "Income";
        String category = "n/a";

        for (Transaction t : tl.getTransactions()) {
            transactionDescription = t.getNumber() + ". ";
            if (t.getIsExpense()) {
                type = "Expense";
                category = t.getCategory();
            }
            transactionDescription += "Expense/ Income: " + type;
            transactionDescription += " Amount: " + t.getAmount();
            transactionDescription += " Category: " + category;
            transactionDescription += " Description: " + t.getDescription();
            transactionDescriptions.add(transactionDescription);

        }

//        transactionDescriptions.add("one");
//        transactionDescriptions.add("two");
//        transactionDescriptions.add("three");
        return transactionDescriptions;
    }

    // EFFECTS: displays all transactions
    private void viewAllTransactions() {
        if (tl.getTransactions().isEmpty()) {
            System.out.println("No transactions yet!");
        }

        String type;

        for (Transaction t : tl.getTransactions()) {

            if (t.getIsExpense()) {
                type = "Expense";
            } else {
                type = "Income";
            }

            System.out.println("Transaction " + t.getNumber());
            System.out.println("Expense/Income: " + type);
            System.out.println("Amount: $" + t.getAmount());

            if (t.getIsExpense()) {
                System.out.println("Category: " + t.getCategory());
            }

            System.out.println("Description: " + t.getDescription());
            System.out.println();
        }
    }

    // EFFECTS: displays all income transactions
    private void viewIncome() {
        int flag = 0;

        for (Transaction t : tl.getTransactions()) {
            if (!t.getIsExpense()) {
                System.out.println("Transaction " + t.getNumber());
                System.out.println("Amount: $" + t.getAmount());
                System.out.println("Description: " + t.getDescription());
                System.out.println();
                flag++;
            }
        }

        if (flag == 0) {
            System.out.println("No income transactions yet!");
        }
    }

    // EFFECTS: displays all expenses
    private void viewAllExpenses() {
        int flag = 0;

        for (Transaction t : tl.getTransactions()) {
            if (t.getIsExpense()) {
                System.out.println("Transaction " + t.getNumber());
                System.out.println("Amount: $" + t.getAmount());
                System.out.println("Category: " + t.getCategory());
                System.out.println("Description: " + t.getDescription());
                System.out.println();
                flag++;
            }
        }

        if (flag == 0) {
            System.out.println("No expenses yet!");
        }
    }

    // EFFECTS: displays expenses of a particular category
    private void viewExpensesByCategory() {
        Scanner sc = new Scanner(System.in);
        displayCategories();
        String category = sc.next().toUpperCase();

        int flag = 0;

        for (Transaction t : tl.getTransactions()) {
            if (t.getIsExpense()) {
                if (t.getCategory().equalsIgnoreCase(category)) {
                    System.out.println("Transaction " + t.getNumber());
                    System.out.println("Amount: $" + t.getAmount());
                    System.out.println("Description: " + t.getDescription());
                    System.out.println();

                    flag++;
                }
            }
        }

        if (flag == 0) {
            System.out.println("No transactions of this category!");
        }
    }

    // EFFECTS: displays account balance
    private void viewBalance() {
        if (tl.getBalance() >= 0) {
            System.out.println("Balance = $" + tl.getBalance());
        } else {
            System.out.println("Balance = -$" + Math.abs(tl.getBalance()));
        }
    }

    // EFFECTS: displays available categories
    private void displayCategories() {
        System.out.println("Choose category (or enter your own category): ");
        System.out.println("HOUSING, EDUCATION, FOOD,\nTRANSPORT, HEALTH, UTILITIES,\nRECREATION, MISC, SAVINGS");
    }

    // MODIFIES: this
    // EFFECTS: adds transaction to transaction list
    public void addTransactionToList(Transaction t) {
        tl.addTransaction(t);

        System.out.println("Transaction added!");
    }

    // EFFECTS: saves the transaction list to file
    // source: JsonSerializationDemo
    public void saveTransactionList() {
        try {
            jsonWriter.open();
            jsonWriter.write(tl);
            jsonWriter.close();
            System.out.println("Saved " + tl.getUserName() + "'s transactions to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads transaction list from file
    // source: JsonSerializationDemo
    public void loadTransactionList() {
        try {
            tl = jsonReader.read();
            System.out.println("Loaded " + tl.getUserName() + "'s transactions from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public ArrayList<String> getCategories() {
        ArrayList<String> categories = new ArrayList<>();

        categories.add("INCOME");
        categories.add("HOUSING");
        categories.add("EDUCATION");
        categories.add("FOOD");
        categories.add("TRANSPORT");
        categories.add("HEALTH");
        categories.add("UTILITIES");
        categories.add("RECREATION");
        categories.add("MISC");
        categories.add("SAVINGS");

        return categories;
    }
}
