package objects;

import resources.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Dino {
    public static final int JUMP_FACTOR = 20;

    public static int dinoBaseY, dinoTopY, dinoStartX, dinoEndX;
    public static int dinoTop, dinoBottom, topPoint;

    public static boolean topPointReached;

    public static int state;

    public final int
            STAND = 1,
            RUNNING = 2,
            JUMPING = 3,
            DIE = 4;

    public static int foot;

    public final int
            LEFT_FOOT = 1,
            RIGHT_FOOT = 2,
            NO_FOOT = 3;

    static BufferedImage standDino;
    BufferedImage leftFootDino;
    BufferedImage rightFootDino;
    BufferedImage deadDino;

    public Dino() {
        standDino = new Resource().getResourceImage("../images/Dino-stand.png");
        leftFootDino = new Resource().getResourceImage("../images/Dino-left-up.png");
        rightFootDino = new Resource().getResourceImage("../images/Dino-right-up.png");
        deadDino = new Resource().getResourceImage("../images/Dino-big-eyes.png");

        dinoStartX = 100;
        dinoEndX = dinoStartX + standDino.getWidth();
        dinoBaseY = Ground.GROUND_Y + 5;
        dinoTopY = dinoBaseY - standDino.getHeight() + 5;

        topPoint = dinoTopY - 140;

        state = 1;
        foot = NO_FOOT;
    }

    public void create(Graphics g) {
        
    }
}
