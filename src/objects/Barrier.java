package objects;

import resources.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

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

    private BarrierImage blockedAt;

    private int getBarrierInterval() {
        return (int) (Math.random() * ((400 - 250) + 1) + 250);
    }

    public Barrier(int panelWidth) {
        firstX = (int) (panelWidth * 1.5);
        speed = 10;

        barrierImagesList = new ArrayList<BarrierImage>();
        imagesList = new ArrayList<BufferedImage>();

        imagesList.add(new Resource().getResourceImage("../images/Cactus-1.png"));
        imagesList.add(new Resource().getResourceImage("../images/Cactus-2.png"));
        imagesList.add(new Resource().getResourceImage("../images/Cactus-5.png"));
        imagesList.add(new Resource().getResourceImage("../images/Cactus-2.png"));
        imagesList.add(new Resource().getResourceImage("../images/Cactus-1.png"));

        int x = firstX;

        for (BufferedImage bi : imagesList) {
            BarrierImage barrierImg = new BarrierImage();
            barrierImg.barrierImage = bi;
            barrierImg.x = firstX;
            barrierImg.y = Ground.GROUND_Y - bi.getHeight() + 5;
            x += getBarrierInterval();

            barrierImagesList.add(barrierImg);
        }
    }

    public void update() {
        Iterator<BarrierImage> barrierImageIterator = barrierImagesList.iterator();
        BarrierImage firstBI = new BarrierImage();

        firstBI.x -= speed;
        while (barrierImageIterator.hasNext()) {
            BarrierImage bi = barrierImageIterator.next();
            bi.x -= speed;
        }

        BarrierImage lastBI = barrierImagesList.get(barrierImagesList.size() - 1);

        if (firstBI.x < -firstBI.barrierImage.getWidth()) {
            barrierImagesList.remove(firstBI);
            firstBI.x = barrierImagesList.get(barrierImagesList.size() - 1).x + getBarrierInterval();
            barrierImagesList.add(firstBI);
        }
    }

    public void create(Graphics g) {
        for (BarrierImage bi : barrierImagesList) {
            g.drawImage(bi.barrierImage, bi.x, bi.y, null);
        }
    }

    public boolean isIntersect() {
        for (BarrierImage bi : barrierImagesList) {
            if(Dino.getDinoRectangle().intersects(bi.getRectangle())) {
                blockedAt = bi;
                return true;
            }
        }
        return false;
    }

    public void resume() {
        
    }
}
