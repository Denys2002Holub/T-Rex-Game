import javax.swing.*;
import java.awt.*;

public class UserPanel {

    public static int
            WIDTH = 800,
            HEIGHT = 800;

    JFrame gameWindow = new JFrame("T-Rex game by raptor");

    public void createAndShowWindow() {
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container container = gameWindow.getContentPane();

        GamePanel gamePanel = new GamePanel();
        gamePanel.addKeyListener(gamePanel);
        gamePanel.setFocusable(true);

        container.setLayout(new BorderLayout());

        container.add(gamePanel, BorderLayout.CENTER);

        gameWindow.setSize(WIDTH, HEIGHT);
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);
    }

    public static void main(String[] args) {
        
    }
}
