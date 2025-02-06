/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.customer;

import backend.entity.Item;
import backend.entity.Order;
import backend.entity.Order.OrderStatus;
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
public class OrderDetailsPanel extends javax.swing.JPanel {

    private Order currentOrder;
    /**
     * Creates new form OrderDetailsPanel
     */
    public OrderDetailsPanel(Order order) {
        
        // Set current order
        currentOrder = order;
        
        // Render GUI components
        initComponents();
        
        // Change background and border if status is pending change
        if (currentOrder.getOrderStatus() == OrderStatus.PENDING_CHANGE) {
            
            // Change background
            Color redColour = new Color(222, 154, 127);
            setBackground(redColour);
            stallNameField.setBackground(redColour);
            itemPanel.setBackground(redColour);
            
            // Change border
            setBorder(BorderFactory.createLineBorder(new Color(140, 33, 19), 2, true));
        }
        
        // Initialize item panel
        initializeItemPanel();
    }
    
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
            int panelWidth = 420;
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

        stallNameScrollPane = new javax.swing.JScrollPane();
        stallNameField = new javax.swing.JTextArea();
        viewIcon = new javax.swing.JLabel();
        statusTitle = new javax.swing.JLabel();
        statusField = new javax.swing.JLabel();
        diningMethodTitle = new javax.swing.JLabel();
        diningMethodField = new javax.swing.JLabel();
        itemTitle = new javax.swing.JLabel();
        itemScrollPane = new javax.swing.JScrollPane();
        itemPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(227, 202, 165));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setMaximumSize(new java.awt.Dimension(620, 280));
        setMinimumSize(new java.awt.Dimension(620, 280));
        setName("detailsPanel"); // NOI18N
        setPreferredSize(new java.awt.Dimension(620, 280));
        setSize(new java.awt.Dimension(620, 280));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stallNameScrollPane.setBorder(null);
        stallNameScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        stallNameScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        stallNameField.setEditable(false);
        stallNameField.setBackground(new java.awt.Color(227, 202, 165));
        stallNameField.setColumns(20);
        stallNameField.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        stallNameField.setRows(1);
        stallNameField.setText(currentOrder.getOrderedStall().getStallName());
        stallNameField.setBorder(null);
        stallNameField.setCaretColor(new java.awt.Color(227, 202, 165));
        stallNameField.setCaretPosition(0);
        stallNameField.setSelectionColor(new java.awt.Color(227, 202, 165));
        stallNameScrollPane.setViewportView(stallNameField);

        add(stallNameScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 500, 30));

        viewIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/search_icon.png"))); // NOI18N
        viewIcon.setFocusable(false);
        add(viewIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 50, 40));

        statusTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        statusTitle.setText("Status");
        statusTitle.setFocusable(false);
        add(statusTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 80, -1));

        statusField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        statusField.setText(currentOrder.getOrderStatus().toString());
        statusField.setFocusable(false);
        add(statusField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 390, -1));

        diningMethodTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        diningMethodTitle.setText("Dining Method");
        diningMethodTitle.setFocusable(false);
        add(diningMethodTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 140, -1));

        diningMethodField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        diningMethodField.setText(currentOrder.getDiningType().toString());
        diningMethodField.setFocusable(false);
        add(diningMethodField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 390, -1));

        itemTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        itemTitle.setText("Item");
        itemTitle.setFocusable(false);
        add(itemTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 140, -1));

        itemScrollPane.setBorder(null);
        itemScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        itemScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        itemPanel.setBackground(new java.awt.Color(227, 202, 165));
        itemPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemPanelMouseClicked(evt);
            }
        });
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 10);
        flowLayout1.setAlignOnBaseline(true);
        itemPanel.setLayout(flowLayout1);
        itemScrollPane.setViewportView(itemPanel);

        add(itemScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 420, 80));
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

        // Disable the parent frame
        MainPage.currentFrame.setEnabled(false);
        
        // Display the pop up
        OrderStatusFrame orderPopUp = new OrderStatusFrame(currentOrder);
        orderPopUp.setLocationRelativeTo(MainPage.currentFrame);
        orderPopUp.setVisible(true);
    }//GEN-LAST:event_formMouseClicked

    private void itemPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemPanelMouseClicked
       
        // Perform the same action as the panel clicked
        formMouseClicked(evt);
    }//GEN-LAST:event_itemPanelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel diningMethodField;
    private javax.swing.JLabel diningMethodTitle;
    private javax.swing.JPanel itemPanel;
    private javax.swing.JScrollPane itemScrollPane;
    private javax.swing.JLabel itemTitle;
    private javax.swing.JTextArea stallNameField;
    private javax.swing.JScrollPane stallNameScrollPane;
    private javax.swing.JLabel statusField;
    private javax.swing.JLabel statusTitle;
    private javax.swing.JLabel viewIcon;
    // End of variables declaration//GEN-END:variables
}
