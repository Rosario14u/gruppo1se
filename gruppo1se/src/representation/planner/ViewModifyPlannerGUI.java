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
import exception.NumberNotValidException;
import exception.MaintenanceActivityException;
import exception.MaterialException;
import exception.SiteException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import persistence.maintenanceactivity.MaintenanceActivityDAOImpl;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAOImpl;
import persistence.maintenanceactivity.SiteDaoImpl;

/**
 *
 * @author gorra
 */

public class ViewModifyPlannerGUI extends javax.swing.JFrame {
    private final DefaultListModel<Material> listModel;
    private final Planner planner;
    /**
     * Creates new form ViewModifyPlannerGUI1
     */
    //Method developed by Rosario Gaeta
    public ViewModifyPlannerGUI() {
        listModel = new DefaultListModel<>();
        planner = new Planner("admin","admin", new MaintenanceActivityDAOImpl(new SiteDaoImpl()),
                new RequiredMaterialForMaintenanceDAOImpl());
        initComponents();
        initializeField(false);
        weekTextField.setEnabled(false);
        weekTextField.setText("");
        workspaceNoteTextArea.setEnabled(false);

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
        estimatedInterventionTimeTextField = new javax.swing.JTextField();
        calendarDateChooser = new com.toedter.calendar.JDateChooser(new Date());
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
        materialComboBox = new javax.swing.JComboBox<>();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("VIEW MODIFY ACTIVITY GUI");

        branchOfficeTextField.setFont(new java.awt.Font("Rockwell", 2, 14)); // NOI18N

        areaTextField.setFont(new java.awt.Font("Rockwell", 2, 14)); // NOI18N

        activityIdLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        activityIdLabel.setText("Activity ID:");

        branchOfficeLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        branchOfficeLabel.setText("Branch Office:");

        areaLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        areaLabel.setText("Area:");

        activityIdTextField.setFont(new java.awt.Font("Rockwell", 2, 14)); // NOI18N
        activityIdTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activityIdTextFieldActionPerformed(evt);
            }
        });
        activityIdTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                activityIdTextFieldKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Workspace Notes:");

        workspaceNoteTextArea.setColumns(20);
        workspaceNoteTextArea.setFont(new java.awt.Font("Rockwell", 2, 14)); // NOI18N
        workspaceNoteTextArea.setRows(5);
        jScrollPane1.setViewportView(workspaceNoteTextArea);

        activityComboBox.setFont(new java.awt.Font("Rockwell", 2, 14)); // NOI18N
        activityComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Planned", "EWO", "Extra"}));

        listOfMaterialsLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        listOfMaterialsLabel.setText("List of Materials:");

        listOfMaterialsJList.setFont(new java.awt.Font("Rockwell", 2, 14)); // NOI18N
        listOfMaterialsJList.setModel(listModel);
        jScrollPane3.setViewportView(listOfMaterialsJList);

        addButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        modifyButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        modifyButton.setText("Modify");
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        viewButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        viewButton.setText("View");
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        estimatedInterventionTimeTextField.setFont(new java.awt.Font("Rockwell", 2, 14)); // NOI18N

        calendarDateChooser.getJCalendar().setMinSelectableDate(new Date());
        JTextFieldDateEditor editor = (JTextFieldDateEditor) calendarDateChooser.getDateEditor();
        editor.setEditable(false);
        editor.setVisible(false);
        editor.addPropertyChangeListener(
            new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent e) {
                    LocalDate date = calendarDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    weekTextField.setText(String.valueOf(getWeekNumber(date)));
                }
            });

            typologyLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            typologyLabel.setText("Typology:");

            interruptibleActivityCheckBox.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    interruptibleActivityCheckBoxActionPerformed(evt);
                }
            });

            estimatedInterventionTimeLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            estimatedInterventionTimeLabel.setText("Estimated Intervention Time:");

            weekLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            weekLabel.setText("Week:");

            activityDescriptionLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            activityDescriptionLabel.setText("Activity Description:");

            interruptibleActivityLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            interruptibleActivityLabel.setText("Interruptible Activity:");

            typologyTextField.setFont(new java.awt.Font("Rockwell", 2, 14)); // NOI18N

            weekTextField.setFont(new java.awt.Font("Rockwell", 2, 14)); // NOI18N

            activityDescriptionTextArea.setColumns(20);
            activityDescriptionTextArea.setFont(new java.awt.Font("Rockwell", 2, 14)); // NOI18N
            activityDescriptionTextArea.setRows(5);
            jScrollPane2.setViewportView(activityDescriptionTextArea);

            materialComboBox.setFont(new java.awt.Font("Rockwell", 2, 14)); // NOI18N
            materialComboBox.setModel(new javax.swing.DefaultComboBoxModel<>());

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(187, 187, 187)
                    .addComponent(jLabel2)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(activityDescriptionLabel)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(interruptibleActivityLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(interruptibleActivityCheckBox))
                            .addComponent(listOfMaterialsLabel)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(activityIdLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(activityIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(97, 97, 97)
                                            .addComponent(branchOfficeLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(branchOfficeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                                    .addComponent(areaLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(areaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(typologyLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(typologyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(38, 38, 38)
                                    .addComponent(weekLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(weekTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(calendarDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32)
                                    .addComponent(estimatedInterventionTimeLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(estimatedInterventionTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jScrollPane1)
                                    .addGap(18, 18, 18)
                                    .addComponent(activityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(materialComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(340, 340, 340)
                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(247, 247, 247)))
                    .addGap(18, 18, 18))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(jLabel2)
                    .addGap(44, 44, 44)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(activityIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(activityIdLabel)
                                .addComponent(branchOfficeLabel)
                                .addComponent(branchOfficeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(typologyLabel)
                                        .addComponent(typologyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(estimatedInterventionTimeLabel)
                                        .addComponent(estimatedInterventionTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(weekTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(weekLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(calendarDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(areaLabel)
                                .addComponent(areaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(34, 34, 34)
                            .addComponent(activityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addComponent(addButton)
                        .addComponent(deleteButton)
                        .addComponent(materialComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(29, 29, 29)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(modifyButton)
                        .addComponent(viewButton))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void interruptibleActivityCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interruptibleActivityCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_interruptibleActivityCheckBoxActionPerformed
//Method developed by Rosario Gaeta
    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed
        String activityId = activityIdTextField.getText().trim().replaceAll("  +", " ");
        try{
            int id = getCheckedNumberParameter(activityId);
            MaintenanceActivity activity = planner.viewMaintenanceActivity(id);
            if (activity != null){
                setField(activity);
                initializeField(true);
                setAvailableMaterialToAdd(id);
            }else{
                clearField();
                infoMessage("Id non presente");
            }        
        }catch(NumberFormatException ex){
            errorMessage("ID must be an integer");
        } catch (SiteException | MaintenanceActivityException | MaterialException | NumberNotValidException ex) {
            errorMessage(ex.getMessage());
        } 
    }//GEN-LAST:event_viewButtonActionPerformed

    private void activityIdTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activityIdTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_activityIdTextFieldActionPerformed

    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed
        try {
            getField();
            clearField();
            initializeField(false);
        } catch (NumberNotValidException ex) {
            errorMessage(ex.getMessage());
        }
    }//GEN-LAST:event_modifyButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        int selectedIndex = materialComboBox.getSelectedIndex();
        if(selectedIndex != -1){
            Material material = materialComboBox.getItemAt(selectedIndex);
            materialComboBox.removeItemAt(selectedIndex);
            List<Material> listMaterialsToAdd = new ArrayList<>(Arrays.asList(material));
            try{
                int activityId = getCheckedNumberParameter(activityIdTextField.getText());
                planner.addRequiredMaterial(activityId, listMaterialsToAdd);
                listModel.addElement(material);
            }catch(MaterialException | NumberNotValidException ex){
                errorMessage(ex.getMessage());
            }
        }else
            infoMessage("No material selected");
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        List<Material> listMaterialsToDelete = new ArrayList<>();
        int[] selectedIndex =  listOfMaterialsJList.getSelectedIndices();
        for (int i = selectedIndex.length - 1; i >= 0; i--) {    
            Material material = listModel.getElementAt(selectedIndex[i]);
            listMaterialsToDelete.add(material);
            listModel.removeElementAt(selectedIndex[i]);
            materialComboBox.addItem(material);
        }
        if(selectedIndex.length <= 0){
            infoMessage("Select Material to delete");
        }else{
            try {
                int activityId = getCheckedNumberParameter(activityIdTextField.getText());
                planner.removeRequiredMaterial(activityId, listMaterialsToDelete);
            } catch (MaterialException | NumberNotValidException ex) {
                errorMessage(ex.getMessage());
            }
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void activityIdTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_activityIdTextFieldKeyPressed
        initializeField(false);
    }//GEN-LAST:event_activityIdTextFieldKeyPressed

    
    
    
    private void getField() throws NumberNotValidException{
        String area =  areaTextField.getText();
        String branchOffice = branchOfficeTextField.getText();
        
        String value = activityComboBox.getSelectedItem().toString().toUpperCase();
        String typologyOfActivity = value.compareTo("PLANNED") == 0 ? "PLANNED" : "UNPLANNED";
        String typologyOfUnplannedActivity = typologyOfActivity.compareTo("UNPLANNED") == 0 ? value : null;
                       
        int estimatedInterventionTime = getCheckedNumberParameter(estimatedInterventionTimeTextField.getText());
        int activityId = getCheckedNumberParameter(activityIdTextField.getText());
        boolean interruptibleActivity = interruptibleActivityCheckBox.isSelected();
        String typology = typologyTextField.getText();
        String activityDescrioption = activityDescriptionTextArea.getText();
        String date = calendarDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
        if(activityId >= 0 && estimatedInterventionTime >= 0)
            try{
                planner.modifyMaintenanceActivity(activityId, branchOffice, area, typology, activityDescrioption, estimatedInterventionTime, 
                        date, interruptibleActivity, typologyOfActivity, typologyOfUnplannedActivity);
            }catch(MaintenanceActivityException ex){
                errorMessage(ex.getMessage());
            }
    }
         
    
    //==================== VEDERE ================================  
    private int getCheckedNumberParameter(String number) throws NumberNotValidException{
        number = number.trim().replaceAll("  +", " ");
        try{
            int id = Integer.parseInt(number);
            if (id >= 0) 
                return id;
            else throw new NumberNotValidException("number must be a positive integer");          
        }catch(NumberFormatException ex){
            throw new NumberNotValidException("number must be an integer"); 
        }
    }
    
    private void setAvailableMaterialToAdd(int id){
        List<Material> list;
        try {
            materialComboBox.removeAllItems();
            list = planner.retrieveAvaliableMaterialToAdd(id);
            for(Material material:list)
                materialComboBox.addItem(material);
        } catch (MaterialException ex) {
            errorMessage(ex.getMessage());
        }
    }
    
    
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
        areaTextField.setEnabled(enabled);
        branchOfficeTextField.setEnabled(enabled);
        deleteButton.setEnabled(enabled);
        estimatedInterventionTimeTextField.setEnabled(enabled);
        interruptibleActivityCheckBox.setEnabled(enabled);
        listOfMaterialsJList.setEnabled(enabled);
        modifyButton.setEnabled(enabled);
        typologyTextField.setEnabled(enabled);        
        activityComboBox.setEnabled(enabled);
        calendarDateChooser.setEnabled(enabled);
        materialComboBox.setEnabled(enabled);
    }
    
    
    private void setField(MaintenanceActivity activity){
        if (activity !=null){
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
            workspaceNoteTextArea.setText(activity.getSite().getWorkSpaceNotes());
            listModel.removeAllElements();
            listModel.addAll(0, activity.getMaterials());
            calendarDateChooser.setDate(Date.from(activity.getDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        }
    }
    
    private void clearField() {
        activityDescriptionTextArea.setText("");
        areaTextField.setText("");
        branchOfficeTextField.setText("");
        estimatedInterventionTimeTextField.setText("");
        interruptibleActivityCheckBox.setSelected(false);
        typologyTextField.setText("");
        weekTextField.setText("");
        workspaceNoteTextArea.setText("");
        weekTextField.setText("");
        workspaceNoteTextArea.setText("");
        listModel.removeAllElements();
        activityComboBox.setSelectedIndex(-1);
        
    }
    
    private int getWeekNumber(LocalDate date){
        Locale userLocale = Locale.ITALY;
        WeekFields weekNumbering = WeekFields.of(userLocale);
        return date.get(weekNumbering.weekOfWeekBasedYear());
    }
    
    //=============================== ERROR/INFO MESSAGES ===============================
    
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
    private javax.swing.JLabel areaLabel;
    private javax.swing.JTextField areaTextField;
    private javax.swing.JLabel branchOfficeLabel;
    private javax.swing.JTextField branchOfficeTextField;
    private com.toedter.calendar.JDateChooser calendarDateChooser;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel estimatedInterventionTimeLabel;
    private javax.swing.JTextField estimatedInterventionTimeTextField;
    private javax.swing.JCheckBox interruptibleActivityCheckBox;
    private javax.swing.JLabel interruptibleActivityLabel;
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
    private javax.swing.JComboBox<Material> materialComboBox;
    private javax.swing.JButton modifyButton;
    private javax.swing.JLabel typologyLabel;
    private javax.swing.JTextField typologyTextField;
    private javax.swing.JButton viewButton;
    private javax.swing.JLabel weekLabel;
    private javax.swing.JTextField weekTextField;
    private javax.swing.JTextArea workspaceNoteTextArea;
    // End of variables declaration//GEN-END:variables
}
