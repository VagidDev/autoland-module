package com.college.controller;

import com.college.model.User;
import com.college.model.database.interfaces.UserDAO;
import com.college.model.database.session.SessionManager;
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


    @Test
    void shouldReturnUser() {
        Mockito.when(userDAO.save(Mockito.any(User.class))).thenReturn(new User(25, "qwerty123", "qwerty123", "USER",
                "John", "Doe", new Date(), "john@gmail.com", "+37367292196", "some address"));

        User user = new User();
        user.setLogin("qwerty123");
        user.setPassword("qwerty123");
        user.setName("John");
        user.setSurname("Doe");
        user.setEmail("john@gmail.com");
        user.setBirthday(new Date());
        user.setAddress("some address");
        user.setTelephone("+37367292196");

        User responseUser = userController.createUser(user);

        assertNotNull(responseUser);
    }

    @Test
    void shouldNotReturnUserBecauseOfInvalidLogin() {
        Mockito.lenient().when(userDAO.save(Mockito.any(User.class))).thenReturn(new User(25, "qwerty123", "qwerty123", "USER",
                "John", "Doe", new Date(), "john@gmail.com", "+37367292196", "some address"));

        User user = new User();
        user.setLogin("qwer!!!!!+-ty123");
        user.setPassword("qwerty123");
        user.setName("John");
        user.setSurname("Doe");
        user.setEmail("john@gmail.com");
        user.setBirthday(new Date());
        user.setAddress("some address");
        user.setTelephone("+37367292196");

        User responseUser = userController.createUser(user);

        assertNull(responseUser);
    }
}