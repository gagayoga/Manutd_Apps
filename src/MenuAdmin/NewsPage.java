/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package MenuAdmin;


import Koneksi.Config;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class NewsPage extends javax.swing.JPanel {
    
    private String namaPenulis;
    private int userIdCounter = 1;
    /**
     * Creates new form News
     */
    public NewsPage() {
        initComponents();
        
        LockInputan();
        ButtonEnabled();

        TanggalNews();
        ShowTable();
        ButtonName();
        namaPenulis = Session.SessionLogin.getNameUser();
        txtpenulis.setText(namaPenulis);
    }
    
    private void LockInputan() {
        txtkode.setEnabled(false);
        txttitle.setEnabled(false);
        txtkategori.setEnabled(false);
        txtpenulis.setEnabled(false);
        txttanggal.setEnabled(false);
        txtisi.setEnabled(false);
        txtdeskripsi.setEnabled(false);
        LblNews.setEnabled(false);
    }

    private void OpenInputan() {
        txtkode.setEnabled(false);
        txttitle.setEnabled(true);
        txtdeskripsi.setEnabled(true);
        txtkategori.setEnabled(true);
        txtpenulis.setEnabled(true);
        txttanggal.setEnabled(true);
        txtisi.setEnabled(true);
        LblNews.setEnabled(true);
    }

    private void ClearForm() {
        txtkode.setEditable(true);
        txtkode.setText(null);
        txtdeskripsi.setText(null);
        txttitle.setText(null);
        txtkategori.setSelectedIndex(0);
        txtisi.setText(null);
        LblNews.setText(null);
        ImageBerita.setIcon(null);
    }

    private void ButtonName() {
        BCreate.setText("CREATE");
        BUpdate.setText("UPDATE");
        BDelete.setText("DELETE");
        BCancel.setText("CLEAR");
    }

    private void ButtonEnabled() {
        BCreate.setEnabled(true);
        BUpdate.setEnabled(true);
        BDelete.setEnabled(true);
        BCancel.setEnabled(true);
    }

    public void TanggalNews() {
        Date skrng = new Date();
        DateFormat kode = new SimpleDateFormat("ddMMyyyy");
        DateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = tgl.format(skrng);
        String Kode = kode.format(skrng);
        txttanggal.setText(tanggal);
    }

    private void KodeNews() {
        Date skrng = new Date();
        DateFormat kodetgl = new SimpleDateFormat("ddMMyyyy");
        DateFormat tglkode = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = tglkode.format(skrng);
        int randomNum = new Random().nextInt(900) + 100;
        String userId = "NEWS-" + tanggal + "-" + String.format("%03d", randomNum);
        txtkode.setText(userId);
        userIdCounter++;
    }

    private void SearchQuery(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode News");
        model.addColumn("Tanggal News");
        model.addColumn("Judul News");
        model.addColumn("Deskripsi News");
        model.addColumn("Isi News");
        model.addColumn("Kategori News");
        model.addColumn("Penulis News");
        model.addColumn("Image News");

        try {
            String SearchValue = txtsearch.getText().trim();
            int no = 1;
            String sql = "SELECT * FROM news WHERE tanggal LIKE ? OR judul_berita LIKE ? OR isi_berita LIKE ? OR kategori_berita LIKE ? OR penulis LIKE ?";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + SearchValue + "%");
            pstm.setString(2, "%" + SearchValue + "%");
            pstm.setString(3, "%" + SearchValue + "%");
            pstm.setString(4, "%" + SearchValue + "%");
            pstm.setString(5, "%" + SearchValue + "%");
            ResultSet res = pstm.executeQuery();

            while (res.next()) {
               model.addRow(new Object[]{no++, res.getString(1), res.getString(2),
                    res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8)
                });
            }
            
            TableNews.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
    // bagian CRUD
    private void FilterBulan(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode News");
        model.addColumn("Tanggal News");
        model.addColumn("Judul News");
        model.addColumn("Deskripsi News");
        model.addColumn("Isi News");
        model.addColumn("Kategori News");
        model.addColumn("Penulis News");
        model.addColumn("Image News");

        try {
            String Bulan = txtbulan.getSelectedItem().toString().trim();
            int no = 1;
            String sql = "SELECT * FROM news WHERE MONTHNAME(tanggal) LIKE ?";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + Bulan + "%");
            ResultSet res = pstm.executeQuery();

            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2),
                    res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8)
                });
            }
            TableNews.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
    
    private void FilterKategori(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode News");
        model.addColumn("Tanggal News");
        model.addColumn("Judul News");
        model.addColumn("Deskripsi News");
        model.addColumn("Isi News");
        model.addColumn("Kategori News");
        model.addColumn("Penulis News");
        model.addColumn("Image News");

        try {
            String KategoriBerita = txtselectkategori.getSelectedItem().toString().trim();
            int no = 1;
            String sql = "SELECT * FROM news WHERE kategori_berita LIKE ?";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + KategoriBerita + "%");
            ResultSet res = pstm.executeQuery();

            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2),
                    res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8)
                });
            }
            TableNews.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
    
    private void ShowTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode News");
        model.addColumn("Tanggal News");
        model.addColumn("Judul News");
        model.addColumn("Deskripsi News");
        model.addColumn("Isi News");
        model.addColumn("Kategori News");
        model.addColumn("Penulis News");
        model.addColumn("Image News");

        try {
            int no = 1;
            String sql = "SELECT * FROM news";
            Connection conn = (Connection) Config.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            if (!res.next()){
                model.addRow(new Object[] {"Data not found"});
            }
            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2),
                    res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8)
                });
            }
            TableNews.setModel(model);
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void CreateQuery() {
        String kodenews, tanggalnews, judulnews, deskripsi, isinews, kategorinews, penulis, imagenews;

        kodenews = txtkode.getText().trim();
        tanggalnews = txttanggal.getText().trim();
        judulnews = txttitle.getText().trim();
        deskripsi = txtdeskripsi.getText().trim();
        isinews = txtisi.getText().trim();
        kategorinews = txtkategori.getSelectedItem().toString().trim();
        penulis = txtpenulis.getText().trim();
        imagenews = LblNews.getText().trim();

        try {
            String sql = "INSERT INTO news (id_berita,tanggal,judul_berita, deskripsi, isi_berita,kategori_berita,penulis,image) VALUES (?,?,?,?,?,?,?,?)";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, kodenews);
            pstm.setString(2, tanggalnews);
            pstm.setString(3, judulnews);
            pstm.setString(4, deskripsi);
            pstm.setString(5, isinews);
            pstm.setString(6, kategorinews);
            pstm.setString(7, penulis);
            pstm.setString(8, imagenews);
            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Create Data News Succesfully.");
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
        String kodenews, tanggalnews, judulnews, deskripsi, isinews, kategorinews, penulis, imagenews;

        kodenews = txtkode.getText().trim();
        tanggalnews = txttanggal.getText().trim();
        judulnews = txttitle.getText().trim();
        deskripsi = txtdeskripsi.getText().trim();
        isinews = txtisi.getText().trim();
        kategorinews = txtkategori.getSelectedItem().toString().trim();
        penulis = txtpenulis.getText().trim();
        imagenews = LblNews.getText().trim();

        try {
            String sqlupdate
                    = "UPDATE news SET tanggal = ? , judul_berita = ?, deskripsi = ?, isi_berita = ?, kategori_berita = ?, penulis = ?, image =? WHERE id_berita =?";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareCall(sqlupdate);
            pstm.setString(1, tanggalnews);
            pstm.setString(2, judulnews);
            pstm.setString(3, isinews);
            pstm.setString(4, deskripsi);
            pstm.setString(5, kategorinews);
            pstm.setString(6, penulis);
            pstm.setString(7, imagenews);
            pstm.setString(8, kodenews);
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
        String kodeplayer;

        kodeplayer = txtkode.getText().trim();
        try {
            String sqldelete
                    = "DELETE FROM news WHERE id_berita= ?";
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
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabContainer = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtkode = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txttanggal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txttitle = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtkategori = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtisi = new javax.swing.JTextArea();
        txtpenulis = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        BtnFile = new javax.swing.JButton();
        ImageBerita = new javax.swing.JLabel();
        LblNews = new javax.swing.JLabel();
        BCancel = new rojerusan.RSMaterialButtonRectangle();
        BDelete = new rojerusan.RSMaterialButtonRectangle();
        BUpdate = new rojerusan.RSMaterialButtonRectangle();
        BCreate = new rojerusan.RSMaterialButtonRectangle();
        txtdeskripsi = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtsearch = new javax.swing.JTextField();
        txtbulan = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableNews = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtselectkategori = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1108, 941));

        TabContainer.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        TabContainer.setPreferredSize(new java.awt.Dimension(1108, 941));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1108, 941));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(240, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 290, 5));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 25)); // NOI18N
        jLabel1.setText("MANAGE DATA NEWS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 330, 30));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel13.setText("Kode News");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 140, -1));

        txtkode.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtkode.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanel1.add(txtkode, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 290, 50));

        jPanel6.setBackground(new java.awt.Color(204, 51, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/Dashboard/Icon Menu/IconCreateCode.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 0, -1, 50));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 60, 50));

        txttanggal.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txttanggal.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanel1.add(txttanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, 330, 50));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel7.setText("Tanggal");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 140, -1));

        txttitle.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txttitle.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanel1.add(txttitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 730, 50));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel14.setText("Tittle News");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 140, -1));

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel16.setText("Category News");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 140, -1));

        txtkategori.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtkategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Match News", "Player News", "Schedule Match", " ", " " }));
        jPanel1.add(txtkategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 360, 50));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel9.setText("Isi Berita");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 140, -1));

        txtisi.setColumns(20);
        txtisi.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        txtisi.setRows(5);
        jScrollPane3.setViewportView(txtisi);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 1040, 190));

        txtpenulis.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtpenulis.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanel1.add(txtpenulis, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 460, 330, 50));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel11.setText("Penulis");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 430, 140, -1));

        BtnFile.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        BtnFile.setText("Upload Image");
        BtnFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFileActionPerformed(evt);
            }
        });
        jPanel1.add(BtnFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 370, 280, 40));

        ImageBerita.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.add(ImageBerita, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, 280, 230));

        LblNews.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        LblNews.setEnabled(false);
        jPanel1.add(LblNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 70, 280, 20));

        BCancel.setBackground(new java.awt.Color(51, 51, 255));
        BCancel.setText("CANCEL");
        BCancel.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelActionPerformed(evt);
            }
        });
        jPanel1.add(BCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 800, 240, 60));

        BDelete.setBackground(new java.awt.Color(204, 0, 0));
        BDelete.setText("DELETE");
        BDelete.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(BDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 800, 250, 60));

        BUpdate.setBackground(new java.awt.Color(255, 153, 0));
        BUpdate.setText("UPDATE");
        BUpdate.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(BUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 800, 250, 60));

        BCreate.setBackground(new java.awt.Color(71, 71, 71));
        BCreate.setText("CREATE");
        BCreate.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        BCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCreateActionPerformed(evt);
            }
        });
        jPanel1.add(BCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 800, 240, 60));

        txtdeskripsi.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtdeskripsi.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPanel1.add(txtdeskripsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 730, 50));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel15.setText("Deskripsi News");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 140, -1));

        TabContainer.addTab("Form Manage News", new javax.swing.ImageIcon(getClass().getResource("/asset/Dashboard/Icon Menu/HomeButton.png")), jPanel1); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1108, 941));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtsearch.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtsearch.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Search Data News", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Verdana", 0, 12))); // NOI18N
        txtsearch.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });
        jPanel2.add(txtsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 1040, 60));

        txtbulan.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtbulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filter Bulan", "Januari", "Febuari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        txtbulan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtbulanItemStateChanged(evt);
            }
        });
        jPanel2.add(txtbulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 280, 50));

        TableNews.setModel(new javax.swing.table.DefaultTableModel(
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
        TableNews.setRowHeight(35);
        TableNews.setSelectionBackground(new java.awt.Color(204, 0, 51));
        TableNews.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableNewsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableNews);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 1110, 620));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 25)); // NOI18N
        jLabel2.setText("DATA NEWS");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 330, 30));

        jPanel5.setBackground(new java.awt.Color(240, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 200, 5));

        txtselectkategori.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtselectkategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filter Kategori", "Match News", "Player News", "Schedule Match" }));
        txtselectkategori.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtselectkategoriItemStateChanged(evt);
            }
        });
        jPanel2.add(txtselectkategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 360, 50));

        TabContainer.addTab("Data News", new javax.swing.ImageIcon(getClass().getResource("/asset/Dashboard/Icon Menu/IconNews.png")), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        KodeNews();
    }//GEN-LAST:event_jLabel8MouseClicked

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
            LblNews.setText(path);

            try {

                String ImagePath = LblNews.getText().toString();
                ImageIcon imageIcon = new ImageIcon(ImagePath);

                int labelWidth = 280;
                int labelHeight = 220;

                int imageWidth = imageIcon.getIconWidth();
                int imageHeight = imageIcon.getIconHeight();

                double scaleX = (double) labelWidth / (double) imageWidth;
                double scaleY = (double) labelHeight / (double) imageHeight;
                double scale = Math.min(scaleX, scaleY);

                Image scaledImage = imageIcon.getImage().getScaledInstance((int) (scale * imageWidth), (int) (scale * imageHeight), Image.SCALE_SMOOTH);

                ImageBerita.setIcon(new ImageIcon(scaledImage));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_BtnFileActionPerformed

    private void BCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCancelActionPerformed
        // TODO add your handling code here:
        if (BCancel.getText().equals("CLEAR")) {
            ClearForm();
        } else if (BCancel.getText().equals("CANCEL")) {
            ButtonEnabled();
            LockInputan();
            ButtonName();
            BCancel.setText("CLEAR");
        }
    }//GEN-LAST:event_BCancelActionPerformed

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        // TODO add your handling code here:
        if (BDelete.getText().equals("DELETE")) {
            BDelete.setText("SUBMIT");
            BCreate.setEnabled(false);
            BUpdate.setEnabled(false);
            BCancel.setText("CANCEL");

            OpenInputan();
        } else if (BDelete.getText().equals("SUBMIT")) {
            if (txtkode.getText().equals("") || txttanggal.getText().equals("")
                || txttitle.getText().equals("") || txtdeskripsi.getText().equals("")
                || txtisi.getText().equals("")
                || txtkategori.getSelectedItem().equals("")
                || txtpenulis.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Input All Inputan");
                txtkode.requestFocus();
            } else {
                int inputan = JOptionPane.showConfirmDialog(this,
                    "Apakah Anda yakin delete data tersebut", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (inputan == JOptionPane.YES_OPTION) {
                    DeleteQuery();
                    BDelete.setText("DELETE");
                    TabContainer.setSelectedIndex(1);
                } else {

                }
            }
        }
    }//GEN-LAST:event_BDeleteActionPerformed

    private void BUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUpdateActionPerformed
        // TODO add your handling code here:
        if (BUpdate.getText().equals("UPDATE")) {
            BUpdate.setText("SUBMIT");
            BCreate.setEnabled(false);
            BDelete.setEnabled(false);
            BCancel.setText("CANCEL");

            OpenInputan();
        } else if (BUpdate.getText().equals("SUBMIT")) {
            if (txtkode.getText().equals("") || txttanggal.getText().equals("")
                || txttitle.getText().equals("") || txtdeskripsi.getText().equals("")
                || txtisi.getText().equals("")
                || txtkategori.getSelectedItem().equals("")
                || txtpenulis.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Input All Inputan");
                txtkode.requestFocus();
            } else {
                UpdateQuery();
                BUpdate.setText("UPDATE");
                TabContainer.setSelectedIndex(1);
            }
        }
    }//GEN-LAST:event_BUpdateActionPerformed

    private void BCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCreateActionPerformed
        // TODO add your handling code here:
        if (BCreate.getText().equals("CREATE")) {
            BCreate.setText("SUBMIT");
            BUpdate.setEnabled(false);
            BDelete.setEnabled(false);
            BCancel.setText("CANCEL");

            OpenInputan();
        } else if (BCreate.getText().equals("SUBMIT")) {
            if (txtkode.getText().equals("") || txttanggal.getText().equals("")
                || txttitle.getText().equals("") || txtdeskripsi.getText().equals("")
                || txtisi.getText().equals("")
                || txtkategori.getSelectedItem().equals("")
                || txtpenulis.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Input All Inputan");
                txtkode.requestFocus();
            } else {
                CreateQuery();
                BCreate.setText("CREATE");
                TabContainer.setSelectedIndex(1);
            }
        }
    }//GEN-LAST:event_BCreateActionPerformed

    private void TableNewsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableNewsMouseClicked
        // TODO add your handling code here:
        
        TabContainer.setSelectedIndex(0);
        int baris = TableNews.rowAtPoint(evt.getPoint());

        String kodeNews = TableNews.getValueAt(baris, 1).toString();
        txtkode.setText(kodeNews);

        String tanggalNews = TableNews.getValueAt(baris, 2).toString();
        txttanggal.setText(tanggalNews);

        String Judul = TableNews.getValueAt(baris, 3).toString();
        txttitle.setText(Judul);
        
        String Deskripsi = TableNews.getValueAt(baris, 4).toString();
        txtdeskripsi.setText(Deskripsi);

        String Isi = TableNews.getValueAt(baris, 5).toString();
        txtisi.setText(Isi);

        String Kategori = TableNews.getValueAt(baris, 6).toString();
        txtkategori.setSelectedItem(Kategori);

        String Penulis = TableNews.getValueAt(baris, 7).toString();
        txtpenulis.setText(Penulis);

        String ImageNews = TableNews.getValueAt(baris, 8).toString();
        LblNews.setText(ImageNews);

        ImageIcon imageIcon = new ImageIcon(ImageNews);
        int labelWidth = 260;
        int labelHeight = 220;

        int imageWidth = imageIcon.getIconWidth();
        int imageHeight = imageIcon.getIconHeight();

        double scaleX = (double) labelWidth / (double) imageWidth;
        double scaleY = (double) labelHeight / (double) imageHeight;
        double scale = Math.min(scaleX, scaleY);

        Image ScaleImage = imageIcon.getImage().getScaledInstance((int) (scale * imageWidth), (int) (scale * imageHeight), Image.SCALE_SMOOTH);

        ImageBerita.setIcon(new ImageIcon(ScaleImage));

        BCreate.setEnabled(false);
    }//GEN-LAST:event_TableNewsMouseClicked

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        // TODO add your handling code here:
        SearchQuery();
    }//GEN-LAST:event_txtsearchKeyReleased

    private void txtselectkategoriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtselectkategoriItemStateChanged
        // TODO add your handling code here:
        FilterKategori();
    }//GEN-LAST:event_txtselectkategoriItemStateChanged

    private void txtbulanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtbulanItemStateChanged
        // TODO add your handling code here:
        FilterBulan();
    }//GEN-LAST:event_txtbulanItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle BCancel;
    private rojerusan.RSMaterialButtonRectangle BCreate;
    private rojerusan.RSMaterialButtonRectangle BDelete;
    private rojerusan.RSMaterialButtonRectangle BUpdate;
    private javax.swing.JButton BtnFile;
    private javax.swing.JLabel ImageBerita;
    private javax.swing.JLabel LblNews;
    private javax.swing.JTabbedPane TabContainer;
    private javax.swing.JTable TableNews;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> txtbulan;
    private javax.swing.JTextField txtdeskripsi;
    private javax.swing.JTextArea txtisi;
    private javax.swing.JComboBox<String> txtkategori;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtpenulis;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JComboBox<String> txtselectkategori;
    private javax.swing.JTextField txttanggal;
    private javax.swing.JTextField txttitle;
    // End of variables declaration//GEN-END:variables
}
