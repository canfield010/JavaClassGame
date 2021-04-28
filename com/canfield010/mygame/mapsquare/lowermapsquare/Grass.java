package com.canfield010.mygame.mapsquare.lowermapsquare;

import com.canfield010.mygame.mapsquare.MapSquare;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Grass extends LowerMapSquare {
    public static final String imageLocation = "img/grass.png";
    public static Image image;
    public Grass() {
        super("Grass");
        //this.imageLocation = "img/grass.png";
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
