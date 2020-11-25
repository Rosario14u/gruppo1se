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
public class Site {
    private final String branchOffice;
    private final String area;
    private String workSpaceNotes;

    /**
     * Constructor of Site 
     * @param branchOffice Branch office
     * @param area Area 
     */
    public Site(String branchOffice, String area) {
        this.branchOffice = branchOffice;
        this.area = area;
        this.workSpaceNotes = null; 
    }

    public Site(String branchOffice, String area, String workSpaceNotes) {
        this.branchOffice = branchOffice;
        this.area = area;
        this.workSpaceNotes = workSpaceNotes;
    }
    
    /**
     * 
     * @return {@code String} branchOffice
     */
    public String getBranchOffice() {
        return branchOffice;
    }

    /**
     * 
     * @return {@code String} Area
     */
    public String getArea() {
        return area;
    }

    /**
     * 
     * @return {@code String} Workspace notes
     */
    public String getWorkSpaceNotes() {
        return workSpaceNotes;
    }

    /**
     * Set Workspace notes
     * @param workSpaceNotes Workspace notes
     */
    public void setWorkSpaceNotes(String workSpaceNotes) {
        this.workSpaceNotes = workSpaceNotes;
    }

    /**
     * Return string representation of the Site object
     * @return {@code String} Workspace notes 
     */
    @Override
    public String toString() {
        return "Site{" + "branchOffice=" + branchOffice + ", area=" + area + ", workSpaceNotes=" + workSpaceNotes + '}';
    }
}
