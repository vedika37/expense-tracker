package ui.tabs;

import ui.ExpenseTrackerGUI;

import javax.swing.*;
import java.awt.*;


public class ViewBalanceTab extends Tab {

    private JLabel balanceLabel;
    private Double balance = 0.0;

    public ViewBalanceTab(ExpenseTrackerGUI controller) {
        super(controller);
        displayBalanceTab();
    }

    public void displayBalanceTab() {
        removeAll();
        balance = getController().getExpenseTracker().getTl().getBalance();
        String balanceMessage = "Current Balance: ";
        if (balance >= 0) {
            balanceMessage += "$" + balance.toString();
        } else {
            balanceMessage += "-$" + balance.toString().substring(1);
        }

        JLabel balanceLabel = new JLabel(balanceMessage);

        Dimension size = balanceLabel.getPreferredSize();
        balanceLabel.setBounds(150, 100, size.width, size.height);

        this.setLayout(null);
        this.add(balanceLabel);


        revalidate();
        repaint();
    }
}


