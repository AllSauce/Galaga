import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.lang.Math;
/**
 * A class that creates a bullet shot by the player that moves straight up.
 *
 * @author Marcus Frodigh, Leo Vainio, Krenar Manxhuka
 * @version 1.0
 * @since 1.0
 */
public class Bullet implements MovingObject{
    private int xPos;
    private int yPos;
    private int width = 30;
    private int heigth = 30;
    private int speed = 5;
    private static BufferedImage bullet;

    /**
     * A constructor that creates a bullet at certain x and y coorinates
     * 
     * @param x the x coordinate for the bullet
     * @param y the y coordinate for the bullet
     */
    public Bullet(int x, int y) {
        yPos = y; // Set this to players if player is the only object that shoots.
        xPos = x; // X and Y is sent from the player (or enemy) 

        try {
            bullet = ImageIO.read(getClass().getResourceAsStream("images/bullet.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method that moves the bullet a certain distance up the screen
     */
    public void move(){yPos -= speed;}

    /**
     * A method to check if a object is a certain distance away from the bullet, if within it is a collision
     * 
     * @param x the other objects x value
     * @param y the other objects y value
     * @param width the other objects width
     * @param height the other objects height
     * @return returns true if the other object is close enough otherwise false
     */
    public boolean hitbox(int x, int y, int width, int height){
        int xDiff = Math.abs((x+width/2)-(xPos+this.width/2));
        int yDiff = Math.abs((y+heigth/2)-(yPos+this.heigth/2));

        if(xDiff < width/2+this.width/2 && yDiff < height/2+this.heigth/2) {
            return true;
        }
        return false;
    }

    /**
     * A method that paints the picture for the bullet at a certain position
     * 
     * @param g the graphics object that draws the image
     */
    public void paint(Graphics g) {
        g.drawImage(bullet, xPos, yPos, width, heigth, null);
    }

    /**
     * A method that gets the y position of the object
     * @return the yPos of the object
     */
    public int getY(){return this.yPos;}

    /**
     * A method that gets the x position of the object
     * @return the xPos of the object
     */
    public int getX(){return this.xPos;}

    /**
     * A method that gets the width of the object
     * @return the width of the object
     */
    public int getWidth() {return width;}

    /**
     * A method that gets the height of the object
     * @return the height of the object
     */
    public int getHeight() {return heigth;}
}
