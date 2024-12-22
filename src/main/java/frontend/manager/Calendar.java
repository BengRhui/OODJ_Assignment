/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.manager;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.util.Date;

/**
 *
 * @author Chun Ming
 */
public class Calendar extends javax.swing.JFrame {

    private JFrame parentFrame;

    public Calendar() {}

    public javax.swing.JFrame calendarDaily(JFrame frame){
        return this;
    }

    public javax.swing.JFrame calendarMonthly(JFrame frame){
        return this;
    }

    public javax.swing.JFrame calendarQuarterly(JFrame frame){
        return this;
    }

    public javax.swing.JFrame calendarYearly(JFrame frame){
        return this;
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        calendarHolder = new javax.swing.JPanel();
        january = new javax.swing.JLabel();
        february = new javax.swing.JLabel();
        march = new javax.swing.JLabel();
        april = new javax.swing.JLabel();
        may = new javax.swing.JLabel();
        june = new javax.swing.JLabel();
        july = new javax.swing.JLabel();
        august = new javax.swing.JLabel();
        september = new javax.swing.JLabel();
        october = new javax.swing.JLabel();
        november = new javax.swing.JLabel();
        december = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        timeFrame = new javax.swing.JLabel();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(500, 500));

        calendarHolder.setBackground(new java.awt.Color(255, 255, 255));
        calendarHolder.setLayout(new java.awt.GridLayout(4, 3));

        january.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        january.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        january.setText("jLabel1");
        calendarHolder.add(january);

        february.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        february.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        february.setText("jLabel2");
        calendarHolder.add(february);

        march.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        march.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        march.setText("jLabel3");
        calendarHolder.add(march);

        april.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        april.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        april.setText("jLabel4");
        calendarHolder.add(april);

        may.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        may.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        may.setText("jLabel5");
        calendarHolder.add(may);

        june.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        june.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        june.setText("jLabel6");
        calendarHolder.add(june);

        july.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        july.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        july.setText("jLabel6");
        calendarHolder.add(july);

        august.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        august.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        august.setText("jLabel6");
        calendarHolder.add(august);

        september.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        september.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        september.setText("jLabel6");
        calendarHolder.add(september);

        october.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        october.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        october.setText("jLabel6");
        calendarHolder.add(october);

        november.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        november.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        november.setText("jLabel6");
        calendarHolder.add(november);

        december.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        december.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        december.setText("jLabel6");
        calendarHolder.add(december);

        getContentPane().add(calendarHolder, java.awt.BorderLayout.CENTER);

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setPreferredSize(new java.awt.Dimension(500, 100));

        timeFrame.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        timeFrame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeFrame.setText("January");

        previousButton.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        previousButton.setText("<");
        previousButton.setBorder(null);
        previousButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                previousButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                previousButtonMouseExited(evt);
            }
        });

        nextButton.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        nextButton.setText(">");
        nextButton.setBorder(null);
        nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(timeFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void previousButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousButtonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_previousButtonMouseEntered

    private void previousButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousButtonMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_previousButtonMouseExited

    private void nextButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_nextButtonMouseEntered

    private void nextButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_nextButtonMouseExited

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
            java.util.logging.Logger.getLogger(Calendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calendar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel april;
    private javax.swing.JLabel august;
    private javax.swing.JPanel calendarHolder;
    private javax.swing.JLabel december;
    private javax.swing.JLabel february;
    private javax.swing.JPanel header;
    private javax.swing.JLabel january;
    private javax.swing.JLabel july;
    private javax.swing.JLabel june;
    private javax.swing.JLabel march;
    private javax.swing.JLabel may;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel november;
    private javax.swing.JLabel october;
    private javax.swing.JButton previousButton;
    private javax.swing.JLabel september;
    private javax.swing.JLabel timeFrame;
    // End of variables declaration//GEN-END:variables
}
