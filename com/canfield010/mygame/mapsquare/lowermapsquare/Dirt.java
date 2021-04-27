package com.canfield010.mygame.mapsquare.lowermapsquare;

import com.canfield010.mygame.mapsquare.MapSquare;

import javax.swing.*;

public class Dirt extends LowerMapSquare{

    //public static ImageIcon icon;

    public Dirt() {
        super("Dirt", "img/dirt.png");
    }

    public static void resetImage(int x, int y) {
        icon = MapSquare.getImage(imageLocation, x, y);
    }

}
