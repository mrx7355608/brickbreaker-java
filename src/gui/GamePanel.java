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
        
        // Add bricks
        for (Brick brick : Levels.createLevelTwoBrickPattern()) {
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

        // Check ball's collision with base
        if (ball.x >= base.x
                && ball.x <= base.x + base.getBASE_WIDTH()
                && ball.y + ball.getBALL_HEIGHT() >= base.y) {
            ball.yVelocity *= -1;

            if (ball.x > 400) {
                ball.xVelocity *= -1;
            }
        }

        // Check ball collision with bricks
        Iterator<Brick> iterator = bricks.iterator();
        while (iterator.hasNext()) {
            Brick brick = iterator.next();
            Rectangle bounds = brick.getBounds();

            if (ball.x >= bounds.x
                    && ball.x <= bounds.x + bounds.width
                    && ball.y <= bounds.y + bounds.height
                    && ball.y >= bounds.y) {
                iterator.remove();
                backgroundImageLabel.remove(brick);
                ball.yVelocity *= -1;
                break;
            }
        }
    }

}
