package com.canfield010.mygame.item.weapon;

import com.canfield010.mygame.item.Item;
import com.canfield010.mygame.mapsquare.MapSquare;

import java.util.Random;

public class SharpenedStick extends Weapon {

    //private final int maxDamage = 3;
    //private final int minDamage = 2;

    public SharpenedStick() {
        super("Sharpened Stick", 3, 2);
    }

    /*@Override
    public void use(MapSquare square) {
        square.actor.damage(
                new Random().nextInt(maxDamage - minDamage) + minDamage
        );

        // or I could do (int)(Math.random()*(maxDamage-minDamage) + minDamage); Idk if that would be better considering it's using a static mehtod instead of making a new instance.
    }*/

    @Override
    public boolean isUseful(MapSquare square) {
        return !(square.actor==null);
    }
}
