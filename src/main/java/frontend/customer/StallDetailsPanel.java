/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.customer;

import backend.entity.Customer;
import backend.entity.Item;
import backend.entity.Order;
import backend.entity.Stall;
import frontend.pop_up.FeedbackPopUp;
import frontend.pop_up.ItemQuantityPopUp;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author limbengrhui
 */
public class StallDetailsPanel extends javax.swing.JPanel {

    private static Stall currentStall;
    private static Order.DiningType selectedDiningType;
    private static String currentTableNumber;

    /**
     * Creates new form ShopDetailsPanel
     */
    public StallDetailsPanel(Stall stall) {

        // Clear the cart
        MainPage.getCustomer().clearCustomerCart();
        
        // Retrieve the current stall
        currentStall = stall;

        // Render GUI components
        initComponents();
        
        // Initialize panel
        initializePanel();
    }
    
    /**
     * This method helps to retrieve the current stall associated.
     * @return The stall associated with the panel
     */
    public static Stall getCurrentStall() {
        return currentStall;
    }
    
    public static void setDiningType(Order.DiningType diningType) {
        selectedDiningType = diningType;
    }
    
    public static Order.DiningType getDiningType() {
        return selectedDiningType;
    }
    
    public static void setTableNumber(String tableNumber) {
        currentTableNumber = tableNumber;
    }
    
    public static String getTableNumber() {
        return currentTableNumber;
    }
    
    /**
     * This method helps to initialize the panels.
     */
    private void initializePanel() {
    
        // Remove all componentd from the panel
        itemPanel.removeAll();
        
        // Retrieve the list of items of the stall
        ArrayList<Item> itemList = Item.getItemList(currentStall);
        
        // If the item list is empty
        if (itemList.isEmpty()) {
            
            // Generate a label to panel
            JPanel emptyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            
            // Add a label to indicate that no order is available for vendor
            JLabel emptyLabel = new JLabel("No item available for the stall.");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            
            // Add the components
            emptyPanel.add(emptyLabel);
            itemPanel.add(emptyPanel);
            
        } else {
            
            // Loop through the item list
            for (Item item : itemList) {
                
                // Create item details panel
                ItemDetailsPanel detailsPanel = new ItemDetailsPanel(item);

                // Add cursor to the panel
                detailsPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                
                // Add mouse listener to the panel
                detailsPanel.addMouseListener(new MouseListener(){
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        // Disable the main frame
                        MainPage.currentFrame.setEnabled(false);
                        
                        // Call item quantity pop up
                        ItemQuantityPopUp quantityPopUp = new ItemQuantityPopUp(MainPage.currentFrame);
                        quantityPopUp.setLocationRelativeTo(MainPage.currentFrame);
                        quantityPopUp.setVisible(true);
     
                        // Retrieve the quantity selected once done
                        int quantitySelected = quantityPopUp.retrieveQuantity();
                        
                        // If the quantity is not an invalid quantity
                        if (quantitySelected != -1) {
                            
                            // Add the item to cart
                            Customer currentCustomer = MainPage.getCustomer();
                            currentCustomer.addItemToCart(item, quantitySelected);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {}

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {}

                    @Override
                    public void mouseExited(MouseEvent e) {}
                });

                // Add the details panel to the overall panel
                itemPanel.add(detailsPanel);
            }
            
            // If the size of the list is smaller than 2
            if (itemList.size() < 3) {
                
                // Loop through the index that hasn't have a panel
                for (int i = itemList.size() + 1; i <= 3; i++) {
                    
                    // Add an empty panel to the container
                    JPanel emptyPanel = new JPanel();
                    emptyPanel.setBackground(new Color(255, 251, 233));
                    
                    itemPanel.add(emptyPanel);
                }
            }
        }
        
        // Refresh the overall panel after modifications
        itemPanel.revalidate();
        itemPanel.repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stallNameScrollPane = new javax.swing.JScrollPane();
        stallNameField = new javax.swing.JTextArea();
        viewFeedbackButton = new javax.swing.JButton();
        checkCartButton = new javax.swing.JButton();
        itemScrollPane = new javax.swing.JScrollPane();
        itemPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 251, 233));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stallNameScrollPane.setBackground(new java.awt.Color(255, 251, 233));
        stallNameScrollPane.setBorder(null);
        stallNameScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        stallNameScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        stallNameField.setBackground(new java.awt.Color(255, 251, 233));
        stallNameField.setColumns(20);
        stallNameField.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        stallNameField.setRows(1);
        stallNameField.setText(currentStall.getStallName());
        stallNameScrollPane.setViewportView(stallNameField);

        add(stallNameScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 760, 60));

        viewFeedbackButton.setBackground(Color.WHITE);
        viewFeedbackButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        viewFeedbackButton.setText("View Feedback");
        viewFeedbackButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        viewFeedbackButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewFeedbackButton.setFocusPainted(false);
        viewFeedbackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewFeedbackButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewFeedbackButtonMouseExited(evt);
            }
        });
        viewFeedbackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewFeedbackButtonActionPerformed(evt);
            }
        });
        add(viewFeedbackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 190, 60));

        checkCartButton.setBackground(new java.awt.Color(0, 0, 0));
        checkCartButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        checkCartButton.setForeground(new java.awt.Color(255, 255, 255));
        checkCartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/cart_icon.png"))); // NOI18N
        checkCartButton.setText("  Check Cart Here");
        checkCartButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkCartButton.setFocusPainted(false);
        checkCartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkCartButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                checkCartButtonMouseExited(evt);
            }
        });
        checkCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCartButtonActionPerformed(evt);
            }
        });
        add(checkCartButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 40, 270, 60));

        itemScrollPane.setBorder(null);
        itemScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        itemScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        itemPanel.setBackground(new java.awt.Color(255, 251, 233));
        itemPanel.setLayout(new java.awt.GridLayout(0, 3, 20, 20));
        itemScrollPane.setViewportView(itemPanel);

        add(itemScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 1260, 400));
    }// </editor-fold>//GEN-END:initComponents

    private void checkCartButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkCartButtonMouseEntered

        // Change background
        checkCartButton.setBackground(new Color(153, 76, 0));
    }//GEN-LAST:event_checkCartButtonMouseEntered

    private void checkCartButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkCartButtonMouseExited

        // Change background
        checkCartButton.setBackground(Color.BLACK);
    }//GEN-LAST:event_checkCartButtonMouseExited

    private void checkCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCartButtonActionPerformed

        // Disable current frame
        MainPage.currentFrame.setEnabled(false);
        
        // Display the cart frame
        CartPopUp cartPopUp = new CartPopUp(MainPage.getCustomer());
        cartPopUp.setLocationRelativeTo(MainPage.currentFrame);
        cartPopUp.setVisible(true);
    }//GEN-LAST:event_checkCartButtonActionPerformed

    private void viewFeedbackButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewFeedbackButtonMouseEntered
        
        // Change background
        viewFeedbackButton.setBackground(new Color(206, 171, 147));
    }//GEN-LAST:event_viewFeedbackButtonMouseEntered

    private void viewFeedbackButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewFeedbackButtonMouseExited

        // Change background
        viewFeedbackButton.setBackground(Color.WHITE);
    }//GEN-LAST:event_viewFeedbackButtonMouseExited

    private void viewFeedbackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewFeedbackButtonActionPerformed

        // Call the notification panel
        FeedbackPopUp feedbackPopUp = new FeedbackPopUp(currentStall);
        feedbackPopUp.setLocationRelativeTo(MainPage.currentFrame);
        feedbackPopUp.setVisible(true);
    }//GEN-LAST:event_viewFeedbackButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkCartButton;
    private javax.swing.JPanel itemPanel;
    private javax.swing.JScrollPane itemScrollPane;
    private javax.swing.JTextArea stallNameField;
    private javax.swing.JScrollPane stallNameScrollPane;
    private javax.swing.JButton viewFeedbackButton;
    // End of variables declaration//GEN-END:variables
}
