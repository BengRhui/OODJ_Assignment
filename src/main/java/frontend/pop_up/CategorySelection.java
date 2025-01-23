/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package frontend.pop_up;

import backend.entity.Stall;
import frontend.admin.StallInformationForm;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Beng Rhui (TP068495)
 */
public class CategorySelection extends javax.swing.JDialog {

    private static JFrame parentFrame;
    private static ArrayList<Stall.StallCategories> newCategories;
    
    /**
     * Creates new form CategorySelection_
     */
    public CategorySelection(JFrame parent, Stall.StallCategories[] categoryList) {
        
        // Initialize pop up
        super(parent, true);
        initComponents();
        
        // Set location
        setLocationRelativeTo(parent);
        
        // Initialize array list
        newCategories = new ArrayList<>();
            
        // Set the parent frame
        parentFrame = parent;
        
        // Disable the parent frame
        parent.setEnabled(false);
        
        // Generate the list of categories
        generateCategory(categoryList);
    }
    
    /**
     * This method helps to generate the panel for the frame.
     * @param categoryList The list of categories that was being selected by user
     */
    private void generateCategory(Stall.StallCategories[] categoryList) {
        
        // Get the categories to be included in the panel
        ArrayList<Stall.StallCategories> overallCategory = new ArrayList<>(Arrays.asList(Stall.StallCategories.values()));
        overallCategory.removeAll(Arrays.asList(categoryList));
        
        // Check if the list is empty
        if (overallCategory.isEmpty()) {

            // Reset the layout of the panel
            categoryPanel.setLayout(null);

            // Create a JLabel to indicate that there is no category
            JLabel emptyLabel = new JLabel("No category to be added.");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            emptyLabel.setBounds(0, 0, 300, 30);

            // Add the label to the container
            categoryPanel.add(emptyLabel);

        } else {

            // Start to iterate through the categories
            for (Stall.StallCategories category : overallCategory) {

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
                            
                        // If the new category list does not contain the category
                        if (!newCategories.contains(category)) {
                            
                            // Change the label to green
                            categoryLabel.setBackground(new Color(0, 100, 0));
                            categoryLabel.setForeground(Color.WHITE);
                            newCategories.add(category);

                        } else {
                            
                            // Remove the category from the new category list
                            newCategories.remove(category);
                            
                            // Reset the label
                            categoryLabel.setBackground(Color.WHITE);
                            categoryLabel.setForeground(Color.BLACK);
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        
                        // Set cursor
                        categoryLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        
                        // Trigger change in colour if the category is not selected
                        if (!newCategories.contains(category)) {
                            categoryLabel.setBackground(new Color(0, 100, 0));
                            categoryLabel.setForeground(Color.WHITE);
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        // Set cursor
                        categoryLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        
                        // Reset colour if the category is not selected
                        if (!newCategories.contains(category)) {
                            categoryLabel.setBackground(Color.WHITE);
                            categoryLabel.setForeground(Color.BLACK);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {}

                    @Override
                    public void mouseReleased(MouseEvent e) {}
                });

                // Add the label to the panel
                categoryPanel.add(categoryLabel);
            }
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

        jPanel1 = new javax.swing.JPanel();
        popUpTitle = new javax.swing.JLabel();
        categoryPanel = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 251, 233));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        popUpTitle.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        popUpTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        popUpTitle.setText("Select Category Here");
        jPanel1.add(popUpTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 260, 40));

        categoryPanel.setBackground(new java.awt.Color(255, 251, 233));
        jPanel1.add(categoryPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 340, 150));

        cancelButton.setBackground(Color.WHITE);
        cancelButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
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
        jPanel1.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 160, 50));

        confirmButton.setBackground(new java.awt.Color(0, 0, 0));
        confirmButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        confirmButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmButton.setText("OK");
        confirmButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        confirmButton.setFocusPainted(false);
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
        jPanel1.add(confirmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 160, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 350));

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

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed

        // Enable the parent frame
        parentFrame.setEnabled(true);
        
        // Dispose the current frame
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        
        // Add to list if the new category list is not empty
        if (newCategories != null || !newCategories.isEmpty()) {
            StallInformationForm.addItemToList(newCategories);
        }
        
        // Enable the parent frame
        parentFrame.setEnabled(true);
        
        // Dispose the current frame
        dispose();
    }//GEN-LAST:event_confirmButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CategorySelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CategorySelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CategorySelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CategorySelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CategorySelection dialog = new CategorySelection(new javax.swing.JFrame(), new Stall.StallCategories[]{Stall.StallCategories.WESTERN});
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel categoryPanel;
    private javax.swing.JButton confirmButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel popUpTitle;
    // End of variables declaration//GEN-END:variables
}
