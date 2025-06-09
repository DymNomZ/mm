package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Apple extends Seed {

    public Apple(){
        super(
            "Apple", 3250, true,
            Configs.SHOCKED_RED_1, Configs.SHOCKED_GREEN_1,
            Tools.gradientImage(
                    SpriteLoader.DEBUG_ITEM,
                    Configs.APPLE_1, Configs.APPLE_2
            )
        );
    }

    public Apple(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
