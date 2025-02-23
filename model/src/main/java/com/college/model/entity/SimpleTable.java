package com.college.model.models;

public abstract class SimpleTable {

    public String getSimpleTableName() {
        return this.getClass().getSimpleName();
    }
    public abstract int getId();
    public abstract String getName();
    public abstract void setId(int id);
    public abstract void setName(String name);
}
