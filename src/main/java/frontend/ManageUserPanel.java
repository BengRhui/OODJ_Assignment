package frontend;

import java.awt.Cursor;

/**
 *
 * @author Chun Ming
 */
public class ManageUserPanel extends javax.swing.JPanel {

    public AdminFrame adminFrame;
    AdminPopUp adminPopUp = new AdminPopUp();

    public ManageUserPanel() {
        initComponents();
        initLayout();
        initContent();
    }

    public void setAdminFrame(AdminFrame adminFrame) {
        this.adminFrame = adminFrame;
    }

    private void initLayout(){
        bgLayer.setBackground(new java.awt.Color(255, 251, 233, 180));
    }

    private void initContent(){
        //ScrollPane content initialization
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
        deleteTest = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setLayout(null);

        bgLayer.setBackground(new java.awt.Color(255, 251, 233));
        bgLayer.setLayout(null);

        userListLabel.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        userListLabel.setText("User List");
        bgLayer.add(userListLabel);
        userListLabel.setBounds(80, 50, 220, 70);

        filter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/filter_icon_1.png"))); // NOI18N
        filter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                filterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                filterMouseExited(evt);
            }
        });
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

        deleteTest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/delete_icon.png"))); // NOI18N
        deleteTest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteTestMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteTestMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteTestMouseExited(evt);
            }
        });
        bgLayer.add(deleteTest);
        deleteTest.setBounds(990, 50, 80, 70);

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
        System.out.println(AdminPopUp.userType);
//       to get into different userType form based on selection, default to customer
        if (AdminPopUp.userType.equals("Customer")){
            CustomerDetailForm form = new CustomerDetailForm(adminFrame);
            form.setVisible(true);
        }else if (AdminPopUp.userType.equals("Vendor")){
            VendorDetailForm form = new VendorDetailForm(adminFrame);
            form.setVisible(true);
        }else if (AdminPopUp.userType.equals("Runner")){
            RunnerDetailForm form = new RunnerDetailForm(adminFrame);
            form.setVisible(true);
        }else{
            CustomerDetailForm form = new CustomerDetailForm(adminFrame);
            form.setVisible(true);
        }

        adminFrame.setEnabled(false);
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

    private void deleteTestMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteTestMouseEntered
        deleteTest.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_deleteTestMouseEntered

    private void deleteTestMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteTestMouseExited
        deleteTest.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_deleteTestMouseExited

    private void deleteTestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteTestMouseClicked
        //delete pop-up test
        adminPopUp.deleteUser(adminFrame);
        adminFrame.setEnabled(false);
    }//GEN-LAST:event_deleteTestMouseClicked

    private void filterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filterMouseClicked
        adminPopUp.userTypeFilter(this);
    }//GEN-LAST:event_filterMouseClicked

    private void filterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filterMouseEntered
        filter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_filterMouseEntered

    private void filterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filterMouseExited
        filter.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_filterMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel background;
    private javax.swing.JPanel bgLayer;
    private javax.swing.JLabel deleteTest;
    private javax.swing.JLabel filter;
    private javax.swing.JLabel logout;
    private javax.swing.JButton registerUser;
    private javax.swing.JScrollPane userListHolder;
    private javax.swing.JLabel userListLabel;
    // End of variables declaration//GEN-END:variables
}
