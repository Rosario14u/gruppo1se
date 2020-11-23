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
public class Site {
    private final String branchOffice;
    private final String area;
    private String workSpaceNotes;

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
    
    public String getBranchOffice() {
        return branchOffice;
    }

    public String getArea() {
        return area;
    }

    public String getWorkSpaceNotes() {
        return workSpaceNotes;
    }

    public void setWorkSpaceNotes(String workSpaceNotes) {
        this.workSpaceNotes = workSpaceNotes;
    }

    @Override
    public String toString() {
        return "Site{" + "branchOffice=" + branchOffice + ", area=" + area + ", workSpaceNotes=" + workSpaceNotes + '}';
    }
}
