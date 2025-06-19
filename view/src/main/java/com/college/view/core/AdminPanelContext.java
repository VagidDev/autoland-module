package com.college.view.core;

public class AdminPanelContext {
    private static int userID = -1;
    private static int dealerID = -1;
    private static int warrantyID = -1;
    private static int automobileID = -1;

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        AdminPanelContext.userID = userID;
    }

    public static int getDealerID() {
        return dealerID;
    }

    public static void setDealerID(int dealerID) {
        AdminPanelContext.dealerID = dealerID;
    }

    public static int getWarrantyID() {
        return warrantyID;
    }

    public static void setWarrantyID(int warrantyID) {
        AdminPanelContext.warrantyID = warrantyID;
    }

    public static int getAutomobileID() {
        return automobileID;
    }

    public static void setAutomobileID(int automobileID) {
        AdminPanelContext.automobileID = automobileID;
    }
}
