package com.canfield010.mygame.item;

import com.canfield010.mygame.mapsquare.MapSquare;

public class Rock extends Item {
    public Rock() {
        super("Rock", (byte)32);
    }

    @Override
    public void use(MapSquare square) {

    }

    @Override
    public boolean isUseful(MapSquare square) {
        return false;
    }
}
