package com.canfield010.mygame.item;

import com.canfield010.mygame.mapsquare.MapSquare;

public class Backpack extends Item {

    public Item[] inventory = new Item[12];

    public Backpack() {
        super("Backpack", (byte)1);
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
