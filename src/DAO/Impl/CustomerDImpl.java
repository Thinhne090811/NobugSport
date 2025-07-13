/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;

import DAO.CustomerD;
import Entity.CustomerE;
import Until.*;
import java.util.List;

/**
 *
 * @author HAPPY
 */
public class CustomerDImpl implements CustomerD {
     String createSql = "INSERT INTO Customers(IdCustomer, Phone, FullnameCustomer, Level, TotalTarget, DiscountCustomer) VALUES(?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Customers SET Phone=?, FullnameCustomer=?, Level=?, TotalTarget=?, DiscountCustomer=? WHERE IdCustomer=?";
    String deleteSql = "DELETE FROM Customers WHERE IdCustomer=?";
    String findAllSql = "SELECT * FROM Customers";
    String findByIdSql = "SELECT * FROM Customers WHERE IdCustomer=?";

    @Override
    public CustomerE create(CustomerE entity) {
        Object[] values = {
            entity.getIdCustomer(),
            entity.getPhone(),
            entity.getFullnameCustomer(),
            entity.getLevel(), // int
            entity.getTotalTarget(), // int
            entity.getDiscountCustomer()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(CustomerE entity) {
        Object[] values = {
            entity.getPhone(),
            entity.getFullnameCustomer(),
            entity.getLevel(), // int
            entity.getTotalTarget(), // int
            entity.getDiscountCustomer(),
            entity.getIdCustomer()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<CustomerE> findAll() {
        return XQuery.getBeanList(CustomerE.class, findAllSql);
    }

    @Override
    public CustomerE findById(String id) {
        return XQuery.getSingleBean(CustomerE.class, findByIdSql, id);
    }

    @Override
    public List<CustomerE> findByPhone(String phone) {
        String sql = "SELECT * FROM Customers WHERE Phone = ?";
        return XQuery.getBeanList(CustomerE.class, sql, phone);
    }

    @Override
    public List<CustomerE> findByFullname(String fullnameCustomer) {
        String sql = "SELECT * FROM Customers WHERE FullnameCustomer LIKE ?";
        return XQuery.getBeanList(CustomerE.class, sql, "%" + fullnameCustomer + "%");
    }
}
