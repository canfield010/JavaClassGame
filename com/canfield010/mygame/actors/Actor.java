package com.canfield010.mygame.actors;

import com.canfield010.mygame.Main;
import com.canfield010.mygame.item.HealingStone;
import com.canfield010.mygame.item.ProtectionRing;
import com.canfield010.mygame.item.armor.Armor;
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

    public static ArrayList<Point> getPath(Point start, Point end, byte length) {

        ArrayList<ArrayList<Point>> paths = new ArrayList<>();
        paths.add(new ArrayList<Point>());
        paths.get(0).add(start);

        ArrayList<Point> closest = null;
        double closestDistance = length;

        while (length>0) {
            for (ArrayList<Point> path: paths) {
                boolean up = true;
                boolean down = true;
                boolean left = true;
                boolean right = true;
                for (Point point: path) {
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
                Point point = path.get(path.size()-1);
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
