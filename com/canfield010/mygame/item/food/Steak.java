package com.canfield010.mygame.item.food;

import com.canfield010.mygame.mapsquare.MapSquare;

public class Steak extends Eatable {

    //private int healthBoost = 7;

    public Steak() {
        super("Steak", 7);
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
