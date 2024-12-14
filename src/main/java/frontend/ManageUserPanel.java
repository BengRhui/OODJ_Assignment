package frontend;

import java.awt.Cursor;

/**
 *
 * @author Chun Ming
 */
public class ManageUserPanel extends javax.swing.JPanel {

    public AdminFrame adminFrame;

    public ManageUserPanel() {
        initComponents();
        initLayout();
    }

    public void setAdminFrame(AdminFrame adminFrame) {
        this.adminFrame = adminFrame;
    }

    private void initLayout(){
        bgLayer.setBackground(new java.awt.Color(255, 251, 233, 180));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgLayer = new javax.swing.JPanel();
        userListLabel = new javax.swing.JLabel();
        filter = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        registerUser = new javax.swing.JButton();
        userListHolder = new javax.swing.JScrollPane();
        background = new javax.swing.JLabel();

        setLayout(null);

        bgLayer.setBackground(new java.awt.Color(255, 251, 233));
        bgLayer.setLayout(null);

        userListLabel.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        userListLabel.setText("User List");
        bgLayer.add(userListLabel);
        userListLabel.setBounds(80, 50, 220, 70);

        filter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/filter_icon_1.png"))); // NOI18N
        bgLayer.add(filter);
        filter.setBounds(1090, 50, 110, 70);

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/logout_icon_1.png"))); // NOI18N
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutMouseExited(evt);
            }
        });
        bgLayer.add(logout);
        logout.setBounds(1230, 50, 70, 70);

        backButton.setBackground(new java.awt.Color(227, 202, 165));
        backButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        backButton.setText("Back");
        backButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        backButton.setPreferredSize(new java.awt.Dimension(200, 50));
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButtonMouseExited(evt);
            }
        });
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        bgLayer.add(backButton);
        backButton.setBounds(800, 710, 230, 60);

        registerUser.setBackground(new java.awt.Color(173, 139, 115));
        registerUser.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        registerUser.setForeground(new java.awt.Color(255, 255, 255));
        registerUser.setText("Register New User");
        registerUser.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        registerUser.setPreferredSize(new java.awt.Dimension(200, 50));
        registerUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerUserMouseExited(evt);
            }
        });
        registerUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerUserActionPerformed(evt);
            }
        });
        bgLayer.add(registerUser);
        registerUser.setBounds(1070, 710, 230, 60);

        userListHolder.setBackground(new java.awt.Color(255, 255, 255));
        userListHolder.setForeground(new java.awt.Color(255, 255, 255));
        bgLayer.add(userListHolder);
        userListHolder.setBounds(90, 140, 1210, 540);

        add(bgLayer);
        bgLayer.setBounds(0, 0, 1400, 800);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/admin_background.jpg"))); // NOI18N
        add(background);
        background.setBounds(0, 0, 1460, 800);
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseEntered
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setBackground(new java.awt.Color(227, 195, 150));
    }//GEN-LAST:event_backButtonMouseEntered

    private void backButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseExited
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        backButton.setBackground(new java.awt.Color(227, 202, 165));
    }//GEN-LAST:event_backButtonMouseExited

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        adminFrame.cardLayout.show(adminFrame.getPageHolder(), "adminHome");
    }//GEN-LAST:event_backButtonActionPerformed

    private void registerUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerUserMouseEntered
        registerUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerUser.setBackground(new java.awt.Color(173, 120, 110));
    }//GEN-LAST:event_registerUserMouseEntered

    private void registerUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerUserMouseExited
        registerUser.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        registerUser.setBackground(new java.awt.Color(173, 139, 115));
    }//GEN-LAST:event_registerUserMouseExited

    private void registerUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerUserActionPerformed
        
//        State control, use if to get into different userType form
//        if (filterType.contains("C")){
//            CustomerDetailForm form = new CustomerDetailForm();
//        }else if (filterType.contains("V")){
//            VendorDetailForm form = new VendorDetailForm();
//        }else if (filterType.contains("R")){
            RunnerDetailForm form = new RunnerDetailForm();
//        }
//        
        form.setVisible(true);
    }//GEN-LAST:event_registerUserActionPerformed

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        // Here should redirect to MainHome
    }//GEN-LAST:event_logoutMouseClicked

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_logoutMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel background;
    private javax.swing.JPanel bgLayer;
    private javax.swing.JLabel filter;
    private javax.swing.JLabel logout;
    private javax.swing.JButton registerUser;
    private javax.swing.JScrollPane userListHolder;
    private javax.swing.JLabel userListLabel;
    // End of variables declaration//GEN-END:variables
}
