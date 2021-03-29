package ui.tabs;

import ui.ExpenseTrackerGUI;

import javax.swing.*;
import java.awt.*;

/*
 * Represents a View Balance Tab that allows the user to view their account balance
 */

// source: Oracle Java Documentation - LabelDemo

public class ViewBalanceTab extends Tab {

    private JLabel balanceLabel;
    private Double balance;

    // EFFECTS: constructs a View Balance Tab that displays user balance
    public ViewBalanceTab(ExpenseTrackerGUI controller) {
        super(controller);
        displayBalanceTab();
    }

    // MODIFIES: this
    // EFFECTS: displays the balance in the user's account on the screen
    public void displayBalanceTab() {
        removeAll();
        balance = getController().getExpenseTracker().getTl().getBalance();
        String balanceMessage = "Current Balance: ";
        if (balance >= 0) {
            balanceMessage += "$" + balance.toString();
        } else {
            balanceMessage += "-$" + balance.toString().substring(1);
        }

        balanceLabel = new JLabel(balanceMessage);

        Dimension size = balanceLabel.getPreferredSize();
        balanceLabel.setBounds(150, 100, size.width, size.height);

        setLayout(null);
        add(balanceLabel);


        revalidate();
        repaint();
    }
}


