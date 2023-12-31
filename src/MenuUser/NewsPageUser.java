/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package MenuUser;

import Koneksi.Config;
import Main.User.Component.CardNews;
import Main.User.Component.CardPlayers;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BoxLayout;

/**
 *
 * @author Asus
 */
public class NewsPageUser extends javax.swing.JPanel {

    /**
     * Creates new form NewsPageUser
     */
    public NewsPageUser() {
        initComponents();

        LoadDataCard();
    }

    private void LoadDataCard() {
        PanelCard.setLayout(new GridLayout(0, 3, 3, 3)); // 0 rows, 4 columns, dengan jarak 10 pixel horizontal dan vertical
        PanelCard.setAutoscrolls(true);
        String idNews, judulNews, deskripsiNews, kategoriNews, imageNews;
        PanelCard.removeAll();
        try {
            String sql = "SELECT * FROM news";
            Connection conn = (Connection) Config.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                idNews = res.getString("id_berita");
                judulNews = res.getString("judul_berita");
                deskripsiNews = res.getString("deskripsi");
                kategoriNews = res.getString("kategori_berita");
                imageNews = res.getString("image");

                CardNews cardnews = new CardNews(idNews, judulNews, deskripsiNews, kategoriNews, imageNews);
                PanelCard.add(cardnews);
                cardnews.setPreferredSize(new Dimension(414, 395));
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
        PanelCard.repaint();
        PanelCard.revalidate();
    }

    private void FilterNews() {
        PanelCard.setLayout(new GridLayout(0, 3, 3, 3)); // 0 rows, 4 columns, dengan jarak 10 pixel horizontal dan vertical
        PanelCard.setAutoscrolls(true);
        String idNews, judulNews, deskripsiNews, kategoriNews, imageNews;
        PanelCard.removeAll();
        try {
            String KategoriBerita = txtselectkategori.getSelectedItem().toString().trim();
            if(KategoriBerita.equals("Filter Kategori")){
                LoadDataCard();
            }else{
            int no = 1;
            String sql = "SELECT * FROM news WHERE kategori_berita LIKE ?";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + KategoriBerita + "%");
            ResultSet res = pstm.executeQuery();

            while (res.next()) {
                idNews = res.getString("id_berita");
                judulNews = res.getString("judul_berita");
                deskripsiNews = res.getString("deskripsi");
                kategoriNews = res.getString("kategori_berita");
                imageNews = res.getString("image");

                CardNews cardnews = new CardNews(idNews, judulNews, deskripsiNews, kategoriNews, imageNews);
                PanelCard.add(cardnews);
                cardnews.setPreferredSize(new Dimension(414, 395));
            }
           }
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
        
        PanelCard.repaint();
        PanelCard.revalidate();
    }

    private void SearchData() {
        PanelCard.setLayout(new GridLayout(0, 3, 3, 3)); // 0 rows, 4 columns, dengan jarak 10 pixel horizontal dan vertical
        PanelCard.setAutoscrolls(true);
        String idNews, judulNews, deskripsiNews, kategoriNews, imageNews;
        PanelCard.removeAll();
        try {
            String valueSearch = txtsearch.getText().toString().trim();
            String sql = "SELECT * FROM news WHERE tanggal LIKE ? OR judul_berita LIKE ? OR isi_berita LIKE ? OR kategori_berita LIKE ? OR penulis LIKE ?";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + valueSearch + "%");
            pstm.setString(2, "%" + valueSearch + "%");
            pstm.setString(3, "%" + valueSearch + "%");
            pstm.setString(4, "%" + valueSearch + "%");
            pstm.setString(5, "%" + valueSearch + "%");
            ResultSet res = pstm.executeQuery();

            if (res.next()){
                do{
                idNews = res.getString("id_berita");
                judulNews = res.getString("judul_berita");
                deskripsiNews = res.getString("deskripsi");
                kategoriNews = res.getString("kategori_berita");
                imageNews = res.getString("image");

                CardNews cardnews = new CardNews(idNews, judulNews, deskripsiNews, kategoriNews, imageNews);
                PanelCard.add(cardnews);
                cardnews.setPreferredSize(new Dimension(414, 395));
                }while (res.next());
            }else{
                idNews = ("000");
                judulNews = ("News Not Found");
                deskripsiNews = ("News Not Found");
                kategoriNews = ("News Not Found");
                imageNews = ("D:\\My File\\Sekolah\\Tugas Semester 1 XII RPL B\\PBO\\Tugas Ke6\\Tugas Kelompok_ManUtdApps\\ManUtd_Apps\\src\\asset\\notfound.png");
                CardNews cardnews = new CardNews(idNews, judulNews, deskripsiNews, kategoriNews, imageNews);
                PanelCard.add(cardnews);
                cardnews.setPreferredSize(new Dimension(414, 395));
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
        PanelCard.repaint();
        PanelCard.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtsearch = new javax.swing.JTextField();
        txtselectkategori = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        PanelCard = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 200, 5));

        txtsearch.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtsearch.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Search Data News", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Verdana", 0, 12))); // NOI18N
        txtsearch.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });
        jPanel1.add(txtsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 1190, 60));

        txtselectkategori.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtselectkategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filter Kategori", "Match News", "Player News", "Schedule Match" }));
        txtselectkategori.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtselectkategoriItemStateChanged(evt);
            }
        });
        jPanel1.add(txtselectkategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 360, 50));

        PanelCard.setBackground(new java.awt.Color(255, 255, 255));
        PanelCard.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane1.setViewportView(PanelCard);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 1260, 640));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 25)); // NOI18N
        jLabel3.setText("NEWS MANCHESTER UNITED");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 410, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        // TODO add your handling code here:
        SearchData();
    }//GEN-LAST:event_txtsearchKeyReleased

    private void txtselectkategoriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtselectkategoriItemStateChanged
        // TODO add your handling code here:
        FilterNews();
    }//GEN-LAST:event_txtselectkategoriItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelCard;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JComboBox<String> txtselectkategori;
    // End of variables declaration//GEN-END:variables
}
