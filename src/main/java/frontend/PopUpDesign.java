package frontend;

/**
 *
 * @author User
 */
public class PopUpDesign extends javax.swing.JFrame {

    public PopUpDesign() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentHolder = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();
        footer = new javax.swing.JPanel();
        filterType = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        contentHolder.setBackground(new java.awt.Color(255, 251, 233));
        contentHolder.setLayout(null);

        title.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Title");
        title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        contentHolder.add(title);
        title.setBounds(35, 18, 430, 42);

        cancelButton.setBackground(new java.awt.Color(227, 202, 165));
        cancelButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cancelButton.setText("No");
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
        contentHolder.add(cancelButton);
        cancelButton.setBounds(35, 214, 200, 48);

        confirmButton.setBackground(new java.awt.Color(173, 139, 115));
        confirmButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        confirmButton.setText("Yes");
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
        contentHolder.add(confirmButton);
        confirmButton.setBounds(265, 214, 200, 48);

        footer.setBackground(new java.awt.Color(255, 251, 233));

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        contentHolder.add(footer);
        footer.setBounds(0, 262, 500, 38);

        filterType.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        filterType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Customer", "Vendor", "Runner" }));
        filterType.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        contentHolder.add(filterType);
        filterType.setBounds(30, 100, 430, 60);

        getContentPane().add(contentHolder, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelButtonMouseExited

    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelButtonMouseEntered

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void confirmButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmButtonMouseExited

    private void confirmButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmButtonMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JPanel contentHolder;
    private javax.swing.JComboBox<String> filterType;
    private javax.swing.JPanel footer;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
