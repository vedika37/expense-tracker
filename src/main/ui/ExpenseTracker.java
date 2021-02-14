package ui;

import model.Transaction;
import model.TransactionList;

import java.util.Scanner;

public class ExpenseTracker {

    private String userName;
    private TransactionList tl;


    public ExpenseTracker(String userName) {
        this.userName = userName;
        tl = new TransactionList();
    }

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
        System.out.println("\tq -> quit");
    }

    private void createTransaction() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Is it an expense or an income? Enter 'e' for expense or 'i' for income: ");
        String s = sc.next().toLowerCase();

        boolean isExpense = false;

        if (s.equals("e")) {
            isExpense = true;
        }

        System.out.print("Enter amount: $");
        double amount = sc.nextDouble();

        String category = null;
        if (isExpense) {
            System.out.println("Choose category: ");
            System.out.println("HOUSING, EDUCATION, FOOD,\nTRANSPORT, HEALTH, UTILITIES,\nRECREATION, MISC, SAVINGS");
            sc.nextLine();
            category = sc.next().toUpperCase();
        }

        System.out.print("Enter a description: ");
        sc.nextLine();
        String description = sc.nextLine();

        Transaction t = new Transaction(isExpense, amount, category, description);

        tl.addTransaction(t);

        System.out.println("Transaction added!");

    }

    void removeTransaction() {

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

    void viewAllTransactions() {
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

    void viewIncome() {
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

    void viewAllExpenses() {
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

    private void viewExpensesByCategory() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the category of expenses you want to view:");
        System.out.println("HOUSING, EDUCATION, FOOD,\nTRANSPORT, HEALTH, UTILITIES,\nRECREATION, MISC, SAVINGS");
        String category = sc.next().toUpperCase();

        int flag = 0;

        for (Transaction t : tl.getTransactions()) {
            if (t.getCategory().equals(category)) {
                System.out.println("Transaction " + t.getNumber());
                System.out.println("Amount: $" + t.getAmount());
                System.out.println("Description: " + t.getDescription());
                System.out.println();

                flag++;
            }
        }

        if (flag == 0) {
            System.out.println("No transactions of this category!");
        }
    }

    private void viewBalance() {
        if (tl.getBalance() >= 0) {
            System.out.println("Balance = $" + tl.getBalance());
        } else {
            System.out.println("Balance = -$" + tl.getBalance());
        }
    }
}
