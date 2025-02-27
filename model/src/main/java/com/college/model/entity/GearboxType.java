package com.college.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "au_gearbox_type")
public class GearboxType extends SimpleTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gt_id")
    private int id;
    @Column(name = "gt_name")
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
