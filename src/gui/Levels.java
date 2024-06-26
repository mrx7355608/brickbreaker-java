package gui;

import java.util.ArrayList;

public class Levels {

    public static ArrayList<Brick> createLevelOneBrickPattern() {
        ArrayList<Brick> bricks = new ArrayList();

        int xPos = 0;
        int yPos = 50;

        for (int i = 0; i < 28; i++) {
            Brick brick = new Brick(null);

            if (i % 7 == 0) {
                xPos = 0;
                yPos = yPos + brick.getBRICK_HEIGHT() + 20;
            }

            brick.setBounds(xPos, yPos, brick.getBRICK_WIDTH(), brick.getBRICK_HEIGHT());
            bricks.add(brick);
            xPos = xPos + brick.getBRICK_WIDTH() + 20;
        }

        return bricks;
    }

    public static ArrayList<Brick> createLevelTwoBrickPattern() {
        ArrayList<Brick> bricks = new ArrayList();

        int xPos = 70;
        int yPos = 50;

        for (int i = 1; i <= 7; i++) {

            for (int j = 1; j <= 7; j++) {

                // For every even row
                if (i % 2 == 0) {
                    Brick brick = new Brick(null);

                    // If the column number is even, only then create a brick
                    if (j % 2 == 0) {
                        brick.setBounds(xPos, yPos, brick.getBRICK_WIDTH(), brick.getBRICK_HEIGHT());
                        bricks.add(brick);
                        xPos = xPos + brick.getBRICK_WIDTH();
                        continue;
                    }

                    xPos = xPos + brick.getBRICK_WIDTH();
                } else {
                    Brick brick = new Brick(null);

                    // For every odd row, ignore the even columns
                    if (j % 2 == 0) {
                        xPos = xPos + brick.getBRICK_WIDTH();
                        continue;
                    }
                    
                    // Add brick to array
                    brick.setBounds(xPos, yPos, brick.getBRICK_WIDTH(), brick.getBRICK_HEIGHT());
                    bricks.add(brick);
                    xPos = xPos + brick.getBRICK_WIDTH();
                }

            }
            
            // After creating bricks for a row
            xPos = 70;  // Reset X position
            yPos = yPos + 30;  // Update Y position
            
        }

        return bricks;
    }
    
    public static ArrayList<Brick> createLevelThreeBrickPattern() {
        ArrayList<Brick> bricks = new ArrayList();
        
        // Box 1
        int xPos1 = 30;
        int yPos1 = 20;
        
        for (int i = 0; i < 8; i++) {            
            for (int j = 0; j < 5; j++) {
                Brick brick = new Brick(null);
                brick.setBounds(xPos1, yPos1, brick.getBRICK_WIDTH(), brick.getBRICK_HEIGHT());
                bricks.add(brick);
                xPos1 += brick.getBRICK_WIDTH();
            }
            xPos1 = 30;
            yPos1 += 30;
        }
        
        // Box 2
        int xPos2 = 400;
        int yPos2 = 20;
        
        for (int i = 0; i < 8; i++) {            
            for (int j = 0; j < 5; j++) {
                Brick brick = new Brick(null);
                brick.setBounds(xPos2, yPos2, brick.getBRICK_WIDTH(), brick.getBRICK_HEIGHT());
                bricks.add(brick);
                xPos2 += brick.getBRICK_WIDTH();
            }
            xPos2 = 400;
            yPos2 += 30;
        }
        
        return bricks;
    }
}
