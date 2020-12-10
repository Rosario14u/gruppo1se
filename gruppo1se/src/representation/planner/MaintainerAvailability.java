/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representation.planner;

import business.maintenanceactivity.Appointment;
import business.maintenanceactivity.MaintenanceActivity;
import business.maintenanceactivity.MaintenanceProcedure;
import business.maintenanceactivity.Material;
import business.maintenanceactivity.PlannedMaintenanceActivity;
import business.maintenanceactivity.Site;
import business.maintenanceactivity.Skill;
import business.user.Maintainer;
import business.user.Planner;
import business.user.WeekConverter;
import exception.AppointmentException;
import exception.DateException;
import exception.SkillException;
import exception.UsersException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import persistence.maintenanceactivity.EmployeeAppointmentDAOImpl;
import persistence.maintenanceactivity.MaintenanceActivityDAOImpl;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAOImpl;

import persistence.maintenanceactivity.RequiredSkillForMaintenanceDAOImpl;
import persistence.maintenanceactivity.SiteDaoImpl;
import persistence.user.MaintainerSkillDAOImpl;
import persistence.user.UsersDAOImpl;

/**
 *
 * @author gorra
 */
public class MaintainerAvailability extends javax.swing.JFrame {
    private MaintenanceActivity activity;
    private Planner planner;
    private DefaultTableModel tableModel;
    private List<Maintainer> maintainerList;
    /**
     * Creates new form MaintainerAvailability
     */
    public MaintainerAvailability(MaintenanceActivity activity) {
        this.planner = new Planner("planner", "planner",new  MaintenanceActivityDAOImpl(new SiteDaoImpl()),
                new RequiredMaterialForMaintenanceDAOImpl(), new UsersDAOImpl(),new EmployeeAppointmentDAOImpl(),
                new RequiredSkillForMaintenanceDAOImpl(), new MaintainerSkillDAOImpl());
        this.activity = activity;
        initComponents();
        tableModel = (DefaultTableModel) maintainerAvailabilityTable.getModel();
        initializeFields();
        PercentageCellRenderer renderer = new PercentageCellRenderer();
        maintainerAvailabilityTable.setDefaultRenderer(Object.class, renderer);
    }
    
    
    private void initializeFields(){
        LocalDate date = activity.getDate();
        WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
        int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
        weekLabel.setText(Integer.toString(weekNumber));
        this.activity = activity;
        activityInfoLabel.setText(Integer.toString(activity.getActivityId())+" - "+activity.getSite().getArea()
                +" - "+activity.getTypology()+" - "+ Integer.toString(activity.getEstimatedInterventionTime())+"'");
        if (activity.getMaintenanceProcedure().getSkills() != null){
            StringBuilder builder2 = new StringBuilder();
            for(Skill skill : activity.getMaintenanceProcedure().getSkills()){
                builder2.append(skill.toString() + "\n");
            }
            skillTextArea.setText(builder2.toString());
            skillTextArea.setEnabled(false);
        }
        populateTable(weekNumber);
    }
    
    private void populateTable(int weekNumber){
        try {
            int numProcedureSkill = activity.getMaintenanceProcedure().getSkills().size();
            maintainerList = planner.viewEmployeeAvailability(weekNumber, activity.getDate().getYear());
            for (Maintainer maintainer : maintainerList){
                String[] rowTable = new String[] {"","","100%","100%","100%","100%","100%","100%","100%"};
                float totalPercentage = 100;
                rowTable[0] = maintainer.getUsername();
                maintainer.getSkills().retainAll(activity.getMaintenanceProcedure().getSkills());
                rowTable[1] =  maintainer.getSkills().size() + "/" + numProcedureSkill;
                for(Appointment appointment: maintainer.getAppointmentsInWeek()){
                    int index = appointment.getStartDateAndTime().getDayOfWeek().getValue();
                    //int integerPartPercentage = Integer.valueOf(rowTable[index+1].replaceAll("%", ""));
                    totalPercentage = (totalPercentage - ((float)appointment.getDuration()*100)/420);
                    rowTable[index+1] = Math.round(totalPercentage) + "%";
                    
                }
                tableModel.addRow(rowTable);
            }
        } catch (UsersException | DateException | AppointmentException | SkillException ex) {
            errorMessage("Error in table loading");
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        maintainerAvailabilityTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(maintainerAvailabilityTable);
        int width = maintainerAvailabilityTable.getSize().width;
        maintainerAvailabilityTable.setFillsViewportHeight(true);
        maintainerAvailabilityTable.getTableHeader().setOpaque(false);
        maintainerAvailabilityTable.getTableHeader().setPreferredSize(new Dimension(width,40));
        maintainerAvailabilityTable.setRowHeight(35);
        maintainerAvailabilityTable.setBackground(new Color(255,204,153));
        maintainerAvailabilityTable.getTableHeader().setBackground(new Color(255,170,0));
        maintainerAvailabilityTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = maintainerAvailabilityTable.rowAtPoint(evt.getPoint());
                int col = maintainerAvailabilityTable.columnAtPoint(evt.getPoint());
                LocalDate newDate = null;
                if(col>=2 && col<=9){
                    newDate = WeekConverter.getDayOfWeek(activity.getDate(), DayOfWeek.values()[col-2]);
                    new ActivityAssignment(activity, newDate, maintainerList.get(row)).setVisible(true);
                }
            }
        });

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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(activityInfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(MaintainerAvailability.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MaintainerAvailability.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MaintainerAvailability.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MaintainerAvailability.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ArrayList<Skill> skills = new ArrayList<>();
                skills.add(new Skill("Java Knowledge"));
                skills.add(new Skill("English Knowledge"));
                skills.add(new Skill("SQL Knowledge"));
                MaintenanceActivity activity =  new PlannedMaintenanceActivity(1, new Site("ProvaArea","ProvaBranchOffice","ProvaWorkspaceNotes"),
                        "ProvaTypology","ProvaActivityDescription",90,LocalDate.of(2020,12,22),new MaintenanceProcedure("FilePDF"),new ArrayList<Material>(), true);
                new MaintainerAvailability(activity).setVisible(true);
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
    private javax.swing.JLabel activityInfoLabel;
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
