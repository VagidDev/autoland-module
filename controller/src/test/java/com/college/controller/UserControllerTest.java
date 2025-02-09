package com.college.controller;

import com.college.controller.validators.UserValidationResponse;
import com.college.model.User;
import com.college.model.database.interfaces.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserDAO userDAO;
    @InjectMocks
    private UserController userController;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setLogin("qwerty123");
        user.setPassword("qwerty123");
        user.setName("John");
        user.setSurname("Doe");
        user.setEmail("john@gmail.com");
        user.setBirthday(new Date());
        user.setAddress("some address");
        user.setTelephone("+37367292196");
    }

    @Test
    void validateUser_shouldBeValid() {
        UserValidationResponse response = userController.validateUser(user);
        assertEquals(UserValidationResponse.VALID, response);
    }

    @Test
    void validateUser_shouldNotBeValid_InvalidLogin() {
        user.setLogin("qwerty()grg");
        UserValidationResponse response = userController.validateUser(user);
        assertNotEquals(UserValidationResponse.VALID, response);
    }

    @Test
    void validateUser_shouldNotBeValid_InvalidPassword() {
        user.setPassword("******");
        UserValidationResponse response = userController.validateUser(user);
        assertNotEquals(UserValidationResponse.VALID, response);
    }

    @Test
    void validateUser_shouldNotBeValid_InvalidName() {
        user.setName(null);
        UserValidationResponse response = userController.validateUser(user);
        assertNotEquals(UserValidationResponse.VALID, response);
    }

    @Test
    void validateUser_shouldNotBeValid_InvalidSurname() {
        user.setSurname("          ");
        UserValidationResponse response = userController.validateUser(user);
        assertNotEquals(UserValidationResponse.VALID, response);
    }

    @Test
    void validateUser_shouldNotBeValid_InvalidBirthday() {
        user.setBirthday(new Date());
        UserValidationResponse response = userController.validateUser(user);
        assertNotEquals(UserValidationResponse.VALID, response);
    }

    @Test
    void validateUser_shouldNotBeValid_InvalidEmail() {
        user.setEmail("null_email");
        UserValidationResponse response = userController.validateUser(user);
        assertNotEquals(UserValidationResponse.VALID, response);
    }

    @Test
    void validateUser_shouldNotBeValid_InvalidTelephone() {
        user.setTelephone("695216484513");
        UserValidationResponse response = userController.validateUser(user);
        assertNotEquals(UserValidationResponse.VALID, response);
    }

    @Test
    void validateUser_shouldNotBeValid_InvalidAddress() {
        user.setAddress("some address");
        UserValidationResponse response = userController.validateUser(user);
        assertNotEquals(UserValidationResponse.VALID, response);
    }

    @Test
    void createUser_shouldCreate() {
        Mockito.when(userDAO.save(Mockito.any(User.class))).thenReturn(new User(25, "qwerty123", "qwerty123", "USER",
                "John", "Doe", new Date(), "john@gmail.com", "+37367292196", "some address"));

        User responseUser = userController.createUser(user);

        assertNotNull(responseUser);
    }

    @Test
    void createUser_shouldNotCreate_InvalidLogin() {
        Mockito.lenient().when(userDAO.save(Mockito.any(User.class))).thenReturn(new User(25, "qwerty123", "qwerty123", "USER",
                "John", "Doe", new Date(), "john@gmail.com", "+37367292196", "some address"));

        user.setLogin("qwer!!!!!+-ty123");

        User responseUser = userController.createUser(user);

        assertNull(responseUser);
    }
}