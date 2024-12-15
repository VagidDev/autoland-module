/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model;

import java.util.Map;

/**
 * @author Vagid Zibliuc
 */
public class Automobile {
    private int id;
    private String mark;
    private String model;
    private BodyType bodyType;
    private int placeCount;
    private int prodYear;

    public Automobile() {
    }

    public Automobile(int id, String mark, String model, BodyType bodyType, int placeCount, int prodYear) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.bodyType = bodyType;
        this.placeCount = placeCount;
        this.prodYear = prodYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(int placeCount) {
        this.placeCount = placeCount;
    }

    public int getProdYear() {
        return prodYear;
    }

    public void setProdYear(int prodYear) {
        this.prodYear = prodYear;
    }

    @Override
    public String toString() {
        return "Automobile{" + "id=" + id + ", mark=" + mark + ", model=" + model + ", bodyType=" + bodyType + ", placeCount=" + placeCount + ", prodYear=" + prodYear + '}';
    }


}
