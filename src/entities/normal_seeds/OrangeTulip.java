package entities.normal_seeds;

import core.SpriteLoader;
import entities.Seed;

public class OrangeTulip extends Seed {

    public OrangeTulip(){
        super();
        this.name = "Orange Tulip";
        this.cost = 600;
        this.sprite = SpriteLoader.DEBUG_ITEM;
        attemptRandomMutation();
    }

    public OrangeTulip(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
