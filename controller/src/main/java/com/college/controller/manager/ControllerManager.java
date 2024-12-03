/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.controller.manager;

import com.college.controller.*;

/**
 *
 * @author Vagid Zibliuc
 */
public abstract class ControllerManager {
    private static final UserController userController = new UserController();
    private static final AutomobileController automobileController = new AutomobileController();
    private static final DealerController dealerController = new DealerController();
    private static final ContractController contractController = new ContractController();

    public static ContractController getContractController() {
        return contractController;
    }

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
