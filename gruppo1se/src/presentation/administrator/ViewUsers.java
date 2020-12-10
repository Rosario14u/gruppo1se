/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.administrator;

import business.maintenanceactivity.MaintenanceActivity;
import business.user.Maintainer;
import business.user.Planner;
import business.user.SystemAdministrator;
import business.user.User;
import exception.UsersException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import persistence.maintenanceactivity.MaintenanceProcedureDAOImpl;
import persistence.user.UsersDAOImpl;

/**
 *
 * @author VincenzaCoppola <v.coppola38@studenti.unisa.it>
 */
public class ViewUsers extends javax.swing.JFrame {
    private final SystemAdministrator systemAdministrator;
    private List<User> list = new ArrayList<>();
    private final DefaultTableModel tableModel;
    private final TableRowSorter<TableModel> rowSorter,userSorter;
    
    /**
     * Creates new form ViewUser
     * @param systemAdministrator
     * @throws exception.UsersException
     */
    public ViewUsers(SystemAdministrator systemAdministrator) throws UsersException {
        initComponents();
        tableModel = (DefaultTableModel) jTable.getModel();
        rowSorter = new TableRowSorter<>(tableModel);
        userSorter = new TableRowSorter<>(tableModel);
        this.systemAdministrator = systemAdministrator;
        jTable.getTableHeader().setFont(new java.awt.Font("Tahoma", 1, 12));
        jFilter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFilter();
            }
        });
        jUsername.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterUser();
                jTable.repaint();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                filterUser();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        jRole.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectRole();
            }
        });
        addRowsToTable();
    }
    
    public void addRowsToTable() throws UsersException{
        list = systemAdministrator.viewUsers();
        Object[] rows = new Object[3];
        for(int i = 0; i < list.size(); i++) {
            rows[0] = list.get(i).getUsername();
            rows[1] = list.get(i).getPassword();
            if(Planner.class.isInstance(list.get(i)))
                rows[2] = "Planner";
            else if (Maintainer.class.isInstance(list.get(i)))
                rows[2] = "Maintainer";
            else 
                rows[2] = "System Administrator";
            tableModel.addRow(rows);
        }
    }
    
    public void selectFilter(){
        if (jFilter.getSelectedItem().equals("Username")){
            jUsername.setEditable(true);
            jRole.setSelectedIndex(0);
            jRole.setEnabled(false); 
        }
        else if(jFilter.getSelectedItem().equals("Role")){
            jUsername.setText("");
            jUsername.setEditable(false);
            jRole.setEnabled(true);
        }
        else{
            jUsername.setText("");
            jRole.setSelectedIndex(0);
            jUsername.setEditable(false);
            jRole.setEnabled(false);
        }
    }
    
    public void selectRole(){
        String filter = jRole.getSelectedItem().toString();
        filterRole(filter);
    }
    
    private void filterRole(String filter){
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(tableModel);
        jTable.setRowSorter(rowSorter);
        if(!filter.equals(" "))
            rowSorter.setRowFilter(RowFilter.regexFilter(filter,2));
        else
            jTable.setRowSorter(rowSorter);      
    }
    
    private void filterUser(){
        jTable.setRowSorter(userSorter);
        String text = jUsername.getText();
        if(text.trim().length() == 0)
            userSorter.setRowFilter(null);
        else 
            userSorter.setRowFilter(RowFilter.regexFilter("^(?i)"+text,0));
    }
    

        
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        roleLabel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JTextField();
        usernameTextField = new javax.swing.JTextField();
        roleComboBox = new javax.swing.JComboBox<>();
        cancelButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jFilter = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jRole = new javax.swing.JComboBox<>();
        deleteButton = new javax.swing.JButton();
        modifyButton = new javax.swing.JButton();

        jDialog1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog1.setMinimumSize(new java.awt.Dimension(400, 270));
        jDialog1.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        jPanel2.setBackground(new java.awt.Color(187, 187, 100));
        jPanel2.setForeground(new java.awt.Color(187, 187, 100));
        jPanel2.setMinimumSize(new java.awt.Dimension(400, 400));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 400));

        usernameLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(0, 0, 0));
        usernameLabel.setText("Username:");

        passwordLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(0, 0, 0));
        passwordLabel.setText("Password:");

        roleLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        roleLabel.setForeground(new java.awt.Color(0, 0, 0));
        roleLabel.setText("Role:");

        roleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Planner", "Maintainer", "System Administrator"}));

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        confirmButton.setText("Confirm");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(passwordLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(usernameLabel))
                    .addComponent(roleLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(roleComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(106, 106, 106))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 95, Short.MAX_VALUE)
                                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(passwordTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameTextField, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(35, 35, 35))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameTextField))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordTextField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roleComboBox)
                    .addComponent(roleLabel))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(confirmButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Filter:");

        jTable.setAutoCreateRowSorter(true);
        jTable.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Password", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable);

        jFilter.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Username", "Role" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Insert an Username:");

        jUsername.setEditable(false);
        jUsername.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Select a Role:");

        jRole.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Maintainer", "Planner", "System Administrator" }));
        jRole.setEnabled(false);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jFilter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRole, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(modifyButton))
                .addGap(17, 17, 17))
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

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try {
            int[] selectedIndex = jTable.getSelectedRows();
            User user = null;
            List<String> usernameList = new ArrayList<>();
            for(int i = 0; i < selectedIndex.length; i++)    
                usernameList.add(String.valueOf(tableModel.getValueAt(i, 0)));
            if(!usernameList.isEmpty()){
                int dialogResult = confirmMessage("Are you sure you wish to delete these users");
                if(dialogResult == JOptionPane.YES_OPTION){
                    systemAdministrator.removeUsers(usernameList);
                    for(int i = 0; i < selectedIndex.length; i++)    
                        tableModel.removeRow(i);
                }
                jTable.clearSelection();
            }else{
                infoMessage("There aren't user selected");
            }
        } catch (UsersException ex) {
            errorMessage(ex.getMessage());
        }
    }//GEN-LAST:event_deleteButtonActionPerformed
   
    
    private void errorMessage(String message){
        JOptionPane.showMessageDialog(this, message, "ERRORE", JOptionPane.ERROR_MESSAGE);
    }
    
    private void infoMessage(String message){
        JOptionPane.showMessageDialog(this, message, "INFO", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private int confirmMessage(String message){
        return JOptionPane.showConfirmDialog(this, message, "Confirm", JOptionPane.YES_NO_OPTION);
    }
    //================================================================================================================================
    
    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed
        int[] indexOfSelectedRow = jTable.getSelectedRows();
        if(indexOfSelectedRow.length==1){
            usernameTextField.setText(String.valueOf(tableModel.getValueAt(indexOfSelectedRow[0], 0))); 
            passwordTextField.setText(String.valueOf(tableModel.getValueAt(indexOfSelectedRow[0], 1))); 
            roleComboBox.setSelectedItem(String.valueOf(tableModel.getValueAt(indexOfSelectedRow[0], 2)));
            jDialog1.setVisible(true);
        }else{
            infoMessage(indexOfSelectedRow.length < 1 ? "Select one row" : "Too row selected");
        }
    }//GEN-LAST:event_modifyButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        int result = confirmRequest("Ara you sure? All modified will be lost", "CANCEL");
        if (result==EXIT_ON_CLOSE)
            jDialog1.dispose();       
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        int result = confirmRequest("Are you sure you want to modify this user?", "CONFIRM");
        if (result==EXIT_ON_CLOSE){
            try {
                int[] indexOfSelectedRow = jTable.getSelectedRows();
                String oldUsername = String.valueOf(tableModel.getValueAt(indexOfSelectedRow[0], 0));
                String oldPassword = String.valueOf(tableModel.getValueAt(indexOfSelectedRow[0], 1));
                String oldRole = String.valueOf(tableModel.getValueAt(indexOfSelectedRow[0], 2));
                
                String newUsername = usernameTextField.getText();
                String newPassword = passwordTextField.getText();
                String newRole = String.valueOf(roleComboBox.getSelectedItem());
                
                if(checkIfModified(oldUsername, oldPassword, oldRole, newUsername, newPassword, newRole)){
                    System.out.println(systemAdministrator.modifyUser(oldUsername, makeUser(newUsername, newPassword, newRole)));
                    tableModel.setValueAt(newUsername, indexOfSelectedRow[0], 0);
                    tableModel.setValueAt(newPassword, indexOfSelectedRow[0], 1);
                    System.out.println(newRole);
                    tableModel.setValueAt(newRole, indexOfSelectedRow[0], 2);
                }       
            } catch (UsersException ex) {
                JOptionPane.showMessageDialog(this, "Error while trying to create this user", "Error", JOptionPane.ERROR_MESSAGE);
            }
            jDialog1.dispose();
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

    private User makeUser(String newUsername, String newPassword, String newRole){
        if (newRole.equals("Planner")){
            return new Planner(newUsername, newPassword);
        }
        else if (newRole.equals("Maintainer")){
            return new Maintainer(newUsername, newPassword);
        }
        else{
            return new SystemAdministrator(newUsername, newPassword);
        }
    }
    
    private boolean checkIfModified(String oldUsername, String oldPassword, String oldRole, String newUsername, String newPassword, String newRole){
        return !(oldUsername.equals(newUsername) &&
               oldPassword.equals(newPassword) &&
               oldRole.equals(newRole));
    }
    
    private int confirmRequest(String msg, String title) {
        switch (JOptionPane.showConfirmDialog(jDialog1, msg, title, JOptionPane.YES_NO_OPTION)) {           
            case JOptionPane.YES_OPTION:
                return EXIT_ON_CLOSE;
            case JOptionPane.NO_OPTION:
                return DO_NOTHING_ON_CLOSE;
            case JOptionPane.CLOSED_OPTION:
                return DO_NOTHING_ON_CLOSE;
            default:
                return DO_NOTHING_ON_CLOSE;
        }
    }
   
    //================================================================================================================================
    
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
            java.util.logging.Logger.getLogger(ViewUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ViewUsers(new SystemAdministrator("Username","Password",new MaintenanceProcedureDAOImpl(), new UsersDAOImpl())).setVisible(true);
                } catch (UsersException ex) {
                    Logger.getLogger(ViewUsers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JComboBox<String> jFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> jRole;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jUsername;
    private javax.swing.JButton modifyButton;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JComboBox<String> roleComboBox;
    private javax.swing.JLabel roleLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
