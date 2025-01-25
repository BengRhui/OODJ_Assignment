package frontend.admin;

import javax.swing.JFrame;
import java.awt.Cursor;

/**
 *
 * @author Chun Ming
 */
public class CustomerDetailForm extends javax.swing.JFrame {

    NotificationPopUp popUp = new NotificationPopUp();
    private JFrame parentFrame;

    public CustomerDetailForm(JFrame parentFrame){
        this.parentFrame = parentFrame;
        initComponents();
        textFormatter();
        initContent();
    }

    public CustomerDetailForm() {
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
        //initialize content of selected user
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        customerDetailsHolder = new javax.swing.JPanel();
        customerDetails = new javax.swing.JLabel();
        closeButton = new javax.swing.JLabel();
        customerIdLabel = new javax.swing.JLabel();
        customerNameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        customerName = new javax.swing.JTextField();
        contactNoLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        contactNumber = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        confirmPassword = new javax.swing.JTextField();
        confirmPasswordLabel = new javax.swing.JLabel();
        addressLine1 = new javax.swing.JTextField();
        deliveryAddressLabel = new javax.swing.JLabel();
        addressLine2 = new javax.swing.JTextField();
        postcode = new javax.swing.JTextField();
        city = new javax.swing.JTextField();
        state = new javax.swing.JComboBox<>();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        note = new javax.swing.JLabel();
        customerId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        customerDetailsHolder.setBackground(new java.awt.Color(255, 251, 233));

        customerDetails.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        customerDetails.setText("Customer Details");

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

        customerIdLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        customerIdLabel.setText("Customer ID");

        customerNameLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        customerNameLabel.setText("Customer Name");

        emailLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        emailLabel.setText("Email");

        email.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        email.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        customerName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        customerName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        contactNoLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        contactNoLabel.setText("Contact Number");

        passwordLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        passwordLabel.setText("Password");

        contactNumber.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        contactNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        password.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        password.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        confirmPassword.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        confirmPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        confirmPasswordLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        confirmPasswordLabel.setText("Confirm Password");

        addressLine1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        addressLine1.setText("Address Line 1");
        addressLine1.setToolTipText("Address Line 1");
        addressLine1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        deliveryAddressLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        deliveryAddressLabel.setText("Delivery Address");

        addressLine2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        addressLine2.setText("Address Line 2");
        addressLine2.setToolTipText("Address Line 2");
        addressLine2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        postcode.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        postcode.setText("Postcode");
        postcode.setToolTipText("Address Line 2");
        postcode.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        city.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        city.setText("City");
        city.setToolTipText("Address Line 2");
        city.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        state.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        state.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        state.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

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

        note.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        note.setText("jLabel1");

        customerId.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        customerId.setText("C001");
        customerId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        customerId.setEnabled(false);

        javax.swing.GroupLayout customerDetailsHolderLayout = new javax.swing.GroupLayout(customerDetailsHolder);
        customerDetailsHolder.setLayout(customerDetailsHolderLayout);
        customerDetailsHolderLayout.setHorizontalGroup(
            customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                                .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addressLine2, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                                        .addComponent(postcode, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(note, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                                .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                                        .addComponent(contactNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(540, 540, 540)
                                        .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                                        .addComponent(deliveryAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(510, 510, 510)
                                        .addComponent(confirmPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                                        .addComponent(addressLine1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(confirmPassword))
                                    .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                                        .addComponent(contactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                                .addComponent(customerIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(customerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(350, 350, 350)
                                .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                                .addComponent(customerId, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(customerName, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(email))
                            .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                                .addComponent(customerDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(789, 789, 789)
                                .addComponent(closeButton)))))
                .addGap(40, 40, 40))
        );
        customerDetailsHolderLayout.setVerticalGroup(
            customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeButton))
                .addGap(50, 50, 50)
                .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(customerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(customerIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(customerName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(customerId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contactNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deliveryAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addressLine1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(addressLine2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(postcode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(customerDetailsHolderLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(note, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(customerDetailsHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        getContentPane().add(customerDetailsHolder, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseEntered
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmButton.setBackground(new java.awt.Color(173, 120, 110));
    }//GEN-LAST:event_confirmButtonMouseEntered

    private void confirmButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseExited
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        confirmButton.setBackground(new java.awt.Color(173, 139, 115));
    }//GEN-LAST:event_confirmButtonMouseExited

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        //register/modify customer method should be here
        popUp.updateUser().setVisible(true);
        parentFrame.setEnabled(true);
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
        parentFrame.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void closeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseEntered
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_closeButtonMouseEntered

    private void closeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseExited
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_closeButtonMouseExited

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
        parentFrame.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_closeButtonMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressLine1;
    private javax.swing.JTextField addressLine2;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField city;
    private javax.swing.JLabel closeButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JTextField confirmPassword;
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JLabel contactNoLabel;
    private javax.swing.JTextField contactNumber;
    private javax.swing.JLabel customerDetails;
    private javax.swing.JPanel customerDetailsHolder;
    private javax.swing.JTextField customerId;
    private javax.swing.JLabel customerIdLabel;
    private javax.swing.JTextField customerName;
    private javax.swing.JLabel customerNameLabel;
    private javax.swing.JLabel deliveryAddressLabel;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel note;
    private javax.swing.JTextField password;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField postcode;
    private javax.swing.JComboBox<String> state;
    // End of variables declaration//GEN-END:variables
}
