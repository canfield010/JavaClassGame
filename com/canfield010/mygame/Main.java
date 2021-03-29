package com.canfield010.mygame;

import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.mapsquare.lowermapsquare.Farmland;
import com.canfield010.mygame.mapsquare.uppermapsquare.plant.Plant;
import com.canfield010.mygame.mapsquare.MapSquare;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    private static final Random PLANT_RANDOM = new Random();
    private static float growthToGo = 0;
    private static float dryingToGo = 0;
    public static final int PLANT_GROWTH_RATE = 10;
    // default is 10.

    private static final int MAP_SIZE = 256;
    public static MapSquare[][] squares = new MapSquare[MAP_SIZE][MAP_SIZE];
    public static ArrayList<Farmland> farmland = new ArrayList<>();
    public static ArrayList<MapSquare> plants = new ArrayList<>();
    public static ArrayList<Actor> actors = new ArrayList<>();

    public static void main(String[] args) {

        // it can change these even though they're final because the selected address is final, not the data inside.
        //squares[0][0] = new MapSquare(new Water(), null, null, new Point());
        //farmland.add((Farmland)new MapSquare(new Farmland(), null, null, new Point(0,1)).lowerMapSquare);
        //plants.add(new MapSquare(new Farmland(), null, null, new Point(0, 2)));

        /*MapHolder<Double> mapHolder = new MapHolder<>();
        try {
            mapHolder.get(1D, 5D);
        } catch (Exception e) {
            System.out.println(e);
        }*/
    }

    public void tick() {
        tickActors();
        tickPlants();
    }

    public void tickActors() {
        for (Actor actor: actors) {
            actor.tick();
        }
    }


    public void tickPlants() {
        // very slowly drying out farmland
        dryingToGo += ((float)farmland.size()* PLANT_GROWTH_RATE)/10000F;
        growthToGo += ((float)plants.size()* PLANT_GROWTH_RATE)/1000F;

        while (dryingToGo>1) {
            farmland.get(PLANT_RANDOM.nextInt(farmland.size())).hydration--;
            dryingToGo--;
        }

        while (growthToGo>1) {
            MapSquare theSquare = plants.get(PLANT_RANDOM.nextInt(farmland.size()));
            Plant thePlant = (Plant)theSquare.upperMapSquare;
            if (theSquare.lowerMapSquare instanceof Farmland) {
                if (((Farmland) theSquare.lowerMapSquare).hydration > 0) {
                    thePlant.grow();
                } else {
                    if (Math.random()>0.5) {
                        thePlant.grow();
                    }
                }
            }
            growthToGo--;
        }

    }

    public static void animate(ArrayList<Point> path) {
        // I have no idea how this will work but it WILL work. I will find a way to make this look EPIC! EPIC I say!
    }
    public static void animateArrow() {

    }

}
