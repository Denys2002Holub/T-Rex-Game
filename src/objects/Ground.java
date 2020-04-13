package objects;

import resources.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Ground {

    private class GroundImage {
        BufferedImage groundImage;
        int x;
    }

    public static int GROUND_Y;

    public BufferedImage image;

    public ArrayList<GroundImage> groundImagesList;

    public Ground(int panelHeight) {
        GROUND_Y = (int) (panelHeight - (0.20 * panelHeight));

        try {
            image = new Resource().getResourceImage("../images/Ground.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        groundImagesList = new ArrayList<GroundImage>();

        for (int i = 0; i < 3; i++) {
            GroundImage groundImageObject = new GroundImage();
            groundImageObject.groundImage = image;
            groundImageObject.x = 0;

            groundImagesList.add(groundImageObject);
        }
    }

    public void update() {
        Iterator<GroundImage> iterator = groundImagesList.iterator();
        GroundImage first = iterator.next();

        first.x -= 10;
        int previousX = first.x;

        while (iterator.hasNext()) {
            GroundImage next = iterator.next();
            next.x = previousX + image.getWidth();
            previousX = next.x;
        }

        if (first.x < -image.getWidth()) {
            groundImagesList.remove(first);
            first.x = previousX + image.getWidth();
            groundImagesList.add(first);
        }
    }

    public void create(Graphics g) {
        for (GroundImage object : groundImagesList) {
            g.drawImage(object.groundImage, object.x, GROUND_Y, null);
        }
    }
}
