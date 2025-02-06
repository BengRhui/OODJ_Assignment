/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.customer;

import backend.entity.Customer;
import backend.entity.Item;
import backend.entity.Order;
import backend.entity.Stall;
import backend.utility.Utility;
import frontend.pop_up.SystemPopUp;
import frontend.pop_up.TableNumberPopUp;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author Jun Hong (TP068580), Beng Rhui (TP068495)
 */
public class CartPopUp extends javax.swing.JFrame {

    public static JFrame currentFrame;
    private static Customer currentCustomer;
    private static Order.DiningType diningType;
    private static String currentTableNumber;
    
    /**
     * Creates new form CartPopUp
     */
    public CartPopUp(Customer customer) {
        
        // Set current frame and customer
        currentFrame = this;
        currentCustomer = customer;
        
        // Render GUI components
        initComponents();
        
        // Initialize the components
        initializeComponents();
        
        // Initialize the panel
        updateCartPanel();
    }
    
    /**
     * This method helps to retrieve the current customer.
     * @return The customer associated
     */
    public static Customer getCustomer() {
        return currentCustomer;
    }
    
    /**
     * This method helps to initialize the components in the frame.
     */
    private void initializeComponents() {
        
        // Retrieve initial table number
        String initialTableNumber = StallDetailsPanel.getTableNumber();
        
        // Set the table number accordingly
        if (initialTableNumber != null) tableNumberLabel.setText("Table Number: " + StallDetailsPanel.getTableNumber());
        else tableNumberLabel.setText("");
        
        // Temporarily remove the action listener (so that no weird actions will be triggered)
        ActionListener[] listener = diningMethodDropDown.getActionListeners();
        for (ActionListener individualListener : listener) 
            diningMethodDropDown.removeActionListener(individualListener);
        
        // Set dining method
        if (StallDetailsPanel.getDiningType() != null) {
            diningMethodDropDown.setSelectedItem(StallDetailsPanel.getDiningType().toString());
            diningMethodDropDown.setForeground(Color.BLACK);
        }
        
        // Add back the listeners
        for (ActionListener individualListener : listener) 
            diningMethodDropDown.addActionListener(individualListener);
    }
    
    /**
     * This method helps to set the dining type of the current customer.
     * @param type The dining type selected by customer
     */
    public static void setDiningType(Order.DiningType type) {
        diningType = type;
    }
    
    /**
     * This method helps to set the table number to be appeared on the panel.
     * @param tableNumber The table number inputted by user
     */
    public static void setTableNumber(String tableNumber) {
        
        // Remove the table number prompt if the input is blank
        if (tableNumber == null) {
            currentTableNumber = null;
            tableNumberLabel.setText("");
        }
        
        // Set the table label if input is not blank
        else {
            currentTableNumber = tableNumber.toUpperCase();
            tableNumberLabel.setText("Table Number: " + tableNumber.toUpperCase());
        }
    }

    public static void updateCartPanel() {
        
        // Remove everything in the panel
        cartPanel.removeAll();
    
        // Depending on the dining type, update the cart
        currentCustomer.setDiningMethodInCart(diningType);
        
        // Retrieve the cart
        Map<String, Integer> customerCart = new TreeMap<>(currentCustomer.getCart());
        
        // Check if the cart is empty, or if it only contains delivery fees
        if (customerCart.isEmpty() || (customerCart.size() == 1 && customerCart.containsKey("I001"))) {
            
            // Generate a label to panel
            JPanel emptyPanel = new JPanel(null);
            emptyPanel.setBackground(new Color(255, 251, 233));
            
            // Add a label to indicate that no order is available for vendor
            JLabel emptyLabel = new JLabel("No item has been placed into your cart.");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            emptyLabel.setBounds(0, 0, 500, 50);
            
            // Add the components
            emptyPanel.add(emptyLabel);
            cartPanel.add(emptyPanel);
            
        } else {
            
            // Loop through each item in the cart
            for (Map.Entry<String, Integer> cartItem : customerCart.entrySet()) {

                // Retrieve the item and the associated quantity
                Item associatedItem = Item.getItem(cartItem.getKey());
                int quantity = cartItem.getValue();

                // Set include removable as true
                boolean includeRemovable = true;

                // If the item is delivery fees, remove the delete button
                if (cartItem.getKey().equals("I001")) includeRemovable = false;

                // Create an item panel and add to the container panel
                CartItemPanel itemPanel = new CartItemPanel(associatedItem, quantity, includeRemovable);
                cartPanel.add(itemPanel);
            }

            // Check if the size of cart is less than 3
            if (customerCart.size() < 3) {
            
                // Loop through the index to create empty panels
                for (int i = customerCart.size() + 1; i <= 3; i++) {
                    
                    // Add an empty panel to the container
                    JPanel emptyPanel = new JPanel();
                    emptyPanel.setBackground(new Color(255, 251, 233));
                    cartPanel.add(emptyPanel);
                }
            }
        }
        
        // Update the total amount
        totalAmountField.setText(
                String.format(
                        "RM%.2f",
                        Utility.getTotalAmountForCart(currentCustomer.getCart())
                )
        );
        
        // Refresh the container panel
        cartPanel.revalidate();
        cartPanel.repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cartTitle = new javax.swing.JLabel();
        itemOrderedTitle = new javax.swing.JLabel();
        quantityTitle = new javax.swing.JLabel();
        priceTitle = new javax.swing.JLabel();
        cartScrollPane = new javax.swing.JScrollPane();
        cartPanel = new javax.swing.JPanel();
        totalAmountPanel = new javax.swing.JPanel();
        totalAmountTitle = new javax.swing.JLabel();
        totalAmountField = new javax.swing.JLabel();
        diningMethodTitle = new javax.swing.JLabel();
        tableNumberLabel = new javax.swing.JLabel();
        diningMethodDropDown = new javax.swing.JComboBox<>(
            Order.DiningType.DINING_OPTIONS
        );
        additionalNotesTitle = new javax.swing.JLabel();
        additionalNotesScrollPane = new javax.swing.JScrollPane();
        additionalNotesField = new javax.swing.JTextArea();
        cancelButton = new javax.swing.JButton();
        paymentButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 251, 233));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cartTitle.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        cartTitle.setText("Cart");
        jPanel1.add(cartTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 110, -1));

        itemOrderedTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        itemOrderedTitle.setText("Item Ordered");
        jPanel1.add(itemOrderedTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 190, 30));

        quantityTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        quantityTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quantityTitle.setText("Quantity");
        jPanel1.add(quantityTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 140, 30));

        priceTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        priceTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceTitle.setText("Price");
        jPanel1.add(priceTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 140, 30));

        cartScrollPane.setBorder(null);
        cartScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        cartScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        cartPanel.setBackground(new java.awt.Color(255, 251, 233));
        cartPanel.setLayout(new java.awt.GridLayout(0, 1));
        cartScrollPane.setViewportView(cartPanel);

        jPanel1.add(cartScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 620, 260));

        totalAmountPanel.setBackground(new java.awt.Color(255, 251, 233));
        totalAmountPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        totalAmountPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        totalAmountTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        totalAmountTitle.setText("Total Amount");
        totalAmountPanel.add(totalAmountTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 220, 40));

        totalAmountField.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        totalAmountField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalAmountField.setText(String.format("RM%.2f", Utility.getTotalAmountForCart(currentCustomer.getCart())));
        totalAmountPanel.add(totalAmountField, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 140, 40));

        jPanel1.add(totalAmountPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 620, 60));

        diningMethodTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        diningMethodTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        diningMethodTitle.setText("Dining Method");
        jPanel1.add(diningMethodTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 140, -1));

        tableNumberLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tableNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tableNumberLabel.setText("Table Number: T001");
        jPanel1.add(tableNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100, 160, 20));

        diningMethodDropDown.setBackground(Color.WHITE);
        diningMethodDropDown.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        diningMethodDropDown.setForeground(new java.awt.Color(204, 204, 204));
        diningMethodDropDown.setSelectedIndex(-1);
        diningMethodDropDown.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        diningMethodDropDown.setFocusable(false);
        diningMethodDropDown.setOpaque(true);
        diningMethodDropDown.setRenderer(new DefaultListCellRenderer() {

            // Override the rendering method
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (index == -1 && value == null) {
                    label.setText("Select Dining Method Here");
                    label.setForeground(new Color(204, 204, 204));
                } else {
                    label.setForeground(Color.BLACK);
                }

                // Apply your existing custom rendering (size and border)
                label.setPreferredSize(new Dimension(label.getPreferredSize().width, 40));
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                return label;
            }
        });
        diningMethodDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diningMethodDropDownActionPerformed(evt);
            }
        });
        jPanel1.add(diningMethodDropDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 130, 310, 50));

        additionalNotesTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        additionalNotesTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        additionalNotesTitle.setText("Additional Notes for Vendor");
        jPanel1.add(additionalNotesTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, 310, -1));

        additionalNotesScrollPane.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        additionalNotesScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        additionalNotesScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        additionalNotesField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        additionalNotesField.setForeground(new java.awt.Color(204, 204, 204));
        additionalNotesField.setLineWrap(true);
        additionalNotesField.setRows(5);
        additionalNotesField.setText("Enter Notes Here");
        additionalNotesField.setWrapStyleWord(true);
        additionalNotesField.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 15, 12, 15));
        additionalNotesField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                additionalNotesFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                additionalNotesFieldFocusLost(evt);
            }
        });
        additionalNotesField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                additionalNotesFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                additionalNotesFieldKeyTyped(evt);
            }
        });
        additionalNotesScrollPane.setViewportView(additionalNotesField);

        jPanel1.add(additionalNotesScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 310, 230));

        cancelButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jPanel1.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 490, 180, 50));

        paymentButton.setBackground(new java.awt.Color(0, 0, 0));
        paymentButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        paymentButton.setForeground(new java.awt.Color(255, 255, 255));
        paymentButton.setText("Pay via E-Wallet");
        paymentButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        paymentButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        paymentButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                paymentButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                paymentButtonMouseExited(evt);
            }
        });
        paymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentButtonActionPerformed(evt);
            }
        });
        jPanel1.add(paymentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 490, 310, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void diningMethodDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diningMethodDropDownActionPerformed

        // Change the text colour to black
        diningMethodDropDown.setForeground(Color.BLACK);
        
        // Update dining method
        String diningTypeString = diningMethodDropDown.getSelectedItem().toString();
        Order.DiningType diningMethod = Order.DiningType.getFromString(diningTypeString);
        
        // If the dining type is dine in, request for table number
        if (diningMethod == Order.DiningType.DINE_IN) {
        
            // Disable the pop up
            setEnabled(false);
            
            // Call the pop up
            TableNumberPopUp tableNumberPopUp = new TableNumberPopUp(this, currentTableNumber);
            tableNumberPopUp.setLocationRelativeTo(this);
            tableNumberPopUp.setVisible(true);
            
            // If user clicks OK
            if (tableNumberPopUp.status == 1) {
                
                // Retrieve the inputted table number
                String inputTableNumber = tableNumberPopUp.retrieveTableNumber();

                // Update the table number
                setTableNumber(inputTableNumber);
                
                // Set dining type
                setDiningType(diningMethod);
                
            } else {
            
                // Revert back to the previous item, case where no dining type is set yet
                if (diningType == null) {
                    diningMethodDropDown.setSelectedIndex(-1);
                    diningMethodDropDown.setForeground(new Color(204, 204, 204));
                    
                } else {
                    
                    // Revert back to the previous dining type if it exists
                    diningMethodDropDown.setSelectedItem(diningType.toString());
                }
            }
            
        } else {
            
            // Remove the table number
            currentTableNumber = null;
            
            // Set the table number
            setTableNumber(null);
            
            // Set the dining type
            setDiningType(diningMethod);
        }
        
        // Update cart
        updateCartPanel();
    }//GEN-LAST:event_diningMethodDropDownActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed

        // Enable the parent frame
        MainPage.currentFrame.setEnabled(true);
        
        // Set the dining method
        if (diningMethodDropDown.getSelectedIndex() != -1) {
            
            // Save the types to the parent panel
            StallDetailsPanel.setDiningType(
                    Order.DiningType.getFromString(
                            diningMethodDropDown.getSelectedItem().toString()
                    )
            );
        
            // Also save the table number
            StallDetailsPanel.setTableNumber(currentTableNumber);
        }
        
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

    private void paymentButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentButtonMouseEntered

        // Change background and text
        paymentButton.setBackground(new Color(206, 171, 147));
        paymentButton.setForeground(Color.BLACK);
    }//GEN-LAST:event_paymentButtonMouseEntered

    private void paymentButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentButtonMouseExited

        // Change background and text
        paymentButton.setBackground(Color.BLACK);
        paymentButton.setForeground(Color.WHITE);
    }//GEN-LAST:event_paymentButtonMouseExited

    private void paymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentButtonActionPerformed

        // Retrieve information
        Stall currentStall = StallDetailsPanel.getCurrentStall();
        String additionalNotes = (additionalNotesField.getText().equals("Enter Notes Here") || additionalNotesField.getText().isBlank()) ? 
                "-" : 
                additionalNotesField.getText().strip();
        
        // Place the order
        int placeStatus = currentCustomer.placeOrder(currentStall, currentCustomer.getCart(), diningType, additionalNotes, currentTableNumber);
        
        // If order is placed successfully
        if (placeStatus == 1) {
        
            // Create a message to inform that order is places successfully
            SystemPopUp successMessage = new SystemPopUp(
                    this,
                    "Order Placed Successfully",
                    "Your order has been placed successfully. You may return to the home page to track your order details.",
                    new String[]{"OK"}
            );
            successMessage.setVisible(true);
            
            // Enable the parent frame
            MainPage.currentFrame.setEnabled(true);
            
            // Update the order panel
            HomePanel.updateOrderPanel();
            
            // Update the e-wallet panel
            EWalletPanel.refreshPanel();
            
            // Dispose the cart panel
            dispose();
            
        } else {
        
            // Declare a variable to store error message
            String errorString = null;
            
            // Generate error message based on different error code
            switch (placeStatus) {
                case 0 -> errorString = "Cart is empty. Please add items before placing your order.";
                case -1 -> errorString = "Insufficient e-wallet balance. Please top up before proceeding with the order.";
                case -2 -> errorString = "Sorry, there is no available delivery runners now. Please change your dining method or try again later.";
                case -3 -> errorString = "Notification failed to be created. Please inspect code.";
                case -4 -> errorString = "Transaction history failed to be created. Please inspect code.";
                
                // Throw exception if status is invalid
                default -> throw new IllegalStateException("Invalid error status. Please inspect code.");
            }
            
            // Call the pop up
            SystemPopUp errorMessage = new SystemPopUp(
                    this,
                    "Error in Placing Order",
                    errorString,
                    new String[]{"OK"}
            );
            errorMessage.setVisible(true);
        }
        
    }//GEN-LAST:event_paymentButtonActionPerformed

    private void additionalNotesFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_additionalNotesFieldFocusGained

        // If the prompt exists
        if (additionalNotesField.getText().equals("Enter Notes Here")) {
            
            // Remove the prompt
            additionalNotesField.setText("");
            
            // Change the text colour to black
            additionalNotesField.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_additionalNotesFieldFocusGained

    private void additionalNotesFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_additionalNotesFieldFocusLost

        // If the input is empty
        if (additionalNotesField.getText().isBlank()) {
            
            // Set the prompt
            additionalNotesField.setText("Enter Notes Here");
            
            // Change the text colour to grey
            additionalNotesField.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_additionalNotesFieldFocusLost

    private void additionalNotesFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_additionalNotesFieldKeyTyped

        // If the key entered is ";"
        if (evt.getKeyChar() == ';') {
            
            // Remove it
            evt.consume();
        }
    }//GEN-LAST:event_additionalNotesFieldKeyTyped

    private void additionalNotesFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_additionalNotesFieldKeyPressed

        // If enter key is pressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        
            // Remove it
            evt.consume();
        }
    }//GEN-LAST:event_additionalNotesFieldKeyPressed

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
            java.util.logging.Logger.getLogger(CartPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CartPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CartPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CartPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CartPopUp(currentCustomer).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea additionalNotesField;
    private javax.swing.JScrollPane additionalNotesScrollPane;
    private javax.swing.JLabel additionalNotesTitle;
    private javax.swing.JButton cancelButton;
    private static javax.swing.JPanel cartPanel;
    private javax.swing.JScrollPane cartScrollPane;
    private javax.swing.JLabel cartTitle;
    private static javax.swing.JComboBox<String> diningMethodDropDown;
    private javax.swing.JLabel diningMethodTitle;
    private javax.swing.JLabel itemOrderedTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton paymentButton;
    private javax.swing.JLabel priceTitle;
    private javax.swing.JLabel quantityTitle;
    private static javax.swing.JLabel tableNumberLabel;
    private static javax.swing.JLabel totalAmountField;
    private javax.swing.JPanel totalAmountPanel;
    private javax.swing.JLabel totalAmountTitle;
    // End of variables declaration//GEN-END:variables
}
