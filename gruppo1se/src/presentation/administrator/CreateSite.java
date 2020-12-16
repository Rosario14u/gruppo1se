/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.administrator;

import business.maintenanceactivity.Site;
import business.user.SystemAdministrator;
import exception.NotValidParameterException;
import exception.SiteException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import persistence.maintenanceactivity.MaintenanceProcedureDAOImpl;
import persistence.maintenanceactivity.SiteDaoImpl;
import persistence.maintenanceactivity.TypologyDAOImpl;
import persistence.user.UsersDAOImpl;
import presentation.manager.MessageManager;

/**
 *
 * @author aless
 */
public class CreateSite extends javax.swing.JFrame {
    private final SystemAdministrator systemAdministrator;
    private final JTextField[] textFields = new JTextField[3];
    private String branchOffice;
    private String area;
    private String workspaceNotes;
    private MyDocumentListener myDocumentListener;
    
    /**
     * Creates new form CreateSite
     * @param systemAdministrator
     */
    public CreateSite(SystemAdministrator systemAdministrator) {
        this.systemAdministrator = systemAdministrator;
        initComponents();
        myDocumentListener = new MyDocumentListener();
        textFields[0] = jBranchOffice;
        textFields[1] = jArea;
        textFields[2] = jWorkspaceNotes;
        for(int i = 0; i<textFields.length; i++){
            textFields[i].getDocument().addDocumentListener(myDocumentListener);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jBranchOffice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jArea = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jWorkspaceNotes = new javax.swing.JTextField();
        jCreate = new javax.swing.JButton();
        jClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Site");

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("INSERT A SITE");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Branch Office:");

        jBranchOffice.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Area:");

        jArea.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Workspace Notes:");

        jWorkspaceNotes.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N

        jCreate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCreate.setText("Create");
        jCreate.setEnabled(false);
        jCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCreateActionPerformed(evt);
            }
        });

        jClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jClear.setText("Clear");
        jClear.setEnabled(false);
        jClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jWorkspaceNotes))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jArea))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBranchOffice, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jClear, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jCreate)))))
                .addGap(68, 68, 68))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBranchOffice, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jArea, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jWorkspaceNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCreate, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
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

    private void jClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClearActionPerformed
        jBranchOffice.setText("");
        jArea.setText("");
        jWorkspaceNotes.setText("");
        jClear.setEnabled(false);
    }//GEN-LAST:event_jClearActionPerformed

    private void jCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCreateActionPerformed
        try{
        branchOffice = jBranchOffice.getText();
        area = jArea.getText();
        workspaceNotes = jWorkspaceNotes.getText();
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to create this site?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION){
                systemAdministrator.makeSite(new Site(branchOffice, area, workspaceNotes)); //create a site
                jBranchOffice.setText("");
                jArea.setText("");
                jWorkspaceNotes.setText("");
                jCreate.setEnabled(false);
            }
        } catch (SiteException | NotValidParameterException e){
            MessageManager.errorMessage(this,"Cannot create this site");
        }
    }//GEN-LAST:event_jCreateActionPerformed

    
    private class MyDocumentListener implements DocumentListener {

        @Override
        public void changedUpdate(DocumentEvent e) {
            checkAnyField();
            checkTextFields();
            checkFields();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            checkAnyField();
            checkTextFields();
            checkFields();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            checkAnyField();
            checkTextFields();
            checkFields();
        }
        
        /**
         * If any change happens to any document checks if at least one JTextfield has text <br>
         * If so, set atLeastOneField variable to true.
         * @return {@code boolean} atLeastOneField
         */
        public boolean checkAnyField(){
            boolean atLeastOneField = false;
            for (int i = 0; i<textFields.length; i++)
                if (!textFields[i].getText().trim().isEmpty())
                    atLeastOneField = true;
            return atLeastOneField;
        }
        
        /**
         * If any change happens to any document checks if all JTextfields have text <br>
         * If so, set allFieldsHaveText variable to true.
         * @return {@code boolean} allFieldsHaveText
         */
        public boolean checkTextFields() {
            boolean allFieldsHaveText = true;
            for (int i = 0; i<textFields.length;i++) {
                if (textFields[i].getText().trim().isEmpty()) {
                    allFieldsHaveText = false;
                }
            }
            return allFieldsHaveText;
        }
    }
    
    /**
     * Enables the jCreate or jClear button, if all fields have been used, disables it otherwise.
     */
    private void checkFields(){
        if (myDocumentListener.checkTextFields())
            jCreate.setEnabled(true);
        else 
            jCreate.setEnabled(false);
        if(myDocumentListener.checkAnyField())
            jClear.setEnabled(true);
        else
            jClear.setEnabled(false);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jArea;
    private javax.swing.JTextField jBranchOffice;
    private javax.swing.JButton jClear;
    private javax.swing.JButton jCreate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jWorkspaceNotes;
    // End of variables declaration//GEN-END:variables
}
