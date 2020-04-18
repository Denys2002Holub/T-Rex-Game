package objects;

import resources.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Sun {

    public class SunImage {
        private BufferedImage image;
        int x;
    }

    public static int speed, sunY;

    private BufferedImage image;

    public ArrayList<SunImage> sunImagesList;

    public Sun(int panelHeight, int panelWidth) {
        sunY = (int) (panelHeight - (0.6 * panelHeight));

        try {
            image = new Resource().getResourceImage("../images/Sun.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        sunImagesList = new ArrayList<SunImage>();

        SunImage sunImage = new SunImage();
        sunImage.image = image;
        sunImage.x = panelWidth;
        sunImagesList.add(sunImage);
    }

    public void update() {
        Iterator<SunImage> iterator = sunImagesList.iterator();
        SunImage sunImage = iterator.next();

        sunImage.x -= speed;

        int previousX = sunImage.x;

        if (sunImage.x < -image.getWidth()) {
            sunImagesList.remove(sunImage);
            sunImage.x = 800;
            sunImagesList.add(sunImage);
        }
    }

    public void create(Graphics g) {
        for (SunImage sunImage : sunImagesList) {
            g.drawImage(sunImage.image, sunImage.x, sunY, null);
        }
    }
}
