package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Mango extends Seed {

    public Mango(){
        super(
                "Mango", 100000, false,
                Configs.SHOCKED_YELLOW_2, Configs.SHOCKED_YELLOW_2,
                Tools.tintImage(SpriteLoader.DEBUG_ITEM, Configs.MANGO)
        );
    }

    public Mango(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
