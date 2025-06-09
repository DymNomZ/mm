package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Mango extends Seed {

    public Mango(){
        super();
        this.name = "Mango";
        this.cost = 100000;
        this.sprite = Tools.tintImage(sprite, Configs.MANGO);
        this.isGradient = false;
        this.shockedPrimary = Configs.SHOCKED_YELLOW_2;
        this.shockedSecondary = Configs.SHOCKED_YELLOW_2;
    }

    public Mango(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
