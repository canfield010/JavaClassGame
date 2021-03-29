package com.canfield010.mygame.mapsquare.uppermapsquare;

import com.canfield010.mygame.mapsquare.uppermapsquare.UpperMapSquare;

public class WoodenDoor extends UpperMapSquare implements Door, Lockable {
    public WoodenDoor() {
        super("Wooden Door", false);
    }

    @Override
    public boolean open() {
        if (isLocked)
            return false;
        canMoveThrough = true;
        return true;
    }

    @Override
    public boolean close() {
        canMoveThrough = false;
        return true;
    }

    @Override
    public boolean unlock() {
        return false;
    }
}
