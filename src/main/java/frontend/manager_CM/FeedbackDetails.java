/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.manager_CM;

import javax.swing.*;
import java.awt.Cursor;

/**
 *
 * @author Chun Ming
 */
public class FeedbackDetails extends javax.swing.JFrame {

    private JFrame mainFrame;

    public FeedbackDetails(JFrame frame) {
        mainFrame = frame;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pageHolder = new javax.swing.JPanel();
        close = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        feedbackIdLabel = new javax.swing.JLabel();
        categoryLabel = new javax.swing.JLabel();
        feedbackId = new javax.swing.JTextField();
        category = new javax.swing.JTextField();
        feedbackTitleLabel = new javax.swing.JLabel();
        feedbackTitle = new javax.swing.JTextField();
        contentLabel = new javax.swing.JLabel();
        content = new javax.swing.JTextArea();
        responseLabel = new javax.swing.JLabel();
        response = new javax.swing.JTextArea();
        submitButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pageHolder.setBackground(new java.awt.Color(255, 251, 233));

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

        title.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        title.setText("Feedback Details");

        feedbackIdLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        feedbackIdLabel.setText("Feedback ID");

        categoryLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        categoryLabel.setText("Category");

        feedbackId.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        feedbackId.setText("C001");
        feedbackId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        feedbackId.setEnabled(false);

        category.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        category.setText("System");
        category.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        category.setEnabled(false);

        feedbackTitleLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        feedbackTitleLabel.setText("Feedback Title");

        feedbackTitle.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        feedbackTitle.setText("title");
        feedbackTitle.setToolTipText("Title");
        feedbackTitle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        contentLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        contentLabel.setText("Description");

        content.setColumns(20);
        content.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        content.setRows(5);
        content.setText("Content");
        content.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        responseLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        responseLabel.setText("Response");

        response.setColumns(20);
        response.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        response.setRows(5);
        response.setText("Response");
        response.setToolTipText("Manager's reponses are here");
        response.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        submitButton.setBackground(new java.awt.Color(173, 139, 115));
        submitButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        submitButton.setForeground(new java.awt.Color(255, 255, 255));
        submitButton.setText("Submit");
        submitButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        submitButton.setOpaque(true);
        submitButton.setPreferredSize(new java.awt.Dimension(200, 50));
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

        cancelButton.setBackground(new java.awt.Color(227, 202, 165));
        cancelButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        cancelButton.setOpaque(true);
        cancelButton.setPreferredSize(new java.awt.Dimension(200, 50));
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

        javax.swing.GroupLayout pageHolderLayout = new javax.swing.GroupLayout(pageHolder);
        pageHolder.setLayout(pageHolderLayout);
        pageHolderLayout.setHorizontalGroup(
            pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pageHolderLayout.createSequentialGroup()
                .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pageHolderLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pageHolderLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pageHolderLayout.createSequentialGroup()
                                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(close))
                            .addGroup(pageHolderLayout.createSequentialGroup()
                                .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(feedbackTitle)
                                        .addGroup(pageHolderLayout.createSequentialGroup()
                                            .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(feedbackIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                                .addComponent(feedbackId))
                                            .addGap(18, 18, 18)
                                            .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(category)
                                                .addComponent(categoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                                        .addComponent(contentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                                        .addComponent(feedbackTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(55, 55, 55)
                                .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pageHolderLayout.createSequentialGroup()
                                        .addComponent(responseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                        .addGap(223, 223, 223))
                                    .addComponent(response))))))
                .addGap(20, 20, 20))
        );
        pageHolderLayout.setVerticalGroup(
            pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageHolderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(feedbackIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pageHolderLayout.createSequentialGroup()
                        .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(feedbackId, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(feedbackTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(feedbackTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(response))
                .addGap(18, 18, 18)
                .addGroup(pageHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pageHolder, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMouseEntered
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitButton.setBackground(new java.awt.Color(173, 120, 110));
    }//GEN-LAST:event_submitButtonMouseEntered

    private void submitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMouseExited
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        submitButton.setBackground(new java.awt.Color(173, 139, 115));
    }//GEN-LAST:event_submitButtonMouseExited

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed

    }//GEN-LAST:event_submitButtonActionPerformed

    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setBackground(new java.awt.Color(227, 195, 150));
    }//GEN-LAST:event_cancelButtonMouseEntered

    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        cancelButton.setBackground(new java.awt.Color(227, 202, 165));
    }//GEN-LAST:event_cancelButtonMouseExited

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        mainFrame.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_closeMouseEntered

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        close.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_closeMouseExited

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        mainFrame.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_closeMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField category;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JLabel close;
    private javax.swing.JTextArea content;
    private javax.swing.JLabel contentLabel;
    private javax.swing.JTextField feedbackId;
    private javax.swing.JLabel feedbackIdLabel;
    private javax.swing.JTextField feedbackTitle;
    private javax.swing.JLabel feedbackTitleLabel;
    private javax.swing.JPanel pageHolder;
    private javax.swing.JTextArea response;
    private javax.swing.JLabel responseLabel;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
