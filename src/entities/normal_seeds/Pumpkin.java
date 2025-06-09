package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Pumpkin extends Seed {

    public Pumpkin(){
        super(
                "Pumpkin", 3000, false,
                Configs.SHOCKED_ORANGE_2, Configs.SHOCKED_ORANGE_2,
                Tools.tintImage(SpriteLoader.DEBUG_ITEM, Configs.PUMPKIN)
        );
    }

    public Pumpkin(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
