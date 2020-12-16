/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.administrator;

import business.user.SystemAdministrator;
import persistence.maintenanceactivity.MaintenanceProcedureDAOImpl;
import persistence.maintenanceactivity.SiteDaoImpl;
import persistence.maintenanceactivity.TypologyDAOImpl;
import persistence.user.UsersDAOImpl;

/**
 *
 * @author gorra
 */
public class MenuSystemAdministrator extends javax.swing.JFrame {
    private final SystemAdministrator administrator;

    /**
     * Creates new form MenuSystemAdministrator
     */
    public MenuSystemAdministrator() {
        administrator = new SystemAdministrator("admin","admin", new MaintenanceProcedureDAOImpl(),
                new UsersDAOImpl(),new TypologyDAOImpl(), new SiteDaoImpl());
        initComponents();
        this.setLocationRelativeTo(null);
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
        createUserButton = new javax.swing.JButton();
        createProcedureButton = new javax.swing.JButton();
        viewUsersButton = new javax.swing.JButton();
        createTypologyButton = new javax.swing.JButton();
        viewTypologyButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu System Administrator");

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.setForeground(new java.awt.Color(187, 187, 100));

        createUserButton.setBackground(new java.awt.Color(50, 50, 50));
        createUserButton.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        createUserButton.setForeground(new java.awt.Color(255, 255, 255));
        createUserButton.setText("Create User");
        createUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createUserButtonActionPerformed(evt);
            }
        });

        createProcedureButton.setBackground(new java.awt.Color(50, 50, 50));
        createProcedureButton.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        createProcedureButton.setForeground(new java.awt.Color(255, 255, 255));
        createProcedureButton.setText("Create Procedure");
        createProcedureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createProcedureButtonActionPerformed(evt);
            }
        });

        viewUsersButton.setBackground(new java.awt.Color(50, 50, 50));
        viewUsersButton.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        viewUsersButton.setForeground(new java.awt.Color(255, 255, 255));
        viewUsersButton.setText("View Users");
        viewUsersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewUsersButtonActionPerformed(evt);
            }
        });

        createTypologyButton.setBackground(new java.awt.Color(50, 50, 50));
        createTypologyButton.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        createTypologyButton.setForeground(new java.awt.Color(255, 255, 255));
        createTypologyButton.setText("Create Typology");
        createTypologyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTypologyButtonActionPerformed(evt);
            }
        });

        viewTypologyButton.setBackground(new java.awt.Color(50, 50, 50));
        viewTypologyButton.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        viewTypologyButton.setForeground(new java.awt.Color(255, 255, 255));
        viewTypologyButton.setText("View Typology");
        viewTypologyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewTypologyButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("MENU SYSTEM ADMINISTRATOR");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/administrator/image/pdfIcon.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/administrator/image/userIcon.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/administrator/image/maintenanceIcon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createUserButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewUsersButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(238, 238, 238)
                                        .addComponent(jLabel2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addComponent(jLabel3)))
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createProcedureButton)
                                .addGap(67, 67, 67)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(createTypologyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewTypologyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(166, 166, 166))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createUserButton)
                    .addComponent(createProcedureButton)
                    .addComponent(createTypologyButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewUsersButton)
                    .addComponent(viewTypologyButton))
                .addGap(127, 127, 127))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * When the dedicated button is pressed it opens ManageTypology GUI
     * @param evt 
     */
    private void viewTypologyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewTypologyButtonActionPerformed
        new ManageTypologies(administrator).setVisible(true);
    }//GEN-LAST:event_viewTypologyButtonActionPerformed

    
    /**
     * When the dedicated button is pressed it opens CreateProcedure GUI
     * @param evt 
     */
    private void createProcedureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createProcedureButtonActionPerformed
        new CreateProcedureGUI(administrator).setVisible(true);
    }//GEN-LAST:event_createProcedureButtonActionPerformed

    
    /**
     * When the dedicated button is pressed it opens CreateUser GUI
     * @param evt 
     */
    private void createUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUserButtonActionPerformed
        new CreateUser(administrator).setVisible(true);
    }//GEN-LAST:event_createUserButtonActionPerformed

    
    /**
     * When the dedicated button is pressed it opens CreateTypology GUI
     * @param evt 
     */
    private void createTypologyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTypologyButtonActionPerformed
        new CreateTypology(administrator).setVisible(true);
    }//GEN-LAST:event_createTypologyButtonActionPerformed

    
    /**
     * When the dedicated button is pressed it opens ViewUsers GUI
     * @param evt 
     */
    private void viewUsersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewUsersButtonActionPerformed
        new ViewUsers(administrator).setVisible(true);
    }//GEN-LAST:event_viewUsersButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MenuSystemAdministrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuSystemAdministrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuSystemAdministrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuSystemAdministrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuSystemAdministrator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createProcedureButton;
    private javax.swing.JButton createTypologyButton;
    private javax.swing.JButton createUserButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton viewTypologyButton;
    private javax.swing.JButton viewUsersButton;
    // End of variables declaration//GEN-END:variables
}
