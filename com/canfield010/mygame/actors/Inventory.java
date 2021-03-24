package com.canfield010.mygame.actors;

import com.canfield010.mygame.item.Item;

public class Inventory {
    public Item meleeWeapon = null;
    public Item rangedWeapon = null;
    public Item axe = null;
    public Item pickaxe = null;

    public Item helmet = null;
    public Item chestplate = null;
    public Item leggins = null;
    public Item boots = null;

    // or...

    public Item[] armor = new Item[4];

    public Item[] inventory = new Item[12];
    public Item backpack = null;

}
