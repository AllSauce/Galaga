import java.awt.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * A class that creates the player object that is controlled by the user
 *
 * @author Marcus Frodigh, Leo Vainio, Krenar Manxhuka
 * @version 1.0
 * @since 1.0
 */
public class Player implements MovingObject {
    private int xPos;
    private int yPos;
    private int width = 50;
    private int height = 50;
    private int speed = 10;
    private ArrayList<Bullet> bullets;
    private static BufferedImage spaceShip;

    /**
     * The constructor for the player class, creates a player at specific x and y coordinates
     * 
     * @param x The x coordinate that the player starts at. 
     * @param y The y coordiante that the player starts at.
     */
    public Player(int x, int y){
        bullets = new ArrayList<Bullet>();
        xPos = x;
        yPos = y;

        try {
            spaceShip = ImageIO.read(getClass().getResourceAsStream("images/spaceship.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method from the movingobject interface is not used in player and i replaced by moveleft/right methods
     */
    public void move(){}

    /**
     * A getter method that returns the y position of the player
     * 
     * @return yPos which is the y position of the player object
     */

    public int getY() {return yPos;}

    /**
     * A getter method that returns the x position of the player
     * 
     * @return xPos which is the x position of the player object
     */
    public int getX() {return xPos;}

    /**
     * A getter method that returns the width of the player
     * 
     * @return width which is the width of the player object
     */
    public int getWidth() {return width;}

    /**
     * A getter method that returns the height of the player
     * 
     * @return height which is the height of the player object
     */
    public int getHeight() {return height;}

    /**
     * A method that moves the player to the set distance to the left and stops the player from leaving the play area
     */
    public void moveLeft(){
        if(xPos <= 0){
            xPos = 0;
        }
        else{
            xPos -= speed;
        }
    }
    /**
     * A method that moves the player to the set distance to the right and stops the player from leaving the play area
     */
    public void moveRight(){
        if(xPos >= 750){
            xPos = 750;
        }
        else{
            xPos +=speed;
        }
    }

    /**
     * A method that checks if a certain area overlaps with the player to check if two objects collide.
     * 
     * @param x the x position of the other object
     * @param y the y position of the other object
     * @param width the width of the other object
     * @param height the height of the other object
     * @return returns true if the other object overlaps otherwise false
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
     * A method that checks if an enemy is hit by one of the bullets the player shoots
     * 
     * @param enemy the enemy that the method checks if it is 
     * @return returns true if the enemy is hit by a bullet otherwise it returns false
     */
    public boolean hitbox(MovingObject enemy){
        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            Bullet b = it.next();
            if(b.hitbox(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight())) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * A method that paints the player at a certain position and updates the bullets positon then painting them
     * 
     * @param g the grapichs that is to paint the objects at ceratain positions
     */
    public void paint(Graphics g) {

        g.drawImage(spaceShip, xPos, yPos, width, height, null);

        for(Bullet bullet : bullets) {
            bullet.move();
            bullet.paint(g); 
        }

        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            Bullet next = it.next();
            if (next.getY() < 0) {
                it.remove();
            }
        }
    }

    /**
     * A method that shoots/creates a new bullet in front of the player 
     */
    public void shoot(){
        bullets.add(new Bullet(this.xPos+10, this.yPos));
    }
}
