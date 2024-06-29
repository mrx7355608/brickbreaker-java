package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Ball extends JButton {

    private final int BALL_WIDTH = 20;
    private final int BALL_HEIGHT = 15;
    public int xVelocity = 4;
    public int yVelocity = -4;
    public int x = 325;
    public int y = 350;
    private final long COLLISION_COOLDOWN = 100;
    private long lastCollisionTime = 0;

    public Ball() {
        super.setPreferredSize(new Dimension(BALL_WIDTH, BALL_HEIGHT));
        super.setBounds(x, y, BALL_WIDTH, BALL_HEIGHT);
        super.setBackground(new Color(255, 255, 255, 0));
        super.setBorderPainted(false);
        super.setFocusable(false);

        // Load ball image
        try {
            Image baseImage = ImageIO.read(new File("assets/ball.png")).getScaledInstance(BALL_WIDTH, BALL_HEIGHT, Image.SCALE_SMOOTH);
            super.setIcon(new ImageIcon(baseImage));
        } catch (IOException ex) {
            System.out.println("[ERROR] Unable to load ball image");
        }
    }

    public void moveBall() {
        if (this.x < 0 || (this.x + this.getBALL_WIDTH()) > 790) {
            this.xVelocity *= -1;
        }
        if (this.y < 0) {
            this.yVelocity *= -1;
        }

        this.x += this.xVelocity;
        this.y += this.yVelocity;
        this.setBounds(this.x, this.y, this.BALL_WIDTH, this.BALL_HEIGHT);

    }

    public void checkCollisionsWithBase(Base base) {
        // Using time base collision checks to avoid some collision glitches
        // like, multiple collision detections at a time
        long currentTime = System.currentTimeMillis();

        // CHECK COLLISION WITH TOP SIDE OF THE BASE
        if ((currentTime - this.lastCollisionTime) > this.COLLISION_COOLDOWN) {
            if (this.x >= base.x
                    && this.x <= base.x + base.getBASE_WIDTH()
                    && this.y + this.getBALL_HEIGHT() >= base.y
                    && this.y + this.getBALL_HEIGHT() <= base.y + base.getBASE_HEIGHT()) {
                System.out.println("normal");
                this.yVelocity *= -1;

                if (this.x > 400) {
                    this.xVelocity *= -1;
                }
                this.lastCollisionTime = currentTime;

            } // CHECK COLLISIONS WITH LEFT SIDE OF THE BASE
            else if (this.x + this.getBALL_WIDTH() >= base.x
                    && this.x + this.getBALL_WIDTH() <= base.x + base.getBASE_WIDTH() / 4
                    && this.y >= base.y
                    && this.y <= base.y + base.getBASE_HEIGHT()) {
                System.out.println("left side");
                this.xVelocity *= -1;
                this.yVelocity *= -1;
                this.lastCollisionTime = currentTime;

            } // CHECK COLLISIONS WITH RIGHT SIDE OF THE BASE (!!! Not tested yet !!!)
            else if (this.x <= base.x + base.getBASE_WIDTH()
                    && this.x >= base.x + base.getBASE_WIDTH() - base.getBASE_WIDTH() / 4
                    && this.y + this.getBALL_HEIGHT() >= base.y
                    && this.y + this.getBALL_HEIGHT() <= base.y + base.getBASE_HEIGHT()) {
                System.out.println("Right side");
                this.xVelocity *= -1;
                this.yVelocity *= -1;
                this.lastCollisionTime = currentTime;

            }

        }
    }

    public Brick checkCollisionsWithBricks(ArrayList<Brick> bricks) {
        Iterator<Brick> iterator = bricks.iterator();
        while (iterator.hasNext()) {
            Brick brick = iterator.next();
            Rectangle bounds = brick.getBounds();

            if (this.x >= bounds.x
                    && this.x <= bounds.x + bounds.width
                    && this.y <= bounds.y + bounds.height
                    && this.y >= bounds.y) {
                iterator.remove();
                this.yVelocity *= -1;
                return brick;
            }
        }
        
        return null;
    }
    
    public int getBALL_WIDTH() {
        return BALL_WIDTH;
    }

    public int getBALL_HEIGHT() {
        return BALL_HEIGHT;
    }

}
