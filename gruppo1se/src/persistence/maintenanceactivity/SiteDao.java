/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Site;

/**
 *
 * @author rosar
 */
public interface SiteDao {
    public Site retrieveSiteDao(String branchOffice, String area);
}
