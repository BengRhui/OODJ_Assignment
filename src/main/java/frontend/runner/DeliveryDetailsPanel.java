/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.runner;

import backend.entity.Item;
import backend.entity.Order;
import static backend.entity.Order.OrderStatus.READY_FOR_PICK_UP;
import static backend.entity.Order.OrderStatus.RUNNER_DELIVERY;
import static backend.entity.Order.OrderStatus.WAITING_RUNNER;
import static backend.entity.Order.OrderStatus.WAITING_VENDOR;
import static backend.entity.Order.OrderStatus.WAITING_VENDOR_AND_RUNNER;
import backend.file_io.PictureIO;
import backend.utility.Utility;
import frontend.pop_up.SystemPopUp;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author Jun Hong (TP068580), Beng Rhui (TP068495)
 */
public class DeliveryDetailsPanel extends javax.swing.JPanel {

    private Order currentOrder;
    
    /**
     * Creates new form DeliveryDetailsPanel
     */
    public DeliveryDetailsPanel(Order order) {
        
        // Initialize the order
        currentOrder = order;
        
        // Render GUI components
        initComponents();
        
        // Add items to panel
        addItemsIntoGUI();
        
        // Update buttons for the panel
        setActionsForOrder();
    }

    
    /**
     * The method that helps to add item information into the GUI.
     */
    private void addItemsIntoGUI() {

        // Initialize a few variables to help in coding
        Map<Item, Integer> itemMap = currentOrder.getOrderItem();
        ArrayList<JLabel> jLabelList = new ArrayList<>();

        // Loop through each item pair
        for (Map.Entry<Item, Integer> entry : itemMap.entrySet()) {

            // Continue the loop if the item is null
            if (entry.getKey() == null) continue;

            // Retrieve the item name and quantity of the pair
            String itemName = entry.getKey().getItemName();
            int quantity = entry.getValue();

            // Ignore delivery fees
            if (itemName.equalsIgnoreCase("Delivery fees")) continue;

            // Create a string that will be displayed
            String displayInfo = itemName + " x " + quantity;

            // Create a JLabel consisting of the information
            JLabel itemLabel = new JLabel(displayInfo);
            itemLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 1, true),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20))
            );
            itemLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            itemLabel.setBackground(Color.WHITE);
            itemLabel.setOpaque(true);

            // Add the JLabel into the flow layout
            itemOverallPanel.add(itemLabel);

            // Add JLabel into list (to calculate the number of line it'll span)
            jLabelList.add(itemLabel);
        }

        // If there is no order involved (should not happen but included just in case)
        if (jLabelList.isEmpty()) {

            // Change the layout of the food ordered panel
            itemOverallPanel.setLayout(null);
            itemOverallPanel.setPreferredSize(new Dimension(300, 100));

            // Generate an empty label to state that the item is unavailable
            JLabel emptyDescriptionLabel = new JLabel("Unavailable item.");
            emptyDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            emptyDescriptionLabel.setBounds(0, 0, 200, 20);

            // Add the empty label to the container panel and return it
            itemOverallPanel.add(emptyDescriptionLabel);

        } else {

            // Calculate and set the dimension of the panel
            int rowsInvolved = getSpanningRowNum(jLabelList);
            int panelWidth = 450;
            int panelHeight = (rowsInvolved + 1) * 10 + jLabelList.getFirst().getPreferredSize().height * rowsInvolved;

            // Based on the calculations, set the sizes for the components
            itemOverallPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
            itemOverallPanel.setBounds(0, 0, panelWidth, panelHeight);
            itemScrollPane.setPreferredSize(new Dimension(panelWidth, panelHeight));
        }
    }

    /**
     * This method helps to calculate the number of rows that the flow layout will span.
     * @return The number of rows spanned
     */
    private static int getSpanningRowNum(ArrayList<JLabel> jLabelList) {

        // Initialize the variables
        int panelWidth = 450;
        int marginWidth = 10;
        int rowCount = 1;
        int currentWidth = 20;

        // Loop through each label
        for (JLabel label : jLabelList) {

            // Obtain the width of the label
            int labelWidth = label.getPreferredSize().width;

            // Check if the current label can fit into the current row
            if (currentWidth + labelWidth > panelWidth) {

                // If nope, increase the row count and reset the row width
                currentWidth = labelWidth + 40;
                rowCount++;

                // Continue the loop (margin has been added: 20 + 20)
                continue;

            } else {

                // If yes, add the width to current width
                currentWidth += labelWidth;
            }

            // Now check if the margin can fit into the current row
            if (currentWidth + marginWidth > panelWidth) {

                // If nope, reset the row width and go to the next row
                currentWidth = 20;
                rowCount++;

                // Continue the loop for the next label
                continue;
            }

            // If yes, the margin is added to the row
            currentWidth += marginWidth;
        }

        // Return the final number of rows
        return rowCount;
    }

    /**
     * This method set the panels for different actions.
     */
    private void setActionsForOrder() {

        // Get the status of the current order
        Order.OrderStatus status = currentOrder.getOrderStatus();

        // Remove the existing components on the action panel
        actionPanel.removeAll();

        // Go through different status
        switch (status) {
            
            // Different status for different options
            case WAITING_VENDOR_AND_RUNNER, WAITING_RUNNER -> actionPanel.add(createPanelForAcceptOrder());
            case WAITING_VENDOR -> actionPanel.add(createPanelForWaitingConfirmation());
            case VENDOR_PREPARING, READY_FOR_PICK_UP -> actionPanel.add(createPanelForPendingPickUp());
            case RUNNER_DELIVERY -> actionPanel.add(createPanelForCompleteDelivery());

            // Throw an exception if the status is incorrect
            default -> throw new IllegalArgumentException("Current status is not allowed here.");
        }
    }
    
    /**
     * This method helps create the panels for orders that are pending to be accepted.
     * @return A JPanel consisting of "Accept" and "Decline" button
     */
    private JPanel createPanelForAcceptOrder() {

        // Declare a new variable (paintComponent added to ensure transparency works well)
        JPanel newPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };

        // Set the attributes for the new panel
        newPanel.setBackground(new Color(0, 0, 0, 0));
        newPanel.setLayout(null);
        newPanel.setBounds(0, 5, 350, 70);
        newPanel.setPreferredSize(actionPanel.getPreferredSize());

        // Prepare the picture icons
        File[] directory = new File(PictureIO.PARENT_PATH_TO_SYSTEM_DIRECTORY).listFiles();
        File acceptButtonPicture = Utility.retrieveFileWithoutExtension(directory, "tick_icon");
        File rejectButtonPicture = Utility.retrieveFileWithoutExtension(directory, "cross_icon");
        ImageIcon acceptButtonIcon = new ImageIcon(acceptButtonPicture.getAbsolutePath());
        ImageIcon rejectButtonIcon = new ImageIcon(rejectButtonPicture.getAbsolutePath());

        // Create the accept button
        JButton acceptButton = new JButton("  Accept");
        acceptButton.setBounds(0, 5, 170, 50);
        acceptButton.setFont(new Font("Arial", Font.BOLD, 18));
        acceptButton.setIcon(acceptButtonIcon);
        acceptButton.setForeground(Color.WHITE);
        acceptButton.setBackground(new Color(0, 102, 0));
        acceptButton.setFocusable(false);
        acceptButton.setFocusPainted(false);
        acceptButton.setContentAreaFilled(false);
        acceptButton.setBorderPainted(false);
        acceptButton.setOpaque(true);

        // Add an action listener to the button
        acceptButton.addActionListener(e -> {

            // Display message before accepting order
            SystemPopUp confirmAcceptOrder = new SystemPopUp(
                    MainPage.currentFrame,
                    "Accept Order",
                    "Are you sure that you wish to accept this order?<br>Once accept, you will not be able to cancel this order.",
                    new String[]{"No", "Yes"}
            );
            confirmAcceptOrder.setVisible(true);

            // If user choose "Yes", current order is accepted
            if (confirmAcceptOrder.getStatus() == 1) currentOrder.runnerAcceptOrder();

            // Refresh the order panel at the home page
            HomePagePanel.updatePanel();
        });

        // Add cursor to button
        acceptButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Create decline button
        JButton declineButton = new JButton("  Decline");
        declineButton.setBounds(180, 5, 170, 50);
        declineButton.setFont(new Font("Arial", Font.BOLD, 18));
        declineButton.setIcon(rejectButtonIcon);
        declineButton.setForeground(Color.WHITE);
        declineButton.setBackground(new Color(224, 46, 56));
        declineButton.setFocusable(false);
        declineButton.setFocusPainted(false);
        declineButton.setContentAreaFilled(false);
        declineButton.setBorderPainted(false);
        declineButton.setOpaque(true);

        // Add action listener to decline button
        declineButton.addActionListener(e -> {

            // Display message to confirm that runner wishes to cancel order
            SystemPopUp confirmDeclineOrder = new SystemPopUp(
                    MainPage.currentFrame,
                    "Decline Order",
                    "Are you sure that you would like to decline the order?<br>You will not be able to revert this operation.",
                    new String[]{"No", "Yes"}
            );
            confirmDeclineOrder.setVisible(true);

            // If yes, runner cancels the order
            if (confirmDeclineOrder.getStatus() == 1) currentOrder.runnerRejectOrder();

            // Refresh the order panel
            HomePagePanel.updatePanel();
            
            // Update the availability button
            HomePagePanel.updateAvailabilityButton();
        });

        // Add cursor to button
        declineButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add buttons into the panel and return it
        newPanel.add(acceptButton);
        newPanel.add(declineButton);
        return newPanel;
    }

    
    /**
     * This method helps to create a JPanel to indicate that the order is pending waiting from vendor.
     * @return A JPanel containing words indicating that order is pending for the vendor
     */
    private JPanel createPanelForWaitingConfirmation() {

        // Create a new panel (paint component for transparency)
        JPanel newPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        newPanel.setBackground(new Color(0, 0, 0, 0));;
        newPanel.setLayout(null);
        newPanel.setBounds(0, 0, 350, 60);
        newPanel.setPreferredSize(actionPanel.getPreferredSize());

        // Retrieve the wait icon file
        File[] directory = new File(PictureIO.PARENT_PATH_TO_SYSTEM_DIRECTORY).listFiles();
        File waitIconFile = Utility.retrieveFileWithoutExtension(directory, "wait_icon");
        ImageIcon waitIcon = new ImageIcon(waitIconFile.getAbsolutePath());

        // Create a label for the icon
        JLabel waitIconLabel = new JLabel();
        waitIconLabel.setIcon(waitIcon);
        waitIconLabel.setBounds(0, 10, 50, 50);

        // Create a label for the text
        JLabel waitLabel = new JLabel();
        waitLabel.setText("Vendor confirmation pending");
        waitLabel.setFont(new Font("Arial", Font.BOLD, 20));
        waitLabel.setForeground(new Color(204, 0, 0));
        waitLabel.setBounds(65, 5, 300, 60);

        // Add the icon and text to panel and return it
        newPanel.add(waitIconLabel);
        newPanel.add(waitLabel);
        return newPanel;
    }
    
    /**
     * This method helps to create a panel for runners to mark the order as picked up.
     * @return A JPanel consisting of text to indicate that the order is picked up
     */
    private JPanel createPanelForPendingPickUp() {

        // Initialize a JPanel
        JPanel newPanel = new JPanel();
        newPanel.setBackground(new Color(227, 202, 165));
        newPanel.setLayout(null);
        newPanel.setBounds(0, 0, 350, 60);
        newPanel.setPreferredSize(actionPanel.getPreferredSize());

        // Retrieve icon picture for the panel
        File[] directory = new File(PictureIO.PARENT_PATH_TO_SYSTEM_DIRECTORY).listFiles();
        File pickUpIconFile = Utility.retrieveFileWithoutExtension(directory, "pick_up_icon");
        ImageIcon pickUpIcon = new ImageIcon(pickUpIconFile.getAbsolutePath());
        
        // Generate JLabel to place icon
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(pickUpIcon);
        iconLabel.setBounds(40, 5, 100, 60);
        
        // Generate text for the panel
        JLabel textLabel = new JLabel("<html>Picked up from stall?<br>Click <u>here</u>!</html>");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel.setBounds(150, 0, 300, 70);
        
        // Add mouse listener to the panel
        newPanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
                // Display message before changing status to picked up
                SystemPopUp pickUpConfirm = new SystemPopUp(
                        MainPage.currentFrame,
                        "Order Pick Up",
                        "Are you sure that you have picked up the order?<br>The status cannot be reverted once set.",
                        new String[]{"No", "Yes"}
                );
                pickUpConfirm.setVisible(true);

                // If user choose "Yes", current order status is changed to on the way of delivering
                if (pickUpConfirm.getStatus() == 1) currentOrder.runnerUpdateOrderStatus(Order.OrderStatus.RUNNER_DELIVERY);

                // Refresh the order panel at the home page
                HomePagePanel.updatePanel();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {

                // Change the label and the cursor, then refresh
                textLabel.setFont(new Font("Arial", Font.BOLD, 18));
                newPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                textLabel.revalidate();
                textLabel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {

                // Change the label and the cursor, then refresh
                textLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                newPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                textLabel.revalidate();
                textLabel.repaint();
            }
        });
        
        // Add the icon and text to the panel
        newPanel.add(iconLabel);
        newPanel.add(textLabel);
        
        return newPanel;
    }

    /**
     * This method helps to create a panel for runners to mark the order as delivered (completed).
     * @return A JPanel consisting of text that allows runners to mark order as done
     */
    private JPanel createPanelForCompleteDelivery() {

        // Initialize a JPanel
        JPanel newPanel = new JPanel();
        newPanel.setBackground(new Color(227, 202, 165));
        newPanel.setLayout(null);
        newPanel.setBounds(0, 0, 350, 60);
        newPanel.setPreferredSize(actionPanel.getPreferredSize());

        // Retrieve icon picture for the panel
        File[] directory = new File(PictureIO.PARENT_PATH_TO_SYSTEM_DIRECTORY).listFiles();
        File pickUpIconFile = Utility.retrieveFileWithoutExtension(directory, "deliver_icon");
        ImageIcon pickUpIcon = new ImageIcon(pickUpIconFile.getAbsolutePath());
        
        // Generate JLabel to place icon
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(pickUpIcon);
        iconLabel.setBounds(40, 0, 100, 65);
        
        // Generate text for the panel
        JLabel textLabel = new JLabel("<html>Delivered to customer?<br>Click <u>here</u>!</html>");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel.setBounds(150, 0, 300, 70);
        
        // Add mouse listener to the panel
        newPanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // Display message before changing status to picked up
                SystemPopUp completeConfirm = new SystemPopUp(
                        MainPage.currentFrame,
                        "Order Sent",
                        "Are you sure that you have sent the order?<br>The status cannot be reverted once updated.",
                        new String[]{"No", "Yes"}
                );
                completeConfirm.setVisible(true);

                // If user choose "Yes", mark current order as completed
                if (completeConfirm.getStatus() == 1) currentOrder.runnerUpdateOrderStatus(Order.OrderStatus.COMPLETED);

                // Refresh the order panel at the home page
                HomePagePanel.updatePanel();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {

                // Change the label and the cursor, then refresh
                textLabel.setFont(new Font("Arial", Font.BOLD, 18));
                newPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                textLabel.revalidate();
                textLabel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {

                // Change the label and the cursor, then refresh
                textLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                newPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                textLabel.revalidate();
                textLabel.repaint();
            }
        });
        
        // Add the icon and text to the panel
        newPanel.add(iconLabel);
        newPanel.add(textLabel);
        
        // The panel is returned
        return newPanel;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deliveryTitlePanel = new javax.swing.JPanel();
        deliverIcon = new javax.swing.JLabel();
        storeNameLabel = new javax.swing.JLabel();
        storeIDLabel = new javax.swing.JLabel();
        actionPanel = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        deliveryDetailsPanel = new javax.swing.JPanel();
        foodIcon = new javax.swing.JLabel();
        customerIcon = new javax.swing.JLabel();
        itemOrderedTitle = new javax.swing.JLabel();
        itemScrollPane = new javax.swing.JScrollPane();
        itemOverallPanel = new javax.swing.JPanel();
        customerNameScrollPane = new javax.swing.JScrollPane();
        customerNameLabel = new javax.swing.JTextArea();
        contactNumberLabel = new javax.swing.JLabel();
        addressTitle = new javax.swing.JLabel();
        addressLineOneLabel = new javax.swing.JLabel();
        addressLineTwoLabel = new javax.swing.JLabel();
        addressLineThreeLabel = new javax.swing.JLabel();
        deliveryNoteTitle = new javax.swing.JLabel();
        deliveryNoteScrollPane = new javax.swing.JScrollPane();
        deliveryNoteLabel = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 251, 233));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deliveryTitlePanel.setBackground(new java.awt.Color(227, 202, 165));
        deliveryTitlePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        deliveryTitlePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deliverIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/order_icon.png"))); // NOI18N
        deliveryTitlePanel.add(deliverIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 70, 70));

        storeNameLabel.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        storeNameLabel.setText("Big Fish");
        deliveryTitlePanel.add(storeNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

        storeIDLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        storeIDLabel.setText("Store ID: S001");
        deliveryTitlePanel.add(storeIDLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, -1, -1));

        actionPanel.setBackground(new Color(0, 0, 0, 0));

        javax.swing.GroupLayout actionPanelLayout = new javax.swing.GroupLayout(actionPanel);
        actionPanel.setLayout(actionPanelLayout);
        actionPanelLayout.setHorizontalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        actionPanelLayout.setVerticalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        deliveryTitlePanel.add(actionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 30, 350, 70));

        add(deliveryTitlePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 130));

        deliveryDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        deliveryDetailsPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        deliveryDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        foodIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/food_icon.png"))); // NOI18N
        deliveryDetailsPanel.add(foodIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 70, 80));

        customerIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        customerIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/customer_icon.jpg"))); // NOI18N
        deliveryDetailsPanel.add(customerIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 100, 80));

        itemOrderedTitle.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        itemOrderedTitle.setText("Item Ordered");
        deliveryDetailsPanel.add(itemOrderedTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 199, -1));

        itemScrollPane.setBorder(null);
        itemScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        itemScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        itemOverallPanel.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10);
        flowLayout1.setAlignOnBaseline(true);
        itemOverallPanel.setLayout(flowLayout1);
        itemScrollPane.setViewportView(itemOverallPanel);

        deliveryDetailsPanel.add(itemScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 450, 210));

        customerNameScrollPane.setBorder(null);
        customerNameScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        customerNameScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        customerNameLabel.setEditable(false);
        customerNameLabel.setBackground(new java.awt.Color(255, 255, 255));
        customerNameLabel.setColumns(20);
        customerNameLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        customerNameLabel.setRows(1);
        customerNameLabel.setText(currentOrder.getOrderingCustomer().getName());
        customerNameLabel.setBorder(null);
        customerNameLabel.setCaretColor(new java.awt.Color(255, 255, 255));
        customerNameLabel.setCaretPosition(0);
        customerNameScrollPane.setViewportView(customerNameLabel);

        deliveryDetailsPanel.add(customerNameScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 480, 30));

        contactNumberLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        contactNumberLabel.setText("(Tel No: " + currentOrder.getOrderingCustomer().getContactNumber() + ")");
        deliveryDetailsPanel.add(contactNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 270, 30));

        addressTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        addressTitle.setText("Address");
        deliveryDetailsPanel.add(addressTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, 90, 30));

        addressLineOneLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        addressLineOneLabel.setText(currentOrder.getOrderingCustomer().getAddress().getAddressLine1());
        deliveryDetailsPanel.add(addressLineOneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 110, 390, 30));

        addressLineTwoLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        addressLineTwoLabel.setText(currentOrder.getOrderingCustomer().getAddress().getAddressLine2());
        deliveryDetailsPanel.add(addressLineTwoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 140, 390, 30));

        addressLineThreeLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        addressLineThreeLabel.setText(currentOrder.getOrderingCustomer().getAddress().getPostcode() + " " +
            currentOrder.getOrderingCustomer().getAddress().getCity() + ", " +
            currentOrder.getOrderingCustomer().getAddress().getState());
        deliveryDetailsPanel.add(addressLineThreeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 390, 30));

        deliveryNoteTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        deliveryNoteTitle.setText("<html>Delivery Note</html>");
        deliveryDetailsPanel.add(deliveryNoteTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, 80, 60));

        deliveryNoteScrollPane.setBorder(null);
        deliveryNoteScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        deliveryNoteScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        deliveryNoteLabel.setColumns(20);
        deliveryNoteLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        deliveryNoteLabel.setLineWrap(true);
        deliveryNoteLabel.setRows(3);
        deliveryNoteLabel.setText(currentOrder.getNoteToVendor());
        deliveryNoteLabel.setWrapStyleWord(true);
        deliveryNoteLabel.setBorder(null);
        deliveryNoteLabel.setCaretPosition(0);
        deliveryNoteScrollPane.setViewportView(deliveryNoteLabel);

        deliveryDetailsPanel.add(deliveryNoteScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 220, 390, 80));

        add(deliveryDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 1240, 330));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private javax.swing.JLabel addressLineOneLabel;
    private javax.swing.JLabel addressLineThreeLabel;
    private javax.swing.JLabel addressLineTwoLabel;
    private javax.swing.JLabel addressTitle;
    private javax.swing.JLabel contactNumberLabel;
    private javax.swing.JLabel customerIcon;
    private javax.swing.JTextArea customerNameLabel;
    private javax.swing.JScrollPane customerNameScrollPane;
    private javax.swing.JLabel deliverIcon;
    private javax.swing.JPanel deliveryDetailsPanel;
    private javax.swing.JTextArea deliveryNoteLabel;
    private javax.swing.JScrollPane deliveryNoteScrollPane;
    private javax.swing.JLabel deliveryNoteTitle;
    private javax.swing.JPanel deliveryTitlePanel;
    private javax.swing.JLabel foodIcon;
    private javax.swing.JLabel itemOrderedTitle;
    private static javax.swing.JPanel itemOverallPanel;
    private javax.swing.JScrollPane itemScrollPane;
    private javax.swing.JLabel storeIDLabel;
    private javax.swing.JLabel storeNameLabel;
    // End of variables declaration//GEN-END:variables
}
