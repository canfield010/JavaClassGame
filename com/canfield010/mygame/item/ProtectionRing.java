package com.canfield010.mygame.item;

import com.canfield010.mygame.mapsquare.MapSquare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class ProtectionRing extends Item {

    public static Image image;
    public ProtectionRing() {
        super("Ring of Protection", (byte)1);
        this.description = "Decreases damage dealt by an additional 15%";
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
