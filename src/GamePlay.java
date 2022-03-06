import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * This class contains the main game loop. It calls all moving entities
 * move methods and print methods. This class also handles key input from 
 * the user. 
 *
 * @author Leo Vainio, Marcus Frodigh, Krenar Manxhuka
 * @version 1.0
 * @since 1.0
 */
public class GamePlay extends JPanel implements KeyListener, ActionListener{
    private static final int DELAY       = 15;
    
    private boolean   play;
    private int      score;
    private int  prevScore;
    private int enemyCount;
    private int maxEnemies = 10;

    private boolean leftPressed;
    private boolean rightPressed;
    private boolean spacePressed;
    
    private Player player;
    private Timer timer;
    private Random rng;
    private ArrayList<MovingObject> enemies;
    private Menu menu;

    private BufferedImage background;

    /**
     * Constructor for GamePlay class. This loads in the background picture 
     * and instanciates all necessary fields like the Player. 
     */
    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);

        player  = new Player(375, 700);
        timer   = new Timer(DELAY, this);
        rng     = new Random();
        enemies = new ArrayList<MovingObject>();
        menu    = new Menu();

        try {
            background = ImageIO.read(getClass().getResourceAsStream("images/space.jpg"));
        } catch(IOException e) {
            e.printStackTrace();
        }

        timer.start();
    }

    /**
     * This method draws out the background image, player (bullets), enemies
     * when the player is playing. If the game is not in play mode, the menu is 
     * drawn instead.
     * 
     * @param g Graphics object that is used to draw.
     */
    public void paint(Graphics g) {
        // drawing background
        g.drawImage(background, 0, 0, null);

        if(!play) {
            menu.paint(g, score);
        }

        if(play) {
            // Drawing the score
            g.setColor(Color.white);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g.drawString("Score: " + score, 40, 40);

            // Drawing the player
            player.paint(g);

            // Drawing the enemies
            for (MovingObject enemy : enemies){
                enemy.paint(g);
            }
        }
        g.dispose();
    }

    /**
     * This method adds enemies of random type into the
     * game. 
     */
    public void addEnemy() {
        switch(rng.nextInt(3)){
            case 0:
                enemies.add(new EnemyA());
                break;
            case 1:
                enemies.add(new EnemyB());
                break;
            case 2:
                enemies.add(new EnemyC());
                break;
            default:
                System.out.println("Invalid number!");
        }
        enemyCount++;    
    }
    
    /**
     * This method is responsible for handling all keyinput from 
     * the user. Moving the player, shooting bullets and navigating
     * menu is done from here.
     * 
     * @param e KeyEvent object
     */
    @Override
    public void keyPressed(KeyEvent e){ 
        // In menu mode:
        // Up and Down arrow keys navigates the menu
        // Enter selects an option (play, help, quit)
        if(!play) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    if(!menu.isGameOver()) {
                        menu.scrollDown();
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(!menu.isGameOver()) {
                        menu.scrollUp();
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    int state = menu.getState();
                    if(menu.isGameOver() == true) {
                        menu.gameOver(false);
                        resetGame();
                    } else if(state == 0) {
                        play = true;
                    } else if (state == 1) {
                        menu.setHelp(true);
                    } else if(state == 2) {
                        System.exit(0);
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    if(menu.getHelp()) {
                        menu.setHelp(false);
                    }
                    break;
            }
        }

        // In play mode:
        // right arrow key moves player to the right
        // left arrow key moves player to the left
        // space is used to shoot bullets.
        if(play) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_RIGHT:         
                    rightPressed = true;
                    break;
                case KeyEvent.VK_LEFT:
                    leftPressed = true;
                    break;
                case KeyEvent.VK_SPACE:
                    if(spacePressed == false) {
                        player.shoot();
                    }
                    spacePressed = true;
                    break;
            } 
        }
    }

    /**
     * This method keeps track of when the player has
     * released movement keys or shooting key. If left or right
     * arrow keys are released, the player will no longer move in that
     * direction. Space has to be released in order to shoot another
     * bullet.
     * 
     * @param e Keyevent object.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(play) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_RIGHT:         
                    rightPressed = false;
                    break;
                case KeyEvent.VK_LEFT:
                    leftPressed = false;
                    break;
                case KeyEvent.VK_SPACE:
                    spacePressed = false;
                    break;
            } 
        }
    }

    /**
     * This gets called every time "timer" field ticks (every 15 ms). The method
     * spawns in new enemies when necessary. It also moves the player if the player 
     * is pressing left or right arrow keys. This method also checks whether enemies go
     * out of screen, are hit by bullets or by player model and removes them if they 
     * do. Paint method is also called from here to display entities at their new position.
     * 
     * @param e ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if (play){
            if(enemyCount < maxEnemies) {
                this.addEnemy();
            }
            if(rightPressed == true) {
                player.moveRight();
            }
            if(leftPressed == true) {
                player.moveLeft();
            }
            if(score - prevScore == 100){
                maxEnemies += 1;
                prevScore += 100;
            }
            
            // Removing enemies that go out of frame 
            Iterator<MovingObject> it = enemies.iterator();
            while (it.hasNext()) {
                MovingObject enemy = it.next();
                enemy.move();

                if (player.hitbox(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight())){
                    play = false;
                    menu.gameOver(true);
                }

                if(player.hitbox(enemy)) {
                    score += 10;
                    it.remove();
                    enemyCount--;
                }

                if (enemy.getY() > 800) {
                    it.remove();
                    enemyCount--;
                }
            }
        }
        repaint(); // Calls paint() in this class
    }

    /**
     * This method resets the game. All necessary field variables
     * are set to default value.
     */
    public void resetGame() {
        play = false;
        enemies = new ArrayList<MovingObject>();
        player  = new Player(375, 700);
        enemyCount = 0;
        maxEnemies = 10;
        score = 0;
        prevScore = 0;
        leftPressed = false;
        rightPressed = false;
        spacePressed = false;
    }

    
    // Necessary override method

    @Override
    public void keyTyped(KeyEvent e){}   
}
