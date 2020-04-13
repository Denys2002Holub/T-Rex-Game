import objects.Barrier;
import objects.Dino;
import objects.Ground;
import objects.Sun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static int
            WIDTH,
            HEIGHT;

    private boolean
            running = false,
            gameOver = false;

    Ground ground;
    Dino dino;
    Barrier barrier;
    Sun sun;

    private Thread animator;

    private int score;

    public GamePanel() {
        WIDTH = UserPanel.WIDTH;
        HEIGHT = UserPanel.HEIGHT;

        ground = new Ground(HEIGHT);
        dino = new Dino();
        barrier = new Barrier(WIDTH);
        sun = new Sun(HEIGHT, WIDTH);

        score = 0;

        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setBackground(Color.lightGray);
    }


    public void paint(Graphics g) {
        super.paint(g);

        g.setFont(new Font("Courier New", Font.BOLD, 25));
        g.drawString(Integer.toString(score), getWidth() / 2 - 5, 100);

        ground.create(g);
        dino.create(g);
        barrier.create(g);
        sun.create(g);
    }

    @Override
    public void run() {
        running = true;

        while (running) {
            updateGame();
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateGame() {
        score++;

        ground.update();
        barrier.update();

        if (barrier.isIntersect()) {
            dino.die();
            repaint();
            running = false;
            gameOver = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == ' ') {
            if (gameOver) {
                score = 0;
                barrier.resume();
                gameOver = false;
            }
            if (animator == null || !running) {
                animator = new Thread(this);
                animator.start();
                dino.startRunning();
            } else {
                dino.jumping();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
