package com.college.model;

public class DriveType {
    private int id;
    private String name;

    public DriveType() {}

    public DriveType(int id, String name) {
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
        return "DriveType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
