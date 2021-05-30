package com.company.Views.Buttons;

import com.company.Db.FlowerRepository;
import com.company.Helpers.ListItemParser;
import com.company.Views.DeleteFlowersView;
import com.company.Views.EditFlowersView;
import com.company.Views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class EditButton implements ActionListener {

    private final MainView parentWindow;
    private final FlowerRepository flowerRepository;
    private static String ViewTitle = "Edit flowers pack";

    public EditButton (MainView parentWindow, FlowerRepository flowerRepository) {
        this.parentWindow = parentWindow;
        this.flowerRepository = flowerRepository;
    }

    public void actionPerformed(ActionEvent event) {
        var selectedIndex = parentWindow.getList().getSelectedIndex();
        if(selectedIndex != -1) {
            var selectedItem = parentWindow.getList().getItem(selectedIndex);
            try {
                new EditFlowersView(parentWindow, ViewTitle, true, flowerRepository, ListItemParser.getId(selectedItem));
                parentWindow.refreshPage();
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}