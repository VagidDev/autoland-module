/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.controller.manager;

import com.college.controller.AutomobileController;
import com.college.controller.DealerController;
import com.college.controller.UserController;

/**
 *
 * @author Vagid Zibliuc
 */
public abstract class ControllerManager {
    private static final UserController userController = new UserController();
    private static final AutomobileController automobileController = new AutomobileController();
    private static final DealerController dealerController = new DealerController();

    public static AutomobileController getAutomobileController() {
        return automobileController;
    }

    public static DealerController getDealerController() {
        return dealerController;
    }
    
    public static UserController getUserController() {
        return userController;
    }
    
}
