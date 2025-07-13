/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import Entity.CustomerE;
import java.util.List;
/**
 *
 * @author HAPPY
 */
public interface CustomerD extends CrudD<CustomerE, String> {
    List<CustomerE> findByPhone(String phone);
    List<CustomerE> findByFullname(String fullnameCustomer);

}
