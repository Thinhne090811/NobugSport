/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.CategoryE;
import Until.XJdbc;
import Until.XQuery;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author PC
 */
public class NewClass {
    public static void main(String[] args) {
        select();
//        beanlist();
//        singbean();
//        values();
    }

//    public static void insert(){
//        String sql = "INSERT INTO Categories (Id, Name) VALUES(?, ?)";
//        XJdbc.executeUpdate(sql, "C01", "Loáº¡i 1");
//        XJdbc.executeUpdate(sql, "C02", "Loáº¡i 2");
//    }
    public static void select() {
    String sql = "SELECT * FROM Categories";
    ResultSet rs = XJdbc.executeQuery(sql);  // ❌ bỏ tham số
    try {
        while (rs.next()) {
            String id = rs.getString("IdCategory");
            String name = rs.getString("NameCategory");
            System.out.println("ID: " + id + "; NameCategory: " + name);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


//    public static void beanlist() {
//        String sql = "SELECT * FROM Categories WHERE Name LIKE ?";
//        List<CategoryE> beans = XQuery.getBeanList(CategoryE.class, sql, "%Loáº¡i%");
//        for (CategoryE c : beans) {
//            System.out.println("Id: " + c.getIdCategory()+ ", Name: " + c.getNameCategory());
//        }
//    }
//
//    public static void singbean() {
//        String sql = "SELECT * FROM Categories WHERE Id=?";
//        CategoryE bean = XQuery.getSingleBean(CategoryE.class, sql, "C02");
//        if (bean != null) {
//            System.out.println("ID: " + bean.getIdCategory());
//            System.out.println("Name: " + bean.getNameCategory());
//        } else {
//            System.out.println("KhÃ´ng tÃ¬m tháº¥y danh má»¥c vá»›i ID = C02");
//        }
//
//    }
//
//    public static void values() {
//        String sql = "SELECT max(Id) FROM Categories WHERE Name LIKE ?";
//        String id = XJdbc.getValue(sql, "%Loáº¡i%");
//        if (id != null) {
//            System.out.println("Id lá»›n nháº¥t cÃ³ Name chá»©a 'Loáº¡i' lÃ : " + id);
//        } else {
//            System.out.println("KhÃ´ng tÃ¬m tháº¥y Id nÃ o khá»›p vá»›i Ä‘iá»u kiá»‡n.");
//        }
//    }
}
