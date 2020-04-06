package objects;

import resources.Resource;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Ground {

    private class GroundImage {
        BufferedImage groundImage;
        int x;
    }
    
    private static int GROUND_Y;

    private BufferedImage image;

    private ArrayList<GroundImage> groundImagesList;

    public Ground (int panelHeight) {
        GROUND_Y = (int)(panelHeight - (0.20*panelHeight));

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
}
