/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.manager;

import frontend.admin.NotificationPopUp;

import javax.swing.JFrame;
import java.awt.*;

/**
 *
 * @author Chun Ming
 */
public class VendorItems extends javax.swing.JFrame {

    private JFrame previousFrame;
    private NotificationPopUp notificationPopUp = new NotificationPopUp();

    public VendorItems(JFrame frame) {
        previousFrame = frame;
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pageHolder = new javax.swing.JPanel();
        itemListScroll = new javax.swing.JScrollPane();
        title = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        deleteTest = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pageHolder.setBackground(new java.awt.Color(255, 251, 233));

        title.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        title.setText("Vendor Item");

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/close_icon.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeMouseExited(evt);
            }
        });

        deleteTest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/delete_icon.png"))); // NOI18N
        deleteTest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteTestMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pageHolderLayout = new javax.swing.GroupLayout(pageHolder);
        pageHolder.setLayout(pageHolderLayout);
        pageHolderLayout.setHorizontalGroup(
            pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageHolderLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pageHolderLayout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteTest)
                        .addGap(169, 169, 169)
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(itemListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        pageHolderLayout.setVerticalGroup(
            pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageHolderLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteTest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(itemListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        getContentPane().add(pageHolder, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        previousFrame.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_closeMouseClicked

    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_closeMouseEntered

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        close.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_closeMouseExited

    private void deleteTestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteTestMouseClicked
        notificationPopUp.deleteItem(this);
        this.setEnabled(false);
    }//GEN-LAST:event_deleteTestMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel close;
    private javax.swing.JLabel deleteTest;
    private javax.swing.JScrollPane itemListScroll;
    private javax.swing.JPanel pageHolder;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
