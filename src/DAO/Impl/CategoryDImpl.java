/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;

import DAO.CategoriesDaoD;
import Entity.CategoryE;
import Until.*;
import java.util.List;

/**
 *
 * @author HAPPY
 */
public class CategoryDImpl implements CategoriesDaoD {
    String createSql = "INSERT INTO Categories(IdCategory, NameCategory) VALUES(?, ?)";
    String updateSql = "UPDATE Categories SET NameCategory=? WHERE IdCategory=?";
    String deleteSql = "DELETE FROM Categories WHERE IdCategory=?";
    String findAllSql = "SELECT * FROM Categories";
    String findByIdSql = "SELECT * FROM Categories WHERE IdCategory=?";

    @Override
    public CategoryE create(CategoryE entity) {
        Object[] values = {
            entity.getIdCategory(),
            entity.getNameCategory()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(CategoryE entity) {
        Object[] values = {
            entity.getNameCategory(),
            entity.getIdCategory()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<CategoryE> findAll() {
        return XQuery.getBeanList(CategoryE.class, findAllSql);
    }

    @Override
    public CategoryE findById(String id) {
        return XQuery.getSingleBean(CategoryE.class, findByIdSql, id);
    }
}
