package com.canfield010.mygame.item.tool;

import com.canfield010.mygame.item.Item;
import com.canfield010.mygame.mapsquare.MapSquare;

public class Key extends Item {

    public Key() {
        super("Key", (byte)1);
    }

    @Override
    public void use(MapSquare square) {

    }

    @Override
    public boolean isUseful(MapSquare square) {
        return false;
    }
}
