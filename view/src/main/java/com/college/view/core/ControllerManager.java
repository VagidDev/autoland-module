package com.college.view.core;

import com.college.controller.*;
import com.college.model.database.session.Session;
import com.college.model.database.session.SessionManager;
import com.college.model.entity.Warranty;

public class ControllerManager {
    private static AuthorizationController authorizationController;
    private static DealerController dealerController;
    private static EquipmentController equipmentController;
    private static AutomobileController automobileController;
    private static WarrantyController warrantyController;
    private static ContractController contractController;

    public static void loadAllControllers() {
        if (SessionManager.connect()) {
            Session session = SessionManager.getSession();
            authorizationController = new AuthorizationController(session.getUserRepository());
            dealerController = new DealerController(session.getDealerRepository());
            warrantyController = new WarrantyController(session.getWarrantyRepository());
            automobileController = new AutomobileController(session.getAutomobileRepository(), session.getEquipmentRepository());
            equipmentController = new EquipmentController(SessionManager.getSession().getEquipmentRepository());
            contractController = new ContractController(session.getContractRepository());
        }
    }

    public static DealerController getDealerController() {
        if (dealerController == null) {
            dealerController = new DealerController(SessionManager.getSession().getDealerRepository());
        }
        return dealerController;
    }

    public static AuthorizationController getAuthorizationController() {
        if (authorizationController == null) {
            authorizationController = new AuthorizationController(SessionManager.getSession().getUserRepository());
        }
        return authorizationController;
    }

    public static EquipmentController getEquipmentController() {
        if (equipmentController == null) {
            equipmentController = new EquipmentController(SessionManager.getSession().getEquipmentRepository());
        }
        return equipmentController;
    }

    public static AutomobileController getAutomobileController() {
        if (automobileController == null) {
            automobileController = new AutomobileController(SessionManager.getSession().getAutomobileRepository(), SessionManager.getSession().getEquipmentRepository());
        }
        return automobileController;
    }

    public static WarrantyController getWarrantyController() {
        if (warrantyController == null) {
            warrantyController = new WarrantyController(SessionManager.getSession().getWarrantyRepository());
        }
        return warrantyController;
    }

    public static ContractController getContractController() {
        if (contractController == null) {
            contractController = new ContractController(SessionManager.getSession().getContractRepository());
        }
        return contractController;
    }

}
