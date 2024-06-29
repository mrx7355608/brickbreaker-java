package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

    private final int PANEL_WIDTH = 800;
    private final int PANEL_HEIGHT = 500;
    private final BackgroundImageLabel backgroundImageLabel;
    private final Base base;
    private final Ball ball;
    ArrayList<Brick> bricks = new ArrayList();
    private final Timer timer;

    public GamePanel() {
        super.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        super.setLayout(new BorderLayout());

        backgroundImageLabel = new BackgroundImageLabel();
        base = new Base();
        ball = new Ball();
        backgroundImageLabel.add(ball);
        backgroundImageLabel.add(base);

        // Add bricks - level 3
        for (Brick brick : Levels.createLevelThreeBrickPattern()) {
            bricks.add(brick);
            backgroundImageLabel.add(brick);
        }

        super.add(backgroundImageLabel, BorderLayout.NORTH);

        // Start gameloop
        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.repaint();

        base.move();
        ball.moveBall();
        ball.checkCollisionsWithBase(base);
        Brick collidedBrick = ball.checkCollisionsWithBricks(bricks);
        if (collidedBrick != null) {
            backgroundImageLabel.remove(collidedBrick);
        }
        
        this.checkGameover();
        
    }

    private void checkGameover() {
        if (ball.y >= 500) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Gameover");
        }
    }

}
