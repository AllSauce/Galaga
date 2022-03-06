import java.awt.*;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * A class that creates an enemy that moves diagonally across the screen
 *
 * @author Marcus Frodigh, Leo Vainio, Krenar Manxhuka
 * @version 1.0
 * @since 1.0
 */
public class EnemyC extends Enemy implements MovingObject {
    private int xChange;
    private int yChange = 2;
    private static BufferedImage spaceShip;


    /**
     * The constructor for the class that creates an EnemyC object
     */
    public EnemyC(){
        //Makes the enemy spawn randomly within this area
        super();
        xChange = rand.nextInt(7) -3;

        try {
            spaceShip = ImageIO.read(getClass().getResourceAsStream("images/enemyc.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method that moves the objects by changing the xPos and yPos.
     */
    public void move(){
        xPos += xChange;
        yPos += yChange;

        if(xPos >= 750){
            xChange = -xChange;
            xPos = 749;
        }
        else if(xPos <= 0){
            xChange = -xChange;
            xPos = 1;
        }
    }

    /**
     * A method that draws the image for the object at the current xPos and yPos
     * @param g the grapichs object used to draw the enemy
     */
    public void paint(Graphics g){
        g.drawImage(spaceShip, xPos, yPos, width, height, null);
    }
}
