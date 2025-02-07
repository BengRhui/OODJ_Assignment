/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.pop_up;

import backend.entity.Customer;
import backend.entity.DeliveryRunner;
import backend.entity.Feedback;
import backend.entity.Stall;
import backend.entity.Vendor;
import frontend.utility.RatingStarPanel;
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
 * @author Beng Rhui (TP068495)
 */
public class FeedbackPopUp extends javax.swing.JFrame {

    private static ArrayList<Feedback> feedbackList;
    
    /**
     * Creates new form FeedbackPopUp
     * 
     * @param object The object that is involved in the feedback
     */
    public FeedbackPopUp(Object object) {
        
        // Retrieve the list of feedback from different users
        switch (object) {
            
            // When vendor, delivery runner and customer are passed into constructor
            case Vendor vendor -> feedbackList = Feedback.getFeedbackList(vendor.getStall());
            case DeliveryRunner runner -> feedbackList = Feedback.getFeedbackList(runner);
            case Customer customer -> feedbackList = Feedback.getFeedbackList(customer);
            
            // When stall is passed
            case Stall stall -> feedbackList = Feedback.getFeedbackList(stall);
            
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
        
        // If there is no feedback
        if (feedbackList.isEmpty()) {
            
            // Create the no feedback label
            JLabel noFeedbackText = new JLabel();
            noFeedbackText.setText("No feedback available for now.");
            noFeedbackText.setFont(new Font("Arial", Font.PLAIN, 24));
            noFeedbackText.setBounds(0, 0, contentPanel.getWidth(), 50);
            noFeedbackText.setHorizontalAlignment(SwingConstants.LEADING);
            noFeedbackText.setVerticalAlignment(SwingConstants.TOP);
            
            // Add label to panel
            contentPanel.add(noFeedbackText);
            
        } else {
            
            // Loop through each feedback
            for (Feedback feedback : feedbackList) {

                // Create a JPanel for the feedback
                JPanel feedbackPanel = createPanel(feedback);

                // Add the panel to the JPanel list
                jPanelList.add(feedbackPanel);

                // Add the JPanel to the content panel
                contentPanel.add(feedbackPanel);

                // Check if the current feedback is the last feedback
                if (!feedback.equals(feedbackList.getLast())) {

                    // Create a gap if its not the last feedback
                    contentPanel.add(Box.createVerticalStrut(verticalGap));
                }
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
     * This method helps to generate panels for feedback.
     * @param feedback The feedback object to be involved
     * @return A JPanel containing information for feedback
     */
    public static JPanel createPanel(Feedback feedback) {
        
        // Get the width for everything
        int panelWidth = contentPanel.getWidth();
        int starPanelWidth = 200;
        int titleWidth = panelWidth - starPanelWidth - 60;
        int descriptionWidth = panelWidth - 60;
        
        // Get the font for title and description
        Font titleFont = new Font("Arial", Font.BOLD, 20);
        Font descriptionFont = new Font("Arial", Font.PLAIN, 18);
        
        // Generate the description (used to calculate the height for the label later)
        JLabel feedbackDescription = new JLabel();
        feedbackDescription.setText("<html><div style='text-align: justify;'>" + feedback.getFeedbackDetails()+ "</div></html>");
        feedbackDescription.setFont(descriptionFont);
        
        // Get the initial width of the description and calculate the number of line it spans
        double initialDescriptionWidth = feedbackDescription.getPreferredSize().width;
        int initialDescriptionHeight = feedbackDescription.getPreferredSize().height;
        int lineCount = (int) Math.ceil(initialDescriptionWidth / descriptionWidth);

        // Calculate the height for everything
        int panelHeight = 100 + initialDescriptionHeight * lineCount;
        int starPanelHeight = 30;
        int titleHeight = 30;
        int descriptionHeight = lineCount * initialDescriptionHeight;
        
        // Get the position for the elements
        int titleX = 30;
        int titleY = 25;
        int descriptionX = 30;
        int descriptionY = 70;
        int starPanelX = titleX + titleWidth;
        int starPanelY = 25;
        
        // Set the dimension of the panel
        Dimension panelDimension = new Dimension(panelWidth, panelHeight);
                
        // Generate the panel
        JPanel feedbackPanel = new JPanel();
        feedbackPanel.setLayout(null);
        feedbackPanel.setMaximumSize(panelDimension);
        feedbackPanel.setPreferredSize(panelDimension);
        feedbackPanel.setMinimumSize(panelDimension);
        feedbackPanel.setSize(panelDimension);
        feedbackPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        feedbackPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        feedbackPanel.setBackground(Color.WHITE);
        feedbackPanel.setOpaque(true);

        // Generate the title
        JLabel feedbackTitle = new JLabel();
        feedbackTitle.setText(feedback.getFeedbackTitle());
        feedbackTitle.setFont(titleFont);
        feedbackTitle.setBounds(titleX, titleY, titleWidth, titleHeight);
        feedbackTitle.setHorizontalAlignment(SwingConstants.LEADING);
        feedbackTitle.setVerticalAlignment(SwingConstants.CENTER);
        
        // Continue generate the description
        feedbackDescription.setBounds(descriptionX, descriptionY, descriptionWidth, descriptionHeight);
        feedbackDescription.setHorizontalAlignment(SwingConstants.LEADING);
        feedbackDescription.setVerticalAlignment(SwingConstants.TOP);
        
        // Generate the star panel
        RatingStarPanel starPanel = new RatingStarPanel(feedback.getRatings());
        starPanel.setBounds(starPanelX, starPanelY, starPanelWidth, starPanelHeight);

        // If the feedback is for delivery runner
        if (feedback.getFeedbackCategory() == Feedback.Category.DELIVERY_RUNNER) {
            
            // Get the tips
            String tipAmount = feedback.getOrderAssociated().getTipsForRunner() == null ? "-" : String.format("%.2f", feedback.getOrderAssociated().getTipsForRunner());
            
            // Get the new position of the description and tips label
            int tipsX = descriptionX;
            int tipsY = descriptionY - 5;
            int tipsWidth = descriptionWidth;
            int tipsHeight = 25;
            descriptionY += tipsHeight + 10;
            
            // Adjust the size of the current panel
            panelHeight += tipsHeight + 10;
            panelDimension = new Dimension(panelWidth, panelHeight);
            feedbackPanel.setMaximumSize(panelDimension);
            feedbackPanel.setPreferredSize(panelDimension);
            feedbackPanel.setMinimumSize(panelDimension);
            feedbackPanel.setSize(panelDimension);
            
            // Adjust the position of the description
            feedbackDescription.setBounds(descriptionX, descriptionY, descriptionWidth, descriptionHeight);
            
            // Adjust the position of star panel (to make it less awkward)
            starPanel.setBounds(starPanelX, starPanelY + 10, starPanelWidth, starPanelHeight);
            
            // Create a JLabel for tips
            JLabel tipsText = new JLabel();
            tipsText.setText("Tips Received: RM" + tipAmount);
            tipsText.setFont(new Font("Arial", Font.BOLD, 18));
            tipsText.setBounds(tipsX, tipsY, tipsWidth, tipsHeight);
            tipsText.setHorizontalAlignment(SwingConstants.LEFT);
            tipsText.setVerticalAlignment(SwingConstants.CENTER);
            
            // Add to panel
            feedbackPanel.add(tipsText);
        }
        
        // Add to panel
        feedbackPanel.add(feedbackTitle);
        feedbackPanel.add(feedbackDescription);
        feedbackPanel.add(starPanel);

        // Return the JPanel after creation
        return feedbackPanel;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Feedback");
        setAlwaysOnTop(true);
        setName("feedbackFrame"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backgroundPanel.setBackground(new java.awt.Color(255, 251, 233));
        backgroundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/cancel_icon.png"))); // NOI18N
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
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
        titleText.setText("Customer Feedback");
        backgroundPanel.add(titleText, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 360, 50));

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
            java.util.logging.Logger.getLogger(FeedbackPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FeedbackPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FeedbackPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FeedbackPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Vendor currentVendor = new Vendor("V001", "vendor@mail.com", "Mno@3456", "Muhammad Abdul Ali bin Ahmad Ghazali", new Stall("S001", "Big Fish and Chips Western", new Stall.StallCategories[]{Stall.StallCategories.LOCAL, Stall.StallCategories.WESTERN, Stall.StallCategories.HALAL}));
                new FeedbackPopUp(currentVendor).setVisible(true);
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
