package com.canfield010.mygame.mapsquare.lowermapsquare;

import com.canfield010.mygame.mapsquare.MapSquare;

import javax.swing.*;

public class Lava extends LowerMapSquare {
    public Lava() {
        super("Lava", "img/grass.png");
    }
    public static void resetImage(int x, int y) {
        bufferedImage = MapSquare.getABufferedImage(imageLocation, x, y);
    }
    //public static void setImage() {
        //image = new ImageIcon(imageLocation).getImage();
    //}
}
