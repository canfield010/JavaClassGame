package com.canfield010.mygame.item.tool;

import com.canfield010.mygame.Main;
import com.canfield010.mygame.item.Item;
import com.canfield010.mygame.item.Log;
import com.canfield010.mygame.mapsquare.MapSquare;
import com.canfield010.mygame.mapsquare.uppermapsquare.plant.OakTree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Axe extends Item {

    public static Image image;

    public Axe() {
        super("Axe", (byte)1);
    }

    @Override
    public void use(MapSquare square) {
        if (square.upperMapSquare instanceof OakTree) {
            int logsToGive = Math.random()>0.5 ? 4 : 5;
            square.upperMapSquare = null;
            for (int i = 0; i<12; i++) {
                if (Main.player.inventory.storage[i] instanceof Log) {
                    while (Main.player.inventory.storage[i].count<Main.player.inventory.storage[i].maximumCount && logsToGive>0) {
                        Main.player.inventory.storage[i].count++;
                        logsToGive--;
                    }
                    if (logsToGive==0) break;
                }
            }
            if (logsToGive == 0) return;
            for (int i = 0; i<12; i++) {
                if (Main.player.inventory.storage[i] == null) {
                    Main.player.inventory.storage[i] = new Log();
                    while (Main.player.inventory.storage[i].count<Main.player.inventory.storage[i].maximumCount && logsToGive>0) {
                        Main.player.inventory.storage[i].count++;
                        logsToGive--;
                    }
                    if (logsToGive==0) break;
                }
            }
        }
    }

    @Override
    public boolean isUseful(MapSquare square) {
        if (square.upperMapSquare instanceof OakTree) {
            return true;
        } else {
            return false;
        }
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
