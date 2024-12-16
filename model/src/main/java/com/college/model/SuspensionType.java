package com.college.model;

public class SuspensionType {
    private int id;
    private String name;

    public SuspensionType() {}

    public SuspensionType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SuspensionType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
