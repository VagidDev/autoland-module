package com.college.controller.core;

import com.college.model.entity.Equipment;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageSaver {
    public static String copyImageToDirectory(String originalImagePath, String toPath, String imageName, String format) {
        if (originalImagePath == null || toPath == null ||
                originalImagePath.isEmpty() || toPath.isEmpty()) {
            return "";
        }

        if (!toPath.endsWith("/")) {
            toPath += "/";
        }

        String outputPath = toPath + imageName + format;
        File outputFile = new File(outputPath);
        try {
            if (Files.notExists(Paths.get(toPath))) {
                Files.createDirectories(Paths.get(toPath));
            }

            Files.copy(Paths.get(new URI(originalImagePath)), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return outputFile.toURI().toString();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
