package com.company.Db;

public class DbDataSource {

    private DbContext dbContext;
    private FlowerRepository flowerRepository;

    public DbDataSource(DbContext context) throws ClassNotFoundException {
        dbContext = context;
        flowerRepository = new FlowerRepository(dbContext);
    }

    public FlowerRepository getFlowerRepository() {
        return flowerRepository;
    }
}
