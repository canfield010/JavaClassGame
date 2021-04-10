package com.canfield010.mygame.actors;

import com.canfield010.mygame.Main;
import com.canfield010.mygame.MapHolder;
import com.canfield010.mygame.item.HealingStone;
import com.canfield010.mygame.item.ProtectionRing;
import com.canfield010.mygame.item.armor.Armor;
import com.canfield010.mygame.mapsquare.FinalPoint;
import com.canfield010.mygame.mapsquare.MapSquare;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Actor {
    public Inventory inventory;
    public int health = 20;
    public int maxHealth = 20;
    public byte movementRange;
    public String name;
    public MapSquare squareOn;

    public Actor(String name, byte movementRange, MapSquare squareOn) {
        this.name = name;
        this.movementRange = movementRange;
        this.squareOn = squareOn;
    }

    public void damage(int damage) {
        for (Armor armor: inventory.armor) {
            damage-=damage*armor.reduction;
        }
        if (inventory.specialItem instanceof ProtectionRing)
            damage-=damage*15;
    }

    public void move(MapSquare mapSquare) {
        this.squareOn = mapSquare;
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
        byte extendedRange = (byte)(movementRange*1.42);



        return null;
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
                        if (!Main.squares[point.x][point.y+1].canMoveTo()) {
                            up = false;
                        }
                    }
                    if (down) {
                        if (!Main.squares[point.x][point.y+1].canMoveTo()) {
                            down = false;
                        }
                    }
                    if (left) {
                        if (!Main.squares[point.x][point.y+1].canMoveTo()) {
                            left = false;
                        }
                    }
                    if (right) {
                        if (!Main.squares[point.x][point.y+1].canMoveTo()) {
                            right = false;
                        }
                    }

                }
                FinalPoint point = path.get(path.size()-1);
                if (up) {
                    if (Main.squares[point.x][point.y+1].canMoveTo()) {
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
                    if (Main.squares[point.x][point.y-1].canMoveTo()) {
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
                    if (Main.squares[point.x-1][point.y].canMoveTo()) {
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
                    if (Main.squares[point.x+1][point.y].canMoveTo()) {
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

    public void remove() {
        Main.actors.remove(this);
    }

}
