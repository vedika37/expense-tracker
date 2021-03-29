package ui;

import ui.tabs.*;

import javax.swing.*;
import java.awt.*;
/*
 * Represents the Graphical User Interface for the Expense Tracker application
 */

// source: CPSC 210 long-form-problem-starters -> SmartHome

public class ExpenseTrackerGUI extends JFrame {

    private ExpenseTracker expenseTracker;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    private JTabbedPane sidebar;
    private JPanel home;
    private JPanel balance;
    private JPanel addTransactions;
    private JPanel viewTransactions;
    private JPanel save;


    //EFFECTS: creates ExpenseTrackerUI, displays sidebar and tabs
    public ExpenseTrackerGUI() {
        super("Expense Tracker");

        expenseTracker = new ExpenseTracker("Vedika");

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);
        addTabs();
        add(sidebar);

        setSize(WIDTH, HEIGHT);
        add(sidebar, BorderLayout.WEST);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //pack();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds a home tab, view balance tab, add transactions tab and view transactions tab to the UI
    public void addTabs() {

        home = new HomeTab(this);
        balance = new ViewBalanceTab(this);
        addTransactions = new AddTab(this);
        viewTransactions = new ViewTransactionsTab(this);
        save = new SaveTab(this);

        sidebar.add(home, 0);
        sidebar.setTitleAt(0, "Home");
        sidebar.add(balance, 1);
        sidebar.setTitleAt(1, "View Balance");
        sidebar.add(addTransactions, 2);
        sidebar.setTitleAt(2, "Add Transactions");
        sidebar.add(viewTransactions, 3);
        sidebar.setTitleAt(3, "View Transactions");
        sidebar.add(save, 4);
        sidebar.setTitleAt(4, "Save");
    }

    public ExpenseTracker getExpenseTracker() {
        return expenseTracker;
    }

    public JPanel getViewBalanceTab() {
        return balance;
    }

    public JPanel getAddTransactionsTab() {
        return addTransactions;
    }

    public JPanel getViewTransactionsTab() {
        return viewTransactions;
    }

    public JPanel getSaveTab() {
        return save;
    }
}
