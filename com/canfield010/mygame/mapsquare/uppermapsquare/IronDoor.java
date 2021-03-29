package com.canfield010.mygame.mapsquare.uppermapsquare;

import com.canfield010.mygame.mapsquare.uppermapsquare.UpperMapSquare;

public class IronDoor extends UpperMapSquare implements Door, Lockable {
    public IronDoor() {
        super("Iron Door", false);
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
