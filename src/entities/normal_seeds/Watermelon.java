package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Watermelon extends Seed {

    public Watermelon(){
        super();
        this.name = "Watermelon";
        this.cost = 2500;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Watermelon(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
