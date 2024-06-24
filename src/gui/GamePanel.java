
package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements ActionListener {

    private final int PANEL_WIDTH = 800;
    private final int PANEL_HEIGHT = 500;
    Image backgroundImage;
    Timer timer;
    
    public GamePanel() {
        super.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        super.setLayout(new BorderLayout());
        
        backgroundImage = new ImageIcon("assets/bg.jpg").getImage();
        timer = new Timer(16, this);
        timer.start();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }
    
}
