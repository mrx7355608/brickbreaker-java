package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BricksPanel extends JPanel {

    private ArrayList<Brick> bricks = new ArrayList();

    public BricksPanel() {
        super.setLayout(new FlowLayout());
        super.setBackground(new Color(255, 255, 255, 0));
        super.setOpaque(false);
        super.setFocusable(true);

        // Load bricks images
        Image skyBlueImg = null;
        Image purpleImg = null;
//        try {
//            skyBlueImg = ImageIO.read(new File("assets/skyblue.jpg"));
//            purpleImg = ImageIO.read(new File("assets/purple.jpg"));
//        } catch (IOException ex) {
//            System.out.println("Unable to load brick images");
//        }

        for (int i = 0; i < 8; i++) {
            Brick b2 = new Brick(null);
            super.add(b2);
        }

    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }
}
