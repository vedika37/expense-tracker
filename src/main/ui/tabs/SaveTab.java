package ui.tabs;

import ui.ExpenseTrackerGUI;

import javax.swing.*;

public class SaveTab extends Tab {

    private JButton save;

    public SaveTab(ExpenseTrackerGUI controller) {
        super(controller);
        displaySaveButton();
    }

    public void displaySaveButton() {
        save = new JButton("Save");
        save.setBounds(130, 80, 100, 30);
        add(save);


        save.addActionListener(e -> saveTransactions());

        setLayout(null);
        setVisible(true);
    }

    private void saveTransactions() {
        getController().getExpenseTracker().saveTransactionList();
        JLabel saved = new JLabel("Transactions saved!");
        saved.setBounds(130, 120, 200, 30);
        add(saved);

        revalidate();
        repaint();
    }
}

