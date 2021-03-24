package com.canfield010.mygame.gui;

import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.mapsquares.MapSquare;

public class AttackButton extends Button {
    AttackButton() {
        super("Attack");
    }

    @Override
    void click(Actor actor, MapSquare target) {
        //attacks with weapon in melee slot in inventory
        actor.inventory.meleeWeapon.use(target);
    }
}
