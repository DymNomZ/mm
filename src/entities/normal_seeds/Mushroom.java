package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Mushroom extends Seed {

    public Mushroom(){
        super();
        this.name = "Mushroom";
        this.cost = 150000;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Mushroom(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
