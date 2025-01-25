/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.manager_CM;

/**
 *
 * @author Chun Ming
 */
public class CheckVendor extends javax.swing.JPanel {

    public ManagerFrame mainFrame;
    private UserStats userStats = new UserStats();

    public CheckVendor() {
        initComponents();
        initLayout();
    }


    private void initLayout() {
        this.setBackground(new java.awt.Color(255, 251, 233, 0));
        bgLayer.setBackground(new java.awt.Color(255, 251, 233, 240));
    }

    public void setMainFrame(ManagerFrame frame){
        this.mainFrame = frame;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgLayer = new javax.swing.JPanel();
        vendorScrollList = new javax.swing.JScrollPane();
        searchNameLabel = new javax.swing.JLabel();
        searchName = new javax.swing.JTextField();
        searchStoreIdLabel = new javax.swing.JLabel();
        searchStoreId = new javax.swing.JTextField();
        searchTest = new javax.swing.JLabel();
        bottomBg = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 251, 233));
        setLayout(null);

        bgLayer.setBackground(new java.awt.Color(255, 251, 233));
        bgLayer.setLayout(null);
        bgLayer.add(vendorScrollList);
        vendorScrollList.setBounds(72, 110, 1260, 500);

        searchNameLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        searchNameLabel.setText("<html>Search <br> by Name:</html>");
        bgLayer.add(searchNameLabel);
        searchNameLabel.setBounds(70, 40, 160, 60);

        searchName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        searchName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        bgLayer.add(searchName);
        searchName.setBounds(230, 40, 510, 60);

        searchStoreIdLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        searchStoreIdLabel.setText("<html>Search by <br>Store ID:</html>");
        bgLayer.add(searchStoreIdLabel);
        searchStoreIdLabel.setBounds(750, 40, 200, 60);

        searchStoreId.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        searchStoreId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        bgLayer.add(searchStoreId);
        searchStoreId.setBounds(950, 40, 380, 60);

        searchTest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/search_icon.png"))); // NOI18N
        searchTest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchTestMouseClicked(evt);
            }
        });
        bgLayer.add(searchTest);
        searchTest.setBounds(20, 20, 60, 40);

        add(bgLayer);
        bgLayer.setBounds(0, 0, 1400, 650);

        bottomBg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/system/manager_background_bottom.png"))); // NOI18N
        add(bottomBg);
        bottomBg.setBounds(0, 0, 1400, 650);
    }// </editor-fold>//GEN-END:initComponents

    private void searchTestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchTestMouseClicked
        userStats.vendorStatsPopUp(mainFrame);
        mainFrame.setEnabled(false);
    }//GEN-LAST:event_searchTestMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgLayer;
    private javax.swing.JLabel bottomBg;
    private javax.swing.JTextField searchName;
    private javax.swing.JLabel searchNameLabel;
    private javax.swing.JTextField searchStoreId;
    private javax.swing.JLabel searchStoreIdLabel;
    private javax.swing.JLabel searchTest;
    private javax.swing.JScrollPane vendorScrollList;
    // End of variables declaration//GEN-END:variables
}
