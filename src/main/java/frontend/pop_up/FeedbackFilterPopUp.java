/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package frontend.pop_up;

import backend.entity.Feedback;
import static backend.entity.Feedback.Filter.HIGH_TO_LOW_RATING;
import static backend.entity.Feedback.Filter.LATEST_TO_OLDEST;
import static backend.entity.Feedback.Filter.LOW_TO_HIGH_RATING;
import static backend.entity.Feedback.Filter.OLDEST_TO_LATEST;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Beng Rhui (TP068495)
 */
public class FeedbackFilterPopUp extends javax.swing.JDialog {

    private static JFrame parentFrame;
    private static Feedback.Filter currentFilter;
    
    /**
     * Creates new form TimeFramePopUp
     */
    public FeedbackFilterPopUp(JFrame parent, Feedback.Filter filter) {
        
        // Run constructor and render GUI
        super(parent, true);
        initComponents();
        
        // Set current filter and parent frame
        parentFrame = parent;
        currentFilter = filter;
        
        // Set position
        setLocationRelativeTo(parent);
        
        // Select button based on current filter
        initializeButton();
    }
    
    /**
     * This method helps to retrieve the filter from the dialog.
     * @return The time frame from the dialog
     */
    public Feedback.Filter getFilter() {
        return currentFilter;
    }
    
    /**
     * This method helps to initialize the button based on the selection from the parent frame.
     */
    private void initializeButton() {
        
        // Select buttons based on different filters
        switch (currentFilter) {
            case LOW_TO_HIGH_RATING -> lowestRatingToHighestButton.setSelected(true);
            case HIGH_TO_LOW_RATING -> highestRatingToLowestButton.setSelected(true);
            case LATEST_TO_OLDEST -> latestToOldestButton.setSelected(true);
            case OLDEST_TO_LATEST -> oldestToLatestButton.setSelected(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        backgroundPanel = new javax.swing.JPanel();
        dialogTitle = new javax.swing.JLabel();
        promptText = new javax.swing.JLabel();
        latestToOldestButton = new javax.swing.JRadioButton();
        oldestToLatestButton = new javax.swing.JRadioButton();
        highestRatingToLowestButton = new javax.swing.JRadioButton();
        lowestRatingToHighestButton = new javax.swing.JRadioButton();
        cancelButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Timeframe Selection");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backgroundPanel.setBackground(new java.awt.Color(255, 251, 233));
        backgroundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dialogTitle.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        dialogTitle.setText("Select Time Frame");
        backgroundPanel.add(dialogTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        promptText.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        promptText.setText("Select one of the following options:");
        backgroundPanel.add(promptText, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        latestToOldestButton.setBackground(new java.awt.Color(255, 251, 233));
        buttonGroup.add(latestToOldestButton);
        latestToOldestButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        latestToOldestButton.setText(" Latest to Oldest");
        latestToOldestButton.setFocusPainted(false);
        backgroundPanel.add(latestToOldestButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        oldestToLatestButton.setBackground(new java.awt.Color(255, 251, 233));
        buttonGroup.add(oldestToLatestButton);
        oldestToLatestButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        oldestToLatestButton.setText(" Oldest to Latest");
        oldestToLatestButton.setFocusPainted(false);
        backgroundPanel.add(oldestToLatestButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, -1, -1));

        highestRatingToLowestButton.setBackground(new java.awt.Color(255, 251, 233));
        buttonGroup.add(highestRatingToLowestButton);
        highestRatingToLowestButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        highestRatingToLowestButton.setText(" Highest Rating to Lowest");
        highestRatingToLowestButton.setFocusPainted(false);
        backgroundPanel.add(highestRatingToLowestButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));

        lowestRatingToHighestButton.setBackground(new java.awt.Color(255, 251, 233));
        buttonGroup.add(lowestRatingToHighestButton);
        lowestRatingToHighestButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lowestRatingToHighestButton.setText(" Lowest Rating to Highest");
        lowestRatingToHighestButton.setFocusPainted(false);
        backgroundPanel.add(lowestRatingToHighestButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        cancelButton.setBackground(Color.WHITE);
        cancelButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelButton.setFocusPainted(false);
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelButtonMouseExited(evt);
            }
        });
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        backgroundPanel.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 150, 50));

        confirmButton.setBackground(new java.awt.Color(0, 0, 0));
        confirmButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        confirmButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmButton.setText("OK");
        confirmButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        confirmButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        confirmButton.setFocusPainted(false);
        confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirmButtonMouseExited(evt);
            }
        });
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });
        backgroundPanel.add(confirmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 150, 50));

        getContentPane().add(backgroundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed

        // Enable the parent frame
        parentFrame.setEnabled(true);
        
        // Dispose the pop up
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed

        // Set the filter based on selection
        if (latestToOldestButton.isSelected()) currentFilter = Feedback.Filter.LATEST_TO_OLDEST;
        else if (oldestToLatestButton.isSelected()) currentFilter = Feedback.Filter.OLDEST_TO_LATEST;
        else if (highestRatingToLowestButton.isSelected()) currentFilter = Feedback.Filter.HIGH_TO_LOW_RATING;
        else if (lowestRatingToHighestButton.isSelected()) currentFilter = Feedback.Filter.LOW_TO_HIGH_RATING;

        // Enable the parent frame
        parentFrame.setEnabled(true);
        
        // Dispose the current pop up
        dispose();
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered

        // Change the colour of the button
        cancelButton.setBackground(new Color(206, 171, 147));
    }//GEN-LAST:event_cancelButtonMouseEntered

    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited

        // Change the colour of the button
        cancelButton.setBackground(Color.WHITE);
    }//GEN-LAST:event_cancelButtonMouseExited

    private void confirmButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseEntered

        // Change the colour of the button
        confirmButton.setBackground(new Color(206, 171, 147));
        
        // Change the text colour
        confirmButton.setForeground(Color.BLACK);
    }//GEN-LAST:event_confirmButtonMouseEntered

    private void confirmButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseExited

        // Change the colour of the button
        confirmButton.setBackground(Color.BLACK);
        
        // Change the text colour
        confirmButton.setForeground(Color.WHITE);
    }//GEN-LAST:event_confirmButtonMouseExited

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
            java.util.logging.Logger.getLogger(FeedbackFilterPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FeedbackFilterPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FeedbackFilterPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FeedbackFilterPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FeedbackFilterPopUp dialog = new FeedbackFilterPopUp(new javax.swing.JFrame(), Feedback.Filter.LATEST_TO_OLDEST);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel dialogTitle;
    private javax.swing.JRadioButton highestRatingToLowestButton;
    private javax.swing.JRadioButton latestToOldestButton;
    private javax.swing.JRadioButton lowestRatingToHighestButton;
    private javax.swing.JRadioButton oldestToLatestButton;
    private javax.swing.JLabel promptText;
    // End of variables declaration//GEN-END:variables
}
