package com.canfield010.mygame.mapsquare.lowermapsquare;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

public abstract class LowerMapSquare {
    public String name;

    public boolean canMoveThrough;

    public LowerMapSquare(String name, boolean canMoveThrough) {
        this.name = name;
        this.canMoveThrough = canMoveThrough;
    }

    public abstract Image getImage();




}
