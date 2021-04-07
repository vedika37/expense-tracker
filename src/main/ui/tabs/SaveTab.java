package ui.tabs;

import ui.ExpenseTrackerGUI;

import javax.swing.*;
import java.awt.*;

/*
 * Represents a tab that has a button that allows the user to save to file
 */

// source: Oracle Java Documentation - LabelDemo, ButtonDemo

public class SaveTab extends Tab {

    private JButton save;

    // EFFECTS: creates a Save Tab with a button allowing the user to save to file
    public SaveTab(ExpenseTrackerGUI controller) {
        super(controller);
        setup();
    }

    // MODIFIES: this
    // EFFECTS: displays a button that saves the ExpenseTracker object to file when pressed
    @Override
    public void setup() {
        save = new JButton("Save");
        save.setBounds(130, 80, 100, 30);
        add(save);


        save.addActionListener(e -> saveTransactions());

        setPreferredSize(new Dimension(ExpenseTrackerGUI.WIDTH, ExpenseTrackerGUI.HEIGHT));
        setLayout(null);
        setVisible(true);
    }


    // EFFECTS: saves transactions to file and displays a message if successful
    private void saveTransactions() {
        getController().getExpenseTracker().saveTransactionList();
        JLabel saved = new JLabel("Transactions saved!");
        saved.setBounds(130, 120, 200, 30);
        add(saved);
        
        revalidate();
        repaint();
    }
}

