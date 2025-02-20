/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model;

/**
 *
 * @author Vagid Zibliuc
 */
public class Dealer {
    private int id;
    private String name;
    private String address;
    private String telephone;
    private String fax;
    
    public Dealer(){}

    public Dealer(int id, String name, String address, String telephone, String fax) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.fax = fax;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String toString() {
        return "Dealer{" + "id=" + id + ", name=" + name + ", address=" + address + ", telephone=" + telephone + ", fax=" + fax + '}';
    }
    
    
}
