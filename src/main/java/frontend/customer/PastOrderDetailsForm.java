/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.customer;

import backend.entity.Feedback;
import backend.entity.Item;
import backend.entity.Order;
import frontend.pop_up.ReorderForm;
import frontend.pop_up.SystemPopUp;
import java.awt.Color;
import java.awt.Font;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jun Hong (TP068580), Beng Rhui (TP068495)
 */
public class PastOrderDetailsForm extends javax.swing.JFrame {

    public static JFrame currentFrame;
    private Order currentOrder;
    
    /**
     * Creates new form CartPopUp
     */
    public PastOrderDetailsForm(Order order) {
        
        // Set current frame and customer
        currentFrame = this;
        currentOrder = order;
        
        // Render GUI components
        initComponents();
        
        // Check if reorder button should be placed
        updateReorderButton();
        
        // Initialize the panel
        updateCartPanel();
    }
    
    /**
     * This method helps to check if reorder button should be placed. If stall is unavailable, we can't reorder stuffs, so reorder button should be removed in this case.
     */
    public void updateReorderButton() {
    
        // Check if the order still has a stall
        if (currentOrder.getOrderedStall() == null) {
            
            // Remove the reorder button and relocate the cancel button
            containerPanel.remove(reorderButton);
            
            // Reposition the cancel button
            containerPanel.remove(cancelButton);
            containerPanel.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 490, 180, 50));
        }
        
        // Refresh the container panel
        containerPanel.revalidate();
        containerPanel.repaint();
    }
    
    /**
     * This method helps to update the cart panel based on the associated order.
     */
    public void updateCartPanel() {
        
        // Remove everything in the panel
        orderedItemPanel.removeAll();
        
        // Retrieve the cart
        Map<Item, Integer> customerCart = currentOrder.getOrderItem();
        
        // Sort the cart based on item ID
        customerCart = customerCart.entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getKey().getItemID()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        // Check if the cart is empty, or if it only contains delivery fees
        if (customerCart.isEmpty() || (customerCart.size() == 1 && customerCart.containsKey(Item.deliveryFees))) {
            
            // Generate a label to panel
            JPanel emptyPanel = new JPanel(null);
            emptyPanel.setBackground(new Color(255, 251, 233));
            
            // Add a label to indicate that no order is available for vendor
            JLabel emptyLabel = new JLabel("No item has been placed for this order.");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            emptyLabel.setBounds(0, 0, 500, 50);
            
            // Add the components
            emptyPanel.add(emptyLabel);
            orderedItemPanel.add(emptyPanel);
            
        } else {
            
            // Loop through each item in the cart
            for (Map.Entry<Item, Integer> cartItem : customerCart.entrySet()) {

                // Retrieve the item and the associated quantity
                Item associatedItem = cartItem.getKey();
                int quantity = cartItem.getValue();

                // Create an item panel and add to the container panel
                CartItemPanel itemPanel = new CartItemPanel(associatedItem, quantity, false);
                orderedItemPanel.add(itemPanel);
            }

            // Check if the size of cart is less than 3
            if (customerCart.size() < 3) {
            
                // Loop through the index to create empty panels
                for (int i = customerCart.size() + 1; i <= 3; i++) {
                    
                    // Add an empty panel to the container
                    JPanel emptyPanel = new JPanel();
                    emptyPanel.setBackground(new Color(255, 251, 233));
                    orderedItemPanel.add(emptyPanel);
                }
            }
        }
        
        // Refresh the container panel
        orderedItemPanel.revalidate();
        orderedItemPanel.repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        containerPanel = new javax.swing.JPanel();
        orderDetailsTitle = new javax.swing.JLabel();
        itemOrderedTitle = new javax.swing.JLabel();
        quantityTitle = new javax.swing.JLabel();
        priceTitle = new javax.swing.JLabel();
        orderedItemScrollPane = new javax.swing.JScrollPane();
        orderedItemPanel = new javax.swing.JPanel();
        totalAmountPanel = new javax.swing.JPanel();
        totalAmountTitle = new javax.swing.JLabel();
        totalAmountField = new javax.swing.JLabel();
        diningMethodTitle = new javax.swing.JLabel();
        tableNumberLabel = new javax.swing.JLabel();
        diningMethodField = new javax.swing.JLabel();
        additionalNotesTitle = new javax.swing.JLabel();
        additionalNotesScrollPane = new javax.swing.JScrollPane();
        additionalNotesField = new javax.swing.JTextArea();
        cancelButton = new javax.swing.JButton();
        reorderButton = new javax.swing.JButton();
        provideFeedbackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Order Details");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        containerPanel.setBackground(new java.awt.Color(255, 251, 233));
        containerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        orderDetailsTitle.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        orderDetailsTitle.setText("Order Details - " + currentOrder.getOrderID());
        containerPanel.add(orderDetailsTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 620, -1));

        itemOrderedTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        itemOrderedTitle.setText("Item Ordered");
        containerPanel.add(itemOrderedTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 190, 30));

        quantityTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        quantityTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quantityTitle.setText("Quantity");
        containerPanel.add(quantityTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 140, 30));

        priceTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        priceTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceTitle.setText("Price");
        containerPanel.add(priceTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 140, 30));

        orderedItemScrollPane.setBorder(null);
        orderedItemScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        orderedItemScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        orderedItemPanel.setBackground(new java.awt.Color(255, 251, 233));
        orderedItemPanel.setLayout(new java.awt.GridLayout(0, 1));
        orderedItemScrollPane.setViewportView(orderedItemPanel);

        containerPanel.add(orderedItemScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 620, 260));

        totalAmountPanel.setBackground(new java.awt.Color(255, 251, 233));
        totalAmountPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        totalAmountPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        totalAmountTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        totalAmountTitle.setText("Total Amount");
        totalAmountPanel.add(totalAmountTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 220, 40));

        totalAmountField.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        totalAmountField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalAmountField.setText(String.format("RM%.2f", currentOrder.getOrderPrice()));
        totalAmountPanel.add(totalAmountField, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 140, 40));

        containerPanel.add(totalAmountPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 620, 60));

        diningMethodTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        diningMethodTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        diningMethodTitle.setText("Dining Method");
        containerPanel.add(diningMethodTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 140, -1));

        tableNumberLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tableNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tableNumberLabel.setText(currentOrder.getTableNumber() == null ? "" : "Table Number: " + currentOrder.getTableNumber());
        containerPanel.add(tableNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100, 160, 20));

        diningMethodField.setBackground(new java.awt.Color(255, 255, 255));
        diningMethodField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        diningMethodField.setText(currentOrder.getDiningType().toString());
        diningMethodField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), javax.swing.BorderFactory.createEmptyBorder(2, 15, 2, 15)));
        diningMethodField.setOpaque(true);
        containerPanel.add(diningMethodField, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 130, 310, 50));

        additionalNotesTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        additionalNotesTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        additionalNotesTitle.setText("Additional Notes for Vendor");
        containerPanel.add(additionalNotesTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, 310, -1));

        additionalNotesScrollPane.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        additionalNotesScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        additionalNotesScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        additionalNotesField.setEditable(false);
        additionalNotesField.setBackground(new java.awt.Color(255, 255, 255));
        additionalNotesField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        additionalNotesField.setLineWrap(true);
        additionalNotesField.setRows(5);
        additionalNotesField.setText(currentOrder.getNoteToVendor() == null ? "-" : currentOrder.getNoteToVendor());
        additionalNotesField.setWrapStyleWord(true);
        additionalNotesField.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 15, 12, 15));
        additionalNotesField.setCaretColor(new java.awt.Color(255, 255, 255));
        additionalNotesField.setCaretPosition(0);
        additionalNotesField.setSelectionColor(new java.awt.Color(255, 255, 255));
        additionalNotesScrollPane.setViewportView(additionalNotesField);

        containerPanel.add(additionalNotesScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 310, 230));

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
        containerPanel.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 490, 180, 50));

        reorderButton.setBackground(new java.awt.Color(0, 0, 0));
        reorderButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        reorderButton.setForeground(new java.awt.Color(255, 255, 255));
        reorderButton.setText("Reorder");
        reorderButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        reorderButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reorderButton.setFocusPainted(false);
        reorderButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reorderButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reorderButtonMouseExited(evt);
            }
        });
        reorderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reorderButtonActionPerformed(evt);
            }
        });
        containerPanel.add(reorderButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 490, 220, 50));

        provideFeedbackButton.setBackground(new java.awt.Color(0, 0, 0));
        provideFeedbackButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        provideFeedbackButton.setForeground(new java.awt.Color(255, 255, 255));
        provideFeedbackButton.setText("Provide Feedback");
        provideFeedbackButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        provideFeedbackButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        provideFeedbackButton.setFocusPainted(false);
        provideFeedbackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                provideFeedbackButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                provideFeedbackButtonMouseExited(evt);
            }
        });
        provideFeedbackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provideFeedbackButtonActionPerformed(evt);
            }
        });
        containerPanel.add(provideFeedbackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 490, 310, 50));

        getContentPane().add(containerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed

        // Enable the parent frame
        MainPage.currentFrame.setEnabled(true);

        // Dispose the current frame
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered

        // Change background
        cancelButton.setBackground(new Color(206, 171, 147));
    }//GEN-LAST:event_cancelButtonMouseEntered

    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited

        // Change background
        cancelButton.setBackground(Color.WHITE);
    }//GEN-LAST:event_cancelButtonMouseExited

    private void reorderButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reorderButtonMouseEntered

        // Change background and text
        reorderButton.setBackground(new Color(206, 171, 147));
        reorderButton.setForeground(Color.BLACK);
    }//GEN-LAST:event_reorderButtonMouseEntered

    private void reorderButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reorderButtonMouseExited

        // Change background and text
        reorderButton.setBackground(Color.BLACK);
        reorderButton.setForeground(Color.WHITE);
    }//GEN-LAST:event_reorderButtonMouseExited

    private void reorderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reorderButtonActionPerformed

        // Disable the current frame
        this.setEnabled(false);
        
        // Display the reorder form
        ReorderForm reorderForm = new ReorderForm(this, currentOrder);
        reorderForm.setLocationRelativeTo(this);
        reorderForm.setVisible(true);
    }//GEN-LAST:event_reorderButtonActionPerformed

    private void provideFeedbackButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_provideFeedbackButtonMouseEntered

        // Change background and text
        provideFeedbackButton.setBackground(new Color(206, 171, 147));
        provideFeedbackButton.setForeground(Color.BLACK);
    }//GEN-LAST:event_provideFeedbackButtonMouseEntered

    private void provideFeedbackButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_provideFeedbackButtonMouseExited

        // Change background and text
        provideFeedbackButton.setBackground(Color.BLACK);
        provideFeedbackButton.setForeground(Color.WHITE);
    }//GEN-LAST:event_provideFeedbackButtonMouseExited

    private void provideFeedbackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provideFeedbackButtonActionPerformed

        // Check if feedback has been filled before
        boolean vendorFeedbackRequired = Feedback.checkNeedToFillVendorFeedback(currentOrder);
        boolean runnerFeedbackRequired = Feedback.checkNeedToFillRunnerFeedback(currentOrder);
        
        // Inform user that feedback is not required if both feedback are filled
        if (!vendorFeedbackRequired && !runnerFeedbackRequired) {
            
            // Display a message
            SystemPopUp feedbackMessage = new SystemPopUp(
                    this,
                    "Feedback Filled",
                    "You have filled all the feedback for this order.",
                    new String[]{"OK"}
            );
            feedbackMessage.setVisible(true);
            
        } else {
        
            // Declare a variable to store status
            int feedbackStatus;
            
            // Based on the check, set different status
            if (vendorFeedbackRequired && runnerFeedbackRequired) feedbackStatus = 0;
            else if (vendorFeedbackRequired) feedbackStatus = 1;
            else if (runnerFeedbackRequired) feedbackStatus = 2;
            else throw new IllegalStateException("Illegal state for feedback status. Please inspect code.");
            
            // Disable the current frame
            setEnabled(false);

            // Call the feedback panel
            ProvideOrderFeedbackFrame feedbackFrame = new ProvideOrderFeedbackFrame(currentOrder, feedbackStatus);
            feedbackFrame.setLocationRelativeTo(this);
            feedbackFrame.setVisible(true);
        }
    }//GEN-LAST:event_provideFeedbackButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PastOrderDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PastOrderDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PastOrderDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PastOrderDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Order order = Order.getOrderList().getFirst();
                new PastOrderDetailsForm(order).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea additionalNotesField;
    private javax.swing.JScrollPane additionalNotesScrollPane;
    private javax.swing.JLabel additionalNotesTitle;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel containerPanel;
    private javax.swing.JLabel diningMethodField;
    private javax.swing.JLabel diningMethodTitle;
    private javax.swing.JLabel itemOrderedTitle;
    private javax.swing.JLabel orderDetailsTitle;
    private static javax.swing.JPanel orderedItemPanel;
    private javax.swing.JScrollPane orderedItemScrollPane;
    private javax.swing.JLabel priceTitle;
    private javax.swing.JButton provideFeedbackButton;
    private javax.swing.JLabel quantityTitle;
    private javax.swing.JButton reorderButton;
    private static javax.swing.JLabel tableNumberLabel;
    private static javax.swing.JLabel totalAmountField;
    private javax.swing.JPanel totalAmountPanel;
    private javax.swing.JLabel totalAmountTitle;
    // End of variables declaration//GEN-END:variables
}
