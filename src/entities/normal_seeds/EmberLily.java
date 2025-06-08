package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class EmberLily extends Seed {

    public EmberLily(){
        super();
        this.name = "EmberLily";
        this.cost = 15000000;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public EmberLily(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
