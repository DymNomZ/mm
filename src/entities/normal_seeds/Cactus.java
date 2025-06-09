package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Cactus extends Seed {

    public Cactus(){
        super(
                "Cactus", 15000, false,
                Configs.SHOCKED_GREEN_2, Configs.SHOCKED_GREEN_2,
                Tools.tintImage(SpriteLoader.DEBUG_ITEM, Configs.CACTUS)
        );
    }

    public Cactus(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
