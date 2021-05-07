package com.canfield010.mygame;

import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.actors.Player;
import com.canfield010.mygame.actors.Skeleton;
import com.canfield010.mygame.actors.Villager;
import com.canfield010.mygame.item.*;
import com.canfield010.mygame.item.armor.boots.IronBoots;
import com.canfield010.mygame.item.armor.boots.LeatherBoots;
import com.canfield010.mygame.item.armor.boots.RustyIronBoots;
import com.canfield010.mygame.item.armor.chestplate.IronChestplate;
import com.canfield010.mygame.item.armor.chestplate.LeatherChestplate;
import com.canfield010.mygame.item.armor.chestplate.RustyIronChestplate;
import com.canfield010.mygame.item.armor.helmet.IronHelmet;
import com.canfield010.mygame.item.armor.helmet.LeatherHelmet;
import com.canfield010.mygame.item.armor.helmet.RustyIronHelmet;
import com.canfield010.mygame.item.armor.leggings.IronLeggings;
import com.canfield010.mygame.item.armor.leggings.LeatherLeggings;
import com.canfield010.mygame.item.armor.leggings.RustyIronLeggings;
import com.canfield010.mygame.item.food.Blueberries;
import com.canfield010.mygame.item.food.CarrotItem;
import com.canfield010.mygame.item.food.PotatoItem;
import com.canfield010.mygame.item.food.Steak;
import com.canfield010.mygame.item.tool.Axe;
import com.canfield010.mygame.item.tool.Key;
import com.canfield010.mygame.item.tool.Pickaxe;
import com.canfield010.mygame.item.weapon.IronSword;
import com.canfield010.mygame.item.weapon.RustyIronSword;
import com.canfield010.mygame.item.weapon.SharpenedStick;
import com.canfield010.mygame.mapsquare.FinalPoint;
import com.canfield010.mygame.gui.Gui;
import com.canfield010.mygame.mapsquare.lowermapsquare.*;
import com.canfield010.mygame.mapsquare.uppermapsquare.StoneWall;
import com.canfield010.mygame.mapsquare.uppermapsquare.plant.OakTree;
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
    //public static boolean testing = false;
    // default is 10.

    //private static final int VISION_SIZE = 256;
    //private static final int MAP_SIZE = 256;
    //public static MapSquare[][] squares = new MapSquare[MAP_SIZE][MAP_SIZE];
    public static MapHolder<MapSquare, Integer> backgroundSquares = new MapHolder<>();
    public static MapHolder<MapSquare, Integer> gameSquares = new MapHolder<>();
    public static MapHolder<MapSquare, Integer> mapSquares;
    //public static MapHolder<MapSquare, Byte> mySquares = new MapHolder<>();
    public static ArrayList<Farmland> farmland = new ArrayList<>();
    public static ArrayList<MapSquare> plants = new ArrayList<>();
    public static ArrayList<Actor> actors = new ArrayList<>();
    public static Player player;// = new Player(gameSquares.get(0, 0));

    //public static InitialSquares[][] initialSquares = {
    public static byte[][] initialSquares = {
            //{InitialSquares.GRASS, },
            {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,3,3,3,4,3,3,3,4,3,3,3,3,3,3,3,3,3,3,3,3,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,3,4,3,4,3,4,3,4,3,4,4,4,4,4,4,4,4,4,4,4,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,3,4,3,3,3,4,3,3,3,4,3,3,3,3,3,3,3,4,3,3,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,3,4,3,4,4,4,4,4,4,4,3,4,4,4,4,4,3,4,4,4,4,4,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,3,4,3,3,3,3,3,3,3,3,3,3,3,4,4,4,3,3,3,3,3,4,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,3,4,4,4,4,4,4,4,4,4,3,4,3,4,4,4,4,4,4,4,3,4,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
            {4,4,3,3,3,4,3,3,3,4,3,3,3,4,3,3,3,3,3,4,3,4,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,4,4,3,4,4,4,3,4,3,4,4,4,4,4,4,4,3,4,3,4,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
            {4,4,3,4,3,4,3,3,3,3,3,4,3,3,3,3,3,4,3,3,3,4,3,4,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
            {4,4,3,4,3,4,3,4,3,4,4,4,4,4,4,4,3,4,4,4,4,4,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
            {4,4,3,3,3,4,3,4,3,3,3,3,3,3,3,4,3,4,3,3,3,4,3,4,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,4,4,4,4,3,4,4,4,3,4,4,4,3,4,3,4,3,4,4,4,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,3,3,3,3,3,4,3,3,3,3,3,4,3,3,3,4,3,3,3,3,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
            {4,4,3,4,4,4,4,4,3,4,3,4,3,4,4,4,4,4,4,4,4,4,4,4,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,3,4,3,3,3,3,3,4,3,4,3,4,3,3,3,3,3,4,3,3,3,4,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
            {4,4,3,4,3,4,4,4,4,4,3,4,3,4,3,4,4,4,3,4,4,4,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,3,4,3,4,3,3,3,3,3,4,3,4,3,3,3,4,3,3,3,3,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,3,4,4,4,3,4,4,4,4,4,3,4,4,4,3,4,4,4,4,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
            {4,4,3,3,3,4,3,4,3,3,3,4,3,4,3,3,3,4,3,3,3,3,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,4,4,3,4,3,4,4,4,3,4,3,4,3,4,4,4,3,4,4,4,3,4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,3,3,3,4,3,3,3,3,3,4,3,3,3,3,3,3,3,4,3,3,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
            {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,3,4,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {2,2,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,0,1,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0}
    };

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        backgroundSquares.setClass(MapSquare.class);
        gameSquares.setClass(MapSquare.class);
        mapSquares = gameSquares;
        player = new Player(mapSquares.get(0, 0));
        //player.inventory.axe = new Axe();
        //player.inventory.meleeWeapon = new IronSword();
        player.move(mapSquares.get(0, 0));
        mapSquares.set(0, 0, new MapSquare(new Grass(), null, player, new FinalPoint(0,0)));
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
        Player.setImage();
        Villager.setImage();
        Skeleton.setImage();
        Backpack.setImage();
        Coal.setImage();
        HealingStone.setImage();
        IronOre.setImage();
        Log.setImage();
        ProtectionRing.setImage();
        RawBeef.setImage();
        Rock.setImage();
        SmallBackpack.setImage();
        IronSword.setImage();
        RustyIronSword.setImage();
        SharpenedStick.setImage();
        Axe.setImage();
        Key.setImage();
        Pickaxe.setImage();
        Blueberries.setImage();
        CarrotItem.setImage();
        PotatoItem.setImage();
        Steak.setImage();
        IronBoots.setImage();
        LeatherBoots.setImage();
        RustyIronBoots.setImage();
        IronChestplate.setImage();
        LeatherChestplate.setImage();
        RustyIronChestplate.setImage();
        IronHelmet.setImage();
        LeatherHelmet.setImage();
        RustyIronHelmet.setImage();
        IronLeggings.setImage();
        LeatherLeggings.setImage();
        RustyIronLeggings.setImage();

        UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Gui.createAndShowGui("MyGame");
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

    /*public void tick() {
        tickActors();
        tickPlants();
    }*/

    public static void tickActors() {
        for (int index = 0; index<actors.size();index++) {
            actors.get(index).tick();
        }
    }


    /*public void tickPlants() {
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

    }*/

    public static void animate(ArrayList<Point> path) {
        // I have no idea how this will work but it WILL work. I will find a way to make this look EPIC! EPIC I say!
    }
    public static void animateArrow() {

    }

    public static void initializeMap() {
        for (int x = 0; x<initialSquares.length;x++) {
            for (int y = 0; y<initialSquares[x].length; y++) {
                int posX = x-(initialSquares.length/2);
                int posY = y-(initialSquares[x].length/2);
                //if (posX<1) {
                    //posX++;
                //}
                //if (posY<1) {
                    //posY++;
                //}
                //if (posX!=0 || posY!=0) {
                    switch (initialSquares[x][y]) {
                        case 0 -> mapSquares.set(posX, posY, new MapSquare(new Grass(), null, null, new FinalPoint(posX, posY)));
                        case 1 -> mapSquares.set(posX, posY, new MapSquare(new Grass(), new OakTree(), null, new FinalPoint(posX, posY)));
                        case 2 -> mapSquares.set(posX, posY, new MapSquare(new Water(), null, null, new FinalPoint(posX, posY)));
                        case 3 -> mapSquares.set(posX, posY, new MapSquare(new Stone(), null, null, new FinalPoint(posX, posY)));
                        case 4 -> mapSquares.set(posX, posY, new MapSquare(new Stone(), new StoneWall(), null, new FinalPoint(posX, posY)));
                        case 5 -> {
                            mapSquares.set(posX, posY, new MapSquare(new Grass(), null, new Villager(), new FinalPoint(posX, posY)));
                            mapSquares.get(posX, posY).occupant.squareOn = mapSquares.get(posX, posY);
                            actors.add(mapSquares.get(posX, posY).occupant);
                        }
                        case 6 -> {
                            mapSquares.set(posX, posY, new MapSquare(new Grass(), null, new Skeleton(), new FinalPoint(posX, posY)));
                            mapSquares.get(posX, posY).occupant.squareOn = mapSquares.get(posX, posY);
                            actors.add(mapSquares.get(posX, posY).occupant);
                        }
                    }
                //}
            }
        }
    }

}
