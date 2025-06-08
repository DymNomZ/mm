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
        this.isGradient = false;
        this.shockedPrimary = Configs.SHOCKED_RED_2;
        this.shockedSecondary = Configs.SHOCKED_RED_2;
        attemptRandomMutation();
    }

    public Tomato(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
