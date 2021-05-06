package com.canfield010.mygame.item.armor.helmet;

import com.canfield010.mygame.item.armor.Armor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class LeatherHelmet extends Armor {

    public static Image image;
    public LeatherHelmet() {
        super("Leather Helmet", 0.15F, Type.HELMET);
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
