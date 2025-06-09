package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Mushroom extends Seed {

    public Mushroom(){
        super(
                "Mushroom", 150000, true,
                Configs.SHOCKED_RED_1, Configs.SHOCKED_WHITE_1,
                Tools.gradientImage(
                        SpriteLoader.DEBUG_ITEM,
                        Configs.MUSHROOM_1, Configs.MUSHROOM_2
                )
        );
    }

    public Mushroom(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
