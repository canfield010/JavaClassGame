package com.canfield010.mygame.item.food;

import com.canfield010.mygame.mapsquare.MapSquare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Blueberries extends Eatable {

    public static Image image;

    //private int healthBoost = 2;

    public Blueberries() {
        super("Blueberries", 2);
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
