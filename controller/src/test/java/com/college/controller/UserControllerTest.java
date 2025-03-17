package com.college.controller;

import com.college.controller.validators.user.UserValidationResponse;
import com.college.model.entity.User;
import com.college.model.database.interfaces.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    void setUp() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        user = new User();
        user.setLogin("qwerty123");
        user.setPassword("qwerty123");
        user.setName("John");
        user.setSurname("Doe");
        user.setEmail("john@gmail.com");
        user.setBirthday(dateFormat.parse("23/05/2005"));
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
        user.setAddress("      ");
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

    @Test
    void editUser_shouldEdit_SimpleUser() {
        Mockito.doNothing().when(userDAO).update(Mockito.any(User.class));
        boolean result = userController.editUser(user);
        assertTrue(result);
    }

    @Test
    void editUser_shouldNotEdit_InvalidLogin() {
        Mockito.lenient().doNothing().when(userDAO).update(Mockito.any(User.class));
        user.setLogin("qwerty!!!+-123");
        boolean result = userController.editUser(user);
        assertFalse(result);
    }

    @Test
    void editUser_shouldNotEdit_ToShortPassword() {
        Mockito.lenient().doNothing().when(userDAO).update(Mockito.any(User.class));
        user.setPassword("qwerty");
        boolean result = userController.editUser(user);
        assertFalse(result);
    }

    @Test
    void editUser_shouldNotEdit_InvalidEmail() {
        Mockito.lenient().doNothing().when(userDAO).update(Mockito.any(User.class));
        user.setEmail("null_email");
        boolean result = userController.editUser(user);
        assertFalse(result);
    }

    @Test
    void editUser_shouldNotEdit_InvalidBirthday() {
        Mockito.lenient().doNothing().when(userDAO).update(Mockito.any(User.class));
        user.setBirthday(new Date());
        boolean result = userController.editUser(user);
        assertFalse(result);
    }
}