package com.canfield010.mygame.mapsquare.uppermapsquare;

import com.canfield010.mygame.mapsquares.uppermapsquare.UpperMapSquare;

public class WoodenDoor extends UpperMapSquare implements Door, Lockable {
    public WoodenDoor() {
        super("Wooden Door");
    }

    @Override
    public boolean open() {
        return false;
    }

    @Override
    public boolean unlock() {
        return false;
    }
}
