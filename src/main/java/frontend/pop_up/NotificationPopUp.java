/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.pop_up;

import org.apache.xmlbeans.impl.store.Cur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author limbengrhui
 */
public class NotificationPopUp extends javax.swing.JFrame {

    /**
     * Private variables to be used to create notification pop up
     */
    private String popUpTitle;
    private String popUpDescription;
    private String[] popUpOptions;
    private static int status = 0;
    
    /**
     * Creates new form NotificationPopUp
     * 
     * @param title The title of the notification
     * @param description The description for the notification
     * @param optionList A list containing the text to be added to buttons (from left to right, either 1 or 2)
     */
    public NotificationPopUp(String title, String description, String[] optionList) {
        
        // Validate the inputs
        if (title == null || title.isBlank() || 
                description == null || description.isBlank() || 
                optionList == null || (optionList.length != 1 && optionList.length != 2)) {
            
            // Throw an exception when input is incorrect
            throw new IllegalArgumentException("Invalid arguments. The input should not be null or empty.");
        }
        
        // Set the inputs into the private variables
        this.popUpTitle = title;
        this.popUpDescription = description;
        this.popUpOptions = optionList;
        
        // Run the code that renders the GUI
        initComponents();
        
        // Get the information for adding buttons
        int gapBetweenButtons = 30;
        int numOfButtons = optionList.length;
        int buttonWidth = (buttonPanel.getWidth() - gapBetweenButtons * (numOfButtons - 1)) / numOfButtons ;
        int buttonHeight = buttonPanel.getHeight();

        // Loop through the list of text
        for (int i = 0; i < numOfButtons; i ++) {

            // Create a new button
            JButton newButton = new JButton();
            newButton.setText(optionList[i]);
            newButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            newButton.setFont(new Font("Arial", Font.BOLD, 18));

            // Set the colour of the button
            int indexFromBack = (numOfButtons - 1 - i) % 2;
            if (indexFromBack == 0) {
                newButton.setForeground(Color.WHITE);
                newButton.setBackground(Color.BLACK);
            } else {
                newButton.setForeground(Color.BLACK);
                newButton.setBackground(Color.WHITE);
            }

            // Set the border and other small details of the button
            newButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
            newButton.setFocusable(false);
            newButton.setOpaque(true);

            // Add mouse listener to the buttons (change cursor and colour when hovered)
            newButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {
                    newButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    newButton.setForeground(Color.BLACK);
                    newButton.setBackground(new Color(206, 171, 147));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    newButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    if (indexFromBack == 0) {
                        newButton.setForeground(Color.WHITE);
                        newButton.setBackground(Color.BLACK);
                    } else {
                        newButton.setForeground(Color.BLACK);
                        newButton.setBackground(Color.WHITE);
                    }
                }
            });

            // Add action listener to button
            int buttonIndex = i;
            newButton.addActionListener(e -> {
                status = buttonIndex;
                dispose();
            });

            // Add the button to button panel
            buttonPanel.add(newButton);
        }
    }
    
    /**
     * The method that has to be executed in order to run the notification
     * (coz we will need to let the current thread to wait until the notification is disposed, then only a status will be returned)
     *
     * @return The status of the selected option (0 for the first button, 1 for the second button (if available))
     */
    public int run() {

        // Make the notification frame visible
        setVisible(true);

        try {

            // Pause the execution of code until the "notify()" method is called
            synchronized (this) {
                wait(); // Wait for user interaction
            }

        } catch (InterruptedException e) {

            // Inform the system that the current thread is interrupted
            Thread.currentThread().interrupt();
        }

        // Return the status after the frame is disposed
        return status;
    }

    /**
     * This method overrides the initial dispose() method, where the {@code notify()} method is called before the usual dispose method takes place.
     */
    @Override
    public void dispose() {

        // Call the notify method to update the status
        synchronized (this) {
            notify();
        }

        // Continue the usual dispose operation
        super.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        notificationTitle = new javax.swing.JLabel();
        notificationDescription = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Confirmation");
        setMinimumSize(new java.awt.Dimension(500, 400));
        setName("notificationPopUp"); // NOI18N
        setSize(new java.awt.Dimension(500, 400));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backgroundPanel.setBackground(new java.awt.Color(255, 251, 233));
        backgroundPanel.setMinimumSize(new java.awt.Dimension(500, 400));
        backgroundPanel.setPreferredSize(new java.awt.Dimension(500, 400));
        backgroundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        notificationTitle.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        notificationTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notificationTitle.setText(popUpTitle);
        backgroundPanel.add(notificationTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 500, -1));

        notificationDescription.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        notificationDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notificationDescription.setText("<html><div style='text-align: center;'>" + popUpDescription + "</div></html>");
        notificationDescription.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        backgroundPanel.add(notificationDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 380, 110));

        buttonPanel.setBackground(new java.awt.Color(255, 255, 255, 0));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 0);
        flowLayout1.setAlignOnBaseline(true);
        buttonPanel.setLayout(flowLayout1);
        backgroundPanel.add(buttonPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 380, 60));

        getContentPane().add(backgroundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(NotificationPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotificationPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotificationPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotificationPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NotificationPopUp("ABC", "123", new String[]{"Okay"}).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JLabel notificationDescription;
    private javax.swing.JLabel notificationTitle;
    // End of variables declaration//GEN-END:variables
}
