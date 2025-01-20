/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.customer;

import backend.entity.Stall;
import backend.entity.Stall.StallCategories;
import backend.file_io.PictureIO;
import frontend.utility.RatingStarPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author limbengrhui
 */
public class StallBriefPanel extends javax.swing.JPanel {

    private static Stall currentStall;
    /**
     * Creates new form StallBriefPanel
     */
    public StallBriefPanel(Stall stall) {
        
        currentStall = stall;
        initComponents();
        
        initializeComponents();
    }

    private void initializeComponents() {
    
        initializePicture();
        initializeCategory();
        initializeStarRatings();
    }
    
    private void initializePicture() {
    
        // Retrieve the stall picture
        File stallPictureFile = PictureIO.retrieveBackgroundPicture(currentStall);
            
        // Declare an empty variable to store picture
        ImageIcon stallPictureIcon;

        // Resize picture to maintain ratio aspect
        try {

            // Get the image buffer
            BufferedImage initialImage = ImageIO.read(stallPictureFile);

            // Retrieve the dimensions for the new picture
            int originalWidth = initialImage.getWidth();
            int originalHeight = initialImage.getHeight();
            int widthAfterResized = 340;
            double scalingRatio = widthAfterResized / (originalWidth + 0.0);
            int heightAfterResized = (int) (originalHeight * scalingRatio);

            // Scale the image and get the image icon
            Image scaledImage = initialImage.getScaledInstance(widthAfterResized, heightAfterResized, Image.SCALE_SMOOTH);
            stallPictureIcon = new ImageIcon(scaledImage);

        } catch (IOException e) {

            // Throw exception if the picture cannot be retrieved
            throw new IllegalStateException("Unable to retrieve picture.");
        }
        
        // Set the picture
        stallPicture.setIcon(stallPictureIcon);
    }
    
    private void initializeCategory() {
        
        // Retrieve the categories of the stall
        StallCategories[] currentCategories = currentStall.getStallCategories();
        
        if (currentCategories.length == 0) {
        
            // Generate a JPanel to store label
            JPanel emptyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            
            // Generate an empty label to state that the item is unavailable
            JLabel emptyLabel = new JLabel("No category available for the stall.");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 18));

            // Add the label to the empty panel
            emptyPanel.add(emptyLabel);

            // Add the empty label to the container panel and return it
            stallCategoryField.add(emptyPanel);

        } else {
            
            // Declare an array list to store the labels
            ArrayList<JLabel> categoryLabelList = new ArrayList<>();
            
            // Loop through the categories of the stall
            for (StallCategories category : currentCategories) {

                // Create a label to display the description
                JLabel categoryLabel = new JLabel(category.toString());
                categoryLabel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.BLACK, 1, true),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20))
                );
                categoryLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                categoryLabel.setBackground(Color.WHITE);
                categoryLabel.setOpaque(true);

                // Add the description to the container panel
                stallCategoryField.add(categoryLabel);    
                
                // Add the label to array (to calculate container size)
                categoryLabelList.add(categoryLabel);
            }
            
            // Declare dimensions for the panel
            int hGapBetweenItem = 20;
            int vGapBetweenItem = 10;
            int descriptionHeight = categoryLabelList.getFirst().getPreferredSize().height;
            int currentWidth = 0;
            int panelWidth = 660;
            int panelHeight = vGapBetweenItem + descriptionHeight + vGapBetweenItem;

            // Loop through each label
            for (JLabel categoryLabel : categoryLabelList) {

                // Get the width for each label
                int descriptionWidth = categoryLabel.getPreferredSize().width;

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
            stallCategoryField.setPreferredSize(new Dimension(panelWidth, panelHeight));
        }
    }
    
    private void initializeStarRatings() {
        
        // Retrieve the star ratings panel
        RatingStarPanel starPanel = new RatingStarPanel(currentStall.getOverallRatings());
        starPanel.setBounds(0, 10, 190, 30);
        
        // Add the star panel to the ratings panel
        starRatingsPanel.add(starPanel);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stallPicture = new javax.swing.JLabel();
        stallNameScrollPane = new javax.swing.JScrollPane();
        stallNameField = new javax.swing.JTextArea();
        stallCategoryScrollPane = new javax.swing.JScrollPane();
        stallCategoryField = new javax.swing.JPanel();
        ratingsField = new javax.swing.JLabel();
        starRatingsPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(227, 202, 165));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stallPicture.setOpaque(true);
        add(stallPicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 340, 194));

        stallNameScrollPane.setBorder(null);
        stallNameScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        stallNameScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        stallNameField.setEditable(false);
        stallNameField.setBackground(new java.awt.Color(227, 202, 165));
        stallNameField.setColumns(20);
        stallNameField.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        stallNameField.setRows(1);
        stallNameField.setText(currentStall.getStallName());
        stallNameField.setFocusable(false);
        stallNameField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stallNameFieldMouseClicked(evt);
            }
        });
        stallNameScrollPane.setViewportView(stallNameField);

        add(stallNameScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 650, 50));

        stallCategoryScrollPane.setBorder(null);
        stallCategoryScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        stallCategoryScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        stallCategoryField.setBackground(new java.awt.Color(227, 202, 165));
        stallCategoryField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stallCategoryFieldMouseClicked(evt);
            }
        });
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 10);
        flowLayout1.setAlignOnBaseline(true);
        stallCategoryField.setLayout(flowLayout1);
        stallCategoryScrollPane.setViewportView(stallCategoryField);

        add(stallCategoryScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 670, 90));

        ratingsField.setFont(new java.awt.Font("Arial", 1, 72)); // NOI18N
        ratingsField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ratingsField.setText(currentStall.getOverallRatings() == -1.0 ? "-" : String.format("%.1f", currentStall.getOverallRatings()));
        add(ratingsField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 30, 190, -1));

        starRatingsPanel.setBackground(new java.awt.Color(227, 202, 165));
        starRatingsPanel.setLayout(null);
        add(starRatingsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 120, 190, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void stallNameFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stallNameFieldMouseClicked
        
        // Create the stall main page
        StallDetailsPanel stallMainPage = new StallDetailsPanel(currentStall);
        MainPage.currentCardPanel.add(stallMainPage, "stallMainPage");

        // Display home page as default
        CardLayout cardLayout = (CardLayout) MainPage.currentCardPanel.getLayout();
        cardLayout.show(MainPage.currentCardPanel, "stallMainPage");
        MainPage.setCardString("stallMainPage");

        // Set the title of the frame
        MainPage.setFrameTitle("Items in Store");

        // Add to list as a record
        MainPage.addAdditionalPanels(stallMainPage);

        // Change the frame title
        MainPage.setFrameTitle("Items in Store");
    }//GEN-LAST:event_stallNameFieldMouseClicked

    private void stallCategoryFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stallCategoryFieldMouseClicked
        
        // Trigger the same action as the stall name mouse listener
        stallNameFieldMouseClicked(evt);
    }//GEN-LAST:event_stallCategoryFieldMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ratingsField;
    private javax.swing.JPanel stallCategoryField;
    private javax.swing.JScrollPane stallCategoryScrollPane;
    private javax.swing.JTextArea stallNameField;
    private javax.swing.JScrollPane stallNameScrollPane;
    private javax.swing.JLabel stallPicture;
    private javax.swing.JPanel starRatingsPanel;
    // End of variables declaration//GEN-END:variables
}
