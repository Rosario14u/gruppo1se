/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.administrator;

import business.user.SystemAdministrator;
import exception.NotValidParameterException;
import exception.ProcedureException;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import presentation.manager.MessageManager;


/**
 * GUI to create the mainteance activity procedure with an smp file
 * @author rosar
 */
/*Class developed by Rosario Gaeta*/
public class CreateProcedureGUI extends javax.swing.JFrame {
    private final static String PROJECT_PATH = System.getProperty("user.dir");
    private final static String RELATIVE_PROJECT_PATH = "/src/smp/";
    private final static String FILE_EXTENSION = ".pdf";
    private File fileChoosen;
    private final SystemAdministrator administrator;
    
    
    /**
     * Creates new form CreateProcedureGUI
     */
    public CreateProcedureGUI(SystemAdministrator administrator) {
        this.administrator = administrator;
        fileChoosen = null;
        initComponents();
        this.setLocationRelativeTo(null);
        setField(false);
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
        RenameLabel = new javax.swing.JLabel();
        chooseFileLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        chooserButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Procedure");

        ProcedurePanel.setBackground(new java.awt.Color(255, 153, 0));
        ProcedurePanel.setForeground(new java.awt.Color(255, 153, 0));

        procedureLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        procedureLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        procedureLabel.setText("Create Procedure GUI");

        fileChooserButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fileChooserButton.setText("Create");
        fileChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserButtonActionPerformed(evt);
            }
        });

        RenameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RenameLabel.setText("Rename file:");

        chooseFileLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chooseFileLabel.setText("Choose file");

        chooserButton.setBackground(new java.awt.Color(255, 255, 255));
        chooserButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/image/yellowfolder.png"))); // NOI18N
        chooserButton.setBorder(null);
        chooserButton.setContentAreaFilled(false);
        chooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooserButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chooserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(chooserButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ProcedurePanelLayout = new javax.swing.GroupLayout(ProcedurePanel);
        ProcedurePanel.setLayout(ProcedurePanelLayout);
        ProcedurePanelLayout.setHorizontalGroup(
            ProcedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProcedurePanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(ProcedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ProcedurePanelLayout.createSequentialGroup()
                        .addGroup(ProcedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(procedureTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RenameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(ProcedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fileChooserButton)
                        .addComponent(chooseFileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProcedurePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(procedureLabel)
                .addGap(64, 64, 64))
        );
        ProcedurePanelLayout.setVerticalGroup(
            ProcedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProcedurePanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(procedureLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(ProcedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RenameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseFileLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ProcedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(procedureTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
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
/**
 * This method allows to save the smp in the system when the button is pressed
 * @param evt 
 */
    private void fileChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserButtonActionPerformed
        StringBuilder builder = new StringBuilder();
        String newName = null;
        String oldName = null;
        String inputFileName = procedureTextField.getText().trim().replaceAll("  +", " ");
        if (inputFileName.lastIndexOf('.')!=-1)
            /*getting the name of input file without extention*/
            inputFileName = inputFileName.substring(0, inputFileName.lastIndexOf('.')); 
        String oldFileName = fileChoosen.getName();
        if (oldFileName.lastIndexOf('.')!=-1)
            /*getting the old name of file without extention*/
            oldFileName = oldFileName.substring(0, oldFileName.lastIndexOf('.'));       
        try {
            if (inputFileName.equals("") || inputFileName.equals(" ") || inputFileName.equals(oldFileName)){
                newName = oldFileName;
            }else{
                newName = inputFileName;
                oldName = oldFileName;
            }
            builder.append(PROJECT_PATH).append(RELATIVE_PROJECT_PATH).append(newName).append(FILE_EXTENSION);
            /*renaming of file*/
            boolean choosen = fileChoosen.renameTo(new File(builder.toString()));       
            if (choosen == true){
                /*if the file is successfully renamed, the smp file is stored in the system*/
                administrator.saveSmpProcedure(newName,oldName);  
                MessageManager.infoMessage(this,"Successful addition of Procedure");
                setField(false); 
                procedureTextField.setText("");
            }else{
                MessageManager.errorMessage(this,"This file already exists");
            }
        } catch (ProcedureException | NotValidParameterException ex) {
            MessageManager.errorMessage(this, ex.getMessage());
        }
    }//GEN-LAST:event_fileChooserButtonActionPerformed
   
    
    /**
     * This method allows to use a file chooser to select a smp file
     * @param evt 
     */
    private void chooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooserButtonActionPerformed
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);
        JFileChooser filechooser = new JFileChooser(PROJECT_PATH.concat(RELATIVE_PROJECT_PATH));
        filechooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF file","pdf");
        filechooser.setFileFilter(filter);
        filechooser.setDialogTitle("Choose a PDF file as Smp"); 
        int result = filechooser.showOpenDialog(this); // get the file choosen with file chooser
        if (result == JFileChooser.APPROVE_OPTION){
            fileChoosen = filechooser.getSelectedFile();
            setField(true);
            procedureTextField.setText(fileChoosen.getName().substring(0, fileChoosen.getName().lastIndexOf('.'))); 
            procedureTextField.requestFocus();
        }
    }//GEN-LAST:event_chooserButtonActionPerformed

   
    /**
     * This method set procedure and rename label of the GUI
     * @param enable 
     */
    private void setField(boolean enable){
        procedureTextField.setEnabled(enable);
        RenameLabel.setVisible(enable);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ProcedurePanel;
    private javax.swing.JLabel RenameLabel;
    private javax.swing.JLabel chooseFileLabel;
    private javax.swing.JButton chooserButton;
    private javax.swing.JButton fileChooserButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel procedureLabel;
    private javax.swing.JTextField procedureTextField;
    // End of variables declaration//GEN-END:variables
}
