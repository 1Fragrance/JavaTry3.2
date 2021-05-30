package com.company.Views;

import com.company.Db.DbContext;
import com.company.Db.DbDataSource;
import com.company.Db.FlowerRepository;
import com.company.Files.FileManager;
import com.company.Models.Flower;
import com.company.Models.FlowersStatus;
import com.company.Views.Buttons.*;

import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainView extends Frame {

    private final DbDataSource dataSource;
    private final FlowerRepository flowerRepository;
    private final FileManager fileManager;

    private final Button addButton = new Button("Add new");
    private final Button editButton = new Button("Edit selected");
    private final Button deleteButton = new Button("Delete selected");
    private final Button saveToFileButton = new Button("Save list to file");
    private final Button filterButton = new Button("Filter");

    private final Choice dropDownItems = new Choice();

    private final Label sortLabel = new Label("Sort by:");
    private final CheckboxGroup checkboxGroup = new CheckboxGroup();
    private final Checkbox dateSortRadio = new Checkbox("Update date", checkboxGroup, true);
    private final Checkbox nameSortRadio = new Checkbox("Title", checkboxGroup, false);
    private final Checkbox costSortRadio = new Checkbox("Cost", checkboxGroup, false);

    private final List list = new List();

    public Checkbox getNameSortRadio() {
        return nameSortRadio;
    }

    public Checkbox getCostSortRadio() {
        return costSortRadio;
    }

    public List getList() {
        return list;
    }

    public Choice GetFilterDropDown() {
        return dropDownItems;
    }

    private void configureWindow() {
        setLayout(null);
        setSize(500,400);
        setBackground(Color.lightGray);
        addWindowListener(new WindowClose());
    }

    private void configureComponents(FlowerRepository flowerRepository, FileManager fileManager) {

        add(list);
        list.setBackground(Color.white);
        list.setBounds(130,60,350,280);

        add(sortLabel);
        sortLabel.setBounds(150,350,50,20);
        add(dateSortRadio);
        dateSortRadio.setBounds(200,350,100,20);
        dateSortRadio.addItemListener(new RadioButtonsListener(this));
        add(nameSortRadio);
        nameSortRadio.setBounds(300,350,50,20);
        nameSortRadio.addItemListener(new RadioButtonsListener(this));
        add(costSortRadio);
        costSortRadio.setBounds(360,350,100,20);
        costSortRadio.addItemListener(new RadioButtonsListener(this));

        add(addButton);
        addButton.setBounds(20,80,95,40);
        addButton.setForeground(Color.black);
        addButton.setBackground(Color.cyan);
        addButton.addActionListener(new AddButton(this, flowerRepository));

        add(editButton);
        editButton.setBounds(20,130,95,40);
        editButton.setForeground(Color.black);
        editButton.setBackground(Color.cyan);
        editButton.addActionListener(new EditButton(this, flowerRepository));

        add(deleteButton);
        deleteButton.setBounds(20,180,95,40);
        deleteButton.setForeground(Color.black);
        deleteButton.setBackground(Color.cyan);
        deleteButton.addActionListener(new DeleteButton(this, flowerRepository));

        add(saveToFileButton);
        saveToFileButton.setBounds(20,230,95,40);
        saveToFileButton.setForeground(Color.black);
        saveToFileButton.setBackground(Color.cyan);
        saveToFileButton.addActionListener(new SaveToFileButton(this, flowerRepository, fileManager));

        add(filterButton);
        filterButton.setBounds(20,280,95,20);
        filterButton.setForeground(Color.black);
        filterButton.setBackground(Color.orange);
        filterButton.addActionListener(new FilterButton(this, flowerRepository));

        add(dropDownItems);
        dropDownItems.setBounds(20,305,95,20);
        for(var status : FlowersStatus.values()) {
            dropDownItems.add(status.name());
        }
    }

    public MainView(String title) throws SQLException, ClassNotFoundException {
        super(title);
        dataSource = new DbDataSource(new DbContext());
        flowerRepository = dataSource.getFlowerRepository();
        fileManager = new FileManager(dataSource);

        configureWindow();
        configureComponents(flowerRepository, fileManager);

        refreshPage();
    }

    public void refreshPage() throws SQLException {
        list.removeAll();

        java.util.List<Flower> flowers;
        if(dateSortRadio.getState()) {
            flowers = flowerRepository.getListOrderedByDate();
        } else if(nameSortRadio.getState()) {
            flowers = flowerRepository.getListOrderedByName();
        } else if (costSortRadio.getState()) {
            flowers = flowerRepository.getListOrderedByCost();
        } else {
            flowers = new ArrayList<>();
        }

        for (var flower : flowers) {
            list.add(flower.toString());
        }
    }

    private class WindowClose extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            dispose();
        }
    }
}
