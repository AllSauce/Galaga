import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Iterator;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class is used to display the main menu of the game and the
 * "game over" screen. 
 * 
 * @author Leo Vainio, Marcus Frodigh, Krenar Manxhuka
 * @version 2021-05-20
 */
public class Menu {
    private int state; // 0 = play, 1 = help, 2 = quit.
    private boolean help;
    private boolean gameOver;
    private static BufferedImage cursor;

    /**
     * Contructor for Menu class.
     */
    public Menu(){
        state = 0; 

        try {
            cursor = ImageIO.read(getClass().getResourceAsStream("images/cursor.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Switches cursor to the menu option below. If the last one is 
     * selected, it goes to the first menu option. 
     */
    public void scrollDown(){
        if(state == 2) {
            state = 0;
        } else {
            state++;
        }
    }

    /**
     * Switches cursor to the menu option above. If the first one is 
     * selected, it goes to the last menu option. 
     */
    public void scrollUp() {
        if(state == 0) {
            state = 2;
        } else {
            state--;
        }
    }

    /**
     * This method draws the menu screen, help screen and game over screen
     * depending on which state the game is in.
     */
    public void paint(Graphics g, int score) {
        g.setColor(Color.white);

        if(gameOver) {
            printGameOver(g, score);

        } else if(help) {
            printHelp(g);

        } else {
            printMenu(g);
        }
    }

    /** 
     * This method returns the state of the menu, i.e. which
     * of the menu options the cursor is pointing at. 0 = play,
     * 1 = help, 2 = quit. 
     * 
     * @return which menu option the cursor is pointing at
     */
    public int getState() {
        return state;
    }

    /**
     * This method sets the help variable. If help is set to 
     * true, the help screen will be printed.
     * 
     * @param h True if help should be printed, otherwise false. 
     */
    public void setHelp(boolean h) {
        help = h;
    }

    /**
     * This method returns true if help screen is printed,
     * otherwise false.
     * 
     * @return state of help screen.
     */
    public boolean getHelp() {
        return help;
    }

    /**
     * This method is called when the player looses, if gameOver 
     * is true the game is lost. This gets set to true when the game is reset.
     * 
     * @param status True if player has lost, otherwise false.
     */
    public void gameOver(boolean status) {
        gameOver = status;
    }

    /**
     * This method is a getter for gameOver variable. 
     * 
     * @return true if game is lost, otherwise false.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    private void printGameOver(Graphics g, int score) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 70));
        g.drawString("GAME OVER", 175, 150);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString("Score: " + score, 175, 250);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g.drawRect(200, 350, 400, 100);
        g.drawString("MENU", 320, 422);
        g.drawImage(cursor, 100, 375, 50, 50, null);
    }

    private void printMenu(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 60));

        g.drawRect(200, 100, 400, 100);
        g.drawRect(200, 300, 400, 100);
        g.drawRect(200, 500, 400, 100);
    
        g.drawString("PLAY", 320, 172);
        g.drawString("HELP", 320, 372);
        g.drawString("QUIT", 320, 572);

        switch(state) {
            case 0:
                g.drawImage(cursor, 100, 125, 50, 50, null);
                break;
            case 1:
                g.drawImage(cursor, 100, 325, 50, 50, null);
                break;
            case 2:
                g.drawImage(cursor, 100, 525, 50, 50, null);
                break;
            default:
                System.out.println("Error: invalid state!");
        }
    }

    private void printHelp(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString("Press [esc] to go back", 40, 40);
        g.drawString("Controls:", 80, 150);
        g.drawString("Move left: left arrow key", 80,200);
        g.drawString("Move right: right arrow key", 80,250);
        g.drawString("Shoot: spacebar", 80,300);
    }
}
