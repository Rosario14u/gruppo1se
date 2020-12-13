/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

import java.security.InvalidParameterException;
import java.util.Objects;

/**
 *
 * @author gorra Developed by Antonio Gorrasi
 */
public class Site {

    private final String branchOffice;
    private final String area;
    private String workSpaceNotes;

    /**
     * Constructor of Site
     *
     * @param branchOffice Branch office
     * @param area Area
     */
    public Site(String branchOffice, String area) {
        if (branchOffice == null || area == null || branchOffice.equals("") || area.equals("")) {
            throw new InvalidParameterException("Site not valid");
        }
        this.branchOffice = branchOffice;
        this.area = area;
        this.workSpaceNotes = null;
    }

    /**
     *
     * @param branchOffice
     * @param area
     * @param workSpaceNotes
     */
    public Site(String branchOffice, String area, String workSpaceNotes) {
        if (branchOffice == null || area == null || branchOffice == null
                || branchOffice.equals("") || area.equals("") || branchOffice.equals("")) {
            throw new InvalidParameterException("Site not valid");
        }
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
     *
     * @param workSpaceNotes Workspace notes
     */
    public void setWorkSpaceNotes(String workSpaceNotes) {
        if (workSpaceNotes == null || workSpaceNotes.equals("")) {
            throw new InvalidParameterException("Site not valid");
        }
        this.workSpaceNotes = workSpaceNotes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.branchOffice);
        hash = 17 * hash + Objects.hashCode(this.area);
        hash = 17 * hash + Objects.hashCode(this.workSpaceNotes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Site other = (Site) obj;
        if (!Objects.equals(this.branchOffice, other.branchOffice)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.workSpaceNotes, other.workSpaceNotes)) {
            return false;
        }
        return true;
    }

    /**
     * Return string representation of the Site object
     *
     * @return {@code String} Workspace notes
     */
    @Override
    public String toString() {
        return "Site{" + "branchOffice=" + branchOffice + ", area=" + area + ", workSpaceNotes=" + workSpaceNotes + '}';
    }
}
