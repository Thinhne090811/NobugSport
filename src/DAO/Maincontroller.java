/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import UI.ChangePasswordJDialog;
import UI.History;
import UI.Login;
import UI.Manager.ManagerCategory;
import UI.Manager.ManagerProduct;
import UI.Sale;
import UI.Welcome;
import Until.XDialog;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author PC
 */
public interface Maincontroller {
    void init();

    default void exit() {
        if (XDialog.confirm("Bạn muốn kết thúc?")) {
            System.exit(0);
        }
    }
    default void showJDialog(JDialog dialog) {
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    default void showWelcomeJDialog(JFrame frame) {
        this.showJDialog(new Welcome(frame, true));
    }
    default void showLoginJDialog(JFrame frame) {
        this.showJDialog(new Login(frame, true));
    }
    default void showChangePasswordJDialog(JFrame frame) {
        this.showJDialog(new ChangePasswordJDialog(frame, true));
    }

    default void showSalesJDialog(JFrame frame) {
        this.showJDialog(new Sale(frame, true));
    }

    default void showHistoryJDialog(JFrame frame) {
        this.showJDialog(new History (frame, true));
    }

    default void showProductJDialog(JFrame frame) {
        this.showJDialog(new ManagerProduct(frame, true));

    }

//    default void showCategoryManagerJDialog(JFrame frame) {
//        this.showJDialog(new ManagerCategory(frame, true));
//    }
//
//    default void showCardManagerJDialog(JFrame frame) {
//        this.showJDialog(new CardManagerJDialog(frame, true));
//    }
//
//    default void showBillManagerJDialog(JFrame frame) {
//        this.showJDialog(new BillManagerJDialog(frame, true));
//    }
//
//    default void showUserManagerJDialog(JFrame frame) {
//        this.showJDialog(new UserManagerJDialog(frame, true));
//    }
//
//    default void showRevenueManagerJDialog(JFrame frame) {
//        this.showJDialog(new RevenueManagerJDialog2(frame, true));
//    }
    
}
