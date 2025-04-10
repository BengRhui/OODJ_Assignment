/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.admin;

import backend.entity.DeliveryRunner;
import backend.utility.Utility;
import frontend.pop_up.SystemPopUp;
import java.awt.Color;
import java.awt.Cursor;

/**
 *
 * @author Chun Ming (TP068983), Beng Rhui (TP068495)
 */
public class RunnerDetailsForm extends javax.swing.JFrame {

    private DeliveryRunner currentRunner;
    
    /**
     * Creates new form VendorDetailsForm
     */
    public RunnerDetailsForm(DeliveryRunner runner) {
        
        // Save the current vendor
        currentRunner = runner;
        
        // Render the GUI
        initComponents();
        
        // Initialize text if vendor detail is meant to be edited
        if (runner != null) initializeLabel();
        
    }

    private void initializeLabel() {
        
        // Retrieve the attributes of the current vendor
        String runnerID = currentRunner.getUserID();
        String runnerName = currentRunner.getName();
        String runnerContactNumber = currentRunner.getContactNumber();
        String runnerEmail = currentRunner.getEmail();
        String runnerPassword = currentRunner.getPassword();
        
        // Set the values to the text fields
        runnerIDField.setText(runnerID);
        runnerNameField.setText(runnerName);
        contactNumberField.setText(runnerContactNumber);
        emailField.setText(runnerEmail);
        passwordField.setText(runnerPassword);
        confirmPasswordField.setText(runnerPassword);
        
        // Change the text colour and echo char
        runnerIDField.setForeground(Color.BLACK);
        runnerNameField.setForeground(Color.BLACK);
        contactNumberField.setForeground(Color.BLACK);
        emailField.setForeground(Color.BLACK);
        passwordField.setForeground(Color.BLACK);
        confirmPasswordField.setForeground(Color.BLACK);
        
        passwordField.setEchoChar('•');
        confirmPasswordField.setEchoChar('•');
        
        // Disable the password field (only can be changed at the reset password page)
        passwordField.setEditable(false);
        passwordField.setFocusable(false);
        passwordField.setBackground(Color.WHITE);
        
        confirmPasswordField.setEditable(false);
        confirmPasswordField.setFocusable(false);
        confirmPasswordField.setBackground(Color.WHITE);
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
        detailsTitle = new javax.swing.JLabel();
        runnerIDLabel = new javax.swing.JLabel();
        runnerIDField = new javax.swing.JTextField();
        runnerNameLabel = new javax.swing.JLabel();
        runnerNameField = new javax.swing.JTextField();
        contactNumberLabel = new javax.swing.JLabel();
        contactNumberField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        confirmPasswordField = new javax.swing.JPasswordField();
        confirmPasswordLabel = new javax.swing.JLabel();
        note = new javax.swing.JLabel();
        note1 = new javax.swing.JLabel();
        note2 = new javax.swing.JLabel();
        note3 = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Delivery Runner Details");
        setAlwaysOnTop(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 251, 233));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        detailsTitle.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        detailsTitle.setText("Delivery Runner Details");
        jPanel1.add(detailsTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        runnerIDLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        runnerIDLabel.setText("Delivery Runner ID");
        jPanel1.add(runnerIDLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        runnerIDField.setEditable(false);
        runnerIDField.setBackground(new java.awt.Color(255, 255, 255));
        runnerIDField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        runnerIDField.setText(DeliveryRunner.generateNewID());
        runnerIDField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), javax.swing.BorderFactory.createEmptyBorder(2, 15, 2, 15)));
        runnerIDField.setFocusable(false);
        jPanel1.add(runnerIDField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 450, 50));

        runnerNameLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        runnerNameLabel.setText("Delivery Runner Name");
        jPanel1.add(runnerNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        runnerNameField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        runnerNameField.setForeground(new java.awt.Color(204, 204, 204));
        runnerNameField.setText("Enter Name Here");
        runnerNameField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), javax.swing.BorderFactory.createEmptyBorder(2, 15, 2, 15)));
        runnerNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                runnerNameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                runnerNameFieldFocusLost(evt);
            }
        });
        runnerNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                runnerNameFieldKeyTyped(evt);
            }
        });
        jPanel1.add(runnerNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 450, 50));

        contactNumberLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        contactNumberLabel.setText("Contact Number");
        jPanel1.add(contactNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, -1, -1));

        contactNumberField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        contactNumberField.setForeground(new java.awt.Color(204, 204, 204));
        contactNumberField.setText("Enter Contact Number (e.g. 012-3456789)");
        contactNumberField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), javax.swing.BorderFactory.createEmptyBorder(2, 15, 2, 15)));
        contactNumberField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                contactNumberFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                contactNumberFieldFocusLost(evt);
            }
        });
        contactNumberField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                contactNumberFieldKeyTyped(evt);
            }
        });
        jPanel1.add(contactNumberField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 450, 50));

        emailLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        emailLabel.setText("Email");
        jPanel1.add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, -1, -1));

        emailField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        emailField.setForeground(new java.awt.Color(204, 204, 204));
        emailField.setText("Enter Email Here");
        emailField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), javax.swing.BorderFactory.createEmptyBorder(2, 15, 2, 15)));
        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFieldFocusLost(evt);
            }
        });
        emailField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emailFieldKeyTyped(evt);
            }
        });
        jPanel1.add(emailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 580, 50));

        passwordLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        passwordLabel.setText("Password");
        jPanel1.add(passwordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, -1, -1));

        passwordField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        passwordField.setForeground(new java.awt.Color(204, 204, 204));
        passwordField.setText("Enter Password Here");
        passwordField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), javax.swing.BorderFactory.createEmptyBorder(2, 15, 2, 15)));
        passwordField.setEchoChar((char) 0);
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFieldFocusLost(evt);
            }
        });
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passwordFieldKeyTyped(evt);
            }
        });
        jPanel1.add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 310, 580, 50));

        confirmPasswordField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        confirmPasswordField.setForeground(new java.awt.Color(204, 204, 204));
        confirmPasswordField.setText("Retype Password Here");
        confirmPasswordField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), javax.swing.BorderFactory.createEmptyBorder(2, 15, 2, 15)));
        confirmPasswordField.setEchoChar((char) 0);
        confirmPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                confirmPasswordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                confirmPasswordFieldFocusLost(evt);
            }
        });
        confirmPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                confirmPasswordFieldKeyTyped(evt);
            }
        });
        jPanel1.add(confirmPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 430, 580, 50));

        confirmPasswordLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        confirmPasswordLabel.setText("Confirm Password");
        jPanel1.add(confirmPasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 390, -1, -1));

        note.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        note.setText(" •  Password should have at least one special characters (#, @, $, etc.)");
        jPanel1.add(note, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 530, 510, 30));

        note1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        note1.setText("<html><b>Note:</b>");
        note1.setToolTipText("");
        jPanel1.add(note1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 490, 60, 30));

        note2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        note2.setText(" •  Password should consist of 8 to 20 characters");
        jPanel1.add(note2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 490, 360, 30));

        note3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        note3.setText(" •  Password should have at least one number and one alphabet");
        jPanel1.add(note3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 510, 500, 30));

        confirmButton.setBackground(new java.awt.Color(0, 0, 0));
        confirmButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        confirmButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmButton.setText("Confirm");
        confirmButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        confirmButton.setFocusPainted(false);
        confirmButton.setOpaque(true);
        confirmButton.setPreferredSize(new java.awt.Dimension(200, 50));
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
        jPanel1.add(confirmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 580, -1, -1));

        cancelButton.setBackground(Color.WHITE);
        cancelButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        cancelButton.setFocusPainted(false);
        cancelButton.setOpaque(true);
        cancelButton.setPreferredSize(new java.awt.Dimension(200, 50));
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
        jPanel1.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 580, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered

        // Change cursor and set background
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setBackground(new Color(206, 171, 147));
    }//GEN-LAST:event_cancelButtonMouseEntered

    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited

        // Change cursor and set background
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        cancelButton.setBackground(Color.WHITE);
    }//GEN-LAST:event_cancelButtonMouseExited

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        
        // Enable the parent frame
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

        // Retrieve the relevant information 
        String runnerID = runnerIDField.getText();
        String runnerName = runnerNameField.getText().strip();
        String runnerContactNumber = contactNumberField.getText().strip();
        String runnerEmail = emailField.getText().strip().toLowerCase();
        char[] runnerPassword = passwordField.getPassword();
        char[] confirmPassword = confirmPasswordField.getPassword();
        
        // A variable to store the status of the operation
        int status;
        
        // Add runner
        if (currentRunner == null) {
        
            // Try to create a new runner
            status = DeliveryRunner.createNewRunner(
                    runnerID,
                    runnerName,
                    runnerContactNumber,
                    runnerEmail,
                    runnerPassword,
                    confirmPassword
            );
            
        } else {
            
            // Modify the existing runner
            status = currentRunner.modifyRunner(
                    runnerName,
                    runnerContactNumber,
                    runnerEmail,
                    runnerPassword,
                    confirmPassword
            );
        }
        
        // Refresh the panes
        UserListPanel.updatePanel();
        
        // Create an empty string to store error message
        String errorMessage = null;
        
        // Generate different error messages
        switch (status) {
            case 0 -> errorMessage = "No empty inputs are allowed. Please make sure that you fill in all the information.";
            case -1 -> errorMessage = "Your email is not in the correct format. Please try again.";
            case -2 -> errorMessage = "Your email has been used by another user. Please try another email.";
            case -3 -> errorMessage = "Your password does not meet the requirement. Please use another password.";
            case -4 -> errorMessage = "Both passwords do not match. Please try again.";
            case -5 -> errorMessage = "Your contact number is not in the correct format. Please try again.";
            case -6 -> errorMessage = "Unable to generate notification for the vendor. Please inspect code.";
        }
        
        // If there is no error message (vendor created / modified successfully)
        if (errorMessage == null) {
            
            // Generate title and description for the notification
            String successTitle = currentRunner == null ? 
                    "Account Created Successfully" : 
                    "Information Updated Successfully";
            
            String successMessage = currentRunner == null ? 
                    "The delivery runner account has been created successfully." : 
                    "The delivery runner information is updated successfully";

            // Display a message to indicate success
            SystemPopUp successCreation = new SystemPopUp(
                    MainPage.currentFrame,
                    successTitle,
                    successMessage,
                    new String[]{"OK"}
            );
            successCreation.setVisible(true);
            
            // Refresh the panel
            UserListPanel.updatePanel();
            
            // Dispose the current form
            dispose();
            
            // Enable the user page
            MainPage.currentFrame.setEnabled(true);
            
        } else {
        
            // Create a message to indicate failure in creation / modification
            SystemPopUp failToCreate = new SystemPopUp(
                    MainPage.currentFrame,
                    "Error",
                    errorMessage,
                    new String[]{"OK"}
            );
            failToCreate.setVisible(true);
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void runnerNameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_runnerNameFieldFocusGained

        // If the text is enter name here
        if (runnerNameField.getText().equals("Enter Name Here")) {

            // Clear the prompt
            runnerNameField.setText("");

            // Set the text colour to black
            runnerNameField.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_runnerNameFieldFocusGained

    private void runnerNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_runnerNameFieldFocusLost

        // If the text is empty
        if (runnerNameField.getText().isBlank()) {

            // Add the prompt
            runnerNameField.setText("Enter Name Here");

            // Set the text colour to grey
            runnerNameField.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_runnerNameFieldFocusLost

    private void contactNumberFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contactNumberFieldFocusGained
        
        // If the text is enter contact number
        if (contactNumberField.getText().equals("Enter Contact Number (e.g. 012-3456789)")) {
            
            // Clear the prompt
            contactNumberField.setText("");
            
            // Set the text colour to black
            contactNumberField.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_contactNumberFieldFocusGained

    private void contactNumberFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contactNumberFieldFocusLost

        // If the text is empty
        if (contactNumberField.getText().isBlank()) {
        
            // Add the prompt
            contactNumberField.setText("Enter Contact Number (e.g. 012-3456789)");
            
            // Set the text colour to grey
            contactNumberField.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_contactNumberFieldFocusLost

    private void emailFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusGained
        
        // If the text is enter email here
        if (emailField.getText().equals("Enter Email Here")) {
            
            // Clear the prompt
            emailField.setText("");
            
            // Set the text colour to black
            emailField.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_emailFieldFocusGained

    private void emailFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusLost

        // If the text is empty
        if (emailField.getText().isBlank()) {
        
            // Add the prompt
            emailField.setText("Enter Email Here");
            
            // Set the text colour to grey
            emailField.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_emailFieldFocusLost

    private void passwordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusGained

        // If the text is enter password here
        if (Utility.generateString(passwordField.getPassword()).equals("Enter Password Here")) {
            
            // Clear the prompt
            passwordField.setText("");
            
            // Set the text colour to black
            passwordField.setForeground(Color.BLACK);
            
            // Set the echo char to dot
            passwordField.setEchoChar('•');
        }
    }//GEN-LAST:event_passwordFieldFocusGained

    private void passwordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusLost

        // If the password is empty
        if (passwordField.getPassword().length == 0) {
        
            // Add the prompt
            passwordField.setText("Enter Password Here");
            
            // Set the text colour to grey
            passwordField.setForeground(new Color(204, 204, 204));
            
            // Set echo char to normal character
            passwordField.setEchoChar((char) 0);
        }
    }//GEN-LAST:event_passwordFieldFocusLost

    private void confirmPasswordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmPasswordFieldFocusGained

        // If the text is enter password here
        if (Utility.generateString(confirmPasswordField.getPassword()).equals("Retype Password Here")) {
            
            // Clear the prompt
            confirmPasswordField.setText("");
            
            // Set the text colour to black
            confirmPasswordField.setForeground(Color.BLACK);
            
            // Set the echo char to dot
            confirmPasswordField.setEchoChar('•');
        }
    }//GEN-LAST:event_confirmPasswordFieldFocusGained

    private void confirmPasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmPasswordFieldFocusLost

        // If the password is empty
        if (confirmPasswordField.getPassword().length == 0) {
        
            // Add the prompt
            confirmPasswordField.setText("Retype Password Here");
            
            // Set the text colour to grey
            confirmPasswordField.setForeground(new Color(204, 204, 204));
            
            // Set echo char to normal character
            confirmPasswordField.setEchoChar((char) 0);
        }
    }//GEN-LAST:event_confirmPasswordFieldFocusLost

    private void runnerNameFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_runnerNameFieldKeyTyped

        // If the key entered is ";"
        if (evt.getKeyChar() == ';') {
            
            // Remove it
            evt.consume();
        }
    }//GEN-LAST:event_runnerNameFieldKeyTyped

    private void contactNumberFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contactNumberFieldKeyTyped

        // If the key entered is ";"
        if (evt.getKeyChar() == ';') {
            
            // Remove it
            evt.consume();
        }
    }//GEN-LAST:event_contactNumberFieldKeyTyped

    private void emailFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailFieldKeyTyped

        // If the key entered is ";"
        if (evt.getKeyChar() == ';') {
            
            // Remove it
            evt.consume();
        }
    }//GEN-LAST:event_emailFieldKeyTyped

    private void passwordFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyTyped

        // If the key entered is ";"
        if (evt.getKeyChar() == ';') {
            
            // Remove it
            evt.consume();
        }
    }//GEN-LAST:event_passwordFieldKeyTyped

    private void confirmPasswordFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confirmPasswordFieldKeyTyped

        // If the key entered is ";"
        if (evt.getKeyChar() == ';') {
            
            // Remove it
            evt.consume();
        }
    }//GEN-LAST:event_confirmPasswordFieldKeyTyped

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
            java.util.logging.Logger.getLogger(RunnerDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RunnerDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RunnerDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RunnerDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RunnerDetailsForm(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JTextField contactNumberField;
    private javax.swing.JLabel contactNumberLabel;
    private javax.swing.JLabel detailsTitle;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel note;
    private javax.swing.JLabel note1;
    private javax.swing.JLabel note2;
    private javax.swing.JLabel note3;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField runnerIDField;
    private javax.swing.JLabel runnerIDLabel;
    private javax.swing.JTextField runnerNameField;
    private javax.swing.JLabel runnerNameLabel;
    // End of variables declaration//GEN-END:variables
}
