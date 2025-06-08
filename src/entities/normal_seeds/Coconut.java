package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Coconut extends Seed {

    public Coconut(){
        super();
        this.name = "Coconut";
        this.cost = 6000;
        this.sprite = Tools.tintImage(sprite, Configs.COCONUT);
        attemptRandomMutation();
    }

    public Coconut(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
