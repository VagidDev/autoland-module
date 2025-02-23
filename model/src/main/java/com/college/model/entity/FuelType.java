package com.college.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "au_fuel_type")
public class FuelType extends SimpleTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ft_id")
    private int id;
    @Column(name = "ft_name")
    private String name;

    public FuelType() {
    }

    public FuelType(int id, String name) {
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
        return "FuelType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
