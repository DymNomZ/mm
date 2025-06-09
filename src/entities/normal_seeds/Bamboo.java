package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Bamboo extends Seed {

    public Bamboo(){
        super();
        this.name = "Bamboo";
        this.cost = 4000;
        this.sprite = Tools.tintImage(sprite, Configs.BAMBOO);
        this.isGradient = false;
        this.shockedPrimary = Configs.SHOCKED_GREEN_1;
        this.shockedSecondary = Configs.SHOCKED_GREEN_1;
    }

    public Bamboo(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
