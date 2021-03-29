package com.canfield010.mygame.item.weapon;

import com.canfield010.mygame.item.Item;
import com.canfield010.mygame.mapsquare.MapSquare;

import java.util.Random;

public abstract class Weapon extends Item {

    public Weapon(String name, int maxDamage, int minDamage) {
        super(name, (byte)1);
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
    }

    public int maxDamage;
    public int minDamage;

    @Override
    public void use(MapSquare square) {
        square.actor.damage(
                new Random().nextInt(maxDamage - minDamage) + minDamage
        );
    }

}
