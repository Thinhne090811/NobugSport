/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI.Manager;

import DAO.CategoryController;
import DAO.Impl.CategoryDImpl;
import Entity.CategoryE;
import Until.XDialog;
import java.util.List;
import java.util.Locale.Category;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class ManagerCategory extends JDialog implements CategoryController {

    /**
     * Creates new form NewJDialog
     */
    public ManagerCategory(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    CategoryDImpl dao = new CategoryDImpl();
    List<CategoryE> items = List.of();

    @Override
    public void open() {
        this.setLocationRelativeTo(null);
        this.fillToTable();
        this.clear();
    }

    @Override
    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblcate.getModel();
        model.setRowCount(0); // Xóa các dòng cũ

        items = dao.findAll(); // Lấy danh sách từ DB
        for (CategoryE item : items) {
            model.addRow(new Object[]{
                item.getIdCategory(),
                item.getNameCategory(),
                false // Checkbox mặc định chưa chọn
            });
        }
    }

    @Override
    public void edit() {
        CategoryE entity = items.get(tblcate.getSelectedRow());
        this.setForm(entity);
        this.setEditable(true);

        tabs.setSelectedIndex(1);
    }

    @Override
    public void checkAll() {
        this.setCheckedAll(true);
    }

    @Override
    public void uncheckAll() {
        this.setCheckedAll(false);
    }

    private void setCheckedAll(boolean checked) {
        for (int i = 0; i < tblcate.getRowCount(); i++) {
            tblcate.setValueAt(checked, i, 2);
        }
    }

    @Override
    public void deleteCheckedItems() {
        if (XDialog.confirm("Bạn thực sự muốn xóa các mục đã chọn?")) {
            int deletedCount = 0;
            try {
                // Duyệt ngược để tránh lỗi chỉ số do xóa thay đổi dòng
                for (int i = tblcate.getRowCount() - 1; i >= 0; i--) {
                    Boolean checked = (Boolean) tblcate.getValueAt(i, 2);
                    if (Boolean.TRUE.equals(checked)) {
                        String id = (String) tblcate.getValueAt(i, 0); // Lấy ID từ cột 0
                        dao.deleteById(id);
                        deletedCount++;
                    }
                }

                if (deletedCount > 0) {
                    JOptionPane.showMessageDialog(this, "Đã xóa " + deletedCount + " mục thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một mục để xóa.");
                }

                this.fillToTable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Xóa thất bại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setForm(CategoryE entity) {
        txtId.setText(entity.getIdCategory());
        txtNamecate.setText(entity.getNameCategory());
    }

    @Override
    public CategoryE getForm() {
        CategoryE entity = new CategoryE();
        entity.setIdCategory(txtId.getText().trim());
        entity.setNameCategory(txtNamecate.getText().trim());
        return entity;
    }

    @Override
    public void create() {
        try {
            CategoryE entity = this.getForm();
            dao.create(entity);
            this.fillToTable();
            this.clear();
            JOptionPane.showMessageDialog(this, "Them dc uiii!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Them hem dcc: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        try {
            CategoryE entity = this.getForm();
            dao.update(entity);
            this.fillToTable();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        if (XDialog.confirm("Muon xoa thiet hem")) {
            try {
                String id = txtId.getText();
                dao.deleteById(id);
                this.fillToTable();
                this.clear();
                JOptionPane.showMessageDialog(this, "Xoa dc ui!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Xoa that bai: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void clear() {
        this.setForm(new CategoryE());
        this.setEditable(false);
    }

    @Override
    public void setEditable(boolean editable) {
        txtId.setEnabled(!editable);
        btnCreate.setEnabled(!editable);
        btnUpdate.setEnabled(editable);
        btnDelete.setEnabled(editable);
        int rowCount = tblcate.getRowCount();
//        btnMoveFirst.setEnabled(editable && rowCount > 0);
//        btnMovePrevious.setEnabled(editable && rowCount > 0);
//        btnMoveNext.setEnabled(editable && rowCount > 0);
//        btnMoveLast.setEnabled(editable && rowCount > 0);
    }

//    @Override
//    public void moveFirst() {
//        try {
//            if (tblcate.getRowCount() > 0) {
//                moveTo(0);
//            } else {
//                JOptionPane.showMessageDialog(this, "Không có dữ liệu để di chuyển!");
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Lỗi khi di chuyển về đầu: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void movePrevious() {
//        try {
//            int index = tblcate.getSelectedRow();
//            if (index > 0) {
//                moveTo(index - 1);
//            } else {
//                JOptionPane.showMessageDialog(this, "Đã ở đầu danh sách!");
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Lỗi khi di chuyển lùi: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void moveNext() {
//        try {
//            int index = tblCategories.getSelectedRow();
//            if (index < tblCategories.getRowCount() - 1) {
//                moveTo(index + 1);
//            } else {
//                JOptionPane.showMessageDialog(this, "Đã ở cuối danh sách!");
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Lỗi khi di chuyển tới: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void moveLast() {
//        try {
//            int rowCount = tblCategories.getRowCount();
//            if (rowCount > 0) {
//                moveTo(rowCount - 1);
//            } else {
//                JOptionPane.showMessageDialog(this, "Không có dữ liệu để di chuyển!");
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Lỗi khi di chuyển về cuối: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void moveTo(int index) {
//        if (index < 0) {
//            this.moveLast();
//        } else if (index >= tblCategories.getRowCount()) {
//            this.moveFirst();
//        } else {
//            tblCategories.clearSelection();
//            tblCategories.setRowSelectionInterval(index, index);
//            this.edit();
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcate = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnClear = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNamecate = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jButton8.setText("CHỌN TẤT CẢ");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("BỎ CHỌN TẤT CẢ");

        jButton10.setText("XÓA CHỌN TẤT CẢ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("QUẢN LÝ SẢN PHẨM");

        tblcate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "MÃ LOẠI", "TÊN LOẠI"
            }
        ));
        jScrollPane1.setViewportView(tblcate);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(jLabel2)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        tabs.addTab("tab1", jPanel1);

        btnClear.setFont(new java.awt.Font("Arial Unicode MS", 0, 18)); // NOI18N
        btnClear.setText("LÀM MỚI");

        jLabel4.setText("TÊN LOẠI");

        jLabel5.setText("MÃ LOẠI");

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        btnCreate.setFont(new java.awt.Font("Arial Unicode MS", 0, 18)); // NOI18N
        btnCreate.setText("THÊM");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Arial Unicode MS", 0, 18)); // NOI18N
        btnUpdate.setText("SỬA");

        btnDelete.setFont(new java.awt.Font("Arial Unicode MS", 0, 18)); // NOI18N
        btnDelete.setText("XÓA");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("QUẢN LÝ SẢN PHẨM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(255, 255, 255))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(txtNamecate, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 191, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(70, 70, 70)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(72, 72, 72)
                            .addComponent(btnCreate)
                            .addGap(78, 78, 78)
                            .addComponent(btnUpdate)
                            .addGap(72, 72, 72)
                            .addComponent(btnDelete)
                            .addGap(69, 69, 69)
                            .addComponent(btnClear)))
                    .addContainerGap(73, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamecate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(164, 164, 164))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(135, 135, 135)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(169, 169, 169)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCreate)
                            .addComponent(btnUpdate))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete)
                            .addComponent(btnClear)))
                    .addContainerGap(62, Short.MAX_VALUE)))
        );

        tabs.addTab("tab2", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        this.create();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.open();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManagerCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ManagerCategory dialog = new ManagerCategory(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblcate;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNamecate;
    // End of variables declaration//GEN-END:variables

    @Override
    public void moveFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void movePrevious() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void moveNext() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void moveLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void moveTo(int rowIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
