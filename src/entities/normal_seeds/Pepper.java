package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Pepper extends Seed {

    public Pepper(){
        super(
                "Pepper", 1000000, true,
                Configs.SHOCKED_RED_1, Configs.SHOCKED_RED_3,
                Tools.gradientImage(
                        SpriteLoader.DEBUG_ITEM,
                        Configs.PEPPER_1, Configs.PEPPER_2
                )
        );
    }

    public Pepper(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
