package com.canfield010.mygame.mapsquare.uppermapsquare.plant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class OakTree extends Plant {

    public static Image image;
    public OakTree() {
        super("Oak Tree", false, 100, false);
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
