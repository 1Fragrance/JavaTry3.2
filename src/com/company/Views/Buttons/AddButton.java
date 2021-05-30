package com.company.Views.Buttons;

import com.company.Db.FlowerRepository;
import com.company.Views.AddFlowersView;
import com.company.Views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddButton implements ActionListener {

    private static String ViewTitle = "Add new flowers pack";
    private final MainView parentWindow;
    private final FlowerRepository flowerRepository;

    public AddButton(MainView parentWindow, FlowerRepository flowerRepository) {
        this.parentWindow = parentWindow;
        this.flowerRepository = flowerRepository;
    }

    public void actionPerformed(ActionEvent event) {
        new AddFlowersView(parentWindow, ViewTitle, true, flowerRepository);
        try {
            parentWindow.refreshPage();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
