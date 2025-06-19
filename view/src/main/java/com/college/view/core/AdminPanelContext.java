package com.college.view.core;

import com.college.model.entity.keys.EquipmentId;

public class AdminPanelContext {
    private static int userID = -1;
    private static int dealerID = -1;
    private static int warrantyID = -1;
    private static int automobileID = -1;
    private static EquipmentId equipmentID = null;
    private static int contractID = -1;

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

    public static EquipmentId getEquipmentID() {
        return equipmentID;
    }

    public static void setEquipmentID(EquipmentId equipmentID) {
        AdminPanelContext.equipmentID = equipmentID;
    }

    public static int getContractID() {
        return contractID;
    }

    public static void setContractID(int contractID) {
        AdminPanelContext.contractID = contractID;
    }
}
