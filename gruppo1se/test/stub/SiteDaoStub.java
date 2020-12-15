/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stub;

import business.maintenanceactivity.Site;
import exception.SiteException;
import persistence.maintenanceactivity.SiteDao;

/**
 *
 * @author rosar
 */
public class SiteDaoStub implements SiteDao {
    /**
     * Simulates the behaviour of retrieveSiteDao of SiteDao.
     * @param branchOffice
     * @param area
     * @return {@ Site}
     * @throws SiteException when branchOffice = "ProvaBranch4" and area = "ProvaArea4"
     */
    @Override
    public Site retrieveSiteDao(String branchOffice, String area) throws SiteException{
        if(branchOffice.compareTo("ProvaBranch4") == 0 && area.compareTo("ProvaArea4") == 0){
            /*This case simulate a case in which branch office or area are not found*/
            throw new SiteException("Site retriving error");
        }else {
            /*This case simulate a case in which the method correctly returns a site*/
            return new Site(branchOffice, area, "ProvaWorkSpaceNotes");
        }
    }
    
    /**
     * 
     * @param site
     * @return
     * @throws SiteException 
     */
    /*Developed by Alessio Citro*/
    @Override
    public boolean addSite(Site site) throws SiteException {
        if(site.getBranchOffice().equals("False"))
            return false;
        else if(site.getBranchOffice().equals("Exception"))
            throw new SiteException();
        else 
            return true;
    }

    /**
     * 
     * @param site
     * @return
     * @throws SiteException 
     */
    /*Developed by Alessio Citro*/
    @Override
    public boolean deleteSite(Site site) throws SiteException {
         if(site.getBranchOffice().equals("False"))
            return false;
        else if(site.getBranchOffice().equals("Exception"))
            throw new SiteException();
        else 
            return true;
    }
    
}
