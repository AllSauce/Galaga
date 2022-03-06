import java.awt.*;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * A class that creates a simple enemy that only moves straight down
 *
 * @author Marcus Frodigh, Leo Vainio, Krenar Manxhuka
 * @version 1.0
 * @since 1.0
 */
public class EnemyA extends Enemy implements MovingObject {
    private static BufferedImage spaceShip;
    /**
     * The constructor for the class, that creates an EnemyA object
     */
    public EnemyA(){
        //Makes the enemy spawn randomly within a set area.
        super();

        try {
            spaceShip = ImageIO.read(getClass().getResourceAsStream("images/enemya.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method that moves the objects by changing the yPos.
     */
    public void move(){
        yPos += 3;
    }

    /**
     * A method that draws the image for the object at the current xPos and yPos
     * @param g the grapichs object used to draw the enemy
     */
    public void paint(Graphics g){
        g.drawImage(spaceShip, xPos, yPos, width, height, null);
    }
}
