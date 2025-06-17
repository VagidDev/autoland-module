package com.college.controller;

import com.college.model.database.interfaces.*;
import com.college.model.entity.*;

import java.util.List;

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

    public List<BodyType> getBodyTypes() {
        return bodyTypeDAO.getAll();
    }

    public List<EngineType> getEngineTypes() {
        return engineTypeDAO.getAll();
    }

    public List<DriveType> getDriveTypes() {
        return driveTypeDAO.getAll();
    }

    public List<SuspensionType> getSuspensionTypes() {
        return suspensionTypeDAO.getAll();
    }

    public List<FuelType> getFuelTypes() {
        return fuelTypeDAO.getAll();
    }

    public List<GearboxType> getGearboxTypes() {
        return gearboxTypeDAO.getAll();
    }

    //maybe can make it without implement
}
