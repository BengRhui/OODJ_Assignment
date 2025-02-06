/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.customer;

import backend.entity.Item;
import backend.entity.Order;
import backend.entity.Order.OrderStatus;
import backend.utility.Utility;
import frontend.pop_up.ChangeDiningPopUp;
import frontend.pop_up.SystemPopUp;
import frontend.pop_up.TableNumberPopUp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jun Hong (TP068580), Beng Rhui (TP068495)
 */
public class OrderStatusFrame extends javax.swing.JFrame {

    private Order currentOrder;
    
    /**
     * Creates new form OrderStatusFrame
     */
    public OrderStatusFrame(Order order) {
        
        // Initiate current order
        currentOrder = order;
        
        // Call pop up to change dining method for pending change orders
        if (currentOrder.getOrderStatus() == OrderStatus.PENDING_CHANGE)
            updateMethodForPendingOrders();
        
        // Render GUI
        initComponents();
        
        // If order is not in waiting status
        if (currentOrder.getOrderStatus() != OrderStatus.WAITING_RUNNER && 
                currentOrder.getOrderStatus() != OrderStatus.WAITING_VENDOR && 
                currentOrder.getOrderStatus() != OrderStatus.WAITING_VENDOR_AND_RUNNER) {
            
            // Remove the cancel button
            buttonPanel.remove(cancelOrderButton);
        }
        
        // Initialize item panel
        initializeItemPanel();
    }
    
    /**
     * This method calls a pop up and request user to change dining method for pending change orders.
     */
    private void updateMethodForPendingOrders() {
        
        // Call the pop up
        ChangeDiningPopUp popUp = new ChangeDiningPopUp(MainPage.currentFrame);
        popUp.setLocationRelativeTo(MainPage.currentFrame);
        popUp.setVisible(true);
        
        // Retrieve the dining method
        Order.DiningType selectedMethod = popUp.getDiningMethod();
        
        // If the dining type is dine in
        if (selectedMethod == Order.DiningType.DINE_IN) {
        
            // Show the table number pop up
            TableNumberPopUp tableNumberPrompt = new TableNumberPopUp(MainPage.currentFrame, null);
            tableNumberPrompt.setLocationRelativeTo(MainPage.currentFrame);
            tableNumberPrompt.removeCancelButton();
            tableNumberPrompt.setVisible(true);
            
            // Retrieve the table number
            String tableNumberRetrieved = tableNumberPrompt.retrieveTableNumber();
            
            // Set the table number to order
            currentOrder.setTableNumber(tableNumberRetrieved);
        }

        // Update dining method
        int changeStatus = currentOrder.customerChangeDiningStatus(selectedMethod);
        
        // Inform success if change is made correctly
        if (changeStatus == 1) {
        
            // Display message
            SystemPopUp successModificationMessage = new SystemPopUp(
                    MainPage.currentFrame,
                    "Update Successful",
                    "Order details have been updated successfully.",
                    new String[]{"OK"}
            );
            successModificationMessage.setVisible(true);
            
            // Update panels
            HomePanel.updateOrderPanel();
            
        } else {
        
            // Declare string to store error message
            String errorString;
            
            // Generate different error message based on status
            switch (changeStatus) {
                case 0 -> errorString = "Invalid order type. Please inspect code.";
                case -1 -> errorString = "Order cannot be cancelled. Please inspect code.";
                case -2 -> errorString = "Notifications cannot be created. Please inspect code.";
                default -> throw new IllegalStateException("Invalid status. Please inspect code.");
            }
            
            // Display error
            SystemPopUp errorMessage = new SystemPopUp(
                    this,
                    "Error Changing Order",
                    errorString,
                    new String[]{"OK"}
            );
            errorMessage.setVisible(true);
            
            // Call back the methods until it is valid
            updateMethodForPendingOrders();
        }
    }

    /**
     * This method helps to initialize the item panel.
     */
    private void initializeItemPanel() {
        
        // Retrieve the map of items ordered
        Map<Item, Integer> orderedItems = currentOrder.getOrderItem();
        
        if (orderedItems.isEmpty() || (orderedItems.size() == 1 && orderedItems.containsKey(Item.deliveryFees))) {
        
            // Generate a JPanel to store label
            JPanel emptyPanel = new JPanel(null);
            
            // Generate an empty label to state that the item is unavailable
            JLabel emptyLabel = new JLabel("No item ordered.");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            emptyLabel.setBounds(0, 0, 300, 30);

            // Add the label to the empty panel
            emptyPanel.add(emptyLabel);

            // Add the empty label to the container panel and return it
            itemPanel.add(emptyPanel);

        } else {
            
            // Declare an array to store the generated text (to calculate size of container)
            ArrayList<JLabel> labelList = new ArrayList<>();
            
            // Loop through the item map
            for (Map.Entry<Item, Integer> item : orderedItems.entrySet()) {

                // If the item is delivery fees, skip it
                if (item.getKey().getItemName().equalsIgnoreCase("Delivery Fees")) continue;
                
                // Create a label to display the description
                String itemDescription = item.getKey().getItemName() + " x " + item.getValue();
                JLabel categoryLabel = new JLabel(itemDescription);
                categoryLabel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.BLACK, 1, true),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20))
                );
                categoryLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                categoryLabel.setBackground(Color.WHITE);
                categoryLabel.setOpaque(true);

                // Add the description to the container panel
                itemPanel.add(categoryLabel);    
                
                // Add the label to array (to calculate container size)
                labelList.add(categoryLabel);
            }
            
            // Declare dimensions for the panel
            int hGapBetweenItem = 20;
            int vGapBetweenItem = 10;
            int descriptionHeight = labelList.getFirst().getPreferredSize().height;
            int currentWidth = 0;
            int panelWidth = 500;
            int panelHeight = vGapBetweenItem + descriptionHeight + vGapBetweenItem;

            // Loop through each label
            for (JLabel label : labelList) {

                // Get the width for each label
                int descriptionWidth = label.getPreferredSize().width;

                // If the width exceeds the panel width after adding
                if ((currentWidth + descriptionWidth) > panelWidth) {

                    // Add the height of the panel and reset the x position
                    panelHeight += descriptionHeight + hGapBetweenItem;
                    currentWidth = descriptionWidth + vGapBetweenItem;
                    continue;
                }

                // If it does not exceed, then add the width
                currentWidth += descriptionWidth;

                // If width is exceeded after adding a gap
                if ((currentWidth + vGapBetweenItem) > panelWidth) {

                    // Add the panel height and reset the x position
                    panelHeight += descriptionHeight + hGapBetweenItem;
                    currentWidth = vGapBetweenItem;
                    continue;
                }

                // If not, add the width of the gap
                currentWidth += vGapBetweenItem;
            }

            // Set the container to the final size and return it
            itemPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
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

        backgroundPanel = new javax.swing.JPanel();
        orderStatusTitle = new javax.swing.JLabel();
        orderIDTitle = new javax.swing.JLabel();
        orderIDField = new javax.swing.JLabel();
        timeTitle = new javax.swing.JLabel();
        timeField = new javax.swing.JLabel();
        statusTitle = new javax.swing.JLabel();
        statusField = new javax.swing.JLabel();
        stallNameTitle = new javax.swing.JLabel();
        stallNameScrollPane = new javax.swing.JScrollPane();
        stallNameField = new javax.swing.JTextArea();
        diningMethodTitle = new javax.swing.JLabel();
        diningMethodField = new javax.swing.JLabel();
        paymentField = new javax.swing.JLabel();
        paymentTitle = new javax.swing.JLabel();
        itemOrderedTitle = new javax.swing.JLabel();
        itemScrollPane = new javax.swing.JScrollPane();
        itemPanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        cancelOrderButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Order Status");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backgroundPanel.setBackground(new java.awt.Color(255, 251, 233));
        backgroundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        orderStatusTitle.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        orderStatusTitle.setText("Order Status");
        backgroundPanel.add(orderStatusTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        orderIDTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        orderIDTitle.setText("Order ID");
        backgroundPanel.add(orderIDTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        orderIDField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        orderIDField.setText(currentOrder.getOrderID());
        backgroundPanel.add(orderIDField, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 510, -1));

        timeTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        timeTitle.setText("Order Placement Time");
        backgroundPanel.add(timeTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        timeField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        timeField.setText(Utility.generateString(currentOrder.getOrderedDate()));
        backgroundPanel.add(timeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 510, -1));

        statusTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        statusTitle.setText("Current Status");
        backgroundPanel.add(statusTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        statusField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        statusField.setText(currentOrder.getOrderStatus().toString());
        backgroundPanel.add(statusField, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 510, -1));

        stallNameTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        stallNameTitle.setText("Stall Name");
        backgroundPanel.add(stallNameTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        stallNameScrollPane.setBorder(null);
        stallNameScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        stallNameScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        stallNameField.setEditable(false);
        stallNameField.setBackground(new java.awt.Color(255, 251, 233));
        stallNameField.setColumns(20);
        stallNameField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        stallNameField.setRows(1);
        stallNameField.setText(currentOrder.getOrderedStall().getStallName());
        stallNameField.setBorder(null);
        stallNameField.setCaretColor(new java.awt.Color(255, 251, 233));
        stallNameScrollPane.setViewportView(stallNameField);

        backgroundPanel.add(stallNameScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 500, 30));

        diningMethodTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        diningMethodTitle.setText("Dining Method");
        backgroundPanel.add(diningMethodTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        diningMethodField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        diningMethodField.setText(currentOrder.getDiningType().toString());
        backgroundPanel.add(diningMethodField, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 510, -1));

        paymentField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        paymentField.setText(String.format("RM%.2f", currentOrder.getOrderPrice()));
        backgroundPanel.add(paymentField, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, 510, -1));

        paymentTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        paymentTitle.setText("Payment Made");
        backgroundPanel.add(paymentTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

        itemOrderedTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        itemOrderedTitle.setText("Item Ordered");
        backgroundPanel.add(itemOrderedTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, -1, -1));

        itemScrollPane.setBorder(null);

        itemPanel.setBackground(new java.awt.Color(255, 251, 233));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 10);
        flowLayout1.setAlignOnBaseline(true);
        itemPanel.setLayout(flowLayout1);
        itemScrollPane.setViewportView(itemPanel);

        backgroundPanel.add(itemScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, 520, 70));

        buttonPanel.setBackground(new java.awt.Color(255, 251, 233));
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        backButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        backButton.setText("Back");
        backButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new java.awt.Dimension(140, 50));
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButtonMouseExited(evt);
            }
        });
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(backButton);

        cancelOrderButton.setBackground(new java.awt.Color(0, 0, 0));
        cancelOrderButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cancelOrderButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelOrderButton.setText("Cancel Order");
        cancelOrderButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        cancelOrderButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelOrderButton.setFocusPainted(false);
        cancelOrderButton.setPreferredSize(new java.awt.Dimension(190, 50));
        cancelOrderButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelOrderButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelOrderButtonMouseExited(evt);
            }
        });
        cancelOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelOrderButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(cancelOrderButton);

        backgroundPanel.add(buttonPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, 370, 60));

        getContentPane().add(backgroundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseEntered

        // Change background
        backButton.setBackground(new Color(206, 171, 147));
    }//GEN-LAST:event_backButtonMouseEntered

    private void backButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseExited

        // Change background
        backButton.setBackground(Color.WHITE);
    }//GEN-LAST:event_backButtonMouseExited

    private void cancelOrderButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelOrderButtonMouseEntered

        // Change background and text colour
        cancelOrderButton.setBackground(new Color(206, 171, 147));
        cancelOrderButton.setForeground(Color.BLACK);
    }//GEN-LAST:event_cancelOrderButtonMouseEntered

    private void cancelOrderButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelOrderButtonMouseExited

        // Change background and text colour
        cancelOrderButton.setBackground(Color.BLACK);
        cancelOrderButton.setForeground(Color.WHITE);
    }//GEN-LAST:event_cancelOrderButtonMouseExited

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed

        // Enable the parent frame
        MainPage.currentFrame.setEnabled(true);
        
        // Dispose the current pop up
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void cancelOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelOrderButtonActionPerformed

        // Show a message to confirm cancellation
        SystemPopUp confirmPopUp = new SystemPopUp(
                this,
                "Order Cancellation",
                "Do you wish to cancel the current order?",
                new String[]{"Cancel", "OK"}
        );
        confirmPopUp.setVisible(true);
        
        // Retrieve the status
        int confirmStatus = confirmPopUp.getStatus();
        
        // If user confirms to cancel order
        if (confirmStatus == 1) {
        
            // Perform cancellation
            int cancelStatus = currentOrder.customerCancelOrder();
            
            // Check the status
            if (cancelStatus == 1) {
                
                // Show message to inform cancellation is successful
                SystemPopUp successMessage = new SystemPopUp(
                        this,
                        "Order Cancelled Successfully",
                        "The current order is cancelled successfully. The payment has been refunded to your account.",
                        new String[]{"OK"}
                );
                successMessage.setVisible(true);
                
                // Update the parent panel and enable parent frame
                HomePanel.updateOrderPanel();
                MainPage.currentFrame.setEnabled(true);
                
                // Update the e-wallet panel
                EWalletPanel.refreshPanel();
                
                // Dispose the current frame
                dispose();
                
            } else {
            
                // Order cannot be cancelled. Declare a variable to store error message
                String errorString = null;
                
                // Generate different error message based on status code
                switch (cancelStatus) {
                    case 0 -> errorString = "The current order cannot be cancelled due to incorrect status. Please inspect code.";
                    case -1 -> errorString = "Notifications cannot be created. Please inspect code.";
                    case -2 -> errorString = "Transaction history cannot be created. Please inspect code.";
                }
                
                // Generate message to indicate failure in cancellation
                SystemPopUp errorMessage = new SystemPopUp(
                        this,
                        "Error in Cancellation",
                        errorString,
                        new String[]{"OK"}
                );
                errorMessage.setVisible(true);
            }
        }
    }//GEN-LAST:event_cancelOrderButtonActionPerformed

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
            java.util.logging.Logger.getLogger(OrderStatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderStatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderStatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderStatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Order order = Order.getOrderList().getFirst();
                new OrderStatusFrame(order).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelOrderButton;
    private javax.swing.JLabel diningMethodField;
    private javax.swing.JLabel diningMethodTitle;
    private javax.swing.JLabel itemOrderedTitle;
    private javax.swing.JPanel itemPanel;
    private javax.swing.JScrollPane itemScrollPane;
    private javax.swing.JLabel orderIDField;
    private javax.swing.JLabel orderIDTitle;
    private javax.swing.JLabel orderStatusTitle;
    private javax.swing.JLabel paymentField;
    private javax.swing.JLabel paymentTitle;
    private javax.swing.JTextArea stallNameField;
    private javax.swing.JScrollPane stallNameScrollPane;
    private javax.swing.JLabel stallNameTitle;
    private javax.swing.JLabel statusField;
    private javax.swing.JLabel statusTitle;
    private javax.swing.JLabel timeField;
    private javax.swing.JLabel timeTitle;
    // End of variables declaration//GEN-END:variables
}
