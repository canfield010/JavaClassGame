package com.canfield010.mygame.item.armor.chestplate;

import com.canfield010.mygame.item.armor.Armor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class RustyIronChestplate extends Armor {

    public static Image image;

    public RustyIronChestplate() {
        super("Rusty Iron Chestplate", 0.5F, Type.CHESTPLATE);
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
