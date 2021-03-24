package com.canfield010.mygame.item;

import com.canfield010.mygame.mapsquares.MapSquare;

public class Backpack extends Item {

    public Backpack() {
        super("Backpack", 1);
    }

    @Override
    public void use(MapSquare square) {
        // can't be used on a square
    }

    @Override
    public boolean isUseful(MapSquare square) {
        return false;
    }
}
