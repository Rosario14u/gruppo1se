/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation.planner;

import business.maintenanceactivity.Appointment;
import business.maintenanceactivity.MaintenanceActivity;
import business.user.Planner;
import business.user.WeekConverter;
import dto.MaintainerDTO;
import exception.AppointmentException;
import exception.MaintenanceActivityException;
import exception.NotValidParameterException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
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
 * @author gorra
 */
public class ActivityAssignment extends javax.swing.JDialog {

    private MaintenanceActivity activity;
    private LocalDate newDate;
    private MaintainerDTO maintainer;
    private DefaultTableModel tableModel;
    private MinuteCellRenderer renderer;
    private List<Appointment> appointmentList;
    private int remainEstimatedInterventionTime;
    private Planner planner;
    private Frame parent;

    /**
     * 
     * @param parent
     * @param modal
     * @param activity
     * @param newDate
     * @param mantainer 
     */
    public ActivityAssignment(java.awt.Frame parent, boolean modal, MaintenanceActivity activity, LocalDate newDate, MaintainerDTO mantainer) {
        super(parent, modal);
        this.activity = activity;
        this.newDate = newDate;
        this.maintainer = mantainer;
        this.appointmentList = new ArrayList<>();
        this.parent = parent;
        this.remainEstimatedInterventionTime = activity.getEstimatedInterventionTime();
        this.planner = new Planner("planner", "planner", new MaintenanceActivityDAOImpl(new SiteDaoImpl()),
                new RequiredMaterialForMaintenanceDAOImpl(), new UsersDAOImpl(), new EmployeeAppointmentDAOImpl(),
                new RequiredSkillForMaintenanceDAOImpl(), new MaintainerSkillDAOImpl(), new TypologyDAOImpl());
        initComponents();
        this.setLocationRelativeTo(null);
        tableModel = (DefaultTableModel) maintainerAvailabilityDayTable.getModel();
        renderer = new MinuteCellRenderer();
        maintainerAvailabilityDayTable.setDefaultRenderer(Object.class, renderer);
        initializeFields();
    }

    
    /**
     * This method inizialize all fields of GUI
     */
    private void initializeFields() {
        int weekNumber = WeekConverter.getWeek(newDate);
        weekLabelDialog.setText(Integer.toString(weekNumber));
        dayLabel.setText(newDate.getDayOfWeek().name());
        dayLabelNumber.setText(String.valueOf(newDate.getDayOfMonth()));
        maintainerLabel.setText("Availability " + maintainer.getUsername());
        sendButton.setEnabled(false);
        activityInfoLabelDialog.setText(Integer.toString(activity.getActivityId()) + " - " + activity.getSite().getArea()
                + " - " + activity.getTypology() + " - " + Integer.toString(activity.getEstimatedInterventionTime()) + "'");
        workspaceNotesTextArea.setText(activity.getSite().getWorkSpaceNotes());
        populateTable();
    }

    
    /**
     * This method allows you to populate the table 
     * according to the availability of the maintainer
     * @param weekNumber 
     */
    private void populateTable() {
        int numProcedureSkill = activity.getMaintenanceProcedure().getSkills().size();
        String[] rowTable = new String[]{"", "", "60 min", "60 min", "60 min", "60 min", "60 min", "60 min", "60 min"};
        rowTable[0] = maintainer.getUsername();
        maintainer.getSkills().retainAll(activity.getMaintenanceProcedure().getSkills());
        rowTable[1] = maintainer.getSkills().size() + "/" + numProcedureSkill;
        for (Appointment appointment : maintainer.getAppointmentsInWeek()) {
            LocalDateTime dateTime = appointment.getStartDateAndTime();
            //takes the activities scheduled for the selected day
            if (dateTime.toLocalDate().equals(newDate)) {
                int hour = dateTime.getHour();
                if(hour >= 8 && hour <= 11){
                    rowTable[hour-6] = Integer.valueOf(rowTable[hour-6].split(" ")[0]) - appointment.getDuration() + " min";
                }else{
                    rowTable[hour-8] = Integer.valueOf(rowTable[hour-8].split(" ")[0]) - appointment.getDuration() + " min";
                }
            }
        }
        System.out.println(rowTable.toString());
        tableModel.addRow(rowTable);
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
        maintainerLabel = new javax.swing.JLabel();
        workspaceNotesLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        workspaceNotesTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        maintainerAvailabilityDayTable = new javax.swing.JTable();
        activityToAssignLabel = new javax.swing.JLabel();
        activityInfoLabelDialog = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        weekLabelDialog = new javax.swing.JLabel();
        dayLabel = new javax.swing.JLabel();
        dayLabelNumber = new javax.swing.JLabel();
        sendButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.setForeground(new java.awt.Color(255, 153, 0));

        maintainerLabel.setBackground(new java.awt.Color(200, 153, 0));
        maintainerLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        maintainerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        maintainerLabel.setOpaque(true);

        workspaceNotesLabel.setBackground(new java.awt.Color(200, 153, 0));
        workspaceNotesLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        workspaceNotesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        workspaceNotesLabel.setText("Workspace Notes");
        workspaceNotesLabel.setOpaque(true);

        workspaceNotesTextArea.setEditable(false);
        workspaceNotesTextArea.setColumns(20);
        workspaceNotesTextArea.setRows(5);
        jScrollPane1.setViewportView(workspaceNotesTextArea);

        maintainerAvailabilityDayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Maintainer", "Skills", "<html>Availab<br><font size=\"1\">8:00 - 9:00</font></html>", "<html>Availab<br><font size=\"1\">9:00 - 10:00</font></html>", "<html>Availab<br><font size=\"1\">10:00 - 11:00</font></html>", "<html>Availab<br><font size=\"1\">11:00 - 12:00</font></html>", "<html>Availab<br><font size=\"1\">14:00 - 15:00</font></html>", "<html>Availab<br><font size=\"1\">15:00 - 16:00</font></html>", "<html>Availab<br><font size=\"1\">16:00 - 17:00</font></html>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        maintainerAvailabilityDayTable.getTableHeader().setReorderingAllowed(false);
        maintainerAvailabilityDayTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maintainerAvailabilityDayTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(maintainerAvailabilityDayTable);
        int width = maintainerAvailabilityDayTable.getSize().width;
        maintainerAvailabilityDayTable.setFillsViewportHeight(true);
        maintainerAvailabilityDayTable.getTableHeader().setPreferredSize(new Dimension(width,40));
        maintainerAvailabilityDayTable.setRowHeight(35);
        maintainerAvailabilityDayTable.setBackground(new Color(255,153,0));
        maintainerAvailabilityDayTable.getTableHeader().setBackground(new Color(255,170,0));

        activityToAssignLabel.setBackground(new java.awt.Color(250, 250, 250));
        activityToAssignLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        activityToAssignLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activityToAssignLabel.setText("<html>Activity to<br>assign<html>");
        activityToAssignLabel.setOpaque(true);

        activityInfoLabelDialog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activityInfoLabelDialog.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(250, 250, 250));
        jLabel5.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Week n°");
        jLabel5.setOpaque(true);

        weekLabelDialog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        weekLabelDialog.setOpaque(true);

        dayLabel.setBackground(new java.awt.Color(250, 250, 250));
        dayLabel.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        dayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dayLabel.setOpaque(true);

        dayLabelNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dayLabelNumber.setOpaque(true);

        sendButton.setText("SEND");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        clearButton.setText("CLEAR");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(workspaceNotesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(maintainerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(weekLabelDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(dayLabelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addComponent(activityToAssignLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(activityInfoLabelDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(activityToAssignLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(activityInfoLabelDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(weekLabelDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dayLabelNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(workspaceNotesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maintainerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sendButton)
                            .addComponent(clearButton)))
                    .addComponent(jScrollPane1))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * This method handles clicks on cells
     * @param evt 
     */
    private void maintainerAvailabilityDayTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maintainerAvailabilityDayTableMouseClicked
        //pattern to look for in the table header
        Pattern p = Pattern.compile("[0-9]?[0-9]:[0-9][0-9] - [0-9]?[0-9]:[0-9][0-9]");
        //row and col indicate which cell was clicked
        int row = maintainerAvailabilityDayTable.rowAtPoint(evt.getPoint());
        int col = maintainerAvailabilityDayTable.columnAtPoint(evt.getPoint());
        if (col >= 2 && col <= 8 && row != -1) {
            String availabilityValue = String.valueOf(maintainerAvailabilityDayTable.getValueAt(row, col)).split(" ")[0];
            int availableMinutes = Integer.valueOf(availabilityValue);
            if (availableMinutes == 0) {
                //check if the cell has minutes available
                MessageManager.infoMessage(this,"Busy Maintainer");
            } else {
                if (remainEstimatedInterventionTime <= 0) {
                    MessageManager.infoMessage(this,"Activity already assign");
                } else {
                    int duration = remainEstimatedInterventionTime > availableMinutes ? availableMinutes : remainEstimatedInterventionTime;
                    remainEstimatedInterventionTime = remainEstimatedInterventionTime - duration;
                    tableModel.setValueAt(availableMinutes - duration + " min", row, col);
                    if (remainEstimatedInterventionTime == 0) {
                        sendButton.setEnabled(true);
                    }
                    String colName = maintainerAvailabilityDayTable.getColumnName(col);

                    Matcher matcher = p.matcher(colName);
                    int hour;
                    if (matcher.find()) {
                        //takes the first part of the time slot. Ex: 10:00 - 11:00 --> 10
                        hour = Integer.valueOf(matcher.group(0).split(" - ")[0].split(":")[0]);
                        appointmentList.add(new Appointment(activity.getActivityId(), newDate.atTime(hour, 00), duration));
                    }
                }
            }
        }
    }//GEN-LAST:event_maintainerAvailabilityDayTableMouseClicked

    
    /**
     * If pressed this button allows to associate 
     * the maintenance activity with the maintainer 
     * @param evt 
     */
    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        try {
            if(!planner.verifyActivityAssignment(activity.getActivityId(), activity.getEstimatedInterventionTime())){
                activity.setDate(newDate);
                if (!planner.saveAppointments(maintainer.getUsername(), activity, appointmentList)) {
                    MessageManager.errorMessage(this, "Saving failed");
                } else {
                    this.dispose();
                }
            }else
                MessageManager.errorMessage(this, "Activity already assign");
        } catch (AppointmentException | MaintenanceActivityException | NotValidParameterException ex) {
            MessageManager.errorMessage(this,ex.getMessage());
        }
    }//GEN-LAST:event_sendButtonActionPerformed

    
    /**
     * This button allows to clean the cells 
     * that have been previously selected
     * @param evt 
     */
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        appointmentList.clear();
        tableModel.removeRow(0);
        remainEstimatedInterventionTime = activity.getEstimatedInterventionTime();
        sendButton.setEnabled(false);
        populateTable();
    }//GEN-LAST:event_clearButtonActionPerformed
    
    
    
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
//            java.util.logging.Logger.getLogger(ActivityAssignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ActivityAssignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ActivityAssignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ActivityAssignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ActivityAssignment dialog = new ActivityAssignment(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel activityInfoLabelDialog;
    private javax.swing.JLabel activityToAssignLabel;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel dayLabel;
    private javax.swing.JLabel dayLabelNumber;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable maintainerAvailabilityDayTable;
    private javax.swing.JLabel maintainerLabel;
    private javax.swing.JButton sendButton;
    private javax.swing.JLabel weekLabelDialog;
    private javax.swing.JLabel workspaceNotesLabel;
    private javax.swing.JTextArea workspaceNotesTextArea;
    // End of variables declaration//GEN-END:variables
}
