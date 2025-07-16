package com.college.controller;

import com.college.controller.core.AppConfig;
import com.college.controller.core.ImageSaver;
import com.college.controller.validators.user.*;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.User;
import com.college.model.database.interfaces.UserDAO;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final UserDAO userDAO;
    private final List<UserValidator> validatorList;
    private final List<UserValidator> userInfoValidatorList;
    private final List<UserValidator> userCredentialsValidatorList;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.validatorList = new ArrayList<>();
        this.userInfoValidatorList = new ArrayList<>();
        this.userCredentialsValidatorList = new ArrayList<>();

        /*user validation criteria*/
        validatorList.add(new UserLoginSyntaxValidator());
        validatorList.add(new UserPasswordValidator());
        validatorList.add(new UserNameValidator());
        validatorList.add(new UserSurnameValidator());
        validatorList.add(new UserBirthdateValidator());
        validatorList.add(new UserEmailValidator());
        validatorList.add(new UserPhoneValidator());
        validatorList.add(new UserAddressValidator());

        /*user info validator list*/
        userInfoValidatorList.add(new UserNameValidator());
        userInfoValidatorList.add(new UserSurnameValidator());
        userInfoValidatorList.add(new UserBirthdateValidator());
        userInfoValidatorList.add(new UserEmailValidator());
        userInfoValidatorList.add(new UserPhoneValidator());
        userInfoValidatorList.add(new UserAddressValidator());

        /*user credential validator list*/
        userCredentialsValidatorList.add(new UserLoginSyntaxValidator());
        userCredentialsValidatorList.add(new UserPasswordValidator());
    }

    public List<User> getUsers() {
        return userDAO.getAll();
    }

    public User getUser(int id) {
        return userDAO.getById(id);
    }

    public UserValidationResponse validateUser(User user) {
        for (UserValidator validator : validatorList) {
            UserValidationResponse response = validator.validate(user);
            if (response != UserValidationResponse.VALID) {
                return response;
            }
        }
        return UserValidationResponse.VALID;
    }

    public UserValidationResponse validateUserInfo(User user) {
        for (UserValidator validator : userInfoValidatorList) {
            UserValidationResponse response = validator.validate(user);
            if (response != UserValidationResponse.VALID) {
                return response;
            }
        }
        return UserValidationResponse.VALID;
    }

    public UserValidationResponse validateUserCredential(User user) {
        for (UserValidator validator : userCredentialsValidatorList) {
            UserValidationResponse response = validator.validate(user);
            if (response != UserValidationResponse.VALID) {
                return response;
            }
        }
        return UserValidationResponse.VALID;
    }

    public User createUser(User user) {
        if (validateUser(user) != UserValidationResponse.VALID) {
            return null;
        }
        //hardcode - will be fixed
        if (user.getRole().isEmpty()) {
            user.setRole("user");
        }

        setUserImage(user);

        return userDAO.save(user);
    }

    public boolean editUser(User user) {
        if (validateUser(user) != UserValidationResponse.VALID) {
            return false;
        }
        //hardcode - will be fixed
        if (user.getRole().isEmpty()) {
            user.setRole("user");
        }

        setUserImage(user);

        userDAO.update(user);
        return true;
    }

    public void deleteUser(User user) throws CascadeDependencyException {
        if (user == null) {
            return;
        }

        if (userDAO.getById(user.getId()) != null) {
            userDAO.delete(user);
        }
    }

    private void setUserImage(User user) {
        if (user == null)
            return;

        if (user.getId() != 0 && (user.getAvatar() == null || user.getAvatar().isEmpty())) {
            return;
        }

        if (user.getId() == 0 && (user.getAvatar() == null || user.getAvatar().isEmpty())) {
            user.setAvatar("file:" + AppConfig.get("default.avatar"));
        }

        if (!user.getAvatar().isEmpty()) {
            String originalImagePath = user.getAvatar();
            int formatDotPosition = originalImagePath.lastIndexOf(".");
            String format = originalImagePath.substring(formatDotPosition);
            String newImageName = user.getLogin();
            String newImagePath = AppConfig.get("photo.avatars");

            String newURL = ImageSaver.copyImageToDirectory(originalImagePath, newImagePath, newImageName, format);
            user.setAvatar(newURL);
        }



    }

}
