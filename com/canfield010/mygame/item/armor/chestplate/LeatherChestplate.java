package com.canfield010.mygame.item.armor.chestplate;

import com.canfield010.mygame.item.armor.Armor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class LeatherChestplate extends Armor {

    public static Image image;

    public LeatherChestplate() {
        super("Leather Chestplate", 0.2F, Type.CHESTPLATE);
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
