package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Pepper extends Seed {

    public Pepper(){
        super();
        this.name = "Pepper";
        this.cost = 1000000;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Pepper(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
