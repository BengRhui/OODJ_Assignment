
package frontend;

import javax.swing.*;
import java.awt.Cursor;
import java.util.List;

/**
 *
 * @author Chun Ming
 */
public class NotificationPopUp extends javax.swing.JFrame {

    // Variables declaration
    private JPanel parentPanel;
    private JFrame parentFrame;
    public static String userType;
    private List<String> filterUser;
    private List<String> paymentOptions;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JPanel contentHolder;
    private javax.swing.JLabel description;
    private javax.swing.JComboBox<String> filterList;
    private javax.swing.JLabel title;
    private javax.swing.JPanel footer;
    private javax.swing.JComboBox<String> paymentMethod;
    private javax.swing.JLabel paymentMethodLabel;
    private javax.swing.JTextField topUpAmount;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JPanel addCategoryButton;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JLabel icon;
    private javax.swing.JTextField storeId;
    private javax.swing.JLabel storeIdLabel;
    private javax.swing.JTextField storeName;
    private javax.swing.JLabel storeNameLabel;
    private String popUpType;

    //customize the pop-up message for different approach
    public javax.swing.JFrame deleteUser(JFrame frame) {
        parentFrame = frame;
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

    public javax.swing.JFrame updateStoreMsg(){
        popUpType = "updateStoreMsg";
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

    public javax.swing.JFrame userTypeFilter(JPanel panel){
        //TODO: fix a bug where filter without changing changing the combobox will return customer as userType
        parentPanel = panel;
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
        //initialize userType and track changes
        userType = filterList.getSelectedItem().toString();
        filterList.addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                userType = e.getItem().toString();
                System.out.println("Updated userType: " + userType);
            }
        });
        filterList.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        contentHolder.add(filterList);
        filterList.setBounds(30, 100, 430, 60);
        return this;
    }

//    public javax.swing.JFrame userTopUp(JFrame frame){
//        parentFrame = frame;
//        popUpType = "userTopUp";
//        initTopUpPopUp();
//        this.setSize(500,500);
//        this.setLocationRelativeTo(null);
//        this.setVisible(true);
//        return this;
//    }
//
//    public javax.swing.JFrame updateStore(JFrame frame){
//        parentFrame = frame;
//        popUpType = "updateStore";
//        initStorePopUp();
//        this.setSize(500,600);
//        this.setLocationRelativeTo(null);
//        this.setVisible(true);
//        return this;
//    }

    public NotificationPopUp() {}

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
        cancelButton.setBounds(55, 190,170,48);
//        cancelButton.setBounds(35, 214, 200, 48);

        confirmButton.setBackground(new java.awt.Color(173, 139, 115));
        confirmButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        confirmButton.setForeground(new java.awt.Color(255, 255, 255));
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
        confirmButton.setBounds(265, 190,170,48);
//        confirmButton.setBounds(265, 214, 200, 48);

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

//    private void initTopUpPopUp(){
//        contentHolder = new javax.swing.JPanel();
//        title = new javax.swing.JLabel();
//        cancelButton = new javax.swing.JButton();
//        confirmButton = new javax.swing.JButton();
//        footer = new javax.swing.JPanel();
//        paymentMethod = new javax.swing.JComboBox<>();
//        paymentMethodLabel = new javax.swing.JLabel();
//        amountLabel = new javax.swing.JLabel();
//        topUpAmount = new javax.swing.JTextField();
//        paymentOptions = List.of("QR Payment", "Cash", "Debit / Credit Card");
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//        setResizable(false);
//
//        contentHolder.setBackground(new java.awt.Color(255, 251, 233));
//        contentHolder.setLayout(null);
//
//        title.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
//        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        title.setText("Top Up");
//        title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
//        contentHolder.add(title);
//        title.setBounds(35, 18, 430, 42);
//
//        cancelButton.setBackground(new java.awt.Color(227, 202, 165));
//        cancelButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
//        cancelButton.setText("Cancel");
//        cancelButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
//        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                cancelButtonMouseEntered(evt);
//            }
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                cancelButtonMouseExited(evt);
//            }
//        });
//        cancelButton.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                cancelButtonActionPerformed(evt);
//            }
//        });
//        contentHolder.add(cancelButton);
//        cancelButton.setBounds(30, 400, 200, 48);
//
//        confirmButton.setBackground(new java.awt.Color(173, 139, 115));
//        confirmButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
//        confirmButton.setForeground(new java.awt.Color(255, 255, 255));
//        confirmButton.setText("Confirm");
//        confirmButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
//        confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                confirmButtonMouseEntered(evt);
//            }
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                confirmButtonMouseExited(evt);
//            }
//        });
//        confirmButton.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                confirmButtonActionPerformed(evt);
//            }
//        });
//        contentHolder.add(confirmButton);
//        confirmButton.setBounds(260, 400, 200, 48);
//
//        footer.setBackground(new java.awt.Color(255, 251, 233));
//
//        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
//        footer.setLayout(footerLayout);
//        footerLayout.setHorizontalGroup(
//                footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGap(0, 500, Short.MAX_VALUE)
//        );
//        footerLayout.setVerticalGroup(
//                footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGap(0, 38, Short.MAX_VALUE)
//        );
//
//        contentHolder.add(footer);
//        footer.setBounds(0, 460, 500, 38);
//
//        paymentMethod.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
//        paymentOptions.forEach(
//                e -> paymentMethod.addItem(e)
//        );
//        paymentMethod.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
//        paymentMethod.setDoubleBuffered(true);
//        contentHolder.add(paymentMethod);
//        paymentMethod.setBounds(30, 150, 430, 60);
//
//        paymentMethodLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
//        paymentMethodLabel.setText("Select Payment Method");
//        contentHolder.add(paymentMethodLabel);
//        paymentMethodLabel.setBounds(30, 120, 210, 30);
//
//        amountLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
//        amountLabel.setText("Enter Amount (RM):");
//        contentHolder.add(amountLabel);
//        amountLabel.setBounds(30, 240, 280, 30);
//
//        topUpAmount.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
//        topUpAmount.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
//        contentHolder.add(topUpAmount);
//        topUpAmount.setBounds(30, 270, 430, 60);
//
//        getContentPane().add(contentHolder, java.awt.BorderLayout.CENTER);
//
//        pack();
//    }
//
//    private void initStorePopUp(){
//
//        contentHolder = new javax.swing.JPanel();
//        title = new javax.swing.JLabel();
//        cancelButton = new javax.swing.JButton();
//        confirmButton = new javax.swing.JButton();
//        footer = new javax.swing.JPanel();
//        storeIdLabel = new javax.swing.JLabel();
//        storeNameLabel = new javax.swing.JLabel();
//        storeId = new javax.swing.JTextField();
//        storeName = new javax.swing.JTextField();
//        categoryLabel = new javax.swing.JLabel();
//        addCategoryButton = new javax.swing.JPanel();
//        icon = new javax.swing.JLabel();
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//        setResizable(false);
//
//        contentHolder.setBackground(new java.awt.Color(255, 251, 233));
//        contentHolder.setLayout(null);
//
//        title.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
//        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        title.setText("Update Store");
//        title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
//        contentHolder.add(title);
//        title.setBounds(35, 18, 430, 42);
//
//        cancelButton.setBackground(new java.awt.Color(227, 202, 165));
//        cancelButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
//        cancelButton.setText("Cancel");
//        cancelButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
//        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                cancelButtonMouseEntered(evt);
//            }
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                cancelButtonMouseExited(evt);
//            }
//        });
//        cancelButton.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                cancelButtonActionPerformed(evt);
//            }
//        });
//        contentHolder.add(cancelButton);
//        cancelButton.setBounds(30, 510, 200, 48);
//
//        confirmButton.setBackground(new java.awt.Color(173, 139, 115));
//        confirmButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
//        confirmButton.setForeground(new java.awt.Color(255, 255, 255));
//        confirmButton.setText("Confirm");
//        confirmButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
//        confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                confirmButtonMouseEntered(evt);
//            }
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                confirmButtonMouseExited(evt);
//            }
//        });
//        confirmButton.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                confirmButtonActionPerformed(evt);
//            }
//        });
//        contentHolder.add(confirmButton);
//        confirmButton.setBounds(260, 510, 200, 48);
//
//        footer.setBackground(new java.awt.Color(255, 251, 233));
//
//        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
//        footer.setLayout(footerLayout);
//        footerLayout.setHorizontalGroup(
//                footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGap(0, 500, Short.MAX_VALUE)
//        );
//        footerLayout.setVerticalGroup(
//                footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGap(0, 38, Short.MAX_VALUE)
//        );
//
//        contentHolder.add(footer);
//        footer.setBounds(0, 560, 500, 38);
//
//        storeIdLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
//        storeIdLabel.setText("Store ID:");
//        contentHolder.add(storeIdLabel);
//        storeIdLabel.setBounds(30, 80, 210, 30);
//
//        storeNameLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
//        storeNameLabel.setText("Store Name:");
//        contentHolder.add(storeNameLabel);
//        storeNameLabel.setBounds(30, 180, 280, 30);
//
//        storeId.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
//        storeId.setText("S001");
//        storeId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
//        storeId.setEnabled(false);
//        contentHolder.add(storeId);
//        storeId.setBounds(30, 110, 430, 60);
//
//        storeName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
//        storeName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
//        contentHolder.add(storeName);
//        storeName.setBounds(30, 210, 430, 60);
//
//        categoryLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
//        categoryLabel.setText("Category");
//        contentHolder.add(categoryLabel);
//        categoryLabel.setBounds(30, 280, 130, 30);
//
//        addCategoryButton.setBackground(new java.awt.Color(255, 255, 255));
//        addCategoryButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
//        addCategoryButton.setToolTipText("Select Label");
//        addCategoryButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
//        addCategoryButton.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                addCategoryButtonMouseClicked(evt);
//            }
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                addCategoryButtonMouseEntered(evt);
//            }
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                addCategoryButtonMouseExited(evt);
//            }
//        });
//
//        icon.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
//        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        icon.setText("+");
//
//        javax.swing.GroupLayout addCategoryButtonLayout = new javax.swing.GroupLayout(addCategoryButton);
//        addCategoryButton.setLayout(addCategoryButtonLayout);
//        addCategoryButtonLayout.setHorizontalGroup(
//                addCategoryButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGap(0, 136, Short.MAX_VALUE)
//                        .addGroup(addCategoryButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                .addComponent(icon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
//        );
//        addCategoryButtonLayout.setVerticalGroup(
//                addCategoryButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGap(0, 28, Short.MAX_VALUE)
//                        .addGroup(addCategoryButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                .addComponent(icon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//        );
//
//        contentHolder.add(addCategoryButton);
//        addCategoryButton.setBounds(30, 320, 140, 30);
//
//        getContentPane().add(contentHolder, java.awt.BorderLayout.CENTER);
//
//        pack();
//    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        switch (popUpType){
            case "userTypeFilter":
                userType = filterList.getSelectedItem().toString();
                parentPanel.setEnabled(true);
                this.dispose();
                break;
            case "deleteUser":
                parentFrame.setEnabled(true);
                this.dispose();
                break;
            default:
                this.dispose();
        }
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        switch(popUpType){
            case "deleteUser":
                //deleteUser method should be call here

                parentFrame.setEnabled(true);
                this.dispose();
                break;
            case "updateUser":
                this.dispose();
                break;
            case "topUpReceipt":
                this.dispose();
                break;
            case "userTypeFilter":
//                userType = filterList.getSelectedItem().toString(); //This will overwrite the type back to the default Customer, after 3rd loop without reopening the Panel, I struggle on this stupid bug for 3 hours
                System.out.println("Final: " + userType);
                parentPanel.setEnabled(true);
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

    private void addCategoryButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCategoryButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addCategoryButtonMouseClicked

    private void addCategoryButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCategoryButtonMouseEntered
        addCategoryButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_addCategoryButtonMouseEntered

    private void addCategoryButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCategoryButtonMouseExited
        addCategoryButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    
}
