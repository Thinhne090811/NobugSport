/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import Entity.ProductE;
/**
 *
 * @author HAPPY
 */
import java.util.List;
public interface ProductD extends CrudD<ProductE, String> {
    List<ProductE> findByCategoryId(String categoryId);
    List<ProductE> findByNameProduct(String nameProduct);

}
