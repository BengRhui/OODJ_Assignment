/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.manager;

import frontend.admin.NotificationPopUp;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;

/**
 *
 * @author Chun Ming
 */
public class UserStats extends javax.swing.JFrame {

    //state variable declaration
    private JFrame mainFrame;
    private String userType;
    private VendorItems vendorItems;
    private NotificationPopUp notificationPopUp = new NotificationPopUp();
    private TimeFilter filter;

    /*
    userType: temporary state that store from where Manager call this pop-up
    mainFrame: to store the mainFrame pass into method to set back the last frame to Enable
     */

    public UserStats() {}

    public javax.swing.JFrame vendorStatsPopUp (JFrame frame) {
        userType = "vendor";
        mainFrame = frame;
        initComponents();
        title.setText("Overview for Vendor ID");
        chartTitle.setText("Revenue");
        kpiLabel1.setText("Total Item Sold");
        kpiValue1.setText("253");
        kpiLabel2.setText("Total Earnings");
        kpiValue2.setText("RM" + "12345");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        return this;
    }

    public javax.swing.JFrame runnerStatsPopUp (JFrame frame) {
        userType = "runner";
        mainFrame = frame;
        initComponents();
        title.setText("Overview for Runner ID");
        chartTitle.setText("Delivery Count");
        kpiLabel1.setText("Total Delivery");
        kpiValue1.setText("253");
        kpiLabel2.setText("Total Tips");
        kpiValue2.setText("RM" + "12345");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pageHolder = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        chartTitle = new javax.swing.JLabel();
        revenueExcel = new javax.swing.JLabel();
        revenueFilter = new javax.swing.JLabel();
        vendorItemList = new javax.swing.JLabel();
        kpiHolder1 = new javax.swing.JPanel();
        kpiLabel1 = new javax.swing.JLabel();
        kpiValue1 = new javax.swing.JLabel();
        kpiHolder2 = new javax.swing.JPanel();
        kpiLabel2 = new javax.swing.JLabel();
        kpiValue2 = new javax.swing.JLabel();
        ratingHolder = new javax.swing.JPanel();
        ratingLabel = new javax.swing.JLabel();
        rating = new javax.swing.JLabel();
        feedbackCount = new javax.swing.JLabel();
        chartHolder = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pageHolder.setBackground(new java.awt.Color(255, 251, 233));

        title.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        title.setText("Overview for Vendor 001");

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

        chartTitle.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        chartTitle.setText("Revenue");

        revenueExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/excel_icon.png"))); // NOI18N
        revenueExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                revenueExcelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                revenueExcelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                revenueExcelMouseExited(evt);
            }
        });

        revenueFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/filter_icon.png"))); // NOI18N
        revenueFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                revenueFilterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                revenueFilterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                revenueFilterMouseExited(evt);
            }
        });

        vendorItemList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/vendor_Item.png"))); // NOI18N
        vendorItemList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendorItemListMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vendorItemListMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vendorItemListMouseExited(evt);
            }
        });

        kpiHolder1.setBackground(new java.awt.Color(227, 202, 165));

        kpiLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        kpiLabel1.setText("kpiLabel");

        kpiValue1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        kpiValue1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        kpiValue1.setText("kpiValue");

        javax.swing.GroupLayout kpiHolder1Layout = new javax.swing.GroupLayout(kpiHolder1);
        kpiHolder1.setLayout(kpiHolder1Layout);
        kpiHolder1Layout.setHorizontalGroup(
            kpiHolder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kpiHolder1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(kpiLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(kpiValue1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kpiHolder1Layout.setVerticalGroup(
            kpiHolder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kpiHolder1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(kpiHolder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kpiLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kpiValue1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        kpiHolder2.setBackground(new java.awt.Color(227, 202, 165));

        kpiLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        kpiLabel2.setText("kpiLabel");

        kpiValue2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        kpiValue2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        kpiValue2.setText("kpiValue");

        javax.swing.GroupLayout kpiHolder2Layout = new javax.swing.GroupLayout(kpiHolder2);
        kpiHolder2.setLayout(kpiHolder2Layout);
        kpiHolder2Layout.setHorizontalGroup(
            kpiHolder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kpiHolder2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(kpiLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(kpiValue2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kpiHolder2Layout.setVerticalGroup(
            kpiHolder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kpiHolder2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(kpiHolder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kpiLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kpiValue2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        ratingHolder.setBackground(new java.awt.Color(227, 202, 165));

        ratingLabel.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        ratingLabel.setText("<html>Overall<br>Rating</html>"
        );

        rating.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        rating.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rating.setText("4.9");

        feedbackCount.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        feedbackCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        feedbackCount.setText("(120 Rating)");

        javax.swing.GroupLayout ratingHolderLayout = new javax.swing.GroupLayout(ratingHolder);
        ratingHolder.setLayout(ratingHolderLayout);
        ratingHolderLayout.setHorizontalGroup(
            ratingHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ratingHolderLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(ratingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(ratingHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rating, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(feedbackCount, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                .addGap(72, 72, 72))
        );
        ratingHolderLayout.setVerticalGroup(
            ratingHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ratingHolderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(ratingHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ratingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ratingHolderLayout.createSequentialGroup()
                        .addComponent(rating, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(feedbackCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout chartHolderLayout = new javax.swing.GroupLayout(chartHolder);
        chartHolder.setLayout(chartHolderLayout);
        chartHolderLayout.setHorizontalGroup(
            chartHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
        );
        chartHolderLayout.setVerticalGroup(
            chartHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pageHolderLayout = new javax.swing.GroupLayout(pageHolder);
        pageHolder.setLayout(pageHolderLayout);
        pageHolderLayout.setHorizontalGroup(
            pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageHolderLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pageHolderLayout.createSequentialGroup()
                        .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pageHolderLayout.createSequentialGroup()
                                .addComponent(chartTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(revenueExcel)
                                .addGap(19, 19, 19)
                                .addComponent(revenueFilter))
                            .addComponent(chartHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kpiHolder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kpiHolder2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ratingHolder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pageHolderLayout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(287, 287, 287)
                        .addComponent(vendorItemList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        pageHolderLayout.setVerticalGroup(
            pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageHolderLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pageHolderLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(vendorItemList))
                    .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pageHolderLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pageHolderLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(chartTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(revenueExcel)
                            .addComponent(revenueFilter))
                        .addGap(14, 14, 14)
                        .addComponent(chartHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pageHolderLayout.createSequentialGroup()
                        .addComponent(kpiHolder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(kpiHolder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(ratingHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(pageHolder, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void revenueExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueExcelMouseClicked
        notificationPopUp.managerExportPopUp(this);
        this.setEnabled(false);
    }//GEN-LAST:event_revenueExcelMouseClicked

    private void revenueExcelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueExcelMouseEntered
        revenueExcel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_revenueExcelMouseEntered

    private void revenueExcelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueExcelMouseExited
        revenueExcel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_revenueExcelMouseExited

    private void revenueFilterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueFilterMouseClicked
        filter = new TimeFilter(this);
        this.setEnabled(false);
    }//GEN-LAST:event_revenueFilterMouseClicked

    private void revenueFilterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueFilterMouseEntered
        revenueFilter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_revenueFilterMouseEntered

    private void revenueFilterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueFilterMouseExited
        revenueFilter.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_revenueFilterMouseExited

    private void vendorItemListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendorItemListMouseClicked
        if (userType.equals("runner")){
            //here should go to runner feedback
        }else if(userType.equals("vendor")){
            vendorItems = new VendorItems(this);
            this.setEnabled(false);
        }
    }//GEN-LAST:event_vendorItemListMouseClicked

    private void vendorItemListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendorItemListMouseEntered
        vendorItemList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_vendorItemListMouseEntered

    private void vendorItemListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendorItemListMouseExited
        vendorItemList.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_vendorItemListMouseExited

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        mainFrame.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_closeMouseClicked

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        close.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_closeMouseExited

    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_closeMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartHolder;
    private javax.swing.JLabel chartTitle;
    private javax.swing.JLabel close;
    private javax.swing.JLabel feedbackCount;
    private javax.swing.JPanel kpiHolder1;
    private javax.swing.JPanel kpiHolder2;
    private javax.swing.JLabel kpiLabel1;
    private javax.swing.JLabel kpiLabel2;
    private javax.swing.JLabel kpiValue1;
    private javax.swing.JLabel kpiValue2;
    private javax.swing.JPanel pageHolder;
    private javax.swing.JLabel rating;
    private javax.swing.JPanel ratingHolder;
    private javax.swing.JLabel ratingLabel;
    private javax.swing.JLabel revenueExcel;
    private javax.swing.JLabel revenueFilter;
    private javax.swing.JLabel title;
    private javax.swing.JLabel vendorItemList;
    // End of variables declaration//GEN-END:variables
}
