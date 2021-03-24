package com.canfield010.mygame.gui;

//import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.mapsquares.MapSquare;

public abstract class Button {
    public String name;
    public Button(String name) {
        this.name = name;
    }
    abstract void click(Actor actor, MapSquare target);
}