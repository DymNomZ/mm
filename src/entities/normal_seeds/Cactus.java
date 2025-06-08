package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Cactus extends Seed {

    public Cactus(){
        super();
        this.name = "Cactus";
        this.cost = 15000;
        this.sprite = Tools.tintImage(sprite, Configs.CACTUS);
        attemptRandomMutation();
    }

    public Cactus(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
