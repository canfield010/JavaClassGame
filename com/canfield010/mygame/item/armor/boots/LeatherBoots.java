package com.canfield010.mygame.item.armor.boots;

import com.canfield010.mygame.item.armor.Armor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class LeatherBoots extends Armor {

    public static Image image;

    public LeatherBoots() {
        super("Leather Boots", 0.10F, Type.BOOTS);
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
