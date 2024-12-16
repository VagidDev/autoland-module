package com.college.model.database.session;

import com.college.model.database.implementations.*;
import com.college.model.database.interfaces.*;

/**
 * @author Vagid Zibliuc
 */

public class Session {

    private final UserDAO userRepository;
    private final DealerDAO dealerRepository;
    private final WarrantyDAO warrantyRepository;
    private final BodyTypeDAO bodyTypeRepository;
    private final AutomobileDAO automobileRepository;
    private final DriveTypeDAO driveTypeRepository;
    private final FuelTypeDAO fuelTypeRepository;
    private final EngineTypeDAO engineTypeRepository;
    private final GearboxTypeDAO gearboxTypeRepository;
    private final SuspensionTypeDAO suspensionTypeRepository;
    private final EquipmentDAO equipmentRepository;
    private final ContractDAO contractRepository;


    public Session() {
        this.userRepository = new UserImpl();
        this.dealerRepository = new DealerImpl();
        this.warrantyRepository = new WarrantyImpl();
        this.bodyTypeRepository = new BodyTypeImpl();
        this.automobileRepository = new AutomobileImpl(bodyTypeRepository);
        this.driveTypeRepository = new DriveTypeImpl();
        this.fuelTypeRepository = new FuelTypeImpl();
        this.engineTypeRepository = new EngineTypeImpl();
        this.gearboxTypeRepository = new GearboxTypeImpl();
        this.suspensionTypeRepository = new SuspensionTypeImpl();
        this.equipmentRepository = new EquipmentImpl(automobileRepository, engineTypeRepository, suspensionTypeRepository, driveTypeRepository, gearboxTypeRepository, fuelTypeRepository);
        this.contractRepository = new ContractImpl(userRepository, dealerRepository, warrantyRepository, automobileRepository, equipmentRepository);
    }

    public UserDAO getUserRepository() {
        return userRepository;
    }

    public DealerDAO getDealerRepository() {
        return dealerRepository;
    }

    public WarrantyDAO getWarrantyRepository() {
        return warrantyRepository;
    }

    public AutomobileDAO getAutomobileRepository() {
        return automobileRepository;
    }

    public EquipmentDAO getEquipmentRepository() {
        return equipmentRepository;
    }

    public ContractDAO getContractRepository() {
        return contractRepository;
    }

    public BodyTypeDAO getBodyTypeRepository() {
        return bodyTypeRepository;
    }

    public DriveTypeDAO getDriveTypeRepository() {
        return driveTypeRepository;
    }

    public FuelTypeDAO getFuelTypeRepository() {
        return fuelTypeRepository;
    }

    public EngineTypeDAO getEngineTypeRepository() {
        return engineTypeRepository;
    }

    public GearboxTypeDAO getGearboxTypeRepository() {
        return gearboxTypeRepository;
    }

    public SuspensionTypeDAO getSuspensionTypeRepository() {
        return suspensionTypeRepository;
    }
}
