package ui.tabs;

import ui.ExpenseTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewTransactionsTab extends Tab {

    //ArrayList allTransactions;
    ArrayList<String> transactionDescriptions;
    ArrayList<JLabel> labelArrayList;

    public ViewTransactionsTab(ExpenseTrackerGUI controller) {
        super(controller);

        displayTransactions();

    }

    public void displayTransactions() {
        removeAll();
      //  allTransactions = getController().getExpenseTracker().getTl().getTransactions();
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


        setVisible(true);

        revalidate();
        repaint();
    }


}



//        listModel = new DefaultListModel();
//        transactionDescriptions = getController().getExpenseTracker().getTransactionDescriptions();
//
//        if (!allTransactions.isEmpty()) {
//            for (String s : transactionDescriptions) {
//                listModel.addElement(s);
//            }
//        }
//
//        transactionJList = new JList<>(listModel);
////        transactionJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
////        transactionJList.setSelectedIndex(0);
////        transactionJList.addListSelectionListener((ListSelectionListener) this);
//        transactionJList.setVisibleRowCount(5);
//        JScrollPane listScrollPane = new JScrollPane(transactionJList);
//        add(listScrollPane, BorderLayout.CENTER);
//
//        add(transactionJList);
