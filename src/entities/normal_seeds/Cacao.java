package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Cacao extends Seed {

    public Cacao(){
        super();
        this.name = "Cacao";
        this.cost = 2500000;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Cacao(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
