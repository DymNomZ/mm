package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Beanstalk extends Seed {

    public Beanstalk(){
        super();
        this.name = "Beanstalk";
        this.cost = 10000000;
        this.sprite = Tools.gradientImage(
                sprite,
                Configs.BEANSTALK_1, Configs.BEANSTALK_2
        );
        this.isGradient = true;
        this.shockedPrimary = Configs.SHOCKED_GREEN_1;
        this.shockedSecondary = Configs.SHOCKED_GREEN_2;
    }

    public Beanstalk(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
