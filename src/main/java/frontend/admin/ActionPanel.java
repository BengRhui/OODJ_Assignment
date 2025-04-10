/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.admin;

import frontend.home_page.HomePage;
import frontend.pop_up.SystemPopUp;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Chun Ming (TP068983), Beng Rhui (TP068495)
 */
public class ActionPanel extends javax.swing.JPanel {

    /**
     * Creates new form ActionPanel
     */
    public ActionPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        logoPicture = new javax.swing.JLabel();
        logoutIcon = new javax.swing.JLabel();
        welcomeTitle = new javax.swing.JLabel();
        welcomeDescription = new javax.swing.JLabel();
        manageStoreButton = new javax.swing.JButton();
        manageUserButton = new javax.swing.JButton();
        topUpButton = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new Color(0, 0, 0, 20));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/logo_light_with_text.png"))); // NOI18N
        jPanel1.add(logoPicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 150, 120));

        logoutIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoutIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/logout_icon.png"))); // NOI18N
        logoutIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutIconMouseClicked(evt);
            }
        });
        jPanel1.add(logoutIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 50, 80, 90));

        welcomeTitle.setFont(new java.awt.Font("Arial", 3, 60)); // NOI18N
        welcomeTitle.setForeground(new java.awt.Color(255, 255, 255));
        welcomeTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeTitle.setText("Welcome Back, Admin!");
        jPanel1.add(welcomeTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 1400, -1));

        welcomeDescription.setFont(new java.awt.Font("Arial", 2, 48)); // NOI18N
        welcomeDescription.setForeground(new java.awt.Color(255, 255, 255));
        welcomeDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeDescription.setText("“Time to start your work for today!”");
        jPanel1.add(welcomeDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 1400, -1));

        manageStoreButton.setBackground(Color.WHITE);
        manageStoreButton.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        manageStoreButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/store_icon.png"))); // NOI18N
        manageStoreButton.setText("Manage Stall");
        manageStoreButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        manageStoreButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        manageStoreButton.setFocusPainted(false);
        manageStoreButton.setIconTextGap(20);
        manageStoreButton.setOpaque(true);
        manageStoreButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                manageStoreButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                manageStoreButtonMouseExited(evt);
            }
        });
        manageStoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageStoreButtonActionPerformed(evt);
            }
        });
        jPanel1.add(manageStoreButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 280, 90));

        manageUserButton.setBackground(Color.WHITE);
        manageUserButton.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        manageUserButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/user_icon.png"))); // NOI18N
        manageUserButton.setText("Manage User");
        manageUserButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        manageUserButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        manageUserButton.setFocusPainted(false);
        manageUserButton.setIconTextGap(20);
        manageUserButton.setOpaque(true);
        manageUserButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                manageUserButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                manageUserButtonMouseExited(evt);
            }
        });
        manageUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageUserButtonActionPerformed(evt);
            }
        });
        jPanel1.add(manageUserButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 450, 280, 90));

        topUpButton.setBackground(Color.WHITE);
        topUpButton.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        topUpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/top_up_icon.png"))); // NOI18N
        topUpButton.setText("Top Up Wallet");
        topUpButton.setToolTipText("");
        topUpButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        topUpButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        topUpButton.setFocusPainted(false);
        topUpButton.setIconTextGap(20);
        topUpButton.setOpaque(true);
        topUpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                topUpButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                topUpButtonMouseExited(evt);
            }
        });
        topUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topUpButtonActionPerformed(evt);
            }
        });
        jPanel1.add(topUpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 450, 280, 90));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 800));
    }// </editor-fold>//GEN-END:initComponents

    private void manageUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageUserButtonActionPerformed

        // Set card to user list panel
        MainPage.setCard("userlistPanel");
    }//GEN-LAST:event_manageUserButtonActionPerformed

    private void topUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topUpButtonActionPerformed

        // Set card to customer credit panel
        MainPage.setCard("customerCreditPanel");
    }//GEN-LAST:event_topUpButtonActionPerformed

    private void manageStoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageStoreButtonActionPerformed

        // Set card to manage store panel
        MainPage.setCard("storePanel");
    }//GEN-LAST:event_manageStoreButtonActionPerformed

    private void manageStoreButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageStoreButtonMouseEntered

        // Change the text to bold
        manageStoreButton.setFont(new Font("Arial", Font.BOLD, 24));
        
        // Change the button colour to brown
        manageStoreButton.setBackground(new Color(227, 202, 165));
    }//GEN-LAST:event_manageStoreButtonMouseEntered

    private void manageStoreButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageStoreButtonMouseExited

        // Change the text to plain
        manageStoreButton.setFont(new Font("Arial", Font.PLAIN, 24));
        
        // Change the button colour to white
        manageStoreButton.setBackground(Color.WHITE);
    }//GEN-LAST:event_manageStoreButtonMouseExited

    private void manageUserButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageUserButtonMouseEntered

        // Change the text to bold
        manageUserButton.setFont(new Font("Arial", Font.BOLD, 24));
        
        // Change the button colour to brown
        manageUserButton.setBackground(new Color(227, 202, 165));
    }//GEN-LAST:event_manageUserButtonMouseEntered

    private void manageUserButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageUserButtonMouseExited

        // Change the text to plain
        manageUserButton.setFont(new Font("Arial", Font.PLAIN, 24));
        
        // Change the button colour to white
        manageUserButton.setBackground(Color.WHITE);
    }//GEN-LAST:event_manageUserButtonMouseExited

    private void topUpButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topUpButtonMouseEntered

        // Change the text to bold
        topUpButton.setFont(new Font("Arial", Font.BOLD, 24));
        
        // Change the button colour to brown
        topUpButton.setBackground(new Color(227, 202, 165));
    }//GEN-LAST:event_topUpButtonMouseEntered

    private void topUpButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topUpButtonMouseExited

        // Change the text to plain
        topUpButton.setFont(new Font("Arial", Font.PLAIN, 24));
        
        // Change the button colour to white
        topUpButton.setBackground(Color.WHITE);
    }//GEN-LAST:event_topUpButtonMouseExited

    private void logoutIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutIconMouseClicked

        // Show message to confirm logout
        SystemPopUp logoutConfirmation = new SystemPopUp(
                MainPage.currentFrame,
                "Logout from System",
                "Are you sure you wish to logout from the system?",
                new String[]{"No", "Yes"}
        );
        logoutConfirmation.setVisible(true);
        
        // Get the status of the notification
        int status = logoutConfirmation.getStatus();
        
        // If "Yes" is chosen
        if (status == 1) {
            
            // Display a message to indicate that logout is successful
            SystemPopUp successLogout = new SystemPopUp(
                MainPage.currentFrame,
                "Logout Success",
                "Thank you for using the system!",
                new String[]{"OK"}
            );
            successLogout.setVisible(true);
            
            // Redirect users to home page
            HomePage homePage = new HomePage();
            homePage.setVisible(true);
            homePage.setLocationRelativeTo(this);
            
            // Dispose the parent frame
            MainPage.currentFrame.dispose();

            // Set the runner instance to null (to avoid any errors when login again)
            MainPage.setAdmin(null);
        }
    }//GEN-LAST:event_logoutIconMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logoPicture;
    private javax.swing.JLabel logoutIcon;
    private javax.swing.JButton manageStoreButton;
    private javax.swing.JButton manageUserButton;
    private javax.swing.JButton topUpButton;
    private javax.swing.JLabel welcomeDescription;
    private javax.swing.JLabel welcomeTitle;
    // End of variables declaration//GEN-END:variables
}
