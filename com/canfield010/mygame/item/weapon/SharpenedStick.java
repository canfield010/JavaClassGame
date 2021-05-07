package com.canfield010.mygame.item.weapon;

import com.canfield010.mygame.mapsquare.MapSquare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class SharpenedStick extends Weapon {

    public static Image image;

    //private final int maxDamage = 3;
    //private final int minDamage = 2;

    public SharpenedStick() {
        super("Sharpened Stick", 3, 2);
    }

    @Override
    public void use(MapSquare square) {
        square.occupant.damage(new Random().nextInt(maxDamage - minDamage) + minDamage);
        //System.out.println("damaging");

        // or I could do (int)(Math.random()*(maxDamage-minDamage) + minDamage); Idk if that would be better considering it's using a static mehtod instead of making a new instance.
    }

    @Override
    public boolean isUseful(MapSquare square) {
        return !(square.occupant==null);
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
