import objects.Barrier;
import objects.Dino;
import objects.Ground;
import objects.Sun;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static final int
            WIDTH = 800,
            HEIGHT = 800;

    private boolean
            running = false,
            gameOver = false;

    Ground ground;
    Dino dino;
    Barrier barrier;
    Sun sun;

    private int score;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {

    }
}
