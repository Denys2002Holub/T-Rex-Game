package objects;

import resources.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sun {

    private static int sunX, sunY;

    private BufferedImage image;

    public Sun(int panelHeight, int panelWidth) {

        image = new Resource().getResourceImage("../images/Sun.png");

        sunY = (int) (panelHeight - (0.6 * panelHeight));
        sunX = (int) (panelWidth - (0.15 * panelWidth));
    }

    public void create(Graphics g) {
        g.drawImage(image, sunX, sunY, null);
    }
}
