/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.manager;

import backend.entity.Feedback;
import frontend.pop_up.FeedbackFilterPopUp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Chun Ming (TP068983), Beng Rhui (TP068495)
 */
public class FeedbackPanel extends javax.swing.JPanel {

    private static Feedback.Filter systemFilterInUse;
    private static Feedback.Filter vendorFilterInUse;
    private static Feedback.Filter runnerFilterInUse;
    
    /**
     * Creates new form FeedbackPanel
     */
    public FeedbackPanel() {
        
        // Render GUI
        initComponents();
        
        // Initialize the filter used
        systemFilterInUse = Feedback.Filter.LATEST_TO_OLDEST;
        vendorFilterInUse = Feedback.Filter.LATEST_TO_OLDEST;
        runnerFilterInUse = Feedback.Filter.LATEST_TO_OLDEST;
        
        // Initialize panel
        updateSystemPanel();
        updateVendorPanel();
        updateRunnerPanel();
    }
    
    public static void updateSystemPanel() {
    
        // Remove everything from the panel
        systemPanel.removeAll();
        
        // Retrieve the list of feedback related to system
        ArrayList<Feedback> systemFeedback = Feedback.arrangeFeedbackList(Feedback.Category.SYSTEM, systemFilterInUse);
        systemFeedback = systemFeedback.stream()
                .filter(feedback -> feedback.getReplyFromManager() == null)
                .collect(Collectors.toCollection(ArrayList::new));
        
        // If there is no system feedback
        if (systemFeedback.isEmpty()) {
            
            // Generate an empty label and panel to accommodate the message
            JPanel emptyPanel = new JPanel(null);
            JLabel emptyLabel = new JLabel("No feedback available to reply.");
            
            // Customize the label and panel
            emptyPanel.setBackground(new Color(255, 251, 233));
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            emptyLabel.setBounds(0, 0, 400, 30);
            
            // Add label to panel, and add panel to container
            emptyPanel.add(emptyLabel);
            systemPanel.add(emptyPanel);
            
        } else {
            
            // Loop through each incomplete feedback
            for (Feedback feedback : systemFeedback) {
                FeedbackDetailsPanel feedbackPanel = new FeedbackDetailsPanel(feedback);
                feedbackPanel.setPreferredSize(new Dimension(400, 150));
                systemPanel.add(feedbackPanel);
            }
            
            // If the feedback size is less than 3
            if (systemFeedback.size() < 3) {
                
                // Lopo through the number of empty boxes need to be added
                for (int i = systemFeedback.size() + 1 ; i <= 3; i++) {
                    
                    // Create an empty panel and set background
                    JPanel emptyPanel = new JPanel();
                    emptyPanel.setBackground(new Color(255, 251, 233));
                    
                    // Add the panel to the container
                    systemPanel.add(emptyPanel);
                }
            }
        }
        
        // Refresh panel
        systemPanel.revalidate();
        systemPanel.repaint();
    }
    
    public static void updateVendorPanel() {
    
        // Remove everything from the panel
        vendorPanel.removeAll();
        
        // Retrieve the list of feedback related to vendor
        ArrayList<Feedback> vendorFeedback = Feedback.arrangeFeedbackList(Feedback.Category.VENDOR, vendorFilterInUse);
        vendorFeedback = vendorFeedback.stream()
                .filter(feedback -> feedback.getReplyFromManager() == null)
                .collect(Collectors.toCollection(ArrayList::new));
        
        // If there is no vendor feedback
        if (vendorFeedback.isEmpty()) {
            
            // Generate an empty label and panel to accommodate the message
            JPanel emptyPanel = new JPanel(null);
            JLabel emptyLabel = new JLabel("No feedback available to reply.");
            
            // Customize the label and panel
            emptyPanel.setBackground(new Color(255, 251, 233));
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            emptyLabel.setBounds(0, 0, 400, 30);
            
            // Add label to panel, and add panel to container
            emptyPanel.add(emptyLabel);
            vendorPanel.add(emptyPanel);
            
        } else {
            
            // Loop through each incomplete feedback
            for (Feedback feedback : vendorFeedback) {
                FeedbackDetailsPanel feedbackPanel = new FeedbackDetailsPanel(feedback);
                feedbackPanel.setPreferredSize(new Dimension(400, 150));
                vendorPanel.add(feedbackPanel);
            }
            
            // If the feedback size is less than 3
            if (vendorFeedback.size() < 3) {
                
                // Lopo through the number of empty boxes need to be added
                for (int i = vendorFeedback.size() + 1 ; i <= 3; i++) {
                    
                    // Create an empty panel and set background
                    JPanel emptyPanel = new JPanel();
                    emptyPanel.setBackground(new Color(255, 251, 233));
                    
                    // Add the panel to the container
                    vendorPanel.add(emptyPanel);
                }
            }
        }
        
        // Refresh panel
        vendorPanel.revalidate();
        vendorPanel.repaint();
    }
    
    
    public static void updateRunnerPanel() {

        // Remove everything from the panel
        runnerPanel.removeAll();
        
        // Retrieve the list of feedback related to vendor
        ArrayList<Feedback> runnerFeedback = Feedback.arrangeFeedbackList(Feedback.Category.DELIVERY_RUNNER, runnerFilterInUse);
        runnerFeedback = runnerFeedback.stream()
                .filter(feedback -> feedback.getReplyFromManager() == null)
                .collect(Collectors.toCollection(ArrayList::new));
        
        // If there is no runner feedback
        if (runnerFeedback.isEmpty()) {
            
            // Generate an empty label and panel to accommodate the message
            JPanel emptyPanel = new JPanel(null);
            JLabel emptyLabel = new JLabel("No feedback available to reply.");
            
            // Customize the label and panel
            emptyPanel.setBackground(new Color(255, 251, 233));
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            emptyLabel.setBounds(0, 0, 400, 30);
            
            // Add label to panel, and add panel to container
            emptyPanel.add(emptyLabel);
            runnerPanel.add(emptyPanel);
            
        } else {
            
            // Loop through each incomplete feedback
            for (Feedback feedback : runnerFeedback) {
                FeedbackDetailsPanel feedbackPanel = new FeedbackDetailsPanel(feedback);
                feedbackPanel.setPreferredSize(new Dimension(400, 150));
                runnerPanel.add(feedbackPanel);
            }
            
            // If the feedback size is less than 3
            if (runnerFeedback.size() < 3) {
                
                // Lopo through the number of empty boxes need to be added
                for (int i = runnerFeedback.size() + 1 ; i <= 3; i++) {
                    
                    // Create an empty panel and set background
                    JPanel emptyPanel = new JPanel();
                    emptyPanel.setBackground(new Color(255, 251, 233));
                    
                    // Add the panel to the container
                    runnerPanel.add(emptyPanel);
                }
            }
        }
        
        // Refresh panel
        runnerPanel.revalidate();
        runnerPanel.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        systemTitle = new javax.swing.JLabel();
        systemFilter = new javax.swing.JLabel();
        vendorTitle = new javax.swing.JLabel();
        vendorFilter = new javax.swing.JLabel();
        runnerTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        systemScrollPane = new javax.swing.JScrollPane();
        systemPanel = new javax.swing.JPanel();
        vendorScrollPane = new javax.swing.JScrollPane();
        vendorPanel = new javax.swing.JPanel();
        runnerScrollPane = new javax.swing.JScrollPane();
        runnerPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 251, 233));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        systemTitle.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        systemTitle.setText("System");
        add(systemTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 50, 130, -1));

        systemFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/customize_icon.png"))); // NOI18N
        systemFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        systemFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                systemFilterMouseClicked(evt);
            }
        });
        add(systemFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 70, 70));

        vendorTitle.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        vendorTitle.setText("Vendor");
        add(vendorTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 140, -1));

        vendorFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/customize_icon.png"))); // NOI18N
        vendorFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vendorFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendorFilterMouseClicked(evt);
            }
        });
        add(vendorFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 70, 70));

        runnerTitle.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        runnerTitle.setText("Runner");
        add(runnerTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 50, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/customize_icon.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 40, 70, 70));

        systemScrollPane.setBorder(null);
        systemScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        systemScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        systemPanel.setBackground(new java.awt.Color(255, 251, 233));
        systemPanel.setLayout(new java.awt.GridLayout(0, 1, 10, 10));
        systemScrollPane.setViewportView(systemPanel);

        add(systemScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 410, 470));

        vendorScrollPane.setBorder(null);
        vendorScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        vendorScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        vendorPanel.setBackground(new java.awt.Color(255, 251, 233));
        vendorPanel.setLayout(new java.awt.GridLayout(0, 1, 10, 10));
        vendorScrollPane.setViewportView(vendorPanel);

        add(vendorScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 410, 470));

        runnerScrollPane.setBorder(null);
        runnerScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        runnerScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        runnerPanel.setBackground(new java.awt.Color(255, 251, 233));
        runnerPanel.setLayout(new java.awt.GridLayout(0, 1, 10, 10));
        runnerScrollPane.setViewportView(runnerPanel);

        add(runnerScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 120, 410, 470));
    }// </editor-fold>//GEN-END:initComponents

    private void systemFilterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_systemFilterMouseClicked

        // Call the filter pop up
        FeedbackFilterPopUp filterPopUp = new FeedbackFilterPopUp(MainPage.currentFrame, systemFilterInUse);
        filterPopUp.setVisible(true);
        
        // Update the filter
        systemFilterInUse = filterPopUp.getFilter();
        
        // Update the panel
        updateSystemPanel();
    }//GEN-LAST:event_systemFilterMouseClicked

    private void vendorFilterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendorFilterMouseClicked

        // Call the filter pop up
        FeedbackFilterPopUp filterPopUp = new FeedbackFilterPopUp(MainPage.currentFrame, vendorFilterInUse);
        filterPopUp.setVisible(true);
        
        // Update the filter
        vendorFilterInUse = filterPopUp.getFilter();
        
        // Update the panel
        updateVendorPanel();
    }//GEN-LAST:event_vendorFilterMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        // Call the filter pop up
        FeedbackFilterPopUp filterPopUp = new FeedbackFilterPopUp(MainPage.currentFrame, runnerFilterInUse);
        filterPopUp.setVisible(true);
        
        // Update the filter
        runnerFilterInUse = filterPopUp.getFilter();
        
        // Update the panel
        updateRunnerPanel();
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private static javax.swing.JPanel runnerPanel;
    private javax.swing.JScrollPane runnerScrollPane;
    private javax.swing.JLabel runnerTitle;
    private javax.swing.JLabel systemFilter;
    private static javax.swing.JPanel systemPanel;
    private javax.swing.JScrollPane systemScrollPane;
    private javax.swing.JLabel systemTitle;
    private javax.swing.JLabel vendorFilter;
    private static javax.swing.JPanel vendorPanel;
    private javax.swing.JScrollPane vendorScrollPane;
    private javax.swing.JLabel vendorTitle;
    // End of variables declaration//GEN-END:variables
}
