package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Blueberry extends Seed {

    public Blueberry(){
        super();
        this.name = "Blueberry";
        this.cost = 400;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Blueberry(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
