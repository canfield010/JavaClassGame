package com.canfield010.mygame.actors;

import com.canfield010.mygame.Main;
import com.canfield010.mygame.item.ProtectionRing;
import com.canfield010.mygame.item.armor.Armor;
import com.canfield010.mygame.mapsquare.MapSquare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Player extends Actor {

    public static Image image;

    public Player(MapSquare squareOn) {
        super("Player", (byte)10, squareOn);
        Main.actors.add(this);
    }

    public static void setImage() {
        try {
            image = ImageIO.read(new File("img/player.png"));
        } catch (Exception ignored) {}
    }
    public Image getImage() {
        return image;
    };

    @Override
    public void damage(int damage) {
        for (Armor armor: inventory.armor) {
            if (armor!=null) {
                damage -= damage * armor.reduction;
            }
        }
        if (inventory.specialItem instanceof ProtectionRing)
            damage-=damage*15;
        health -= damage;
    }
}
