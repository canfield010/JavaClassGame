package com.canfield010.mygame.mapsquare.lowermapsquare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Stone extends LowerMapSquare{

    public static Image image;

    public Stone() {
        super("Stone", true);
    }

    public static void setImage() {
        try {
            image = ImageIO.read(new File("img/stone.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public Image getImage() {
        return image;
    };

}