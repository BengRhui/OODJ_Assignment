/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.customer;

import backend.entity.Order;
import backend.entity.Stall.StallCategories;
import backend.file_io.PictureIO;
import backend.utility.Utility;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author limbengrhui
 */
public class HomePanel extends javax.swing.JPanel {

    /**
     * Creates new form HomePage
     */
    public HomePanel() {
        
        // Render GUI components
        initComponents();
        
        // Initialize categories panel
        initializeCategoryPanel();
        
        // Update order panel
        updateOrderPanel();
    }
    
    /**
     * This method helps to refresh the order panels.
     */
    public static void updateOrderPanel() {
    
        // Remove everything on the panel
        currentOrderContainer.removeAll();
        
        // Retrieve the incomplete orders from customer
        ArrayList<Order> incompleteOrders = Order.getIncompleteOrders(MainPage.getCustomer());
        
        // Sort the orders - pending orders first, then the remaining ones based on time
        Collections.sort(incompleteOrders, (Order order1, Order order2) -> {
            
            // Compare status with pending changes first
            if (order1.getOrderStatus() == Order.OrderStatus.PENDING_CHANGE && order2.getOrderStatus() != Order.OrderStatus.PENDING_CHANGE) {
                
                // Return order 1 (-1)
                return -1;
                
            } else if (order1.getOrderStatus() != Order.OrderStatus.PENDING_CHANGE && order2.getOrderStatus() == Order.OrderStatus.PENDING_CHANGE) {
                
                // Return order 2 (1)
                return 1;
                
            } else {
                
                // Compare time for other orders
                return order1.getOrderedDate().compareTo(order2.getOrderedDate());
            }
        });
        
        // If there is no incomplete orders
        if (incompleteOrders.isEmpty()) {
        
            // Create a JLabel and JPanel to display message
            JPanel emptyPanel = new JPanel(null);
            JLabel emptyLabel = new JLabel("No orders available currently.");
            
            // Set attributes to the label
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            emptyLabel.setBounds(0, 0, 500, 30);
            
            // Add label to panel
            emptyPanel.add(emptyLabel);
            emptyPanel.setBackground(new Color(255, 251, 233));
            
            // Add panel to the container
            currentOrderContainer.add(emptyPanel);
            
        } else {
        
            // Loop through order list
            for (Order order : incompleteOrders) {

                // Create panel to show order information
                OrderDetailsPanel orderDetailsPanel = new OrderDetailsPanel(order);
                currentOrderContainer.add(orderDetailsPanel);
                
                // Add spaces between panels
                if (!incompleteOrders.getLast().equals(order)) 
                    currentOrderContainer.add(Box.createVerticalStrut(20));
            }
        }
        
        // Refresh panel
        currentOrderContainer.revalidate();
        currentOrderContainer.repaint();
    }
    
    /**
     * This method helps to load the category panels.
     */
    private void initializeCategoryPanel() {
    
        // Declare initial size
        int categoryWidth = 300;
        int categoryHeight = 100;
        
        // Loop through each available stall categories
        for (String category : StallCategories.STALL_CATEGORIES) {
            
            // Generate a panel based on the category
            JPanel categoryPanel = new JPanel();
            categoryPanel.setLayout(null);
            categoryPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            categoryPanel.setPreferredSize(new Dimension(categoryWidth, categoryHeight));
            
            // Add an action listener for the category
            categoryPanel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    // Convert the category into stall category type
                    StallCategories stallCategory = StallCategories.generateFromString(category);

                    // Generate the card that shows shops for a particular category
                    StallBasedOnCategoryPanel shopCategoryCard = new StallBasedOnCategoryPanel(stallCategory);
                    MainPage.currentCardPanel.add(shopCategoryCard, "shopCategoryPanel");

                    // Display the new card
                    CardLayout cardLayout = (CardLayout) MainPage.currentCardPanel.getLayout();
                    cardLayout.show(MainPage.currentCardPanel, "shopCategoryPanel");
                    MainPage.setCardString("shopCategoryPanel");

                    // Add to list as a record
                    MainPage.addAdditionalPanels(shopCategoryCard);

                    // Change formatting for the text
                    MainPage.resetSelectStatus();
                    
                    // Change the frame title
                    MainPage.setFrameTitle("List of Shops based on Category");
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
            
            
            // Retrieve the picture file
            File[] directory = new File(PictureIO.PARENT_PATH_TO_SYSTEM_DIRECTORY).listFiles();
            String expectedFileName = category.toLowerCase().replace("-", "_").replace(" ", "_") + "_food";
            File categoryIconFile = Utility.retrieveFileWithoutExtension(directory, expectedFileName);

            // If the icon file is null, return the empty picture
            if (categoryIconFile == null) categoryIconFile = PictureIO.getEmptyPicture();
            
            // Declare an empty variable to store picture
            ImageIcon categoryIcon;
            
            // Resize picture to maintain ratio aspect
            try {
                
                // Get the image buffer
                BufferedImage initialImage = ImageIO.read(categoryIconFile);

                // Retrieve the dimensions for the new picture
                int originalWidth = initialImage.getWidth();
                int originalHeight = initialImage.getHeight();
                int widthAfterResized = categoryWidth;
                double scalingRatio = widthAfterResized / (originalWidth + 0.0);
                int heightAfterResized = (int) (originalHeight * scalingRatio);
                
                // Scale the image and get the image icon
                Image scaledImage = initialImage.getScaledInstance(widthAfterResized, heightAfterResized, Image.SCALE_SMOOTH);
                categoryIcon = new ImageIcon(scaledImage);
                
            } catch (IOException e) {
                
                // Throw exception if the picture cannot be retrieved
                throw new IllegalStateException("Unable to retrieve picture.");
            }
            
            // Generate a JLabel to store the picture
            JLabel pictureLabel = new JLabel();
            pictureLabel.setIcon(categoryIcon);
            pictureLabel.setBounds(0, 0, categoryWidth, categoryHeight);
            
            // Generate a JPanel to darken the picture
            JPanel darkenBackground = new JPanel() {
                
                // Added to make sure that the transparency renders correctly
                @Override
                protected void paintComponent(Graphics g) {
                    g.setColor( getBackground() );
                    g.fillRect(0, 0, getWidth(), getHeight());
                    super.paintComponent(g);
                }
            };
            darkenBackground.setLayout(null);
            darkenBackground.setOpaque(false);
            darkenBackground.setBackground(new Color(0, 0, 0, 150));
            darkenBackground.setBounds(0, 0, categoryWidth, categoryHeight);
            
            // Generate a JLabel to show category
            JLabel categoryName = new JLabel(category);
            categoryName.setFont(new Font("Arial", Font.BOLD, 30));
            categoryName.setForeground(Color.WHITE);
            categoryName.setHorizontalAlignment(SwingConstants.CENTER);
            categoryName.setBounds(0, 0, categoryWidth, categoryHeight);
            
            // Add the text to the darken background
            darkenBackground.add(categoryName);
            
            // Add the other components into the category panel
            categoryPanel.add(darkenBackground);
            categoryPanel.add(pictureLabel);
            
            // Add the category panel to the container panel
            categoryContainer.add(categoryPanel);
            
            // Refresh panel after adding items
            categoryContainer.revalidate();
            categoryContainer.repaint();
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

        currentOrderTitle = new javax.swing.JLabel();
        categoriesTitle = new javax.swing.JLabel();
        currentOrderScrollPane = new javax.swing.JScrollPane();
        currentOrderContainer = new javax.swing.JPanel();
        categoryScrollPane = new javax.swing.JScrollPane();
        categoryContainer = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 251, 233));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        currentOrderTitle.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        currentOrderTitle.setText("Current Orders");
        add(currentOrderTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        categoriesTitle.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        categoriesTitle.setText("Categories");
        add(categoriesTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        currentOrderScrollPane.setBorder(null);
        currentOrderScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        currentOrderScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        currentOrderContainer.setBackground(new java.awt.Color(255, 251, 233));
        currentOrderContainer.setLayout(new javax.swing.BoxLayout(currentOrderContainer, javax.swing.BoxLayout.Y_AXIS));
        currentOrderScrollPane.setViewportView(currentOrderContainer);

        add(currentOrderScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 630, 430));

        categoryScrollPane.setBorder(null);
        categoryScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        categoryScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        categoryContainer.setBackground(new java.awt.Color(255, 251, 233));
        categoryContainer.setLayout(new java.awt.GridLayout(8, 2, 10, 10));
        categoryScrollPane.setViewportView(categoryContainer);

        add(categoryScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, 610, 430));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel categoriesTitle;
    private javax.swing.JPanel categoryContainer;
    private javax.swing.JScrollPane categoryScrollPane;
    private static javax.swing.JPanel currentOrderContainer;
    private javax.swing.JScrollPane currentOrderScrollPane;
    private javax.swing.JLabel currentOrderTitle;
    // End of variables declaration//GEN-END:variables
}
