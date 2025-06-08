package entities;

import core.SpriteLoader;

public class Carrot extends Seed{

    public Carrot(){
        super();
        this.name = "Carrot";
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public Carrot(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
