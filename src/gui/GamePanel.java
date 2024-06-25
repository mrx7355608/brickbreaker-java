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

        int xPosition = 0;
        int yPosition = 0;

        // Create bricks
        for (int i = 0; i < 28; i++) {
            Brick b2 = new Brick(null);
            
            xPosition = xPosition + b2.getBRICK_WIDTH() + 10;
            
            // Change row after 6 bricks are rendered
            if (i % 7 == 0) {
                xPosition = 80;
                yPosition = yPosition + b2.getBRICK_HEIGHT() + 20;
            }

            b2.setBounds(xPosition, yPosition, b2.getBRICK_WIDTH(), b2.getBRICK_HEIGHT());
            bricks.add(b2);
            backgroundImageLabel.add(b2);
        }
        super.add(backgroundImageLabel, BorderLayout.NORTH);

        // Start gameloop
        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Component component : bricks) {
            Rectangle brick = component.getBounds();
            g.drawRect(brick.x, brick.y, brick.width, brick.height);
        }
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
