
package frontend;

import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chun Ming
 */
public class AdminPopUp extends javax.swing.JFrame {

    // Variables declaration
    public static String userType;
    private List<String> filterUser;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JPanel contentHolder;
    private javax.swing.JLabel description;
    private javax.swing.JComboBox<String> filterList;
    private javax.swing.JLabel title;
    private javax.swing.JPanel footer;
    private String popUpType;

    //customize the pop-up message for different approach
    public javax.swing.JFrame deleteUser(){
        popUpType = "deleteUser";
        initNotificationPopUp();
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        title.setText("Message");
        description.setText("Are you sure you wish to delete vendor V001?"); //+ .getuserType() + .getuserId() + " ?"
        return this;
    }

    public javax.swing.JFrame updateUser(){
        popUpType = "updateUser";
        initNotificationPopUp();
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        title.setText("New User Message");
        description.setText("<html>User updated successfully. <br>You will be redirected to the User List page.</html>");
        contentHolder.remove(cancelButton);
        confirmButton.setText("OK");
        confirmButton.setBounds(170, 190, 170, 48);
        return this;
    }

    public javax.swing.JFrame topUpReceipt(){
        popUpType = "topUpReceipt";
        initNotificationPopUp();
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        title.setText("Top Up Message");
        description.setText("<html>Top up successful. <br>A digital receipt will be issued to the customer.</html>");
        contentHolder.remove(cancelButton);
        confirmButton.setText("OK");
        confirmButton.setBounds(170, 190, 170, 48);
        return this;
    }

    public javax.swing.JFrame updateStore(){
        popUpType = "updateStore";
        initNotificationPopUp();
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        title.setText("Message");
        description.setText("Store information has been updated successfully. ");
        contentHolder.remove(cancelButton);
        confirmButton.setText("OK");
        confirmButton.setBounds(170, 190, 170, 48);
        return this;
    }

    public javax.swing.JFrame userTypeFilter(){
        popUpType = "userTypeFilter";
        initNotificationPopUp();
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        title.setText("Select User Type");
        contentHolder.remove(description);
        confirmButton.setText("Confirm");
        cancelButton.setText("Cancel");
        filterUser = List.of("Customer", "Vendor", "Runner");
        filterList = new javax.swing.JComboBox<>();
        filterList.setFont(new java.awt.Font("Arial", 0, 18));
        filterUser.forEach(
                e -> filterList.addItem(e)
        );
        filterList.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        contentHolder.add(filterList);
        filterList.setBounds(30, 100, 430, 60);
        return this;
    }


    public AdminPopUp() {}

    private void initNotificationPopUp() {
        //this function generate the base structure of pop out like notification and confirmation

        contentHolder = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();
        footer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        contentHolder.setBackground(new java.awt.Color(255, 251, 233));
        contentHolder.setLayout(null);

        title.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Title");
        title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        contentHolder.add(title);
        title.setBounds(35, 18, 430, 42);

        description.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        description.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        description.setText("Description");
        contentHolder.add(description);
        description.setBounds(35, 86, 430, 93);

        cancelButton.setBackground(new java.awt.Color(227, 202, 165));
        cancelButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cancelButton.setText("No");
        cancelButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
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
        contentHolder.add(cancelButton);
        cancelButton.setBounds(35, 214, 200, 48);

        confirmButton.setBackground(new java.awt.Color(173, 139, 115));
        confirmButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        confirmButton.setText("Yes");
        confirmButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
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
        contentHolder.add(confirmButton);
        confirmButton.setBounds(265, 214, 200, 48);

        footer.setBackground(new java.awt.Color(255, 251, 233));

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
                footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 500, Short.MAX_VALUE)
        );
        footerLayout.setVerticalGroup(
                footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 38, Short.MAX_VALUE)
        );

        contentHolder.add(footer);
        footer.setBounds(0, 262, 500, 38);

        getContentPane().add(contentHolder, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        switch(popUpType){
            case "deleteUser":
                //deleteUser method should be call here
                this.dispose();
                break;
            case "updateUser":
                this.dispose();
                break;
            case "topUpReceipt":
                this.dispose();
                break;
            case "userTypeFilter":
                userType = filterList.getSelectedItem().toString();
                this.dispose();
                break;
            default:
                this.dispose();
                break;
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void confirmButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseEntered
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmButton.setBackground(new java.awt.Color(173, 120, 110));
    }//GEN-LAST:event_confirmButtonMouseEntered

    private void confirmButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseExited
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        confirmButton.setBackground(new java.awt.Color(173, 139, 115)); //AD8B73
    }//GEN-LAST:event_confirmButtonMouseExited

    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setBackground(new java.awt.Color(227, 195, 150));
    }//GEN-LAST:event_cancelButtonMouseEntered

    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        cancelButton.setBackground(new java.awt.Color(227, 202, 165)); //E3CAA5
    }//GEN-LAST:event_cancelButtonMouseExited

    
}
