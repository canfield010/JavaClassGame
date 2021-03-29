package com.canfield010.mygame.item.food;

import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.item.Item;

public abstract class Eatable extends Item {

    public Eatable(String name, int healthBoost) {
        super(name, (byte)32);
        this.healthBoost = healthBoost;
    }

    //public abstract void eat(Actor actor);

    public int healthBoost;

    public void eat(Actor actor) {
        actor.health+=healthBoost;
    }

}
