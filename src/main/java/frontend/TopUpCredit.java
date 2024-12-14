package frontend;

import java.awt.Cursor;

/**
 *
 * @author Chun Ming
 */
public class TopUpCredit extends javax.swing.JPanel {

    public AdminFrame adminFrame;

    public TopUpCredit() {
        initComponents();
        textFormatter();
    }

    private void textFormatter() {
        //This function is to set the text and format of the JLabel
        byName.setText("<html>Search <br> by Name:</html>");
        byContactNo.setText("<html>Search by<br>Contact No.</html>");
    }

    public void setAdminFrame(AdminFrame adminFrame) {
        this.adminFrame = adminFrame;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Page Layout Structure">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgLayer = new javax.swing.JPanel();
        customerCredit = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        userListScroll = new javax.swing.JScrollPane();
        byName = new javax.swing.JLabel();
        searchByName = new javax.swing.JTextField();
        byContactNo = new javax.swing.JLabel();
        searchByContact = new javax.swing.JTextField();
        background = new javax.swing.JLabel();

        setLayout(null);

        bgLayer.setBackground(new java.awt.Color(255, 251, 233));
        bgLayer.setLayout(null);

        customerCredit.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        customerCredit.setText("Customer Credit");
        bgLayer.add(customerCredit);
        customerCredit.setBounds(80, 50, 390, 70);

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/logout_icon.png"))); // NOI18N
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutMouseExited(evt);
            }
        });
        bgLayer.add(logout);
        logout.setBounds(1230, 60, 70, 60);

        back.setBackground(new java.awt.Color(173, 139, 115));
        back.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setText("Back");
        back.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        back.setPreferredSize(new java.awt.Dimension(200, 50));
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backMouseExited(evt);
            }
        });
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        bgLayer.add(back);
        back.setBounds(1070, 710, 230, 60);

        userListScroll.setBackground(new java.awt.Color(255, 255, 255));
        userListScroll.setForeground(new java.awt.Color(255, 255, 255));
        bgLayer.add(userListScroll);
        userListScroll.setBounds(90, 230, 1210, 450);

        byName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        byName.setText("Search \nby Name:");
        byName.setToolTipText("");
        bgLayer.add(byName);
        byName.setBounds(90, 160, 110, 60);

        searchByName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        bgLayer.add(searchByName);
        searchByName.setBounds(240, 160, 520, 60);

        byContactNo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        byContactNo.setText("Search by \nContact No.:");
        bgLayer.add(byContactNo);
        byContactNo.setBounds(800, 160, 120, 60);

        searchByContact.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        searchByContact.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        bgLayer.add(searchByContact);
        searchByContact.setBounds(930, 160, 370, 60);

        add(bgLayer);
        bgLayer.setBounds(0, 0, 1400, 800);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/customer_credit.jpg"))); // NOI18N
        add(background);
        background.setBounds(0, 0, 1460, 800);
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseEntered
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.setBackground(new java.awt.Color(173, 120, 110));
    }//GEN-LAST:event_backMouseEntered

    private void backMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseExited
        back.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        back.setBackground(new java.awt.Color(173, 139, 115));
    }//GEN-LAST:event_backMouseExited

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        adminFrame.cardLayout.show(adminFrame.getPageHolder(), "adminHome");
    }//GEN-LAST:event_backActionPerformed

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        // Here will redirect to MainHome
    }//GEN-LAST:event_logoutMouseClicked

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_logoutMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel background;
    private javax.swing.JPanel bgLayer;
    private javax.swing.JLabel byContactNo;
    private javax.swing.JLabel byName;
    private javax.swing.JLabel customerCredit;
    private javax.swing.JLabel logout;
    private javax.swing.JTextField searchByContact;
    private javax.swing.JTextField searchByName;
    private javax.swing.JScrollPane userListScroll;
    // End of variables declaration//GEN-END:variables
}
