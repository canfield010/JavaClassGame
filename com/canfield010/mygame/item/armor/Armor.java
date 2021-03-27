package com.canfield010.mygame.item.armor;

import com.canfield010.mygame.item.Item;

public class Armor extends Item {

    public Armor(String name, float reduction, Type type) {
        super(name, 1);
        this.reduction = reduction;
        this.type = type;
    }

    public enum Type {
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS
    }

    public final float reduction;
    public final Type type;

    @Override
    public boolean isUseful(com.canfield010.mygame.mapsquares.MapSquare square) {
        return false;
    }

    @Override
    public void use(com.canfield010.mygame.mapsquares.MapSquare square) {}

}
