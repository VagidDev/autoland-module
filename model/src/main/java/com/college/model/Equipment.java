/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model;

import com.college.model.keys.EquipmentId;

/**
 *
 * @author Vagid Zibliuc
 */
public class Equipment {
    private EquipmentId id;
    private String name;
    private String engineName;
    private EngineType engineType;
    private float engineVolume;
    private int horsepower;
    private SuspensionType suspensionType;
    private DriveType driveType;
    private GearboxType gearboxType;
    private int speedCount;
    private FuelType fuelType;
    private String interior;
    private String bodyKit;
    private int weight;
    private double price;
    private String imagePath;

    public Equipment() {
    }

    public Equipment(Automobile automobile, int id, String name, String engineName, EngineType engineType,
                     float engineVolume, int horsepower, SuspensionType suspensionType, DriveType driveType, GearboxType gearboxType,
                     int speedCount, FuelType fuelType, String interior, String bodyKit, int weight, double price, String imagePath) {
        this.id = new EquipmentId(automobile, id);
        this.name = name;
        this.engineName = engineName;
        this.engineType = engineType;
        this.engineVolume = engineVolume;
        this.horsepower = horsepower;
        this.suspensionType = suspensionType;
        this.driveType = driveType;
        this.gearboxType = gearboxType;
        this.speedCount = speedCount;
        this.fuelType = fuelType;
        this.interior = interior;
        this.bodyKit = bodyKit;
        this.weight = weight;
        this.price = price;
        this.imagePath = imagePath;
    }

    public EquipmentId getId() {
        return id;
    }

    public void setId(EquipmentId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEngineName() {
        return engineName;
    }

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public float getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(float engineVolume) {
        this.engineVolume = engineVolume;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public SuspensionType getSuspensionType() {
        return suspensionType;
    }

    public void setSuspensionType(SuspensionType suspensionType) {
        this.suspensionType = suspensionType;
    }

    public DriveType getDriveType() {
        return driveType;
    }

    public void setDriveType(DriveType driveType) {
        this.driveType = driveType;
    }

    public GearboxType getGearboxType() {
        return gearboxType;
    }

    public void setGearboxType(GearboxType gearboxType) {
        this.gearboxType = gearboxType;
    }

    public int getSpeedCount() {
        return speedCount;
    }

    public void setSpeedCount(int speedCount) {
        this.speedCount = speedCount;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getBodyKit() {
        return bodyKit;
    }

    public void setBodyKit(String bodyKit) {
        this.bodyKit = bodyKit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String[] getShortEquipment() {
        return new String[] {
                "Engine: " + this.engineName,
                "Engine type: " + this.engineType,
                "Engine volume: " + this.engineVolume,
                "Horsepower: " + this.horsepower,
                "Suspension: " + this.suspensionType,
                "Drive type: " + this.driveType,
                "Gearbox: " + this.gearboxType,
                "Speed count: " + this.speedCount,
                "Fuel: " + this.fuelType,
                "Interior: " + this.interior,
                "Body kit: " + this.bodyKit,
                "Weight: " + this.weight,
        };
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "automobile=" + id.getAutomobile() +
                ", id=" + id.getEquipmentId() +
                ", name='" + name + '\'' +
                ", engineName='" + engineName + '\'' +
                ", engineType='" + engineType + '\'' +
                ", engineVolume=" + engineVolume +
                ", horsepower=" + horsepower +
                ", suspensionType='" + suspensionType + '\'' +
                ", driveType='" + driveType + '\'' +
                ", gearboxType='" + gearboxType + '\'' +
                ", speedCount=" + speedCount +
                ", fuelType='" + fuelType + '\'' +
                ", interior='" + interior + '\'' +
                ", bodyKit='" + bodyKit + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
