package com.canfield010.mygame.actors;

import com.canfield010.mygame.item.Item;
import com.canfield010.mygame.item.armor.Armor;
import com.canfield010.mygame.item.weapon.Weapon;

public class Inventory {
    public Weapon meleeWeapon = null;
    public Weapon rangedWeapon = null;
    public Item axe = null;
    public Item pickaxe = null;

    //public Item helmet = null;
    //public Item chestplate = null;
    //public Item leggins = null;
    //public Item boots = null;

    // or...

    public Armor[] armor = new Armor[4];
    public Item specialItem = null;

    public Item[] storage = new Item[12];
    public Item backpack = null;
    public int coins = 100;

}
