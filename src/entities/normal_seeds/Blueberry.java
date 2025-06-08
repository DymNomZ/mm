package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Blueberry extends Seed {

    public Blueberry(){
        super();
        this.name = "Blueberry";
        this.cost = 400;
        this.sprite = Tools.tintImage(sprite, Configs.BLUEBERRY);
        this.isGradient = false;
        this.shockedPrimary = Configs.SHOCKED_BLUE_1;
        this.shockedSecondary = Configs.SHOCKED_BLUE_1;
        attemptRandomMutation();
    }

    public Blueberry(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
