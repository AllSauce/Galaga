import java.awt.*;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * A enemy class that creates an enemy that moves across the screen and then down
 *
 * @author Marcus Frodigh, Leo Vainio, Krenar Manxhuka
 * @version 1.0
 * @since 1.0
 */
public class EnemyB extends Enemy implements MovingObject {

    private int xChange = 3;
    private int yChange = 60;
    private static BufferedImage spaceShip;


    /**
     * The constructor for the class that creates an EnemyB object
     */
    public EnemyB(){
        //Makes the enemy spawn randomly within this area.
        super();
        try {
            spaceShip = ImageIO.read(getClass().getResourceAsStream("images/enemyb.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * A method that moves the objects by changing the xPos and yPos.
     */
    public void move(){
        xPos += xChange;

        if(xPos >= 750){
            xChange = -3;
            yPos += yChange;
        }
        else if(xPos <= 0){
            xChange = 3;
            yPos += yChange;
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
