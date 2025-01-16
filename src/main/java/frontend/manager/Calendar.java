/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.manager;

//import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
//import java.util.Date;

/**
 *
 * @author Chun Ming
 */
public class Calendar extends javax.swing.JFrame {

    private JFrame parentFrame;
    private final ArrayList<String> yearList = initializeYearList();
    private int yearIndex = 0;

    private ArrayList<String> initializeYearList(){
        ArrayList<String> years = new ArrayList<>();
        for (int year = 2021; year <= 2030; year++) {
            years.add(String.valueOf(year));
        }
        return years;
    }

    private void checkIndex(JButton previous, JButton next) {
        if (yearIndex == yearList.size()) {
            yearIndex = 9;
            next.setEnabled(false);
        }else if (yearIndex < 0) {
            yearIndex = 0;
            previous.setEnabled(false);
        }else{
            previous.setEnabled(true);
            next.setEnabled(true);
        }
    }

    public Calendar() {
//        parentFrame = frame;
        initComponents();
        initLayout();
        this.setLocationRelativeTo(null);
        this.setSize(530, 500);
        System.out.println(yearList);
    }

    private void initLayout(){
        previousButton.setBackground(new Color(255,255,255,0));
        nextButton.setBackground(new Color(255,255,255,0));
        timeFrame.setText(yearList.get(yearIndex));
    }
//    public javax.swing.JFrame calendarDaily(JFrame frame){
//        return this;
//    }
//
//    public javax.swing.JFrame calendarMonthly(JFrame frame){
//        return this;
//    }
//
//    public javax.swing.JFrame calendarQuarterly(JFrame frame){
//        return this;
//    }
//
//    public javax.swing.JFrame calendarYearly(JFrame frame){
//        return this;
//    }



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
        january.setText("January");
        january.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                januaryMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                januaryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                januaryMouseExited(evt);
            }
        });
        calendarHolder.add(january);

        february.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        february.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        february.setText("February");
        february.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                februaryMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                februaryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                februaryMouseExited(evt);
            }
        });
        calendarHolder.add(february);

        march.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        march.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        march.setText("March");
        march.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                marchMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                marchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                marchMouseExited(evt);
            }
        });
        calendarHolder.add(march);

        april.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        april.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        april.setText("April");
        april.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aprilMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aprilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aprilMouseExited(evt);
            }
        });
        calendarHolder.add(april);

        may.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        may.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        may.setText("May");
        may.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mayMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mayMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mayMouseExited(evt);
            }
        });
        calendarHolder.add(may);

        june.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        june.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        june.setText("June");
        june.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                juneMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                juneMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                juneMouseExited(evt);
            }
        });
        calendarHolder.add(june);

        july.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        july.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        july.setText("July");
        july.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                julyMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                julyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                julyMouseExited(evt);
            }
        });
        calendarHolder.add(july);

        august.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        august.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        august.setText("August");
        august.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                augustMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                augustMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                augustMouseExited(evt);
            }
        });
        calendarHolder.add(august);

        september.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        september.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        september.setText("September");
        september.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                septemberMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                septemberMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                septemberMouseExited(evt);
            }
        });
        calendarHolder.add(september);

        october.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        october.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        october.setText("October");
        october.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                octoberMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                octoberMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                octoberMouseExited(evt);
            }
        });
        calendarHolder.add(october);

        november.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        november.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        november.setText("November");
        november.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                novemberMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                novemberMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                novemberMouseExited(evt);
            }
        });
        calendarHolder.add(november);

        december.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        december.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        december.setText("December");
        december.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decemberMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                decemberMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                decemberMouseExited(evt);
            }
        });
        calendarHolder.add(december);

        getContentPane().add(calendarHolder, java.awt.BorderLayout.CENTER);

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setPreferredSize(new java.awt.Dimension(500, 100));

        timeFrame.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        timeFrame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeFrame.setText("2024");
        timeFrame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timeFrameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                timeFrameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                timeFrameMouseExited(evt);
            }
        });

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
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
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
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(timeFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void previousButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousButtonMouseEntered
        previousButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_previousButtonMouseEntered

    private void previousButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousButtonMouseExited
        previousButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_previousButtonMouseExited

    private void nextButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseEntered
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_nextButtonMouseEntered

    private void nextButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseExited
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_nextButtonMouseExited

    private void timeFrameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeFrameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_timeFrameMouseClicked

    private void timeFrameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeFrameMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_timeFrameMouseEntered

    private void timeFrameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeFrameMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_timeFrameMouseExited

    private void januaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_januaryMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_januaryMouseClicked

    private void januaryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_januaryMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_januaryMouseEntered

    private void januaryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_januaryMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_januaryMouseExited

    private void februaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_februaryMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_februaryMouseClicked

    private void februaryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_februaryMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_februaryMouseEntered

    private void februaryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_februaryMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_februaryMouseExited

    private void marchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_marchMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_marchMouseClicked

    private void marchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_marchMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_marchMouseEntered

    private void marchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_marchMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_marchMouseExited

    private void aprilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aprilMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_aprilMouseClicked

    private void aprilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aprilMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_aprilMouseEntered

    private void aprilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aprilMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_aprilMouseExited

    private void mayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mayMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_mayMouseClicked

    private void mayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mayMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mayMouseEntered

    private void mayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mayMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_mayMouseExited

    private void juneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_juneMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_juneMouseClicked

    private void juneMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_juneMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_juneMouseEntered

    private void juneMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_juneMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_juneMouseExited

    private void julyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_julyMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_julyMouseClicked

    private void julyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_julyMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_julyMouseEntered

    private void julyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_julyMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_julyMouseExited

    private void augustMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_augustMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_augustMouseClicked

    private void augustMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_augustMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_augustMouseEntered

    private void augustMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_augustMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_augustMouseExited

    private void septemberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_septemberMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_septemberMouseClicked

    private void septemberMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_septemberMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_septemberMouseEntered

    private void septemberMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_septemberMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_septemberMouseExited

    private void octoberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_octoberMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_octoberMouseClicked

    private void octoberMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_octoberMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_octoberMouseEntered

    private void octoberMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_octoberMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_octoberMouseExited

    private void novemberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_novemberMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_novemberMouseClicked

    private void novemberMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_novemberMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_novemberMouseEntered

    private void novemberMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_novemberMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_novemberMouseExited

    private void decemberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decemberMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_decemberMouseClicked

    private void decemberMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decemberMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_decemberMouseEntered

    private void decemberMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decemberMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_decemberMouseExited

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        yearIndex--;
        checkIndex(previousButton,nextButton);
        System.out.println(yearList.get(yearIndex));
        timeFrame.setText(yearList.get(yearIndex));
    }//GEN-LAST:event_previousButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        yearIndex++;
        checkIndex(previousButton,nextButton);
        System.out.println(yearList.get(yearIndex));
        timeFrame.setText(yearList.get(yearIndex));
    }//GEN-LAST:event_nextButtonActionPerformed

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
