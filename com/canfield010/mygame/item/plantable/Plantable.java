package com.canfield010.mygame.item.plantable;

import com.canfield010.mygame.item.Item;
import com.canfield010.mygame.mapsquare.lowermapsquare.Farmland;
import com.canfield010.mygame.mapsquare.MapSquare;

public abstract class Plantable extends Item {

    boolean requiresFarmLand;

    public Plantable(String name, boolean requiresFarmLand) {
        super(name, (byte)32);
        this.requiresFarmLand = requiresFarmLand;
    }

    @Override
    public void use(MapSquare square) {
        if (!requiresFarmLand || square.lowerMapSquare instanceof Farmland) {

        }
    }

    @Override
    public boolean isUseful(MapSquare square) {
        return false;
    }
}
