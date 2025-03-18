package com.college.view.core;

import com.college.controller.DealerController;
import com.college.model.database.session.SessionManager;

public class ControllerManager {
    private static DealerController dealerController;
    public static DealerController getDealerController() {
        if (dealerController == null) {
            dealerController = new DealerController(SessionManager.getSession().getDealerRepository());
        }
        return dealerController;
    }
}
