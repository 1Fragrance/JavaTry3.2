package com.company;

import com.company.Views.MainView;

import java.sql.SQLException;

public class Main {

    private static final String TITLE = "Flowers manager";

    static public void main(String args[]) throws SQLException, ClassNotFoundException {
        MainView MyFrame = new MainView(TITLE);
        MyFrame.setVisible(true);
    }
}
