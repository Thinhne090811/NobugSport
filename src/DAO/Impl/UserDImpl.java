/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;

import DAO.UserD;
import Entity.UserE;
import Until.*;
import java.util.List;

/**
 *
 * @author HAPPY
 */
public class UserDImpl implements UserD{
    String createSql = "INSERT INTO Users(Username, Password, FullnameUser, StatusUser, Service, PhotoUser, Birthday) VALUES(?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Users SET Password=?, FullnameUser=?, StatusUser=?, Service=?, PhotoUser=?, Birthday=? WHERE Username=?";
    String deleteSql = "DELETE FROM Users WHERE Username=?";
    String findAllSql = "SELECT * FROM Users";
    String findByIdSql = "SELECT * FROM Users WHERE Username=?";

    @Override
    public UserE create(UserE entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getPassword(),
            entity.getFullnameUser(),
            entity.isStatusUser(), // Assuming XJdbc converts boolean to appropriate DB type (String/int)
            entity.isService(),   // Assuming XJdbc converts boolean to appropriate DB type (String/int)
            entity.getPhotoUser(),
            entity.getBirthday() // XJdbc should handle java.util.Date to java.sql.Date conversion
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(UserE entity) {
        Object[] values = {
            entity.getPassword(),
            entity.getFullnameUser(),
            entity.isStatusUser(), // Assuming XJdbc converts boolean to appropriate DB type (String/int)
            entity.isService(),   // Assuming XJdbc converts boolean to appropriate DB type (String/int)
            entity.getPhotoUser(),
            entity.getBirthday(), // XJdbc should handle java.util.Date to java.sql.Date conversion
            entity.getUsername()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    
    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<UserE> findAll() {
        return XQuery.getBeanList(UserE.class, findAllSql);
    }

    @Override
    public UserE findById(String id) {
        return XQuery.getSingleBean(UserE.class, findByIdSql, id);
    }
}
