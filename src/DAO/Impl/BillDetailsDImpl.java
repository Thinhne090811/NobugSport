/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;


import DAO.BillDetailsDaoD;
import Entity.BillDetailsE;
import Until.*;
import java.util.List;

/**
 *
 * @author HAPPY
 */
public class BillDetailsDImpl implements BillDetailsDaoD {
    String createSql = "INSERT INTO BillDetails(BillId, ProductId, UnitPrice, Quantity) VALUES(?, ?, ?, ?)";
    String updateSql = "UPDATE BillDetails SET BillId=?, ProductId=?, UnitPrice=?, Quantity=? WHERE IdBillDetail=?";
    String deleteSql = "DELETE FROM BillDetails WHERE IdBillDetail=?";
    String findAllSql = "SELECT * FROM BillDetails";
    String findByIdSql = "SELECT * FROM BillDetails WHERE IdBillDetail=?";

    @Override
    public BillDetailsE create(BillDetailsE entity) {
        Object[] values = { // int
            entity.getProductId(),
            entity.getUnitPrice(), // int
            entity.getQuantity() // int
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(BillDetailsE entity) {
        Object[] values = {
            entity.getBillId(), // int
            entity.getProductId(),
            entity.getUnitPrice(), // int
            entity.getQuantity(), // int
            entity.getIdBillDetail() // int
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(Long id) { // Interface uses Long, but BillDetailsE uses int. Sticking to Long as per interface.
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<BillDetailsE> findAll() {
        return XQuery.getBeanList(BillDetailsE.class, findAllSql);
    }

    @Override
    public BillDetailsE findById(Long id) { // Interface uses Long, but BillDetailsE uses int. Sticking to Long as per interface.
        return XQuery.getSingleBean(BillDetailsE.class, findByIdSql, id);
    }

    @Override
    public List<BillDetailsE> findByBillId(Long billId) { // Interface uses Long, but BillDetailsE uses int. Sticking to Long as per interface.
        String sql = "SELECT * FROM BillDetails WHERE BillId = ?";
        return XQuery.getBeanList(BillDetailsE.class, sql, billId);
    }

    @Override
    public List<BillDetailsE> findByProductsId(String productId) {
        String sql = "SELECT * FROM BillDetails WHERE ProductId = ?";
        return XQuery.getBeanList(BillDetailsE.class, sql, productId);
    }
}
