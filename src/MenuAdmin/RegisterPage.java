/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package MenuAdmin;

import Koneksi.Config;
import MainAdmin.LoginForm;
import java.awt.HeadlessException;
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Asus
 */
public class RegisterPage extends javax.swing.JPanel {

    private int userIdCounter = 1;
    private String kodeUser;

    /**
     * Creates new form RegisterPage
     */
    public RegisterPage() {
        initComponents();

        KodeUser();
        txtname.requestFocus();
    }

    private void ValidasiPassword() {
        String password = txtpassword.getText();
        String confirm = txtconfirm.getText();

        if (!confirm.equals(password)) {
            txtconfirm.setForeground(new Color(240, 0, 0));
            txtconfirm.requestFocus();
        } else {
            txtconfirm.setForeground(new Color(0, 0, 0));
        }
    }

    private void ClearForm() {
        txtname.setText("");
        txttelepon.setText("");
        txtconfirm.setText("");
        txtemail.setText("");
        txtpassword.setText("");
    }

    private void LoadRegister() {
        if (txtname.getText().equals("Input name") || txtemail.getText().equals("Input email")
                || txtpassword.getText().equals("Input password") || txtconfirm.getText().equals("Confirm password")
                || txttelepon.getText().equals("Input telepon")) {
            JOptionPane.showMessageDialog(null, "Please Input All Inputan");
        } else {
            if (txtname.getText().equals("") || txtemail.getText().equals("")
                    || txtpassword.getText().equals("") || txtconfirm.getText().equals("")
                    || txttelepon.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Input All Inputan");
                txtname.requestFocus();
            } else {
                RegisterQuery();
            }
        }
    }

    private void KodeUser() {
        int randomNum = new Random().nextInt(900) + 100;

        String userId = "USR" + String.format("%03d", randomNum);

        kodeUser = userId;

        userIdCounter++;
    }

    private void RegisterQuery() {
        String role = "User";
        try {
            String sql = "INSERT INTO user VALUES ('" + kodeUser + "','" + txtname.getText() + "','" + txtemail.getText() + "','" + txtpassword.getText() + "','" + txttelepon.getText() + "','" + role + "')";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Register Succesfully.");
            ClearForm();

            LoginForm container = new LoginForm();

            JPanel loginPanel = container.getLoginPanel();

            this.removeAll();
            this.repaint();
            this.revalidate();
            this.add(loginPanel);
            this.repaint();
            this.revalidate();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RegisterPanel = new javax.swing.JPanel();
        lbljudul = new javax.swing.JLabel();
        lbldeskripsi = new javax.swing.JLabel();
        lblname = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        lblemail = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        lblpassword = new javax.swing.JLabel();
        lblconfirm = new javax.swing.JLabel();
        lbltelepon = new javax.swing.JLabel();
        txttelepon = new javax.swing.JTextField();
        btnregister = new rojerusan.RSMaterialButtonRectangle();
        lbllink = new javax.swing.JLabel();
        linklogin = new javax.swing.JLabel();
        txtpassword = new javax.swing.JPasswordField();
        txtconfirm = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(255, 255, 255));

        RegisterPanel.setBackground(new java.awt.Color(255, 255, 255));
        RegisterPanel.setPreferredSize(new java.awt.Dimension(561, 818));
        RegisterPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbljudul.setFont(new java.awt.Font("Verdana", 1, 60)); // NOI18N
        lbljudul.setText("REGISTER");
        RegisterPanel.add(lbljudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 22, -1, -1));

        lbldeskripsi.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lbldeskripsi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbldeskripsi.setText("Register now to unclock your exclusive access to contents.");
        RegisterPanel.add(lbldeskripsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 109, 465, -1));

        lblname.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblname.setText("Name");
        RegisterPanel.add(lblname, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 161, 163, -1));

        txtname.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtname.setForeground(new java.awt.Color(204, 204, 204));
        txtname.setText("Input name");
        txtname.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtname.setCaretColor(new java.awt.Color(93, 95, 239));
        txtname.setDisabledTextColor(new java.awt.Color(93, 95, 239));
        txtname.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txtname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtnameFocusLost(evt);
            }
        });
        txtname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnameActionPerformed(evt);
            }
        });
        RegisterPanel.add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 189, 446, 48));

        lblemail.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblemail.setText("Email address");
        RegisterPanel.add(lblemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 252, 163, -1));

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
        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });
        RegisterPanel.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 280, 446, 48));

        lblpassword.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblpassword.setText("Password");
        RegisterPanel.add(lblpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 343, 163, -1));

        lblconfirm.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblconfirm.setText("Confirm Password");
        RegisterPanel.add(lblconfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 436, 163, -1));

        lbltelepon.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbltelepon.setText("No Telepon");
        RegisterPanel.add(lbltelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 532, 163, -1));

        txttelepon.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txttelepon.setForeground(new java.awt.Color(204, 204, 204));
        txttelepon.setText("Input telepon");
        txttelepon.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txttelepon.setCaretColor(new java.awt.Color(93, 95, 239));
        txttelepon.setDisabledTextColor(new java.awt.Color(93, 95, 239));
        txttelepon.setMargin(new java.awt.Insets(2, 15, 2, 10));
        txttelepon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtteleponFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtteleponFocusLost(evt);
            }
        });
        txttelepon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtteleponActionPerformed(evt);
            }
        });
        RegisterPanel.add(txttelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 560, 446, 48));

        btnregister.setBackground(new java.awt.Color(204, 0, 0));
        btnregister.setText("REGISTER");
        btnregister.setBorderPainted(false);
        btnregister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnregister.setFont(new java.awt.Font("Roboto Medium", 1, 24)); // NOI18N
        btnregister.setRippleColor(new java.awt.Color(0, 255, 204));
        btnregister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregisterActionPerformed(evt);
            }
        });
        RegisterPanel.add(btnregister, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 649, 446, -1));

        lbllink.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        lbllink.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbllink.setText("Do you have an Account?");
        RegisterPanel.add(lbllink, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 747, -1, -1));

        linklogin.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        linklogin.setForeground(new java.awt.Color(204, 0, 0));
        linklogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        linklogin.setText("Login here");
        linklogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        linklogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                linkloginMouseClicked(evt);
            }
        });
        RegisterPanel.add(linklogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 747, 101, -1));

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
        RegisterPanel.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 371, 446, 50));

        txtconfirm.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        txtconfirm.setForeground(new java.awt.Color(204, 204, 204));
        txtconfirm.setText("Confirm password");
        txtconfirm.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txtconfirm.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txtconfirm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtconfirmFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtconfirmFocusLost(evt);
            }
        });
        txtconfirm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtconfirmKeyReleased(evt);
            }
        });
        RegisterPanel.add(txtconfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 469, 446, 48));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(RegisterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(RegisterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailActionPerformed

    private void txtteleponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtteleponActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtteleponActionPerformed

    private void linkloginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkloginMouseClicked
        // TODO add your handling code here:
        LoginForm container = new LoginForm();

        JPanel loginPanel = container.getLoginPanel();

        this.removeAll();
        this.repaint();
        this.revalidate();
        this.add(loginPanel);
        this.repaint();
        this.revalidate();

    }//GEN-LAST:event_linkloginMouseClicked

    private void btnregisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregisterActionPerformed
        // TODO add your handling code here:;
        LoadRegister();
    }//GEN-LAST:event_btnregisterActionPerformed

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

    private void txtconfirmFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtconfirmFocusGained
        // TODO add your handling code here:
        if (txtconfirm.getText().equals("Confirm password")) {
            txtconfirm.setText("");
            txtconfirm.setEchoChar('*');
            txtconfirm.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtconfirmFocusGained

    private void txtconfirmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtconfirmFocusLost
        // TODO add your handling code here:
        if (txtconfirm.getText().equals("")) {
            txtconfirm.setText("Confirm password");
            txtconfirm.setEchoChar((char) 0);
            txtconfirm.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_txtconfirmFocusLost

    private void txtnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnameFocusGained
        // TODO add your handling code here:
        if (txtname.getText().equals("Input name")) {
            txtname.setText("");
            txtname.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtnameFocusGained

    private void txtnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnameFocusLost
        // TODO add your handling code here:
        if (txtname.getText().equals("")) {
            txtname.setText("Input name");
            txtname.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_txtnameFocusLost

    private void txtemailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtemailFocusGained
        // TODO add your handling code here:
        if (txtemail.getText().equals("Input email")) {
            txtemail.setText("");
            txtemail.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtemailFocusGained

    private void txtteleponFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtteleponFocusGained
        // TODO add your handling code here:
        if (txttelepon.getText().equals("Input telepon")) {
            txttelepon.setText("");
            txttelepon.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtteleponFocusGained

    private void txtteleponFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtteleponFocusLost
        // TODO add your handling code here:
        if (txttelepon.getText().equals("")) {
            txttelepon.setText("Input telepon");
            txttelepon.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_txtteleponFocusLost

    private void txtemailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtemailFocusLost
        // TODO add your handling code here:
        if (txtemail.getText().equals("")) {
            txtemail.setText("Input email");
            txtemail.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_txtemailFocusLost

    private void txtconfirmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtconfirmKeyReleased
        // TODO add your handling code here:
        ValidasiPassword();
    }//GEN-LAST:event_txtconfirmKeyReleased

    private void txtnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel RegisterPanel;
    public rojerusan.RSMaterialButtonRectangle btnregister;
    public javax.swing.JLabel lblconfirm;
    public javax.swing.JLabel lbldeskripsi;
    public javax.swing.JLabel lblemail;
    public javax.swing.JLabel lbljudul;
    public javax.swing.JLabel lbllink;
    public javax.swing.JLabel lblname;
    public javax.swing.JLabel lblpassword;
    public javax.swing.JLabel lbltelepon;
    public javax.swing.JLabel linklogin;
    public javax.swing.JPasswordField txtconfirm;
    public javax.swing.JTextField txtemail;
    public javax.swing.JTextField txtname;
    public javax.swing.JPasswordField txtpassword;
    public javax.swing.JTextField txttelepon;
    // End of variables declaration//GEN-END:variables
}
