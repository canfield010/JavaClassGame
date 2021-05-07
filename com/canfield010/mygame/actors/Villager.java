package com.canfield010.mygame.actors;

import com.canfield010.mygame.item.Item;
import com.canfield010.mygame.item.Log;
import com.canfield010.mygame.item.tool.Axe;
import com.canfield010.mygame.mapsquare.MapSquare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Villager extends Actor {

    public static Image image;

    public Item itemToBuy = new Log();
    public int cost = 50;
    public Item itemToSell = new Axe();
    public int value = 5;
    public int swordCost = 150;

    public Villager() {
        super("Villager", (byte)2, null);
    }

    public static void setImage() {
        try {
            image = ImageIO.read(new File("img/player.png"));
        } catch (Exception ignored) {}
    }
    @Override
    public Image getImage() {
        return image;
    }
}
