package com.college.controller;

import com.college.model.database.interfaces.*;
import com.college.model.entity.SimpleTable;

public class SimpleTableController {
    private BodyTypeDAO bodyTypeDAO;
    private DriveTypeDAO driveTypeDAO;
    private EngineTypeDAO engineTypeDAO;
    private FuelTypeDAO fuelTypeDAO;
    private GearboxTypeDAO gearboxTypeDAO;
    private SuspensionTypeDAO suspensionTypeDAO;

    public SimpleTableController(BodyTypeDAO bodyTypeDAO, DriveTypeDAO driveTypeDAO, EngineTypeDAO engineTypeDAO, FuelTypeDAO fuelTypeDAO, GearboxTypeDAO gearboxTypeDAO, SuspensionTypeDAO suspensionTypeDAO) {
        this.bodyTypeDAO = bodyTypeDAO;
        this.driveTypeDAO = driveTypeDAO;
        this.engineTypeDAO = engineTypeDAO;
        this.fuelTypeDAO = fuelTypeDAO;
        this.gearboxTypeDAO = gearboxTypeDAO;
        this.suspensionTypeDAO = suspensionTypeDAO;
    }

    //maybe can make it without implement
}
