package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class EmberLily extends Seed {

    public EmberLily(){
        super(
                "Ember Lily", 15000000, true,
                Configs.SHOCKED_ORANGE_2, Configs.SHOCKED_CYAN_1,
                Tools.gradientImage(
                        SpriteLoader.DEBUG_ITEM,
                        Configs.EMBERLILY_1, Configs.EMBERLILY_2
                )
        );
    }

    public EmberLily(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
