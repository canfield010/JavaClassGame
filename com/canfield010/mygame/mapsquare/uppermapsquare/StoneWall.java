package com.canfield010.mygame.mapsquare.uppermapsquare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class StoneWall extends UpperMapSquare {

    public static Image image;

    public StoneWall() {
        super("Stone Wall", false);
    }

    public static void setImage() {
        try {
            image = ImageIO.read(new File("img/stoneWall.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Image getImage() {
        return image;
    }
}
