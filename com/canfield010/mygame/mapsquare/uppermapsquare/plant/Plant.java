package com.canfield010.mygame.mapsquare.uppermapsquare.plant;

import com.canfield010.mygame.Main;
import com.canfield010.mygame.mapsquares.uppermapsquare.UpperMapSquare;

import java.io.File;

public class Plant extends UpperMapSquare {

    public byte growthStage;

    public File imageFile = new File("resources/"+name+growthStage+".png");

    //public final boolean requiresFarmland;

    private final int maxGrowthStage;

    public static boolean REQUIRES_FARMLAND;

    public Plant(String name, boolean requiresFarmland, int maxGrowthStage) {
        super(name);
        this.growthStage = 0;
        //this.requiresFarmland = requiresFarmland;
        this.maxGrowthStage = maxGrowthStage;
        REQUIRES_FARMLAND = requiresFarmland;
    }

    public void grow() {
        if (growthStage<maxGrowthStage) {
            growthStage++;
            updateImage();
        }
    }

    public void updateImage() {
        imageFile = new File("resources/"+name+growthStage+".png");
    }

}
