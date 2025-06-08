package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Tomato extends Seed {

    public Tomato(){
        super();
        this.name = "Tomato";
        this.cost = 800;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Tomato(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
