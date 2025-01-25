package frontend.admin_CM;

import javax.swing.*;

public class StandardButton {
//Not completed
    private final String filePath = "/asset/system/";
    
    private JLayeredPane button;


    public JLabel getImage(String imageName){
        //To put selected image in JLabel

        //Get file path
        ImageIcon image = new ImageIcon(filePath + imageName);

        JLabel imageHolder = new JLabel();
        imageHolder.setSize(image.getIconWidth(), image.getIconHeight());
        imageHolder.setIcon(image);

        //return imageHolder
        return imageHolder;
    }

    private class RoundedEdgeButton extends JPanel {

    }
}
