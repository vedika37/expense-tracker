package ui.tabs;

import ui.ExpenseTrackerGUI;

import javax.swing.*;
import java.awt.*;

/*
 * An abstract class for all the different panels in the GUI
 */

// source: CPSC 210 long-form-problem-starters -> SmartHome

public abstract class Tab extends JPanel {

    private ExpenseTrackerGUI controller;

    // REQUIRES: ExpenseTrackerUI controller that holds this tab
    public Tab(ExpenseTrackerGUI controller) {
        this.controller = controller;
    }

    //EFFECTS: returns the ExpenseTracker controller for this tab
    public ExpenseTrackerGUI getController() {
        return controller;
    }

}
