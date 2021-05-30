package com.company.Views;

import com.company.Db.FlowerRepository;
import com.company.Models.Flower;
import com.company.Models.FlowersStatus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Date;

public class AddFlowersView extends Dialog implements ActionListener {

    private final Label nameLabel = new Label("Name:");
    private final TextField nameTextField = new TextField(60);

    private final Label countLabel = new Label("Count:");
    private final TextField countTextField = new TextField(60);

    private final Label costLabel = new Label("Cost:");
    private final TextField costTextField = new TextField(60);

    private final Button submitButton = new Button("Submit");

    private final FlowerRepository flowerRepository;

    private void configureWindow() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setSize(400,250);
        addWindowListener(new WindowClose());
    }

    private void configureComponents() {
        add(nameLabel);
        add(nameTextField);
        add(countLabel);
        add(countTextField);
        add(costLabel);
        add(costTextField);

        add(submitButton);
        submitButton.addActionListener(this);
    }

    public AddFlowersView(MainView parentView, String title, boolean b, FlowerRepository repository) {
        super(parentView, title, b);
        flowerRepository = repository;

        configureWindow();
        configureComponents();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {

            var flowerPack = new Flower(0,
                    nameTextField.getText(),
                    Integer.parseInt(countTextField.getText()),
                    Double.parseDouble(costTextField.getText()),
                    new Date(System.currentTimeMillis()),
                    FlowersStatus.Active.ordinal());

            flowerRepository.save(flowerPack);
        }  catch (SQLException e) {
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
