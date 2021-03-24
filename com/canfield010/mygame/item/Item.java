package com.canfield010.mygame.item;

import com.canfield010.mygame.mapsquares.MapSquare;

public abstract class Item {
    public int count;
    public final int maximumCount;
    public String name;
    public Item(String name, int maxCount) {
        this.name = name;
        this.maximumCount = maxCount;
    }
    public abstract void use(MapSquare square);
    public abstract boolean isUseful(MapSquare square);
}
