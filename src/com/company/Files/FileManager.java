package com.company.Files;

import com.company.Db.DbDataSource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class FileManager {

    private final DbDataSource dataSource;
    private static String flowersBackupFileName = "flowers-backup.txt";

    public FileManager(DbDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void backupFlowersToFile() {
        try (FileWriter fileWriter = new FileWriter(flowersBackupFileName)) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                var flowersRepository = dataSource.getFlowerRepository();
                var flowers = flowersRepository.getListOrderedByDate();

                for (var flowerPack : flowers) {
                    bufferedWriter.write(flowerPack.toString() + "\n");
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
