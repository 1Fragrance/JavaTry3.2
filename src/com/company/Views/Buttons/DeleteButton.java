package com.company.Views.Buttons;

import com.company.Db.FlowerRepository;
import com.company.Helpers.ListItemParser;
import com.company.Views.DeleteFlowersView;
import com.company.Views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeleteButton implements ActionListener {

    private static String ViewTitle = "Confirm deletion";
    private final MainView parentWindow;
    private final FlowerRepository flowerRepository;

    public DeleteButton(MainView parentWindow, FlowerRepository flowerRepository) {
        this.parentWindow = parentWindow;
        this.flowerRepository = flowerRepository;
    }

    public void actionPerformed(ActionEvent event) {
        var selectedIndex = parentWindow.getList().getSelectedIndex();

        if(selectedIndex != -1) {
            var selectedItem = parentWindow.getList().getItem(selectedIndex);
            new DeleteFlowersView(parentWindow, ViewTitle, true, flowerRepository, ListItemParser.getId(selectedItem));
            try {
                parentWindow.refreshPage();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}