/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.college.view.admin;

import com.college.Main;
import com.college.controller.*;
import com.college.controller.manager.ControllerManager;
import com.college.model.*;
import com.college.view.admin.add.AutomobileDataForm;
import com.college.view.admin.add.DealerDataForm;
import com.college.view.admin.add.EquipmentDataForm;
import com.college.view.admin.add.UserDataForm;
import com.college.view.admin.add.WarrantyDataForm;
import com.college.view.interfaces.Showable;
import java.awt.Component;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vagid Zibliuc
 */
public class AdminWindow extends javax.swing.JFrame implements Showable {

    private final UserController userController;
    private final DealerController dealerController;
    private final WarrantyController warrantyController;
    private final AutomobileController automobileController;
    private final EquipmentController equipmentController;
    private final ContractController contractController;
    private Map<String, List<String>> classFields;
    private String currentClass = "Automobile";

    /**
     * Creates new form UserWindow
     */
    public AdminWindow() {
        initComponents();
        this.setLocationRelativeTo(null);
        userController = ControllerManager.getUserController();
        dealerController = ControllerManager.getDealerController();
        warrantyController = ControllerManager.getWarrantyContlroller();
        automobileController = ControllerManager.getAutomobileController();
        equipmentController = ControllerManager.getEquipmentController();
        contractController = ControllerManager.getContractController();
        initializeFields();
        loadDataByClass();
    }

    private void initializeFields() {
        classFields = new HashMap();
        classFields.put("User", Arrays.asList("Id", "Login", "Password", "Name", "Surname", "Birthday", "Email", "Telephone", "Address"));
        classFields.put("Dealer", Arrays.asList("Id", "Name", "Address", "Telephone", "Fax"));
        classFields.put("Warranty", Arrays.asList("Id", "Name", "Duration", "Price"));
        classFields.put("Automobile", Arrays.asList("Id", "Mark", "Model", "Body Type", "Place Count", "Production Year", "Image Path"));
        classFields.put("Equipment", Arrays.asList("Auto", "Id", "Name", "Engine Name", "Engine Type", "Engine Volume",
                "Horse power", "Suspension Type", "Drive Type", "Gearbox Type", "Speeds count", "Fuel Type", "Interior", "Body Kit", "Weight", "Price"));
        classFields.put("Contract", Arrays.asList("Id", "User", "Dealer", "Automobile", "Equipment", "Warranty", "Registration Date"));
    }

    private void loadDataByClass() {
        DefaultTableModel tableModel = (DefaultTableModel) userTable.getModel();

        Vector<String> columns = new Vector<>(classFields.get(currentClass));
        Vector<Vector<String>> rows = new Vector<>();

        Map<String, Runnable> dataLoaders = Map.of(
                "User", () -> loadUsers(rows),
                "Dealer", () -> loadDealers(rows),
                "Automobile", () -> loadAutomobiles(rows),
                "Equipment", () -> loadEquipments(rows),
                "Warranty", () -> loadWarranties(rows),
                "Contract", () -> loadContracts(rows)
        );

        if (dataLoaders.containsKey(currentClass)) {
            dataLoaders.get(currentClass).run();
        }

        tableModel.setDataVector(rows, columns);
        userTable.setModel(tableModel);
        userTable.revalidate();
        userTable.repaint();
    }

    private void loadUsers(Vector<Vector<String>> rows) {
        List<User> users = userController.getUsers();
        users.forEach(e -> {
            Vector<String> row = new Vector<>(Arrays.asList(
                    String.valueOf(e.getId()),
                    e.getLogin(),
                    "*******",
                    e.getName(),
                    e.getSurname(),
                    e.getBirthday().toString(),
                    e.getEmail(),
                    e.getTelephone(),
                    e.getAddress()
            ));
            rows.add(row);
        });
    }

    private void loadDealers(Vector<Vector<String>> rows) {
        List<Dealer> dealers = dealerController.getAllDealers();
        dealers.forEach(e -> {
            Vector<String> row = new Vector<>(Arrays.asList(
                    String.valueOf(e.getId()),
                    e.getName(),
                    e.getAddress(),
                    e.getTelephone(),
                    e.getFax()
            ));
            rows.add(row);
        });
    }

    private void loadAutomobiles(Vector<Vector<String>> rows) {
        List<Automobile> automobiles = automobileController.getAllAutomobiles();
        automobiles.forEach(e -> {
            Vector<String> row = new Vector<>(Arrays.asList(
                    String.valueOf(e.getId()),
                    e.getMark(),
                    e.getModel(),
                    e.getBodyType(),
                    String.valueOf(e.getPlaceCount()),
                    String.valueOf(e.getProdYear()),
                    e.getImagePath()
            ));

            rows.add(row);
        });
    }

    private void loadEquipments(Vector<Vector<String>> rows) {
        List<Equipment> equipments = equipmentController.getAllEquipments();
        equipments.forEach(e -> {
            Vector<String> row = new Vector<>(Arrays.asList(
                    e.getAutomobile().getMark() + " " + e.getAutomobile().getModel(),
                    String.valueOf(e.getAutomobile().getId()) + ":" + String.valueOf(e.getId()),
                    e.getName(),
                    e.getEngineName(),
                    e.getEngineType(),
                    String.valueOf(e.getEngineVolume()),
                    String.valueOf(e.getHorsepower()),
                    e.getSuspensiveType(),
                    e.getDriveType(),
                    e.getGearboxType(),
                    String.valueOf(e.getSpeedCount()),
                    e.getFuelType(),
                    e.getInterior(),
                    e.getBodyKit(),
                    String.valueOf(e.getWeigth()),
                    String.valueOf(e.getPrice())
            ));

            rows.add(row);
        });
    }

    private void loadWarranties(Vector<Vector<String>> rows) {
        List<Warranty> warranties = warrantyController.getAllWarranties();
        warranties.forEach(e -> {
            Vector<String> row = new Vector<>(Arrays.asList(
                    String.valueOf(e.getId()),
                    e.getName(),
                    String.valueOf(e.getDuration()),
                    String.valueOf(e.getPrice())
            ));

            rows.add(row);
        });
    }

    private void loadContracts(Vector<Vector<String>> rows) {
        List<Contract> contracts = contractController.getAllContracts();
        contracts.forEach(e -> {
            Vector<String> row = new Vector<>(Arrays.asList(
                    String.valueOf(e.getId()),
                    e.getUser().getName() + " " + e.getUser().getSurname(),
                    e.getDealer().getName(),
                    e.getAutomobile().getMark() + " " + e.getAutomobile().getModel(),
                    e.getEquipment().getName(),
                    e.getWarranty().getName(),
                    e.getConclusionDate().toString()
            ));

            rows.add(row);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableScrollPanel = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        menuPanel = new javax.swing.JPanel();
        refreshButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        addEquipButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        userItem = new javax.swing.JMenu();
        dealrsItem = new javax.swing.JMenu();
        warrantyItem = new javax.swing.JMenu();
        automobilesItem = new javax.swing.JMenu();
        equipmentItem = new javax.swing.JMenu();
        contractItem = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Admin");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        userTable.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Login", "Password", "Name", "Surname", "Birthdate", "Email", "Telephone", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        userTable.setFillsViewportHeight(true);
        userTable.setRowHeight(35);
        userTable.getTableHeader().setResizingAllowed(false);
        userTable.getTableHeader().setReorderingAllowed(false);
        tableScrollPanel.setViewportView(userTable);
        if (userTable.getColumnModel().getColumnCount() > 0) {
            userTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            userTable.getColumnModel().getColumn(1).setPreferredWidth(30);
            userTable.getColumnModel().getColumn(2).setPreferredWidth(20);
            userTable.getColumnModel().getColumn(3).setPreferredWidth(30);
            userTable.getColumnModel().getColumn(4).setPreferredWidth(30);
            userTable.getColumnModel().getColumn(5).setPreferredWidth(20);
        }

        refreshButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        updateButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        addButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        addEquipButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        addEquipButton.setText("Add equipment");
        addEquipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEquipButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(addButton)
                .addGap(120, 120, 120)
                .addComponent(updateButton)
                .addGap(120, 120, 120)
                .addComponent(deleteButton)
                .addGap(120, 120, 120)
                .addComponent(refreshButton)
                .addGap(108, 108, 108)
                .addComponent(addEquipButton)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshButton)
                    .addComponent(deleteButton)
                    .addComponent(updateButton)
                    .addComponent(addButton)
                    .addComponent(addEquipButton))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        menuBar.setBorder(new javax.swing.border.MatteBorder(null));
        menuBar.setBorderPainted(false);
        menuBar.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        menuBar.setPreferredSize(new java.awt.Dimension(314, 46));

        userItem.setText("Users");
        userItem.setActionCommand("User");
        userItem.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        userItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemMouseClicked(evt);
            }
        });
        menuBar.add(userItem);

        dealrsItem.setText("Dealers");
        dealrsItem.setActionCommand("Dealer");
        dealrsItem.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        dealrsItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemMouseClicked(evt);
            }
        });
        menuBar.add(dealrsItem);

        warrantyItem.setText("Warranties");
        warrantyItem.setActionCommand("Warranty");
        warrantyItem.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        warrantyItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemMouseClicked(evt);
            }
        });
        menuBar.add(warrantyItem);

        automobilesItem.setText("Automobiles");
        automobilesItem.setActionCommand("Automobile");
        automobilesItem.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        automobilesItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemMouseClicked(evt);
            }
        });
        menuBar.add(automobilesItem);

        equipmentItem.setText("Equipments");
        equipmentItem.setActionCommand("Equipment");
        equipmentItem.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        equipmentItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemMouseClicked(evt);
            }
        });
        menuBar.add(equipmentItem);

        contractItem.setText("Contracts");
        contractItem.setActionCommand("Contract");
        contractItem.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        contractItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemMouseClicked(evt);
            }
        });
        menuBar.add(contractItem);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollPanel)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        loadDataByClass();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        int id = -1;
        if (!"Equipment".equals(currentClass)) {
            id = getIdFromTable();
        } else {
            id = -2;
        }
        if (id == -1) {
            JOptionPane.showMessageDialog(this, "Before delete u need to choose row!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int result = JOptionPane.showConfirmDialog(this, "Are you sure that you want to delete this row?", "Question", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (JOptionPane.CANCEL_OPTION == result) {
            return;
        }

        boolean isProcessed = switch (currentClass) {
            case "Dealer" ->
                dealerController.deleteDealer(id);
            case "User" ->
                userController.deleteUserById(id);
            case "Automobile" ->
                automobileController.deleteAutomobile(id);
            case "Equipment" -> {
                int[] eqId = getEquipmentId();
                int autoId = eqId[0];
                int equipId = eqId[1];
                yield equipmentController.deleteEquipment(autoId, equipId);
            }
            case "Warranty" ->
                warrantyController.deleteWarranty(id);
            case "Contract" ->
                contractController.deleteContract(id);
            default ->
                false;
        };
        JOptionPane.showMessageDialog(this, isProcessed ? "Row was deleted!" : "Row cannot be delete because it is in use in other table!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        loadDataByClass();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void itemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemMouseClicked
        // TODO add your handling code here:
        Component cmp = evt.getComponent();
        if (cmp instanceof JMenu menuItem) {
            currentClass = menuItem.getActionCommand();
        }

        if (currentClass.equals("Automobile")) {
            addEquipButton.setVisible(true);
        } else {
            addEquipButton.setVisible(false);
        }

        if (currentClass.equals("Equipment")) {
            addButton.setEnabled(false);
        } else {
            addButton.setEnabled(true);
        }

        loadDataByClass();
    }//GEN-LAST:event_itemMouseClicked

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        switch (currentClass) {
            case "Dealer" ->
                new DealerDataForm(this).setVisible(true);
            case "User" ->
                new UserDataForm(this).setVisible(true);
            case "Warranty" ->
                new WarrantyDataForm(this).setVisible(true);
            case "Automobile" ->
                new AutomobileDataForm(this).setVisible(true);
            default -> {
                JOptionPane.showMessageDialog(this, "You cannot add a row in this table", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        this.dispose();
    }//GEN-LAST:event_addButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        int id = -1;
        if (!"Equipment".equals(currentClass)) {
            id = getIdFromTable();
        } else {
            id = -2;
        }
        if (id == -1) {
            JOptionPane.showMessageDialog(this, "Before update you need to choose row!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        switch (currentClass) {
            case "Dealer" -> {
                Dealer dealer = dealerController.getDealerById(id);
                new DealerDataForm(this, dealer).setVisible(true);
            }
            case "User" -> {
                User user = userController.getUserById(id);
                new UserDataForm(this).setVisible(true);
            }
            case "Warranty" -> {
                Warranty warranty = warrantyController.getWarrantyById(id);
                new WarrantyDataForm(this, warranty).setVisible(true);
            }
            case "Automobile" -> {
                Automobile auto = automobileController.getAutoById(id);
                new AutomobileDataForm(this, auto).setVisible(true);
            }
            case "Equipment" -> {
                int[] eqId = getEquipmentId();
                int autoId = eqId[0];
                int equipId = eqId[1];
                if (eqId != null) {
                    Equipment equipment = equipmentController.getEquipmentById(autoId, equipId);
                    new EquipmentDataForm(this, equipment).setVisible(true);
                }
            }
            default -> {
                JOptionPane.showMessageDialog(this, "You cannot update a row in this table", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        this.dispose();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void addEquipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEquipButtonActionPerformed
        // TODO add your handling code here:
        int id = getIdFromTable();
        if (id == -1) {
            JOptionPane.showMessageDialog(this, "Before adding you need to choose automobile!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        new EquipmentDataForm(this, automobileController.getAutoById(id)).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_addEquipButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        Main.run();
    }//GEN-LAST:event_formWindowClosing

    private int getIdFromTable() {
        int rowNumber = userTable.getSelectedRow();
        if (rowNumber == -1) {
            return -1;
        }
        return Integer.parseInt(userTable.getValueAt(rowNumber, 0).toString());
    }

    private int[] getEquipmentId() {
        int rowNumber = userTable.getSelectedRow();
        if (rowNumber == -1) {
            return null;
        }
        String[] strId = String.valueOf(userTable.getValueAt(rowNumber, 1)).split(":");
        int[] id = new int[2];
        id[0] = Integer.parseInt(strId[0]);
        id[1] = Integer.parseInt(strId[1]);
        return id;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton addEquipButton;
    private javax.swing.JMenu automobilesItem;
    private javax.swing.JMenu contractItem;
    private javax.swing.JMenu dealrsItem;
    private javax.swing.JButton deleteButton;
    private javax.swing.JMenu equipmentItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JButton refreshButton;
    private javax.swing.JScrollPane tableScrollPanel;
    private javax.swing.JButton updateButton;
    private javax.swing.JMenu userItem;
    private javax.swing.JTable userTable;
    private javax.swing.JMenu warrantyItem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void showWindow() {
        this.setVisible(true);
    }
}
