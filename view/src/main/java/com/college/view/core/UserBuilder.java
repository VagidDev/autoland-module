package com.college.view.core;

import com.college.controller.validators.user.UserValidationResponse;
import com.college.model.entity.User;

import java.util.Date;

public abstract class UserBuilder {
    private static String login;
    private static String password;
    private static String role;

    private static String name;
    private static String surname;
    private static Date birthday;
    private static String email;
    private static String telephone;
    private static String address;
    private static String avatar;


    public static void setBasicInformation(String name, String surname, Date birthday, String email, String telephone, String address) {
        UserBuilder.name = name;
        UserBuilder.surname = surname;
        UserBuilder.birthday = birthday;
        UserBuilder.email = email;
        UserBuilder.telephone = telephone;
        UserBuilder.address = address;
        UserBuilder.avatar = "/images/default.jpg";
    }

    public static void setCredentials(String login, String password) {
        UserBuilder.login = login;
        UserBuilder.password = password;
        UserBuilder.role = "user";
    }

    public static User buildUser() {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        user.setName(name);
        user.setSurname(surname);
        user.setBirthday(birthday);
        user.setEmail(email);
        user.setTelephone(telephone);
        user.setAddress(address);
        user.setAvatar(avatar);
        return user;
    }

    public static void clearAllData() {
        login = null;
        password = null;
        role = null;
        name = null;
        surname = null;
        birthday = null;
        email = null;
        telephone = null;
        address = null;
        avatar = null;
    }

}
