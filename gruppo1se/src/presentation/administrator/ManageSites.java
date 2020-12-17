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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import presentation.manager.MessageManager;

/**
 *
 * @author VincenzaCoppola <v.coppola38@studenti.unisa.it>
 */
public class ManageSites extends javax.swing.JFrame {
    private final SystemAdministrator systemAdministrator;
    private List<Site> list = new ArrayList<>();
    private final DefaultTableModel tableModel;
    private final TableRowSorter<TableModel> rowSorter;
    
    /**
     * Creates new form ManageSites
     * @param systemAdministrator
     */
    public ManageSites(SystemAdministrator systemAdministrator) {
        initComponents();
        tableModel = (DefaultTableModel) jTable.getModel();
        rowSorter = new TableRowSorter<>(tableModel);
        this.systemAdministrator = systemAdministrator;
        jTable.getTableHeader().setFont(new java.awt.Font("Tahoma", 1, 12));
        jFilter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFilter();
            }
        });
        jBranchOffice.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterBranchOffice();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                filterBranchOffice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterBranchOffice();
            }
        });
        jArea.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterArea();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                filterArea();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterArea();
            }
        });
        jTable.setRowSorter(rowSorter);
        addRowsToTable();
    }

    /**
     * Fills the jTable with the rows of the SQL table Site.
     */    
    private void addRowsToTable() {
        try {
            list = systemAdministrator.readSites();
            Object[] rows = new Object[3];
            for(int i = 0; i < list.size(); i++) {
                rows[0] = list.get(i).getBranchOffice();
                rows[1] = list.get(i).getArea();
                rows[2] = list.get(i).getWorkSpaceNotes();
                tableModel.addRow(rows);
            }
        } catch (SiteException | NotValidParameterException ex) {
            MessageManager.errorMessage(this, ex.getMessage());
        }
    }
    
    /**
     * Enables jBranchOffice/jArea if the filter Branch Office/Area is chosen. 
     */    
    private void selectFilter(){
        if (jFilter.getSelectedItem().equals("Branch Office")){
            jBranchOffice.setEditable(true);
            jArea.setEditable(false);
            jArea.setText("");
        }
        else if(jFilter.getSelectedItem().equals("Area")){
            jBranchOffice.setText("");
            jBranchOffice.setEditable(false);
            jArea.setEditable(true);
        }
        else{
            jBranchOffice.setText("");
            jArea.setText("");
            jBranchOffice.setEditable(false);
            jArea.setEditable(false);
        }
    }
    
    /**
     * Filter the content of the jTable based on the content of jBranchOffice.
     */    
    private void filterBranchOffice(){
        String text = jBranchOffice.getText();
        if(text.trim().length() == 0)
            rowSorter.setRowFilter(null);
        else 
            rowSorter.setRowFilter(RowFilter.regexFilter("^(?i)"+text,0));
    }
    
    /**
     * Filter the content of the jTable based on the content of jArea.
     */    
    private void filterArea(){
        String text = jArea.getText();
        if(text.trim().length() == 0)
            rowSorter.setRowFilter(null);
        else 
            rowSorter.setRowFilter(RowFilter.regexFilter("^(?i)"+text,1));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jNewBranchOffice = new javax.swing.JTextField();
        jNewArea = new javax.swing.JTextField();
        jNewWorkspaceNotes = new javax.swing.JTextField();
        jConfirm = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jFilter = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jBranchOffice = new javax.swing.JTextField();
        jArea = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jModify = new javax.swing.JButton();
        jDelete = new javax.swing.JButton();

        jDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog.setMinimumSize(new java.awt.Dimension(410, 261));
        jDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        jPanel2.setBackground(new java.awt.Color(255, 153, 0));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Branch Office:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("UPDATE SITE");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Area:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Workspace Notes:");

        jNewBranchOffice.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N

        jNewArea.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N

        jNewWorkspaceNotes.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N

        jConfirm.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jConfirm.setText("Confirm");
        jConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jNewBranchOffice, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jNewArea))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jNewWorkspaceNotes))
                            .addComponent(jConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNewBranchOffice, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNewArea, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNewWorkspaceNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogLayout = new javax.swing.GroupLayout(jDialog.getContentPane());
        jDialog.getContentPane().setLayout(jDialogLayout);
        jDialogLayout.setHorizontalGroup(
            jDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogLayout.setVerticalGroup(
            jDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manage Sites");

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Branch Office", "Area", "Workspace Notes"
            }
        ));
        jScrollPane1.setViewportView(jTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Filter:");

        jFilter.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Branch Office", "Area" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SITES' TABLE");

        jBranchOffice.setEditable(false);
        jBranchOffice.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N

        jArea.setEditable(false);
        jArea.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Branch Office:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Area:");

        jModify.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jModify.setText("Modify");
        jModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jModifyActionPerformed(evt);
            }
        });

        jDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jDelete.setText("Delete");
        jDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jModify, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBranchOffice, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jArea, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(217, 217, 217))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBranchOffice, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jArea, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jModify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jModifyActionPerformed
        int[] indexOfSelectedRow = jTable.getSelectedRows();
        int index;
        if(indexOfSelectedRow.length==1){
            index = jTable.convertRowIndexToModel(indexOfSelectedRow[0]);
            String oldBranchOffice = String.valueOf(tableModel.getValueAt(index, 0)); 
            String oldArea = String.valueOf(tableModel.getValueAt(index, 1)); 
            String oldWorkspaceNotes = String.valueOf(tableModel.getValueAt(index, 2));
            jNewBranchOffice.setText(oldBranchOffice);
            jNewArea.setText(oldArea);
            jNewWorkspaceNotes.setText(oldWorkspaceNotes);
            jDialog.setVisible(true);
        }else{
            MessageManager.infoMessage(this,indexOfSelectedRow.length < 1 ? "Select one row" : "Too many rows selected");
        } 
    }//GEN-LAST:event_jModifyActionPerformed

    private void jConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConfirmActionPerformed
        int result = MessageManager.confirmRequest(this,"Are you sure you want to modify this site?", "CONFIRM");
        if (result==EXIT_ON_CLOSE){
            try {
                int[] indexOfSelectedRow = jTable.getSelectedRows();
                int convertedIndex = jTable.convertRowIndexToModel(indexOfSelectedRow[0]);
                String oldBranchOffice = String.valueOf(tableModel.getValueAt(convertedIndex, 0)); // Get the old branch office
                String newBranchOffice = jNewBranchOffice.getText(); // Get the new branch office
                String oldArea = String.valueOf(tableModel.getValueAt(convertedIndex, 1)); // Get the old area
                String newArea = jNewArea.getText(); // Get the new area
                String oldWorkspaceNotes = String.valueOf(tableModel.getValueAt(convertedIndex, 2)); // Get the old workspace notes
                String newWorkspaceNotes= jNewWorkspaceNotes.getText(); // Get the new workspace notes  
                Site oldSite = new Site(oldBranchOffice,oldArea,oldWorkspaceNotes);
                Site newSite = new Site(newBranchOffice,newArea,newWorkspaceNotes);
                if(!(oldSite.equals(newSite))){
                    systemAdministrator.updateSite(oldSite, newSite); // Updates the old site with the new site
                    tableModel.setValueAt(newBranchOffice, convertedIndex, 0); // Updates the jTable
                    tableModel.setValueAt(newArea, convertedIndex, 1);
                    tableModel.setValueAt(newWorkspaceNotes, convertedIndex, 2);
                }       
            } catch (SiteException | NotValidParameterException ex) {
                JOptionPane.showMessageDialog(this, "Error while trying to modify this site", "Error", JOptionPane.ERROR_MESSAGE);
            }
            jDialog.dispose();
        }
    }//GEN-LAST:event_jConfirmActionPerformed

    /*Method developed by Alessio Citro*/
    private void jDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteActionPerformed
        int[] selectedIndex = jTable.getSelectedRows();
            if(selectedIndex.length == 1){
                int dialogResult = MessageManager.confirmMessage(this,"Are you sure you wish to delete this site");
                if(dialogResult == JOptionPane.YES_OPTION){
                    int convertedIndex = jTable.convertRowIndexToModel(selectedIndex[0]);
                    String branchOffice = String.valueOf(tableModel.getValueAt(convertedIndex, 0)); // Get the selected branch office
                    String area = String.valueOf(tableModel.getValueAt(convertedIndex, 1)); // Get the selected area
                    String workspaceNotes = String.valueOf(tableModel.getValueAt(convertedIndex, 2)); // Get the selected workspace notes
                    Site site = new Site(branchOffice, area, workspaceNotes);
                    try {
                        systemAdministrator.removeSite(site); // Removes the selected site
                        for(int i: selectedIndex){
                            tableModel.removeRow(jTable.convertRowIndexToModel(i)); // Updates the content of jTable
                        }
                    } catch (SiteException | NotValidParameterException ex) {
                        MessageManager.errorMessage(this,"Cannot remove this typology");
                    }
                    jTable.clearSelection();
                }
            }else {
                MessageManager.infoMessage(this,selectedIndex.length < 1 ? "Select one row" : "Too many rows selected");
            } 
    }//GEN-LAST:event_jDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jArea;
    private javax.swing.JTextField jBranchOffice;
    private javax.swing.JButton jConfirm;
    private javax.swing.JButton jDelete;
    private javax.swing.JDialog jDialog;
    private javax.swing.JComboBox<String> jFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton jModify;
    private javax.swing.JTextField jNewArea;
    private javax.swing.JTextField jNewBranchOffice;
    private javax.swing.JTextField jNewWorkspaceNotes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
