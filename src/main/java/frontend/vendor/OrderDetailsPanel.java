/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.vendor;

import backend.entity.Item;
import backend.entity.Order;
import backend.entity.Order.OrderStatus;
import static backend.entity.Order.OrderStatus.COMPLETED;
import static backend.entity.Order.OrderStatus.READY_FOR_PICK_UP;
import static backend.entity.Order.OrderStatus.VENDOR_PREPARING;
import backend.file_io.PictureIO;
import backend.utility.Utility;
import frontend.pop_up.SystemPopUp;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Xuan Jack (TP067678), Beng Rhui (TP068495)
 */
public class OrderDetailsPanel extends JPanel {

    private final Order currentOrder;

    /**
     * Creates new form OrderDetailsPanel
     *
     * @param order The order associated
     */
    public OrderDetailsPanel(Order order) {

        // Set the order to current order
        this.currentOrder = order;

        // Render GUI components
        initComponents();

        // Add items into GUI
        addItemIntoGUI();

        // Set actions for orders
        setActionsForOrder();
    }

    /**
     * The method that helps to add item information into the GUI.
     */
    private void addItemIntoGUI() {

        // Initialize a few variables to help in coding
        Map<Item, Integer> itemMap = currentOrder.getOrderItem();
        ArrayList<JLabel> jLabelList = new ArrayList<>();
        Color buttonColor = new Color(227, 202, 165);

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

            // Add mouse listeners to the labels
            itemLabel.addMouseListener(new MouseListener() {

                // Change panel colours (this can act as record purpose)
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (itemLabel.getBackground() == Color.WHITE) {
                        itemLabel.setBackground(buttonColor);
                    } else if (itemLabel.getBackground() == buttonColor) {
                        itemLabel.setBackground(Color.WHITE);
                    }
                }

                // Set cursor
                @Override
                public void mouseEntered(MouseEvent e) {
                    itemLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                // Set cursor
                @Override
                public void mouseExited(MouseEvent e) {
                    itemLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}
            });

            // Add the JLabel into the flow layout
            foodOrderedPanel.add(itemLabel);

            // Add JLabel into list (to calculate the number of line it'll span)
            jLabelList.add(itemLabel);
        }

        // If there is no order involved (should not happen but included just in case)
        if (jLabelList.isEmpty()) {

            // Change the layout of the food ordered panel
            foodOrderedPanel.setLayout(null);
            foodOrderedPanel.setPreferredSize(new Dimension(300, 100));

            // Generate an empty label to state that the item is unavailable
            JLabel emptyDescriptionLabel = new JLabel("Unavailable item.");
            emptyDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            emptyDescriptionLabel.setBounds(0, 0, 200, 20);

            // Add the empty label to the container panel and return it
            foodOrderedPanel.add(emptyDescriptionLabel);

        } else {

            // Calculate and set the dimension of the panel
            int rowsInvolved = getSpanningRowNum(jLabelList);
            int panelWidth = 650;
            int panelHeight = (rowsInvolved + 1) * 10 + jLabelList.getFirst().getPreferredSize().height * rowsInvolved;

            // Based on the calculations, set the sizes for the components
            foodOrderedPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
            foodOrderedPanel.setBounds(0, 0, panelWidth, panelHeight);
            foodOrderedBaseContainer.setPreferredSize(new Dimension(panelWidth, panelHeight));
        }
    }

    /**
     * This method helps to calculate the number of rows that the flow layout will span.
     * @return The number of rows spanned
     */
    private static int getSpanningRowNum(ArrayList<JLabel> jLabelList) {

        // Initialize the variables
        int panelWidth = 650;
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
        newPanel.setBounds(0, 0, 350, 60);
        newPanel.setPreferredSize(actionContainer.getPreferredSize());

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
            if (confirmAcceptOrder.getStatus() == 1) currentOrder.vendorAcceptOrder();

            // Refresh the order panel at the home page
            HomePagePanel.updateOrderPanel();
        });

        // Add mouse listener to the buttons (set the cursors and colour)
        acceptButton.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                acceptButton.setBackground(new Color(0, 51, 0));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                acceptButton.setBackground(new Color(0, 102, 0));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                acceptButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                acceptButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {}
        });

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

            // Display message to confirm that vendor wishes to cancel order
            SystemPopUp confirmDeclineOrder = new SystemPopUp(
                    MainPage.currentFrame,
                    "Decline Order",
                    "Are you sure that you would like to decline the order?<br>You will not be able to revert this operation.",
                    new String[]{"No", "Yes"}
            );
            confirmDeclineOrder.setVisible(true);

            // If yes, vendor cancels the order
            if (confirmDeclineOrder.getStatus() == 1) currentOrder.vendorCancelOrder();

            // Refresh the order panel
            HomePagePanel.updateOrderPanel();
        });

        // Add mouse listener to button (set colour and cursor)
        declineButton.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                declineButton.setBackground(new Color(193, 40, 27));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                declineButton.setBackground(new Color(233, 67, 55));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                declineButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                declineButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {}
        });

        // Add buttons into the panel and return it
        newPanel.add(acceptButton);
        newPanel.add(declineButton);
        return newPanel;
    }

    /**
     * This method helps to create a JPanel to indicate that the order is pending waiting from runner.
     * @return A JPanel containing words indicating that order is pending for a runner
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
        newPanel.setPreferredSize(actionContainer.getPreferredSize());

        // Retrieve the wait icon file
        File[] directory = new File(PictureIO.PARENT_PATH_TO_SYSTEM_DIRECTORY).listFiles();
        File waitIconFile = Utility.retrieveFileWithoutExtension(directory, "wait_icon");
        ImageIcon waitIcon = new ImageIcon(waitIconFile.getAbsolutePath());

        // Create a label for the icon
        JLabel waitIconLabel = new JLabel();
        waitIconLabel.setIcon(waitIcon);
        waitIconLabel.setBounds(0, 5, 50, 50);

        // Create a label for the text
        JLabel waitLabel = new JLabel();
        waitLabel.setText("Runner confirmation pending");
        waitLabel.setFont(new Font("Arial", Font.BOLD, 20));
        waitLabel.setForeground(new Color(204, 0, 0));
        waitLabel.setBounds(65, 0, 300, 60);

        // Add the icon and text to panel and return it
        newPanel.add(waitIconLabel);
        newPanel.add(waitLabel);
        return newPanel;
    }

    /**
     * This method helps to create a dropdown box for vendors to update their choices.
     * @return A JPanel consisting of a combo box
     */
    private JPanel createPanelForStatusUpdate() {

        // Initialize a JPanel
        JPanel newPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        newPanel.setBackground(new Color(0, 0, 0, 0));;
        newPanel.setLayout(null);
        newPanel.setBounds(0, 0, 350, 60);
        newPanel.setPreferredSize(actionContainer.getPreferredSize());

        // Declare a map to store key-value pairs
        Map<OrderStatus, String> statusMap = new LinkedHashMap<>();

        // Store the order status and their corresponding outputs into a map
        switch (currentOrder.getDiningType()) {
            case DINE_IN -> {
                statusMap.put(VENDOR_PREPARING, "Preparing");
                statusMap.put(READY_FOR_PICK_UP, "Ready to Send to Table");
                statusMap.put(COMPLETED, "Completed");
            }
            case TAKEAWAY -> {
                statusMap.put(VENDOR_PREPARING, "Preparing");
                statusMap.put(READY_FOR_PICK_UP, "Ready for Pickup");
                statusMap.put(COMPLETED, "Completed");
            }
            case DELIVERY -> {
                statusMap.put(VENDOR_PREPARING, "Preparing");
                statusMap.put(READY_FOR_PICK_UP, "Ready for Pickup");
            }
            default -> throw new IllegalStateException("Invalid dining type. Please inspect the code.");
        }

        //Retrieve the necessary information to be added to GUI later (dropdown options and current status)
        String[] options = statusMap.values().toArray(new String[0]);
        String currentStatus = statusMap.getOrDefault(currentOrder.getOrderStatus(), null);
        if (currentStatus == null) throw new IllegalStateException("Invalid order status. Please inspect the code");

        // Generate a combo box
        JComboBox<String> statusComboBox = new JComboBox<>(options);
        statusComboBox.setSelectedItem(currentStatus);
        statusComboBox.setBounds(0, 5, 350, 50);
        statusComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        statusComboBox.setBackground(Color.WHITE);
        statusComboBox.setFocusable(false);

        // Customize the combo box
        statusComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                // Get the current cell component
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Set the size and add border to the cell
                component.setPreferredSize(new Dimension(component.getPreferredSize().width, 40));
                ((JLabel) component).setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return component;
            }
        });

        // Add an action listener to the combo box
        statusComboBox.addActionListener(e -> {

            // Get the selected item
            Object selectedItem = statusComboBox.getSelectedItem();
            String selectedItemString = (String) selectedItem;

            // Depending on the item, generate the corresponding order status
            OrderStatus status;
            switch (selectedItemString) {
                case "Preparing" -> status = OrderStatus.VENDOR_PREPARING;
                case "Ready to Send to Table", "Ready for Pickup" -> status = OrderStatus.READY_FOR_PICK_UP;
                case "Completed" -> status = OrderStatus.COMPLETED;
                case null, default -> throw new IllegalStateException("The status chosen is incorrect. Please inspect code.");
            }

            // If the order status changes
            if (currentOrder.getOrderStatus() != status) {

                // Create a message to confirm changes
                SystemPopUp changeStatusPopUp = new SystemPopUp(
                        MainPage.currentFrame,
                        "Update Status",
                        "Do you wish to switch the order status to: " + selectedItem + "?",
                        new String[]{"No", "Yes"}
                );
                changeStatusPopUp.setVisible(true);
                int popUpStatus = changeStatusPopUp.getStatus();

                // If user confirms, perform update on the order
                if (popUpStatus == 1) currentOrder.vendorUpdateOrderStatus(status);

                // Update the panel and the combo box
                HomePagePanel.updateOrderPanel();
                statusComboBox.setSelectedItem(selectedItem);
            }
        });

        // Add the combo box to panel and return it
        newPanel.add(statusComboBox);
        return newPanel;
    }

    /**
     * This method set the panels for different actions.
     */
    private void setActionsForOrder() {

        // Get the status of the current order
        OrderStatus status = currentOrder.getOrderStatus();

        // Remove the existing components on the action container
        actionContainer.removeAll();

        // Go through different status
        switch (status) {
            
            // Different status for different options
            case WAITING_VENDOR_AND_RUNNER, WAITING_VENDOR -> actionContainer.add(createPanelForAcceptOrder());
            case WAITING_RUNNER -> actionContainer.add(createPanelForWaitingConfirmation());
            case VENDOR_PREPARING, READY_FOR_PICK_UP -> actionContainer.add(createPanelForStatusUpdate());

            // Throw an exception if the status is incorrect
            default -> throw new IllegalArgumentException("Current status is not allowed here.");
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

        orderIDPanel = new javax.swing.JPanel();
        orderIDText = new javax.swing.JLabel();
        actionContainer = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }

        };
        orderDetailsPanel = new javax.swing.JPanel();
        foodIcon = new javax.swing.JLabel();
        noteIcon = new javax.swing.JLabel();
        foodOrderedTitle1 = new javax.swing.JLabel();
        noteTitle = new javax.swing.JLabel();
        foodOrderedScrollPane = new javax.swing.JScrollPane();
        foodOrderedBaseContainer = new javax.swing.JPanel();
        foodOrderedPanel = new javax.swing.JPanel();
        vendorNoteScrollPane = new javax.swing.JScrollPane();
        vendorNoteTextArea = new javax.swing.JTextArea();

        setBackground(new Color(0, 0, 0, 0));
        setName("orderPanel"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        orderIDPanel.setBackground(new java.awt.Color(227, 202, 165));
        orderIDPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        orderIDPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        orderIDText.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        orderIDText.setText("ORD00001 - Eat In (T003)");
        orderIDPanel.add(orderIDText, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 480, 100));

        actionContainer.setBackground(new Color(0, 0, 0, 0));
        actionContainer.setOpaque(false);
        actionContainer.setLayout(null);
        orderIDPanel.add(actionContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, 350, 60));

        add(orderIDPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 100));

        orderDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        orderDetailsPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        orderDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        foodIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/food_icon.png"))); // NOI18N
        orderDetailsPanel.add(foodIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 70, 70));

        noteIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/notes_icon.png"))); // NOI18N
        orderDetailsPanel.add(noteIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 30, 70, 70));

        foodOrderedTitle1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        foodOrderedTitle1.setText("Food Ordered");
        orderDetailsPanel.add(foodOrderedTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 200, -1));

        noteTitle.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        noteTitle.setText("Additional Notes");
        orderDetailsPanel.add(noteTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 40, 200, -1));

        foodOrderedScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        foodOrderedScrollPane.setBorder(null);
        foodOrderedScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        foodOrderedScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        foodOrderedBaseContainer.setBackground(new java.awt.Color(255, 255, 255));
        foodOrderedBaseContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        foodOrderedPanel.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10);
        flowLayout1.setAlignOnBaseline(true);
        foodOrderedPanel.setLayout(flowLayout1);
        foodOrderedBaseContainer.add(foodOrderedPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        foodOrderedScrollPane.setViewportView(foodOrderedBaseContainer);

        orderDetailsPanel.add(foodOrderedScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 650, 70));

        vendorNoteScrollPane.setBorder(null);
        vendorNoteScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        vendorNoteScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        vendorNoteTextArea.setEditable(false);
        vendorNoteTextArea.setColumns(20);
        vendorNoteTextArea.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        vendorNoteTextArea.setLineWrap(true);
        vendorNoteTextArea.setRows(3);
        vendorNoteTextArea.setText(currentOrder.getNoteToVendor());
        vendorNoteTextArea.setWrapStyleWord(true);
        vendorNoteTextArea.setBorder(null);
        vendorNoteTextArea.setCaret(new DefaultCaret() {
            // Called to render nothing for caret
            public void paint(Graphics g) {
                // Do nothing
            }
        });
        vendorNoteScrollPane.setViewportView(vendorNoteTextArea);

        orderDetailsPanel.add(vendorNoteScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 80, 340, 70));

        add(orderDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 1280, 170));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JPanel actionContainer;
    private javax.swing.JLabel foodIcon;
    private static javax.swing.JPanel foodOrderedBaseContainer;
    private static javax.swing.JPanel foodOrderedPanel;
    private javax.swing.JScrollPane foodOrderedScrollPane;
    private javax.swing.JLabel foodOrderedTitle1;
    private javax.swing.JLabel noteIcon;
    private javax.swing.JLabel noteTitle;
    private javax.swing.JPanel orderDetailsPanel;
    private javax.swing.JPanel orderIDPanel;
    private javax.swing.JLabel orderIDText;
    private javax.swing.JScrollPane vendorNoteScrollPane;
    private javax.swing.JTextArea vendorNoteTextArea;
    // End of variables declaration//GEN-END:variables
}
