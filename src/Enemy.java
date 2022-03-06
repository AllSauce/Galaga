import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * An abstract class used to create the base of an enemy.
 * Contain methods and fields that every function that the enemy classes needs
 * 
 * @author Marcus Frodigh, Leo Vainio, Krenar Manxhuka
 * @version 1.0
 * @since 1.0
 */
public abstract class Enemy extends JPanel implements MovingObject{
    protected int yPos;
    protected int xPos;
    protected int width = 60;
    protected int height = 60;
    protected Random rand = new Random();

    /**
     * The constructor for the Enemy abstract class
     */
    public Enemy(){
        yPos = rand.nextInt(300 - 50 + 1) + 50;
        xPos = rand.nextInt(700 - 100 + 1) + 100;
    }

    /**
     * A method that is called to check if two objects overlap
     * @param x The x value of the object to check if they overlap
     * @param y The y value of the object to check if they overlap
     * @param width The width value of the object to check if they overlap
     * @param height The height value of the object to check if they overlap
     * @return Returns true if the object overlaps this object else false
     */
    public boolean hitbox(int x, int y, int width, int height) {
        if (xPos < x && x < xPos + this.width){
            if (yPos < y && y < yPos + this.height){
                return true;
            }
            else if (yPos < y + height && y + height < yPos + this.height){
                return true;
            }
        }else if (xPos < x + width && x + width < xPos + this.width){
            if (yPos < y && y < yPos + this.height){
                return true;
            }
            else if (yPos < y + height && y + height < yPos + this.height){
                return true;
            }
        }
        return false;
    }

    /**
     * An abstract method that is called to move the object
     */
    public abstract void move();

    /**
     * A method that gets the y position of the object
     * @return the yPos of the object
     */
    public int getY(){
        return this.yPos;
    }

    /**
     * A method that gets the x position of the object
     * @return the xPos of the object
     */
    public int getX(){
        return this.xPos;
    }
    /**
     * A method that gets the width of the object
     * @return the width of the object
     */
    public int getWidth() {
        return width;
    }
    /**
     * A method that gets the height of the object
     * @return the height of the object
     */
    public int getHeight() {
        return height;
    }

    /**
     * An abstract method that is called to draw the object at a certain position
     * @param g the grapichs object used to draw the enemy
     */
    public abstract void paint(Graphics g);
}
