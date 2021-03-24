package com.canfield010.mygame.gu;

import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.mapsquares.MapSquare;

public class AttackButton extends Button {
    AttackButton() {
        super("Attack");
    }

    @Override
    void click(MapSquare square) {
        square.actor.weapon.use(square);
    }
}
