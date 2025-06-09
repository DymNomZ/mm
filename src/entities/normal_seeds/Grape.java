package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Grape extends Seed {

    public Grape(){
        super(
                "Grape", 850000, false,
                Configs.SHOCKED_PURPLE_1, Configs.SHOCKED_PURPLE_1,
                Tools.tintImage(SpriteLoader.DEBUG_ITEM, Configs.GRAPE)
        );
    }

    public Grape(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
