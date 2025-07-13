/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;


import DAO.BillDaoD;
import Entity.BillE;
import Entity.CategoryE;
import Until.*;
import java.util.Date;
import java.util.List;


/**
 *
 * @author HAPPY
 */
public class BillDaoDImpl implements BillDaoD {
    String createSql = "INSERT INTO Bills(Username, CustomerId, Checkin, Checkout, StatusBill) VALUES(?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Bills SET Username=?, CustomerId=?, Checkin=?, Checkout=?, StatusBill=? WHERE IdBill=?";
    String deleteSql = "DELETE FROM Bills WHERE IdBill=?";
    String findAllSql = "SELECT * FROM Bills";
    String findByIdSql = "SELECT * FROM Bills WHERE IdBill=?";

    @Override
    public BillE create(BillE entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getCustomerId(),
            entity.getCheckIn(),
            entity.getCheckOut(),
            // Assuming XJdbc converts boolean entity.isStatus() to a String like "Servicing" or "Completed"
            entity.isStatus() ? "Servicing" : "Completed" // Or adjust based on your actual status values
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(BillE entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getCustomerId(),
            entity.getCheckIn(),
            entity.getCheckOut(),
            // Assuming XJdbc converts boolean entity.isStatus() to a String like "Servicing" or "Completed"
            entity.isStatus() ? "Servicing" : "Completed", // Or adjust based on your actual status values
            entity.getIdBill() // int
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(Long id) { // Interface uses Long, but BillE uses int. Sticking to Long as per interface.
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<BillE> findAll() {
        return XQuery.getBeanList(BillE.class, findAllSql);
    }

    @Override
    public BillE findById(Long id) { // Interface uses Long, but BillE uses int. Sticking to Long as per interface.
        return XQuery.getSingleBean(BillE.class, findByIdSql, id);
    }

    @Override
    public List<BillE> findByUsername(String username) {
        String sql = "SELECT * FROM Bills WHERE Username = ?";
        return XQuery.getBeanList(BillE.class, sql, username);
    }

    @Override
    public List<BillE> findByCustomerId(Integer customerId) {
        String sql = "SELECT * FROM Bills WHERE CustomerId = ?";
        return XQuery.getBeanList(BillE.class, sql, customerId);
    }

    @Override
    public List<BillE> findByTimeRange(Date begin, Date end) {
        String sql = "SELECT * FROM Bills WHERE Checkin BETWEEN ? AND ?";
        return XQuery.getBeanList(BillE.class, sql, begin, end);
    }

    @Override
    public BillE findServicingByCustomerId(Integer customerId) {
        String sql = "SELECT * FROM Bills WHERE CustomerId = ? AND StatusBill = 'Servicing'"; // Assuming "Servicing" is the string representation of a true status
        return XQuery.getSingleBean(BillE.class, sql, customerId);
    }

    @Override
    public List<BillE> findByUserAndTimeRange(String username, Date begin, Date end) {
        String sql = "SELECT * FROM Bills WHERE Username = ? AND Checkin BETWEEN ? AND ?";
        return XQuery.getBeanList(BillE.class, sql, username, begin, end);
    }
}
