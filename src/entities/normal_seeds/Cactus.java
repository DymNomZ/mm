package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Cactus extends Seed {

    public Cactus(){
        super();
        this.name = "Cactus";
        this.cost = 15000;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Cactus(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
