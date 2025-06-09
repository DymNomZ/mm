package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Coconut extends Seed {

    public Coconut(){
        super(
                "Coconut", 6000, false,
                Configs.SHOCKED_WHITE_1, Configs.SHOCKED_WHITE_1,
                Tools.tintImage(SpriteLoader.DEBUG_ITEM, Configs.COCONUT)
        );
    }

    public Coconut(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
