package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class EmberLily extends Seed {

    public EmberLily(){
        super();
        this.name = "EmberLily";
        this.cost = 15000000;
        this.sprite = Tools.gradientImage(
                sprite,
                Configs.EMBERLILY_1, Configs.EMBERLILY_2
        );
        this.isGradient = true;
        this.shockedPrimary = Configs.SHOCKED_ORANGE_2;
        this.shockedSecondary = Configs.SHOCKED_CYAN_1;
        attemptRandomMutation();
    }

    public EmberLily(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
