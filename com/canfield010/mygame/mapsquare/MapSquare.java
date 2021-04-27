package com.canfield010.mygame.mapsquare;

import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.gui.Button;
import com.canfield010.mygame.mapsquare.FinalPoint;
import com.canfield010.mygame.mapsquare.lowermapsquare.LowerMapSquare;
import com.canfield010.mygame.mapsquare.uppermapsquare.UpperMapSquare;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapSquare extends JButton {

    public FinalPoint coordinates;
    public LowerMapSquare lowerMapSquare;
    public UpperMapSquare upperMapSquare;
    public Actor occupant;
    //public Button[] gui;
    //private String imageLocation;
    public Icon icon;

    public MapSquare(LowerMapSquare lowerMapSquare, UpperMapSquare upperMapSquare, Actor actor, FinalPoint coordinates) {
        this.lowerMapSquare = lowerMapSquare;
        this.upperMapSquare = upperMapSquare;
        this.occupant = actor;
        this.coordinates = coordinates;
        icon = lowerMapSquare.icon;
    }
    public MapSquare(LowerMapSquare lowerMapSquare, UpperMapSquare upperMapSquare, Actor actor) {
        this.lowerMapSquare = lowerMapSquare;
        this.upperMapSquare = upperMapSquare;
        this.occupant = actor;
        icon = lowerMapSquare.icon;
    }

    public boolean isEmpty() {
        // The compiler did it this way and I think it is EPIC!
        return upperMapSquare == null && occupant == null;
    }

    public void resetImage() {
        icon = lowerMapSquare.icon;
    }

    public boolean canMoveTo() {
        return true;
    }
}
