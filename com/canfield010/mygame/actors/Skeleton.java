package com.canfield010.mygame.actors;

import com.canfield010.mygame.Main;
import com.canfield010.mygame.mapsquare.MapSquare;

import java.awt.Point;

public class Skeleton extends Actor {

    public final byte searchRange = 20;
    public final byte attackRange = 25;

    public Skeleton(MapSquare squareOn) {
        super("Skeleton", (byte)5, squareOn);
    }

    @Override
    public void tick() {
        super.tick();
        for (Actor actor: Main.actors) {
            if (actor instanceof Player) {
                int dx = actor.squareOn.getCoordinates().x - squareOn.getCoordinates().x;
                int dy = actor.squareOn.getCoordinates().y - squareOn.getCoordinates().y;
                if (Math.sqrt(dx*dx + dy*dy)<=searchRange) {
                    Main.animate(Player.getPath(squareOn.getCoordinates(), actor.squareOn.getCoordinates(), movementRange));
                }

                if (Math.sqrt(dx*dx + dy*dy)<=attackRange) {
                    inventory.rangedWeapon.use(actor.squareOn);
                    Main.animateArrow();
                }

                break;
            }
        }
    }
}
