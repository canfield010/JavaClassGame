package com.canfield010.mygame.item;

import com.canfield010.mygame.mapsquare.MapSquare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class HealingStone extends Item {

    public static Image image;

    public byte coolDown = 0;

    public HealingStone() {
        super("Healing Stone", (byte)1);
        this.description = "Slowly heals you +1/2 heart every 4 turns";
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
