/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package MenuUser;

import Koneksi.Config;
import Main.User.Component.CardNews;
import Main.User.Component.CardPlayers;
import Model.Player;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Asus
 */
public class PlayersPageUser extends javax.swing.JPanel {

    public static int PAGE_AXIS = 4;

    /**
     * Creates new form PlayersPageUser
     */
    public PlayersPageUser() {
        initComponents();

        LoadDataCard();
        ShowNegara();
    }

    private void FilterNegara() {
        PanelCard.setLayout(new GridLayout(0, 4, 5, 5)); // 0 rows, 4 columns, dengan jarak 10 pixel horizontal dan vertical
        PanelCard.setAutoscrolls(true);
        String idplayer, firstname, lastname, position, number, image;
        PanelCard.removeAll();
        try {
            String negaraplayer = txtnegara.getSelectedItem().toString().trim();
            String kategoriplayer = txtselectkategori.getSelectedItem().toString().trim();
            if (kategoriplayer.equals("Filter Position Player")){
                LoadDataCard();
            }else{
            String sql = "SELECT * FROM player WHERE negara LIKE ? AND kategori_position LIKE ?";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + negaraplayer + "%");
            pstm.setString(2, "%" + kategoriplayer + "%");
            ResultSet res = pstm.executeQuery();

            if(res.next()){
                do{
                idplayer = res.getString("id_player");
                firstname = res.getString("first_name");
                lastname = res.getString("last_name");
                position = res.getString("position");
                number = res.getString("no_punggung");
                image = res.getString("image");

                CardPlayers cardnews = new CardPlayers(idplayer, firstname, lastname, position, number, image);
                PanelCard.add(cardnews);
                cardnews.setPreferredSize(new Dimension(310, 434));
                }while(res.next());
            }else{
                idplayer = ("Not Found");
                firstname = ("Not Found");
                lastname = ("Not Found");
                position = ("Not Found");
                number = ("0");
                image = ("C:\\Users\\Asus\\Downloads\\Upload Image\\Not Found.png");

                CardPlayers cardnews = new CardPlayers(idplayer, firstname, lastname, position, number, image);
                PanelCard.add(cardnews);
                cardnews.setPreferredSize(new Dimension(310, 434));
            }
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
        PanelCard.repaint();
        PanelCard.revalidate();
    }

    private void FilterPlayer() {
        PanelCard.setLayout(new GridLayout(0, 4, 5, 5)); // 0 rows, 4 columns, dengan jarak 10 pixel horizontal dan vertical
        PanelCard.setAutoscrolls(true);
        String idplayer, firstname, lastname, position, number, image;
        PanelCard.removeAll();
        try {
            String kategoriplayer = txtselectkategori.getSelectedItem().toString().trim();
            if (kategoriplayer.equals("Filter Position Player")){
                LoadDataCard();
            }else{
            String sql = "SELECT * FROM player WHERE kategori_position LIKE ?";
            Connection conn = (Connection) Config.configDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + kategoriplayer + "%");
            ResultSet res = pstm.executeQuery();

            if(res.next()){
                do{
                idplayer = res.getString("id_player");
                firstname = res.getString("first_name");
                lastname = res.getString("last_name");
                position = res.getString("position");
                number = res.getString("no_punggung");
                image = res.getString("image");

                CardPlayers cardnews = new CardPlayers(idplayer, firstname, lastname, position, number, image);
                PanelCard.add(cardnews);
                cardnews.setPreferredSize(new Dimension(310, 434));
                }while(res.next());
            }else{
                idplayer = ("Not Found");
                firstname = ("Not Found");
                lastname = ("Not Found");
                position = ("Not Found");
                number = ("0");
                image = ("C:\\Users\\Asus\\Downloads\\Upload Image\\Not Found.png");

                CardPlayers cardnews = new CardPlayers(idplayer, firstname, lastname, position, number, image);
                PanelCard.add(cardnews);
                cardnews.setPreferredSize(new Dimension(310, 434));
            }
            }
            
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
        PanelCard.repaint();
        PanelCard.revalidate();
    }

    private void ShowNegara() {
        String negara;
        DefaultComboBoxModel<String> negaraModel = new DefaultComboBoxModel<>();

        txtnegara.setModel(negaraModel);

        try {
            String sql = "SELECT negara AS negara_pemain FROM player GROUP BY negara";
            Connection conn = (Connection) Config.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                negara = res.getString("negara_pemain");
                negaraModel.addElement(negara);
            }

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void LoadDataCard() {
        PanelCard.setLayout(new GridLayout(0, 4, 5, 5)); // 0 rows, 4 columns, dengan jarak 10 pixel horizontal dan vertical
        PanelCard.setAutoscrolls(true);
        String idplayer, firstname, lastname, position, number, image;
        PanelCard.removeAll();
        try {
            String sql = "SELECT * FROM player ORDER BY id_player";
            Connection conn = (Connection) Config.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                idplayer = res.getString("id_player");
                firstname = res.getString("first_name");
                lastname = res.getString("last_name");
                position = res.getString("position");
                number = res.getString("no_punggung");
                image = res.getString("image");

                CardPlayers cardnews = new CardPlayers(idplayer, firstname, lastname, position, number, image);
                PanelCard.add(cardnews);
                cardnews.setPreferredSize(new Dimension(310, 434));
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
        jLabel1 = new javax.swing.JLabel();
        txtselectkategori = new javax.swing.JComboBox<>();
        txtnegara = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        PanelCard = new javax.swing.JPanel();

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
        jLabel1.setText("PLAYERS SEASON 2023");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 330, 30));

        txtselectkategori.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtselectkategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filter Position Player", "GoalKeeper", "Defenders", "Midfielders", "Wingers", "Forwards" }));
        txtselectkategori.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtselectkategoriItemStateChanged(evt);
            }
        });
        add(txtselectkategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 360, 50));

        txtnegara.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtnegara.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filter Bulan", "Januari", "Febuari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        txtnegara.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtnegaraItemStateChanged(evt);
            }
        });
        add(txtnegara, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 280, 50));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setToolTipText("");
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        PanelCard.setBackground(new java.awt.Color(255, 255, 255));
        PanelCard.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.white));
        PanelCard.setMaximumSize(new java.awt.Dimension(1252, 720));
        PanelCard.setMinimumSize(new java.awt.Dimension(1252, 720));
        PanelCard.setLayout(new javax.swing.BoxLayout(PanelCard, javax.swing.BoxLayout.X_AXIS));
        jScrollPane2.setViewportView(PanelCard);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 1260, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void txtselectkategoriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtselectkategoriItemStateChanged
        // TODO add your handling code here:
        FilterPlayer();
//        FilterKategori();
    }//GEN-LAST:event_txtselectkategoriItemStateChanged

    private void txtnegaraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtnegaraItemStateChanged
        // TODO add your handling code here:
        FilterNegara();
//        FilterBulan();
    }//GEN-LAST:event_txtnegaraItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelCard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> txtnegara;
    private javax.swing.JComboBox<String> txtselectkategori;
    // End of variables declaration//GEN-END:variables
}
