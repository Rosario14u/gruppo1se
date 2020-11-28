/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation.planner;

import business.maintenanceactivity.Ewo;
import business.maintenanceactivity.MaintenanceActivity;
import business.maintenanceactivity.Material;
import business.maintenanceactivity.PlannedMaintenanceActivity;
import business.user.Planner;
import com.toedter.calendar.JTextFieldDateEditor;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import persistence.database.ConnectionDB;
import persistence.maintenanceactivity.MaintenanceActivityDAO;
import persistence.maintenanceactivity.MaintenanceActivityDAOImpl;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAOImpl;
import persistence.maintenanceactivity.SiteDao;
import persistence.maintenanceactivity.SiteDaoImpl;

/**
 *
 * @author gorra
 */
public class ViewModifyPlannerGUI extends javax.swing.JFrame {
    private final DefaultListModel<Material> listModel;
    private Planner planner;
    private Connection conn;
    /**
     * Creates new form ViewModifyPlannerGUI1
     */
    public ViewModifyPlannerGUI() {
        listModel = new DefaultListModel<>();
        planner = new Planner("admin","admin", new MaintenanceActivityDAOImpl(new SiteDaoImpl()),
                new RequiredMaterialForMaintenanceDAOImpl());
        initComponents();
        initializeField(false);
        try {    
            conn = ConnectionDB.getInstanceConnection().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ViewModifyPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jSpinner1 = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        branchOfficeTextField = new javax.swing.JTextField();
        areaTextField = new javax.swing.JTextField();
        activityIdLabel = new javax.swing.JLabel();
        branchOfficeLabel = new javax.swing.JLabel();
        areaLabel = new javax.swing.JLabel();
        activityIdTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        workspaceNoteTextArea = new javax.swing.JTextArea();
        activityComboBox = new javax.swing.JComboBox<>();
        listOfMaterialsLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listOfMaterialsJList = new javax.swing.JList<>();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        modifyButton = new javax.swing.JButton();
        viewButton = new javax.swing.JButton();
        addMaterialTextField = new javax.swing.JTextField();
        estimatedInterventionTimeTextField = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser(new Date());
        typologyLabel = new javax.swing.JLabel();
        interruptibleActivityCheckBox = new javax.swing.JCheckBox();
        estimatedInterventionTimeLabel = new javax.swing.JLabel();
        weekLabel = new javax.swing.JLabel();
        activityDescriptionLabel = new javax.swing.JLabel();
        interruptibleActivityLabel = new javax.swing.JLabel();
        typologyTextField = new javax.swing.JTextField();
        weekTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        activityDescriptionTextArea = new javax.swing.JTextArea();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(210, 102, 70));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("VIEW MODIFY ACTIVITY GUI");

        activityIdLabel.setForeground(new java.awt.Color(0, 0, 0));
        activityIdLabel.setText("Activity ID:");

        branchOfficeLabel.setForeground(new java.awt.Color(0, 0, 0));
        branchOfficeLabel.setText("Branch Office:");

        areaLabel.setForeground(new java.awt.Color(0, 0, 0));
        areaLabel.setText("Area:");

        activityIdTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activityIdTextFieldActionPerformed(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Workspace Notes:");

        workspaceNoteTextArea.setColumns(20);
        workspaceNoteTextArea.setRows(5);
        jScrollPane1.setViewportView(workspaceNoteTextArea);

        activityComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Planned", "EWO", "Extra"}));

        listOfMaterialsLabel.setForeground(new java.awt.Color(0, 0, 0));
        listOfMaterialsLabel.setText("List of Materials:");

        listOfMaterialsJList.setModel(listModel);
        jScrollPane3.setViewportView(listOfMaterialsJList);

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        modifyButton.setText("Modify");
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        viewButton.setText("View");
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        jDateChooser1.getJCalendar().setMinSelectableDate(new Date());
        JTextFieldDateEditor editor = (JTextFieldDateEditor) jDateChooser1.getDateEditor();
        editor.setEditable(false);
        editor.setVisible(false);

        typologyLabel.setForeground(new java.awt.Color(0, 0, 0));
        typologyLabel.setText("Typology:");

        interruptibleActivityCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interruptibleActivityCheckBoxActionPerformed(evt);
            }
        });

        estimatedInterventionTimeLabel.setForeground(new java.awt.Color(0, 0, 0));
        estimatedInterventionTimeLabel.setText("Estimated Intervention Time:");

        weekLabel.setForeground(new java.awt.Color(0, 0, 0));
        weekLabel.setText("Week:");

        activityDescriptionLabel.setForeground(new java.awt.Color(0, 0, 0));
        activityDescriptionLabel.setText("Activity Description:");

        interruptibleActivityLabel.setForeground(new java.awt.Color(0, 0, 0));
        interruptibleActivityLabel.setText("Interruptible Activity:");

        weekTextField.setForeground(new java.awt.Color(0, 0, 0));

        activityDescriptionTextArea.setColumns(20);
        activityDescriptionTextArea.setRows(5);
        jScrollPane2.setViewportView(activityDescriptionTextArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(265, 265, 265))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(addMaterialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(activityDescriptionLabel)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(typologyLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(typologyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(62, 62, 62)
                                    .addComponent(weekLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(weekTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(52, 52, 52)
                                    .addComponent(estimatedInterventionTimeLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(estimatedInterventionTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(activityIdLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(activityIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(97, 97, 97)
                                        .addComponent(branchOfficeLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(branchOfficeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(areaLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(areaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(activityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(interruptibleActivityLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(interruptibleActivityCheckBox))
                            .addComponent(listOfMaterialsLabel))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(activityIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(activityIdLabel)
                            .addComponent(branchOfficeLabel)
                            .addComponent(branchOfficeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(areaLabel)
                            .addComponent(areaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(activityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(weekLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(typologyLabel)
                                .addComponent(typologyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(estimatedInterventionTimeLabel)
                                .addComponent(estimatedInterventionTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(weekTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(activityDescriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(interruptibleActivityLabel)
                    .addComponent(interruptibleActivityCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(listOfMaterialsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addMaterialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton)
                    .addComponent(deleteButton))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyButton)
                    .addComponent(viewButton))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void interruptibleActivityCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interruptibleActivityCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_interruptibleActivityCheckBoxActionPerformed

    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed
        String activityId = activityIdTextField.getText().trim().replaceAll("  +", " ");
        try{
            int id = Integer.parseInt(activityId);
            if (id >= 0){
                MaintenanceActivity activity = planner.viewMaintenanceActivity(id);
                if(activity != null){
                    setField(activity);
                    initializeField(true);
                }else{
                    errorMessage("ID not present"); 
                }
            }else{
               errorMessage("ID must be a positive integer"); 
            }
        }catch(NumberFormatException ex){
            errorMessage("ID must be an integer");
        }  
    }//GEN-LAST:event_viewButtonActionPerformed

    private void activityIdTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activityIdTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_activityIdTextFieldActionPerformed

    //============================================================================================================!!!!!!!!==========
    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed
        getField();
    }//GEN-LAST:event_modifyButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        Material material = new Material(addMaterialTextField.getText());
        List<Material> listMaterialsToAdd = new ArrayList<>(Arrays.asList(material));
        int activityId = getCheckedNumberParameter(activityIdTextField.getText());
        if(planner.addRequiredMaterial(activityId, listMaterialsToAdd)){
            listModel.addElement(material);
            addMaterialTextField.setText("");
        }else
            infoMessage("Material not added");
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        List<Material> listMaterialsToDelete = new ArrayList<>();
        int[] selectedIndex =  listOfMaterialsJList.getSelectedIndices();
        for (int i = selectedIndex.length - 1; i >= 0; i--) {    
            listMaterialsToDelete.add(listModel.getElementAt(selectedIndex[i]));
            listModel.removeElementAt(selectedIndex[i]);
        }
        if(selectedIndex.length <= 0){
            infoMessage("Select Material to delete");
        }else{
            int activityId = getCheckedNumberParameter(activityIdTextField.getText());
            planner.removeRequiredMaterial(activityId, listMaterialsToDelete);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

        
    private void getField(){
        String area =  areaTextField.getText();
        String branchOffice = branchOfficeTextField.getText();
        
        String value = activityComboBox.getSelectedItem().toString();
        String typologyOfActivity = value.compareTo("Planned") == 0 ? "Planned" : "Unplanned";
        String typologyOfUnplannedActivity = typologyOfActivity.compareTo("Unplanned") == 0 ? value : null;
                       
        int estimatedInterventionTime = getCheckedNumberParameter(estimatedInterventionTimeTextField.getText());
        int activityId = getCheckedNumberParameter(activityIdTextField.getText());
        boolean interruptibleActivity = interruptibleActivityCheckBox.isSelected();
        String typology = typologyTextField.getText();
        String activityDescrioption = activityDescriptionTextArea.getText();
        String date = jDateChooser1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
        
        if(activityId >= 0 && estimatedInterventionTime >= 0)
            planner.modifyMaintenanceActivity(activityId, branchOffice, area, typology, activityDescrioption, estimatedInterventionTime, 
                    date, interruptibleActivity, typologyOfActivity, typologyOfUnplannedActivity);
    }
    
    
    //==================== VEDERE ================================  
    private int getCheckedNumberParameter(String number){
        number = number.trim().replaceAll("  +", " ");
        try{
            int id = Integer.parseInt(number);
            if (id >= 0) 
                return id;
            else errorMessage("ID must be a positive integer");            
        }catch(NumberFormatException ex){
            errorMessage("ID must be an integer");
        }
        System.out.println(-1);
        return -1;
    }
    
    //===========================================================================================================!!!!!!!=============
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewModifyPlannerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewModifyPlannerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewModifyPlannerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewModifyPlannerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewModifyPlannerGUI().setVisible(true);
            }
        });
    }
    
    private void initializeField(boolean enabled) {
        activityDescriptionTextArea.setEnabled(enabled);
        addButton.setEnabled(enabled);
        addMaterialTextField.setEnabled(enabled);
        areaTextField.setEnabled(enabled);
        branchOfficeTextField.setEnabled(enabled);
        deleteButton.setEnabled(enabled);
        estimatedInterventionTimeTextField.setEnabled(enabled);
        interruptibleActivityCheckBox.setEnabled(enabled);
        listOfMaterialsJList.setEnabled(enabled);
        modifyButton.setEnabled(enabled);
        typologyTextField.setEnabled(enabled);
        weekTextField.setEnabled(enabled);
        workspaceNoteTextArea.setEnabled(enabled);        
        activityComboBox.setEnabled(enabled);
    }
    
    
    private void setField(MaintenanceActivity activity){
        if (activity instanceof PlannedMaintenanceActivity){
            activityComboBox.setSelectedItem("Planned");
        }else if (activity instanceof Ewo){
            activityComboBox.setSelectedItem("EWO");
        }else{
            activityComboBox.setSelectedItem("Extra");
        }
        activityDescriptionTextArea.setText(activity.getActivityDescription());
        areaTextField.setText(activity.getSite().getArea());
        branchOfficeTextField.setText(activity.getSite().getBranchOffice());
        estimatedInterventionTimeTextField.setText(String.valueOf(activity.getEstimatedInterventionTime()));
        interruptibleActivityCheckBox.setSelected(activity.isInterruptibleActivity());
        typologyTextField.setText(activity.getTypology());
        weekTextField.setText(String.valueOf(getWeekNumber(activity.getDate())));
        workspaceNoteTextArea.setText(activity.getSite().getWorkSpaceNotes());
        listModel.removeAllElements();
        listModel.addAll(0, activity.getMaterials());
    }
    
    private int getWeekNumber(LocalDate date){
        Locale userLocale = Locale.ITALY;
        WeekFields weekNumbering = WeekFields.of(userLocale);
        return date.get(weekNumbering.weekOfWeekBasedYear());
    }
    
    private void errorMessage(String message){
        JOptionPane.showMessageDialog(this, message, "ERRORE", JOptionPane.ERROR_MESSAGE);
    }
    
    private void infoMessage(String message){
        JOptionPane.showMessageDialog(this, message, "INFO", JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> activityComboBox;
    private javax.swing.JLabel activityDescriptionLabel;
    private javax.swing.JTextArea activityDescriptionTextArea;
    private javax.swing.JLabel activityIdLabel;
    private javax.swing.JTextField activityIdTextField;
    private javax.swing.JButton addButton;
    private javax.swing.JTextField addMaterialTextField;
    private javax.swing.JLabel areaLabel;
    private javax.swing.JTextField areaTextField;
    private javax.swing.JLabel branchOfficeLabel;
    private javax.swing.JTextField branchOfficeTextField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel estimatedInterventionTimeLabel;
    private javax.swing.JTextField estimatedInterventionTimeTextField;
    private javax.swing.JCheckBox interruptibleActivityCheckBox;
    private javax.swing.JLabel interruptibleActivityLabel;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JList<Material> listOfMaterialsJList;
    private javax.swing.JLabel listOfMaterialsLabel;
    private javax.swing.JButton modifyButton;
    private javax.swing.JLabel typologyLabel;
    private javax.swing.JTextField typologyTextField;
    private javax.swing.JButton viewButton;
    private javax.swing.JLabel weekLabel;
    private javax.swing.JTextField weekTextField;
    private javax.swing.JTextArea workspaceNoteTextArea;
    // End of variables declaration//GEN-END:variables
}
