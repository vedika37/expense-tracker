//package ui;
//
//import model.TransactionList;
//import ui.tabs.AddTab;
//import ui.tabs.RemoveTab;
//import ui.tabs.ViewBalanceTab;
//
//import javax.swing.*;
//
//public class ExpenseTrackerUI extends JFrame {
//    public static final int VIEW_TAB_INDEX = 0;
//    public static final int ADD_TAB_INDEX = 1;
//    public static final int REMOVE_TAB_INDEX = 2;
//
//    public static final int WIDTH = 600;
//    public static final int HEIGHT = 400;
//    private JTabbedPane sidebar;
//    private ExpenseTracker expenseTracker;
//
//    public static void main(String[] args) {
//        new ExpenseTracker("Vedika");
//    }
//
//    //MODIFIES: this
//    //EFFECTS: creates SmartHomeUI, loads SmartHome appliances, displays sidebar and tabs
//    private ExpenseTrackerUI() {
//        super("Expense Tracker");
//        setSize(WIDTH, HEIGHT);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        expenseTracker = new ExpenseTracker("Vedika");
//        loadExpenses();
//
//        sidebar = new JTabbedPane();
//        sidebar.setTabPlacement(JTabbedPane.LEFT);
//
//        loadTabs();
//        add(sidebar);
//
//        setVisible(true);
//    }
//
//    //EFFECTS: returns SmartHome object controlled by this UI
//    public ExpenseTracker getExpenseTracker() {
//        return expenseTracker;
//    }
//
//    //MODIFIES: this
//    //EFFECTS: installs several appliances and sets no one home
//    private void loadExpenses() {
//        TransactionList transactionList = new TransactionList("Vedika");
//    }
//
//    //MODIFIES: this
//    //EFFECTS: adds home tab, settings tab and report tab to this UI
//    private void loadTabs() {
//        JPanel viewTab = new ViewBalanceTab(this);
//        JPanel addTab = new AddTab(this);
//        JPanel removeTab = new RemoveTab(this);
//
//        sidebar.add(viewTab, VIEW_TAB_INDEX);
//        sidebar.setTitleAt(VIEW_TAB_INDEX, "View Balance");
//        sidebar.add(addTab, ADD_TAB_INDEX);
//        sidebar.setTitleAt(ADD_TAB_INDEX, "Add Transaction");
//        sidebar.add(removeTab, REMOVE_TAB_INDEX);
//        sidebar.setTitleAt(REMOVE_TAB_INDEX, "Remove Transaction");
//    }
//
//    //EFFECTS: returns sidebar of this UI
//    public JTabbedPane getTabbedPane() {
//        return sidebar;
//    }
//
//
//}
