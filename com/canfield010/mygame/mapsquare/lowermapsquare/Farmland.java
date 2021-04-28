package com.canfield010.mygame.mapsquare.lowermapsquare;

import com.canfield010.mygame.Main;
import com.canfield010.mygame.mapsquare.MapSquare;

import javax.swing.*;

public class Farmland extends LowerMapSquare {

    public byte hydration = 0;

    public Farmland() {
        super("Farmland", "img/grass.png");
        Main.farmland.add(this);
    }
    public static void resetImage(int x, int y) {
        bufferedImage = MapSquare.getABufferedImage(imageLocation, x, y);
    }
    //public static void setImage() {
        //image = new ImageIcon(imageLocation).getImage();
    //}
}
