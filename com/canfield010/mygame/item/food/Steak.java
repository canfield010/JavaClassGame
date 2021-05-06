package com.canfield010.mygame.item.food;

import com.canfield010.mygame.mapsquare.MapSquare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Steak extends Eatable {

    public static Image image;

    //private int healthBoost = 7;

    public Steak() {
        super("Steak", 7);
    }

    @Override
    public void use(MapSquare square) {

    }

    @Override
    public boolean isUseful(MapSquare square) {
        return false;
    }

    /*@Override
    public void eat(Actor actor) {
        actor.health+=healthBoost;
    }*/

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
