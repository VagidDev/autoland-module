package com.college.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "au_engine_type")
public class EngineType extends SimpleTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "et_id")
    private int id;
    @Column(name = "et_name")
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
