package com.company.Views.Buttons;

import com.company.Db.FlowerRepository;
import com.company.Models.Flower;
import com.company.Models.FlowersStatus;
import com.company.Views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilterButton implements ActionListener {

    private final MainView parentWindow;
    private final FlowerRepository flowerRepository;

    public FilterButton (MainView parentWindow, FlowerRepository flowerRepository) {
        this.parentWindow = parentWindow;
        this.flowerRepository = flowerRepository;
    }

    public void actionPerformed(ActionEvent event) {
        var filterDropDown = parentWindow.GetFilterDropDown();
        var selectedItem = filterDropDown.getSelectedIndex();
        String orderField;
        if(parentWindow.getNameSortRadio().getState()) {
            orderField = "Title";
        } else if(parentWindow.getCostSortRadio().getState()) {
            orderField = "Price";
        } else {
            orderField = "UpdateDate";
        }

        List<Flower> flowers = null;
        try {
            flowers = flowerRepository.getFilteredList(FlowersStatus.values()[selectedItem], orderField);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        var list = parentWindow.getList();
        list.removeAll();
        for (var flower : flowers) {
            list.add(flower.toString());
        }
    }
}