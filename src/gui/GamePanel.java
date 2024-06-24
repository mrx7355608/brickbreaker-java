package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

    private final int PANEL_WIDTH = 800;
    private final int PANEL_HEIGHT = 500;
    
    BackgroundImageLabel backgroundImageLabel;

    public GamePanel() {
        super.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        super.setLayout(new BorderLayout());

        backgroundImageLabel = new BackgroundImageLabel();
        backgroundImageLabel.add(new Base());
        
        super.add(backgroundImageLabel, BorderLayout.NORTH);
    }

//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        g.drawImage(backgroundImage, 0, 0, null);
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
//        this.repaint();
    }

}
