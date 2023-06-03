package com.model;

public class Generic {
    final private String tableName;
    final private String[] columnName;

    public Generic(String tableName, String[] columnName) {
        this.tableName = tableName;
        this.columnName = columnName;
    }

    public String getTableName() {
        return tableName;
    }

    public String[] getColumnName() {
        return columnName;
    }
}
