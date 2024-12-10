/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.college.view;

import com.college.Main;
import com.college.controller.DealerController;
import com.college.controller.manager.ControllerManager;
import com.college.model.Contract;
import com.college.model.Dealer;
import com.college.view.interfaces.Showable;
import com.college.view.utilites.ImageUploader;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Vagid Zibliuc
 */
public class DealersWindow extends javax.swing.JFrame implements Showable {

    private final Showable lastWindow;
    private DealerController dealerController;
    private Contract contract;
    private int dealerId;

    /**
     * Creates new form DealersWindow
     */
    public DealersWindow() {
        initComponents();
        this.lastWindow = null;
        customInitComponents();
    }

    /**
     * Creates new form DealersWindow
     *
     * @param lastWindow
     * @param contract
     */
    public DealersWindow(Showable lastWindow, Contract contract) {
        initComponents();
        this.lastWindow = lastWindow;
        this.contract = contract;
        customInitComponents();
    }

    private void customInitComponents() {
        this.setLocationRelativeTo(null);
        dealerController = ControllerManager.getDealerController();
        uploadImages();
        loadDealers();
        if (lastWindow == null) {
            confirmButton.setVisible(false);
        }
    }

    private void uploadImages() {
        ImageIcon mainIcon = ImageUploader.uploadImage(
                backgroundImage.getWidth(),
                backgroundImage.getHeight(),
                "src/resources/images/dealer_img.jpg",
                ImageUploader.WIDTH);
        backgroundImage.setIcon(mainIcon);

        backgroundImage.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundImage.setVerticalAlignment(SwingConstants.CENTER);

    }

    private void loadDealers() {
        List<Dealer> dealers = dealerController.getAllDealers();
        for (Dealer dealer : dealers) {
            addDynamicDealerPanel(dealer);
        }
    }

    private void addDynamicDealerPanel(Dealer dealer) {
        // Создаём новую панель
        JPanel newPanel = new JPanel(new java.awt.GridBagLayout());
        newPanel.setBackground(new java.awt.Color(255, 255, 255));
        newPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        JLabel idLabel = new JLabel();
        idLabel.setText(String.valueOf(dealer.getId()));
        idLabel.setVisible(false);
        newPanel.add(idLabel);

        // Копируем слушатель событий
        newPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                elemOfListMouseClicked(evt); // Ваш обработчик событий
            }
        });

        // Создаём и добавляем компоненты с использованием GridBagConstraints
        GridBagConstraints gridBagConstraints;

        // Добавляем nameLabel2
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(7, 7, 0, 0);
        newPanel.add(nameLabel, gridBagConstraints);

        // Добавляем addressLabel2
        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(new java.awt.Font("Yu Gothic UI", 1, 14));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 7, 0, 0);
        newPanel.add(addressLabel, gridBagConstraints);

        // Добавляем discLabel2
        JLabel discLabel = new JLabel("Description");
        discLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 14));
        discLabel.setForeground(new java.awt.Color(153, 153, 153));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 7, 19, 133);
        newPanel.add(discLabel, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = getNextGridX(); // Определяем следующую позицию по X
        gridBagConstraints.gridy = 0; // Если нужно, можно добавить в новую строку
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(26, 13, 26, 13);

        nameLabel.setText(dealer.getName());
        addressLabel.setText(dealer.getAddress());
        discLabel.setText(dealer.getTelephone());

        listPanel.add(newPanel, gridBagConstraints);

        listPanel.revalidate();
        listPanel.repaint();
    }

    private int getNextGridX() {
        int componentCount = listPanel.getComponentCount();
        return componentCount; // Возвращает индекс для размещения новой панели
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
        backgroundImage = new javax.swing.JLabel();
        dealersPanel = new javax.swing.JPanel();
        dealersLabel = new javax.swing.JLabel();
        dealersSubLabel = new javax.swing.JLabel();
        confirmButton = new javax.swing.JToggleButton();
        dealerScrollPane = new javax.swing.JScrollPane();
        listPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        hometsItem = new javax.swing.JMenu();
        automobilesItem = new javax.swing.JMenu();
        dealrsItem = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dealers");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        mainPanel.setBackground(new java.awt.Color(204, 204, 204));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        welcomeLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 40)); // NOI18N
        welcomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLabel.setText("Dealers");
        mainPanel.add(welcomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 810, -1));

        welcomeSubLable.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 20)); // NOI18N
        welcomeSubLable.setForeground(new java.awt.Color(255, 255, 255));
        welcomeSubLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeSubLable.setText("Choose the best");
        mainPanel.add(welcomeSubLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 810, -1));
        mainPanel.add(backgroundImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 299));

        dealersLabel.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        dealersLabel.setText("List of dealers");

        dealersSubLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        dealersSubLabel.setText("Choose the dealer");

        confirmButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        confirmButton.setText("Next");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        dealerScrollPane.setBorder(null);
        dealerScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        dealerScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        listPanel.setLayout(new java.awt.GridBagLayout());
        dealerScrollPane.setViewportView(listPanel);

        javax.swing.GroupLayout dealersPanelLayout = new javax.swing.GroupLayout(dealersPanel);
        dealersPanel.setLayout(dealersPanelLayout);
        dealersPanelLayout.setHorizontalGroup(
            dealersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dealersPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(dealersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dealersSubLabel)
                    .addComponent(dealersLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dealersPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dealersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dealersPanelLayout.createSequentialGroup()
                        .addComponent(dealerScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dealersPanelLayout.createSequentialGroup()
                        .addComponent(confirmButton)
                        .addGap(362, 362, 362))))
        );
        dealersPanelLayout.setVerticalGroup(
            dealersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dealersPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(dealersLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dealersSubLabel)
                .addGap(18, 18, 18)
                .addComponent(dealerScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confirmButton)
                .addContainerGap(18, Short.MAX_VALUE))
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
        automobilesItem.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        automobilesItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                automobilesItemMouseClicked(evt);
            }
        });
        menuBar.add(automobilesItem);

        dealrsItem.setText("Dealers");
        dealrsItem.setEnabled(false);
        dealrsItem.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        menuBar.add(dealrsItem);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dealersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dealersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void setBlack(JPanel clickedPanel) {
        clickedPanel.setBackground(Color.black);
        for (Component cmp : clickedPanel.getComponents()) {
            if (cmp instanceof JLabel) {
                cmp.setForeground(Color.white);
            }
        }
    }

    private void setAllWhite(JPanel parent) {
        for (Component panel : parent.getComponents()) {
            if (panel instanceof JPanel) {
                panel.setBackground(Color.white);
                for (Component cmp : ((JPanel) panel).getComponents()) {
                    if (cmp instanceof JLabel) {
                        cmp.setForeground(Color.black);
                    }
                }
            }
        }
    }

    private void elemOfListMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        JPanel panel = (JPanel) evt.getComponent();
        int id = Integer.parseInt(((JLabel) panel.getComponent(0)).getText());
        dealerId = id;
        setAllWhite((JPanel) panel.getParent());
        setBlack(panel);
    }

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        // TODO add your handling code here:
        if (dealerId != 0) {
            Dealer dealer = dealerController.getDealerById(dealerId);
            contract.setDealer(dealer);
            new ConfirmationWindow(this, contract).showWindow();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Choose dealer by clicking on him!", "Choose error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

    @Override
    public void showWindow() {
        /* Create and display the form */
        this.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu automobilesItem;
    private javax.swing.JLabel backgroundImage;
    private javax.swing.JToggleButton confirmButton;
    private javax.swing.JScrollPane dealerScrollPane;
    private javax.swing.JLabel dealersLabel;
    private javax.swing.JPanel dealersPanel;
    private javax.swing.JLabel dealersSubLabel;
    private javax.swing.JMenu dealrsItem;
    private javax.swing.JMenu hometsItem;
    private javax.swing.JPanel listPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel welcomeLabel;
    private javax.swing.JLabel welcomeSubLable;
    // End of variables declaration//GEN-END:variables

}
