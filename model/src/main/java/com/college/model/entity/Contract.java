/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 *
 * @author Vagid Zibliuc
 */
@Entity
@Table(name = "au_contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_dealer_id")
    private Dealer dealer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_auto_id")
    private Automobile automobile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "c_auto_id", referencedColumnName = "automobileId"),
            @JoinColumn(name = "c_equip_id", referencedColumnName = "equipmentId")
    })
    private Equipment equipment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_warranty_id")
    private Warranty warranty;
    @Column(name = "c_data")
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
