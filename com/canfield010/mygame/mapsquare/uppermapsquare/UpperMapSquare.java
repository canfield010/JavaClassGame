package com.canfield010.mygame.mapsquare.uppermapsquare;

import java.awt.*;

public abstract class UpperMapSquare {
    public String name;
    public boolean canMoveThrough;
    public Button[] buttons;
    public UpperMapSquare(String name, boolean canMoveThrough) {
        this.name = name;
        this.canMoveThrough = canMoveThrough;
    }

    public abstract Image getImage();

    public enum Button {
        USE_ITEM,
        DESTROY
    }

}
