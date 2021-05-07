package com.canfield010.mygame.actors;

import com.canfield010.mygame.Main;
import com.canfield010.mygame.MapHolder;
import com.canfield010.mygame.item.HealingStone;
import com.canfield010.mygame.item.ProtectionRing;
import com.canfield010.mygame.item.armor.Armor;
import com.canfield010.mygame.mapsquare.FinalPoint;
import com.canfield010.mygame.mapsquare.MapSquare;
import com.canfield010.mygame.mapsquare.lowermapsquare.Grass;
import com.canfield010.mygame.mapsquare.lowermapsquare.Water;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public abstract class Actor {
    public Inventory inventory = new Inventory();
    public int health = 20;
    public int maxHealth = 20;
    public double width = 0.5;
    public byte movementRange;
    public String name;
    public MapSquare squareOn;
    MapHolder<Byte, Byte> squares;

    public Actor(String name, byte movementRange, MapSquare squareOn) {
        this.name = name;
        this.movementRange = movementRange;
        this.squareOn = squareOn;
    }

    public void damage(int damage) {
        for (Armor armor: inventory.armor) {
            if (armor!=null) {
                damage -= damage * armor.reduction;
            }
        }
        if (inventory.specialItem instanceof ProtectionRing)
            damage-=damage*15;
        health -= damage;
        if (health<=0) {
           squareOn.occupant = null;
           Main.actors.remove(this);
        }
    }

    public void move(MapSquare mapSquare) {
        if (squareOn!=null) {
            squareOn.occupant = null;
        }
        this.squareOn = mapSquare;
        mapSquare.occupant = this;
    }

    public void tick() {
        if (inventory.specialItem instanceof HealingStone) {
            if (((HealingStone)inventory.specialItem).coolDown==0) {
                if (health<maxHealth) {
                    health++;
                    ((HealingStone)inventory.specialItem).coolDown = 3;
                }
            } else {
                ((HealingStone)inventory.specialItem).coolDown--;
            }
        }



    }

    /*public static MapHolder<Boolean, Byte> getSquaresToMoveTo(FinalPoint start, byte length) {
        MapHolder<Boolean, Byte> mapHolder = new MapHolder<>();

        byte extendedLength = (byte)((double)length*1.42D);

        mapHolder.set((byte) 0, (byte) 0, true);




        return mapHolder;
    }

    private static MapHolder<Boolean, Byte> searchMapSquares(MapHolder<Boolean, Byte> holder, byte depth, int x, int y) {

        if (holder.get((byte)x, (byte)y)) {
            return holder;
        }

        if (Main.mapSquares.get(x, y).canMoveTo()) {
            holder.set(x, y, true);
            holder = searchMapSquares(holder, (byte)(depth-1), x+1, y);
            holder = searchMapSquares(holder, (byte)(depth-1), x, y+1);
            holder = searchMapSquares(holder, (byte)(depth-1), x-1, y);
            holder = searchMapSquares(holder, (byte)(depth-1), x, y-1);
        } else {
            return holder;
        }

        if (holder.get())

        return null;
    }*/
    public MapHolder<Boolean, Byte> getSquaresToMoveTo() {
        //System.out.println("actually though?");
        byte extendedRange = (byte)Math.floor(movementRange*1.42);
        byte negativeExtendedRange = (byte)(-extendedRange);
        squares = new MapHolder<>();
        squares.setClass(Byte.class);
        squares.set((byte)0, (byte)0, extendedRange);
        //squares = getAdjacentSquares(squares, extendedRange, 0, 0);
        getAdjacentSquares(extendedRange, 0, 0);
        //System.out.println(squares.get((byte)0, (byte)0));

        MapHolder<Boolean, Byte> boolSquares = new MapHolder<>();
        boolSquares.setClass(boolean.class);
        //System.out.println(squares.get((byte)(negativeExtendedRange+2), (byte)0));
        /*for (byte x = negativeExtendedRange; x<extendedRange+1; x++) {
            String string = "";
            for (byte y = negativeExtendedRange; y<extendedRange+1; y++) {
                string += squares.get(x, y);
                if (x==-4 && y==-1) {
                    string+=".";
                }
                string+=", ";
            }
            System.out.println(string);
        }*/

        for (byte x = negativeExtendedRange; x<extendedRange+1; x++) {
            //String string = "";
            for (byte y = negativeExtendedRange; y<extendedRange+1; y++) {
                //System.out.println(x+", "+y);
                //string += (squares.get(x, y))+", ";
                //System.out.println(squares.get(x, y));
                if (squares.get(x, y)==null) continue;

                //System.out.println("got one not null");
                //System.out.println(x+", "+y);
                ////////////////////
                boolSquares.set(x, y, true);
                /////////////
                //string += (boolSquares.get(x, y))+", ";


                if (extendedRange-movementRange>=squares.get(x, y)) {
                    //if (rayTrace(squares, x, y)) {
                        //boolSquares.set(x, y, true);
                    //} else {
                        //boolSquares.set(x, y, false);
                    //}
                    boolSquares.set(x, y, rayTraces(squares, x, y));
                    // I AM A GENIUS!!!!!!!!!!!!!!!!
                }
            }
            //System.out.println(string);
        }

        /*for (byte x = negativeExtendedRange; x<extendedRange+1; x++) {
            String string = "";
            for (byte y = negativeExtendedRange; y<extendedRange+1; y++) {
                string += boolSquares.get(x, y);
                if (x==-4 && y==-1) {
                    string+=".";
                }
                string+=", ";
            }
            System.out.println(string);
        }*/


        return boolSquares;
    }

    private boolean rayTraces(MapHolder<Byte, Byte> squares, byte x, byte y) {
        byte newX;// = x;
        byte newY;// = y;
        byte endX = 0;
        byte endY = 0;
        ArrayList<FinalPoint> path = new ArrayList<>();
        path.add(new FinalPoint(0, 0));

        do {
            //System.out.println("doing");
            newX = x;
            newY = y;
            while (!rayTrace(squares, newX, newY, endX, endY)) {
                //System.out.println(newX+", "+newY);
                if (squares.get((byte)(newX + 1), newY)!=null) {
                    //System.out.println("Trying num 1, x+1 has "+squares.get((byte)(x+1), y)+", and original has "+squares.get(x, y)+".");
                    if (squares.get((byte) (newX + 1), newY) > squares.get(newX, newY)) {
                        newX = (byte) (newX + 1);
                        //System.out.println("in");
                        break;
                    }
                }
                if (squares.get(newX, (byte)(newY+1))!=null) {
                    //System.out.println("Trying num 2, y+1 has "+squares.get(x, (byte)(y+1))+", and original has "+squares.get(x, y)+".");
                    if (squares.get(newX, (byte) (newY + 1)) > squares.get(newX, newY)) {
                        //System.out.println("in");
                        newY = (byte) (newY + 1);
                        //System.out.println("in");
                        break;
                    }
                }
                if (squares.get((byte)(newX - 1), newY)!=null) {
                    //System.out.println("Trying num 3, x-1 has "+squares.get((byte)(x-1), y)+", and original has "+squares.get(x, y)+".");
                    if (squares.get((byte) (newX - 1), newY) > squares.get(newX, newY)) {
                        newX = (byte) (newX - 1);
                        //System.out.println("in");
                        break;
                    }
                }
                //System.out.println(newX+", "+newY+", "+squares.get(newX, (byte)(newY)));
                //System.out.println(newX+", "+newY+", "+squares.get(newX, (byte)(newY-1)));
                if (squares.get(newX, (byte)(newY-1))!=null) {
                    //System.out.println("Trying num 4, y-1 has "+squares.get(x, (byte)(y-1))+", and original has "+squares.get(x, y)+".");
                    if (squares.get(newX, (byte) (newY - 1)) > squares.get(newX, newY)) {
                        newY = (byte) (newY - 1);
                        //System.out.println("in");
                        break;
                    }
                }
            }

            if (newX == endX && newY == endY) {
                return false;
                // this would happen if the entity is too fat to fit through a small hole, and probably wouldn't happen with the main character, but some other creatures might be too fat.
                // this function would probably only be used for the player though, so I wouldn't be surprised if it's unnecessary. I think the other creatures might use a much simpler method of transportation.
            }

            path.add(new FinalPoint(newX, newY));
            endX = newX;
            endY = newY;
        } while (newX != x || newY!= y);

        // never in my LIFE did I think I would ever use a do while statement! I just could never find a use for them until now! (I needed newX and newY to have been used for the while statement)

        float pathLength = 0;
        for (int index = 0; index<path.size()-1; index++) {
            int dx = path.get(index).x-path.get(index+1).x;
            int dy = path.get(index).y-path.get(index+1).y;
            pathLength += Math.sqrt((dx*dx) + (dy*dy));
        }
        //System.out.println(pathLength);
        //System.out.println(movementRange);
        //System.out.println("pathLength<movementRange: "+(pathLength<movementRange));
        return pathLength < movementRange;
        // maybe Later I'll try just < and not <= because it could make nicer circles.
        // I just finished writing this, and I really think there's a good chance it won't work, but we will see!
    }

    // needed a second function because false from the first function could mean that it's obstructed OR it could mean that the path is too long in which case we would not need to keep looking.
    private boolean rayTrace(MapHolder<Byte, Byte> squares, byte xStart, byte yStart, byte xEnd, byte yEnd) {
        // Got this from watching this youtube video: https://youtu.be/NbSee-XM7WA
        // The title is "Super Fast Ray Casting in Tiled Worlds using DDA." I had watched it a few months ago.
        double dx = xStart - xEnd;
        double dy = yStart - yEnd;
        double length = Math.sqrt((dx*dx)+(dy*dy));
        double xLength = Math.sqrt(1 + Math.abs(dy/dx));
        double yLength = Math.sqrt(1 + Math.abs(dx/dy));
        //xStart += (width/2)*(dx/dy);
        // that's wrong. Just figured out Math.sqrt(1 + dy/dx) == width, width^2 - 1 = dy/dx
        // no, c^2 = a^2 + b^2. I think that's what I gotta use. But I need to find a and b. I know a/b. c^2 = a^2 + ... It's late at night and I will do this later. For now, the creatures will clip through the sides of blocks.
        double lengthXTraveled = 0.0;
        double lengthYTraveled = 0.0;

        //double endX = 0.5;

        double x = xStart;// + 0.5;
        double y = yStart;// + 0.5;

        //x += yLength/(width/2);
        //xEnd += yLength/(width/2);
        //y += xLength/(width/2);
        //yEnd += xLength/(width/2);
        int xPos = xStart;
        int yPos = yStart;
        int xDirection = dx>0 ? 1 : -1;
        int yDirection = dy>0 ? 1 : -1;

        lengthXTraveled += Math.abs((xStart-x)*xLength);
        xPos += xDirection;
        lengthYTraveled += Math.abs((yStart-y)*yLength);
        yPos += yDirection;

        while (lengthXTraveled<length && lengthYTraveled<length) {
            if (lengthXTraveled>lengthYTraveled) {
                lengthXTraveled += xLength;
                xPos += xDirection;
            } else {
                lengthYTraveled += yLength;
                yPos += yDirection;
            }
            if (squares.get((byte)xPos, (byte)yPos)==null) {
                return false;
            }
        }
        /*
        x = xStart + 0.5;
        y = yStart + 0.5;
        x -= yLength/width;
        //xEnd -= yLength/width;
        y -= xLength/width;
        //yEnd -= xLength/width;

        lengthXTraveled = 0.0;
        lengthYTraveled = 0.0;
        xPos = xStart;
        yPos = yStart;

        lengthXTraveled += Math.abs((xStart-x)*xLength);
        xPos += xDirection;
        lengthYTraveled += Math.abs((yStart-y)*yLength);
        yPos += yDirection;

        while (lengthXTraveled<length && lengthYTraveled<length) {
            if (lengthXTraveled>lengthYTraveled) {
                lengthXTraveled += xLength;
                xPos += xDirection;
            } else {
                lengthYTraveled += yLength;
                yPos += yDirection;
            }
            if (squares.get((byte)xPos, (byte)yPos)==null) {
                return false;
            }
        }*/


        return true;
        // I think this looks good, but there's no way this works first try.
    }

    //private MapHolder<Byte, Byte> getAdjacentSquares(MapHolder<Byte, Byte> squares, byte depth, int x, int y) {
    private void getAdjacentSquares(byte depth, int x, int y) {
        if (depth>0) {
            if (Main.mapSquares.get(x + squareOn.coordinates.x, y + squareOn.coordinates.y).lowerMapSquare instanceof Water && Main.mapSquares.get(x + squareOn.coordinates.x, y + squareOn.coordinates.y).lowerMapSquare.canMoveThrough) {
                System.out.println("WFT??!?");
            }
            if (Main.mapSquares.get(x + squareOn.coordinates.x, y + squareOn.coordinates.y).lowerMapSquare instanceof  Water) {
                System.out.println("got wet here");
                System.out.println(squareOn.lowerMapSquare);
            }// else {
                //System.out.println(x+", "+y);
            //}
            if (Main.mapSquares.get(x + 1 + squareOn.coordinates.x, y + squareOn.coordinates.y).lowerMapSquare instanceof Water) {
                //System.out.println("got wet");
            }
            //System.out.println(Main.mapSquares.get(x + squareOn.coordinates.x, y + squareOn.coordinates.y).lowerMapSquare.canMoveThrough);
            //if (Main.mapSquares.get(x + 1 + squareOn.coordinates.x, y + squareOn.coordinates.y).canMoveTo() && (squares.get((byte)(x + 1), (byte)y) == null || squares.get((byte)(x + 1), (byte)y)<depth)) {
            //if ((Main.mapSquares.get(x + squareOn.coordinates.x, y + squareOn.coordinates.y).canMoveTo())) {
                if ((squares.get((byte) (x + 1), (byte) y) == null || squares.get((byte) (x + 1), (byte) y) < depth)) {
                    //System.out.println(Main.mapSquares.get(x + 1 + squareOn.coordinates.x, y + squareOn.coordinates.y).lowerMapSquare);
                    if (Main.mapSquares.get(x + 1 + squareOn.coordinates.x, y + squareOn.coordinates.y).canMoveTo()) {
                        squares.set((byte) (x + 1), (byte) y, (byte) (depth - 1));
                        getAdjacentSquares((byte) (depth - 1), x + 1, y);
                    } else {
                        //System.out.println("got wet here");
                    }
                }
                //if (Main.mapSquares.get(x + squareOn.coordinates.x, y + 1 + squareOn.coordinates.y).canMoveTo() && (squares.get((byte)x, (byte)(y + 1)) == null || squares.get((byte)x, (byte)(y + 1))<depth)) {
                if ((squares.get((byte) x, (byte) (y + 1)) == null || squares.get((byte) x, (byte) (y + 1)) < depth)) {
                    //System.out.println(Main.mapSquares.get(x + 1 + squareOn.coordinates.x, y + squareOn.coordinates.y).lowerMapSquare);
                    if (Main.mapSquares.get(x + squareOn.coordinates.x, y + 1 + squareOn.coordinates.y).canMoveTo()) {
                        squares.set((byte) x, (byte) (y + 1), (byte) (depth - 1));
                        getAdjacentSquares((byte) (depth - 1), x, y + 1);
                    }
                }
                //if (Main.mapSquares.get(x - 1 + squareOn.coordinates.x, y + squareOn.coordinates.y).canMoveTo() && (squares.get((byte)(x - 1), (byte)y) == null || squares.get((byte)(x - 1), (byte)y)<depth)) {
                if ((squares.get((byte) (x - 1), (byte) y) == null || squares.get((byte) (x - 1), (byte) y) < depth)) {
                    //System.out.println(Main.mapSquares.get(x + 1 + squareOn.coordinates.x, y + squareOn.coordinates.y).lowerMapSquare);
                    if (Main.mapSquares.get(x - 1 + squareOn.coordinates.x, y + squareOn.coordinates.y).canMoveTo()) {
                        squares.set((byte) (x - 1), (byte) y, (byte) (depth - 1));
                        getAdjacentSquares((byte) (depth - 1), x - 1, y);
                    }
                }
                //if (Main.mapSquares.get(x + squareOn.coordinates.x, y - 1 + squareOn.coordinates.y).canMoveTo() && (squares.get((byte)x, (byte)(y - 1)) == null || squares.get((byte)x, (byte)(y - 1))<depth)) {
                if ((squares.get((byte) x, (byte) (y - 1)) == null || squares.get((byte) x, (byte) (y - 1)) < depth)) {
                    //System.out.println(Main.mapSquares.get(x + 1 + squareOn.coordinates.x, y + squareOn.coordinates.y).lowerMapSquare);
                    if (Main.mapSquares.get(x + squareOn.coordinates.x, y - 1 + squareOn.coordinates.y).canMoveTo()) {
                        squares.set((byte) x, (byte) (y - 1), (byte) (depth - 1));
                        getAdjacentSquares((byte) (depth - 1), x, y - 1);
                    }
                }
            //}
        }
        //return squares;
    }

    public static ArrayList<Point> getPath(FinalPoint start, FinalPoint end, byte length) {

        ArrayList<ArrayList<FinalPoint>> paths = new ArrayList<>();
        paths.add(new ArrayList<FinalPoint>());
        paths.get(0).add(start);

        ArrayList<Point> closest = null;
        double closestDistance = length;

        while (length>0) {
            for (ArrayList<FinalPoint> path: paths) {
                boolean up = true;
                boolean down = true;
                boolean left = true;
                boolean right = true;
                for (FinalPoint point: path) {
                    if (up) {
                        if (!Main.mapSquares.get(point.x, point.y+1).canMoveTo()) {
                            up = false;
                        }
                    }
                    if (down) {
                        if (!Main.mapSquares.get(point.x, point.y+1).canMoveTo()) {
                            down = false;
                        }
                    }
                    if (left) {
                        if (!Main.mapSquares.get(point.x, point.y+1).canMoveTo()) {
                            left = false;
                        }
                    }
                    if (right) {
                        if (!Main.mapSquares.get(point.x, point.y+1).canMoveTo()) {
                            right = false;
                        }
                    }

                }
                FinalPoint point = path.get(path.size()-1);
                if (up) {
                    if (Main.mapSquares.get(point.x, point.y+1).canMoveTo()) {
                        ArrayList<Point> newList = (ArrayList)path.clone();
                        Point newPoint = new Point(point.x, point.y+1);
                        newList.add(newPoint);
                        if (newPoint.equals(end)) {
                            return newList;
                        }
                        int dx = newPoint.x - end.x;
                        int dy = newPoint.y - end.y;

                        if (Math.sqrt(dx*dx + dy*dy)<closestDistance) {
                            closestDistance = Math.sqrt(dx*dx + dy*dy);
                            closest = newList;
                        }
                    }
                }
                if (down) {
                    if (Main.mapSquares.get(point.x, point.y-1).canMoveTo()) {
                        ArrayList<Point> newList = (ArrayList)path.clone();
                        Point newPoint = new Point(point.x, point.y-1);
                        newList.add(newPoint);
                        if (newPoint.equals(end)) {
                            return newList;
                        }
                        int dx = newPoint.x - end.x;
                        int dy = newPoint.y - end.y;

                        if (Math.sqrt(dx*dx + dy*dy)<closestDistance) {
                            closestDistance = Math.sqrt(dx*dx + dy*dy);
                            closest = newList;
                        }
                    }
                }
                if (left) {
                    if (Main.mapSquares.get(point.x-1, point.y).canMoveTo()) {
                        ArrayList<Point> newList = (ArrayList)path.clone();
                        Point newPoint = new Point(point.x-1, point.y);
                        newList.add(newPoint);
                        if (newPoint.equals(end)) {
                            return newList;
                        }
                        int dx = newPoint.x - end.x;
                        int dy = newPoint.y - end.y;

                        if (Math.sqrt(dx*dx + dy*dy)<closestDistance) {
                            closestDistance = Math.sqrt(dx*dx + dy*dy);
                            closest = newList;
                        }
                    }
                }
                if (right) {
                    if (Main.mapSquares.get(point.x+1, point.y).canMoveTo()) {
                        ArrayList<Point> newList = (ArrayList)path.clone();
                        Point newPoint = new Point(point.x+1, point.y);
                        newList.add(newPoint);
                        if (newPoint.equals(end)) {
                            return newList;
                        }
                        int dx = newPoint.x - end.x;
                        int dy = newPoint.y - end.y;

                        if (Math.sqrt(dx*dx + dy*dy)<closestDistance) {
                            closestDistance = Math.sqrt(dx*dx + dy*dy);
                            closest = newList;
                        }
                    }
                }
            }
            length--;
        }

        return null;
    }

    public abstract Image getImage();

}
