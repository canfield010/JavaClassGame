package com.canfield010.mygame.item.weapon;

import com.canfield010.mygame.mapsquare.MapSquare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class RustyIronSword extends Weapon {

    public static Image image;

    //private final int maxDamage = 8;
    //private final int minDamage = 5;

    RustyIronSword() {
        super("Rusty Iron Sword", 8, 5);
    }

    /*@Override
    public void use(MapSquare square) {
        square.actor.damage(
                new Random().nextInt(maxDamage - minDamage) + minDamage
        );
    }*/

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
