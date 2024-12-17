package com.college.model;

public class GearboxType extends SimpleTable {
    private int id;
    private String name;

    public GearboxType() {}

    public GearboxType(int id, String name) {
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
        return "GearboxType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
