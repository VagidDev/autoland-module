/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.entity;

import com.college.model.keys.EquipmentId;
import jakarta.persistence.*;

/**
 *
 * @author Vagid Zibliuc
 */
@Entity
@Table(name = "au_equipments")
public class Equipment {
    @EmbeddedId
    private EquipmentId id;
    @ManyToOne
    @MapsId("automobileId")
    @JoinColumn(name = "e_auto_id")
    private Automobile automobile;
    @Column(name = "e_name")
    private String name;
    @Column(name = "e_engine_name")
    private String engineName;
    @ManyToOne
    @JoinColumn(name = "e_engine_id")
    private EngineType engineType;
    @Column(name = "e_engine_volume")
    private float engineVolume;
    @Column(name = "e_horse_power")
    private int horsepower;
    @ManyToOne
    @JoinColumn(name = "e_susp_id")
    private SuspensionType suspensionType;
    @ManyToOne
    @JoinColumn(name = "e_drive_id")
    private DriveType driveType;
    @ManyToOne
    @JoinColumn(name = "e_gearbox_id")
    private GearboxType gearboxType;
    @Column(name = "e_speed_count")
    private int speedCount;
    @ManyToOne
    @JoinColumn(name = "e_fuel_id")
    private FuelType fuelType;
    @Column(name = "e_interior")
    private String interior;
    @Column(name = "e_body_kit")
    private String bodyKit;
    @Column(name = "e_weight")
    private int weight;
    @Column(name = "e_price")
    private double price;
    @Column(name = "e_image")
    private String imagePath;

    public Equipment() {
    }

    public Equipment(Automobile automobile, int id, String name, String engineName, EngineType engineType,
                     float engineVolume, int horsepower, SuspensionType suspensionType, DriveType driveType, GearboxType gearboxType,
                     int speedCount, FuelType fuelType, String interior, String bodyKit, int weight, double price, String imagePath) {
        this.id = new EquipmentId(automobile.getId(), id);
        this.automobile = automobile;
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

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
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
                "automobile=" + id.getAutomobileId() +
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
