package com.canfield010.mygame.mapsquare;

import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.actors.Player;
import com.canfield010.mygame.actors.Villager;
import com.canfield010.mygame.mapsquare.lowermapsquare.*;
import com.canfield010.mygame.mapsquare.uppermapsquare.IronDoor;
import com.canfield010.mygame.mapsquare.uppermapsquare.StoneWall;
import com.canfield010.mygame.mapsquare.uppermapsquare.UpperMapSquare;
import com.canfield010.mygame.mapsquare.uppermapsquare.WoodenDoor;
import com.canfield010.mygame.mapsquare.uppermapsquare.plant.BlueberryBush;
import com.canfield010.mygame.mapsquare.uppermapsquare.plant.Carrot;
import com.canfield010.mygame.mapsquare.uppermapsquare.plant.OakTree;
import com.canfield010.mygame.mapsquare.uppermapsquare.plant.Potato;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapSquare{

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
    }
    public MapSquare(LowerMapSquare lowerMapSquare, UpperMapSquare upperMapSquare, Actor actor) {
        this.lowerMapSquare = lowerMapSquare;
        this.upperMapSquare = upperMapSquare;
        this.occupant = actor;
    }

    public boolean isEmpty() {
        // The compiler did it this way and I think it is EPIC!
        return upperMapSquare == null && occupant == null;
    }
    public static void initalizeImages() {
        // lowerMapSquares:
        Dirt.setImage();
        Farmland.setImage();
        Grass.setImage();
        Lava.setImage();
        Water.setImage();
        WoodenPlanks.setImage();
        Stone.setImage();
        // upperMapSquares:
        IronDoor.setImage();
        WoodenDoor.setImage();
        BlueberryBush.setImage();
        Carrot.setImage();
        OakTree.setImage();
        Potato.setImage();
        Player.setImage();
        StoneWall.setImage();
    }

    /*public static BufferedImage getABufferedImage(String imageLocation, int x, int y) {
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
    }*/

    public ImageIcon getImage(int x, int y) {
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();

        //g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        //if (lowerMapSquare == null) {
            //System.out.println("It truly is null!");
        //}
        g2.drawImage(lowerMapSquare.getImage(), 0, 0, x, y, null);
        if (upperMapSquare!=null) {
            g2.drawImage(upperMapSquare.getImage(), 0, 0, x, y, null);
        }
        if (occupant!=null) {
            g2.drawImage(occupant.getImage(), 0, 0, x, y, null);
            //g2.drawImage(new Grass().image, 0, 0, x, y, null);
        }
        g2.dispose();
        return new ImageIcon(bufferedImage);
    }

    public void setActor(Actor actor) {
        this.occupant = actor;
    }

    public boolean canMoveTo() {
        /*if (upperMapSquare!=null) {
            return lowerMapSquare.canMoveThrough && upperMapSquare.canMoveThrough && occupant == null;
        } else {
            return lowerMapSquare.canMoveThrough && occupant == null;
        }*/
        if (upperMapSquare==null) {
            return lowerMapSquare.canMoveThrough && occupant == null;
        } else {
            return lowerMapSquare.canMoveThrough && upperMapSquare.canMoveThrough && occupant == null;
        }
    }
    public UpperMapSquare.Button[] getButtons() {
        if (upperMapSquare!=null) {
            if (occupant == null) {
                return upperMapSquare.buttons;
            } else {
                if (occupant instanceof Villager) {
                    UpperMapSquare.Button[] btns = new UpperMapSquare.Button[upperMapSquare.buttons.length + 3];
                    System.arraycopy(upperMapSquare.buttons, 0, btns, 0, upperMapSquare.buttons.length);
                    btns[btns.length - 3] = UpperMapSquare.Button.TRADE;
                    btns[btns.length - 2] = UpperMapSquare.Button.ATTACK;
                    btns[btns.length - 1] = UpperMapSquare.Button.SHOOT;
                    return btns;
                } else {
                    UpperMapSquare.Button[] btns = new UpperMapSquare.Button[upperMapSquare.buttons.length + 2];
                    System.arraycopy(upperMapSquare.buttons, 0, btns, 0, upperMapSquare.buttons.length);
                    btns[btns.length - 2] = UpperMapSquare.Button.ATTACK;
                    btns[btns.length - 1] = UpperMapSquare.Button.SHOOT;
                    return btns;
                }
            }
        } else {
            if (occupant==null) {
                return null;
            } else {
                if (occupant instanceof Villager) {
                    //System.out.println("he's a villy");
                    UpperMapSquare.Button[] btns = new UpperMapSquare.Button[3];
                    //System.arraycopy(upperMapSquare.buttons, 0, btns, 0, upperMapSquare.buttons.length);
                    btns[btns.length - 3] = UpperMapSquare.Button.TRADE;
                    btns[btns.length - 2] = UpperMapSquare.Button.ATTACK;
                    btns[btns.length - 1] = UpperMapSquare.Button.SHOOT;
                    return btns;
                } else {
                    UpperMapSquare.Button[] btns = new UpperMapSquare.Button[2];
                    //System.arraycopy(upperMapSquare.buttons, 0, btns, 0, upperMapSquare.buttons.length);
                    btns[btns.length - 2] = UpperMapSquare.Button.ATTACK;
                    btns[btns.length - 1] = UpperMapSquare.Button.SHOOT;
                    return btns;
                }
            }
        }
    }
}
