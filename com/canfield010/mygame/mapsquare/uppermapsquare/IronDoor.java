package com.canfield010.mygame.mapsquare.uppermapsquare;

import com.canfield010.mygame.mapsquare.uppermapsquare.UpperMapSquare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class IronDoor extends UpperMapSquare implements Door, Lockable {

    public static Image image;

    public IronDoor() {
        super("Iron Door", false);
    }

    @Override
    public boolean open() {
        if (isLocked)
            return false;
        canMoveThrough = true;
        return true;
    }

    @Override
    public boolean close() {
        canMoveThrough = false;
        return true;
    }

    @Override
    public boolean unlock() {
        return false;
    }

    public static void setImage() {
        try {
            image = ImageIO.read(new File("img/tree.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public Image getImage() {
        return image;
    };
}
