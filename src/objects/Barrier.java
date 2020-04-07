package objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Barrier {

    public class BarrierImage {
        BufferedImage barrierImage;
        int x, y;

        public Rectangle getRectangle() {
            Rectangle rectangle = new Rectangle();
            rectangle.x = x;
            rectangle.y = y;
            rectangle.width = barrierImage.getWidth();
            rectangle.height = barrierImage.getHeight();

            return rectangle;
        }
    }

    private int speed, firstX;

    ArrayList<BarrierImage> barrierImagesList;
    ArrayList<BufferedImage> imagesList;

    private int getBarrierInterval() {
        return (int) (Math.random() * ((400 - 250) + 1) + 250);
    }

    public Barrier(int panelWidth) {
        
    }
}
