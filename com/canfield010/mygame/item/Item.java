package com.canfield010.mygame.item;

import com.canfield010.mygame.mapsquare.MapSquare;

import java.awt.*;

public abstract class Item {
    public byte count;
    public final byte maximumCount;
    public String name;
    public String description = "";
    public Item(String name, byte maxCount) {
        this.name = name;
        this.maximumCount = maxCount;
    }
    public abstract void use(MapSquare square);
    public abstract boolean isUseful(MapSquare square);
    public abstract Image getImage();
}
