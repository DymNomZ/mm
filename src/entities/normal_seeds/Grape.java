package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Grape extends Seed {

    public Grape(){
        super();
        this.name = "Grape";
        this.cost = 850000;
        this.sprite = Tools.tintImage(sprite, Configs.GRAPE);
        this.isGradient = false;
        this.shockedPrimary = Configs.SHOCKED_PURPLE_1;
        this.shockedSecondary = Configs.SHOCKED_PURPLE_1;
        attemptRandomMutation();
    }

    public Grape(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
