package com.canfield010.mygame.item;

import com.canfield010.mygame.mapsquare.MapSquare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Rock extends Item {

    public static Image image;
    public Rock() {
        super("Rock", (byte)32);
    }

    @Override
    public void use(MapSquare square) {

    }

    @Override
    public boolean isUseful(MapSquare square) {
        return false;
    }

    public static void setImage() {
        try {
            image = ImageIO.read(new File("img/grass.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public Image getImage() {
        return image;
    };
}
