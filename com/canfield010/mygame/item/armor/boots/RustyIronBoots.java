package com.canfield010.mygame.item.armor.boots;

import com.canfield010.mygame.item.armor.Armor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class RustyIronBoots extends Armor {

    public static Image image;

    public RustyIronBoots() {
        super("Rusty Iron Boots", 0.25F, Type.BOOTS);
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
