package DAO;


import Until.XDialog;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author PC
 */
public interface LoginController {
    void open();

    void login();

    default void exit() {
        if (XDialog.confirm("Bạn muốn kết thúc?")) {
            System.exit(0);
}
}
}
