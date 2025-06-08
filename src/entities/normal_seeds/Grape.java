package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Grape extends Seed {

    public Grape(){
        super();
        this.name = "Grape";
        this.cost = 850000;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Grape(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
