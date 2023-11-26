/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package MenuAdmin;

import Koneksi.Config;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class PlayerPage extends javax.swing.JPanel {

    /**
     * Creates new form DashboardPage
     */
    public PlayerPage() {
        initComponents();

        ButtonEnabled();
        LockInputan();
        ShowTable();
        ButtonName();
        KodePlayer();
    }

    private void ButtonName() {
        BCreate.setText("CREATE");
        BUpdate.setText("UPDATE");
        BDelete.setText("DELETE");
        BCancel.setText("CLEAR");
    }

    private void LockInputan() {
        txtkode.setEnabled(false);
        txtfirst.setEnabled(false);
        txtlast.setEnabled(false);
        txtnoplayer.setEnabled(false);
        txtkategori.setEnabled(false);
        txtposition.setEnabled(false);
        txtnegara.setEnabled(false);
        BtnFile.setEnabled(false);
        ImagePemain.setEnabled(false);
    }

    private void OpenInputan() {
        txtfirst.setEnabled(true);
        txtlast.setEnabled(true);
        txtnoplayer.setEnabled(true);
        txtkategori.setEnabled(true);
        txtposition.setEnabled(true);
        txtnegara.setEnabled(true);
        BtnFile.setEnabled(true);
        ImagePemain.setEnabled(true);
    }

    private void ClearForm() {
        txtkode.setEditable(true);
        txtfirst.setText(null);
        txtlast.setText(null);
        txtnoplayer.setText(null);
        txtnegara.setText(null);
        txtkategori.setSelectedIndex(1);
        txtposition.setSelectedIndex(1);
        ImagePemain.setIcon(null);
        Lblimage.setText(null);
    }

    private void ButtonEnabled() {
        BCreate.setEnabled(true);
        BUpdate.setEnabled(true);
        BDelete.setEnabled(true);
        BCancel.setEnabled(true);
    }

    public void PositionPlayer() {
        String position = txtkategori.getSelectedItem().toString();
        switch (position) {
            case "GoalKeeper":
                txtposition.removeAllItems();
                String[] GK = {"GoalKeeper"};
                for (String gk : GK) {
                    txtposition.addItem(gk);
                }
                break;
            case "Defenders":
                txtposition.removeAllItems();
                String[] defender = {"Center Back", "Left Back", "Right Back"};
                for (String defend : defender) {
                    txtposition.addItem(defend);
                }
                break;
            case "Midfielders":
                txtposition.removeAllItems();
                String[] gelandang = {"Defensive Midfielder", "Central Midfielder", "Attacking Midfielder"};
                for (String mid : gelandang) {
                    txtposition.addItem(mid);
                }
                break;
            case "Wingers":
                txtposition.removeAllItems();
                String[] sayap = {"Left Winger", "Right Winger"};
                for (String syp : sayap) {
                    txtposition.addItem(syp);
                }
                break;
            case "Forwards":
                txtposition.removeAllItems();
                String[] striker = {"Center Forward", "Seconds Forward"};
                for (String str : striker) {
                    txtposition.addItem(str);
                }
                break;
            default:
                txtposition.removeAllItems();
                break;
        }
    }

    private void SearchQuery() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("Player Id.");
        model.addColumn("Fisrt Name");
        model.addColumn("Last Name");
        model.addColumn("Shirt Number");
        model.addColumn("Position.");
        model.addColumn("Category Position");
        model.addColumn("Country");
        model.addColumn("Image Pemain");

        try {
            String SearchValue = "";
            int no = 1;
            String sql = "SELECT * FROM player WHERE name LIKE ? OR no_punggung LIKE ? OR position LIKE ? OR negara LIKE ?";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + SearchValue + "%");
            pstm.setString(2, "%" + SearchValue + "%");
            pstm.setString(3, "%" + SearchValue + "%");
            pstm.setString(4, "%" + SearchValue + "%");
            ResultSet res = pstm.executeQuery();

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

    private void KodePlayer() {
        try {
            String sql = "SELECT * FROM player ORDER BY id_player DESC";
            Connection conn = (Connection) Config.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            if (res.next()) {
                String NoPlayer = res.getString("id_player").substring(3);
                String IdPlayer = "" + (Integer.parseInt(NoPlayer) + 1);
                String Zero = "";

                if (IdPlayer.length() == 1) {
                    Zero = "000";
                } else if (IdPlayer.length() == 2) {
                    Zero = "00";
                } else if (IdPlayer.length() == 3) {
                    Zero = "0";
                } else if (IdPlayer.length() == 4) {
                    Zero = "";
                }
                txtkode.setText("MUN" + Zero + IdPlayer);
            } else {
                txtkode.setText("MUN0001");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    // bagian CRUD

    private void ShowTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("Player Id.");
        model.addColumn("Fisrt Name");
        model.addColumn("Last Name");
        model.addColumn("Shirt Number");
        model.addColumn("Position.");
        model.addColumn("Category Position");
        model.addColumn("Country");
        model.addColumn("Image Pemain");

        try {
            int no = 1;
            String sql = "SELECT * FROM player";
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
        String kodeplayer, firstname, lastname, nopunggung, posisi, kategori, negara, gambar;

        kodeplayer = txtkode.getText().trim();
        firstname = txtfirst.getText().trim();
        lastname = txtlast.getText().trim();
        nopunggung = txtnoplayer.getText().trim();
        posisi = txtposition.getSelectedItem().toString().trim();
        kategori = txtkategori.getSelectedItem().toString().trim();
        negara = txtnegara.getText().trim();
        gambar = Lblimage.getText().trim();

        try {
            String sql = "INSERT INTO player (id_player,first_name,last_name,no_punggung,position,kategori_position,negara,image) VALUES (?,?,?,?,?,?,?,?)";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, kodeplayer);
            pstm.setString(2, firstname);
            pstm.setString(3, lastname);
            pstm.setString(4, nopunggung);
            pstm.setString(5, posisi);
            pstm.setString(6, kategori);
            pstm.setString(7, negara);
            pstm.setString(8, gambar);
            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Create Data Player Succesfully.");
            LockInputan();
            ShowTable();
            ClearForm();
            ButtonEnabled();
            KodePlayer();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    private void UpdateQuery() {
        String kodeplayer, firstname, lastname, nopunggung, posisi, kategori, negara, gambar;

        kodeplayer = txtkode.getText().trim();
        firstname = txtfirst.getText().trim();
        lastname = txtlast.getText().trim();
        nopunggung = txtnoplayer.getText().trim();
        posisi = txtposition.getSelectedItem().toString().trim();
        kategori = txtkategori.getSelectedItem().toString().trim();
        negara = txtnegara.getText().trim();
        gambar = Lblimage.getText().trim();

        try {
            String sqlupdate
                    = "UPDATE player SET first_name = ?, last_name = ? , no_punggung = ?, position = ?, kategori_position = ?, negara = ?, image =? WHERE id_player =?";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareCall(sqlupdate);
            pstm.setString(1, firstname);
            pstm.setString(2, lastname);
            pstm.setString(3, nopunggung);
            pstm.setString(4, posisi);
            pstm.setString(5, kategori);
            pstm.setString(6, negara);
            pstm.setString(7, gambar);
            pstm.setString(8, kodeplayer);
            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Update Data " + firstname + lastname + "Succesfully");
            ClearForm();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        LockInputan();
        ShowTable();
        ButtonEnabled();
        KodePlayer();
    }

    private void DeleteQuery() {
        String kodeplayer;

        kodeplayer = txtkode.getText().trim();
        try {
            String sqldelete
                    = "DELETE FROM player WHERE id_player= ?";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareCall(sqldelete);
            pstm.setString(1, kodeplayer);
            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Delete Data " + kodeplayer + " Succesfully");
            ClearForm();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        LockInputan();
        ShowTable();
        ButtonEnabled();
        KodePlayer();
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BCreate = new rojerusan.RSMaterialButtonRectangle();
        BUpdate = new rojerusan.RSMaterialButtonRectangle();
        BDelete = new rojerusan.RSMaterialButtonRectangle();
        BCancel = new rojerusan.RSMaterialButtonRectangle();
        jLabel6 = new javax.swing.JLabel();
        txtkode = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtfirst = new javax.swing.JTextField();
        txtnoplayer = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtposition = new javax.swing.JComboBox<>();
        txtkategori = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtnegara = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        ImagePemain = new javax.swing.JLabel();
        Lblimage = new javax.swing.JLabel();
        BtnFile = new javax.swing.JButton();
        txtlast = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablePlayer = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1110, 986));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 290, 5));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 25)); // NOI18N
        jLabel1.setText("MANAGE DATA PLAYER");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 330, 30));

        BCreate.setBackground(new java.awt.Color(71, 71, 71));
        BCreate.setText("CREATE");
        BCreate.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCreateActionPerformed(evt);
            }
        });
        jPanel2.add(BCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 240, 60));

        BUpdate.setBackground(new java.awt.Color(255, 153, 0));
        BUpdate.setText("UPDATE");
        BUpdate.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUpdateActionPerformed(evt);
            }
        });
        jPanel2.add(BUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 520, 250, 60));

        BDelete.setBackground(new java.awt.Color(204, 0, 0));
        BDelete.setText("DELETE");
        BDelete.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(BDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 520, 250, 60));

        BCancel.setBackground(new java.awt.Color(51, 51, 255));
        BCancel.setText("CANCEL");
        BCancel.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelActionPerformed(evt);
            }
        });
        jPanel2.add(BCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 520, 240, 60));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel6.setText("Kode Player");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 140, -1));

        txtkode.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtkode.setEnabled(false);
        txtkode.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanel2.add(txtkode, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 320, 50));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel7.setText("First Name");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 140, -1));

        txtfirst.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtfirst.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanel2.add(txtfirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 380, 50));

        txtnoplayer.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtnoplayer.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanel2.add(txtnoplayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 320, 50));

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel8.setText("Shirt Number");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 140, -1));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel9.setText("Position");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, 140, -1));

        txtposition.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jPanel2.add(txtposition, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, 380, 50));

        txtkategori.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtkategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GoalKeeper", "Defenders", "Midfielders", "Wingers", "Forwards" }));
        txtkategori.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtkategoriItemStateChanged(evt);
            }
        });
        jPanel2.add(txtkategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, 380, 50));

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel10.setText("Position Category");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 160, -1));

        txtnegara.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtnegara.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanel2.add(txtnegara, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 710, 50));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel11.setText("Country");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 140, -1));

        ImagePemain.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.add(ImagePemain, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 130, 315, 290));

        Lblimage.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        Lblimage.setEnabled(false);
        jPanel2.add(Lblimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 100, 310, 20));

        BtnFile.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        BtnFile.setText("Upload Image");
        BtnFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFileActionPerformed(evt);
            }
        });
        jPanel2.add(BtnFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 440, 310, 40));

        txtlast.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtlast.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanel2.add(txtlast, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 320, 50));

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel12.setText("Last Name");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 140, -1));

        TablePlayer.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
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
        TablePlayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePlayerMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TablePlayer);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 1110, 290));

        jTabbedPane1.addTab("Form Manage Player", new javax.swing.ImageIcon(getClass().getResource("/asset/Dashboard/Icon Menu/HomeButton.png")), jPanel2); // NOI18N

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 940));
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
            if (txtkode.getText().equals("") || txtfirst.getText().equals("")
                    || txtlast.getText().equals("")
                    || txtnoplayer.getText().equals("") || txtkategori.getSelectedIndex() == -1
                    || txtposition.getSelectedIndex() == -1
                    || txtnegara.getText().equals("") || Lblimage.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Input All Inputan");
                txtkode.requestFocus();
            } else {
                CreateQuery();
                BCreate.setText("CREATE");
            }
            KodePlayer();
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
            if (txtkode.getText().equals("") || txtfirst.getText().equals("")
                    || txtlast.getText().equals("")
                    || txtnoplayer.getText().equals("") || txtkategori.getSelectedIndex() == -1
                    || txtposition.getSelectedIndex() == -1
                    || txtnegara.getText().equals("") || Lblimage.getText().equals("")) {
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
            if (txtkode.getText().equals("") || txtfirst.getText().equals("")
                    || txtlast.getText().equals("")
                    || txtnoplayer.getText().equals("") || txtkategori.getSelectedIndex() == -1
                    || txtposition.getSelectedIndex() == -1
                    || txtnegara.getText().equals("") || Lblimage.getText().equals("")) {
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
            ButtonName();
            KodePlayer();
            BCancel.setText("CLEAR");
        }
    }//GEN-LAST:event_BCancelActionPerformed

    private void txtkategoriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtkategoriItemStateChanged
        // TODO add your handling code here:
        PositionPlayer();
    }//GEN-LAST:event_txtkategoriItemStateChanged

    private void BtnFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFileActionPerformed
        // TODO add your handling code here:
        JFileChooser filechooser = new JFileChooser();
        filechooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "png", "jpeg", "svg");
        filechooser.addChoosableFileFilter(filter);
        int result = filechooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filechooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            Lblimage.setText(path);

            try {

                String ImagePath = Lblimage.getText().toString();
                ImageIcon imageIcon = new ImageIcon(ImagePath);

                int labelWidth = 330;
                int labelHeight = 300;

                int imageWidth = imageIcon.getIconWidth();
                int imageHeight = imageIcon.getIconHeight();

                double scaleX = (double) labelWidth / (double) imageWidth;
                double scaleY = (double) labelHeight / (double) imageHeight;
                double scale = Math.min(scaleX, scaleY);

                Image scaledImage = imageIcon.getImage().getScaledInstance((int) (scale * imageWidth), (int) (scale * imageHeight), Image.SCALE_SMOOTH);

                ImagePemain.setIcon(new ImageIcon(scaledImage));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_BtnFileActionPerformed

    private void TablePlayerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePlayerMouseClicked
        // TODO add your handling code here:
        int baris = TablePlayer.rowAtPoint(evt.getPoint());

        String kodePlayer = TablePlayer.getValueAt(baris, 1).toString();
        txtkode.setText(kodePlayer);

        String namaPlayer = TablePlayer.getValueAt(baris, 2).toString();
        txtfirst.setText(namaPlayer);

        String lastname = TablePlayer.getValueAt(baris, 3).toString();
        txtlast.setText(lastname);

        String noJersey = TablePlayer.getValueAt(baris, 4).toString();
        txtnoplayer.setText(noJersey);

//        String position = TablePlayer.getValueAt(baris, 5).toString();
//        txtposition.setSelectedItem(position);
        String kategori = TablePlayer.getValueAt(baris, 6).toString();
        txtkategori.setSelectedItem(kategori);

        String negara = TablePlayer.getValueAt(baris, 7).toString();
        txtnegara.setText(negara);

        String pathImage = TablePlayer.getValueAt(baris, 8).toString();
        Lblimage.setText(pathImage);

        ImageIcon imageIcon = new ImageIcon(pathImage);

        int labelWidth = 340;
        int labelHeight = 300;

        int imageWidth = imageIcon.getIconWidth();
        int imageHeight = imageIcon.getIconHeight();

        double scaleX = (double) labelWidth / (double) imageWidth;
        double scaleY = (double) labelHeight / (double) imageHeight;
        double scale = Math.min(scaleX, scaleY);

        Image scaledImage = imageIcon.getImage().getScaledInstance((int) (scale * imageWidth), (int) (scale * imageHeight), Image.SCALE_SMOOTH);

        ImagePemain.setIcon(new ImageIcon(scaledImage));

        BCreate.setEnabled(false);
    }//GEN-LAST:event_TablePlayerMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle BCancel;
    private rojerusan.RSMaterialButtonRectangle BCreate;
    private rojerusan.RSMaterialButtonRectangle BDelete;
    private rojerusan.RSMaterialButtonRectangle BUpdate;
    private javax.swing.JButton BtnFile;
    private javax.swing.JLabel ImagePemain;
    private javax.swing.JLabel Lblimage;
    private javax.swing.JTable TablePlayer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtfirst;
    private javax.swing.JComboBox<String> txtkategori;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtlast;
    private javax.swing.JTextField txtnegara;
    private javax.swing.JTextField txtnoplayer;
    private javax.swing.JComboBox<String> txtposition;
    // End of variables declaration//GEN-END:variables
}
