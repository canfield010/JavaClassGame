package com.canfield010.mygame.item;

import com.canfield010.mygame.mapsquares.MapSquare;

public abstract class Item {
    public String name;
    public Item(String name) {
        this.name = name;
    }
    public abstract void use(MapSquare square);
    public abstract boolean isUseful(MapSquare square);
}
