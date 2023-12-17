/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MainAdmin;

import Koneksi.Config;
import Main.User.DashboardFormUser;
import MenuAdmin.RegisterPage;
import Session.SessionLogin;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class LoginForm extends javax.swing.JFrame {

    /**
     * Creates new form LoginPage_Form
     */
    public LoginForm() {
        initComponents();
        setLocationRelativeTo(null);

        txtpassword.setText("Input password");
        txtpassword.setEchoChar((char) 0);
    }

    public javax.swing.JPanel getLoginPanel() {
        return LoginPanel;
    }

    private void ClearForm() {
        txtemail.setText("");
        txtpassword.setText("");
    }

    private void LoadLogin() {
        if (!txtemail.getText().equals("Input email") || !txtpassword.getText().equals("Input password")) {
            if (txtemail.getText().equals("") || txtpassword.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please input Username or Password",
                        "Login Failed", JOptionPane.INFORMATION_MESSAGE);
            } else{
                if(validateEmail(txtemail.getText())){
                    LoginQuerry();
                }else{
                    JOptionPane.showMessageDialog(this, "Email tidak valid, harus menggunakan @gmail.com",
                    "Login Failed", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please input Username or Password",
                    "Login Failed", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void LoginQuerry() {
        String getname = null, getemail = null, getpassword = null, gettelepon = null, getrole = null;
        try {
            int no = 1;
            String sql = "SELECT * FROM user WHERE email ='" + txtemail.getText() + "' AND password='" + txtpassword.getText() + "'";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            java.sql.ResultSet rslt = stm.executeQuery(sql);

            while (rslt.next()) {

                getname = rslt.getString("name");
                getemail = txtemail.getText().toString();
                getpassword = txtpassword.getText().toString();
                gettelepon = rslt.getString("no_telp");
                getrole = rslt.getString("role");
            }
            rslt.last();
            //mengecek jumlah baris pada hasil query jika datanya 1 maka login berhasil
            if (rslt.getRow() == 1) {
                SessionLogin.setNameUser(getname);
                SessionLogin.setEmailUser(getemail);
                SessionLogin.setPasswordUser(getpassword);
                SessionLogin.setTeleponUser(gettelepon);
                SessionLogin.setRoleUser(getrole);

                String RoleUser = SessionLogin.getRoleUser();

                if (RoleUser.equals("Admin")) {

                    JOptionPane.showMessageDialog(this, "Login Succesfully, Welcome back " + getname,
                            "Login Succes", JOptionPane.INFORMATION_MESSAGE);
                    DashboardForm admin = new DashboardForm();
                    admin.setVisible(true);
                    ClearForm();
                    this.dispose();

                } else if (RoleUser.equals("User")) {
                    JOptionPane.showMessageDialog(this, "Login Succesfully, Welcome back " + getname,
                            "Login Succes", JOptionPane.INFORMATION_MESSAGE);
                    DashboardFormUser user = new DashboardFormUser();
                    user.setVisible(true);
                    ClearForm();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Akun anda berbahaya" + getname,
                            "Login Succes", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Login Failed, Please Check Your Username or Password",
                        "Login Failed", JOptionPane.ERROR_MESSAGE);
                txtemail.requestFocus();
                txtemail.setText("");
                txtpassword.setText("");
            }
        } catch (SQLException e) {
            System.out.print("Eror : " + e.getMessage());
        }
    }
    
    private boolean validateEmail(String email){
        return email.endsWith("@gmail.com");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImagePanel = new javax.swing.JPanel();
        Imagelogin = new javax.swing.JLabel();
        MainPanel = new javax.swing.JPanel();
        LoginPanel = new javax.swing.JPanel();
        lbljudul = new javax.swing.JLabel();
        lbldeskripsi = new javax.swing.JLabel();
        lblemail = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        lblpassword = new javax.swing.JLabel();
        btnlogin = new rojerusan.RSMaterialButtonRectangle();
        lbllink = new javax.swing.JLabel();
        linkregister = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        checkpassword = new javax.swing.JCheckBox();
        txtpassword = new javax.swing.JPasswordField();
        lbldeskripsi1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ManUtd Apps Login");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/asset/Logo Mu.png")).getImage());
        setResizable(false);

        ImagePanel.setBackground(new java.awt.Color(0, 0, 0));

        Imagelogin.setBackground(new java.awt.Color(0, 0, 0));
        Imagelogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/Background Login.png"))); // NOI18N
        Imagelogin.setText("jLabel1");
        Imagelogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ImageloginMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ImagePanelLayout = new javax.swing.GroupLayout(ImagePanel);
        ImagePanel.setLayout(ImagePanelLayout);
        ImagePanelLayout.setHorizontalGroup(
            ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Imagelogin, javax.swing.GroupLayout.PREFERRED_SIZE, 978, Short.MAX_VALUE)
        );
        ImagePanelLayout.setVerticalGroup(
            ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Imagelogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MainPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainPanel.setLayout(new java.awt.BorderLayout());

        LoginPanel.setBackground(new java.awt.Color(255, 255, 255));

        lbljudul.setFont(new java.awt.Font("Verdana", 1, 60)); // NOI18N
        lbljudul.setText("LOGIN");

        lbldeskripsi.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbldeskripsi.setText("Log in now to unclock your exclusive access to");

        lblemail.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblemail.setText("Email address");

        txtemail.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtemail.setForeground(new java.awt.Color(204, 204, 204));
        txtemail.setText("Input email");
        txtemail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtemail.setCaretColor(new java.awt.Color(93, 95, 239));
        txtemail.setDisabledTextColor(new java.awt.Color(93, 95, 239));
        txtemail.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txtemail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtemailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtemailFocusLost(evt);
            }
        });

        lblpassword.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblpassword.setText("Password");

        btnlogin.setBackground(new java.awt.Color(204, 0, 0));
        btnlogin.setText("LOGIN");
        btnlogin.setBorderPainted(false);
        btnlogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnlogin.setFont(new java.awt.Font("Roboto Medium", 1, 24)); // NOI18N
        btnlogin.setRippleColor(new java.awt.Color(0, 255, 204));
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });

        lbllink.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lbllink.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbllink.setText("Do you not have an Account?");

        linkregister.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        linkregister.setForeground(new java.awt.Color(204, 0, 0));
        linkregister.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        linkregister.setText("Register here");
        linkregister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        linkregister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                linkregisterMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("My United is your personal profile and includes access ");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("to exclusive content and competitions. To manage");

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(" your Official Membership account.");

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("____________________________________________");

        checkpassword.setBackground(new java.awt.Color(255, 255, 255));
        checkpassword.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        checkpassword.setText("Show Password");
        checkpassword.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkpasswordItemStateChanged(evt);
            }
        });
        checkpassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkpasswordMouseClicked(evt);
            }
        });
        checkpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkpasswordActionPerformed(evt);
            }
        });

        txtpassword.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtpassword.setForeground(new java.awt.Color(204, 204, 204));
        txtpassword.setText("Input password");
        txtpassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtpassword.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txtpassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtpasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpasswordFocusLost(evt);
            }
        });

        lbldeskripsi1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbldeskripsi1.setText(" contents and offers");

        javax.swing.GroupLayout LoginPanelLayout = new javax.swing.GroupLayout(LoginPanel);
        LoginPanel.setLayout(LoginPanelLayout);
        LoginPanelLayout.setHorizontalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginPanelLayout.createSequentialGroup()
                .addGap(0, 64, Short.MAX_VALUE)
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkpassword)
                    .addComponent(lblemail, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(LoginPanelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lbllink)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linkregister))
                    .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnlogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtemail, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtpassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LoginPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(62, 62, 62))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbldeskripsi)
                .addGap(114, 114, 114))
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LoginPanelLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(lbljudul))
                    .addGroup(LoginPanelLayout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(lbldeskripsi1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LoginPanelLayout.setVerticalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lbljudul)
                .addGap(18, 18, 18)
                .addComponent(lbldeskripsi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbldeskripsi1)
                .addGap(49, 49, 49)
                .addComponent(lblemail)
                .addGap(15, 15, 15)
                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lblpassword)
                .addGap(15, 15, 15)
                .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(checkpassword)
                .addGap(29, 29, 29)
                .addComponent(btnlogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbllink)
                    .addComponent(linkregister))
                .addGap(36, 36, 36)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(jLabel5)
                .addGap(5, 5, 5)
                .addComponent(jLabel6)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        MainPanel.add(LoginPanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void linkregisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkregisterMouseClicked
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        RegisterPage register = new RegisterPage();
        MainPanel.add(register);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_linkregisterMouseClicked

    private void checkpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkpasswordActionPerformed

    private void checkpasswordItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkpasswordItemStateChanged
        // TODO add your handling code here:
        if (checkpassword.isSelected()) {
            txtpassword.setEchoChar((char) 0); //password = JPasswordField
        } else {
            txtpassword.setEchoChar('*');
        }
    }//GEN-LAST:event_checkpasswordItemStateChanged

    private void checkpasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkpasswordMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_checkpasswordMouseClicked

    private void ImageloginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImageloginMouseClicked
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();
        MainPanel.add(LoginPanel);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_ImageloginMouseClicked

    private void txtemailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtemailFocusGained
        // TODO add your handling code here:
        if (txtemail.getText().equals("Input email")) {
            txtemail.setText("");
            txtemail.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtemailFocusGained

    private void txtemailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtemailFocusLost
        // TODO add your handling code here:
        if (txtemail.getText().equals("")) {
            txtemail.setText("Input email");
            txtemail.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_txtemailFocusLost

    private void txtpasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpasswordFocusGained
        // TODO add your handling code here:
        if (txtpassword.getText().equals("Input password")) {
            txtpassword.setText("");
            txtpassword.setEchoChar('*');
            txtpassword.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtpasswordFocusGained

    private void txtpasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpasswordFocusLost
        // TODO add your handling code here:
        if (txtpassword.getText().equals("")) {
            txtpassword.setText("Input password");
            txtpassword.setEchoChar((char) 0);
            txtpassword.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_txtpasswordFocusLost

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
        // TODO add your handling code here:
        LoadLogin();
    }//GEN-LAST:event_btnloginActionPerformed

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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ImagePanel;
    public javax.swing.JLabel Imagelogin;
    public javax.swing.JPanel LoginPanel;
    public javax.swing.JPanel MainPanel;
    private rojerusan.RSMaterialButtonRectangle btnlogin;
    private javax.swing.JCheckBox checkpassword;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lbldeskripsi;
    private javax.swing.JLabel lbldeskripsi1;
    private javax.swing.JLabel lblemail;
    public javax.swing.JLabel lbljudul;
    private javax.swing.JLabel lbllink;
    private javax.swing.JLabel lblpassword;
    private javax.swing.JLabel linkregister;
    private javax.swing.JTextField txtemail;
    private javax.swing.JPasswordField txtpassword;
    // End of variables declaration//GEN-END:variables
}
