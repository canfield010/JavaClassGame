package com.canfield010.mygame.mapsquare.uppermapsquare;

import com.canfield010.mygame.mapsquares.uppermapsquare.UpperMapSquare;

public class IronDoor extends UpperMapSquare implements Door, Lockable {
    public IronDoor() {
        super("Iron Door");
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
