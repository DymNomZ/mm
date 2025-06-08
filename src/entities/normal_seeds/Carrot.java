package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Carrot extends Seed {

    public Carrot(){
        super();
        this.name = "Carrot";
        this.cost = 10;
        this.sprite = Tools.tintImage(sprite, Configs.CARROT);
        attemptRandomMutation();
    }

    public Carrot(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}

