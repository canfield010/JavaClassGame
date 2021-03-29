package com.canfield010.mygame.actors;

import com.canfield010.mygame.Main;

import java.awt.*;

public class Player extends Actor {

    public String username = "";

    public Player() {
        super("Player", (byte)0, new Point());
        Main.actors.add(this);
    }
}
