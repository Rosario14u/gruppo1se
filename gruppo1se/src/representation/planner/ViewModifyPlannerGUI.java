/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation.planner;

import business.maintenanceactivity.Ewo;
import business.maintenanceactivity.ExtraActivity;
import business.maintenanceactivity.MaintenanceActivity;
import business.maintenanceactivity.Material;
import business.maintenanceactivity.PlannedMaintenanceActivity;
import business.user.Planner;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author gorra
 */
public class ViewModifyPlannerGUI extends javax.swing.JFrame {
    private final DefaultListModel<Material> listModel;
    private Planner planner;
    private static final String URL = "jdbc:postgresql://localhost/Gruppo1_SE";
    private static final  String USER = "gruppo1";
    private static final  String PWD = "123456";
    private Connection conn;
    /**
     * Creates new form ViewModifyPlannerGUI1
     */
    public ViewModifyPlannerGUI() {
        listModel = new DefaultListModel<>();
        planner = new Planner("admin","admin");
        initComponents();
        initializeField(false);
        try {    
            conn = DriverManager.getConnection(URL, USER, PWD);
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
        estimatedInterventionTimeLabel = new javax.swing.JLabel();
        weekLabel = new javax.swing.JLabel();
        activityDescriptionLabel = new javax.swing.JLabel();
        interruptibleActivityLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        typologyTextField = new javax.swing.JTextField();
        activityIdTextField = new javax.swing.JTextField();
        weekTextField = new javax.swing.JTextField();
        branchOfficeTextField = new javax.swing.JTextField();
        areaTextField = new javax.swing.JTextField();
        estimatedInterventionTimeTextField = new javax.swing.JTextField();
        activityIdLabel = new javax.swing.JLabel();
        branchOfficeLabel = new javax.swing.JLabel();
        areaLabel = new javax.swing.JLabel();
        typologyLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        workspaceNoteTextArea = new javax.swing.JTextArea();
        interruptibleActivityCheckBox = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        activityDescriptionTextArea = new javax.swing.JTextArea();
        listOfMaterialsLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listOfMaterialsJList = new javax.swing.JList<>();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        modifyButton = new javax.swing.JButton();
        viewButton = new javax.swing.JButton();
        addMaterialTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        activityComboBox = new javax.swing.JComboBox<>();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        estimatedInterventionTimeLabel.setText("Estimated Intervention Time:");

        weekLabel.setText("Week:");

        activityDescriptionLabel.setText("Activity Description:");

        interruptibleActivityLabel.setText("Interruptible Activity:");

        jLabel11.setText("Workspace Notes:");

        activityIdTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activityIdTextFieldActionPerformed(evt);
            }
        });

        weekTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weekTextFieldActionPerformed(evt);
            }
        });

        activityIdLabel.setText("Activity ID:");

        branchOfficeLabel.setText("Branch Office:");

        areaLabel.setText("Area:");

        typologyLabel.setText("Typology:");

        workspaceNoteTextArea.setColumns(20);
        workspaceNoteTextArea.setRows(5);
        jScrollPane1.setViewportView(workspaceNoteTextArea);

        interruptibleActivityCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interruptibleActivityCheckBoxActionPerformed(evt);
            }
        });

        activityDescriptionTextArea.setColumns(20);
        activityDescriptionTextArea.setRows(5);
        activityDescriptionTextArea.setPreferredSize(new java.awt.Dimension(220, 80));
        jScrollPane2.setViewportView(activityDescriptionTextArea);

        listOfMaterialsLabel.setText("List of Materials:");

        listOfMaterialsJList.setModel(listModel);
        jScrollPane3.setViewportView(listOfMaterialsJList);

        addButton.setText("Add");

        deleteButton.setText("Delete");

        modifyButton.setText("Modify");

        viewButton.setText("View");
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Activity:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel2.setText("VIEW MODIFY ACTIVITY GUI");

        activityComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Planned", "EWO", "Extra"}));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(listOfMaterialsLabel)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(activityDescriptionLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(interruptibleActivityLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(interruptibleActivityCheckBox)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addMaterialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(typologyLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(typologyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(weekLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(weekTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87)
                                .addComponent(estimatedInterventionTimeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(estimatedInterventionTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(210, 210, 210))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(activityIdLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(activityIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(103, 103, 103)
                                            .addComponent(branchOfficeLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(branchOfficeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(135, 135, 135)
                                            .addComponent(areaLabel)))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(areaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(25, 25, 25))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(activityComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(18, 18, 18)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(44, 44, 44))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activityIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(activityIdLabel)
                    .addComponent(branchOfficeLabel)
                    .addComponent(branchOfficeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(areaLabel)
                    .addComponent(areaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(activityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typologyLabel)
                    .addComponent(typologyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(weekLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(weekTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estimatedInterventionTimeLabel)
                    .addComponent(estimatedInterventionTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(activityDescriptionLabel)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(interruptibleActivityLabel)
                    .addComponent(interruptibleActivityCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(listOfMaterialsLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(deleteButton)
                    .addComponent(addMaterialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyButton)
                    .addComponent(viewButton))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void interruptibleActivityCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interruptibleActivityCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_interruptibleActivityCheckBoxActionPerformed

    private void weekTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weekTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_weekTextFieldActionPerformed

    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed
        String activityId = activityIdTextField.getText().trim().replaceAll("  +", " ");
        try{
            int id = Integer.parseInt(activityId);
            if (id >= 0){
                MaintenanceActivity activity = planner.viewMaintenanceActivity(id, conn);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
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
