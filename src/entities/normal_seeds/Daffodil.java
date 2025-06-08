package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Daffodil extends Seed {

    public Daffodil(){
        super();
        this.name = "Daffodil";
        this.cost = 1000;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Daffodil(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
