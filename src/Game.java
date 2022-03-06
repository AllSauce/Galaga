import javax.swing.JFrame;

/**
 * The main class that contains the main method and creates the jframe and gameplay class and starts the game
 * 
 * This game is a game inspired by the old arcade game galaga, the player can move around using the left/right key
 * and shoot using the space bar. The bojective with the game is to get the higgest possible score by shooting enemies
 * and surrviving as long as possible by avoiding the enemies
 *
 * @author Marcus Frodigh, Leo Vainio, Krenar Manxhuka
 * @version 1.0
 * @since 1.0
 */
public class Game {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true"); // Enables video acceleration for linux (DO NOT REMOVE)

        JFrame   frame    = new JFrame("GARAGA");
        GamePlay gamePlay = new GamePlay();

        frame.setBounds(10,10,800, 800);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.add(gamePlay);
    }
}
