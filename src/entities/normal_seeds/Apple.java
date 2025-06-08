package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Apple extends Seed {

    public Apple(){
        super();
        this.name = "Apple";
        this.cost = 3250;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Apple(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
