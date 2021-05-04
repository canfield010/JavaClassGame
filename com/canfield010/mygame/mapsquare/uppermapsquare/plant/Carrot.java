package com.canfield010.mygame.mapsquare.uppermapsquare.plant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Carrot extends Plant {

    public static Image image;
    public Carrot() {
        super("Carrot", true, 8, true);
    }

    public static void setImage() {
        try {
            image = ImageIO.read(new File("img/tree.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public Image getImage() {
        return image;
    };
}
