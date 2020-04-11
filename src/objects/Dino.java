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
        dinoBottom = dinoTop + standDino.getHeight();

        switch (state) {
            case STAND:
                g.drawImage(standDino, dinoStartX, dinoTopY, null);
                g.drawString("P R E S S  'S P A C E'  T O  S T A R T", 120, 250);
                break;

            case JUMPING:
                if (dinoTop > topPoint && !topPointReached) {
                    g.drawImage(standDino, dinoStartX, dinoTop -= JUMP_FACTOR, null);
                    break;
                }
                if (dinoTop >= topPoint && !topPointReached) {
                    topPointReached = true;
                    g.drawImage(standDino, dinoStartX, dinoTop += JUMP_FACTOR, null);
                    break;
                }
                if (dinoTop > topPoint && topPointReached) {
                    if (dinoTopY == dinoTop && topPointReached) {
                        state = RUNNING;
                        topPointReached = false;
                        break;
                    }
                    g.drawImage(standDino, dinoStartX, dinoTop += JUMP_FACTOR, null);
                    break;
                }

            case RUNNING:
                if (foot == LEFT_FOOT) {
                    g.drawImage(rightFootDino, dinoStartX, dinoTopY, null);
                    foot = RIGHT_FOOT;
                } else if (foot == RIGHT_FOOT) {
                    g.drawImage(leftFootDino, dinoStartX, dinoTopY, null);
                    foot = LEFT_FOOT;
                } else if (foot == NO_FOOT) {
                    g.drawImage(leftFootDino, dinoStartX, dinoTopY, null);
                    foot = LEFT_FOOT;
                }
                break;

            case DIE:
                g.drawImage(deadDino, dinoStartX, dinoTop, null);
                g.drawString("G A M E  O V E R", 280, 150);
                break;
        }
    }

    public Rectangle getDinoRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.x = dinoStartX;

        if (state == JUMPING && !topPointReached) {
            rectangle.y = dinoTop - JUMP_FACTOR;
        } else if (state == JUMPING && topPointReached) {
            rectangle.y = dinoTop + JUMP_FACTOR;
        } else {
            rectangle.y = dinoTop;
        }

        rectangle.height = standDino.getHeight();
        rectangle.width = standDino.getWidth();

        return rectangle;
    }
}
