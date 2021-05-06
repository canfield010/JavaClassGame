package com.canfield010.mygame.item.armor.boots;

import com.canfield010.mygame.item.armor.Armor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class IronBoots extends Armor {

    public static Image image;

    public IronBoots() {
        super("Iron Boots", 0.30F, Type.BOOTS);
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
