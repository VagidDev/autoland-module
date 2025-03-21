package com.college.view.core;

import com.college.controller.AuthorizationController;
import com.college.controller.DealerController;
import com.college.model.database.session.SessionManager;

public class ControllerManager {
    private static AuthorizationController authorizationController;
    private static DealerController dealerController;

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
}
