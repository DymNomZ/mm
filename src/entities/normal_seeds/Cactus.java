package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Cactus extends Seed {

    public Cactus(){
        super();
        this.name = "Cactus";
        this.cost = 15000;
        this.sprite = Tools.tintImage(sprite, Configs.CACTUS);
        this.isGradient = false;
        this.shockedPrimary = Configs.SHOCKED_GREEN_2;
        this.shockedSecondary = Configs.SHOCKED_GREEN_2;
    }

    public Cactus(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
