/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.administrator;

import business.user.SystemAdministrator;
import exception.ProcedureException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import persistence.maintenanceactivity.MaintenanceProcedureDAOImpl;
/**
 *
 * @author rosar
 */
public class CreateProcedureGUI extends javax.swing.JFrame {
    private final static String PROJECT_PATH = System.getProperty("user.dir");
    private final static String RELATIVE_PROJECT_PATH = "/src/smp/";
    private final static String FILE_EXTENSION = ".pdf";
    private File fileChoosen;
    
    private SystemAdministrator admin;
    
    /**
     * Creates new form CreateProcedureGUI
     */
    public CreateProcedureGUI() {
        fileChoosen = null;
        admin = new SystemAdministrator("admin","admin", new MaintenanceProcedureDAOImpl());
        initComponents();
        procedureTextField.setEnabled(false);
        RenameLabel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ProcedurePanel = new javax.swing.JPanel();
        procedureTextField = new javax.swing.JTextField();
        procedureLabel = new javax.swing.JLabel();
        fileChooserButton = new javax.swing.JButton();
        chooserButton = new javax.swing.JButton();
        RenameLabel = new javax.swing.JLabel();
        chooseFileLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ProcedurePanel.setBackground(new java.awt.Color(187, 187, 100));
        ProcedurePanel.setForeground(new java.awt.Color(187, 187, 100));

        procedureLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        procedureLabel.setText("Create Procedure GUI");

        fileChooserButton.setText("Create");
        fileChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserButtonActionPerformed(evt);
            }
        });

        chooserButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/image/yellowfolder.png"))); // NOI18N
        chooserButton.setBorder(null);
        chooserButton.setContentAreaFilled(false);
        chooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooserButtonActionPerformed(evt);
            }
        });

        RenameLabel.setText("Rinomina File:");

        chooseFileLabel.setText("Scegli il file");

        javax.swing.GroupLayout ProcedurePanelLayout = new javax.swing.GroupLayout(ProcedurePanel);
        ProcedurePanel.setLayout(ProcedurePanelLayout);
        ProcedurePanelLayout.setHorizontalGroup(
            ProcedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProcedurePanelLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(ProcedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(procedureTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RenameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ProcedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chooserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chooseFileLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProcedurePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ProcedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProcedurePanelLayout.createSequentialGroup()
                        .addComponent(procedureLabel)
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProcedurePanelLayout.createSequentialGroup()
                        .addComponent(fileChooserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        ProcedurePanelLayout.setVerticalGroup(
            ProcedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProcedurePanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(procedureLabel)
                .addGap(52, 52, 52)
                .addGroup(ProcedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RenameLabel)
                    .addComponent(chooseFileLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ProcedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(procedureTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooserButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(fileChooserButton)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ProcedurePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ProcedurePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserButtonActionPerformed
        StringBuilder builder = new StringBuilder();
        try {
            String newName = procedureTextField.getText().equals("") ?  fileChoosen.getName() : procedureTextField.getText();
            builder.append(PROJECT_PATH).append(RELATIVE_PROJECT_PATH).append(newName);
            if (!procedureTextField.getText().equals("")){
                builder.append(FILE_EXTENSION);
            }
            boolean choosen = fileChoosen.renameTo(new File(builder.toString()));
            if (choosen == true){
                String fileName = fileChoosen.getName();
                admin.saveSmpProcedure(fileName);
                infoMessage("Procedura aggiunta con successo");
                procedureTextField.setEnabled(false);
                RenameLabel.setVisible(false);
            }else{
                errorMessage("Nome file già esistente");
            }
        } catch (ProcedureException ex) {
            errorMessage(ex.getMessage());
        }
        
     
    }//GEN-LAST:event_fileChooserButtonActionPerformed

    private void chooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooserButtonActionPerformed
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);
        JFileChooser filechooser = new JFileChooser(PROJECT_PATH.concat(RELATIVE_PROJECT_PATH));
        filechooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF file","pdf");
        filechooser.setFileFilter(filter);
        filechooser.setDialogTitle("Scegli un pdf come smp");
        int result = filechooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            fileChoosen = filechooser.getSelectedFile();
            procedureTextField.setEnabled(true);
            RenameLabel.setVisible(true);
            procedureTextField.requestFocus();
        }
    }//GEN-LAST:event_chooserButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CreateProcedureGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateProcedureGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateProcedureGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateProcedureGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateProcedureGUI().setVisible(true);
            }
        });
    }
    
    private void errorMessage(String message){
        JOptionPane.showMessageDialog(this, message, "ERRORE", JOptionPane.ERROR_MESSAGE);
    }
    
    private void infoMessage(String message){
        JOptionPane.showMessageDialog(this, message, "INFO", JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ProcedurePanel;
    private javax.swing.JLabel RenameLabel;
    private javax.swing.JLabel chooseFileLabel;
    private javax.swing.JButton chooserButton;
    private javax.swing.JButton fileChooserButton;
    private javax.swing.JLabel procedureLabel;
    private javax.swing.JTextField procedureTextField;
    // End of variables declaration//GEN-END:variables
}
