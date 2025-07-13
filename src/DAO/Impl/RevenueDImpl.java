/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;

import DAO.RevenueD;
import Entity.RevenueE;
import Until.XQuery;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HAPPY
 */
public class RevenueDImpl implements RevenueD {
    @Override
    public List<RevenueE.ByProduct> getByProduct(Date begin, Date end) {
        // Assuming database column names (NameProduct, TotalRevenue) map to
        // properties in RevenueE.ByProduct (product, revenue) by XQuery
        String sql = "SELECT p.NameProduct AS product, SUM(bd.Quantity * bd.UnitPrice) AS revenue " +
                     "FROM Products p " +
                     "JOIN BillDetails bd ON p.IdProduct = bd.ProductId " +
                     "JOIN Bills b ON bd.BillId = b.IdBill " +
                     "WHERE b.Checkin BETWEEN ? AND ? " +
                     "GROUP BY p.NameProduct";
        // If your XQuery can auto-map, you don't need aliases.
        // If it requires exact matches, ensure aliases like 'AS product' and 'AS revenue' match your entity's field names.
        return XQuery.getBeanList(RevenueE.ByProduct.class, sql, begin, end);
    }

    @Override
    public List<RevenueE.ByUser> getByUser(Date begin, Date end) {
        // Assuming database column names (FullnameUser, TotalRevenue) map to
        // properties in RevenueE.ByUser (user, revenue) by XQuery
        String sql = "SELECT u.FullnameUser AS user, SUM(bd.Quantity * bd.UnitPrice) AS revenue " +
                     "FROM Users u " +
                     "JOIN Bills b ON u.Username = b.Username " +
                     "JOIN BillDetails bd ON b.IdBill = bd.BillId " +
                     "WHERE b.Checkin BETWEEN ? AND ? " +
                     "GROUP BY u.FullnameUser";
        // If your XQuery can auto-map, you don't need aliases.
        // If it requires exact matches, ensure aliases like 'AS user' and 'AS revenue' match your entity's field names.
        return XQuery.getBeanList(RevenueE.ByUser.class, sql, begin, end);
    }
}
