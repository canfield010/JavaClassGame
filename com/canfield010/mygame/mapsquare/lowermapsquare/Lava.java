package com.canfield010.mygame.mapsquare.lowermapsquare;

import com.canfield010.mygame.mapsquare.MapSquare;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Lava extends LowerMapSquare {
    public static final String imageLocation = "img/grass.png";
    public static Image image;
    public Lava() {
        super("Lava");
    }

    public static void setImage() {
        try {
            image = ImageIO.read(new File("img/grass.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static String getImageLocation() {
        return "img/grass.png";
    };
}
