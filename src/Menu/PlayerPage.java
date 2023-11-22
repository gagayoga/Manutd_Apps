/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Menu;

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
        KodePlayer();
    }

    private void LockInputan() {
        txtkode.setEnabled(false);
        txtname.setEnabled(false);
        txtnoplayer.setEnabled(false);
        txtkategori.setEnabled(false);
        txtposition.setEnabled(false);
        txtnegara.setEnabled(false);
        BtnFile.setEnabled(false);
        ImagePemain.setEnabled(false);
    }

    private void OpenInputan() {
        txtname.setEnabled(true);
        txtnoplayer.setEnabled(true);
        txtkategori.setEnabled(true);
        txtposition.setEnabled(true);
        txtnegara.setEnabled(true);
        BtnFile.setEnabled(true);
        ImagePemain.setEnabled(true);
    }

    private void ClearForm() {
        txtkode.setEditable(true);
        txtname.setText(null);
        txtnoplayer.setText(null);
        txtnegara.setText(null);
        txtkategori.setSelectedIndex(0);
        txtposition.setSelectedIndex(0);
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

    int kode = 2001;

    private void KodePlayer() {
        String kodePlayer = "MUN" + kode;
        kode++;

        txtkode.setText(kodePlayer);
    }
    // bagian CRUD

    private void ShowTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("Player Id.");
        model.addColumn("Player Name");
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
                    res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7)
                });
            }
            TablePlayer.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void CreateQuery() {
        String kodeplayer, nameplayer, nopunggung, posisi, kategori, negara, gambar;

        kodeplayer = txtkode.getText().trim();
        nameplayer = txtname.getText().trim();
        nopunggung = txtnoplayer.getText().trim();
        posisi = txtposition.getSelectedItem().toString().trim();
        kategori = txtkategori.getSelectedItem().toString().trim();
        negara = txtnegara.getText().trim();
        gambar = Lblimage.getText().trim();

        try {
            String sql = "INSERT INTO player (id_player,name,no_punggung,position,kategori_position,negara,image) VALUES (?,?,?,?,?,?,?)";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, kodeplayer);
            pstm.setString(2, nameplayer);
            pstm.setString(3, nopunggung);
            pstm.setString(4, posisi);
            pstm.setString(5, kategori);
            pstm.setString(6, negara);
            pstm.setString(7, gambar);
            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Create Data User Succesfully.");
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
        String kodeplayer, nameplayer, nopunggung, posisi, kategori, negara, gambar;

        kodeplayer = txtkode.getText().trim();
        nameplayer = txtname.getText().trim();
        nopunggung = txtnoplayer.getText().trim();
        posisi = txtposition.getSelectedItem().toString().trim();
        kategori = txtkategori.getSelectedItem().toString().trim();
        negara = txtnegara.getText().trim();
        gambar = Lblimage.getText().trim();

        try {
            String sqlupdate
                    = "UPDATE player SET name = ? , no_punggung = ?, position = ?, kategori_position = ?, negara = ?, image =? WHERE id_player =?";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareCall(sqlupdate);
            pstm.setString(1, nameplayer);
            pstm.setString(2, nopunggung);
            pstm.setString(3, posisi);
            pstm.setString(4, kategori);
            pstm.setString(5, negara);
            pstm.setString(6, gambar);
            pstm.setString(7, kodeplayer);
            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Update Data Berhasil..");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        LockInputan();
        ShowTable();
        ClearForm();
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

            JOptionPane.showMessageDialog(null, "Delete Data Berhasil..");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        LockInputan();
        ShowTable();
        ClearForm();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablePlayer = new javax.swing.JTable();
        BCreate = new rojerusan.RSMaterialButtonRectangle();
        BUpdate = new rojerusan.RSMaterialButtonRectangle();
        BDelete = new rojerusan.RSMaterialButtonRectangle();
        BCancel = new rojerusan.RSMaterialButtonRectangle();
        jLabel6 = new javax.swing.JLabel();
        txtkode = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
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
        jLabel1.setText("MANAGE DATA PLAYER");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 330, 30));

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
        jScrollPane1.setViewportView(TablePlayer);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 1110, 380));

        BCreate.setBackground(new java.awt.Color(0, 153, 0));
        BCreate.setText("CREATE");
        BCreate.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCreateActionPerformed(evt);
            }
        });
        add(BCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 240, 60));

        BUpdate.setBackground(new java.awt.Color(255, 153, 0));
        BUpdate.setText("UPDATE");
        BUpdate.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUpdateActionPerformed(evt);
            }
        });
        add(BUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 490, 250, 60));

        BDelete.setBackground(new java.awt.Color(204, 0, 0));
        BDelete.setText("DELETE");
        BDelete.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });
        add(BDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 490, 250, 60));

        BCancel.setBackground(new java.awt.Color(51, 51, 255));
        BCancel.setText("CANCEL");
        BCancel.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelActionPerformed(evt);
            }
        });
        add(BCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 490, 240, 60));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel6.setText("Kode Player");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 140, -1));

        txtkode.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtkode.setMargin(new java.awt.Insets(2, 10, 2, 10));
        add(txtkode, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 370, 50));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel7.setText("Player Name");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 140, -1));

        txtname.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtname.setMargin(new java.awt.Insets(2, 10, 2, 10));
        add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 370, 50));

        txtnoplayer.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtnoplayer.setMargin(new java.awt.Insets(2, 10, 2, 10));
        add(txtnoplayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 370, 50));

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel8.setText("Shirt Number");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 140, -1));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel9.setText("Position");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, 140, -1));

        txtposition.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        add(txtposition, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 370, 50));

        txtkategori.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtkategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GoalKeeper", "Defenders", "Midfielders", "Wingers", "Forwards" }));
        txtkategori.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtkategoriItemStateChanged(evt);
            }
        });
        add(txtkategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 370, 50));

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel10.setText("Position Category");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 160, -1));

        txtnegara.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtnegara.setMargin(new java.awt.Insets(2, 10, 2, 10));
        add(txtnegara, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, 370, 50));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel11.setText("Country");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 140, -1));

        ImagePemain.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(ImagePemain, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 160, 260, 220));

        Lblimage.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        Lblimage.setEnabled(false);
        add(Lblimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, 260, 20));

        BtnFile.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        BtnFile.setText("Upload Image");
        BtnFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFileActionPerformed(evt);
            }
        });
        add(BtnFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 390, 260, 40));
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
            if (txtkode.getText().equals("") || txtname.getText().equals("")
                    || txtnoplayer.getText().equals("") || txtkategori.getSelectedIndex() == -1
                    || txtposition.getSelectedIndex() == -1
                    || txtnegara.getText().equals("") || Lblimage.getText().equals("")) {
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
            if (txtkode.getText().equals("") || txtname.getText().equals("")
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
            LockInputan();
        } else if (BCancel.getText().equals("CANCEL")) {
            ClearForm();
            LockInputan();
            ButtonEnabled();
            OpenInputan();
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

                int labelWidth = 260;
                int labelHeight = 220;

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
        txtname.setText(namaPlayer);

        String noJersey = TablePlayer.getValueAt(baris, 3).toString();
        txtnoplayer.setText(noJersey);

        String kategori = TablePlayer.getValueAt(baris, 4).toString();
        txtkategori.setSelectedItem(kategori);

        String negara = TablePlayer.getValueAt(baris, 6).toString();
        txtnegara.setText(negara);

        String pathImage = TablePlayer.getValueAt(baris, 7).toString();
        Lblimage.setText(pathImage);

        ImageIcon imageIcon = new ImageIcon(pathImage);
        int labelWidth = 260;
        int labelHeight = 220;

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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> txtkategori;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtnegara;
    private javax.swing.JTextField txtnoplayer;
    private javax.swing.JComboBox<String> txtposition;
    // End of variables declaration//GEN-END:variables
}
