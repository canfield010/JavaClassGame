package com.canfield010.mygame.item.weapon;

import com.canfield010.mygame.item.Item;
import com.canfield010.mygame.mapsquare.MapSquare;

import java.util.Random;

public class IronSword extends Weapon {

    //private final int maxDamage = 12;
    //private final int minDamage = 9;

    IronSword() {
        super("Iron Sword", 12, 9);
    }

    /*@Override
    public void use(MapSquare square) {
        square.actor.damage(
                new Random().nextInt(maxDamage - minDamage) + minDamage
        );
    }*/

    @Override
    public boolean isUseful(MapSquare square) {
        return !(square.actor==null);
    }
}
