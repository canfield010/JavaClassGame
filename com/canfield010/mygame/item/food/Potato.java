package com.canfield010.mygame.item.food;

import com.canfield010.mygame.mapsquare.MapSquare;
import com.canfield010.mygame.mapsquare.lowermapsquare.Farmland;

public class Potato extends Eatable {
    public Potato() {
        super("Carrot", 2);
    }

    @Override
    public void use(MapSquare square) {
        square.upperMapSquare = new com.canfield010.mygame.mapsquare.uppermapsquare.plant.Potato();
        this.count--;
    }

    @Override
    public boolean isUseful(MapSquare square) {
        return square.lowerMapSquare instanceof Farmland && square.isEmpty();
    }
}
