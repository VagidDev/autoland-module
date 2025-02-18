package com.college.model;

import jakarta.persistence.*;

@Entity
@Table(name = "au_body_type")
public class BodyType extends SimpleTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bt_id")
    private int id;
    @Column(name = "bt_name")
    private String name;

    public BodyType() {
    }

    public BodyType(int id, String name) {
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
        return "BodyType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

