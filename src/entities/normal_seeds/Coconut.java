package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Coconut extends Seed {

    public Coconut(){
        super();
        this.name = "Coconut";
        this.cost = 6000;
        this.sprite = Tools.tintImage(sprite, Configs.COCONUT);
        this.isGradient = false;
        this.shockedPrimary = Configs.SHOCKED_WHITE_1;
        this.shockedSecondary = Configs.SHOCKED_WHITE_1;
    }

    public Coconut(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
