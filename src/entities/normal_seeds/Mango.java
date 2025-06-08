package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Mango extends Seed {

    public Mango(){
        super();
        this.name = "Mango";
        this.cost = 100000;
        this.sprite = Tools.tintImage(sprite, Configs.MANGO);
        attemptRandomMutation();
    }

    public Mango(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
