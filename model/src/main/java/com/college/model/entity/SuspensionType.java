package com.college.model.models;

import jakarta.persistence.*;

@Entity
@Table(name = "au_suspension_type")
public class SuspensionType extends SimpleTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "st_id")
    private int id;
    @Column(name = "st_name")
    private String name;

    public SuspensionType() {}

    public SuspensionType(int id, String name) {
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
        return "SuspensionType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
