
package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Base extends JButton {
    private final int BASE_WIDTH = 130;
    private final int BASE_HEIGHT = 15;
    public int x = 325;
    public int y = 400;
    public int velocity = 0;
    
    
    public Base() {
        super.setPreferredSize(new Dimension(BASE_WIDTH, BASE_HEIGHT));
        super.setBounds(x, y, BASE_WIDTH, BASE_HEIGHT); // set initial x & y coordinates of base
        super.setBorderPainted(false);
        super.setFocusable(false);
        
        // Load base image
        Image baseImage;
        try {
            baseImage = ImageIO.read(new File("assets/base2.jpg")).getScaledInstance(BASE_WIDTH, BASE_HEIGHT, Image.SCALE_SMOOTH);
            super.setIcon(new ImageIcon(baseImage));
        } catch (IOException ex) {
            System.out.println("[ERROR] Unable to load base image");
        }
        
        // Setting up keybindings
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getInputMap(condition);
        ActionMap actionMap = getActionMap();
        
        KeyStroke rightKey = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
        KeyStroke leftKey = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
        
        inputMap.put(leftKey, "moveLeft");
        inputMap.put(rightKey, "moveRight");
        
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Base.this.velocity = -5;
            }
            
        });
        
        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Base.this.velocity = 5;
            }
            
        });
    }
    
    public void move() {
        this.x += this.velocity;
        this.setBounds(this.x, this.y, this.BASE_WIDTH, this.BASE_HEIGHT);
        
        if (this.x <= 5 || (this.x + this.BASE_WIDTH) > 775)
            this.velocity = 0;
    }

    public int getBASE_WIDTH() {
        return BASE_WIDTH;
    }

    public int getBASE_HEIGHT() {
        return BASE_HEIGHT;
    }
    
}
