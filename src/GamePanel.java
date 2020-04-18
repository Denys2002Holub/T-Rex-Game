import objects.Barrier;
import objects.Dino;
import objects.Ground;
import objects.Sun;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

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
    private int maxScore;

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
        g.drawString(Integer.toString(score), (getWidth() / 2 - 5) + 175, 50);
        g.drawString("max: " + Integer.toString(getMaxScore()), getWidth() / 2 - 5, 50);

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
        getGameSpeed();

        getBackgroundColor();

        if (score % 500 == 0) {
            getScoreSound();
        }

        ground.update();
        barrier.update();

        if (barrier.isIntersect()) {
            dino.die();
            repaint();
            running = false;
            gameOver = true;
            getDinoDieSound();
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
                getDinoJumpSound();
            }
        }
    }

    public void getGameSpeed() {
        if (score < 500) {
            barrier.speed = 10;
            ground.speed = 10;
            score++;
        } else if (score < 1000) {
            barrier.speed = 12;
            ground.speed = 12;
            score += 2;
        } else {
            barrier.speed = 14;
            ground.speed = 14;
            score += 4;
        }
    }

    public void getBackgroundColor() {
        if (score < 3000) {
            setBackground(Color.lightGray);
        } else if (score < 14000) {
            setBackground(Color.black);
        } else if (score < 25000) {
            setBackground(Color.lightGray);
        } else if (score < 40000) {
            setBackground(Color.black);
        } else {
            setBackground(Color.lightGray);
        }
    }

    public int getMaxScore() {
        if (gameOver) {
            if (score > maxScore) {
                maxScore = score;
            }
        }
        return maxScore;
    }

    public void getDinoJumpSound() {
        try {
            File dinoJumpSound = new File("..\\T-Rex-Game\\src\\sounds\\dinoJump.wav");

            AudioInputStream ais = AudioSystem.getAudioInputStream(dinoJumpSound);

            Clip clip = AudioSystem.getClip();

            clip.open(ais);

            clip.setFramePosition(0);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDinoDieSound() {
        try {
            File dinoDieSound = new File("..\\T-Rex-Game\\src\\sounds\\dinoDie.wav");

            AudioInputStream ais = AudioSystem.getAudioInputStream(dinoDieSound);

            Clip clip = AudioSystem.getClip();

            clip.open(ais);

            clip.setFramePosition(0);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void getScoreSound() {
        try {
            File scoreSound = new File("..\\T-Rex-Game\\src\\sounds\\scoreSound.wav");

            AudioInputStream ais = AudioSystem.getAudioInputStream(scoreSound);

            Clip clip = AudioSystem.getClip();

            clip.open(ais);

            clip.setFramePosition(0);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
