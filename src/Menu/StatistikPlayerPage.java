/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Menu;

import Koneksi.Config;
import Model.Player;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class StatistikPlayerPage extends javax.swing.JPanel {
    
    ArrayList<Player> arrPlayer = new ArrayList<>();
    String nama;

    /**
     * Creates new form StatistikPlayerPage
     */
    public StatistikPlayerPage() {
        initComponents();

        LockInputan();
        ButtonEnabled();
        ShowTable();
        KodeStatistik();
        ShowDataPlayer();
    }

    private void LockInputan() {
        txtkode.setEnabled(false);
        txtplayer.setEnabled(false);
        txtname.setEnabled(false);
        txtappea.setEnabled(false);
        txtgoal.setEnabled(false);
        txtasist.setEnabled(false);
        txtyellow.setEnabled(false);
        txtred.setEnabled(false);
        txtshoot.setEnabled(false);
    }

    private void OpenInputan() {
        txtplayer.setEnabled(true);
        txtname.setEnabled(true);
        txtappea.setEnabled(true);
        txtgoal.setEnabled(true);
        txtasist.setEnabled(true);
        txtyellow.setEnabled(true);
        txtred.setEnabled(true);
        txtshoot.setEnabled(true);
    }

    int kode = 5000;

    private void KodeStatistik() {
        String kodeStatistik = "STA" + kode;
        kode++;

        txtkode.setText(kodeStatistik);
    }

    private void ClearForm() {
        txtplayer.setSelectedItem(null);
        txtname.setText(null);
        txtappea.setText(null);
        txtgoal.setText(null);
        txtasist.setText(null);
        txtyellow.setText(null);
        txtred.setText(null);
        txtshoot.setText(null);
    }

    private void ButtonEnabled() {
        BCreate.setEnabled(true);
        BUpdate.setEnabled(true);
        BDelete.setEnabled(true);
        BCancel.setEnabled(true);
    }
    
    private void ShowNamePlayer(){
        String kode = txtplayer.getSelectedItem().toString().trim();
        
        try {
            String sql = "SELECT * FROM player WHERE id_player ='"+kode+"'";
            Connection conn = (Connection) Config.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            
            while(res.next()){
               nama  = res.getString("name");
               txtname.setText(nama);
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
    
    private void ShowDataPlayer(){
        txtplayer.removeAllItems();
        
         try {
            String sql = "SELECT * FROM player";
            Connection conn = (Connection) Config.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            
            while(res.next()){
                arrPlayer.add(new Player(
                        res.getNString("id_player"),
                        res.getString("name")
                ));
            }
            for (int i=0; i < arrPlayer.size(); i++ ){
                txtplayer.addItem(arrPlayer.get(i).getId_player());
            }
            
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    // bagian CRUD
    private void ShowTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("Id Statistik");
        model.addColumn("Kode Player");
        model.addColumn("Appearances");
        model.addColumn("Goal");
        model.addColumn("Asist");
        model.addColumn("Yellow Card");
        model.addColumn("Red Card");
        model.addColumn("Shooting");

        try {
            int no = 1;
            String sql = "SELECT * FROM statistik_player";
            Connection conn = (Connection) Config.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2),
                    res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8)
                });
            }
            TablePlayer.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void CreateQuery() {
        String kodestatistik, kodeplayer, appereances, goal, asist, yellow, card, shoot;

        kodestatistik = txtkode.getText().trim();
        kodeplayer = txtplayer.getSelectedItem().toString().trim();
        appereances = txtappea.getText().trim();
        goal = txtgoal.getText().trim();
        asist = txtasist.getText().trim();
        yellow = txtyellow.getText().trim();
        card = txtred.getText().trim();
        shoot = txtshoot.getText().trim();

        try {
            String sql = "INSERT INTO player (id_statistik, id_player ,appearances, gol, asist, yellow_card, red_card, shotting) VALUES (?,?,?,?,?,?,?)";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, kodestatistik);
            pstm.setString(2, kodeplayer);
            pstm.setString(3, appereances);
            pstm.setString(4, goal);
            pstm.setString(5, asist);
            pstm.setString(6, yellow);
            pstm.setString(7, card);
            pstm.setString(7, shoot);
            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Create Data User Succesfully.");
            LockInputan();
            ShowTable();
            ClearForm();
            ButtonEnabled();
            KodeStatistik();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    private void UpdateQuery() {
        String kodestatistik, kodeplayer, appereances, goal, asist, yellow, card, shoot;

        kodestatistik = txtkode.getText().trim();
        kodeplayer = txtplayer.getSelectedItem().toString().trim();
        appereances = txtappea.getText().trim();
        goal = txtgoal.getText().trim();
        asist = txtasist.getText().trim();
        yellow = txtyellow.getText().trim();
        card = txtred.getText().trim();
        shoot = txtshoot.getText().trim();

        try {
            String sqlupdate
                    = "UPDATE statistik_player SET id_player = ? , appearances = ?, gol = ?, asist = ?, yellow_card = ?, red_card =?, shotting=? WHERE id_statistik =?";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareCall(sqlupdate);
            pstm.setString(1, kodeplayer);
            pstm.setString(2, appereances);
            pstm.setString(3, goal);
            pstm.setString(4, asist);
            pstm.setString(5, yellow);
            pstm.setString(6, card);
            pstm.setString(7, shoot);
            pstm.setString(8, kodestatistik);
            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Update Data Berhasil..");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        LockInputan();
        ShowTable();
        ClearForm();
        ButtonEnabled();
        KodeStatistik();
    }

    private void DeleteQuery() {
        String kodeplayer;

        kodeplayer = txtkode.getText().trim();
        try {
            String sqldelete
                    = "DELETE FROM statistik_player WHERE id_statistik= ?";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareCall(sqldelete);
            pstm.setString(1, kodeplayer);
            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Delete Data Berhasil..");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        LockInputan();
        ShowTable();
        ClearForm();
        ButtonEnabled();
        KodeStatistik();
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablePlayer = new javax.swing.JTable();
        BCreate = new rojerusan.RSMaterialButtonRectangle();
        BUpdate = new rojerusan.RSMaterialButtonRectangle();
        BDelete = new rojerusan.RSMaterialButtonRectangle();
        BCancel = new rojerusan.RSMaterialButtonRectangle();
        jLabel6 = new javax.swing.JLabel();
        txtkode = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        txtplayer = new javax.swing.JComboBox<>();
        txtasist = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtgoal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtappea = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtyellow = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtred = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtshoot = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 25)); // NOI18N
        jLabel1.setText("MANAGE DATA STATISTIK PLAYER");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 490, 30));

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

        TablePlayer.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        TablePlayer.setModel(new javax.swing.table.DefaultTableModel(
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
        TablePlayer.setRowHeight(35);
        TablePlayer.setSelectionBackground(new java.awt.Color(204, 0, 0));
        TablePlayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePlayerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablePlayer);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 1110, 350));

        BCreate.setBackground(new java.awt.Color(0, 153, 0));
        BCreate.setText("CREATE");
        BCreate.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCreateActionPerformed(evt);
            }
        });
        add(BCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 240, 60));

        BUpdate.setBackground(new java.awt.Color(255, 153, 0));
        BUpdate.setText("UPDATE");
        BUpdate.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUpdateActionPerformed(evt);
            }
        });
        add(BUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 480, 250, 60));

        BDelete.setBackground(new java.awt.Color(204, 0, 0));
        BDelete.setText("DELETE");
        BDelete.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });
        add(BDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 480, 250, 60));

        BCancel.setBackground(new java.awt.Color(51, 51, 255));
        BCancel.setText("CANCEL");
        BCancel.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelActionPerformed(evt);
            }
        });
        add(BCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 480, 240, 60));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel6.setText("Kode Statistik");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 140, -1));

        txtkode.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtkode.setMargin(new java.awt.Insets(2, 10, 2, 10));
        add(txtkode, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 320, 50));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel7.setText("Kode Player");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 140, -1));

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel8.setText("Name Player");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 140, -1));

        txtname.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtname.setMargin(new java.awt.Insets(2, 10, 2, 10));
        add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 320, 50));

        txtplayer.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtplayer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtplayerItemStateChanged(evt);
            }
        });
        add(txtplayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 320, 50));

        txtasist.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtasist.setMargin(new java.awt.Insets(2, 10, 2, 10));
        add(txtasist, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 370, 300, 50));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel9.setText("Asist");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 340, 140, -1));

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel10.setText("Goal");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 240, 140, -1));

        txtgoal.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtgoal.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txtgoal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgoalActionPerformed(evt);
            }
        });
        add(txtgoal, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 300, 50));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel11.setText("Appearances");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 140, -1));

        txtappea.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtappea.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txtappea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtappeaActionPerformed(evt);
            }
        });
        add(txtappea, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 300, 50));

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel12.setText("Yellow Card");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 140, 140, -1));

        txtyellow.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtyellow.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txtyellow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtyellowActionPerformed(evt);
            }
        });
        add(txtyellow, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 170, 300, 50));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel13.setText("Red Card");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 240, 140, -1));

        txtred.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtred.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txtred.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtredActionPerformed(evt);
            }
        });
        add(txtred, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 270, 300, 50));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel14.setText("Shooting");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 340, 140, -1));

        txtshoot.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtshoot.setMargin(new java.awt.Insets(2, 10, 2, 10));
        add(txtshoot, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 370, 300, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void BCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCreateActionPerformed
        // TODO add your handling code here:
        if (BCreate.getText().equals("CREATE")) {
            BCreate.setText("SUBMIT");
            BUpdate.setEnabled(false);
            BDelete.setEnabled(false);
            BCancel.setText("CANCEL");

            OpenInputan();
        } else if (BCreate.getText().equals("SUBMIT")) {
            if (txtkode.getText().equals("") || txtplayer.getSelectedIndex() == -1
                    || txtappea.getText().equals("") || txtgoal.getText().equals("")
                    || txtasist.getText().equals("")
                    || txtyellow.getText().equals("")
                    || txtred.getText().equals("")
                    || txtshoot.getText().equals("")) {
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
            if (txtkode.getText().equals("") || txtplayer.getSelectedIndex() == -1
                    || txtappea.getText().equals("") || txtgoal.getText().equals("")
                    || txtasist.getText().equals("")
                    || txtyellow.getText().equals("")
                    || txtred.getText().equals("")
                    || txtshoot.getText().equals("")) {
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
            if (txtkode.getText().equals("") || txtplayer.getSelectedIndex() == -1
                    || txtappea.getText().equals("") || txtgoal.getText().equals("")
                    || txtasist.getText().equals("")
                    || txtyellow.getText().equals("")
                    || txtred.getText().equals("")
                    || txtshoot.getText().equals("")) {
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
            ClearForm();
            ButtonEnabled();
            OpenInputan();
            BCancel.setText("CLEAR");
        }
    }//GEN-LAST:event_BCancelActionPerformed

    private void txtgoalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgoalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgoalActionPerformed

    private void txtappeaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtappeaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtappeaActionPerformed

    private void txtyellowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtyellowActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtyellowActionPerformed

    private void txtredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtredActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtredActionPerformed

    private void TablePlayerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePlayerMouseClicked
        // TODO add your handling code here:
        int baris = TablePlayer.rowAtPoint(evt.getPoint());

        String kodeStatis = TablePlayer.getValueAt(baris, 1).toString();
        txtkode.setText(kodeStatis);

        String namaPlayer = TablePlayer.getValueAt(baris, 2).toString();
        txtplayer.setSelectedItem(namaPlayer);

        String appearan = TablePlayer.getValueAt(baris, 3).toString();
        txtappea.setText(appearan);

        String goall = TablePlayer.getValueAt(baris, 4).toString();
        txtgoal.setText(goall);

        String asistt = TablePlayer.getValueAt(baris, 6).toString();
        txtasist.setText(asistt);

        String yellowcard = TablePlayer.getValueAt(baris, 6).toString();
        txtyellow.setText(yellowcard);
        
        String redcard = TablePlayer.getValueAt(baris, 6).toString();
        txtred.setText(redcard);
        
        String shoot = TablePlayer.getValueAt(baris, 6).toString();
        txtshoot.setText(shoot);
        
        BCreate.setEnabled(false);
    }//GEN-LAST:event_TablePlayerMouseClicked

    private void txtplayerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtplayerItemStateChanged
        // TODO add your handling code here:
        ShowNamePlayer();
    }//GEN-LAST:event_txtplayerItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle BCancel;
    private rojerusan.RSMaterialButtonRectangle BCreate;
    private rojerusan.RSMaterialButtonRectangle BDelete;
    private rojerusan.RSMaterialButtonRectangle BUpdate;
    private javax.swing.JTable TablePlayer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtappea;
    private javax.swing.JTextField txtasist;
    private javax.swing.JTextField txtgoal;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtname;
    private javax.swing.JComboBox<String> txtplayer;
    private javax.swing.JTextField txtred;
    private javax.swing.JTextField txtshoot;
    private javax.swing.JTextField txtyellow;
    // End of variables declaration//GEN-END:variables
}
