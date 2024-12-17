package com.college.model;

public class EngineType extends SimpleTable {
    private int id;
    private String name;

    public EngineType() {
    }

    public EngineType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EngineType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
