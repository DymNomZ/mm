package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Apple extends Seed {

    public Apple(){
        super();
        this.name = "Apple";
        this.cost = 3250;
        this.sprite = Tools.gradientImage(
                sprite,
                Configs.APPLE_1, Configs.APPLE_2
        );
        this.isGradient = true;
        this.shockedPrimary = Configs.SHOCKED_RED_1;
        this.shockedSecondary = Configs.SHOCKED_RED_1;
    }

    public Apple(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
