/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entity.RevenueE;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HAPPY
 */
public interface RevenueD {
    List<RevenueE.ByProduct> getByProduct(Date begin, Date end);
    List<RevenueE.ByUser> getByUser(Date begin, Date end);
}
