package com.company.Db;

import com.company.Models.Flower;
import com.company.Models.FlowersStatus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlowerRepository {
    private final DbContext dbContext;

    public FlowerRepository(DbContext dbContext) throws ClassNotFoundException {
        this.dbContext = dbContext;
    }

    public List<Flower> getListOrderedByName() throws SQLException {
        var sql = "SELECT * FROM Flower ORDER BY Title";
        return getList(sql);
    }

    public List<Flower> getListOrderedByDate() throws SQLException {
        var sql = "SELECT * FROM Flower ORDER BY UpdateDate DESC";
        return getList(sql);
    }

    public List<Flower> getListOrderedByCost() throws SQLException {
        var sql = "SELECT * FROM Flower ORDER BY Price";
        return getList(sql);
    }

    public Flower getById(int id) throws SQLException {
        var sql = "SELECT * FROM Flower WHERE Id = " + id;
        return getList(sql).get(0);
    }

    public List<Flower> getFilteredList(FlowersStatus status, String orderByField) throws SQLException {
        var sql = "SELECT * FROM Flower WHERE status = " + status.ordinal() + " ORDER BY " + orderByField;
        return getList(sql);
    }

    private List<Flower> getList(String sql) throws SQLException {
        var connection = dbContext.getConnection();
        var statement = connection.prepareStatement(sql);
        var result = statement.executeQuery();

        var list = new ArrayList<Flower>();
        while (result.next()) {
            var id = result.getInt("Id");
            var title = result.getString("Title");
            var count = result.getInt("Count");
            var price = result.getDouble("Price");
            var updateDate = result.getDate("UpdateDate");
            var status = result.getInt("Status");
            var flowersPack = new Flower(id, title, count, price, updateDate, status);

            list.add(flowersPack);
        }
        return list;
    }

    public void save(Flower flowerEntity) throws SQLException {
        var sql = "INSERT INTO Flower (Title, Count, Price, UpdateDate, Status) VALUES (?,?,?,?,?)";

        var connection = dbContext.getConnection();
        var statement = connection.prepareStatement(sql);

        statement.setString(1, flowerEntity.getTitle());
        statement.setInt(2, flowerEntity.getCount());
        statement.setDouble(3, flowerEntity.getPrice());
        statement.setDate(4, flowerEntity.getUpdateDate());
        statement.setInt(5, flowerEntity.getStatus().ordinal());

        statement.executeUpdate();
    }

    public void update(Flower flowerEntity) throws SQLException {
        var sql = "UPDATE Flower SET Title =?, Count=?, Price=?, UpdateDate=?, Status=? WHERE Id=? ";

        var connection = dbContext.getConnection();
        var statement = connection.prepareStatement(sql);

        statement.setString(1, flowerEntity.getTitle());
        statement.setInt(2, flowerEntity.getCount());
        statement.setDouble(3, flowerEntity.getPrice());
        statement.setDate(4, flowerEntity.getUpdateDate());
        statement.setInt(5, flowerEntity.getStatus().ordinal());
        statement.setInt(6, flowerEntity.getId());

        statement.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        var sql = "DELETE FROM Flower WHERE Id=?";

        var connection = dbContext.getConnection();
        var statement = connection.prepareStatement(sql);

        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
