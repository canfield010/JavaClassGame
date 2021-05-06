package com.canfield010.mygame.item.armor.leggings;

import com.canfield010.mygame.item.armor.Armor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class RustyIronLeggings extends Armor {

    public static Image image;

    public RustyIronLeggings() {
        super("Rusty Iron Leggings", 0.30F, Type.LEGGINGS);
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
