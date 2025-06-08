package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Coconut extends Seed {

    public Coconut(){
        super();
        this.name = "Coconut";
        this.cost = 6000;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Coconut(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
