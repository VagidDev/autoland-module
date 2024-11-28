/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.view.utilites;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Vagid Zibliuc
 */
public abstract class ImageUploader {
    public static final int WIDTH = 0;
    public static final int HEIGHT = 1;
    
    public static ImageIcon uploadImage(int width, int height, String path, int target) {
        ImageIcon imageIcon = new ImageIcon(path);
        
        int originalHeight = imageIcon.getIconHeight();
        int originalWidth = imageIcon.getIconWidth();
        
        int targetWidth = 0;
        int targetHeight = 0;
        
        if (target == 0) {
            targetWidth = width;
            targetHeight = (targetWidth * originalHeight) / originalWidth;
        } else {
            targetHeight = height;
            targetWidth = (targetHeight * originalWidth) / originalHeight;
        }
        
        Image scaledImage = imageIcon.getImage().getScaledInstance(
                targetWidth, 
                targetHeight, 
                Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
