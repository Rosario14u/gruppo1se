/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

/**
 *
 * @author gorra
 * Developed by Antonio Gorrasi
 */
public class MaintenanceProcedure {
    private String smp;

    /**
     * Constructor of Maintenance Procedure
     * @param smp Standard maintenance procedure
     */
    public MaintenanceProcedure(String smp) {
        this.smp = smp;
    }

    /**
     * 
     * @return {@code String} Standard maintenance procedure
     */
    public String getSmp() {
        return smp;
    }

    /**
     * Set Standard maintenance procedure
     * @param smp Standard maintenance procedure
     */
    public void setSmp(String smp) {
        this.smp = smp;
    }

        /**
     * Return string representation of the MaintenanceProcedure object
     * @return {@code String} Maintenance Procedure
     */
    @Override
    public String toString() {
        return "MaintenanceProcedure{" + "smp=" + smp + '}';
    }
}
