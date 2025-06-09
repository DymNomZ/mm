package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Carrot extends Seed {

    public Carrot(){
        super(
                "Carrot", 10, false,
                Configs.SHOCKED_ORANGE_1, Configs.SHOCKED_ORANGE_1,
                Tools.tintImage(SpriteLoader.DEBUG_ITEM, Configs.CARROT)
        );
    }

    public Carrot(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}

