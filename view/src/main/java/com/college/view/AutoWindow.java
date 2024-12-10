/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.college.view;

import com.college.Main;
import com.college.controller.AutomobileController;
import com.college.controller.manager.ControllerManager;
import com.college.model.Automobile;
import com.college.view.interfaces.Showable;
import com.college.view.utilites.ImageUploader;
import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Vagid Zibliuc
 */
public class AutoWindow extends javax.swing.JFrame implements Showable{
    private final Showable lastWindow;
    private AutomobileController automobileController;
    
    /**
     * Creates new form AutoWindow
     */
    public AutoWindow() {
        initComponents();
        this.lastWindow = null;
        customInitComponents();
    }
    
    /**
     * Creates new form AutoWindow
     * @param lastWindow
     */
    public AutoWindow(Showable lastWindow) {
        initComponents();
        this.lastWindow = lastWindow;
        customInitComponents();
    }
    
    private void customInitComponents() {
        this.setLocationRelativeTo(null);
        automobileController = ControllerManager.getAutomobileController();
        List<Automobile> automobiles = automobileController.getAllAutomobiles();
        for (Automobile auto : automobiles) {
            addDynamicAutoPane(auto);
        }
    }
    
    private void addDynamicAutoPane(Automobile automobile) {
        JPanel newAutoPanel = new JPanel();
        
        newAutoPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        newAutoPanel.setMinimumSize(new java.awt.Dimension(183, 230));
        newAutoPanel.setPreferredSize(new java.awt.Dimension(170, 220));
        newAutoPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                autoPanelItemMouseClicked(evt);
            }
        });
        newAutoPanel.setLayout(new java.awt.GridBagLayout());

        JLabel idLabel = new JLabel(String.valueOf(automobile.getId()));
        idLabel.setVisible(false);
        newAutoPanel.add(idLabel);
        
        JLabel autoImg = new JLabel();
        
        autoImg.setBackground(new java.awt.Color(153, 153, 153));
        autoImg.setPreferredSize(new Dimension(140, 150));
        autoImg.setMaximumSize(new Dimension(140, 150));
        autoImg.setSize(new Dimension(140, 150));
        autoImg.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        newAutoPanel.add(autoImg, gridBagConstraints);

        JLabel autoText = new JLabel();
        
        autoText.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        autoText.setText("Text");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        newAutoPanel.add(autoText, gridBagConstraints);

        JLabel autoPrice = new JLabel();
        
        autoPrice.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        autoPrice.setText("0 $");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 21, 0);
        newAutoPanel.add(autoPrice, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = getGridX();
        gridBagConstraints.gridy = getGridY();
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 20);
        
        autoText.setText(automobile.getMark() + " " + automobile.getModel());
        autoPrice.setText(automobile.getBodyType());
        ImageIcon image = ImageUploader.uploadImage(autoImg.getWidth(), autoImg.getHeight(), automobile.getImagePath(), ImageUploader.HEIGHT);
        
        autoImg.setIcon(image);
        
        autoViewPanel.add(newAutoPanel, gridBagConstraints);
    }
    
    private int getGridX() {
        Component[] components = autoViewPanel.getComponents();
        return components.length % 3;
    }
    
    private int getGridY() {
        Component[] components = autoViewPanel.getComponents();
        return (int) Math.floor(components.length / 3);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filterPanel = new javax.swing.JPanel();
        bodyLabel = new javax.swing.JLabel();
        bodyPanel = new javax.swing.JPanel();
        sedanCheckBox = new javax.swing.JCheckBox();
        coupeCheckBox = new javax.swing.JCheckBox();
        suvCheckBox = new javax.swing.JCheckBox();
        pickupCheckBox = new javax.swing.JCheckBox();
        vanCheckBox = new javax.swing.JCheckBox();
        pricePanel = new javax.swing.JPanel();
        priceLabel = new javax.swing.JLabel();
        priceSlider = new javax.swing.JSlider();
        priceRangeLabel = new javax.swing.JLabel();
        enginePanel = new javax.swing.JPanel();
        engineLabel = new javax.swing.JLabel();
        electricCheckBox = new javax.swing.JCheckBox();
        gasolineCheckBox = new javax.swing.JCheckBox();
        diselCheckBox = new javax.swing.JCheckBox();
        gearboxPanel = new javax.swing.JPanel();
        gearboxLabel = new javax.swing.JLabel();
        autoCheckBox = new javax.swing.JCheckBox();
        mechanCheckBox = new javax.swing.JCheckBox();
        seqCheckBox = new javax.swing.JCheckBox();
        mainPanel = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        descPrice = new javax.swing.JToggleButton();
        ascPrice = new javax.swing.JToggleButton();
        ratingButton = new javax.swing.JToggleButton();
        autoScrollPane = new javax.swing.JScrollPane();
        autoViewPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        hometsItem = new javax.swing.JMenu();
        automobilesItem = new javax.swing.JMenu();
        dealrsItem = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Automobiles");
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        filterPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));

        bodyLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        bodyLabel.setText("Body Type");

        sedanCheckBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        sedanCheckBox.setSelected(true);
        sedanCheckBox.setText("Sedan");
        sedanCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxMouseClicked(evt);
            }
        });

        coupeCheckBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        coupeCheckBox.setSelected(true);
        coupeCheckBox.setText("Coupe");
        coupeCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxMouseClicked(evt);
            }
        });

        suvCheckBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        suvCheckBox.setSelected(true);
        suvCheckBox.setText("SUV");
        suvCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxMouseClicked(evt);
            }
        });

        pickupCheckBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        pickupCheckBox.setSelected(true);
        pickupCheckBox.setText("Pick-Up");
        pickupCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxMouseClicked(evt);
            }
        });

        vanCheckBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        vanCheckBox.setSelected(true);
        vanCheckBox.setText("Mini-van");
        vanCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sedanCheckBox)
                    .addComponent(coupeCheckBox)
                    .addComponent(suvCheckBox)
                    .addComponent(pickupCheckBox)
                    .addComponent(vanCheckBox))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addComponent(sedanCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(coupeCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(suvCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pickupCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vanCheckBox))
        );

        priceLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        priceLabel.setText("Price");

        priceSlider.setMaximum(100000);
        priceSlider.setMinimum(1000);
        priceSlider.setValue(2000);
        priceSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                priceSliderStateChanged(evt);
            }
        });

        priceRangeLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        priceRangeLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        priceRangeLabel.setText("1000$-2000$");

        javax.swing.GroupLayout pricePanelLayout = new javax.swing.GroupLayout(pricePanel);
        pricePanel.setLayout(pricePanelLayout);
        pricePanelLayout.setHorizontalGroup(
            pricePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pricePanelLayout.createSequentialGroup()
                .addComponent(priceLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(priceRangeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(priceSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        pricePanelLayout.setVerticalGroup(
            pricePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pricePanelLayout.createSequentialGroup()
                .addGroup(pricePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceLabel)
                    .addComponent(priceRangeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        engineLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        engineLabel.setText("Engine");

        electricCheckBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        electricCheckBox.setSelected(true);
        electricCheckBox.setText("Electric");
        electricCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxMouseClicked(evt);
            }
        });

        gasolineCheckBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        gasolineCheckBox.setSelected(true);
        gasolineCheckBox.setText("Gasoline");
        gasolineCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxMouseClicked(evt);
            }
        });

        diselCheckBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        diselCheckBox.setSelected(true);
        diselCheckBox.setText("Disel");
        diselCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout enginePanelLayout = new javax.swing.GroupLayout(enginePanel);
        enginePanel.setLayout(enginePanelLayout);
        enginePanelLayout.setHorizontalGroup(
            enginePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enginePanelLayout.createSequentialGroup()
                .addGroup(enginePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(engineLabel)
                    .addComponent(electricCheckBox)
                    .addComponent(gasolineCheckBox)
                    .addComponent(diselCheckBox))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        enginePanelLayout.setVerticalGroup(
            enginePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enginePanelLayout.createSequentialGroup()
                .addComponent(engineLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(electricCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gasolineCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diselCheckBox)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        gearboxLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        gearboxLabel.setText("Gearbox");

        autoCheckBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        autoCheckBox.setSelected(true);
        autoCheckBox.setText("Automat");
        autoCheckBox.setActionCommand("Automatic");
        autoCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxMouseClicked(evt);
            }
        });

        mechanCheckBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        mechanCheckBox.setSelected(true);
        mechanCheckBox.setText("Mechanic");
        mechanCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxMouseClicked(evt);
            }
        });

        seqCheckBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        seqCheckBox.setSelected(true);
        seqCheckBox.setText("Sequental");
        seqCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkBoxMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout gearboxPanelLayout = new javax.swing.GroupLayout(gearboxPanel);
        gearboxPanel.setLayout(gearboxPanelLayout);
        gearboxPanelLayout.setHorizontalGroup(
            gearboxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gearboxPanelLayout.createSequentialGroup()
                .addGroup(gearboxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gearboxLabel)
                    .addComponent(autoCheckBox)
                    .addComponent(mechanCheckBox)
                    .addComponent(seqCheckBox))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        gearboxPanelLayout.setVerticalGroup(
            gearboxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gearboxPanelLayout.createSequentialGroup()
                .addComponent(gearboxLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autoCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mechanCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seqCheckBox))
        );

        javax.swing.GroupLayout filterPanelLayout = new javax.swing.GroupLayout(filterPanel);
        filterPanel.setLayout(filterPanelLayout);
        filterPanelLayout.setHorizontalGroup(
            filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bodyLabel)
                    .addComponent(pricePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(enginePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gearboxPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        filterPanelLayout.setVerticalGroup(
            filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(bodyLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bodyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pricePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enginePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gearboxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        searchField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        searchField.setText("Search");
        searchField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        descPrice.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        descPrice.setText("Price descending");

        ascPrice.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        ascPrice.setText("Price ascending");

        ratingButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        ratingButton.setText("Rating");

        autoScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        autoScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        autoViewPanel.setLayout(new java.awt.GridBagLayout());
        autoScrollPane.setViewportView(autoViewPanel);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ascPrice)
                        .addGap(18, 18, 18)
                        .addComponent(descPrice)
                        .addGap(18, 18, 18)
                        .addComponent(ratingButton))
                    .addComponent(autoScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ratingButton)
                    .addComponent(ascPrice)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(autoScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        menuBar.setBorder(new javax.swing.border.MatteBorder(null));
        menuBar.setBorderPainted(false);
        menuBar.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        menuBar.setPreferredSize(new java.awt.Dimension(314, 46));

        hometsItem.setText("Home");
        hometsItem.setAutoscrolls(true);
        hometsItem.setBorderPainted(false);
        hometsItem.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        hometsItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hometsItemMouseClicked(evt);
            }
        });
        menuBar.add(hometsItem);

        automobilesItem.setText("Automobiles");
        automobilesItem.setEnabled(false);
        automobilesItem.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        menuBar.add(automobilesItem);

        dealrsItem.setText("Dealers");
        dealrsItem.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        dealrsItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dealrsItemMouseClicked(evt);
            }
        });
        menuBar.add(dealrsItem);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(filterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (lastWindow != null)
            lastWindow.showWindow();
        else {
            Main.run();
        }
    }//GEN-LAST:event_formWindowClosing

    private void dealrsItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dealrsItemMouseClicked
        // TODO add your handling code here:
        new DealersWindow().showWindow();
        this.dispose();
    }//GEN-LAST:event_dealrsItemMouseClicked

    private void hometsItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hometsItemMouseClicked
        // TODO add your handling code here:
        new HomeWindow().showWindow();
        this.dispose();
    }//GEN-LAST:event_hometsItemMouseClicked

    /**
     * Changes range of prices
     * TODO: change it to two input fields
     * @param evt 
     */
    private void priceSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_priceSliderStateChanged
        // TODO add your handling code here:
        int value = priceSlider.getValue();
        priceRangeLabel.setText("1000$-" + value + '$');
    }//GEN-LAST:event_priceSliderStateChanged

    private void checkBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkBoxMouseClicked
        // TODO add your handling code here:
        checkState();
    }//GEN-LAST:event_checkBoxMouseClicked
    
    private void autoPanelItemMouseClicked(java.awt.event.MouseEvent evt) {
        Component component = evt.getComponent();
        if (component instanceof JPanel jpanel) {
            int id = Integer.parseInt(((JLabel) jpanel.getComponent(0)).getText());
            new BuyingWindow(this, id).showWindow();
        }
        this.dispose();
    }
    
    private void checkState() {
        HashMap<String, Boolean> checkedItems = new HashMap<>();
        Component[] components = filterPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                Component[] panelComponents = ((JPanel) component).getComponents();
                for (Component panelComponent : panelComponents) {
                    if (panelComponent instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) panelComponent;
                        checkedItems.put(checkBox.getActionCommand(), checkBox.isSelected());
                    }
                }
            }
        }
        
        StringBuilder builder = new StringBuilder("");
        for (Map.Entry<String, Boolean> checkbox : checkedItems.entrySet()) {
            builder.append(checkbox.getKey()).append(": ").append(checkbox.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(this, builder.toString());
    }
    
    @Override
    public void showWindow() {
        /* Display the form */
        this.setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton ascPrice;
    private javax.swing.JCheckBox autoCheckBox;
    private javax.swing.JScrollPane autoScrollPane;
    private javax.swing.JPanel autoViewPanel;
    private javax.swing.JMenu automobilesItem;
    private javax.swing.JLabel bodyLabel;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JCheckBox coupeCheckBox;
    private javax.swing.JMenu dealrsItem;
    private javax.swing.JToggleButton descPrice;
    private javax.swing.JCheckBox diselCheckBox;
    private javax.swing.JCheckBox electricCheckBox;
    private javax.swing.JLabel engineLabel;
    private javax.swing.JPanel enginePanel;
    private javax.swing.JPanel filterPanel;
    private javax.swing.JCheckBox gasolineCheckBox;
    private javax.swing.JLabel gearboxLabel;
    private javax.swing.JPanel gearboxPanel;
    private javax.swing.JMenu hometsItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JCheckBox mechanCheckBox;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JCheckBox pickupCheckBox;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JPanel pricePanel;
    private javax.swing.JLabel priceRangeLabel;
    private javax.swing.JSlider priceSlider;
    private javax.swing.JToggleButton ratingButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JCheckBox sedanCheckBox;
    private javax.swing.JCheckBox seqCheckBox;
    private javax.swing.JCheckBox suvCheckBox;
    private javax.swing.JCheckBox vanCheckBox;
    // End of variables declaration//GEN-END:variables

}
