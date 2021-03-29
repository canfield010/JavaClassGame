package com.canfield010.mygame.gui;

import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.mapsquare.MapSquare;

public class AttackRangedButton extends Button {

    public AttackRangedButton() {
        super("Shoot");
    }

    @Override
    void click(Actor actor, MapSquare target) {
        //Attacks with weapon in ranged slot in inventory
        actor.inventory.rangedWeapon.use(target);
    }
}
