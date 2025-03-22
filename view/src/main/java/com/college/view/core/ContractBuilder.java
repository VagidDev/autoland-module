package com.college.view.core;

import com.college.model.entity.Automobile;
import com.college.model.entity.Dealer;
import com.college.model.entity.Equipment;
import com.college.model.entity.Warranty;
import com.college.model.entity.keys.EquipmentId;

public class ContractBuilder {
    private static Dealer dealer;
    private static Warranty warranty;
    private static Automobile automobile;
    private static Equipment equipment;

    public static void setDealerById(int id) {
        dealer = ControllerManager.getDealerController().getDealerById(id);
    }
    public static void setWarrantyById(int id) {
        warranty = ControllerManager.getWarrantyController().getWarranty(id);
    }
    public static void setAutomobileById(int id) {
        automobile = ControllerManager.getAutomobileController().getAutomobileById(id);
    }
    public static void setEquipmentById(int id) {
        EquipmentId equipmentId = new EquipmentId(getAutomobile().getId(), id);
        equipment = ControllerManager.getEquipmentController().getEquipment(equipmentId);
    }

    public static Dealer getDealer() {
        return dealer;
    }

    public static Warranty getWarranty() {
        return warranty;
    }

    public static Automobile getAutomobile() {
        return automobile;
    }

    public static Equipment getEquipment() {
        return equipment;
    }
}
