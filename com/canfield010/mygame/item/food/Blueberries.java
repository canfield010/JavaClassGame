package com.canfield010.mygame.item.food;

import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.item.Item;
import com.canfield010.mygame.mapsquares.MapSquare;

public class Blueberries extends Eatable {

    //private int healthBoost = 2;

    public Blueberries() {
        super("Blueberries", 2);
    }

    @Override
    public void use(MapSquare square) {

    }

    @Override
    public boolean isUseful(MapSquare square) {
        return false;
    }

    /*@Override
    public void eat(Actor actor) {
        actor.health+=healthBoost;
    }*/
}
