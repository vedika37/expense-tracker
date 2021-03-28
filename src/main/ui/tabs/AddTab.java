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

public class AddTab extends Tab {

    private ExpenseTracker expenseTracker;
    private TransactionList transactionList;

    private JComboBox expenseChoice;
    private Label label;
    private Label dollar;
    private JTextField field;
    private Label labelCategory;
    private JComboBox categoryChoice;
    private Label description;
    private JTextField descriptionField;
    private JButton button;

    private Double balance;
    private ViewBalanceTab viewBalanceTab;
    private ViewTransactionsTab viewTransactionsTab;


    public AddTab(ExpenseTrackerGUI controller) {
        super(controller);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, controller.HEIGHT, controller.WIDTH));
        expenseTracker = controller.getExpenseTracker();
        transactionList = expenseTracker.getTl();
        setUpAddTab();
        balance = expenseTracker.getTl().getBalance();
    }

    public void setUpAddTab() {
        setUpHelper();

        Object[] categories = expenseTracker.getCategories().toArray();
        categoryChoice = new JComboBox(categories);
        categoryChoice.setBounds(240, 200, 100, 30);
        categoryChoice.setLightWeightPopupEnabled(false);

        description = new Label("Description:");
        description.setBounds(100, 170, 70, 30);
        descriptionField = new JTextField(5);
        descriptionField.setBounds(100, 200, 100, 30);
        button = new JButton("Submit");
        button.setBounds(150, 300, 100, 30);

        addAllComponents();

        setLayout(null);
        button.addActionListener(e -> guiMakeEntry());
    }

    private void setUpHelper() {
        String[] expenseType = {"Expense", "Income"};
        expenseChoice = new JComboBox(expenseType);
        expenseChoice.setBounds(240, 130, 100, 30);
        expenseChoice.setLightWeightPopupEnabled(false);
        label = new Label("Amount:");
        label.setBounds(100, 100, 600, 30);
        dollar = new Label("$");
        dollar.setBounds(80, 130, 20, 30);
        field = new JTextField(5);
        field.setBounds(100, 130, 100, 30);
        labelCategory = new Label("Choose Category:");
        labelCategory.setBounds(240, 170, 200, 30);
    }

    private void addAllComponents() {
        add(expenseChoice);
        add(label);
        add(dollar);
        add(field);
        add(description);
        add(descriptionField);
        add(labelCategory);
        add(categoryChoice);
        add(button);
    }

    private void guiMakeEntry() {
        if (!field.getText().isEmpty()) {
            Double amount = Double.parseDouble(field.getText());
            String category = (String) categoryChoice.getSelectedItem();
            Boolean isExpense = (expenseChoice.getSelectedItem() == "Expense");
            String description = descriptionField.getText();
            expenseTracker.addTransactionToList(new Transaction(isExpense, amount, category, description));

            playSound("./data/kaching.wav");
            removeAll();
            setUpAddTab();
            revalidate();
            repaint();


            update();
        }
    }

    public void update() {
        viewBalanceTab = (ViewBalanceTab) getController().getViewBalanceTab();
        viewBalanceTab.displayBalanceTab();

        viewTransactionsTab = (ViewTransactionsTab) getController().getViewTransactions();
        viewTransactionsTab.displayTransactions();
    }

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
