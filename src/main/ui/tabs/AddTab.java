package ui.tabs;

import model.Transaction;
import model.TransactionList;
import ui.ExpenseTracker;
import ui.ExpenseTrackerGUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/*
 * Represents an Add Transactions Tab that allows the user to add a transaction to their account
 * and play a sound whenever a transaction is successfully added
 */

// sources: Oracle Java Documentation - LabelDemo, ComboBoxDemo, ComboBoxDemo2, TextFieldDemo
//          sound - http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
public class AddTab extends Tab {

    private ExpenseTracker expenseTracker;

    private JComboBox expenseChoice;
    private Label amountLabel;
    private Label dollar;
    private JTextField field;
    private Label category;
    private JComboBox categoryChoice;
    private Label description;
    private JTextField descriptionField;
    private JButton submitButton;

    private ViewBalanceTab viewBalanceTab;
    private ViewTransactionsTab viewTransactionsTab;


    // EFFECTS: creates a tab that allows users to add transactions to their account
    public AddTab(ExpenseTrackerGUI controller) {
        super(controller);
        expenseTracker = controller.getExpenseTracker();
        setup();
    }

    // MODIFIES: this
    // EFFECTS: sets up a tab allowing the user to input a transaction
    @Override
    public void setup() {
        setUpHelper();

        Object[] categories = expenseTracker.getCategories().toArray();
        categoryChoice = new JComboBox(categories);
        categoryChoice.setBounds(240, 200, 100, 30);
        categoryChoice.setLightWeightPopupEnabled(false);

        description = new Label("Description:");
        description.setBounds(100, 170, 70, 30);
        descriptionField = new JTextField(5);
        descriptionField.setBounds(100, 200, 100, 30);

        submitButton = new JButton("Submit");
        submitButton.setBounds(150, 300, 100, 30);

        addAllComponents();

        setLayout(null);
        submitButton.addActionListener(e -> guiAddTransaction());
    }

    // MODIFIES: this
    // EFFECTS: helper method for setting up the add transaction tab
    private void setUpHelper() {
        String[] expenseType = {"Expense", "Income"};
        expenseChoice = new JComboBox(expenseType);
        expenseChoice.setBounds(240, 130, 100, 30);
        expenseChoice.setLightWeightPopupEnabled(false);

        amountLabel = new Label("Amount:");
        amountLabel.setBounds(100, 100, 600, 30);

        dollar = new Label("$");
        dollar.setBounds(80, 130, 20, 30);

        field = new JTextField(5);
        field.setBounds(100, 130, 100, 30);

        category = new Label("Choose Category:");
        category.setBounds(240, 170, 200, 30);
    }

    // EFFECTS: adds the components to the Add Transaction Tab
    private void addAllComponents() {
        add(expenseChoice);
        add(amountLabel);
        add(dollar);
        add(field);
        add(description);
        add(descriptionField);
        add(category);
        add(categoryChoice);
        add(submitButton);

        setPreferredSize(new Dimension(ExpenseTrackerGUI.WIDTH, ExpenseTrackerGUI.HEIGHT));
    }

    // MODIFIES: this, ExpenseTracker
    // EFFECTS: adds the transaction entered by the user into the ExpenseTracker and plays a sound
    private void guiAddTransaction() {
        if (!field.getText().isEmpty()) {
            Double amount = Double.parseDouble(field.getText());
            String category = (String) categoryChoice.getSelectedItem();
            String expenseOrIncome = (String) expenseChoice.getSelectedItem();
            Boolean isExpense = false;
            if (expenseOrIncome.equalsIgnoreCase("expense")) {
                isExpense = true;
            }
            String description = descriptionField.getText();
            expenseTracker.addTransactionToList(new Transaction(isExpense, amount, category, description));

            playSound("./data/kaching.wav");
            removeAll();
            setup();
            revalidate();
            repaint();


            update();
        }
    }

    // MODIFIES: ViewBalanceTab, ViewTransactionsTab
    // EFFECTS:  updates the other panels when changes are made to this panel
    public void update() {
        viewBalanceTab = (ViewBalanceTab) getController().getViewBalanceTab();
        viewBalanceTab.setup();

        viewTransactionsTab = (ViewTransactionsTab) getController().getViewTransactionsTab();
        viewTransactionsTab.setup();
    }

    // EFFECTS: plays a sound
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
