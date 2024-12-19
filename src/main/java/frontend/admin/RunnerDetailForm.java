/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.admin;

import javax.swing.*;
import java.awt.Cursor;

/**
 *
 * @author Chun Ming
 */
public class RunnerDetailForm extends javax.swing.JFrame {

    NotificationPopUp popUp = new NotificationPopUp();
    private JFrame parentFrame;

    public RunnerDetailForm(JFrame parentFrame){
        this.parentFrame = parentFrame;
        initComponents();
        textFormatter();
        initContent();
    }

    public RunnerDetailForm() {
        initComponents();
        textFormatter();
        initContent();
    }

    private void textFormatter() {
        //this function is to set the text and format of the JLabel
        note.setText("""
                <html><b><u>Note:</u></b>
                <ul>
                <li>Password should consist of 8 to 20 characters</li>
                <li>Password should have at least one number and one alphabet</li>
                <li>Password should have at least one special characters (#, @, $, etc.)</li>
                </ul>
                </html>
                """);
        this.setLocationRelativeTo(null);
    }

    private void initContent() {
        //Initialize content of selected user
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        runnerDetailsHolder = new javax.swing.JPanel();
        runnerDetails = new javax.swing.JLabel();
        runnerIdLabel = new javax.swing.JLabel();
        note = new javax.swing.JLabel();
        confirmPasswordLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        confirmPassword = new javax.swing.JTextField();
        closeButton = new javax.swing.JLabel();
        runnerNameLabel = new javax.swing.JLabel();
        runnerName = new javax.swing.JTextField();
        contactNoLabel = new javax.swing.JLabel();
        contactNo = new javax.swing.JTextField();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        runnerId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        runnerDetailsHolder.setBackground(new java.awt.Color(255, 251, 233));

        runnerDetails.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        runnerDetails.setText("Delivery Runner Details");

        runnerIdLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        runnerIdLabel.setText("Delivery Runner ID");

        note.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        note.setText("Note");

        confirmPasswordLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        confirmPasswordLabel.setText("Confirm Password");

        passwordLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        passwordLabel.setText("Password");

        emailLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        emailLabel.setText("Email");

        email.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        email.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        password.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        password.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        confirmPassword.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        confirmPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/close_icon.png"))); // NOI18N
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButtonMouseExited(evt);
            }
        });

        runnerNameLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        runnerNameLabel.setText("Delivery Runner Name");

        runnerName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        runnerName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        contactNoLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        contactNoLabel.setText("Contact Number");

        contactNo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        contactNo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        confirmButton.setBackground(new java.awt.Color(173, 139, 115));
        confirmButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        confirmButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmButton.setText("Confirm");
        confirmButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        confirmButton.setOpaque(true);
        confirmButton.setPreferredSize(new java.awt.Dimension(200, 50));
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

        runnerId.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        runnerId.setText("R001");
        runnerId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        runnerId.setEnabled(false);

        javax.swing.GroupLayout runnerDetailsHolderLayout = new javax.swing.GroupLayout(runnerDetailsHolder);
        runnerDetailsHolder.setLayout(runnerDetailsHolderLayout);
        runnerDetailsHolderLayout.setHorizontalGroup(
            runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, runnerDetailsHolderLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(runnerDetailsHolderLayout.createSequentialGroup()
                        .addGroup(runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(runnerName, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                            .addComponent(contactNo, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                            .addComponent(runnerIdLabel)
                            .addComponent(runnerId)
                            .addComponent(runnerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(contactNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, runnerDetailsHolderLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(email, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(runnerDetailsHolderLayout.createSequentialGroup()
                                        .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, runnerDetailsHolderLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addGroup(runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                                        .addComponent(confirmPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(confirmPassword)
                                        .addComponent(note, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(runnerDetailsHolderLayout.createSequentialGroup()
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(runnerDetailsHolderLayout.createSequentialGroup()
                        .addComponent(runnerDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 659, Short.MAX_VALUE)
                        .addComponent(closeButton)))
                .addGap(40, 40, 40))
        );
        runnerDetailsHolderLayout.setVerticalGroup(
            runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(runnerDetailsHolderLayout.createSequentialGroup()
                .addGroup(runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(runnerDetailsHolderLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(runnerDetails))
                    .addGroup(runnerDetailsHolderLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(closeButton)))
                .addGap(38, 38, 38)
                .addGroup(runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(runnerIdLabel))
                .addGroup(runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(runnerId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(runnerNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(runnerName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contactNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(note, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(runnerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );

        getContentPane().add(runnerDetailsHolder, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        parentFrame.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        cancelButton.setBackground(new java.awt.Color(227, 202, 165));
    }//GEN-LAST:event_cancelButtonMouseExited

    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setBackground(new java.awt.Color(227, 195, 150));
    }//GEN-LAST:event_cancelButtonMouseEntered

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        //register/modify vendor method should be here
        popUp.updateUser().setVisible(true);
        parentFrame.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void confirmButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseExited
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        confirmButton.setBackground(new java.awt.Color(173, 139, 115));
    }//GEN-LAST:event_confirmButtonMouseExited

    private void confirmButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseEntered
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmButton.setBackground(new java.awt.Color(173, 120, 110));
    }//GEN-LAST:event_confirmButtonMouseEntered

    private void closeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseExited
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_closeButtonMouseExited

    private void closeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseEntered
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_closeButtonMouseEntered

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
        parentFrame.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_closeButtonMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel closeButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JTextField confirmPassword;
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JTextField contactNo;
    private javax.swing.JLabel contactNoLabel;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel note;
    private javax.swing.JTextField password;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel runnerDetails;
    private javax.swing.JPanel runnerDetailsHolder;
    private javax.swing.JTextField runnerId;
    private javax.swing.JLabel runnerIdLabel;
    private javax.swing.JTextField runnerName;
    private javax.swing.JLabel runnerNameLabel;
    // End of variables declaration//GEN-END:variables
}
