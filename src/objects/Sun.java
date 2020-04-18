package objects;

import resources.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sun {

    public class SunImage{
        private BufferedImage image;
        int x;
    }

    private static int speed, sunY;

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

    public void create(Graphics g) {
        g.drawImage(image, sunX, sunY, null);
    }
}
