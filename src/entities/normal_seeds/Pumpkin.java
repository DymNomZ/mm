package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Pumpkin extends Seed {

    public Pumpkin(){
        super();
        this.name = "Pumpkin";
        this.cost = 3000;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Pumpkin(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
