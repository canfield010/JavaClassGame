package com.canfield010.mygame.mapsquares;

import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.mapsquares.lowermapsquare.LowerMapSquare;
import com.canfield010.mygame.mapsquares.uppermapsquare.UpperMapSquare;

public class MapSquare {
    public LowerMapSquare lowerMapSquare;
    public UpperMapSquare upperMapSquare;
    public Actor actor;

    public boolean isEmpty() {
        // The compiler did it this way and I think it is EPIC!
        return upperMapSquare == null && lowerMapSquare == null;
    }
}
