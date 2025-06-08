package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Corn extends Seed {

    public Corn(){
        super();
        this.name = "Corn";
        this.cost = 1400;
        this.sprite = Tools.tintImage(sprite, Configs.CORN);
        this.isGradient = false;
        this.shockedPrimary = Configs.SHOCKED_YELLOW_1;
        this.shockedSecondary = Configs.SHOCKED_YELLOW_1;
        attemptRandomMutation();
    }

    public Corn(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
