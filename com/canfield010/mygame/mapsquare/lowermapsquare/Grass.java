package com.canfield010.mygame.mapsquare.lowermapsquare;

import com.canfield010.mygame.mapsquare.MapSquare;

import javax.swing.*;

public class Grass extends LowerMapSquare {
    public Grass() {
        super("Grass", "img/grass.png");
    }
    public static void resetImage(int x, int y) {
        bufferedImage = MapSquare.getABufferedImage(imageLocation, x, y);
    }
    //public static void setImage() {
        //image = new ImageIcon(imageLocation).getImage();
        //image = (new ImageIcon("img/grass.png")).getImage();
    //}
}
