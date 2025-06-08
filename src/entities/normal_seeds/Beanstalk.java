package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Beanstalk extends Seed {

    public Beanstalk(){
        super();
        this.name = "Beanstalk";
        this.cost = 10000000;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Beanstalk(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
