package com.canfield010.mygame.mapsquare.lowermapsquare;

import com.canfield010.mygame.Main;
import com.canfield010.mygame.mapsquare.lowermapsquare.LowerMapSquare;

public class Farmland extends LowerMapSquare {

    public byte hydration = 0;

    public Farmland() {
        super("Farmland", "img/grass.png");
        Main.farmland.add(this);
    }
}
