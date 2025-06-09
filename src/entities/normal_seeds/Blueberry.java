package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Blueberry extends Seed {

    public Blueberry(){
        super(
                "Blueberry", 400, false,
                Configs.SHOCKED_BLUE_1, Configs.SHOCKED_BLUE_1,
                Tools.tintImage(SpriteLoader.DEBUG_ITEM, Configs.BLUEBERRY)
        );
    }

    public Blueberry(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
