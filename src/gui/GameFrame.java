package gui;

import brickbreaker.Settings;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 500;
    private final AudioPlayback audioPlayback;
    private final Settings gameSettings;

    public GameFrame() {
        setTitle("Brick Breaker");
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        super.setLocationRelativeTo(null);
        super.setIconImage(new ImageIcon("assets/icon.png").getImage());
        super.add(new GamePanel());
        
        audioPlayback = new AudioPlayback();
        gameSettings = new Settings();
        
        if (gameSettings.isBackgroundMusicOn()) {
            this.playBackgroundMusic();
        }
    }

    /**
     * Plays background music loop in a separate thread
     */
    private void playBackgroundMusic() {
        new Thread(() -> {
            audioPlayback.playMusic();
        }).start();
    }
}
