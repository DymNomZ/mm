package entities.normal_seeds;

import core.Configs;
import core.Tools;
import entities.Seed;

public class Carrot extends Seed {

    public Carrot(){
        super();
        this.name = "Carrot";
        this.cost = 10;
        this.sprite = Tools.tintImage(sprite, Configs.CARROT);
        this.isGradient = false;
        this.shockedPrimary = Configs.SHOCKED_ORANGE_1;
        this.shockedSecondary = Configs.SHOCKED_ORANGE_1;
    }

    public Carrot(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}

