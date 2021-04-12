package com.canfield010.mygame.mapsquare;

import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.gui.Button;
import com.canfield010.mygame.mapsquare.lowermapsquare.LowerMapSquare;
import com.canfield010.mygame.mapsquare.uppermapsquare.UpperMapSquare;

import javax.swing.*;

public abstract class MapSquare extends JButton {

    public FinalPoint coordinates;
    public LowerMapSquare lowerMapSquare;
    public UpperMapSquare upperMapSquare;
    public Actor occupant;
    public Button[] gui;

    public MapSquare(LowerMapSquare lowerMapSquare, UpperMapSquare upperMapSquare, Actor actor, FinalPoint coordinates) {
        this.lowerMapSquare = lowerMapSquare;
        this.upperMapSquare = upperMapSquare;
        this.occupant = actor;
        this.coordinates = coordinates;
    }
    public MapSquare(LowerMapSquare lowerMapSquare, UpperMapSquare upperMapSquare, Actor actor) {
        this.lowerMapSquare = lowerMapSquare;
        this.upperMapSquare = upperMapSquare;
        this.occupant = actor;
    }

    public boolean isEmpty() {
        // The compiler did it this way and I think it is EPIC!
        return upperMapSquare == null && occupant == null;
    }

    public boolean canMoveTo() {
        return true;
    }
}
