/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation.planner;

import business.maintenanceactivity.Appointment;
import business.maintenanceactivity.MaintenanceActivity;
import business.maintenanceactivity.Skill;
import business.user.Planner;
import business.user.WeekConverter;
import dto.MaintainerDTO;
import exception.AppointmentException;
import exception.MaintenanceActivityException;
import exception.NotValidParameterException;
import exception.UsersException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import presentation.manager.MessageManager;

/**
 * GUI to visualize Maintainer Availability in a specific week
 * @author gorra
 */
public class MaintainerAvailability extends javax.swing.JFrame {
    private final MaintenanceActivity activity;
    private final Planner planner;
    private final DefaultTableModel tableModel;
    private List<MaintainerDTO> maintainerList;
    private ActivityAssignment jDialogGUI;
    private static final int INDEX_FIRST_AVAIL_COL = 2;
    private static final int NUM_COL = 9; 
    private static final int WORKING_MINUTES_IN_A_DAY = 420;
    
    
    /**
     * Creates new form MaintainerAvailability
     * @param activity 
     * @param planner 
     */
    public MaintainerAvailability(MaintenanceActivity activity, Planner planner) {
        this.planner = planner;
        this.activity = activity;
        initComponents();
        tableModel = (DefaultTableModel) maintainerAvailabilityTable.getModel();
        initializeFields();
        this.setLocationRelativeTo(null);
        /*This object performs the coloring of the cell of jTable*/
        PercentageCellRenderer renderer = new PercentageCellRenderer();  
        maintainerAvailabilityTable.setDefaultRenderer(Object.class, renderer);
    }
    
    
    /**
     * This method initializes the fields of the GUI.
     */
    private void initializeFields() {
        try {
            setAssignedLabel();
            LocalDate date = activity.getDate();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
            weekLabel.setText(Integer.toString(weekNumber));
            activityInfoLabel.setText(Integer.toString(activity.getActivityId()) + " - " + activity.getSite().getArea()
                    + " - " + activity.getTypology() + " - " + Integer.toString(activity.getEstimatedInterventionTime()) + "'");
            /*Printing of the skills*/
            if (activity.getMaintenanceProcedure()!= null && activity.getMaintenanceProcedure().getSkills() != null) {
                StringBuilder builder2 = new StringBuilder();
                for (Skill skill : activity.getMaintenanceProcedure().getSkills()) {
                    builder2.append(skill.getName()).append("\n");
                }
                skillTextArea.setText(builder2.toString());
                skillTextArea.setEnabled(false);
            }
            populateTable();
        } catch (MaintenanceActivityException | NotValidParameterException | AppointmentException ex) {
            MessageManager.errorMessage(this, ex.getMessage());
        }
    }
    
    
    /**
     * This method allows to fill the jTable with information about Maintainer <br> 
     * (username, skills accordance and days availability of the maintainers in a specific week). 
     */
    /*Method developed by Rosario Gaeta*/
    private void populateTable() {
        try {
            /*get the list of activity skills*/
            int numProcedureSkill = activity.getMaintenanceProcedure().getSkills().size();
            /*get a list of maintainer with their skills and appointments*/
            maintainerList = planner.viewEmployeeAvailability(WeekConverter.getWeek(activity.getDate()),
                    WeekConverter.getYear(activity.getDate())); 
            for (MaintainerDTO maintainer : maintainerList) {
                /*initial row*/
                String[] rowTable = new String[]{"", "", "100", "100", "100", "100", "100", "100", "100"};
                /*adding of the maintainer username to the row*/
                rowTable[0] = maintainer.getUsername(); 
                maintainer.getSkills().retainAll(activity.getMaintenanceProcedure().getSkills());
                /*adding of the skills to the row*/
                rowTable[1] = maintainer.getSkills().size() + "/" + numProcedureSkill; 
                for (Appointment appointment : maintainer.getAppointmentsInWeek()) {
                    /*conversion of the day of the week in an index*/
                    int index = appointment.getStartDateAndTime().getDayOfWeek().getValue(); 
                    float totalCellPercentage=  Float.valueOf(rowTable[index+1]);
                    /*subtracts the duration of an appointment from the total (in percentage)*/
                    totalCellPercentage = (totalCellPercentage - ((float) appointment.getDuration() * 100) / WORKING_MINUTES_IN_A_DAY);
                    rowTable[index+1] = String.valueOf(totalCellPercentage);
                }
                for(int index=INDEX_FIRST_AVAIL_COL; index < NUM_COL ; index++){
                    /*final rounding*/
                    rowTable[index] = Math.round(Float.valueOf(rowTable[index])) + "%"; 
                }
                /*inserting of a row in the table*/
                tableModel.addRow(rowTable); 
            }
        } catch (UsersException | NotValidParameterException ex) {
            MessageManager.errorMessage(this,ex.getMessage());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        skillTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        maintainerAvailabilityTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        activityInfoLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        weekLabel = new javax.swing.JLabel();
        assignedLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Maintainers Availiability");

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.setForeground(new java.awt.Color(255, 153, 0));

        jLabel1.setBackground(new java.awt.Color(200, 153, 0));
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Maintainer AVAILABILITY");
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(200, 153, 0));
        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Skills needed");
        jLabel2.setOpaque(true);

        skillTextArea.setEditable(false);
        skillTextArea.setColumns(20);
        skillTextArea.setRows(5);
        jScrollPane1.setViewportView(skillTextArea);

        maintainerAvailabilityTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Maintainer", "Skills", "<html>Availab<br>Mon</html>", "<html>Availab<br>Tue</html>", "<html>Availab<br>Wed</html>", "<html>Availab<br>Thu</html>", "<html>Availab<br>Fri</html>", "<html>Availab<br>Sat</html>", "<html>Availab<br>Sun</html>"
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
        maintainerAvailabilityTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        maintainerAvailabilityTable.getTableHeader().setReorderingAllowed(false);
        maintainerAvailabilityTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maintainerAvailabilityTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(maintainerAvailabilityTable);
        if (maintainerAvailabilityTable.getColumnModel().getColumnCount() > 0) {
            maintainerAvailabilityTable.getColumnModel().getColumn(0).setResizable(false);
            maintainerAvailabilityTable.getColumnModel().getColumn(1).setResizable(false);
            maintainerAvailabilityTable.getColumnModel().getColumn(2).setResizable(false);
            maintainerAvailabilityTable.getColumnModel().getColumn(3).setResizable(false);
            maintainerAvailabilityTable.getColumnModel().getColumn(4).setResizable(false);
            maintainerAvailabilityTable.getColumnModel().getColumn(5).setResizable(false);
            maintainerAvailabilityTable.getColumnModel().getColumn(6).setResizable(false);
            maintainerAvailabilityTable.getColumnModel().getColumn(7).setResizable(false);
            maintainerAvailabilityTable.getColumnModel().getColumn(8).setResizable(false);
        }
        int width = maintainerAvailabilityTable.getSize().width;
        maintainerAvailabilityTable.setFillsViewportHeight(true);
        maintainerAvailabilityTable.getTableHeader().setOpaque(false);
        maintainerAvailabilityTable.getTableHeader().setPreferredSize(new Dimension(width,40));
        maintainerAvailabilityTable.setRowHeight(35);
        maintainerAvailabilityTable.setBackground(new Color(255,204,153));
        maintainerAvailabilityTable.getTableHeader().setBackground(new Color(255,170,0));

        jLabel3.setBackground(new java.awt.Color(250, 250, 250));
        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<html>Activity to<br>assign<html>");
        jLabel3.setOpaque(true);

        activityInfoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activityInfoLabel.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(250, 250, 250));
        jLabel5.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Week n°");
        jLabel5.setOpaque(true);

        weekLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        weekLabel.setOpaque(true);

        assignedLabel.setBackground(new java.awt.Color(0, 200, 0));
        assignedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        assignedLabel.setText("ASSEGNATA");
        assignedLabel.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(weekLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(assignedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(activityInfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(620, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(activityInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(weekLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(assignedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(44, 44, 44))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(99, 99, 99)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(312, Short.MAX_VALUE)))
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

    private void maintainerAvailabilityTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maintainerAvailabilityTableMouseClicked
        int row = maintainerAvailabilityTable.rowAtPoint(evt.getPoint());
        int col = maintainerAvailabilityTable.columnAtPoint(evt.getPoint());
        LocalDate newDate = null;
        if (col >= INDEX_FIRST_AVAIL_COL && col <= NUM_COL && row != -1) {
            newDate = WeekConverter.getDayOfWeek(activity.getDate(), DayOfWeek.values()[col - 2]);
            if (newDate.isBefore(LocalDate.now())) {
                MessageManager.infoMessage(this,"Invalid date!");
            } //new ActivityAssignment(this, activity, newDate, maintainerList.get(row)).setVisible(true);
            else {
                jDialogGUI = new ActivityAssignment(this, true, activity, newDate, maintainerList.get(row), planner);
                jDialogGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            tableModel.setRowCount(0);
                            populateTable();
                            setAssignedLabel();
                        } catch (MaintenanceActivityException | NotValidParameterException | AppointmentException ex) {
                            MessageManager.confirmRequest(e.getComponent().getParent(), ex.getMessage(), "CONFIRM");
                        }
                    }
                });
                jDialogGUI.setVisible(true);
            }
        }
    }//GEN-LAST:event_maintainerAvailabilityTableMouseClicked

    
    /**
     * This method sets the label to "assigned" if the label is already assigned (using the method verifyActivityAssignmentMethod),
     * not assigned if otherwise.
     * @throws MaintenanceActivityException
     * @throws NotValidParameterException
     * @throws AppointmentException 
     */
    private void setAssignedLabel() throws MaintenanceActivityException, NotValidParameterException, AppointmentException{
        if(!planner.verifyActivityAssignment(activity.getActivityId(), activity.getEstimatedInterventionTime())){
            assignedLabel.setBackground(new Color(255, 0, 0));
            assignedLabel.setText("Not Assigned");
        }else{
            assignedLabel.setBackground(new Color(0, 255, 0));
            assignedLabel.setText("Assigned");
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel activityInfoLabel;
    private javax.swing.JLabel assignedLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable maintainerAvailabilityTable;
    private javax.swing.JTextArea skillTextArea;
    private javax.swing.JLabel weekLabel;
    // End of variables declaration//GEN-END:variables
}
