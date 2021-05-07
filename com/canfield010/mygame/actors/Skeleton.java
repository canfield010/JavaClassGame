package com.canfield010.mygame.actors;

import com.canfield010.mygame.Main;
import com.canfield010.mygame.MapHolder;
import com.canfield010.mygame.item.weapon.RustyIronSword;
import com.canfield010.mygame.item.weapon.SharpenedStick;
import com.canfield010.mygame.mapsquare.MapSquare;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Skeleton extends Actor {

    public final byte searchRange = 25;
    public final byte attackRange = 15;
    public static Image image;

    public Skeleton() {
        super("Skeleton", (byte)4, null);
        inventory.meleeWeapon = new SharpenedStick();
        health = 5;
    }

    @Override
    public void tick() {
        super.tick();
        for (Actor actor: Main.actors) {
            if (actor instanceof Player) {
                int dx = actor.squareOn.coordinates.x - squareOn.coordinates.x;
                int dy = actor.squareOn.coordinates.y - squareOn.coordinates.y;
                if (Math.sqrt(dx*dx + dy*dy)<=searchRange) {
                    MapHolder<Boolean, Byte> squares = getSquaresToMoveTo();
                    Point point = new Point();
                    for (int x = -63; x<64; x++) {
                        for (int y = -63; y<64; y++) {
                            if (squares.get((byte)x, (byte)y)!=null) {
                                if (squares.get((byte)x, (byte)y)) {
                                    if (((actor.squareOn.coordinates.x - (squareOn.coordinates.x + x)) * (actor.squareOn.coordinates.x - (squareOn.coordinates.x + x))) + ((actor.squareOn.coordinates.y - (squareOn.coordinates.y + y)) * (actor.squareOn.coordinates.y - (squareOn.coordinates.y + y))) < ((actor.squareOn.coordinates.x - (squareOn.coordinates.x + point.x)) * (actor.squareOn.coordinates.x - (squareOn.coordinates.x + point.x))) + ((actor.squareOn.coordinates.y - (squareOn.coordinates.y + point.y)) * (actor.squareOn.coordinates.y - (squareOn.coordinates.y + point.y)))) {
                                        point.x = x;
                                        point.y = y;
                                    }
                                }
                            }
                        }
                    }
                    move(Main.mapSquares.get(point.x+squareOn.coordinates.x, point.y+squareOn.coordinates.y));
                }

                if (Math.sqrt(dx*dx + dy*dy)==1) {
                    inventory.meleeWeapon.use(actor.squareOn);
                    //System.out.println(actor.health);
                    //Main.animateArrow();
                }

                break;
            }
        }
    }
    public static void setImage() {
        try {
            image = ImageIO.read(new File("img/skeleton.png"));
        } catch (Exception ignored) {}
    }
    public Image getImage() {
        return image;
    };
}
