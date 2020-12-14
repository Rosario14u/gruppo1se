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
     * @return
     * @throws SiteException when branchOffice = "ProvaBranch4" and area = "ProvaArea4"
     */
    @Override
    public Site retrieveSiteDao(String branchOffice, String area) throws SiteException{
        if(branchOffice.compareTo("ProvaBranch4") == 0 && area.compareTo("ProvaArea4") == 0){
            throw new SiteException("Site retriving error");
        }else {
            return new Site(branchOffice, area, "ProvaWorkSpaceNotes");
        }
    }
    
}
