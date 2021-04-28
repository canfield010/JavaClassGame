package com.canfield010.mygame.mapsquare.lowermapsquare;

import com.canfield010.mygame.mapsquare.MapSquare;

public class Water extends LowerMapSquare {
    public Water() {
        super("Water", "img/grass.png");
    }
    public static void resetImage(int x, int y) {
        bufferedImage = MapSquare.getAnImage(imageLocation, x, y);
    }
}
