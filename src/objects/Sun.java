package objects;

import Resource.Resource;

import java.awt.image.BufferedImage;

public class Sun {

    private class SunImage{
        BufferedImage image;
        int x, y;
    }

    private BufferedImage image;

    public Sun(int panelHeight, int panelWidth) {

        try {
            image = new Resource().getResourceImage("../images/Sun.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SunImage sunImageObject = new SunImage();
        sunImageObject.image = image;
        sunImageObject.y = (int)(panelHeight-(0.6*panelHeight));
        sunImageObject.x = (int)(panelWidth - (0.15*panelWidth));
    }
}
