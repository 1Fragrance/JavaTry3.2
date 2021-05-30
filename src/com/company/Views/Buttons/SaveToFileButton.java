package com.company.Views.Buttons;

import com.company.Db.FlowerRepository;
import com.company.Files.FileManager;
import com.company.Views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveToFileButton implements ActionListener {
    private final MainView parentWindow;
    private final FlowerRepository flowerRepository;
    private final FileManager fileManager;

    public SaveToFileButton(MainView parentWindow, FlowerRepository flowerRepository, FileManager fileManager) {
        this.parentWindow = parentWindow;
        this.flowerRepository = flowerRepository;
        this.fileManager = fileManager;
    }

    public void actionPerformed(ActionEvent event) {
        fileManager.backupFlowersToFile();
    }
}