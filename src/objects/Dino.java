package objects;

import java.awt.image.BufferedImage;

public class Dino {
    public static final int JUMP_FACTOR = 20;

    public static int dinoBaseY, dinoTopY, dinoStartX, dinoEndX;
    public static int dinoTop, dinoBottom, topPoint;

    public static boolean topPointReached;

    public static int state;

    public final int
            STAND = 0,
            RUNNING = 1,
            JUMPING = 2,
            DIE = 3;

    public static int foot;

    public final int
            LEFT_FOOT = 0,
            RIGHT_FOOT = 1,
            NO_FOOT = 3;

    static BufferedImage standDino;
    BufferedImage leftFootDino;
    BufferedImage rightFootDino;
    BufferedImage deadDino;

    public Dino() {
        
    }
}
