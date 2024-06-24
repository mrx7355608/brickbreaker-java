package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

    private final int PANEL_WIDTH = 800;
    private final int PANEL_HEIGHT = 500;
    private final BackgroundImageLabel backgroundImageLabel;
    private final Base base;
    private final Ball ball;
    private final Timer timer;

    public GamePanel() {
        super.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        super.setLayout(new BorderLayout());

        backgroundImageLabel = new BackgroundImageLabel();
        base = new Base();
        ball = new Ball();
        backgroundImageLabel.add(ball);
        backgroundImageLabel.add(base);
        super.add(backgroundImageLabel, BorderLayout.NORTH);
        
        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        base.move();
        ball.moveBall();
        
        if (ball.x >= base.x &&
                ball.x <= base.x + base.getBASE_WIDTH() &&
                ball.y + ball.getBALL_HEIGHT() >= base.y
                ) {
            ball.yVelocity *= -1;
            
            if (ball.x > 400) {
                ball.xVelocity *= -1;
            }
        }
    }

}
