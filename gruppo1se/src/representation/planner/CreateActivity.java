/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation.planner;

import business.maintenanceactivity.MaintenanceActivityFactory.Typology;
import business.maintenanceactivity.Material;
import business.maintenanceactivity.Site;
import business.user.Planner;
import exception.MaintenanceActivityException;
import exception.NotValidParameterException;
import exception.TypologyException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import persistence.maintenanceactivity.EmployeeAppointmentDAOImpl;
import persistence.maintenanceactivity.MaintenanceActivityDAOImpl;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAOImpl;
import persistence.maintenanceactivity.RequiredSkillForMaintenanceDAOImpl;
import persistence.maintenanceactivity.SiteDaoImpl;
import persistence.maintenanceactivity.TypologyDAOImpl;
import persistence.user.MaintainerSkillDAOImpl;
import persistence.user.UsersDAOImpl;
import presentation.manager.MessageManager;


/**
 *
 * @author aless
 */
public class CreateActivity extends javax.swing.JFrame {
    private Planner planner = null;
    private final JTextField[] textFields = new JTextField[7];
    private boolean interruptible = false;
    private boolean typologyBool = false;
    private boolean typeOfActivity = false;
    private MyDocumentListener myDocumentListener = null;
    /**
     * Creates new form CreateActivity
     * @param planner
     * @throws exception.TypologyException
     * @throws exception.NotValidParameterException
     */
    public CreateActivity(Planner planner) throws TypologyException, NotValidParameterException {
        this.planner = planner;
        initComponents();
        myDocumentListener = new MyDocumentListener();
        textFields[0] = jActivityId;
        textFields[1] = jBranchOffice;
        textFields[2] = jArea;
        textFields[3] = jActivityDescription;
        textFields[4] = jEstimatedInterventionTime;
        textFields[5] = jDate;
        textFields[6] = jMaterials;
        for (int i = 0; i<textFields.length-1;i++) {
            textFields[i].getDocument().addDocumentListener(myDocumentListener);
        }
        fillTypology();
        jTypology.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(!jTypology.getSelectedItem().equals("")){
                    typologyBool = true;
                    checkFields();
                }
                else{ 
                    typologyBool = false;
                    checkFields();
                }
            }
        });
        jTypologyOfActivity.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(!jTypologyOfActivity.getSelectedItem().equals("")){
                    typeOfActivity = true;
                    checkFields();
                }else {
                    typeOfActivity = false;
                    checkFields();
                }
            }
        });
    }

    private void fillTypology() throws TypologyException, NotValidParameterException{
        List<String> typologiesList = planner.readTypologies();
        String[] strings = new String[typologiesList.size()+1];
        Object[] objArray = typologiesList.toArray();
        strings[0] = "";
        for(int i = 1; i<strings.length; i++)
            strings[i] = (String) objArray[i-1];
        jTypology.setModel(new DefaultComboBoxModel(strings));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupTypeOfActivity = new javax.swing.ButtonGroup();
        buttonGroupInterruptible = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jActivityId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jBranchOffice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jArea = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jActivityDescription = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jEstimatedInterventionTime = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jDate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jMaterials = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jCreate = new javax.swing.JButton();
        jInterruptibleTrue = new javax.swing.JRadioButton();
        jInterruptibleFalse = new javax.swing.JRadioButton();
        jErrorActivityId = new javax.swing.JLabel();
        jErrorEstimatedInterventionTime = new javax.swing.JLabel();
        jTypology = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTypologyOfActivity = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.setForeground(new java.awt.Color(255, 153, 0));
        jPanel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("ActivityID: ");

        jActivityId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jActivityIdKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Branch Office:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Area:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Typology:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Activity Description:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Estimated Intervention Time:");
        jLabel7.setToolTipText("");

        jEstimatedInterventionTime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jEstimatedInterventionTimeKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Date (YYYY-MM-DD):");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Materials (separate each material with comma):");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Interruptible Activity:");

        jCreate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCreate.setText("Create Activity");
        jCreate.setEnabled(false);
        jCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCreateActionPerformed(evt);
            }
        });

        jInterruptibleTrue.setBackground(new java.awt.Color(255, 153, 0));
        buttonGroupInterruptible.add(jInterruptibleTrue);
        jInterruptibleTrue.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jInterruptibleTrue.setText("True");
        jInterruptibleTrue.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jInterruptibleTrueItemStateChanged(evt);
            }
        });

        jInterruptibleFalse.setBackground(new java.awt.Color(255, 153, 0));
        buttonGroupInterruptible.add(jInterruptibleFalse);
        jInterruptibleFalse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jInterruptibleFalse.setText("False");
        jInterruptibleFalse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jInterruptibleFalseItemStateChanged(evt);
            }
        });

        jErrorActivityId.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        jErrorEstimatedInterventionTime.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        jTypology.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jTypology.setPreferredSize(new java.awt.Dimension(57, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Typology of activity: ");

        jTypologyOfActivity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Planned", "EWO", "Extra Activity" }));
        jTypologyOfActivity.setPreferredSize(new java.awt.Dimension(57, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jArea, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jActivityId))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jBranchOffice)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jActivityDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTypology, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jErrorActivityId, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jEstimatedInterventionTime, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jErrorEstimatedInterventionTime, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jMaterials, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(27, 27, 27)
                                        .addComponent(jInterruptibleTrue)
                                        .addGap(18, 18, 18)
                                        .addComponent(jInterruptibleFalse))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTypologyOfActivity, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jActivityId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jErrorActivityId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBranchOffice, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jArea, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTypology, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jActivityDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jErrorEstimatedInterventionTime, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jEstimatedInterventionTime, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(33, 33, 33))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(313, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jMaterials, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jInterruptibleTrue)
                    .addComponent(jInterruptibleFalse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTypologyOfActivity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84)
                .addComponent(jCreate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jActivityId.getAccessibleContext().setAccessibleName("");

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

    private void jInterruptibleTrueItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jInterruptibleTrueItemStateChanged
        interruptible = true;
        checkFields();
    }//GEN-LAST:event_jInterruptibleTrueItemStateChanged

    private void jInterruptibleFalseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jInterruptibleFalseItemStateChanged
        interruptible = true;
        checkFields();
    }//GEN-LAST:event_jInterruptibleFalseItemStateChanged

    private void jCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCreateActionPerformed
        try {
            int activityId = Integer.parseInt(jActivityId.getText());
            String branchOffice = jBranchOffice.getText();
            String area = jArea.getText();
            Site site = new Site(branchOffice,area);
            String typology = (String) jTypology.getSelectedItem();
            String activityDescription = jActivityDescription.getText();
            int estimatedInterventionTime = Integer.parseInt(jEstimatedInterventionTime.getText());
            LocalDate date = LocalDate.parse(jDate.getText());
            LinkedList<Material> materials = new LinkedList<>();
            String materialString = jMaterials.getText();
            if (!materialString.equals("")){
                String[] materialStringList = materialString.split(",");
                for (String material : materialStringList)
                    materials.add(new Material(material.trim()));
            }else materials = null;
            boolean interruptibleActivity;
            interruptibleActivity = jInterruptibleTrue.isSelected();
            Typology typologyOfActivity;
            if(jTypologyOfActivity.getSelectedItem().equals("Planned"))
                typologyOfActivity = Typology.PLANNED;
            else if(jTypologyOfActivity.getSelectedItem().equals("EWO"))
                typologyOfActivity = Typology.EWO;
            else
                typologyOfActivity = Typology.EXTRA;
            planner.makeMaintenanceActivity(activityId, site, typology, activityDescription,
                    estimatedInterventionTime, date, null, materials, interruptibleActivity, typologyOfActivity);
            jActivityId.setText("");
            jBranchOffice.setText("");
            jArea.setText("");
            jTypology.setSelectedIndex(0);
            jActivityDescription.setText("");
            jEstimatedInterventionTime.setText("");
            jDate.setText("");
            jMaterials.setText("");
            jTypologyOfActivity.setSelectedIndex(0);
            jCreate.setEnabled(false);
        } catch (MaintenanceActivityException | NotValidParameterException ex) {
            MessageManager.errorMessage(this,ex.getMessage());
        }catch(DateTimeParseException ex){
            MessageManager.errorMessage(this, "Not valid date format");
        }
    }//GEN-LAST:event_jCreateActionPerformed

    private void jActivityIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jActivityIdKeyPressed
        char c = evt.getKeyChar();
        if(Character.isLetter(c)){
            jActivityId.setEditable(false);
            jErrorActivityId.setText("Enter digits only!");
        }else {
            jActivityId.setEditable(true);
            jErrorActivityId.setText("");
        }
    }//GEN-LAST:event_jActivityIdKeyPressed

    private void jEstimatedInterventionTimeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jEstimatedInterventionTimeKeyPressed
        char c = evt.getKeyChar();
        if(Character.isLetter(c)){
            jEstimatedInterventionTime.setEditable(false);
            jErrorEstimatedInterventionTime.setText("Enter digits only!");
        }else {
            jEstimatedInterventionTime.setEditable(true);
            jErrorEstimatedInterventionTime.setText("");
        }
    }//GEN-LAST:event_jEstimatedInterventionTimeKeyPressed

    private class MyDocumentListener implements DocumentListener {

        @Override
        public void changedUpdate(DocumentEvent e) {
            checkTextFields();
            checkFields();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            checkTextFields();
            checkFields();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            checkTextFields();
            checkFields();
        }

        // if any changes to any document (any of the above methods called)
        // check if all JTextfields have text. If so, activate the action
        public boolean checkTextFields() {
            boolean allFieldsHaveText = true;
            for (int i = 0; i<textFields.length-1;i++) {
                if (textFields[i].getText().trim().isEmpty()) {
                    allFieldsHaveText = false;
                }
            }
            return allFieldsHaveText;
        }

    }
    
    private void checkFields(){
        if (interruptible && typeOfActivity && typologyBool && myDocumentListener.checkTextFields()){
            jCreate.setEnabled(true);
        }else jCreate.setEnabled(false);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Planner planner = new Planner("ProvaUsername", "ProvaPassword", new MaintenanceActivityDAOImpl(new SiteDaoImpl()), 
                new RequiredMaterialForMaintenanceDAOImpl(), new UsersDAOImpl(), new EmployeeAppointmentDAOImpl(),
                new RequiredSkillForMaintenanceDAOImpl(), new MaintainerSkillDAOImpl(), new TypologyDAOImpl());
//         Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new CreateActivity(planner).setVisible(true);
                } catch (TypologyException | NotValidParameterException ex) {
                    Logger.getLogger(CreateActivity.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupInterruptible;
    private javax.swing.ButtonGroup buttonGroupTypeOfActivity;
    private javax.swing.JTextField jActivityDescription;
    private javax.swing.JTextField jActivityId;
    private javax.swing.JTextField jArea;
    private javax.swing.JTextField jBranchOffice;
    private javax.swing.JButton jCreate;
    private javax.swing.JTextField jDate;
    private javax.swing.JLabel jErrorActivityId;
    private javax.swing.JLabel jErrorEstimatedInterventionTime;
    private javax.swing.JTextField jEstimatedInterventionTime;
    private javax.swing.JRadioButton jInterruptibleFalse;
    private javax.swing.JRadioButton jInterruptibleTrue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jMaterials;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> jTypology;
    private javax.swing.JComboBox<String> jTypologyOfActivity;
    // End of variables declaration//GEN-END:variables
}
