/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.admin_CM;

import java.awt.Cursor;

/**
 *
 * @author Chun Ming
 */
public class ManageStorePanel extends javax.swing.JPanel {

    public AdminFrame adminFrame;
    NotificationPopUp notificationPopUp = new NotificationPopUp();
    AdminPopUp adminPopUp = new AdminPopUp();

    public ManageStorePanel() {
        initComponents();
        initLayout();
        initContent();
    }

    public void setAdminFrame(AdminFrame adminFrame) {
        this.adminFrame = adminFrame;
    }

    private void initLayout(){
        //show half transparent background
        bgLayer.setBackground(new java.awt.Color(255, 251, 233, 180));
    }

    private void initContent(){
        //to initialize the storeListHolder
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgLayer = new javax.swing.JPanel();
        storeListLabel = new javax.swing.JLabel();
        storeListHolder = new javax.swing.JScrollPane();
        registerStore = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        logout = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setLayout(null);

        bgLayer.setBackground(new java.awt.Color(255, 251, 233));

        storeListLabel.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        storeListLabel.setText("Store List");

        storeListHolder.setBackground(new java.awt.Color(255, 255, 255));
        storeListHolder.setForeground(new java.awt.Color(255, 255, 255));

        registerStore.setBackground(new java.awt.Color(173, 139, 115));
        registerStore.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        registerStore.setForeground(new java.awt.Color(255, 255, 255));
        registerStore.setText("Register New Store");
        registerStore.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        registerStore.setOpaque(true);
        registerStore.setPreferredSize(new java.awt.Dimension(200, 50));
        registerStore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerStoreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerStoreMouseExited(evt);
            }
        });
        registerStore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerStoreActionPerformed(evt);
            }
        });

        backButton.setBackground(new java.awt.Color(227, 202, 165));
        backButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        backButton.setText("Back");
        backButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        backButton.setOpaque(true);
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

        javax.swing.GroupLayout bgLayerLayout = new javax.swing.GroupLayout(bgLayer);
        bgLayer.setLayout(bgLayerLayout);
        bgLayerLayout.setHorizontalGroup(
            bgLayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayerLayout.createSequentialGroup()
                .addGroup(bgLayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bgLayerLayout.createSequentialGroup()
                        .addContainerGap(819, Short.MAX_VALUE)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(registerStore, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayerLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(storeListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74))
            .addGroup(bgLayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayerLayout.createSequentialGroup()
                    .addContainerGap(65, Short.MAX_VALUE)
                    .addComponent(storeListHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 1260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(75, Short.MAX_VALUE)))
        );
        bgLayerLayout.setVerticalGroup(
            bgLayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayerLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(bgLayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(storeListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 587, Short.MAX_VALUE)
                .addGroup(bgLayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerStore, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
            .addGroup(bgLayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bgLayerLayout.createSequentialGroup()
                    .addGap(0, 110, Short.MAX_VALUE)
                    .addComponent(storeListHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 150, Short.MAX_VALUE)))
        );

        add(bgLayer);
        bgLayer.setBounds(0, 0, 1400, 800);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/store_background.png"))); // NOI18N
        background.setToolTipText("");
        add(background);
        background.setBounds(0, 0, 1400, 800);
    }// </editor-fold>//GEN-END:initComponents

    private void registerStoreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerStoreMouseEntered
        registerStore.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerStore.setBackground(new java.awt.Color(173, 120, 110));
    }//GEN-LAST:event_registerStoreMouseEntered

    private void registerStoreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerStoreMouseExited
        registerStore.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        registerStore.setBackground(new java.awt.Color(173, 139, 115));
    }//GEN-LAST:event_registerStoreMouseExited

    private void registerStoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerStoreActionPerformed
        //Show pop out form
        adminPopUp.updateStore(adminFrame);
        adminFrame.setEnabled(false);
//        notificationPopUp.updateStoreMsg();
    }//GEN-LAST:event_registerStoreActionPerformed

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
    private javax.swing.JLabel logout;
    private javax.swing.JButton registerStore;
    private javax.swing.JScrollPane storeListHolder;
    private javax.swing.JLabel storeListLabel;
    // End of variables declaration//GEN-END:variables
}
