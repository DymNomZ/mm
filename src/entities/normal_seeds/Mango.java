package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Mango extends Seed {

    public Mango(){
        super();
        this.name = "Mango";
        this.cost = 100000;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Mango(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
