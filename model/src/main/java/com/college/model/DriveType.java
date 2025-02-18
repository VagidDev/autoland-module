package com.college.model;

import jakarta.persistence.*;

@Entity
@Table(name = "au_drive_type")
public class DriveType extends SimpleTable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dt_id")
    private int id;
    @Column(name = "dt_name")
    private String name;

    public DriveType() {}

    public DriveType(int id, String name) {
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
        return "DriveType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
