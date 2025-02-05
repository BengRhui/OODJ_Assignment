/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.runner;

import backend.entity.DeliveryRunner;
import backend.entity.Item;
import backend.entity.Order;
import backend.utility.Utility.TimeframeFilter;
import frontend.utility.Graph;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.*;

/**
 *
 * @author Jun Hong (TP068580), Beng Rhui (TP068495)
 */
public class DeliveryHistoryPanel extends javax.swing.JPanel {

    private static DeliveryRunner currentRunner;
    private static TimeframeFilter timeFrame;
    private static ArrayList<Order> orderList;
    private final static Font boldFont = new Font("Arial", Font.BOLD, 18);
    private final static Font normalFont = new Font("Arial", Font.PLAIN, 18);
    private final static Color brownColour = new Color(227, 202, 165);
    
    /**
     * Creates new form DeliveryHistoryPanel
     */
    public DeliveryHistoryPanel() {
        
        // Initialize values
        currentRunner = MainPage.getRunner();
        timeFrame = TimeframeFilter.DAILY;
        orderList = Order.filterOrder(currentRunner, timeFrame).stream()
                .filter(order -> order.getOrderStatus() == Order.OrderStatus.COMPLETED ||
                        order.getOrderStatus() == Order.OrderStatus.CANCELLED)
                .sorted(Comparator.comparing(Order::getOrderedDate).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
        
        // Render the GUI components
        initComponents();
        
        // Update order panel
        updateRecentOrderPanel();
        
        // Update graph panel
        updateGraphPanel();
    }
    
    /**
     * This method helps to refresh the panel containing the graph.
     */
    private void updateGraphPanel() {
        
        // Remove all components on the graph panel
        graphPanel.removeAll();
                
        // Only take completed orders
        ArrayList<Order> listForGraph = orderList.stream()
                .filter(order -> order.getOrderStatus() == Order.OrderStatus.COMPLETED)
                .collect(Collectors.toCollection(ArrayList::new));
        
        // Generate the graph and set location
        Graph deliveryCountGraph = new Graph(
                listForGraph,
                Graph.DELIVERY_COUNT_GRAPH,
                timeFrame,
                650,
                425
        );
        deliveryCountGraph.setLocation(20, 30);
        
        // Add the graph to the panel, then refresh
        graphPanel.add(deliveryCountGraph);
        graphPanel.revalidate();
        graphPanel.repaint();
    }

    /**
     * This method helps to update and refresh the panel containing order history.
     */
    private void updateRecentOrderPanel() {

        // Obtain the order history list (only take the completed and cancelled ones)
        orderList = Order.filterOrder(currentRunner, timeFrame).stream()
                .filter(order -> order.getOrderStatus() == Order.OrderStatus.COMPLETED ||
                                 order.getOrderStatus() == Order.OrderStatus.CANCELLED)
                .sorted(Comparator.comparing(Order::getOrderedDate).reversed())
                .collect(Collectors.toCollection(ArrayList::new));

        // Remove all components on the panel
        recentDeliveryPanel.removeAll();

        // If order list is empty, then add a text to inform that no orders are available
        if (orderList.isEmpty()) {

            // Generate an empty label to state that the item is unavailable
            JLabel emptyDescriptionLabel = new JLabel("No delivery history found.");
            emptyDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 24));

            // Add the empty label to the panel and return it
            recentDeliveryPanel.add(emptyDescriptionLabel);
        }

        // Loop through each order
        for (Order order : orderList) {

            // Add the generated individual panels to the panel
            recentDeliveryPanel.add(generateIndividualOrderPanel(order));

            // Add a gap if the order is not the last order
            if (!orderList.getLast().equals(order)) recentDeliveryPanel.add(Box.createVerticalStrut(20));
        }

        // Refresh the panel
        recentDeliveryPanel.revalidate();
        recentDeliveryPanel.repaint();
    }

    /**
     * This method helps to generate an individual delivery history panel.
     * @param order The order involved in the panel
     * @return A JPanel consisting of the dine in method and the ordered items of an order
     */
    private JPanel generateIndividualOrderPanel(Order order) {

        // Declare an empty JPanel and set its attributes
        JPanel individualPanel = new JPanel();
        individualPanel.setLayout(null);
        individualPanel.setBackground(brownColour);

        // Get the title for the label
        String titleString = order.getOrderStatus() == Order.OrderStatus.CANCELLED ? order.getDiningType().toString() + " (Cancelled)" : order.getDiningType().toString();

        // Generate title label
        JLabel titleLabel = new JLabel(titleString);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(30, 20, 400, 30);

        // Generate time label
        JLabel timeLabel = new JLabel(order.getOrderedDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timeLabel.setBounds(385, 20, 150, 30);
        
        // Generate description label
        JPanel descriptionPanel = generateItemListGUI(order);
        descriptionPanel.setLocation(5, 65);

        // Calculate and set the size of the panel
        int panelWidth = 550;
        int panelHeight = 70 + descriptionPanel.getSize().height;

        individualPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        individualPanel.setSize(new Dimension(panelWidth, panelHeight));
        individualPanel.setMinimumSize(new Dimension(panelWidth, panelHeight));
        individualPanel.setMaximumSize(new Dimension(panelWidth, panelHeight));

        // Set a border for the order panel
        individualPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

        // Add components to panel and return the panel
        individualPanel.add(titleLabel);
        individualPanel.add(timeLabel);
        individualPanel.add(descriptionPanel);
        return individualPanel;
    }

    /**
     * This method helps to generate a panel that contains all items.
     * @param order The order associated with the panel
     * @return A JPanel in flow layout that consists of all items (except delivery fees) in the
     */
    private JPanel generateItemListGUI(Order order) {

        // Declare a JPanel to store the items and set properties
        JPanel container = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        container.setBackground(new Color(0, 0, 0, 0));
        container.setOpaque(true);

        // Create a flow layout for the panel
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setHgap(20);
        flowLayout.setVgap(10);
        flowLayout.setAlignOnBaseline(true);
        container.setLayout(flowLayout);

        // Get the list of items
        Map<Item, Integer> itemList = order.getOrderItem();

        // Declare a variable to store the list of descriptions for the item
        ArrayList<JLabel> descriptionList = new ArrayList<>();

        // Loop through each item in the order
        for (Map.Entry<Item, Integer> itemPair : itemList.entrySet()) {

            // Ignore if the item is not available or if the item is delivery fees
            if (itemPair.getKey() == null || itemPair.getKey().getItemName().equalsIgnoreCase("Delivery fees")) {
                continue;
            }

            // Generate a description for the item
            String description = itemPair.getKey().getItemName() + " x " + itemPair.getValue();

            // Create a label to display the description
            JLabel descriptionLabel = new JLabel(description);
            descriptionLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 1, true),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20))
            );
            descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            descriptionLabel.setBackground(Color.WHITE);
            descriptionLabel.setOpaque(true);

            // Add the description to the container panel
            container.add(descriptionLabel);

            // Add the label to an array list (for calculating the panel size later)
            descriptionList.add(descriptionLabel);
        }

        // If there is no item added
        if (descriptionList.isEmpty()) {

            // Generate an empty label to state that the item is unavailable
            JLabel emptyDescriptionLabel = new JLabel(" Item is unavailable (due to previous item deletion).");
            emptyDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 18));

            // Set a size for the container
            container.setSize(new Dimension(550, 40));

            // Add the empty label to the container panel and return it
            container.add(emptyDescriptionLabel);
            return container;
        }

        // Declare dimensions for the panel
        int hGapBetweenItem = 20;
        int vGapBetweenItem = 10;
        int descriptionHeight = descriptionList.getFirst().getPreferredSize().height;
        int currentWidth = 0;
        int panelWidth = 550;
        int panelHeight = vGapBetweenItem + descriptionHeight + vGapBetweenItem;

        // Loop through each label
        for (JLabel descriptionLabel : descriptionList) {

            // Get the width for each label
            int descriptionWidth = descriptionLabel.getPreferredSize().width;

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
        container.setSize(new Dimension(panelWidth, panelHeight));
        return container;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deliveryCountTitle = new javax.swing.JLabel();
        dailyButton = new javax.swing.JButton();
        monthlyButton = new javax.swing.JButton();
        quarterlyButton = new javax.swing.JButton();
        yearlyButton = new javax.swing.JButton();
        totalDeliveryTitle = new javax.swing.JLabel();
        totalDeliveryCount = new javax.swing.JLabel();
        totalEarningsTitle = new javax.swing.JLabel();
        totalEarningsCount = new javax.swing.JLabel();
        recentDeliveryTitle = new javax.swing.JLabel();
        graphPanel = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        recentDeliveryScrollPane = new javax.swing.JScrollPane() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        }
        ;
        recentDeliveryPanel = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };

        setBackground(new java.awt.Color(255, 251, 233));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deliveryCountTitle.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        deliveryCountTitle.setText("DELIVERY COUNT");
        add(deliveryCountTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 580, 70));

        dailyButton.setBackground(brownColour);
        dailyButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        dailyButton.setText("Daily");
        dailyButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        dailyButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dailyButton.setFocusPainted(false);
        dailyButton.setOpaque(true);
        dailyButton.setRequestFocusEnabled(false);
        dailyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dailyButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dailyButtonMouseExited(evt);
            }
        });
        dailyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dailyButtonActionPerformed(evt);
            }
        });
        add(dailyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(786, 60, 140, 50));

        monthlyButton.setBackground(Color.WHITE);
        monthlyButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        monthlyButton.setText("Monthly");
        monthlyButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        monthlyButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        monthlyButton.setFocusPainted(false);
        monthlyButton.setOpaque(true);
        monthlyButton.setRequestFocusEnabled(false);
        monthlyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                monthlyButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                monthlyButtonMouseExited(evt);
            }
        });
        monthlyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthlyButtonActionPerformed(evt);
            }
        });
        add(monthlyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(924, 60, 140, 50));

        quarterlyButton.setBackground(Color.WHITE);
        quarterlyButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        quarterlyButton.setText("Quarterly");
        quarterlyButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        quarterlyButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        quarterlyButton.setFocusPainted(false);
        quarterlyButton.setOpaque(true);
        quarterlyButton.setRequestFocusEnabled(false);
        quarterlyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                quarterlyButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                quarterlyButtonMouseExited(evt);
            }
        });
        quarterlyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quarterlyButtonActionPerformed(evt);
            }
        });
        add(quarterlyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1062, 60, 140, 50));

        yearlyButton.setBackground(Color.WHITE);
        yearlyButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        yearlyButton.setText("Yearly");
        yearlyButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        yearlyButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        yearlyButton.setFocusPainted(false);
        yearlyButton.setOpaque(true);
        yearlyButton.setRequestFocusEnabled(false);
        yearlyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                yearlyButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                yearlyButtonMouseExited(evt);
            }
        });
        yearlyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearlyButtonActionPerformed(evt);
            }
        });
        add(yearlyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 60, 140, 50));

        totalDeliveryTitle.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        totalDeliveryTitle.setText("<html>Total Delivery</html>");
        add(totalDeliveryTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 140, 240, -1));

        totalDeliveryCount.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        totalDeliveryCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        totalDeliveryCount.setText(String.valueOf(
            currentRunner.getDeliveryCount(timeFrame)
        ));
        add(totalDeliveryCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, 240, -1));

        totalEarningsTitle.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        totalEarningsTitle.setText("Total Earnings");
        add(totalEarningsTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 140, 230, -1));

        totalEarningsCount.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        totalEarningsCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        totalEarningsCount.setText("RM" + String.format("%.2f", currentRunner.getTipsAmount(timeFrame)));
        add(totalEarningsCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 190, 240, -1));

        recentDeliveryTitle.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        recentDeliveryTitle.setText("Recent Delivery");
        add(recentDeliveryTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 260, -1, -1));

        graphPanel.setBackground(new java.awt.Color(255, 255, 255));
        graphPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        graphPanel.setLayout(null);
        add(graphPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 700, 470));

        recentDeliveryScrollPane.setBackground(new Color(0, 0, 0, 0));
        recentDeliveryScrollPane.setBorder(null);
        recentDeliveryScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        recentDeliveryScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        recentDeliveryScrollPane.setOpaque(false);
        recentDeliveryScrollPane.setViewportView(null);
        recentDeliveryScrollPane.getViewport().setOpaque(false);

        recentDeliveryPanel.setBackground(new Color(0, 0, 0, 0));
        recentDeliveryPanel.setOpaque(false);
        recentDeliveryPanel.setLayout(new javax.swing.BoxLayout(recentDeliveryPanel, javax.swing.BoxLayout.Y_AXIS));
        recentDeliveryScrollPane.setViewportView(recentDeliveryPanel);

        add(recentDeliveryScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, 550, 290));
    }// </editor-fold>//GEN-END:initComponents

    private void dailyButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dailyButtonMouseEntered

        // If the daily button is not currently selected
        if (timeFrame != TimeframeFilter.DAILY) {
            dailyButton.setFont(boldFont);
        }
    }//GEN-LAST:event_dailyButtonMouseEntered

    private void dailyButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dailyButtonMouseExited

        // If the daily button is not currently selected
        if (timeFrame != TimeframeFilter.DAILY) {
            dailyButton.setFont(normalFont);
        }
    }//GEN-LAST:event_dailyButtonMouseExited

    private void monthlyButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monthlyButtonMouseEntered

        // If the monthly button is not currently selected
        if (timeFrame != TimeframeFilter.MONTHLY) {
            monthlyButton.setFont(boldFont);
        }
    }//GEN-LAST:event_monthlyButtonMouseEntered

    private void monthlyButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monthlyButtonMouseExited

        // If the monthly button is not currently selected
        if (timeFrame != TimeframeFilter.MONTHLY) {
            monthlyButton.setFont(normalFont);
        }
    }//GEN-LAST:event_monthlyButtonMouseExited

    private void quarterlyButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quarterlyButtonMouseEntered

        // If the quarterly button is not currently selected
        if (timeFrame != TimeframeFilter.QUARTERLY) {
            quarterlyButton.setFont(boldFont);
        }
    }//GEN-LAST:event_quarterlyButtonMouseEntered

    private void quarterlyButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quarterlyButtonMouseExited

        // If the quarterly button is not currently selected
        if (timeFrame != TimeframeFilter.QUARTERLY) {
            quarterlyButton.setFont(normalFont);
        }
    }//GEN-LAST:event_quarterlyButtonMouseExited

    private void yearlyButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yearlyButtonMouseEntered

        // If the yearly button is not currently selected
        if (timeFrame != TimeframeFilter.YEARLY) {
            yearlyButton.setFont(boldFont);
        }
    }//GEN-LAST:event_yearlyButtonMouseEntered

    private void yearlyButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yearlyButtonMouseExited

        // If the yearly button is not currently selected
        if (timeFrame != TimeframeFilter.YEARLY) {
            yearlyButton.setFont(normalFont);
        }
    }//GEN-LAST:event_yearlyButtonMouseExited

    private void dailyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dailyButtonActionPerformed

        // If the daily button is not selected
        if (timeFrame != TimeframeFilter.DAILY) {
            
            // Change the font style
            dailyButton.setFont(boldFont);
            
            // Fill the box with brown colour
            dailyButton.setBackground(brownColour);
            
            // Update the current time frame
            timeFrame = TimeframeFilter.DAILY;
            
            // Reset the font for the other options
            JButton[] buttonList = new JButton[]{monthlyButton, quarterlyButton, yearlyButton};
            for (JButton button : buttonList) {
                 button.setFont(normalFont);
                 button.setBackground(Color.WHITE);
            }

            // Update the order count
            totalDeliveryCount.setText(
                    String.valueOf(
                            currentRunner.getDeliveryCount(TimeframeFilter.DAILY)
                    )
            );
                        
            // Update total revenue count
            totalEarningsCount.setText(
                    "RM" + String.format(
                            "%.2f", 
                            currentRunner.getTipsAmount(TimeframeFilter.DAILY)
                    )
            );

            // Update the recent order panel
            updateRecentOrderPanel();
                        
            // Update the graph panel
            updateGraphPanel();
        }
    }//GEN-LAST:event_dailyButtonActionPerformed

    private void monthlyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthlyButtonActionPerformed

        // If the monthly button is not selected
        if (timeFrame != TimeframeFilter.MONTHLY) {
            
            // Change the font style
            monthlyButton.setFont(boldFont);
            
            // Fill the box with brown colour
            monthlyButton.setBackground(brownColour);
            
            // Update the current time frame
            timeFrame = TimeframeFilter.MONTHLY;
            
            // Reset the font for the other options
            JButton[] buttonList = new JButton[]{dailyButton, quarterlyButton, yearlyButton};
            for (JButton button : buttonList) {
                 button.setFont(normalFont);
                 button.setBackground(Color.WHITE);
            }
            
            // Update the order count
            totalDeliveryCount.setText(
                    String.valueOf(
                            currentRunner.getDeliveryCount(TimeframeFilter.MONTHLY)
                    )
            );
                        
            // Update total revenue count
            totalEarningsCount.setText(
                    "RM" + String.format(
                            "%.2f", 
                            currentRunner.getTipsAmount(TimeframeFilter.MONTHLY)
                    )
            );
            
            // Update the recent order panel
            updateRecentOrderPanel();
                        
            // Update the graph panel
            updateGraphPanel();
        }
    }//GEN-LAST:event_monthlyButtonActionPerformed

    private void quarterlyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quarterlyButtonActionPerformed

        // If the quartely button is not selected
        if (timeFrame != TimeframeFilter.QUARTERLY) {
            
            // Change the font style
            quarterlyButton.setFont(boldFont);
            
            // Fill the box with brown colour
            quarterlyButton.setBackground(brownColour);
            
            // Update the current time frame
            timeFrame = TimeframeFilter.QUARTERLY;
            
            // Reset the font for the other options
            JButton[] buttonList = new JButton[]{dailyButton, monthlyButton, yearlyButton};
            for (JButton button : buttonList) {
                 button.setFont(normalFont);
                 button.setBackground(Color.WHITE);
            }

            // Update the order count
            totalDeliveryCount.setText(
                    String.valueOf(
                            currentRunner.getDeliveryCount(TimeframeFilter.QUARTERLY)
                    )
            );
                        
            // Update total revenue count
            totalEarningsCount.setText(
                    "RM" + String.format(
                            "%.2f", 
                            currentRunner.getTipsAmount(TimeframeFilter.QUARTERLY)
                    )
            );
            
            // Update the recent order panel
            updateRecentOrderPanel();
                        
            // Update the graph panel
            updateGraphPanel();
        }
    }//GEN-LAST:event_quarterlyButtonActionPerformed

    private void yearlyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearlyButtonActionPerformed

        // If the yearly button is not selected
        if (timeFrame != TimeframeFilter.YEARLY) {
            
            // Change the font style
            yearlyButton.setFont(boldFont);
            
            // Fill the box with brown colour
            yearlyButton.setBackground(brownColour);
            
            // Update the current time frame
            timeFrame = TimeframeFilter.YEARLY;
            
            // Reset the font for the other options
            JButton[] buttonList = new JButton[]{dailyButton, monthlyButton, quarterlyButton};
            for (JButton button : buttonList) {
                 button.setFont(normalFont);
                 button.setBackground(Color.WHITE);
            }

            // Update the order count
            totalDeliveryCount.setText(
                    String.valueOf(
                            currentRunner.getDeliveryCount(TimeframeFilter.YEARLY)
                    )
            );
                        
            // Update total revenue count
            totalEarningsCount.setText(
                    "RM" + String.format(
                            "%.2f", 
                            currentRunner.getTipsAmount(TimeframeFilter.YEARLY)
                    )
            );
            
            // Update the recent order panel
            updateRecentOrderPanel();
            
            // Update the graph panel
            updateGraphPanel();
        }
    }//GEN-LAST:event_yearlyButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dailyButton;
    private javax.swing.JLabel deliveryCountTitle;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JButton monthlyButton;
    private javax.swing.JButton quarterlyButton;
    private javax.swing.JPanel recentDeliveryPanel;
    private javax.swing.JScrollPane recentDeliveryScrollPane;
    private javax.swing.JLabel recentDeliveryTitle;
    private javax.swing.JLabel totalDeliveryCount;
    private javax.swing.JLabel totalDeliveryTitle;
    private javax.swing.JLabel totalEarningsCount;
    private javax.swing.JLabel totalEarningsTitle;
    private javax.swing.JButton yearlyButton;
    // End of variables declaration//GEN-END:variables
}
