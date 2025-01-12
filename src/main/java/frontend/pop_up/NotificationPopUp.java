/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.pop_up;

import backend.entity.Customer;
import backend.entity.DeliveryRunner;
import backend.entity.Stall;
import backend.entity.User;
import backend.entity.Vendor;
import backend.notification.CustomerNotification;
import backend.notification.DeliveryRunnerNotification;
import backend.notification.Notification;
import backend.notification.NotificationStatus;
import backend.notification.VendorNotification;
import frontend.home_page.LoginPage;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author limbengrhui
 */
public class NotificationPopUp extends javax.swing.JFrame {

    private static ArrayList<? extends Notification> notificationList;
    
    /**
     * Creates new form NotificationPopUp
     * 
     * @param user The user object that is involved in the notification
     */
    public NotificationPopUp(User user) {
        
        // Retrieve the list of notifications from different users
        switch (user) {
            
            // When vendor, delivery runner and customer are passed into constructor
            case Vendor vendor -> notificationList = VendorNotification.getNotificationList(vendor);
            case DeliveryRunner runner -> notificationList = DeliveryRunnerNotification.getNotificationList(runner);
            case Customer customer -> notificationList = CustomerNotification.getNotificationList(customer);
            
            // If other user types are passed into the constructor
            default -> throw new IllegalArgumentException(
                    "Invalid user type passed into method. User type should be either Vendor, DeliveryRunner or Customer."
            );
        }
        
        // Render GUI
        initComponents();

        // Declare variables to store vertical gap
        int verticalGap = 20;
        
        // Initialize the list of JPanels (used to calculate size later)
        ArrayList<JPanel> jPanelList = new ArrayList<>();
        
        // If there is no notifications
        if (notificationList.isEmpty()) {
            
            // Create the no notification label
            JLabel noNotificationText = new JLabel();
            noNotificationText.setText("No notifications available for now.");
            noNotificationText.setFont(new Font("Arial", Font.PLAIN, 24));
            noNotificationText.setBounds(0, 0, contentPanel.getWidth(), 50);
            noNotificationText.setHorizontalAlignment(SwingConstants.LEADING);
            noNotificationText.setVerticalAlignment(SwingConstants.TOP);
            
            // Add label to panel
            contentPanel.add(noNotificationText);
            
        } else {
            
            // Loop through each notification
            for (Notification notification : notificationList) {

                // Create a JPanel for the notification
                JPanel notificationPanel = createPanel(notification);

                // Add the panel to the JPanel list
                jPanelList.add(notificationPanel);

                // Add the JPanel to the content panel
                contentPanel.add(notificationPanel);

                // Check if the current notification is the last notification
                if (!notification.equals(notificationList.getLast())) {

                    // Create a gap if its not the last notification
                    contentPanel.add(Box.createVerticalStrut(verticalGap));
                }

                // Set notification status to read after viewing
                notification.markAsRead();
            }

            // Calculate the height of the container
            int calculatedContainerHeight = verticalGap * (jPanelList.size() - 1);
            for (JPanel panel : jPanelList) calculatedContainerHeight += panel.getHeight();

            // If the container height exceeds the current content panel height
            if (calculatedContainerHeight > contentPanel.getHeight()) {

                // Set the preferred size, minimum size and the maximum size
                contentPanel.setPreferredSize(new Dimension(contentPanel.getWidth(), calculatedContainerHeight));
                contentPanel.setMinimumSize(new Dimension(contentPanel.getWidth(), calculatedContainerHeight));
                contentPanel.setMaximumSize(new Dimension(contentPanel.getWidth(), calculatedContainerHeight));
            }
        }
    }

    /**
     * This method helps to generate panels for notifications.
     * @param notification The notification object to be involved
     * @return A JPanel containing information for the notifications
     */
    public static JPanel createPanel(Notification notification) {
        
        // Get an estimation for the number of lines that the description will span
        int lineCount = notification.getNotificationDetails().length() / 90 + 1;
        
        // Get the size of the panel
        int panelWidth = contentPanel.getWidth();
        int panelHeight = 80 + 25 * lineCount;
        Dimension panelDimension = new Dimension(panelWidth, panelHeight);

        // Get the size of the title and description
        int contentWidth = panelWidth - 60;
        int titleHeight = 25;
        int descriptionHeight = lineCount * 23;
        
        // Get the position for the "New" word
        int newWordWidth = 50;
        int newWordX = panelWidth - 30 - newWordWidth;
        
        // Get the font for title and description
        Font titleFont = new Font("Arial", Font.BOLD, 18);
        Font descriptionFont = new Font("Arial", Font.PLAIN, 18);
                
        // Generate the panel
        JPanel notificationPanel = new JPanel();
        notificationPanel.setLayout(null);
        notificationPanel.setMaximumSize(panelDimension);
        notificationPanel.setPreferredSize(panelDimension);
        notificationPanel.setMinimumSize(panelDimension);
        notificationPanel.setSize(panelDimension);
        notificationPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        notificationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        notificationPanel.setBackground(Color.WHITE);
        notificationPanel.setOpaque(true);

        // Generate the title
        JLabel notificationTitle = new JLabel();
        notificationTitle.setText(notification.getNotificationTitle());
        notificationTitle.setFont(titleFont);
        notificationTitle.setBounds(30, 25, contentWidth, titleHeight);
        notificationTitle.setHorizontalAlignment(SwingConstants.LEADING);
        notificationTitle.setVerticalAlignment(SwingConstants.CENTER);
        
        // Generate the description
        JLabel notificationText = new JLabel();
        notificationText.setText("<html><div style='text-align: justify;'>" + notification.getNotificationDetails() + "</div></html>");
        notificationText.setFont(descriptionFont);
        notificationText.setBounds(30, 55, contentWidth, descriptionHeight);
        notificationText.setHorizontalAlignment(SwingConstants.LEADING);
        notificationText.setVerticalAlignment(SwingConstants.TOP);

        // If the notification is unread
        if (notification.getReadStatus() == NotificationStatus.UNREAD) {
            
            // Create a JLabel to indicate that the notification is a new notification
            JLabel newNotificationText = new JLabel();
            newNotificationText.setText("NEW!");
            newNotificationText.setFont(titleFont);
            newNotificationText.setForeground(Color.RED);
            newNotificationText.setBounds(newWordX, 25, newWordWidth, titleHeight);
            newNotificationText.setHorizontalAlignment(SwingConstants.RIGHT);
            newNotificationText.setVerticalAlignment(SwingConstants.CENTER);
            
            // Add to panel
            notificationPanel.add(newNotificationText);
            
            // Change the colour of the panel to light brown
            notificationPanel.setBackground(new Color(227, 202, 165));
        }
        
        // Add to panel
        notificationPanel.add(notificationTitle);
        notificationPanel.add(notificationText);

        // Return the JPanel after creation
        return notificationPanel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        titleText = new javax.swing.JLabel();
        contentScrollPane = new javax.swing.JScrollPane() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        contentPanel = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Notification");
        setAlwaysOnTop(true);
        setName("notificationFrame"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backgroundPanel.setBackground(new java.awt.Color(255, 251, 233));
        backgroundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/cancel_icon.png"))); // NOI18N
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButtonMouseExited(evt);
            }
        });
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        backgroundPanel.add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 50, 40, 50));

        titleText.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        titleText.setText("Notification List");
        backgroundPanel.add(titleText, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 280, 50));

        contentScrollPane.setBackground(new Color(0, 0, 0, 0));
        contentScrollPane.setBorder(null);
        contentScrollPane.setViewportBorder(null);
        contentScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contentScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        contentScrollPane.setOpaque(false);
        contentScrollPane.getViewport().setOpaque(false);
        contentScrollPane.setPreferredSize(new java.awt.Dimension(880, 420));

        contentPanel.setBackground(new Color(0, 0, 0, 0));
        contentPanel.setMinimumSize(new java.awt.Dimension(0, 0));
        contentPanel.setOpaque(false);
        contentPanel.setPreferredSize(new java.awt.Dimension(800, 420));
        contentPanel.setLayout(new javax.swing.BoxLayout(contentPanel, javax.swing.BoxLayout.Y_AXIS));
        contentScrollPane.setViewportView(contentPanel);

        backgroundPanel.add(contentScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 880, 420));

        getContentPane().add(backgroundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseEntered

        // Set the cursor
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_closeButtonMouseEntered

    private void closeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseExited

        // Set the cursor
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_closeButtonMouseExited

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed

        // Dispose the current pop up
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NotificationPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotificationPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotificationPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotificationPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Vendor currentVendor = new Vendor("V001", "vendor@mail.com", "Mno@3456", "Muhammad Abdul Ali bin Ahmad Ghazali", new Stall("S001", "Big Fish and Chips Western", new Stall.StallCategories[]{Stall.StallCategories.LOCAL, Stall.StallCategories.WESTERN, Stall.StallCategories.HALAL}));
                new NotificationPopUp(currentVendor).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton closeButton;
    private static javax.swing.JPanel contentPanel;
    private javax.swing.JScrollPane contentScrollPane;
    private javax.swing.JLabel titleText;
    // End of variables declaration//GEN-END:variables
}
