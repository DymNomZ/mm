package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Beanstalk extends Seed {

    public Beanstalk(){
        super(
                "Beanstalk", 10000000, true,
                Configs.SHOCKED_GREEN_1, Configs.SHOCKED_GREEN_2,
                Tools.gradientImage(
                        SpriteLoader.DEBUG_ITEM,
                        Configs.BEANSTALK_1, Configs.BEANSTALK_2
                )
        );
    }

    public Beanstalk(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
