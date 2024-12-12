/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.controller;

import com.college.model.Automobile;
import com.college.model.database.interfaces.AutomobileDAO;
import com.college.model.database.session.SessionManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vagid Zibliuc
 */
public class AutomobileController {

    private final AutomobileDAO automobileRepository;

    public AutomobileController() {
        automobileRepository = SessionManager.getSession().getAutomobileRepository();
    }

    public List<String> getBodyTypes() {
        Map<Integer, String> map = automobileRepository.getBodyTypes();
        return map.entrySet()
                .stream()
                .map(e -> e.getValue())
                .toList();
    }

    public List<Automobile> getAllAutomobiles() {
        return automobileRepository.getAll();
    }

    public Automobile getAutoById(int id) {
        return automobileRepository.getById(id);
    }

    private String saveImage(String imagePath) {
        try {

            String targetPath = "car_images/";

            if (!Files.exists(Path.of(targetPath))) {
                Files.createDirectories(Path.of(targetPath));
            }

            imagePath = imagePath.replace("\\", "/");
            String[] splitedPath = imagePath.split("/");
            targetPath += splitedPath[splitedPath.length - 1];

            Files.copy(Path.of(imagePath), Path.of(targetPath), StandardCopyOption.REPLACE_EXISTING);
            return targetPath;
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public boolean saveAutomobile(String mark, String model, String bodyType, String placeCount, String prodYear, String imagePath) {
        int processedPlaceCount = Integer.parseInt(placeCount);
        int processedProdYear = Integer.parseInt(prodYear);

        String targetPath = "";
        if (imagePath != null && !imagePath.equals("")) {
            targetPath = saveImage(imagePath);
        }

        Automobile auto = new Automobile();

        auto.setMark(mark);
        auto.setModel(model);
        auto.setBodyType(bodyType);
        auto.setPlaceCount(processedPlaceCount);
        auto.setProdYear(processedProdYear);
        auto.setImagePath(targetPath);

        Automobile newAuto = automobileRepository.save(auto);
        return newAuto != null;
    }

    public boolean updateAutomobile(Automobile auto, String mark, String model, String bodyType, String placeCount, String prodYear, String imagePath) {
        int processedPlaceCount = Integer.parseInt(placeCount);
        int processedProdYear = Integer.parseInt(prodYear);

        String targetPath = "";
        if (imagePath.equals(auto.getImagePath()) || imagePath.equals("") || imagePath == null) {
            targetPath = auto.getImagePath();
        } else {
            try {
                Files.deleteIfExists(Path.of(auto.getImagePath()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            targetPath = saveImage(imagePath);
        }

        auto.setMark(mark);
        auto.setModel(model);
        auto.setBodyType(bodyType);
        auto.setPlaceCount(processedPlaceCount);
        auto.setProdYear(processedProdYear);
        auto.setImagePath(targetPath);

        return automobileRepository.update(auto);
    }

    public boolean deleteAutomobile(int id) {
        Automobile auto = automobileRepository.getById(id);
        try {
            boolean isDeleted = automobileRepository.deleteByID(id);
            if (isDeleted && !auto.getImagePath().isEmpty())
                Files.deleteIfExists(Path.of(auto.getImagePath()));
            return isDeleted;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
