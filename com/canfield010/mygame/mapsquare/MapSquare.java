package com.canfield010.mygame.mapsquare;

import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.mapsquare.lowermapsquare.LowerMapSquare;
import com.canfield010.mygame.mapsquare.uppermapsquare.UpperMapSquare;

import java.awt.Point;

public class MapSquare {

    public MapSquare(LowerMapSquare lowerMapSquare, UpperMapSquare upperMapSquare, Actor actor, Point coordinates) {
        this.lowerMapSquare = lowerMapSquare;
        this.upperMapSquare = upperMapSquare;
        this.actor = actor;
        this.coordinates = coordinates;
    }
    public MapSquare(LowerMapSquare lowerMapSquare, UpperMapSquare upperMapSquare, Actor actor) {
        this.lowerMapSquare = lowerMapSquare;
        this.upperMapSquare = upperMapSquare;
        this.actor = actor;
    }

    private Point coordinates = new Point();
    public LowerMapSquare lowerMapSquare;
    public UpperMapSquare upperMapSquare;
    public Actor actor;

    public boolean isEmpty() {
        // The compiler did it this way and I think it is EPIC!
        return upperMapSquare == null && actor == null;
    }

    public boolean canMoveTo() {
        return true;
    }

    public Point getCoordinates() {
        return coordinates;
    }
}
