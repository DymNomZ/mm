package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Bamboo extends Seed {

    public Bamboo(){
        super();
        this.name = "Bamboo";
        this.cost = 4000;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Bamboo(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
