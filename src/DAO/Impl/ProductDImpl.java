/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;

import DAO.ProductD;
import Entity.ProductE;
import Until.*;
import java.util.List;

/**
 *
 * @author HAPPY
 */
public class ProductDImpl implements ProductD {
    String createSql = "INSERT INTO Products(IdProduct, NameProduct, PriceProduct, CategoryId, ImageProduct, StatusProduct, DiscountProduct) VALUES(?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Products SET NameProduct=?, PriceProduct=?, CategoryId=?, ImageProduct=?, StatusProduct=?, DiscountProduct=? WHERE IdProduct=?";
    String deleteSql = "DELETE FROM Products WHERE IdProduct=?";
    String findAllSql = "SELECT * FROM Products";
    String findByIdSql = "SELECT * FROM Products WHERE IdProduct=?";

    @Override
    public ProductE create(ProductE entity) {
        Object[] values = {
            entity.getIdProduct(),
            entity.getNameProduct(),
            entity.getPriceProduct(),
            entity.getCategoryId(),
            entity.getImageProduct(),
            // Assuming XJdbc converts boolean entity.isStatusProduct() to a String like "Active" or "Inactive"
            entity.isStatusProduct() ? "Active" : "Inactive", // Or adjust based on your actual status values
            entity.getDiscountProduct()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(ProductE entity) {
        Object[] values = {
            entity.getNameProduct(),
            entity.getPriceProduct(),
            entity.getCategoryId(),
            entity.getImageProduct(),
            // Assuming XJdbc converts boolean entity.isStatusProduct() to a String like "Active" or "Inactive"
            entity.isStatusProduct() ? "Active" : "Inactive", // Or adjust based on your actual status values
            entity.getDiscountProduct(),
            entity.getIdProduct()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<ProductE> findAll() {
        return XQuery.getBeanList(ProductE.class, findAllSql);
    }

    @Override
    public ProductE findById(String id) {
        return XQuery.getSingleBean(ProductE.class, findByIdSql, id);
    }

    @Override
    public List<ProductE> findByCategoryId(String categoryId) {
        String sql = "SELECT * FROM Products WHERE CategoryId = ?";
        return XQuery.getBeanList(ProductE.class, sql, categoryId);
    }

    @Override
    public List<ProductE> findByNameProduct(String nameProduct) {
        String sql = "SELECT * FROM Products WHERE NameProduct LIKE ?";
        return XQuery.getBeanList(ProductE.class, sql, "%" + nameProduct + "%");
    }
}
