package com.canfield010.mygame;

import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.actors.Player;
import com.canfield010.mygame.mapsquare.FinalPoint;
import com.canfield010.mygame.gui.Gui;
import com.canfield010.mygame.mapsquare.lowermapsquare.Dirt;
import com.canfield010.mygame.mapsquare.lowermapsquare.Farmland;
import com.canfield010.mygame.mapsquare.uppermapsquare.plant.Plant;
import com.canfield010.mygame.mapsquare.MapSquare;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static Point playerPosition = new Point();
    private static final Random PLANT_RANDOM = new Random();
    private static float growthToGo = 0;
    private static float dryingToGo = 0;
    public static final int PLANT_GROWTH_RATE = 10;
    // default is 10.

    //private static final int VISION_SIZE = 256;
    //private static final int MAP_SIZE = 256;
    //public static MapSquare[][] squares = new MapSquare[MAP_SIZE][MAP_SIZE];
    public static MapHolder<MapSquare, Integer> backgroundSquares = new MapHolder<>();
    public static MapHolder<MapSquare, Integer> gameSquares = new MapHolder<>();
    public static MapHolder<MapSquare, Byte> mySquares = new MapHolder<>();
    public static ArrayList<Farmland> farmland = new ArrayList<>();
    public static ArrayList<MapSquare> plants = new ArrayList<>();
    public static ArrayList<Actor> actors = new ArrayList<>();

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        backgroundSquares.setClass(MapSquare.class);
        gameSquares.setClass(MapSquare.class);
        //mySquares.setClass(MapSquare.class);
        backgroundSquares.set(10, 10, new MapSquare(new Dirt(), null, new Player(backgroundSquares.get(10, 10)), new FinalPoint(10, 10)));
        backgroundSquares.set(11, 11, new MapSquare(new Dirt(), null, null, new FinalPoint(11, 11)));
        //mySquares.set((byte)-10, (byte)-10, new MapSquare(new Dirt(), null, null, new FinalPoint(-10, -10)));
        //System.out.println(backgroundSquares.get(10, 10).lowerMapSquare.name);
        //MapSquare.initalizeImages();
        //for (int byte1 = -128; byte1<128; byte1++) {
            //for (int byte2 = -128; byte2<128; byte2++) {
                //mySquares.set((byte)byte1, (byte)byte2, new MapSquare(new Dirt(), null, null, new FinalPoint(-10, -10)));
            //}
        //}
        //System.out.println(mySquares.get((byte)10, (byte)10).lowerMapSquare.name);

        UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Gui.createAndShowGui("testing testing 123");
            }
        });


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
        //MapHolder<String, Byte> testing = new MapHolder<>();
        //testing.setClass(String.class);
        //byte ten = 10;
        //System.out.println(testing.get(ten, ten));
        //testing.set(ten, ten, "hello");
        //System.out.println();
        //System.out.println(testing.get(ten, ten));
        //System.out.println(testing.head.nextDownLeft);
        //testing.set(ten, ten, "hello");
        //System.out.println();
        //System.out.println(testing.get(ten, ten));
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
