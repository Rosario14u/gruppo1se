/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Site;
import exception.NotValidParameterException;
import exception.SiteException;
import java.util.List;

/**
 *
 * @author rosar
 */
public interface SiteDao {
    public Site retrieveSiteDao(String branchOffice, String area) throws SiteException;
    public boolean addSite(Site site) throws SiteException;
    public boolean deleteSite(Site site) throws SiteException;
    public List<Site> viewSites() throws SiteException;
    public boolean modifySite(Site oldSite, Site newSite) throws SiteException, NotValidParameterException;
}
