package frontend;

import java.awt.Cursor;

/**
 *
 * @author Chun Ming
 */
public class VendorDetailForm extends javax.swing.JFrame {


    public VendorDetailForm() {
        initComponents();
        textFormatter();
        initContent();
    }
    private void textFormatter() {
        //this function is to set the text and format of the JLabel
        note.setText("""
                <html><b><u>Note:</u></b>
                <ul>
                <li>Password should consist of 6 to 12 characters</li>
                <li>Password should have at least one special characters (#, @, $, etc.)</li>
                </ul>
                </html>
                """);
    }

    private void initContent() {
        //Initialize content of selected user
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vendorDetailsHolder = new javax.swing.JPanel();
        vendorDetails = new javax.swing.JLabel();
        vendorIdLabel = new javax.swing.JLabel();
        note = new javax.swing.JLabel();
        confirmPasswordLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        confirmPassword = new javax.swing.JTextField();
        closeButton = new javax.swing.JLabel();
        storeIdLabel = new javax.swing.JLabel();
        storeId = new javax.swing.JTextField();
        vendorNameLabel = new javax.swing.JLabel();
        vendorName = new javax.swing.JTextField();
        storeNameLabel = new javax.swing.JLabel();
        storeName = new javax.swing.JTextField();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        vendorId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        vendorDetailsHolder.setBackground(new java.awt.Color(255, 251, 233));

        vendorDetails.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        vendorDetails.setText("Vendor Details");

        vendorIdLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        vendorIdLabel.setText("Vendor ID");

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

        storeIdLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        storeIdLabel.setText("Store ID");

        storeId.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        storeId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        vendorNameLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        vendorNameLabel.setText("Vendor Name");

        vendorName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        vendorName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        storeNameLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        storeNameLabel.setText("Store Name");

        storeName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        storeName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        confirmButton.setBackground(new java.awt.Color(173, 139, 115));
        confirmButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        confirmButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmButton.setText("Confirm");
        confirmButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
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

        vendorId.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        vendorId.setText("V001");
        vendorId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        vendorId.setEnabled(false);

        javax.swing.GroupLayout vendorDetailsHolderLayout = new javax.swing.GroupLayout(vendorDetailsHolder);
        vendorDetailsHolder.setLayout(vendorDetailsHolderLayout);
        vendorDetailsHolderLayout.setHorizontalGroup(
            vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vendorDetailsHolderLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vendorDetailsHolderLayout.createSequentialGroup()
                        .addGroup(vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(vendorDetailsHolderLayout.createSequentialGroup()
                                .addComponent(vendorIdLabel)
                                .addGap(244, 244, 244)
                                .addComponent(storeIdLabel))
                            .addComponent(vendorNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(storeNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(storeName, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(vendorDetailsHolderLayout.createSequentialGroup()
                                .addComponent(vendorId, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(storeId)))
                        .addGroup(vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vendorDetailsHolderLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(email, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(vendorDetailsHolderLayout.createSequentialGroup()
                                        .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vendorDetailsHolderLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addGroup(vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(vendorDetailsHolderLayout.createSequentialGroup()
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                                        .addComponent(confirmPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(confirmPassword)
                                        .addComponent(note, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(vendorDetailsHolderLayout.createSequentialGroup()
                        .addComponent(vendorDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeButton)))
                .addGap(40, 40, 40))
        );
        vendorDetailsHolderLayout.setVerticalGroup(
            vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vendorDetailsHolderLayout.createSequentialGroup()
                .addGroup(vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vendorDetailsHolderLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(closeButton))
                    .addGroup(vendorDetailsHolderLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(vendorDetails)))
                .addGap(38, 38, 38)
                .addGroup(vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vendorIdLabel)
                    .addComponent(storeIdLabel))
                .addGroup(vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storeId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vendorId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vendorNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storeNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storeName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(note, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(vendorDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );

        getContentPane().add(vendorDetailsHolder, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
        this.dispose();
    }//GEN-LAST:event_closeButtonMouseClicked

    private void closeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseEntered
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_closeButtonMouseEntered

    private void closeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseExited
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_closeButtonMouseExited

    private void confirmButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseEntered
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmButton.setBackground(new java.awt.Color(173, 120, 110));
    }//GEN-LAST:event_confirmButtonMouseEntered

    private void confirmButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseExited
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        confirmButton.setBackground(new java.awt.Color(173, 139, 115));
    }//GEN-LAST:event_confirmButtonMouseExited

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        //register/modify vendor method should be here
        this.dispose();
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setBackground(new java.awt.Color(227, 195, 150));
    }//GEN-LAST:event_cancelButtonMouseEntered

    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        cancelButton.setBackground(new java.awt.Color(227, 202, 165));
    }//GEN-LAST:event_cancelButtonMouseExited

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel closeButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JTextField confirmPassword;
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel note;
    private javax.swing.JTextField password;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField storeId;
    private javax.swing.JLabel storeIdLabel;
    private javax.swing.JTextField storeName;
    private javax.swing.JLabel storeNameLabel;
    private javax.swing.JLabel vendorDetails;
    private javax.swing.JPanel vendorDetailsHolder;
    private javax.swing.JTextField vendorId;
    private javax.swing.JLabel vendorIdLabel;
    private javax.swing.JTextField vendorName;
    private javax.swing.JLabel vendorNameLabel;
    // End of variables declaration//GEN-END:variables
}
