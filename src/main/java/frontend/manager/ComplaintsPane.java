/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.manager;

/**
 *
 * @author Chun Ming
 */
public class ComplaintsPane extends javax.swing.JPanel {

    public ComplaintsPane() {
        initComponents();
        initLayout();
    }


    private void initLayout() {
        this.setBackground(new java.awt.Color(255, 251, 233, 0));
        bgLayer.setBackground(new java.awt.Color(255, 251, 233, 240));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgLayer = new javax.swing.JPanel();
        vendorComplaintsScroll = new javax.swing.JScrollPane();
        runnerComplaintsScroll = new javax.swing.JScrollPane();
        systemComplaintsScroll = new javax.swing.JScrollPane();
        systemLabel = new javax.swing.JLabel();
        vendorLabel = new javax.swing.JLabel();
        runnerLabel = new javax.swing.JLabel();
        bottomBackground = new javax.swing.JLabel();

        setLayout(null);

        bgLayer.setBackground(new java.awt.Color(255, 251, 233));
        bgLayer.setLayout(null);
        bgLayer.add(vendorComplaintsScroll);
        vendorComplaintsScroll.setBounds(500, 81, 400, 540);
        bgLayer.add(runnerComplaintsScroll);
        runnerComplaintsScroll.setBounds(950, 81, 400, 540);
        bgLayer.add(systemComplaintsScroll);
        systemComplaintsScroll.setBounds(50, 81, 400, 540);

        systemLabel.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        systemLabel.setText("System");
        bgLayer.add(systemLabel);
        systemLabel.setBounds(50, 35, 180, 40);

        vendorLabel.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        vendorLabel.setText("Vendor");
        bgLayer.add(vendorLabel);
        vendorLabel.setBounds(500, 35, 180, 40);

        runnerLabel.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        runnerLabel.setText("Runner");
        bgLayer.add(runnerLabel);
        runnerLabel.setBounds(950, 35, 180, 40);

        add(bgLayer);
        bgLayer.setBounds(0, 0, 1400, 650);

        bottomBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/manager_background_bottom.png"))); // NOI18N
        add(bottomBackground);
        bottomBackground.setBounds(0, 0, 1400, 650);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgLayer;
    private javax.swing.JLabel bottomBackground;
    private javax.swing.JScrollPane runnerComplaintsScroll;
    private javax.swing.JLabel runnerLabel;
    private javax.swing.JScrollPane systemComplaintsScroll;
    private javax.swing.JLabel systemLabel;
    private javax.swing.JScrollPane vendorComplaintsScroll;
    private javax.swing.JLabel vendorLabel;
    // End of variables declaration//GEN-END:variables
}
