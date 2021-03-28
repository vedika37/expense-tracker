package ui.tabs;

import ui.ExpenseTrackerGUI;

import javax.swing.*;
import java.awt.*;

public class HomeTab extends Tab {

    JButton load;
    private ViewBalanceTab viewBalanceTab;
    private ViewTransactionsTab viewTransactionsTab;

    public HomeTab(ExpenseTrackerGUI controller) {
        super(controller);
        displayWelcomeMessage();
        displayLoadButton();
    }

    public void displayWelcomeMessage() {

        String message = "Welcome to ExpenseTracker!";
        JLabel welcome = new JLabel(message);

        Dimension size = welcome.getPreferredSize();
        welcome.setBounds(135, 20, size.width, size.height);

        this.setLayout(null);
        this.add(welcome);
        //this.setBackground(Color.cyan);
    }

    public void displayLoadButton() {
        load = new JButton("Load");
        load.setBounds(130, 80, 100, 30);
        add(load);


        load.addActionListener(e -> loadTransactions());

        setLayout(null);
        setVisible(true);
    }

    private void loadTransactions() {
        getController().getExpenseTracker().loadTransactionList();
        update();

        JLabel loaded = new JLabel("Transactions loaded!");
        loaded.setBounds(130, 120, 200, 30);
        add(loaded);

        revalidate();
        repaint();
    }

    public void update() {
        viewBalanceTab = (ViewBalanceTab) getController().getViewBalanceTab();
        viewBalanceTab.displayBalanceTab();

        viewTransactionsTab = (ViewTransactionsTab) getController().getViewTransactions();
        viewTransactionsTab.displayTransactions();
    }
}
