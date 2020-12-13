/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.administrator;

import business.user.SystemAdministrator;
import business.user.UserRole;
import exception.NotValidParameterException;
import exception.UsersException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import persistence.maintenanceactivity.MaintenanceProcedureDAOImpl;
import persistence.maintenanceactivity.TypologyDAOImpl;
import persistence.user.UsersDAOImpl;
import presentation.manager.MessageManager;

/**
 *
 * @author aless
 */
public class CreateUser extends javax.swing.JFrame {
    private final SystemAdministrator systemAdministrator;
    private final JTextField[] textFields = new JTextField[2];
    private String username;
    private String password;
    private UserRole role;
    private MyDocumentListener myDocumentListener;
    private boolean roleCheck;

    /**
     * Creates new form CreateUser
     * @param systemAdministrator
     */
    public CreateUser(SystemAdministrator systemAdministrator) {
        this.systemAdministrator = systemAdministrator;
        initComponents();
        myDocumentListener = new MyDocumentListener();
        textFields[0] = jUsername;
        textFields[1] = jPassword;
        for(int i = 0; i<textFields.length; i++){
            textFields[i].getDocument().addDocumentListener(myDocumentListener);
        }
        jRole.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                checkComboBox();
            }
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

        buttonGroupRole = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jCreate = new javax.swing.JButton();
        jPassword = new javax.swing.JTextField();
        jRole = new javax.swing.JComboBox<>();
        jClear = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Username:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Password:");

        jUsername.setFont(new java.awt.Font("Rockwell", 0, 12));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Role:");

        jCreate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCreate.setText("Create User");
        jCreate.setEnabled(false);
        jCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCreateActionPerformed(evt);
            }
        });

        jPassword.setFont(new java.awt.Font("Rockwell", 0, 12));

        jRole.setFont(new java.awt.Font("Rockwell", 0, 12));
        jRole.setModel(new javax.swing.DefaultComboBoxModel<>(UserRole.values()));

        jClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jClear.setText("Clear");
        jClear.setEnabled(false);
        jClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClearActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("ADD AN USER");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jRole, 0, 186, Short.MAX_VALUE)
                            .addComponent(jUsername)
                            .addComponent(jPassword))
                        .addComponent(jCreate)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRole, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jClear, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
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

    private void jCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCreateActionPerformed
        try {
            username = jUsername.getText();
            password = jPassword.getText();
            role = UserRole.valueOf(jRole.getSelectedItem().toString());
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to create this user?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION){
                systemAdministrator.makeUser(username, password, role);
                jUsername.setText("");
                jPassword.setText("");
                jRole.setSelectedIndex(0);
                jCreate.setEnabled(false);
            }
        } catch (UsersException | NotValidParameterException ex) {
            MessageManager.errorMessage(this,ex.getMessage());
        }
    }//GEN-LAST:event_jCreateActionPerformed

    private void jClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClearActionPerformed
        jUsername.setText("");
        jPassword.setText("");
        jRole.setSelectedIndex(0);
        jClear.setEnabled(false);
    }//GEN-LAST:event_jClearActionPerformed

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
        
        public boolean checkAnyField(){
            boolean atLeastOneField = false;
            for (int i = 0; i<textFields.length; i++)
                if (!textFields[i].getText().trim().isEmpty())
                    atLeastOneField = true;
            return atLeastOneField;
        }
        
        // if any changes to any document (any of the above methods called)
        // check if all JTextfields have text. If so, activate the action
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
    
    private void checkComboBox(){
        if(jRole.getSelectedIndex() == 0)
            roleCheck = false;
        else
            roleCheck = true;
        checkFields();
    }
    
    private void checkFields(){
        if (roleCheck && myDocumentListener.checkTextFields())
            jCreate.setEnabled(true);
        else jCreate.setEnabled(false);
        
        if(roleCheck || myDocumentListener.checkAnyField())
            jClear.setEnabled(true);
        else
            jClear.setEnabled(false);
    }
    
    
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CreateUser(new SystemAdministrator("user","pass", new MaintenanceProcedureDAOImpl(), new UsersDAOImpl(), new TypologyDAOImpl())).setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupRole;
    private javax.swing.JButton jClear;
    private javax.swing.JButton jCreate;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jPassword;
    private javax.swing.JComboBox<UserRole> jRole;
    private javax.swing.JTextField jUsername;
    // End of variables declaration//GEN-END:variables
}
