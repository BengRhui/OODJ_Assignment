/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.customer;

import backend.entity.Address;
import backend.entity.Customer;
import frontend.pop_up.SystemPopUp;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Jun Hong (TP068580), Beng Rhui (TP068495)
 */
public class DeliveryAddressForm extends javax.swing.JFrame {

    private static Customer currentCustomer;
    
    /**
     * Creates new form DeliveryAddressForm
     */
    public DeliveryAddressForm(Customer customer) {
        
        // Initialize customer
        currentCustomer = customer;
        
        // Render GUI components
        initComponents();
        
        // Initialize the fields
        initializeFields();
    }
    
    private void initializeFields() {
        
        // Get the details for the current customer
        String addressLine1 = currentCustomer.getAddress().getAddressLine1();
        String addressLine2 = currentCustomer.getAddress().getAddressLine2();
        String postcode = currentCustomer.getAddress().getPostcode();
        String city = currentCustomer.getAddress().getCity();
        String state = currentCustomer.getAddress().getState().toString();
        String deliveryNote = currentCustomer.getDeliveryNote();
        
        // Set the fields
        addressLineOneField.setText(addressLine1);
        addressLineTwoField.setText(addressLine2);
        postcodeField.setText(postcode);
        cityField.setText(city);
        stateField.setSelectedItem(state);
        deliveryNoteField.setText(deliveryNote);
        
        // Set the text colour to black
        addressLineOneField.setForeground(Color.BLACK);
        addressLineTwoField.setForeground(Color.BLACK);
        postcodeField.setForeground(Color.BLACK);
        cityField.setForeground(Color.BLACK);
        stateField.setForeground(Color.BLACK);
        deliveryNoteField.setForeground(Color.BLACK);
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
        panelTitle = new javax.swing.JLabel();
        addressTitle = new javax.swing.JLabel();
        descriptionTitle = new javax.swing.JLabel();
        addressLineOneField = new javax.swing.JTextField();
        addressLineTwoField = new javax.swing.JTextField();
        postcodeField = new javax.swing.JTextField();
        cityField = new javax.swing.JTextField();
        stateField = new javax.swing.JComboBox<>(
            Address.State.STATE_OPTIONS
        );
        deliveryNoteScrollPane = new javax.swing.JScrollPane();
        deliveryNoteField = new javax.swing.JTextArea();
        cancelButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Delivery Details");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 251, 233));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTitle.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        panelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelTitle.setText("Modify Address");
        jPanel1.add(panelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 40, 210, -1));

        addressTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        addressTitle.setText("Delivery Address");
        jPanel1.add(addressTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        descriptionTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        descriptionTitle.setText("Delivery Note");
        jPanel1.add(descriptionTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, -1, -1));

        addressLineOneField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        addressLineOneField.setForeground(new java.awt.Color(204, 204, 204));
        addressLineOneField.setText("Address Line 1");
        addressLineOneField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), javax.swing.BorderFactory.createEmptyBorder(2, 15, 2, 15)));
        addressLineOneField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addressLineOneFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                addressLineOneFieldFocusLost(evt);
            }
        });
        addressLineOneField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                addressLineOneFieldKeyTyped(evt);
            }
        });
        jPanel1.add(addressLineOneField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 380, 50));

        addressLineTwoField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        addressLineTwoField.setForeground(new java.awt.Color(204, 204, 204));
        addressLineTwoField.setText("Address Line 2");
        addressLineTwoField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), javax.swing.BorderFactory.createEmptyBorder(2, 15, 2, 15)));
        addressLineTwoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addressLineTwoFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                addressLineTwoFieldFocusLost(evt);
            }
        });
        addressLineTwoField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                addressLineTwoFieldKeyTyped(evt);
            }
        });
        jPanel1.add(addressLineTwoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 380, 50));

        postcodeField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        postcodeField.setForeground(new java.awt.Color(204, 204, 204));
        postcodeField.setText("Postcode");
        postcodeField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), javax.swing.BorderFactory.createEmptyBorder(2, 15, 2, 15)));
        postcodeField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                postcodeFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                postcodeFieldFocusLost(evt);
            }
        });
        postcodeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                postcodeFieldKeyTyped(evt);
            }
        });
        jPanel1.add(postcodeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 120, 50));

        cityField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cityField.setForeground(new java.awt.Color(204, 204, 204));
        cityField.setText("City");
        cityField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), javax.swing.BorderFactory.createEmptyBorder(2, 15, 2, 15)));
        cityField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cityFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cityFieldFocusLost(evt);
            }
        });
        cityField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cityFieldKeyTyped(evt);
            }
        });
        jPanel1.add(cityField, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 250, 50));

        stateField.setBackground(Color.WHITE);
        stateField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        stateField.setForeground(new java.awt.Color(204, 204, 204));
        stateField.setSelectedIndex(-1);
        stateField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        stateField.setFocusable(false);
        stateField.setOpaque(true);
        stateField.setRenderer(new DefaultListCellRenderer() {

            // Override the rendering method
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (index == -1 && value == null) {
                    label.setText("Select State Here");
                    label.setForeground(new Color(204, 204, 204));
                } else {
                    label.setForeground(Color.BLACK);
                }

                // Apply your existing custom rendering (size and border)
                label.setPreferredSize(new Dimension(label.getPreferredSize().width, 40));
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                return label;
            }
        });
        stateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateFieldActionPerformed(evt);
            }
        });
        jPanel1.add(stateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 380, 50));

        deliveryNoteScrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        deliveryNoteField.setColumns(20);
        deliveryNoteField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        deliveryNoteField.setForeground(new java.awt.Color(204, 204, 204));
        deliveryNoteField.setLineWrap(true);
        deliveryNoteField.setRows(3);
        deliveryNoteField.setText("Delivery Note");
        deliveryNoteField.setToolTipText("");
        deliveryNoteField.setWrapStyleWord(true);
        deliveryNoteField.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 15, 12, 15));
        deliveryNoteField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                deliveryNoteFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                deliveryNoteFieldFocusLost(evt);
            }
        });
        deliveryNoteField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deliveryNoteFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                deliveryNoteFieldKeyTyped(evt);
            }
        });
        deliveryNoteScrollPane.setViewportView(deliveryNoteField);

        jPanel1.add(deliveryNoteScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, 380, 100));

        cancelButton.setBackground(Color.WHITE);
        cancelButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelButtonMouseExited(evt);
            }
        });
        jPanel1.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 530, 180, 50));

        confirmButton.setBackground(new java.awt.Color(0, 0, 0));
        confirmButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        confirmButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmButton.setText("OK");
        confirmButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirmButtonMouseExited(evt);
            }
        });
        jPanel1.add(confirmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 530, 180, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateFieldActionPerformed

        // Change the text colour to black
        stateField.setForeground(Color.BLACK);
    }//GEN-LAST:event_stateFieldActionPerformed

    private void addressLineOneFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addressLineOneFieldFocusGained

        // If the prompt still exist
        if (addressLineOneField.getText().equals("Address Line 1")) {
            
            // Remove the prompt
            addressLineOneField.setText("");
            
            // Set the text colour to black
            addressLineOneField.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_addressLineOneFieldFocusGained

    private void addressLineOneFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addressLineOneFieldFocusLost

        // If the field is blank
        if (addressLineOneField.getText().isBlank()) {
            
            // Set the prompt
            addressLineOneField.setText("Address Line 1");
            
            // Set text colour to grey
            addressLineOneField.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_addressLineOneFieldFocusLost

    private void addressLineTwoFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addressLineTwoFieldFocusGained

        // If the prompt still exist
        if (addressLineTwoField.getText().equals("Address Line 2")) {
            
            // Remove the prompt
            addressLineTwoField.setText("");
            
            // Set the text colour to black
            addressLineTwoField.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_addressLineTwoFieldFocusGained

    private void addressLineTwoFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addressLineTwoFieldFocusLost

        // If the field is blank
        if (addressLineTwoField.getText().isBlank()) {
            
            // Set the prompt
            addressLineTwoField.setText("Address Line 2");
            
            // Set text colour to grey
            addressLineTwoField.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_addressLineTwoFieldFocusLost

    private void postcodeFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_postcodeFieldFocusGained

        // If the prompt still exist
        if (postcodeField.getText().equals("Postcode")) {
            
            // Remove the prompt
            postcodeField.setText("");
            
            // Set the text colour to black
            postcodeField.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_postcodeFieldFocusGained

    private void postcodeFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_postcodeFieldFocusLost

        // If the field is blank
        if (addressLineTwoField.getText().isBlank()) {
            
            // Set the prompt
            addressLineTwoField.setText("Address Line 2");
            
            // Set text colour to grey
            addressLineTwoField.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_postcodeFieldFocusLost

    private void cityFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cityFieldFocusGained

        // If the prompt still exist
        if (cityField.getText().equals("City")) {
            
            // Remove the prompt
            cityField.setText("");
            
            // Set the text colour to black
            cityField.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_cityFieldFocusGained

    private void cityFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cityFieldFocusLost

        // If the field is blank
        if (cityField.getText().isBlank()) {
            
            // Set the prompt
            cityField.setText("City");
            
            // Set text colour to grey
            cityField.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_cityFieldFocusLost

    private void deliveryNoteFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_deliveryNoteFieldFocusGained

        // If the prompt still exist
        if (deliveryNoteField.getText().equals("Delivery Note")) {
            
            // Remove the prompt
            deliveryNoteField.setText("");
            
            // Set the text colour to black
            deliveryNoteField.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_deliveryNoteFieldFocusGained

    private void deliveryNoteFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_deliveryNoteFieldFocusLost

        // If the prompt still exist
        if (deliveryNoteField.getText().isBlank()) {
            
            // Remove the prompt
            deliveryNoteField.setText("Delivery Note");
            
            // Set the text colour to black
            deliveryNoteField.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_deliveryNoteFieldFocusLost

    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered

        // Change the background
        cancelButton.setBackground(new Color(206, 171, 147));
    }//GEN-LAST:event_cancelButtonMouseEntered

    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited

        // Change the background
        cancelButton.setBackground(Color.WHITE);
    }//GEN-LAST:event_cancelButtonMouseExited

    private void confirmButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseEntered

        // Change the background and text colour
        confirmButton.setBackground(new Color(206, 171, 147));
        confirmButton.setForeground(Color.BLACK);
    }//GEN-LAST:event_confirmButtonMouseEntered

    private void confirmButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseExited

        // Change the background and text colour
        confirmButton.setBackground(Color.BLACK);
        confirmButton.setForeground(Color.WHITE);
    }//GEN-LAST:event_confirmButtonMouseExited

    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked

        // Enable the parent frame
        MainPage.currentFrame.setEnabled(true);
        
        // Dispose the current frame
        dispose();
    }//GEN-LAST:event_cancelButtonMouseClicked

    private void confirmButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseClicked

        // Retrieve information from the fields
        String addressLine1 = addressLineOneField.getText().strip();
        String addressLine2 = addressLineTwoField.getText().strip();
        String postcode = postcodeField.getText().strip();
        String city = cityField.getText().strip();
        Address.State state = Address.State.getFromString(stateField.getSelectedItem().toString());
        String deliveryNote = deliveryNoteField.getText().strip();
        
        // Perform modification
        boolean modifyStatus = currentCustomer.modifyAddressAndDeliveryNotes(
                addressLine1, 
                addressLine2, 
                postcode, 
                state, 
                city, 
                deliveryNote
        );
        
        // If modification is successful
        if (modifyStatus) {
        
            // Create a message to indicate empty values
            SystemPopUp successMessage = new SystemPopUp(
                    this,
                    "Modification Successful",
                    "You have successfully modified the delivery address and notes.",
                    new String[]{"OK"}
            );
            successMessage.setVisible(true);
            
            // Enable the parent frame
            MainPage.currentFrame.setEnabled(true);
            
            // Dispose this frame
            dispose();
            
        } else {
        
            // Create a message to indicate empty values
            SystemPopUp errorMessage = new SystemPopUp(
                    this,
                    "Blank Input",
                    "Please fill in all fields before submitting.",
                    new String[]{"OK"}
            );
            errorMessage.setVisible(true);
        }
    }//GEN-LAST:event_confirmButtonMouseClicked

    private void addressLineOneFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressLineOneFieldKeyTyped

        // If ';' is pressed
        if (evt.getKeyChar() == ';') {
        
            // Remove it
            evt.consume();
        }
    }//GEN-LAST:event_addressLineOneFieldKeyTyped

    private void addressLineTwoFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressLineTwoFieldKeyTyped

        // If ';' is pressed
        if (evt.getKeyChar() == ';') {
        
            // Remove it
            evt.consume();
        }
    }//GEN-LAST:event_addressLineTwoFieldKeyTyped

    private void postcodeFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_postcodeFieldKeyTyped

        // If ';' is pressed
        if (evt.getKeyChar() == ';') {
        
            // Remove it
            evt.consume();
        }
    }//GEN-LAST:event_postcodeFieldKeyTyped

    private void cityFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cityFieldKeyTyped

        // If ';' is pressed
        if (evt.getKeyChar() == ';') {
        
            // Remove it
            evt.consume();
        }
    }//GEN-LAST:event_cityFieldKeyTyped

    private void deliveryNoteFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deliveryNoteFieldKeyTyped

        // If ';' is pressed
        if (evt.getKeyChar() == ';') {
        
            // Remove it
            evt.consume();
        }
    }//GEN-LAST:event_deliveryNoteFieldKeyTyped

    private void deliveryNoteFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deliveryNoteFieldKeyPressed

        // If enter key is pressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        
            // Remove it
            evt.consume();
        }
    }//GEN-LAST:event_deliveryNoteFieldKeyPressed

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
            java.util.logging.Logger.getLogger(DeliveryAddressForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeliveryAddressForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeliveryAddressForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeliveryAddressForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeliveryAddressForm(currentCustomer).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressLineOneField;
    private javax.swing.JTextField addressLineTwoField;
    private javax.swing.JLabel addressTitle;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField cityField;
    private javax.swing.JButton confirmButton;
    private javax.swing.JTextArea deliveryNoteField;
    private javax.swing.JScrollPane deliveryNoteScrollPane;
    private javax.swing.JLabel descriptionTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel panelTitle;
    private javax.swing.JTextField postcodeField;
    private javax.swing.JComboBox<String> stateField;
    // End of variables declaration//GEN-END:variables
}
