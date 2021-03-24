package com.canfield010.mygame.item.weapon;

import com.canfield010.mygame.item.Item;
import com.canfield010.mygame.mapsquares.MapSquare;

public class IronSword extends Item {
    IronSword() {
        super("Iron Sword");
    }

    @Override
    public void use(MapSquare square) {
        if (isUseful(square)) {
            // deals damage
        }
    }

    @Override
    public boolean isUseful(MapSquare square) {
        return !(square.actor==null);
    }
}
