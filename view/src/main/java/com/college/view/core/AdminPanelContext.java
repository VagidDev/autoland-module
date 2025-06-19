package com.college.view.core;

public class AdminPanelContext {
    private static int userID = -1;
    private static int dealerID = -1;

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
}
