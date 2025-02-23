/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.models;

import jakarta.persistence.*;

/**
 *
 * @author Vagid Zibliuc
 */
@Entity
@Table(name = "au_warranties")
public class Warranty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "w_id")
    private int id;
    @Column(name = "w_name")
    private String name;
    @Column(name = "w_duration")
    private int duration;
    @Column(name = "w_price")
    private double price;

    public Warranty() {}

    public Warranty(int id, String name, int duration, double price) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.price = price;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Warranty{" + "id=" + id + ", name=" + name + ", duration=" + duration + ", price=" + price + '}';
    }
    
    
}
