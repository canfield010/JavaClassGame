package com.canfield010.mygame.mapsquare.lowermapsquare;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LowerMapSquare {
    public String name;
    static String imageLocation;
    public static ImageIcon icon;
    public LowerMapSquare(String name, String imageLocation) {
        this.name = name;
        this.imageLocation = imageLocation;
        /*BufferedImage resizedImg = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        try {
            g2.drawImage(ImageIO.read(new File(imageLocation)), 0, 0, 32, 32, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.dispose();
        icon = new ImageIcon(resizedImg);*/
    }

}
