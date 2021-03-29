package com.canfield010.mygame.item;

import com.canfield010.mygame.mapsquare.MapSquare;

public class ProtectionRing extends Item {
    public ProtectionRing() {
        super("Ring of Protection", (byte)1);
        this.description = "Decreases damage dealt by an additional 15%";
    }

    @Override
    public void use(MapSquare square) {

    }

    @Override
    public boolean isUseful(MapSquare square) {
        return false;
    }
}
