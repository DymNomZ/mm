package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Corn extends Seed {

    public Corn(){
        super(
                "Corn", 1400, false,
                Configs.SHOCKED_YELLOW_1, Configs.SHOCKED_YELLOW_1,
                Tools.tintImage(SpriteLoader.DEBUG_ITEM, Configs.CORN)
        );
    }

    public Corn(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
