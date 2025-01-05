/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.manager;

import frontend.admin.NotificationPopUp;

import javax.swing.*;
import java.awt.*;


/**
 * @author Chun Ming
 */
public class ManagerFrame extends javax.swing.JFrame {

    public String currentPage;
    public CardLayout cardLayout;
    private NotificationPopUp notificationPopUp = new NotificationPopUp();
    private TimeFilter filter;
    private CheckVendor checkVendorPage;
    private CheckRunner checkRunnerPage;
    private FeedbackPane complaintsPage;

    public ManagerFrame() {
        cardLayout = new CardLayout();
        contentHolder = new javax.swing.JPanel(cardLayout);
        initComponents();
        initLayout();
        initContent();
        cardLayout = (CardLayout) contentHolder.getLayout();
    }

    private void initLayout(){
        //initialize the layout for better visualization

        checkVendorPage = new CheckVendor();
        checkRunnerPage = new CheckRunner();
        complaintsPage = new FeedbackPane();
        header.setBackground(new java.awt.Color(0, 0, 0, 220));
        contentHolder.setBackground(new java.awt.Color(255, 251, 233, 240));
        dashboardLabel.setText("<html><b><u>Dashboard</b></u></html>");
        dashboard.setBackground(new java.awt.Color(255, 251, 233, 0));
        dashboardContent.setBackground(new java.awt.Color(255, 251, 233, 240));
        contentHolder.add(checkVendorPage,"checkVendor");
        contentHolder.add(checkRunnerPage,"checkRunner");
        contentHolder.add(complaintsPage,"checkComplaints");
        this.setLocationRelativeTo(null);
        checkVendorPage.setMainFrame(this);
        checkRunnerPage.setMainFrame(this);
        complaintsPage.setMainFrame(this);
    }

    private void initContent(){

    }

    private void labelFormat(JLabel dashboard, JLabel vendor, JLabel runner, JLabel complaints){
        switch(currentPage){
            case "dashboard":
                dashboard.setText("<html><b><u>Dashboard</b></u></html>");
                vendor.setText("Vendor");
                runner.setText("Delivery Runner");
                complaints.setText("Complaints");
                break;
            case "vendor":
                dashboard.setText("Dashboard");
                vendor.setText("<html><b><u>Vendor</b></u></html>");
                runner.setText("Delivery Runner");
                complaints.setText("Complaints");
                break;
            case "runner":
                dashboard.setText("Dashboard");
                vendor.setText("Vendor");
                runner.setText("<html><b><u>Delivery Runner</b></u></html>");
                complaints.setText("Complaints");
                break;
            case "complaints":
                dashboard.setText("Dashboard");
                vendor.setText("Vendor");
                runner.setText("Delivery Runner");
                complaints.setText("<html><b><u>Complaints</b></u></html>");
                break;
            default:
                System.out.println("Wrong Case is detected.");
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pageHolder = new javax.swing.JPanel();
        contentHolder = new javax.swing.JPanel();
        dashboard = new javax.swing.JPanel();
        dashboardContent = new javax.swing.JPanel();
        revenue = new javax.swing.JLabel();
        revenueExcel = new javax.swing.JLabel();
        revenueFilter = new javax.swing.JLabel();
        revenueChartHolder = new javax.swing.JPanel();
        feedbacks = new javax.swing.JLabel();
        feedbackExcel = new javax.swing.JLabel();
        feedbackFilter = new javax.swing.JLabel();
        feedbacksChartHolder = new javax.swing.JPanel();
        bottomBackground = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        dashboardLabel = new javax.swing.JLabel();
        vendorLabel = new javax.swing.JLabel();
        runnerLabel = new javax.swing.JLabel();
        feedbacksLabel = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pageHolder.setPreferredSize(new java.awt.Dimension(1400, 800));
        pageHolder.setLayout(null);

        contentHolder.setBackground(new java.awt.Color(255, 251, 233));
        contentHolder.setPreferredSize(new java.awt.Dimension(1400, 650));
        contentHolder.setLayout(new java.awt.CardLayout());

        dashboard.setBackground(new java.awt.Color(255, 251, 233));
        dashboard.setLayout(null);

        dashboardContent.setBackground(new java.awt.Color(255, 251, 233));
        dashboardContent.setLayout(null);

        revenue.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        revenue.setText("Revenue");
        dashboardContent.add(revenue);
        revenue.setBounds(42, 54, 280, 56);

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
        dashboardContent.add(revenueExcel);
        revenueExcel.setBounds(566, 50, 61, 60);

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
        dashboardContent.add(revenueFilter);
        revenueFilter.setBounds(645, 50, 68, 60);

        javax.swing.GroupLayout revenueChartHolderLayout = new javax.swing.GroupLayout(revenueChartHolder);
        revenueChartHolder.setLayout(revenueChartHolderLayout);
        revenueChartHolderLayout.setHorizontalGroup(
            revenueChartHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        revenueChartHolderLayout.setVerticalGroup(
            revenueChartHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        dashboardContent.add(revenueChartHolder);
        revenueChartHolder.setBounds(42, 128, 671, 480);

        feedbacks.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        feedbacks.setText("Feedbacks");
        dashboardContent.add(feedbacks);
        feedbacks.setBounds(749, 50, 280, 56);

        feedbackExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/excel_icon.png"))); // NOI18N
        feedbackExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                feedbackExcelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                feedbackExcelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                feedbackExcelMouseExited(evt);
            }
        });
        dashboardContent.add(feedbackExcel);
        feedbackExcel.setBounds(1220, 50, 61, 60);

        feedbackFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/filter_icon.png"))); // NOI18N
        feedbackFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                feedbackFilterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                feedbackFilterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                feedbackFilterMouseExited(evt);
            }
        });
        dashboardContent.add(feedbackFilter);
        feedbackFilter.setBounds(1300, 50, 68, 60);

        javax.swing.GroupLayout feedbacksChartHolderLayout = new javax.swing.GroupLayout(feedbacksChartHolder);
        feedbacksChartHolder.setLayout(feedbacksChartHolderLayout);
        feedbacksChartHolderLayout.setHorizontalGroup(
            feedbacksChartHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 619, Short.MAX_VALUE)
        );
        feedbacksChartHolderLayout.setVerticalGroup(
            feedbacksChartHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        dashboardContent.add(feedbacksChartHolder);
        feedbacksChartHolder.setBounds(749, 128, 619, 480);

        dashboard.add(dashboardContent);
        dashboardContent.setBounds(0, 0, 1400, 650);

        bottomBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/manager_background_bottom.png"))); // NOI18N
        dashboard.add(bottomBackground);
        bottomBackground.setBounds(0, 0, 1400, 650);

        contentHolder.add(dashboard, "managerDashboard");

        pageHolder.add(contentHolder);
        contentHolder.setBounds(0, 150, 1400, 650);

        header.setBackground(new java.awt.Color(0, 0, 0));
        header.setPreferredSize(new java.awt.Dimension(1400, 150));
        header.setLayout(null);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/logo_wo_name.png"))); // NOI18N
        header.add(logo);
        logo.setBounds(41, 30, 104, 100);

        dashboardLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        dashboardLabel.setForeground(new java.awt.Color(255, 255, 255));
        dashboardLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dashboardLabel.setText("Dashboard");
        dashboardLabel.setPreferredSize(new java.awt.Dimension(130, 30));
        dashboardLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dashboardLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dashboardLabelMouseExited(evt);
            }
        });
        header.add(dashboardLabel);
        dashboardLabel.setBounds(250, 50, 160, 40);

        vendorLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        vendorLabel.setForeground(new java.awt.Color(255, 255, 255));
        vendorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vendorLabel.setText("Vendor");
        vendorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendorLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vendorLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vendorLabelMouseExited(evt);
            }
        });
        header.add(vendorLabel);
        vendorLabel.setBounds(490, 50, 120, 40);

        runnerLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        runnerLabel.setForeground(new java.awt.Color(255, 255, 255));
        runnerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        runnerLabel.setText("Delivery Runner");
        runnerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                runnerLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                runnerLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                runnerLabelMouseExited(evt);
            }
        });
        header.add(runnerLabel);
        runnerLabel.setBounds(680, 50, 250, 40);

        feedbacksLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        feedbacksLabel.setForeground(new java.awt.Color(255, 255, 255));
        feedbacksLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        feedbacksLabel.setText("Feedbacks");
        feedbacksLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                feedbacksLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                feedbacksLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                feedbacksLabelMouseExited(evt);
            }
        });
        header.add(feedbacksLabel);
        feedbacksLabel.setBounds(980, 50, 170, 40);

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/logout_icon.png"))); // NOI18N
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
        header.add(logout);
        logout.setBounds(1261, 30, 74, 80);

        pageHolder.add(header);
        header.setBounds(0, 0, 1400, 150);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/manager_background.png"))); // NOI18N
        pageHolder.add(background);
        background.setBounds(0, 0, 1400, 800);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1412, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pageHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 812, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pageHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardLabelMouseClicked
        cardLayout.show(contentHolder,"managerDashboard");
        currentPage = "dashboard";
        labelFormat(dashboardLabel, vendorLabel, runnerLabel, feedbacksLabel);
    }//GEN-LAST:event_dashboardLabelMouseClicked

    private void dashboardLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardLabelMouseEntered
        dashboardLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_dashboardLabelMouseEntered

    private void dashboardLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardLabelMouseExited
        dashboardLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_dashboardLabelMouseExited

    private void vendorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendorLabelMouseClicked
        cardLayout.show(contentHolder,"checkVendor");
        currentPage = "vendor";
        labelFormat(dashboardLabel, vendorLabel, runnerLabel, feedbacksLabel);
    }//GEN-LAST:event_vendorLabelMouseClicked

    private void vendorLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendorLabelMouseEntered
        vendorLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_vendorLabelMouseEntered

    private void vendorLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendorLabelMouseExited
        vendorLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_vendorLabelMouseExited

    private void runnerLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_runnerLabelMouseClicked
        cardLayout.show(contentHolder, "checkRunner");
        currentPage = "runner";
        labelFormat(dashboardLabel, vendorLabel, runnerLabel, feedbacksLabel);
    }//GEN-LAST:event_runnerLabelMouseClicked

    private void runnerLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_runnerLabelMouseEntered
        runnerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_runnerLabelMouseEntered

    private void runnerLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_runnerLabelMouseExited
        runnerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_runnerLabelMouseExited

    private void feedbacksLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedbacksLabelMouseClicked
        cardLayout.show(contentHolder, "checkComplaints");
        currentPage = "complaints";
        labelFormat(dashboardLabel, vendorLabel, runnerLabel, feedbacksLabel);
    }//GEN-LAST:event_feedbacksLabelMouseClicked

    private void feedbacksLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedbacksLabelMouseEntered
        feedbacksLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_feedbacksLabelMouseEntered

    private void feedbacksLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedbacksLabelMouseExited
        feedbacksLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_feedbacksLabelMouseExited

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutMouseClicked

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_logoutMouseExited

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

    private void feedbackExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedbackExcelMouseClicked
        notificationPopUp.managerExportPopUp(this);
        this.setEnabled(false);
    }//GEN-LAST:event_feedbackExcelMouseClicked

    private void feedbackExcelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedbackExcelMouseEntered
        feedbackExcel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_feedbackExcelMouseEntered

    private void feedbackExcelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedbackExcelMouseExited
        feedbackExcel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_feedbackExcelMouseExited

    private void feedbackFilterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedbackFilterMouseClicked
        filter = new TimeFilter(this);
        this.setEnabled(false);
    }//GEN-LAST:event_feedbackFilterMouseClicked

    private void feedbackFilterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedbackFilterMouseEntered
        feedbackFilter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_feedbackFilterMouseEntered

    private void feedbackFilterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedbackFilterMouseExited
        feedbackFilter.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_feedbackFilterMouseExited


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel bottomBackground;
    private javax.swing.JPanel contentHolder;
    private javax.swing.JPanel dashboard;
    private javax.swing.JPanel dashboardContent;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JLabel feedbackExcel;
    private javax.swing.JLabel feedbackFilter;
    private javax.swing.JLabel feedbacks;
    private javax.swing.JPanel feedbacksChartHolder;
    private javax.swing.JLabel feedbacksLabel;
    private javax.swing.JPanel header;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel logout;
    private javax.swing.JPanel pageHolder;
    private javax.swing.JLabel revenue;
    private javax.swing.JPanel revenueChartHolder;
    private javax.swing.JLabel revenueExcel;
    private javax.swing.JLabel revenueFilter;
    private javax.swing.JLabel runnerLabel;
    private javax.swing.JLabel vendorLabel;
    // End of variables declaration//GEN-END:variables
}
