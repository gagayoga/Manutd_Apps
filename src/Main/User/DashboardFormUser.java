/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main.User;

import Koneksi.Config;
import Main.User.Component.CardNews;
import MenuAdmin.UserPage;
import MenuUser.AchievementPage;
import MenuUser.NewsPageUser;
import MenuUser.PlayersPageUser;
import MenuUser.StatistikDetailPage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author Asus
 */
public class DashboardFormUser extends javax.swing.JFrame {

    /**
     * Creates new form DashboardFormUser
     */
    Timer timer;

    public DashboardFormUser() {
        initComponents();

        String name = Session.SessionLogin.getNameUser();
        LblWelcome.setText("Welcome Back " + name);

        LoadDateTime();
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadDateTime();
            }
        });

        timer.start();

        LoadNews();

    }

    private void LoadNews() {
        String idNews, judulNews, deskripsiNews, kategoriNews, imageNews;
        PanelCard.removeAll();
        try {
            String sql = "SELECT * FROM news ORDER BY id_berita DESC LIMIT 3";
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

    private void LoadDateTime() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = dateFormat.format(now);
        LabelDatetime.setText(dateTime);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContainerPanel = new javax.swing.JPanel();
        HeaderPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        LblWelcome = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        LabelDatetime = new javax.swing.JLabel();
        SidebarPanel = new javax.swing.JPanel();
        PanelButton = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PanelLogout = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        PanelBtnPlayer = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        PanelBtnStatistik = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        PanelBtnNews = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        MainPanel = new javax.swing.JPanel();
        DashboardPanel = new javax.swing.JPanel();
        PanelImage = new javax.swing.JPanel();
        LabelImage = new javax.swing.JLabel();
        PanelNews = new javax.swing.JPanel();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        jLabel1 = new javax.swing.JLabel();
        PanelCard = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard ManUtd Pengguna");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/asset/Logo Mu.png")).getImage());
        setResizable(false);

        ContainerPanel.setBackground(new java.awt.Color(255, 255, 255));

        HeaderPanel.setBackground(new java.awt.Color(204, 0, 0));
        HeaderPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/Logo MU Shadow.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        HeaderPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 3, 61, 80));

        LblWelcome.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        LblWelcome.setForeground(new java.awt.Color(255, 255, 255));
        LblWelcome.setText("Dashboard ManUtd Apps");
        HeaderPanel.add(LblWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, 45));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setPreferredSize(new java.awt.Dimension(146, 10));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 59, Short.MAX_VALUE)
        );

        HeaderPanel.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 10, 3, 59));

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("No Room For Racism");
        HeaderPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 10, -1, -1));

        LabelDatetime.setFont(new java.awt.Font("Verdana", 0, 19)); // NOI18N
        LabelDatetime.setForeground(new java.awt.Color(255, 255, 255));
        LabelDatetime.setText("No Room For Racism");
        HeaderPanel.add(LabelDatetime, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 40, 235, -1));

        SidebarPanel.setBackground(new java.awt.Color(0, 0, 0));

        PanelButton.setBackground(new java.awt.Color(255, 255, 255));
        PanelButton.setToolTipText("Dashboard Home");
        PanelButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/Dashboard/Icon Menu/HomeButton.png"))); // NOI18N
        jLabel2.setToolTipText("Menu Dashboard");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });
        PanelButton.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 53));

        PanelLogout.setBackground(new java.awt.Color(240, 0, 0));
        PanelLogout.setToolTipText("Menu Log Out");
        PanelLogout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/Dashboard/Icon Menu/IconLogout.png"))); // NOI18N
        jLabel5.setToolTipText("Menu Log Out");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });
        PanelLogout.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 0, -1, 53));

        PanelBtnPlayer.setBackground(new java.awt.Color(255, 255, 255));
        PanelBtnPlayer.setToolTipText("Menu Players");
        PanelBtnPlayer.setPreferredSize(new java.awt.Dimension(100, 53));
        PanelBtnPlayer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/Dashboard/Icon Menu/IconPlayer.png"))); // NOI18N
        jLabel18.setToolTipText("Menu Players");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel18MouseExited(evt);
            }
        });
        PanelBtnPlayer.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 53));

        PanelBtnStatistik.setBackground(new java.awt.Color(255, 255, 255));
        PanelBtnStatistik.setToolTipText("Menu Statistik");
        PanelBtnStatistik.setPreferredSize(new java.awt.Dimension(100, 53));
        PanelBtnStatistik.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/Dashboard/Icon Menu/IconStatistik.png"))); // NOI18N
        jLabel19.setToolTipText("Menu Statistik");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel19MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel19MouseExited(evt);
            }
        });
        PanelBtnStatistik.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 53));

        PanelBtnNews.setBackground(new java.awt.Color(255, 255, 255));
        PanelBtnNews.setToolTipText("Menu News");
        PanelBtnNews.setPreferredSize(new java.awt.Dimension(100, 53));
        PanelBtnNews.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/Dashboard/Icon Menu/IconNews.png"))); // NOI18N
        jLabel20.setToolTipText("Menu News");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel20MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel20MouseExited(evt);
            }
        });
        PanelBtnNews.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 53));

        javax.swing.GroupLayout SidebarPanelLayout = new javax.swing.GroupLayout(SidebarPanel);
        SidebarPanel.setLayout(SidebarPanelLayout);
        SidebarPanelLayout.setHorizontalGroup(
            SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidebarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(PanelButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PanelBtnNews, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(PanelBtnStatistik, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(PanelBtnPlayer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SidebarPanelLayout.setVerticalGroup(
            SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidebarPanelLayout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(PanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanelBtnPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanelBtnStatistik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanelBtnNews, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        MainPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainPanel.setLayout(new java.awt.BorderLayout());

        DashboardPanel.setPreferredSize(new java.awt.Dimension(1257, 910));
        DashboardPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelImage.setBackground(new java.awt.Color(255, 255, 255));

        LabelImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/DashboardUser/ImageDashboardUser.png"))); // NOI18N

        javax.swing.GroupLayout PanelImageLayout = new javax.swing.GroupLayout(PanelImage);
        PanelImage.setLayout(PanelImageLayout);
        PanelImageLayout.setHorizontalGroup(
            PanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelImageLayout.setVerticalGroup(
            PanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        DashboardPanel.add(PanelImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        PanelNews.setBackground(new java.awt.Color(255, 255, 255));
        PanelNews.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(246, 246, 246));
        rSMaterialButtonRectangle1.setForeground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonRectangle1.setText("View All");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        PanelNews.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 240, 50));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel1.setText("News Latest");
        PanelNews.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        PanelCard.setBackground(new java.awt.Color(255, 255, 255));
        PanelNews.add(PanelCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 69, 1260, 400));

        jPanel4.setBackground(new java.awt.Color(240, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        PanelNews.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 135, 3));

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(246, 246, 246));
        rSMaterialButtonRectangle2.setForeground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonRectangle2.setText("REGISTER NOW");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 240, 60));

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 19)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Rasakan konten - konten yang tidak terbatas dengan langganan Premium Rp. 65.000 /bulan.");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 4, -1, 70));

        PanelNews.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 1260, 80));

        DashboardPanel.add(PanelNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 343, 1257, 560));

        MainPanel.add(DashboardPanel, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout ContainerPanelLayout = new javax.swing.GroupLayout(ContainerPanel);
        ContainerPanel.setLayout(ContainerPanelLayout);
        ContainerPanelLayout.setHorizontalGroup(
            ContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HeaderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ContainerPanelLayout.createSequentialGroup()
                .addComponent(SidebarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ContainerPanelLayout.setVerticalGroup(
            ContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContainerPanelLayout.createSequentialGroup()
                .addComponent(HeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SidebarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContainerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContainerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
        PanelButton.setBackground(new Color(240, 0, 0));
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // TODO add your handling code here:
        PanelButton.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseEntered
        // TODO add your handling code here:
        PanelBtnPlayer.setBackground(new Color(240, 0, 0));
    }//GEN-LAST:event_jLabel18MouseEntered

    private void jLabel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseExited
        // TODO add your handling code here:
        PanelBtnPlayer.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jLabel18MouseExited

    private void jLabel19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseEntered
        // TODO add your handling code here:
        PanelBtnStatistik.setBackground(new Color(240, 0, 0));
    }//GEN-LAST:event_jLabel19MouseEntered

    private void jLabel19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseExited
        // TODO add your handling code here:
        PanelBtnStatistik.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jLabel19MouseExited

    private void jLabel20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseEntered
        // TODO add your handling code here:
        PanelBtnNews.setBackground(new Color(240, 0, 0));
    }//GEN-LAST:event_jLabel20MouseEntered

    private void jLabel20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseExited
        // TODO add your handling code here:
        PanelBtnNews.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jLabel20MouseExited

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        // TODO add your handling code here:
        PanelLogout.setBackground(new Color(127, 4, 4));
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        // TODO add your handling code here:
        PanelLogout.setBackground(new Color(240, 0, 0));
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        PlayersPageUser panelPlayers = new PlayersPageUser();
        MainPanel.add(panelPlayers);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        MainPanel.add(DashboardPanel);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        NewsPageUser panelNews = new NewsPageUser();
        MainPanel.add(panelNews);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_jLabel20MouseClicked

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        NewsPageUser panelNews = new NewsPageUser();
        MainPanel.add(panelNews);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        StatistikDetailPage panelNews = new StatistikDetailPage();
        MainPanel.add(panelNews);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        AchievementPage panelNews = new AchievementPage();
        MainPanel.add(panelNews);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardFormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardFormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardFormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardFormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardFormUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContainerPanel;
    private javax.swing.JPanel DashboardPanel;
    private javax.swing.JPanel HeaderPanel;
    private javax.swing.JLabel LabelDatetime;
    private javax.swing.JLabel LabelImage;
    private javax.swing.JLabel LblWelcome;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel PanelBtnNews;
    private javax.swing.JPanel PanelBtnPlayer;
    private javax.swing.JPanel PanelBtnStatistik;
    private javax.swing.JPanel PanelButton;
    private javax.swing.JPanel PanelCard;
    private javax.swing.JPanel PanelImage;
    private javax.swing.JPanel PanelLogout;
    private javax.swing.JPanel PanelNews;
    private javax.swing.JPanel SidebarPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    // End of variables declaration//GEN-END:variables
}
