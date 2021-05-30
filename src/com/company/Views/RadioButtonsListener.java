package com.company.Views;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

public class RadioButtonsListener implements ItemListener {

    private final MainView parentWindow;
    public RadioButtonsListener(MainView parentWindow) {
        this.parentWindow = parentWindow;
    }

    public void itemStateChanged(ItemEvent e) {
        try {
            parentWindow.refreshPage();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}