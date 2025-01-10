/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package frontend.pop_up;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author limbengrhui
 */
public class NotificationPopUp extends javax.swing.JDialog {

    private static int status = -1;
    private Frame parent;
    private String popUpTitle;
    private String popUpDescription;
    private String[] popUpOptions;

    /**
     * Creates new form Testing
     *
     * @param parent The parent frame of the JDialog
     */
    public NotificationPopUp(java.awt.Frame parent, String title, String description, String[] optionList) {
        super(parent, "Confirmation", true);

        // Validate the inputs
        if (title == null || title.isBlank() ||
                description == null || description.isBlank() ||
                optionList == null || (optionList.length != 1 && optionList.length != 2)) {

            // Throw an exception when input is incorrect
            throw new IllegalArgumentException("Invalid arguments. The input should not be null or empty.");
        }

        // Set the inputs into the private variables
        this.parent = parent;
        this.popUpTitle = title;
        this.popUpDescription = description;
        this.popUpOptions = optionList;

        initComponents();

        addButtonToPanel(buttonPanel, popUpOptions);

    }

    private void addButtonToPanel(JPanel panel, String[] optionList) {

        // Get the information for adding buttons
        int gapBetweenButtons = 30;
        int numOfButtons = optionList.length;
        int buttonWidth = (panel.getWidth() - gapBetweenButtons * (numOfButtons - 1)) / numOfButtons ;
        int buttonHeight = panel.getHeight();

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
            panel.add(newButton);
        }
    }

    /**
     * A method to return the status of the notification after it is disposed.
     * @return The status of the notification
     */
    public int getStatus() {
        return status;
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
        titleText = new javax.swing.JLabel();
        descriptionText = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Confirmation");
        setAlwaysOnTop(true);
        setLocation(parent.getX() + (parent.getWidth() - 500) / 2,
            parent.getY() + (parent.getHeight() - 350) / 2);
        setName("notificationDialog"); // NOI18N
        setPreferredSize(new java.awt.Dimension(500, 350));
        setResizable(false);
        setSize(new java.awt.Dimension(500, 350));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backgroundPanel.setBackground(new java.awt.Color(255, 251, 233));
        backgroundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleText.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        titleText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleText.setText(popUpTitle);
        backgroundPanel.add(titleText, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 500, -1));

        descriptionText.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        descriptionText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descriptionText.setText("<html><div style='text-align: center;'>" + popUpDescription + "</div></html>");
        descriptionText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        backgroundPanel.add(descriptionText, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 100, 500, 90));

        buttonPanel.setBackground(new java.awt.Color(255, 251, 233));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 0);
        flowLayout1.setAlignOnBaseline(true);
        buttonPanel.setLayout(flowLayout1);
        backgroundPanel.add(buttonPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 380, 60));

        getContentPane().add(backgroundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 350));

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
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NotificationPopUp dialog = new NotificationPopUp(new javax.swing.JFrame(), "ABC", "123", new String[]{"Okay"});
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
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JLabel descriptionText;
    private javax.swing.JLabel titleText;
    // End of variables declaration//GEN-END:variables
}
