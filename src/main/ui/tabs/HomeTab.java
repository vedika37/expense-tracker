package ui.tabs;

import ui.ExpenseTrackerGUI;

import javax.swing.*;
import java.awt.*;

/*
 * Represents a home tab with a greeting and button that allows the user to load transactions from file
 */

// source: Oracle Java Documentation - LabelDemo, ButtonDemo
public class HomeTab extends Tab {

    JButton load;
    private ViewBalanceTab viewBalanceTab;
    private ViewTransactionsTab viewTransactionsTab;

    //EFFECTS: constructs a home tab with a greeting and a load button
    public HomeTab(ExpenseTrackerGUI controller) {
        super(controller);

        setup();
    }

    @Override
    public void setup() {
        displayWelcomeMessage();
        displayLoadButton();
    }


    // EFFECTS: displays a welcome message on the screen
    public void displayWelcomeMessage() {

        String message = "Welcome to ExpenseTracker!";
        JLabel welcome = new JLabel(message);

        Dimension size = welcome.getPreferredSize();
        welcome.setBounds(135, 20, size.width, size.height);

        setLayout(null);
        add(welcome);
        //setBackground(Color.cyan);
    }

    // MODIFIES: this, ExpenseTrackerGUI
    // EFFECTS: displays a button that loads transactions from file when clicked
    public void displayLoadButton() {
        load = new JButton("Load");
        load.setBounds(130, 80, 100, 30);
        add(load);


        load.addActionListener(e -> loadTransactions());

        setPreferredSize(new Dimension(ExpenseTrackerGUI.WIDTH, ExpenseTrackerGUI.HEIGHT));
        setLayout(null);
        setVisible(true);
    }

    // EFFECTS: loads transactions from file and displays a message if successful
    private void loadTransactions() {
        getController().getExpenseTracker().loadTransactionList();
        update();

        JLabel loaded = new JLabel("Transactions loaded!");
        loaded.setBounds(130, 120, 200, 30);
        add(loaded);

        revalidate();
        repaint();
    }

    // MODIFIES: ViewBalanceTab, ViewTransactionsTab
    // EFFECTS: updates the other panels when changes are made to this panel
    public void update() {
        viewBalanceTab = (ViewBalanceTab) getController().getViewBalanceTab();
        viewBalanceTab.setup();

        viewTransactionsTab = (ViewTransactionsTab) getController().getViewTransactionsTab();
        viewTransactionsTab.setup();
    }
}
