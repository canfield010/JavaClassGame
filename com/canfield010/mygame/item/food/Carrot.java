package com.canfield010.mygame.item.food;

import com.canfield010.mygame.mapsquare.MapSquare;
import com.canfield010.mygame.mapsquare.lowermapsquare.Farmland;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Carrot extends Eatable {

    public static Image image;

    public Carrot() {
        super("Carrot", 2);
    }

    @Override
    public void use(MapSquare square) {
        square.upperMapSquare = new com.canfield010.mygame.mapsquare.uppermapsquare.plant.Carrot();
        this.count--;
    }

    @Override
    public boolean isUseful(MapSquare square) {
        return square.lowerMapSquare instanceof Farmland && square.isEmpty();
    }

    public static void setImage() {
        try {
            image = ImageIO.read(new File("img/grass.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public Image getImage() {
        return image;
    };
}
