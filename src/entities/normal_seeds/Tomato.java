package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Tomato extends Seed {

    public Tomato(){
        super();
        this.name = "Tomato";
        this.cost = 800;
        this.sprite = Tools.tintImage(sprite, Configs.TOMATO);
        attemptRandomMutation();
    }

    public Tomato(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
