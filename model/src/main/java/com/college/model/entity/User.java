/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.models;

import jakarta.persistence.*;

import java.util.Date;

/**
 * @author Vagid Zibliuc
 */
@Entity
@Table(name = "au_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private int id;
    @Column(name = "u_login")
    private String login;
    @Column(name = "u_password")
    private String password;
    @Column(name = "u_role")
    private String role;
    @Column(name = "u_name")
    private String name;
    @Column(name = "u_surname")
    private String surname;
    @Column(name = "u_birthday")
    private Date birthday;
    @Column(name = "u_email")
    private String email;
    @Column(name = "u_telephone")
    private String telephone;
    @Column(name = "u_address")
    private String address;
    @Column(name = "u_avatar")
    private String avatar;

    public User() {
    }

    public User(int id, String login, String password, String role, String name, String surname, Date birthday, String email, String telephone, String address) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
