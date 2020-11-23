/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

/**
 *
 * @author gorra
 */
public class MaintenanceProcedure {
    private String smp;

    public MaintenanceProcedure(String smp) {
        this.smp = smp;
    }

    public String getSmp() {
        return smp;
    }

    public void setSmp(String smp) {
        this.smp = smp;
    }

    @Override
    public String toString() {
        return "MaintenanceProcedure{" + "smp=" + smp + '}';
    }
}
