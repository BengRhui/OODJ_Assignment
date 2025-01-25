/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.admin;

import backend.entity.Stall;
import backend.entity.Stall.StallCategories;
import frontend.pop_up.CategorySelection;
import frontend.pop_up.SystemPopUp;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author Chun Ming (TP068983), Beng Rhui (TP068495)
 */
public class StallInformationForm extends javax.swing.JFrame {

    private static Stall currentStall;
    private static StallCategories[] currentCategories;

    /**
     * Creates new form StallInformationForm
     */
    public StallInformationForm(Stall stall) {

        // Set the stall and category
        currentStall = stall;
        currentCategories = stall == null ? new StallCategories[0] : stall.getStallCategories();

        // Render the GUI
        initComponents();

        // Update information if stall is to be edited
        if (stall != null) initializeInformation();
        
        // Update the panel
        updateCategories();
    }
    
    public static void addItemToList(ArrayList<StallCategories> newCategories) {
        
        // Declare a new variable to store the overall lists
        ArrayList<StallCategories> newCategoryList = new ArrayList<>(Arrays.asList(currentCategories));

        // Add the new categories to list and sort them
        newCategoryList.addAll(newCategories);
        newCategoryList.sort(Comparator.comparing(StallCategories::toString));
        
        // Set the new list to the current list
        currentCategories = newCategoryList.toArray(new StallCategories[0]);
        
        // Update the panel
        updateCategories();
    }

    /**
     * This method helps to initialize the information in the frame.
     */
    private void initializeInformation() {

        // Retrieve the information
        String stallID = currentStall.getStallID();
        String stallName = currentStall.getStallName();

        // Set the values into the fields
        stallIDField.setText(stallID);
        stallNameField.setText(stallName);
        
        // Set the text field to black
        stallNameField.setForeground(Color.BLACK);

        // Initialize the categories field
        updateCategories();
    }

    /**
     * This method helps to update the category panel.
     */
    public static void updateCategories() {

        // Remove all elements in the panel
        categoryPane.removeAll();

        // Convert the category list into an array list
        ArrayList<StallCategories> categoryList = new ArrayList<>(Arrays.asList(currentCategories));

        // Check if the list is empty
        if (categoryList.isEmpty()) {

            // Create a JLabel to indicate that there is no category
            JLabel emptyLabel = new JLabel("No category is added for the stall.");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 18));

            // Add the label to the container
            categoryPane.add(emptyLabel);

        } else {
            
            // Start to iterate through the categories
            for (StallCategories category : categoryList) {

                // Retrieve the current string value of the iterator
                String categoryName = category.toString();

                // Generate a JLabel to display the category
                JLabel categoryLabel = new JLabel(categoryName);
                categoryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                categoryLabel.setBackground(Color.WHITE);
                categoryLabel.setOpaque(true);
                categoryLabel.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                                BorderFactory.createEmptyBorder(5, 10, 5, 10)
                        )
                );

                // Add a listener to the JLabel to remove the category when pressed
                categoryLabel.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {

                        // Remove the category from the list and update the global category list
                        categoryList.remove(category);
                        currentCategories = categoryList.toArray(new StallCategories[0]);

                        // Refresh the category panel
                        updateCategories();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        // Set cursor and change the label to red (to indicate removal)
                        categoryLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        categoryLabel.setBackground(new Color(100, 0, 0));
                        categoryLabel.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        // Reset the style for the label
                        categoryLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        categoryLabel.setBackground(Color.WHITE);
                        categoryLabel.setForeground(Color.BLACK);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {}

                    @Override
                    public void mouseReleased(MouseEvent e) {}
                });

                // Add the label to the panel
                categoryPane.add(categoryLabel);
            }
        }

        // Refresh the pane after everything
        categoryPane.revalidate();
        categoryPane.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentHolder = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        stallIDTitle = new javax.swing.JLabel();
        stallIDField = new javax.swing.JTextField();
        stallNameTitle = new javax.swing.JLabel();
        stallNameField = new javax.swing.JTextField();
        categoryTitle = new javax.swing.JLabel();
        addCategoryIcon = new javax.swing.JLabel();
        categoryScrollPane = new javax.swing.JScrollPane();
        categoryPane = new javax.swing.JPanel();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contentHolder.setBackground(new java.awt.Color(255, 251, 233));
        contentHolder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Stall Information");
        title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        contentHolder.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 500, -1));

        stallIDTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        stallIDTitle.setText("Stall ID");
        contentHolder.add(stallIDTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 210, 30));

        stallIDField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        stallIDField.setText(Stall.generateNewID());
        stallIDField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), javax.swing.BorderFactory.createEmptyBorder(2, 15, 2, 15)));
        stallIDField.setFocusable(false);
        contentHolder.add(stallIDField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 420, 50));

        stallNameTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        stallNameTitle.setText("Stall Name");
        contentHolder.add(stallNameTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 280, 30));

        stallNameField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        stallNameField.setForeground(new java.awt.Color(204, 204, 204));
        stallNameField.setText("Enter Stall Name");
        stallNameField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), javax.swing.BorderFactory.createEmptyBorder(2, 15, 2, 15)));
        stallNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                stallNameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                stallNameFieldFocusLost(evt);
            }
        });
        contentHolder.add(stallNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 420, 50));

        categoryTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        categoryTitle.setText("Category");
        contentHolder.add(categoryTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 130, 30));

        addCategoryIcon.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        addCategoryIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addCategoryIcon.setText("+");
        addCategoryIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addCategoryIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addCategoryIconMouseClicked(evt);
            }
        });
        contentHolder.add(addCategoryIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, 30, 30));

        categoryScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        categoryScrollPane.setBorder(null);

        categoryPane.setBackground(new java.awt.Color(255, 251, 233));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout1.setAlignOnBaseline(true);
        categoryPane.setLayout(flowLayout1);
        categoryScrollPane.setViewportView(categoryPane);

        contentHolder.add(categoryScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 420, 70));

        confirmButton.setBackground(new java.awt.Color(0, 0, 0));
        confirmButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        confirmButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmButton.setText("Confirm");
        confirmButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirmButtonMouseExited(evt);
            }
        });
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });
        contentHolder.add(confirmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 460, 200, 48));

        cancelButton.setBackground(Color.WHITE);
        cancelButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
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
        contentHolder.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 200, 48));

        getContentPane().add(contentHolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered

        // Change cursor and set background and foreground
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setBackground(new Color(206, 171, 147));
    }//GEN-LAST:event_cancelButtonMouseEntered

    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited

        // Change cursor and set background and foreground
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        cancelButton.setBackground(Color.WHITE);
    }//GEN-LAST:event_cancelButtonMouseExited

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        
        // Enable the main frame
        MainPage.currentFrame.setEnabled(true);
        
        // Dispose the current pop up
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void confirmButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseEntered

        // Change cursor and set background and foreground
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmButton.setBackground(new Color(206, 171, 147));
        confirmButton.setForeground(Color.BLACK);
    }//GEN-LAST:event_confirmButtonMouseEntered

    private void confirmButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseExited

        // Change cursor and set background and foreground
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        confirmButton.setBackground(Color.BLACK);
        confirmButton.setForeground(Color.WHITE);
    }//GEN-LAST:event_confirmButtonMouseExited

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed

        // Retrieve the information
        String stallID = stallIDField.getText();
        String stallName = stallNameField.getText();
        String[] stallCategories = new String[currentCategories.length];
        
        for (int i = 0; i < currentCategories.length; i++) {
            stallCategories[i] = currentCategories[i].toString();
        }
        
        // For adding stall
        if (currentStall == null) {
            
            // Try to add the stall
            int status = Stall.createNewStall(stallID, stallName, stallCategories);
            
            // Successful adding stall
            if (status == 1) {
            
                // Create a message to notify that stall is created successfully
                SystemPopUp successCreation = new SystemPopUp(
                        this,
                        "Stall Created Successfully",
                        "The stall is created successfully.",
                        new String[]{"OK"}
                );
                successCreation.setVisible(true);
                
                // Update the panel
                StallListPanel.updatePanel();
                
                // Dispose the form
                dispose();
                
                // Enable the parent frame
                MainPage.currentFrame.setEnabled(true);
                
            } else {
            
                // Unsuccessful attempt. Declare a variable to store error message
                String errorMessage = null;
                
                // Generate different error message based on modification status
                switch (status) {
                    case 0 -> errorMessage = "Please make sure that you have filled in all inputs.";
                    case -1 -> errorMessage = "The stall name has been used by another stall. Please try another name.";
                    case -2 -> errorMessage = "Invalid category detected. Please inspect code.";
                }

                // Generate the error message
                SystemPopUp errorMessagePopUp = new SystemPopUp(
                        this,
                        "Error",
                        errorMessage,
                        new String[]{"OK"}
                );
                errorMessagePopUp.setVisible(true);
            }
            
        } else {
            
            // Modify the stall
            int status = currentStall.modifyStall(stallName, stallCategories);

            // If modification is succesfful
            if (status == 1) {
            
                // Generate a message to notify users
                SystemPopUp successMessage = new SystemPopUp(
                        this,
                        "Modification Successful",
                        "The stall information is updated successfully.",
                        new String[]{"OK"}
                );
                successMessage.setVisible(true);

                // Refresh the pane
                StallListPanel.updatePanel();

                // Enable the main frame
                MainPage.currentFrame.setEnabled(true);

                // Dispose the current pop up
                dispose();

            } else {

                // Declare a variable to store error messages
                String errorMessage = null;

                // Generate different error message based on modification status
                switch (status) {
                    case 0 -> errorMessage = "Please make sure that you have filled in all inputs.";
                    case -1 -> errorMessage = "The stall name has been used by another stall. Please try another name.";
                    case -2 -> errorMessage = "Invalid category detected. Please inspect code.";
                    case -3 -> errorMessage = "Notification for vendors cannot be created. Please inspect code.";
                }

                // Generate the error message
                SystemPopUp errorMessagePopUp = new SystemPopUp(
                        this,
                        "Error",
                        errorMessage,
                        new String[]{"OK"}
                );
                errorMessagePopUp.setVisible(true);
            }
        }
        
        
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void addCategoryIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCategoryIconMouseClicked

        // Call the category pop up for users to select category
        CategorySelection categoryPopUp = new CategorySelection(
                this,
                currentCategories
        );
        categoryPopUp.setVisible(true);
    }//GEN-LAST:event_addCategoryIconMouseClicked

    private void stallNameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stallNameFieldFocusGained

        // If the prompt is still there
        if (stallNameField.getText().equals("Enter Stall Name")) {
            
            // Remove the prompt
            stallNameField.setText("");
            
            // Set the text colour to black
            stallNameField.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_stallNameFieldFocusGained

    private void stallNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stallNameFieldFocusLost

        // If the prompt is blank
        if (stallNameField.getText().isBlank()) {
            
            // Set the prompt
            stallNameField.setText("Enter Stall Name");
            
            // Set the text colour to grey
            stallNameField.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_stallNameFieldFocusLost

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
            java.util.logging.Logger.getLogger(StallInformationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StallInformationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StallInformationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StallInformationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Stall stall = new Stall("S002", "Testing stall", new StallCategories[]{StallCategories.WESTERN});
                new StallInformationForm(stall).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addCategoryIcon;
    private javax.swing.JButton cancelButton;
    private static javax.swing.JPanel categoryPane;
    private javax.swing.JScrollPane categoryScrollPane;
    private javax.swing.JLabel categoryTitle;
    private javax.swing.JButton confirmButton;
    private javax.swing.JPanel contentHolder;
    private javax.swing.JTextField stallIDField;
    private javax.swing.JLabel stallIDTitle;
    private javax.swing.JTextField stallNameField;
    private javax.swing.JLabel stallNameTitle;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
