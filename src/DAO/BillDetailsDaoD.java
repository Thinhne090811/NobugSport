/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entity.BillDetailsE;
import java.util.List;

/**
 *
 * @author HAPPY
 */
public interface BillDetailsDaoD extends CrudD<BillDetailsE, Long>{
    List<BillDetailsE> findByBillId(Long billId);
    List<BillDetailsE> findByProductsId(String productId);
}
