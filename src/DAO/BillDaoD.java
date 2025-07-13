/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entity.BillE;
import java.util.Date;
import java.util.List;
/**
 *
 * @author HAPPY
 */
public interface BillDaoD extends CrudD<BillE, Long>{
    List<BillE> findByUsername(String username);
    List<BillE> findByCustomerId(Integer customerId);
    List<BillE> findByTimeRange(Date begin, Date end);
    
    BillE findServicingByCustomerId(Integer customerId);
    List<BillE> findByUserAndTimeRange(String username, Date begin, Date end);
}
