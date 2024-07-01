package gui;

import brickbreaker.AudioPlayback;
import brickbreaker.Settings;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

    private final int PANEL_WIDTH = 800;
    private final int PANEL_HEIGHT = 500;
    private final Base base;
    private final Ball ball;
    ArrayList<Brick> bricks = new ArrayList();
    private final BackgroundImageLabel backgroundImageLabel;
    private final Timer timer;
    private final Settings gameSettings;

    public GamePanel() {
        super.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        super.setLayout(new BorderLayout());

        backgroundImageLabel = new BackgroundImageLabel();
        base = new Base();
        ball = new Ball();
        backgroundImageLabel.add(ball);
        backgroundImageLabel.add(base);
        for (Brick brick : Levels.createLevelFiveBrickPattern()) {
            bricks.add(brick);
            backgroundImageLabel.add(brick);
        }
        super.add(backgroundImageLabel, BorderLayout.NORTH);

        // Game settings
        gameSettings = Settings.getInstance();

        // Start gameloop
        timer = new Timer(16, this);
        timer.start();
    }

    /**
     * Main game-loop method. It repaints the game after every 16 milliseconds
     * (60 FPS)
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.repaint();

        base.move();
        ball.moveBall();
        ball.checkCollisionsWithBase(base);
        Brick collidedBrick = ball.checkCollisionsWithBricks(bricks);
        if (collidedBrick != null) {
            if (gameSettings.isSoundEffectsOn()) {
                AudioPlayback.playSoundEffect();
            }
            backgroundImageLabel.remove(collidedBrick);
        }

        this.checkGameover();

    }

    /**
     * Displays a game-over dialog box if ball has gone all the way down, below
     * the base
     *
     * @param
     * @return
     */
    private void checkGameover() {
        if (ball.y >= 500) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Gameover");
        }
    }

}
