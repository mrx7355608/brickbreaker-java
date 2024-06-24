/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author fawad
 */
public class Base extends JButton implements KeyListener {
    private final int BASE_WIDTH = 130;
    private final int BASE_HEIGHT = 20;
    public int x = 400;
    public int y = 400;
    private int velocity = 0;
    
    
    public Base() {
        super.setPreferredSize(new Dimension(BASE_WIDTH, BASE_HEIGHT));
        super.setBounds(x, y, BASE_WIDTH, BASE_HEIGHT); // set initial x & y coordinates of base
        super.addKeyListener(this);

        
        // Load base image
        Image baseImage;
        try {
            baseImage = ImageIO.read(new File("assets/base2.jpg"));
            super.setIcon(new ImageIcon(baseImage));
        } catch (IOException ex) {
            System.out.println("[ERROR] Unable to load base image");
        }
        
    }

    public void move() {
        this.x += this.velocity;
        
        if (this.x <= 5 || (this.x + this.BASE_WIDTH) >= 780 ) {
            this.velocity = 0;
        }
    }

    public int getBASE_WIDTH() {
        return BASE_WIDTH;
    }

    public int getBASE_HEIGHT() {
        return BASE_HEIGHT;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_RIGHT) {
            System.out.println("right");
            this.velocity = 5;
        }
        if (code == KeyEvent.VK_LEFT) {
            System.out.println("left");
            this.velocity = -5;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
}
