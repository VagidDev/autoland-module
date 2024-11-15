/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model;

import java.util.Date;

/**
 *
 * @author Vagid Zibliuc
 */
public class Contract {
    private int id;
    private User user;
    private Dealer dealer;
    private Automobile automobile;
    private Equipment equipment;
    private Warranty warranty;
    private Date conclusionDate;

    public Contract() {
    }

    public Contract(int id, User user, Dealer dealer, Automobile automobile, Equipment equipment, Warranty warranty, Date conclusionDate) {
        this.id = id;
        this.user = user;
        this.dealer = dealer;
        this.automobile = automobile;
        this.equipment = equipment;
        this.warranty = warranty;
        this.conclusionDate = conclusionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public Warranty getWarranty() {
        return warranty;
    }

    public void setWarranty(Warranty warranty) {
        this.warranty = warranty;
    }

    public Date getConclusionDate() {
        return conclusionDate;
    }

    public void setConclusionDate(Date conclusionDate) {
        this.conclusionDate = conclusionDate;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "Contract{" + "id=" + id + ", user=" + user + ", dealer=" + dealer + ", equipment=" + equipment + ", warranty=" + warranty + ", conclusionDate=" + conclusionDate + '}';
    }
    
    
}
