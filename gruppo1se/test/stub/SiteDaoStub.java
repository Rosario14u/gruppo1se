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

    @Override
    public Site retrieveSiteDao(String branchOffice, String area) throws SiteException{
        if(branchOffice.compareTo("ProvaBranch1") == 0 && area.compareTo("ProvaArea1") == 0){
            return new Site(branchOffice,area, "ProvaWorkSpaceNotes1");
        }else if (branchOffice.compareTo("ProvaBranch2") == 0 && area.compareTo("ProvaArea2") == 0){
            return new Site(branchOffice,area, "ProvaWorkSpaceNotes2");
        }else if (branchOffice.compareTo("ProvaBranch3") == 0 && area.compareTo("ProvaArea3") == 0) {
            return new Site(branchOffice,area, "ProvaWorkSpaceNotes3");
        }
        else if (branchOffice.compareTo("ProvaBranch4") == 0 && area.compareTo("ProvaArea4") == 0) {
            return null;
        }
        else if (branchOffice.compareTo("ProvaBranch5") == 0 && area.compareTo("ProvaArea5") == 0) {
            throw new SiteException("Site retriving error");
        }else{
            return new Site(branchOffice,area, "ProvaWorkSpaceNotes6");
        }
    }
    
}
