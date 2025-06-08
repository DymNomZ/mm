package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class Strawberry extends Seed {

    public Strawberry(){
        super();
        this.name = "Strawberry";
        this.cost = 50;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Strawberry(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
