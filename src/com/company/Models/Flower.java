package com.company.Models;

import java.sql.Date;

public class Flower {
    private int Id;
    private String Title;
    private int Count;
    private double Price;
    private Date UpdateDate;
    private FlowersStatus Status;

    public Flower(int id, String title, int count, double price, Date updateDate, int status) {

        this.Id = id;
        this.Title = title;
        this.Count = count;
        this.Price = price;
        this.UpdateDate = updateDate;
        this.Status = FlowersStatus.values()[status];
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public Date getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(Date updateDate) {
        UpdateDate = updateDate;
    }

    public FlowersStatus getStatus() { return Status; }

    public void setStatus (FlowersStatus status) { Status = status; }

    public String toString() {
        return getId() + ", " + getTitle() + ", " + getCount() + ", " + getPrice() + ", " + getStatus() + ", " + getUpdateDate();
    }
}
