package com.canfield010.mygame.actors;

import com.canfield010.mygame.Main;
import com.canfield010.mygame.mapsquare.MapSquare;

import java.awt.*;

public class Player extends Actor {

    public String username = "";

    public Player(MapSquare squareOn) {
        super("Player", (byte)0, squareOn);
        Main.actors.add(this);
    }
}
