/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.manager;

import backend.entity.Feedback;
import frontend.pop_up.SystemPopUp;
import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 *
 * @author Chun Ming (TP068983), Beng Rhui (TP068495)
 */
public class FeedbackReplyForm extends javax.swing.JFrame {

    private static Feedback currentFeedback;
    
    /**
     * Creates new form FeedbackReplyForm
     */
    public FeedbackReplyForm(Feedback feedback) {
        
        // Set current feedback
        currentFeedback = feedback;
        
        // Render GUI components
        initComponents();
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
        title = new javax.swing.JLabel();
        categoryLabel = new javax.swing.JLabel();
        categoryField = new javax.swing.JTextField();
        ratingsIDTitle = new javax.swing.JLabel();
        ratingsIDField = new javax.swing.JTextField();
        feedbackTitleLabel = new javax.swing.JLabel();
        feedbackTitleScrollPane = new javax.swing.JScrollPane();
        feedbackTitleField = new javax.swing.JTextArea();
        descriptionLabel = new javax.swing.JLabel();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionField = new javax.swing.JTextArea();
        responseLabel = new javax.swing.JLabel();
        responseScrollPane = new javax.swing.JScrollPane();
        responseField = new javax.swing.JTextArea();
        cancelButton = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Feedback Details");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backgroundPanel.setBackground(new java.awt.Color(255, 251, 233));
        backgroundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        title.setText("Feedback Details");
        backgroundPanel.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        categoryLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        categoryLabel.setText("Category");
        backgroundPanel.add(categoryLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        categoryField.setEditable(false);
        categoryField.setBackground(new java.awt.Color(255, 255, 255));
        categoryField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        categoryField.setText(currentFeedback.getFeedbackCategory().toString());
        categoryField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), javax.swing.BorderFactory.createEmptyBorder(2, 15, 2, 15)));
        categoryField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        categoryField.setEnabled(false);
        backgroundPanel.add(categoryField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 210, 50));

        ratingsIDTitle.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        ratingsIDTitle.setText("Ratings");
        backgroundPanel.add(ratingsIDTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, -1));

        ratingsIDField.setEditable(false);
        ratingsIDField.setBackground(new java.awt.Color(255, 255, 255));
        ratingsIDField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        ratingsIDField.setText(String.valueOf(currentFeedback.getRatings()));
        ratingsIDField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), javax.swing.BorderFactory.createEmptyBorder(2, 15, 2, 15)));
        ratingsIDField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ratingsIDField.setEnabled(false);
        backgroundPanel.add(ratingsIDField, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 210, 50));

        feedbackTitleLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        feedbackTitleLabel.setText("Feedback Title");
        backgroundPanel.add(feedbackTitleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        feedbackTitleScrollPane.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        feedbackTitleField.setEditable(false);
        feedbackTitleField.setBackground(new java.awt.Color(255, 255, 255));
        feedbackTitleField.setColumns(20);
        feedbackTitleField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        feedbackTitleField.setRows(1);
        feedbackTitleField.setText(currentFeedback.getFeedbackTitle());
        feedbackTitleField.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 15, 12, 15));
        feedbackTitleField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        feedbackTitleField.setEnabled(false);
        feedbackTitleScrollPane.setViewportView(feedbackTitleField);

        backgroundPanel.add(feedbackTitleScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 440, 50));

        descriptionLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        descriptionLabel.setText("Description");
        backgroundPanel.add(descriptionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        descriptionScrollPane.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        descriptionScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        descriptionScrollPane.setToolTipText("");
        descriptionScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        descriptionField.setEditable(false);
        descriptionField.setBackground(new java.awt.Color(255, 255, 255));
        descriptionField.setColumns(20);
        descriptionField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        descriptionField.setLineWrap(true);
        descriptionField.setRows(4);
        descriptionField.setText(currentFeedback.getFeedbackDetails());
        descriptionField.setToolTipText("");
        descriptionField.setWrapStyleWord(true);
        descriptionField.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 15, 12, 15));
        descriptionField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        descriptionField.setEnabled(false);
        descriptionScrollPane.setViewportView(descriptionField);

        backgroundPanel.add(descriptionScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 440, 120));

        responseLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        responseLabel.setText("Response");
        backgroundPanel.add(responseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, -1, -1));

        responseScrollPane.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        responseField.setColumns(20);
        responseField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        responseField.setForeground(new java.awt.Color(204, 204, 204));
        responseField.setLineWrap(true);
        responseField.setRows(5);
        responseField.setText("Enter Response Here");
        responseField.setWrapStyleWord(true);
        responseField.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 15, 12, 15));
        responseField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                responseFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                responseFieldFocusLost(evt);
            }
        });
        responseField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                responseFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                responseFieldKeyTyped(evt);
            }
        });
        responseScrollPane.setViewportView(responseField);

        backgroundPanel.add(responseScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 440, 340));

        cancelButton.setBackground(Color.WHITE);
        cancelButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
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
        backgroundPanel.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 500, 200, 50));

        submitButton.setBackground(new java.awt.Color(0, 0, 0));
        submitButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        submitButton.setForeground(new java.awt.Color(255, 255, 255));
        submitButton.setText("Submit");
        submitButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButtonMouseExited(evt);
            }
        });
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        backgroundPanel.add(submitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 500, 200, 50));

        getContentPane().add(backgroundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered

        // Set background colour
        cancelButton.setBackground(new Color(206, 171, 147));
    }//GEN-LAST:event_cancelButtonMouseEntered

    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited

        // Set background colour
        cancelButton.setBackground(Color.WHITE);
    }//GEN-LAST:event_cancelButtonMouseExited

    private void submitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMouseEntered

        // Set background colour
        submitButton.setBackground(new Color(206, 171, 147));
        
        // Set text colour
        submitButton.setForeground(Color.BLACK);
    }//GEN-LAST:event_submitButtonMouseEntered

    private void submitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMouseExited

        // Set background colour
        submitButton.setBackground(Color.BLACK);
        
        // Set text colour
        submitButton.setForeground(Color.WHITE);
    }//GEN-LAST:event_submitButtonMouseExited

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed

        // Dispose the current pop up
        dispose();
        
        // Enable the parent frame
        MainPage.currentFrame.setEnabled(true);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed

        // Retrieve response
        String response = responseField.getText().strip();
        
        // If the response is empty
        if (response.isBlank()) {
        
            // Display message to request user to input again
            SystemPopUp emptyMessage = new SystemPopUp(
                    this,
                    "Error",
                    "Please make sure that you have provided your input before submititng.",
                    new String[]{"OK"}
            );
            emptyMessage.setVisible(true);
            
        } else {
        
            // Update the response
            boolean replyStatus = currentFeedback.managerProvideReply(response);
        
            // If response is updated successfully
            if (replyStatus) {
            
                // Display message to inform success update
                SystemPopUp successMessage = new SystemPopUp(
                        this,
                        "Response Updated Successfully",
                        "Your response is updated to the system successfully.",
                        new String[]{"OK"}
                );
                successMessage.setVisible(true);
                
                // Dispose the current frame
                dispose();
                
                // Refresh the panels
                FeedbackPanel.updateSystemPanel();
                FeedbackPanel.updateVendorPanel();
                FeedbackPanel.updateRunnerPanel();
                
                // Enable the parent frame
                MainPage.currentFrame.setEnabled(true);
                
            } else {
                
                // Display error
                SystemPopUp errorMessage = new SystemPopUp(
                        this,
                        "Error",
                        "Please make sure that you have provided your input before submititng.",
                        new String[]{"OK"}
                );
                errorMessage.setVisible(true);
            }
        }
        
    }//GEN-LAST:event_submitButtonActionPerformed

    private void responseFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_responseFieldFocusGained

        // If the prompt is enter response here
        if (responseField.getText().equals("Enter Response Here")) {
            
            // Remove the prompt
            responseField.setText("");
            
            // Change the text colour to black
            responseField.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_responseFieldFocusGained

    private void responseFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_responseFieldFocusLost

        // If the prompt is blank
        if (responseField.getText().isBlank()) {
            
            // Set the prompt
            responseField.setText("Enter Response Here");
            
            // Change text to grey
            responseField.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_responseFieldFocusLost

    private void responseFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_responseFieldKeyPressed

        // If enter key is pressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        
            // Remove it
            evt.consume();
        }
    }//GEN-LAST:event_responseFieldKeyPressed

    private void responseFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_responseFieldKeyTyped

        // If ';' is pressed
        if (evt.getKeyChar() == ';') {
        
            // Remove it
            evt.consume();
        }
    }//GEN-LAST:event_responseFieldKeyTyped

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
            java.util.logging.Logger.getLogger(FeedbackReplyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FeedbackReplyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FeedbackReplyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FeedbackReplyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FeedbackReplyForm(currentFeedback).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField categoryField;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JTextArea descriptionField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTextArea feedbackTitleField;
    private javax.swing.JLabel feedbackTitleLabel;
    private javax.swing.JScrollPane feedbackTitleScrollPane;
    private javax.swing.JTextField ratingsIDField;
    private javax.swing.JLabel ratingsIDTitle;
    private javax.swing.JTextArea responseField;
    private javax.swing.JLabel responseLabel;
    private javax.swing.JScrollPane responseScrollPane;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
