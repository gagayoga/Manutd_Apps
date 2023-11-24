/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Menu;

import Koneksi.Config;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Random;

/**
 *
 * @author Asus
 */
public class UserPage extends javax.swing.JPanel {

    private int userIdCounter = 1;

    /**
     * Creates new form NotFoundForm
     */
    public UserPage() {
        initComponents();

        LockInputan();
        ButtonEnabled();
        ShowTable();
    }

    private void LockInputan() {
        txtkode.setEnabled(false);
        txtname.setEnabled(false);
        txtemail.setEnabled(false);
        txtpassword.setEnabled(false);
        txttelepon.setEnabled(false);
        txtrole.setEnabled(false);
    }

    private void OpenInputan() {
        txtkode.setEnabled(false);
        txtname.setEnabled(true);
        txtemail.setEnabled(true);
        txtpassword.setEnabled(true);
        txttelepon.setEnabled(true);
        txtrole.setEnabled(true);
    }

    private void ClearForm() {
        txtkode.setEditable(true);
        txtkode.setText(null);
        txtname.setText(null);
        txtemail.setText(null);
        txtpassword.setText(null);
        txtrole.setSelectedIndex(0);
        txttelepon.setText(null);
    }

    private void ButtonEnabled() {
        BCreate.setEnabled(true);
        BUpdate.setEnabled(true);
        BDelete.setEnabled(true);
        BCancel.setEnabled(true);
    }

    private void KodeUser() {
        int randomNum = new Random().nextInt(900) + 100;

        String userId = "USR" + String.format("%03d", randomNum);
        txtkode.setText(userId);
        userIdCounter++;
    }
    // bagian CRUD

    private void ShowTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("UserId.");
        model.addColumn("nama.");
        model.addColumn("Email.");
        model.addColumn("Password.");
        model.addColumn("No telp.");
        model.addColumn("Role.");

        try {
            int no = 1;
            String sql = "SELECT * FROM User";
            Connection conn = (Connection) Config.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6)
                });
                TableUser.setModel(model);
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void CreateQuery() {
        try {
            String sql = "INSERT INTO user VALUES ('" + txtkode.getText() + "','" + txtname.getText() + "','" + txtemail.getText() + "','" + txtpassword.getText() + "','" + txttelepon.getText() + "','" + txtrole.getSelectedItem() + "')";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Create Data User Succesfully.");
            LockInputan();
            ShowTable();
            ClearForm();
            ButtonEnabled();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            System.out.println(e.getMessage());
        }
        
    }

    private void UpdateQuery() {
        try {
            String sqlupdate
                    = "UPDATE user SET id_user='" + txtkode.getText() + "', "
                    + "name='" + txtname.getText() + "', email='" + txtemail.getText() + "', "
                    + "password='" + txtpassword.getText() + "', "
                    + "no_telp='" + txttelepon.getText() + "', "
                    + "role='" + txtrole.getSelectedItem() + "'"
                    + "WHERE id_user='" +txtkode.getText()+ "'";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareCall(sqlupdate);
            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Update Data Berhasil..");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        LockInputan();
        ShowTable();
        ClearForm();
        ButtonEnabled();
    }

    private void DeleteQuery() {
        try {
            String sqldelete
                    = "DELETE FROM user WHERE id_user= '" + txtkode.getText() + "'";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareCall(sqldelete);
            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Delete Data Berhasil..");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        LockInputan();
        ShowTable();
        ClearForm();
        ButtonEnabled();
    }
    
    private void SearchQuery(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("UserId.");
        model.addColumn("nama.");
        model.addColumn("Email.");
        model.addColumn("Password.");
        model.addColumn("No telp.");
        model.addColumn("Role.");

        try {
            String SearchValue = txtsearch.getText().trim();
            int no = 1;
            String sql = "SELECT * FROM user WHERE name LIKE ? OR email LIKE ? OR role LIKE ? OR no_telp LIKE ?";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + SearchValue + "%");
            pstm.setString(2, "%" + SearchValue + "%");
            pstm.setString(3, "%" + SearchValue + "%");
            pstm.setString(4, "%" + SearchValue + "%");
            ResultSet res = pstm.executeQuery();

            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6)
                });
            }
            
            TableUser.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    // selesai bagian CRUD
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtkode = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txttelepon = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableUser = new javax.swing.JTable();
        BCreate = new rojerusan.RSMaterialButtonRectangle();
        BUpdate = new rojerusan.RSMaterialButtonRectangle();
        BDelete = new rojerusan.RSMaterialButtonRectangle();
        BCancel = new rojerusan.RSMaterialButtonRectangle();
        txtrole = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(240, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 290, 5));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 25)); // NOI18N
        jLabel1.setText("MANAGE DATA USER");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 290, 30));

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel2.setText("Kode user");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 120, -1));

        txtkode.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtkode.setEnabled(false);
        txtkode.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txtkode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkodeActionPerformed(evt);
            }
        });
        add(txtkode, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 410, 50));

        txtname.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtname.setMargin(new java.awt.Insets(2, 10, 2, 10));
        add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 480, 50));

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel3.setText("Name user");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 120, -1));

        txtemail.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtemail.setMargin(new java.awt.Insets(2, 10, 2, 10));
        add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 480, 50));

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel4.setText("Email address");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 130, -1));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel5.setText("Password user");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, 130, -1));

        txttelepon.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txttelepon.setMargin(new java.awt.Insets(2, 10, 2, 10));
        add(txttelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 260, 520, 50));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel6.setText("Nomor telepon");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, 140, -1));

        txtsearch.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtsearch.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Search User"));
        txtsearch.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });
        add(txtsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 1040, 60));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel7.setText("Role user");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 330, 120, -1));

        TableUser.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        TableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableUser.setGridColor(new java.awt.Color(204, 204, 204));
        TableUser.setRowHeight(38);
        TableUser.setSelectionBackground(new java.awt.Color(204, 0, 0));
        TableUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableUser);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 1110, 310));

        BCreate.setBackground(new java.awt.Color(71, 71, 71));
        BCreate.setText("CREATE");
        BCreate.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCreateActionPerformed(evt);
            }
        });
        add(BCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 240, 60));

        BUpdate.setBackground(new java.awt.Color(255, 153, 0));
        BUpdate.setText("UPDATE");
        BUpdate.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUpdateActionPerformed(evt);
            }
        });
        add(BUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 450, 250, 60));

        BDelete.setBackground(new java.awt.Color(204, 0, 0));
        BDelete.setText("DELETE");
        BDelete.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });
        add(BDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 450, 250, 60));

        BCancel.setBackground(new java.awt.Color(51, 51, 255));
        BCancel.setText("CLEAR");
        BCancel.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelActionPerformed(evt);
            }
        });
        add(BCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 450, 240, 60));

        txtrole.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtrole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User", " " }));
        txtrole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtroleActionPerformed(evt);
            }
        });
        add(txtrole, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 360, 520, 50));

        jPanel2.setBackground(new java.awt.Color(204, 51, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/Dashboard/Icon Menu/IconCreateCode.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 0, -1, 50));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 60, 50));
        add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 520, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void txtroleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtroleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtroleActionPerformed

    private void txtkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkodeActionPerformed

    private void BCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCreateActionPerformed
        // TODO add your handling code here:
        if (BCreate.getText().equals("CREATE")) {
            BCreate.setText("SUBMIT");
            BUpdate.setEnabled(false);
            BDelete.setEnabled(false);
            BCancel.setText("CANCEL");

            OpenInputan();
        } else if (BCreate.getText().equals("SUBMIT")) {
            if (txtkode.getText().equals("") || txtname.getText().equals("")
                    || txtemail.getText().equals("") || txtpassword.getText().equals("")
                    || txttelepon.getText().equals("")
                    || txtrole.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Input All Inputan");
                txtkode.requestFocus();
            } else {
                CreateQuery();
                BCreate.setText("CREATE");
            }
        }
    }//GEN-LAST:event_BCreateActionPerformed

    private void BUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUpdateActionPerformed
        // TODO add your handling code here:
        if (BUpdate.getText().equals("UPDATE")) {
            BUpdate.setText("SUBMIT");
            BCreate.setEnabled(false);
            BDelete.setEnabled(false);
            BCancel.setText("CANCEL");

            OpenInputan();
        } else if (BUpdate.getText().equals("SUBMIT")) {
            if (txtkode.getText().equals("") || txtname.getText().equals("")
                    || txtemail.getText().equals("") || txtpassword.getText().equals("")
                    || txttelepon.getText().equals("")
                    || txtrole.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Input All Inputan");
                txtkode.requestFocus();
            } else {
                UpdateQuery();
                BUpdate.setText("UPDATE");
            }
        }
    }//GEN-LAST:event_BUpdateActionPerformed

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        // TODO add your handling code here:
        if (BDelete.getText().equals("DELETE")) {
            BDelete.setText("SUBMIT");
            BCreate.setEnabled(false);
            BUpdate.setEnabled(false);
            BCancel.setText("CANCEL");

            OpenInputan();
        } else if (BDelete.getText().equals("SUBMIT")) {
            if (txtkode.getText().equals("") || txtname.getText().equals("")
                    || txtemail.getText().equals("") || txtpassword.getText().equals("")
                    || txttelepon.getText().equals("")
                    || txtrole.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Input All Inputan");
                txtkode.requestFocus();
            } else {
                int inputan = JOptionPane.showConfirmDialog(this,
                        "Apakah Anda yakin delete data tersebut", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (inputan == JOptionPane.YES_OPTION) {
                    DeleteQuery();
                    BDelete.setText("DELETE");
                } else {
                }
            }
        }
    }//GEN-LAST:event_BDeleteActionPerformed

    private void BCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCancelActionPerformed
        // TODO add your handling code here:
        if (BCancel.getText().equals("CLEAR")) {
            ClearForm();
        } else if (BCancel.getText().equals("CANCEL")) {
            LockInputan();
            ButtonEnabled();
            BCancel.setText("CLEAR");
        }
    }//GEN-LAST:event_BCancelActionPerformed

    private void TableUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableUserMouseClicked
        // TODO add your handling code here:
        int baris = TableUser.rowAtPoint(evt.getPoint());
        
        String kodeUser = TableUser.getValueAt(baris, 1).toString();
        txtkode.setText(kodeUser);
        
        String nameUser = TableUser.getValueAt(baris, 2).toString();
        txtname.setText(nameUser);
        
        String emailUser = TableUser.getValueAt(baris, 3).toString();
        txtemail.setText(emailUser);
        
        String passwordUser = TableUser.getValueAt(baris, 4).toString();
        txtpassword.setText(passwordUser);
        
        String teleponUser = TableUser.getValueAt(baris, 5).toString();
        txttelepon.setText(teleponUser);
        
        String roleUser = TableUser.getValueAt(baris, 6).toString();
        txtrole.setSelectedItem(roleUser);
        
        BCreate.setEnabled(false);
    }//GEN-LAST:event_TableUserMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        KodeUser();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        // TODO add your handling code here:
         SearchQuery();
    }//GEN-LAST:event_txtsearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle BCancel;
    private rojerusan.RSMaterialButtonRectangle BCreate;
    private rojerusan.RSMaterialButtonRectangle BDelete;
    private rojerusan.RSMaterialButtonRectangle BUpdate;
    private javax.swing.JTable TableUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtname;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JComboBox<String> txtrole;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txttelepon;
    // End of variables declaration//GEN-END:variables
}
