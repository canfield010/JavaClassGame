package com.canfield010.mygame.mapsquare.lowermapsquare;

import com.canfield010.mygame.mapsquare.MapSquare;
import com.canfield010.mygame.mapsquare.lowermapsquare.LowerMapSquare;

public class Grass extends LowerMapSquare {
    public Grass() {
        super("Grass", "img/grass.png");
    }
    public static void resetImage(int x, int y) {
        icon = MapSquare.getImage(imageLocation, x, y);
    }
}
