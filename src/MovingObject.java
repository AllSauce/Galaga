import java.awt.*;
/**
 * An interface containg all the function needed for an moving object in the game 
 *
 * @author Marcus Frodigh, Leo Vainio, Krenar Manxhuka
 * @version 1.0
 * @since 1.0
 */
public interface MovingObject {
    /**
     * 
     */
    void move();

    /**
     * A method to check if another object overlaps with the current object
     * 
     * @param x the x coordinate of the other object
     * @param y the y coordinate of the other object
     * @param width the width of the other object
     * @param height the height of the other object
     * @return returns true if overlaping and false otherwise
     */
    boolean hitbox(int x, int y, int width, int height);

    /**
     * A method that paints the moving object
     * 
     * @param g the graphics object used to paint the moving object
     */
    void paint(Graphics g);

    /**
     *  A method that returns the y value of the movning object
     * 
     * @return the y value
     */
    int getY();

    /**
     * A method that returns the x value of the moving object
     * 
     * @return the x value
     */
    int getX();
    
    /**
     * A method that returns the width of the moving object
     * 
     * @return the width
     */
    int getWidth();

    /**
     * A method that returns the height of the movning object
     * 
     * @return the height
     */
    int getHeight();
}
