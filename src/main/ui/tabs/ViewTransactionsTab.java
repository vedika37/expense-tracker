package ui.tabs;

import ui.ExpenseTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
 * Represents a View Transactions Tab that allows the user to view the transactions in their account
 */

// source: Oracle Java Documentation - LabelDemo

public class ViewTransactionsTab extends Tab {

    ArrayList<String> transactionDescriptions;
    ArrayList<JLabel> labelArrayList;

    // EFFECTS: constructs a View Transactions Tab that displays the transactions added to the user's account
    public ViewTransactionsTab(ExpenseTrackerGUI controller) {
        super(controller);

        setup();

    }

    // MODIFIES: this
    // EFFECTS: displays transactions currently saved to the user's account
    @Override
    public void setup() {
        removeAll();

        transactionDescriptions = getController().getExpenseTracker().getTransactionDescriptions();
        labelArrayList = new ArrayList<>();

        setLayout(null);
        for (String s : transactionDescriptions) {
            labelArrayList.add(new JLabel(s));
        }

        int i = 0;

        for (JLabel label : labelArrayList) {

            Dimension size = label.getPreferredSize();
            label.setBounds(5, 20 + (i * size.height), size.width, size.height);

            add(label);

            i++;

        }

        setPreferredSize(new Dimension(ExpenseTrackerGUI.WIDTH, ExpenseTrackerGUI.HEIGHT));
        setVisible(true);

        revalidate();
        repaint();
    }

}
