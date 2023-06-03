package com.citawarisan.dao;

import com.citawarisan.util.DBConnection;

import java.sql.Connection;
import java.util.List;

public abstract class ModelDao<T> {
    protected final Connection connection;

    public ModelDao() {
        this.connection = DBConnection.getConnection();
    }

    abstract public boolean create(T object);
    abstract public List<T> read();
    abstract public boolean update(T object);
    abstract public boolean delete(T object);
}
