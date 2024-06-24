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
public class Base extends JButton {
    private final int BASE_WIDTH = 130;
    private final int BASE_HEIGHT = 20;
    private int x = 400;
    private int y = 400;
    private int velocity = 1;
    
    
    public Base() {
        super.setPreferredSize(new Dimension(BASE_WIDTH, BASE_HEIGHT));
        super.setBounds(x, y, BASE_WIDTH, BASE_HEIGHT); // set initial x & y coordinates of base

        
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
    }
}
