package com.canfield010.mygame.mapsquare.lowermapsquare;

import com.canfield010.mygame.mapsquare.MapSquare;
import com.canfield010.mygame.mapsquare.lowermapsquare.LowerMapSquare;

public class Lava extends LowerMapSquare {
    public Lava() {
        super("Lava", "img/grass.png");
    }
    public static void resetImage(int x, int y) {
        icon = MapSquare.getImage(imageLocation, x, y);
    }
}
