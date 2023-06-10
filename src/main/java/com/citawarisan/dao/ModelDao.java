package com.citawarisan.dao;

import com.citawarisan.model.User;
import com.citawarisan.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class ModelDao<T> {

    protected final Connection connection;

    public ModelDao() {
        this.connection = DBConnection.getConnection();
    }

    abstract protected List<T> parseModels(ResultSet rs) throws SQLException;

    abstract protected PreparedStatement parseQuery(String query, T object) throws SQLException;

    abstract public boolean create(T object);

    abstract public List<T> read();

    abstract public List<T> read(User user);

    abstract public boolean update(T object);

    abstract public boolean delete(T object);
}
