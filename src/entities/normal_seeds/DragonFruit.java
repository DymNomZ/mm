package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class DragonFruit extends Seed {

    public DragonFruit(){
        super();
        this.name = "Dragon Fruit";
        this.cost = 45000;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public DragonFruit(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
