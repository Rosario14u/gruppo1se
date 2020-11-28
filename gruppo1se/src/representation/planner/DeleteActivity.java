/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation.planner;

import business.maintenanceactivity.*;
import business.user.Planner;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import persistence.maintenanceactivity.MaintenanceActivityDAOImpl;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAOImpl;
import persistence.maintenanceactivity.SiteDAOImpl;
/**
 *
 * @author VincenzaCoppola <v.coppola38@studenti.unisa.it>
 */
public class DeleteActivity extends javax.swing.JFrame {
    private Planner planner = null;
    
     
    /**
     * Creates new form DeleteActivity
     */
    public DeleteActivity(Planner planner) {
        this.planner = planner;
        initComponents();
        jActivityID.getDocument().addDocumentListener(new DListener());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jActivityID = new javax.swing.JTextField();
        jSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jMaintenanceActivity = new javax.swing.JTextArea();
        jDelete = new javax.swing.JButton();
        jError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.setForeground(new java.awt.Color(255, 153, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("ActivityID :");

        jActivityID.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jActivityID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jActivityIDKeyPressed(evt);
            }
        });

        jSearch.setBackground(new java.awt.Color(102, 102, 102));
        jSearch.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jSearch.setForeground(new java.awt.Color(255, 255, 255));
        jSearch.setText("Search");
        jSearch.setEnabled(false);
        jSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchActionPerformed(evt);
            }
        });

        jMaintenanceActivity.setEditable(false);
        jMaintenanceActivity.setColumns(20);
        jMaintenanceActivity.setFont(new java.awt.Font("Rockwell", 3, 14)); // NOI18N
        jMaintenanceActivity.setRows(5);
        jScrollPane2.setViewportView(jMaintenanceActivity);

        jDelete.setBackground(new java.awt.Color(102, 102, 102));
        jDelete.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jDelete.setForeground(new java.awt.Color(255, 255, 255));
        jDelete.setText("Delete");
        jDelete.setEnabled(false);
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
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(34, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jActivityID, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jError, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jError, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                    .addComponent(jActivityID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
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

    private void jActivityIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jActivityIDKeyPressed
        char ch = evt.getKeyChar();
        if( Character.isLetter(ch)){
            jActivityID.setEditable(false);
            jError.setText("Enter digits only!");
        }
        else{
            jActivityID.setEditable(true);
            jError.setText("");
        }
    }//GEN-LAST:event_jActivityIDKeyPressed

    private void jSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchActionPerformed
        int activityId = Integer.parseInt(jActivityID.getText());
        MaintenanceActivity activity = planner.viewMaintenanceActivity(activityId);
        StringBuilder builder = new StringBuilder(); 
        if (activity!= null){
            jDelete.setEnabled(true);
            for(Material m : activity.getMaterials()){
                builder.append(m.getName());
                builder.append(",");
            }
            jMaintenanceActivity.setText("\n"+" ActivityID: "+ Integer.toString(activity.getActivityId())+"\n\n"+
                    " Branch Office: "+ activity.getSite().getBranchOffice()+"\n\n"+
                    " Area: "+ activity.getSite().getArea()+"\n\n"+
                    " Workspace Notes: "+activity.getSite().getWorkSpaceNotes()+"\n\n"+
                    " Typology: "+activity.getTypology()+"\n\n"+
                    " Activity Description: "+activity.getActivityDescription()+"\n\n"+
                    " Estimated Intervention Time: "+ Integer.toString(activity.getEstimatedInterventionTime())+"\n\n"+
                    " Date: "+activity.getDate().toString()+"\n\n"+
                    " Materials: "+builder.toString()+ "\n\n"+
                    " Interruptible Activity: "+Boolean.toString(activity.isInterruptibleActivity())+"\n\n"+
                    " Activity: "+activity.getClass().getSimpleName());   
        }
        else
            jMaintenanceActivity.setText("The activity with ActivityID: "+jActivityID.getText()+" isn't in the database!");
    }//GEN-LAST:event_jSearchActionPerformed

    private void jDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteActionPerformed
        int activityId = Integer.parseInt(jActivityID.getText());
        planner.removeMaintenanceActivity(activityId);
        jActivityID.setText("");
        jMaintenanceActivity.setText("");
        jSearch.setEnabled(false);
        jDelete.setEnabled(false);
    }//GEN-LAST:event_jDeleteActionPerformed

    /**
     * @param args the command line arguments
     */
    private class DListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            checkField();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            checkField();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            checkField();
        }
        
        public void checkField(){
            boolean fieldHasText= true;
            if(jActivityID.getText().trim().isEmpty())
                fieldHasText=false;
            jSearch.setEnabled(fieldHasText);
        }
    }    
   
    
    public static void main(String args[]) {
        Planner planner = new Planner("User","pwd", new MaintenanceActivityDAOImpl(new SiteDAOImpl()),
                new RequiredMaterialForMaintenanceDAOImpl());
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DeleteActivity(planner).setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jActivityID;
    private javax.swing.JButton jDelete;
    private javax.swing.JLabel jError;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextArea jMaintenanceActivity;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jSearch;
    private javax.swing.JSpinner jSpinner1;
    // End of variables declaration//GEN-END:variables
}
