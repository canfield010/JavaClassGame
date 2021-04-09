package com.canfield010.mygame.item.armor;

import com.canfield010.mygame.item.Item;
import com.canfield010.mygame.mapsquare.MapSquare;

public class Armor extends Item {

    public final float reduction;
    public final Type type;

    public Armor(String name, float reduction, Type type) {
        super(name, (byte)1);
        this.reduction = reduction;
        this.type = type;
    }

    public enum Type {
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS
    }

    @Override
    public boolean isUseful(MapSquare square) {
        return false;
    }

    @Override
    public void use(MapSquare square) {}

}
