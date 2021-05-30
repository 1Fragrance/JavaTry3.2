package com.company.Views;

import com.company.Db.FlowerRepository;
import com.company.Models.Flower;
import com.company.Models.FlowersStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;

public class EditFlowersView extends Dialog implements ActionListener {
    private final Label nameLabel = new Label("Name:");
    private final TextField nameTextField = new TextField(60);

    private final Label countLabel = new Label("Count:");
    private final TextField countTextField = new TextField(60);

    private final Label costLabel = new Label("Cost:");
    private final TextField costTextField = new TextField(60);

    private final Label statusLabel = new Label("Status:");
    private final Choice statusChoice = new Choice();


    private final Button submitButton = new Button("\nSubmit");

    private final FlowerRepository flowerRepository;

    private final int selectedFlowerId;


    private void configureWindow() {
        setLayout(new FlowLayout (FlowLayout.LEFT));
        setSize(400,300);
        addWindowListener(new WindowClose());
    }

    private void configureComponents() {
        add(nameLabel);
        add(nameTextField);
        add(countLabel);
        add(countTextField);
        add(costLabel);
        add(costTextField);

        var panel = new Panel(new GridBagLayout());
        add(panel);
        var gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,0,10,5);
        gbc.gridy = 0;
        gbc.gridx = 0;
        panel.add(statusLabel, gbc);
        gbc.gridx = 1;
        panel.add(statusChoice, gbc);

        for (var i : FlowersStatus.values()) {
            statusChoice.add(i.name());
        }
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(submitButton, gbc);
        submitButton.addActionListener(this);
        panel.setVisible(true);
    }


    public EditFlowersView(MainView parentWindow, String title, boolean b, FlowerRepository flowerRepository, int selectedFlowerId) throws SQLException {
        super(parentWindow, title, b);
        this.flowerRepository = flowerRepository;
        this.selectedFlowerId = selectedFlowerId;

        configureWindow();
        configureComponents();
        seedInputs(flowerRepository, selectedFlowerId);
        setVisible(true);
    }

    private void seedInputs(FlowerRepository flowerRepository, int selectedFlowerId) throws SQLException {
        var selectedFlower = flowerRepository.getById(selectedFlowerId);

        nameTextField.setText(selectedFlower.getTitle());
        countTextField.setText(Integer.toString(selectedFlower.getCount()));
        costTextField.setText(Double.toString(selectedFlower.getPrice()));
        statusChoice.select(selectedFlower.getStatus().ordinal());

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            var flowerPack = new Flower(selectedFlowerId,
                    nameTextField.getText(),
                    Integer.parseInt(countTextField.getText()),
                    Double.parseDouble(costTextField.getText()),
                    new Date(System.currentTimeMillis()),
                    statusChoice.getSelectedIndex());

            flowerRepository.update(flowerPack);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.dispose();
    }

    private class WindowClose extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            dispose();
        }
    }
}
