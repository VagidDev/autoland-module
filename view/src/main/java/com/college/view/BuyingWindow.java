/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.college.view;

import com.college.controller.AutomobileController;
import com.college.model.Automobile;
import com.college.model.Equipment;
import com.college.view.interfaces.Showable;
import com.college.view.utilites.ImageUploader;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.LineBorder;

/**
 *
 * @author Vagid Zibliuc
 */
public class BuyingWindow extends javax.swing.JFrame implements Showable {

    private final Showable lastWindow;
    private AutomobileController automobileController;
    private int autoId;
    private int equipmentId;
    /**
     * Creates new form BuyingForm
     */
    public BuyingWindow() {
        initComponents();
        this.lastWindow = null;
        this.setLocationRelativeTo(null);
    }

    public BuyingWindow(Showable lastWindow, int autoId) {
        initComponents();
        automobileController = new AutomobileController();
        this.lastWindow = lastWindow;
        this.autoId = autoId;
        this.setLocationRelativeTo(null);
        loadImage();
    }
    
    private void loadImage() {
        Automobile auto = automobileController.getAutoById(autoId);
       
        ImageIcon icon = ImageUploader.uploadImage(imageLabel.getWidth(), imageLabel.getHeight(), auto.getImagePath(), ImageUploader.WIDTH);
        imageLabel.setIcon(icon);
        
        List<Equipment> equipments = automobileController.getEquipmentsByAutomobile(auto);
        for (Equipment e : equipments) {
            addPlate(e);        
        }
        
    }
    
    private void addPlate(Equipment equipment) {
        JPanel newPanel = new JPanel();
        
        JLabel idLabel = new JLabel();
        idLabel.setText(String.valueOf(equipment.getId()));
        newPanel.add(idLabel);
        
        newPanel.setBackground(new java.awt.Color(255, 255, 255));
        newPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        newPanel.setMinimumSize(new java.awt.Dimension(251, 220));
        newPanel.setPreferredSize(new java.awt.Dimension(215, 253));
        newPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabel newCompName = new JLabel(); 
        
        newCompName.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        newCompName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newCompName.setText("Title");
        newPanel.add(newCompName, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 7, 230, -1));

        JLabel newPrice = new JLabel();
        
        newPrice.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        newPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newPrice.setText("1500$");
        newPanel.add(newPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 38, 230, -1));

        JScrollPane newListScrollPane = new JScrollPane();
        
        newListScrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        JList newList = new JList();
        
        newList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        newList.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        newList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = equipment.getShortEquipment();
            @Override
            public int getSize() { return strings.length; }
            @Override
            public String getElementAt(int i) { return strings[i]; }
        });
        newList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        newListScrollPane.setViewportView(newList);

        newPanel.add(newListScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 83, 183, 130));

        JButton newChooseButton = new JButton();
        
        newChooseButton.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        newChooseButton.setText("Select");
        newChooseButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            chooseButtonClicked(evt);
        });
        newPanel.add(newChooseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, -1));
        
        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = getGridX();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 8, 15);
        
        newCompName.setText(equipment.getName());
        newPrice.setText(equipment.getPrice() + " $");
        
        compPanel.add(newPanel, gridBagConstraints);
    }
    
    private int getGridX() {
       return compPanel.getComponents().length;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        welcomeLabel = new javax.swing.JLabel();
        welcomeSubLable = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        complLabel = new javax.swing.JLabel();
        confirmButton = new javax.swing.JToggleButton();
        equipScrollPane = new javax.swing.JScrollPane();
        compPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        hometsItem = new javax.swing.JMenu();
        automobilesItem = new javax.swing.JMenu();
        dealrsItem = new javax.swing.JMenu();
        contactsItem = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        mainPanel.setBackground(new java.awt.Color(204, 204, 204));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        welcomeLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 36)); // NOI18N
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLabel.setText("Automobile");
        welcomeLabel.setToolTipText("");
        mainPanel.add(welcomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, -1));

        welcomeSubLable.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        welcomeSubLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeSubLable.setText("Mark");
        mainPanel.add(welcomeSubLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 154, -1, -1));
        mainPanel.add(imageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 299));

        complLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 36)); // NOI18N
        complLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        complLabel.setText("Equipment");
        complLabel.setToolTipText("");

        confirmButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        confirmButton.setText("Next");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        equipScrollPane.setBorder(null);

        compPanel.setLayout(new java.awt.GridBagLayout());
        equipScrollPane.setViewportView(compPanel);

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
        automobilesItem.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        automobilesItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                automobilesItemMouseClicked(evt);
            }
        });
        menuBar.add(automobilesItem);

        dealrsItem.setText("Dealers");
        dealrsItem.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        dealrsItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dealrsItemMouseClicked(evt);
            }
        });
        menuBar.add(dealrsItem);

        contactsItem.setText("Contacts");
        contactsItem.setAutoscrolls(true);
        contactsItem.setBorderPainted(false);
        contactsItem.setEnabled(false);
        contactsItem.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        menuBar.add(contactsItem);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
            .addComponent(equipScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(complLabel)
                        .addGap(308, 308, 308))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(confirmButton)
                        .addGap(360, 360, 360))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(complLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(equipScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(confirmButton)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (lastWindow != null)
            lastWindow.showWindow();
        else
            System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void hometsItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hometsItemMouseClicked
        // TODO add your handling code here:
        new HomeWindow().showWindow();
        this.dispose();
    }//GEN-LAST:event_hometsItemMouseClicked

    private void automobilesItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_automobilesItemMouseClicked
        // TODO add your handling code here:
        new AutoWindow().showWindow();
        this.dispose();
    }//GEN-LAST:event_automobilesItemMouseClicked

    private void dealrsItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dealrsItemMouseClicked
        // TODO add your handling code here:
        new DealersWindow().showWindow();
        this.dispose();
    }//GEN-LAST:event_dealrsItemMouseClicked

    private void setBlack(JPanel comp) {
        if (comp instanceof JPanel) {
            comp.setBackground(Color.BLACK);
            for (Component subComp : ((JPanel) comp).getComponents()) {
                if (subComp instanceof JLabel) {
                    subComp.setForeground(Color.WHITE);
                } else if (subComp instanceof JScrollPane) {
                    JScrollPane scrollPane = ((JScrollPane) subComp);
                    scrollPane.setBorder(new LineBorder(Color.BLACK, 1));
                    if (scrollPane.getComponent(0) instanceof JViewport) {
                        JViewport view = (JViewport) scrollPane.getComponent(0);
                        if (view.getComponent(0) instanceof JList) {
                            JList list = (JList) view.getComponent(0);
                            list.setBackground(Color.BLACK);
                            list.setForeground(Color.WHITE);
                            list.setBorder(new LineBorder(Color.BLACK, 1));
                        }
                    }
                } 
            }
        }
    }

    private void setAllWhite(JPanel parent) {
        for (Component comp : parent.getComponents()) {
            if (comp instanceof JPanel) {
                comp.setBackground(Color.WHITE);
                for (Component subComp : ((JPanel) comp).getComponents()) {
                    if (subComp instanceof JLabel) {
                        subComp.setForeground(Color.BLACK);
                    } else if (subComp instanceof JScrollPane) {
                        JScrollPane scrollPane = ((JScrollPane) subComp);
                        scrollPane.setBorder(new LineBorder(Color.WHITE, 1));
                        if (scrollPane.getComponent(0) instanceof JViewport) {
                            JViewport view = (JViewport) scrollPane.getComponent(0);
                            if (view.getComponent(0) instanceof JList) {
                                JList list = (JList) view.getComponent(0);
                                list.setBackground(Color.WHITE);
                                list.setForeground(Color.BLACK);
                                list.setBorder(new LineBorder(Color.WHITE, 1));
                            }
                        }
                    } 
                }
            }
        }
    }

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        // TODO add your handling code here:
        new DealersWindow().showWindow();
        this.dispose();
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void chooseButtonClicked(java.awt.event.ActionEvent evt) {
        Component comp = ((JButton) evt.getSource()).getParent();
        if (comp instanceof JPanel panel) {
            int id = Integer.parseInt(((JLabel) panel.getComponent(0)).getText());
            this.equipmentId = id;
            setAllWhite((JPanel) panel.getParent());
            setBlack(panel);
        }
        
    }
    
    @Override
    public void showWindow() {
        /* Create and display the form */
        this.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu automobilesItem;
    private javax.swing.JPanel compPanel;
    private javax.swing.JLabel complLabel;
    private javax.swing.JToggleButton confirmButton;
    private javax.swing.JMenu contactsItem;
    private javax.swing.JMenu dealrsItem;
    private javax.swing.JScrollPane equipScrollPane;
    private javax.swing.JMenu hometsItem;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel welcomeLabel;
    private javax.swing.JLabel welcomeSubLable;
    // End of variables declaration//GEN-END:variables

}
