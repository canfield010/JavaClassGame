package com.canfield010.mygame.item;

import com.canfield010.mygame.mapsquare.MapSquare;

public class HealingStone extends Item {

    public byte coolDown = 0;

    public HealingStone() {
        super("Healing Stone", (byte)1);
        this.description = "Slowly heals you +1/2 heart every 4 turns";
    }

    @Override
    public void use(MapSquare square) {

    }

    @Override
    public boolean isUseful(MapSquare square) {
        return false;
    }
}
