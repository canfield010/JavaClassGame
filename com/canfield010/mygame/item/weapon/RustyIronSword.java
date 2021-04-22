package com.canfield010.mygame.item.weapon;

import com.canfield010.mygame.item.Item;
import com.canfield010.mygame.mapsquare.MapSquare;

import java.util.Random;

public class RustyIronSword extends Weapon {

    //private final int maxDamage = 8;
    //private final int minDamage = 5;

    RustyIronSword() {
        super("Rusty Iron Sword", 8, 5);
    }

    /*@Override
    public void use(MapSquare square) {
        square.actor.damage(
                new Random().nextInt(maxDamage - minDamage) + minDamage
        );
    }*/

    @Override
    public boolean isUseful(MapSquare square) {
        return !(square.occupant==null);
    }
}
