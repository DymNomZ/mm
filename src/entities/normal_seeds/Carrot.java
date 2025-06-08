package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Carrot extends Seed {

    public Carrot(){
        super();
        this.name = "Carrot";
        this.cost = 10;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Carrot(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}

