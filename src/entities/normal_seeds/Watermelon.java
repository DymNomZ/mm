package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Watermelon extends Seed {

    public Watermelon(){
        super(
                "Watermelon", 2500, false,
                Configs.SHOCKED_GREEN_2, Configs.SHOCKED_GREEN_2,
                Tools.tintImage(SpriteLoader.DEBUG_ITEM, Configs.WATERMELON)
        );
    }

    public Watermelon(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
