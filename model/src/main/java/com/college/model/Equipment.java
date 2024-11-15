/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model;

/**
 *
 * @author Vagid Zibliuc
 */
public class Equipment {
    private Automobile automobile;
    private int id;
    private String name;
    private String engineName;
    private String engineType;
    private float engineVolume;
    private int horsepower;
    private String suspensiveType;
    private String driveType;
    private String gearboxType;
    private int speedCount;
    private String fuelType;
    private String interior;
    private String bodyKit;
    private int weigth;
    private double price;

    public Equipment() {
    }

    public Equipment(Automobile automobile, int id, String name, String engineName, String engineType, 
            float engineVolume, int horsepower, String suspensiveType, String driveType, String gearboxType, 
            int speedCount, String fuelType, String interior, String bodyKit, int weigth, double price) {
        this.automobile = automobile;
        this.id = id;
        this.name = name;
        this.engineName = engineName;
        this.engineType = engineType;
        this.engineVolume = engineVolume;
        this.horsepower = horsepower;
        this.suspensiveType = suspensiveType;
        this.driveType = driveType;
        this.gearboxType = gearboxType;
        this.speedCount = speedCount;
        this.fuelType = fuelType;
        this.interior = interior;
        this.bodyKit = bodyKit;
        this.weigth = weigth;
        this.price = price;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
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

    public String getSuspensiveType() {
        return suspensiveType;
    }

    public void setSuspensiveType(String suspensiveType) {
        this.suspensiveType = suspensiveType;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public void setGearboxType(String gearboxType) {
        this.gearboxType = gearboxType;
    }

    public int getSpeedCount() {
        return speedCount;
    }

    public void setSpeedCount(int speedCount) {
        this.speedCount = speedCount;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
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

    public int getWeigth() {
        return weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Equipment{" + "automobile=" + automobile + ", id=" + id + ", name=" + name + ", engineName=" + engineName + ", engineType=" + engineType + ", engineVolume=" + engineVolume + ", horsepower=" + horsepower + ", suspensiveType=" + suspensiveType + ", driveType=" + driveType + ", gearboxType=" + gearboxType + ", speedCount=" + speedCount + ", fuelType=" + fuelType + ", interior=" + interior + ", bodyKit=" + bodyKit + ", weigth=" + weigth + ", price=" + price + '}';
    }
    
    
}
