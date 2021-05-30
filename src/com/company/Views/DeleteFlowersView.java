package com.company.Views;

import com.company.Db.FlowerRepository;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class DeleteFlowersView extends Dialog {

    private final Button yesButton = new Button("Yes");
    private final Button noButton = new Button("No");
    private final Label message = new Label("Are you sure?");
    private final FlowerRepository flowerRepository;
    private final int selectedFlowerId;

    private void configureWindow() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setSize(200,100);
        addWindowListener(new WindowClose());
    }

    private void configureComponents() {
        add(message);
        add(yesButton);
        add(noButton);
        yesButton.addActionListener(new ButtonYes());
        noButton.addActionListener(new ButtonNo());
    }

    public DeleteFlowersView(MainView parentView, String title, boolean b, FlowerRepository flowerRepository, int selectedFlowerId) {
        super(parentView, title, b);
        this.flowerRepository = flowerRepository;
        this.selectedFlowerId = selectedFlowerId;

        configureWindow();
        configureComponents();

        setVisible(true);
    }

    class ButtonNo implements ActionListener   {
        public void actionPerformed(ActionEvent ae) {
            dispose();
        }
    }

    class ButtonYes implements ActionListener   {
        public void actionPerformed(ActionEvent ae) {
            try {
                flowerRepository.delete(selectedFlowerId);
            }  catch (SQLException e) {
                e.printStackTrace();
            }
            dispose();
        }
    }

    private class WindowClose extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            dispose();
        }
    }
}
