package com.canfield010.mygame.mapsquare;

import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.gui.Button;
import com.canfield010.mygame.mapsquare.FinalPoint;
import com.canfield010.mygame.mapsquare.lowermapsquare.*;
import com.canfield010.mygame.mapsquare.uppermapsquare.UpperMapSquare;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class MapSquare extends JButton {

    public FinalPoint coordinates;
    public LowerMapSquare lowerMapSquare;
    public UpperMapSquare upperMapSquare;
    public Actor occupant;
    //public Button[] gui;
    //private String imageLocation;
    //public Icon icon;

    public MapSquare(LowerMapSquare lowerMapSquare, UpperMapSquare upperMapSquare, Actor actor, FinalPoint coordinates) {
        this.lowerMapSquare = lowerMapSquare;
        this.upperMapSquare = upperMapSquare;
        this.occupant = actor;
        this.coordinates = coordinates;
        //icon = lowerMapSquare.icon;
    }
    public MapSquare(LowerMapSquare lowerMapSquare, UpperMapSquare upperMapSquare, Actor actor) {
        this.lowerMapSquare = lowerMapSquare;
        this.upperMapSquare = upperMapSquare;
        this.occupant = actor;
        //icon = lowerMapSquare.icon;
    }

    public boolean isEmpty() {
        // The compiler did it this way and I think it is EPIC!
        return upperMapSquare == null && occupant == null;
    }

    //public void resetImage(int x, int y) {
        //icon = lowerMapSquare.icon;
    //}
    public static void resetImages(int x, int y) {
        Dirt.resetImage(x, y);
        Farmland.resetImage(x, y);
        Grass.resetImage(x, y);
        Lava.resetImage(x, y);
        Water.resetImage(x, y);
        WoodenPlanks.resetImage(x, y);
    }
    public static void initalizeImages() {
        Dirt.setImage();
        Farmland.setImage();
        Grass.setImage();
        Lava.setImage();
        Water.setImage();
        WoodenPlanks.setImage();
    }

    public static BufferedImage getABufferedImage(String imageLocation, int x, int y) {
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        try {
            g2.drawImage(ImageIO.read(new File(imageLocation)), 0, 0, x, y, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.dispose();
        return bufferedImage;
    }

    //public static Image getAnImage(String imageLoation)

    public ImageIcon getImage(int x, int y) {
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
        //Image bufferedImage = lowerMapSquare.image;
        Graphics2D g2 = bufferedImage.createGraphics();
        /*BufferedImageOp bufferedImageOp = new BufferedImageOp() {
            @Override
            public BufferedImage filter(BufferedImage src, BufferedImage dest) {
                return null;
            }
            @Override
            public Rectangle2D getBounds2D(BufferedImage src) {
                return null;
            }
            @Override
            public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM) {
                return null;
            }
            @Override
            public Point2D getPoint2D(Point2D srcPt, Point2D dstPt) {
                return null;
            }
            @Override
            public RenderingHints getRenderingHints() {
                return null;
            }};*/

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        if (g2 == null) {
            System.out.println("It truly is null!");
        }
        //g2.drawImage(lowerMapSquare.bufferedImage, bufferedImageOp, 0, 0);
        //g2.drawImage(lowerMapSquare.image, 0, 0, x, y, new ImageObserver() {
            //@Override
            //public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                //return false;
            //}
        //});
        //try {
            //g2.drawImage(ImageIO.read(new File(lowerMapSquare.imageLocation)), 0, 0, x, y, null);
        //} catch (IOException e) {
            //e.printStackTrace();
        //}
        g2.drawImage(lowerMapSquare.image, 0, 0, x, y, null);
        g2.dispose();
        return new ImageIcon(bufferedImage);
    }

    public boolean canMoveTo() {
        return true;
    }

    public void drawGraphics(Graphics2D graphics, int x, int y) {
        if (graphics==null) {
            return;
        }
        System.out.println(lowerMapSquare.bufferedImage==null);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        //graphics.drawImage(lowerMapSquare.bufferedImage, 0, 0, x, y, imageObserver);
        System.out.println(lowerMapSquare.image==null);
        graphics.drawImage(lowerMapSquare.image, 0, 0, x, y, null);
        graphics.dispose();
    }
}
