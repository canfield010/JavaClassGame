package com.canfield010.mygame.item;

import com.canfield010.mygame.mapsquare.MapSquare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class SmallBackpack extends Item {

    public static Image image;

    public Item[] inventory = new Item[6];

    public SmallBackpack() {
        super("Small Backpack", (byte)1);
    }

    @Override
    public void use(MapSquare square) {
        // can't be used on a square
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
