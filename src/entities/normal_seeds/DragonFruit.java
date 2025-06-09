package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class DragonFruit extends Seed {

    public DragonFruit(){
        super(
                "Dragon Fruit", 45000, true,
                Configs.SHOCKED_PINK_1, Configs.SHOCKED_GREEN_1,
                Tools.gradientImage(
                        SpriteLoader.DEBUG_ITEM,
                        Configs.DRAGONFRUIT_1, Configs.DRAGONFRUIT_2
                )
        );
    }

    public DragonFruit(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
