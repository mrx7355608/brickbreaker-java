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
    private final long COLLISION_COOLDOWN = 100;
    private long lastCollisionTime = 0;

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

        long currentTime = System.currentTimeMillis();

        base.move();
        ball.moveBall();

        // Check ball's collision with base
        // Change ball direction if base's top collides with ball's bottom
        if (ball.x >= base.x
                && ball.x <= base.x + base.getBASE_WIDTH()
                && ball.y + ball.getBALL_HEIGHT() >= base.y
                && ball.y + ball.getBALL_HEIGHT() <= base.y + 1) {
            System.out.println("normal");
            ball.yVelocity *= -1;

            if (ball.x > 400) {
                ball.xVelocity *= -1;
            }
        }
        
        if ((currentTime - lastCollisionTime) > COLLISION_COOLDOWN) {
            if (ball.x + ball.getBALL_WIDTH() >= base.x
                    && ball.x + ball.getBALL_WIDTH() <= base.x + 4
                    && ball.y + ball.getBALL_HEIGHT() >= base.y
                    && ball.y + ball.getBALL_HEIGHT() <= base.y + 5) {
                System.out.println("left side");
                ball.xVelocity *= -1;
                ball.yVelocity *= -1;
                lastCollisionTime = currentTime;
            }
        }
        
        if (ball.y >= 500) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Gameover");
        }

//        if (ball.x <= base.x + base.getBASE_WIDTH() && ball.y >= base.y && ball.y <= base.y + base.getBASE_HEIGHT()) {
//            ball.xVelocity *= -1;
//            ball.yVelocity *= -1;
//        }
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
