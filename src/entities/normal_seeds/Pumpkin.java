package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Pumpkin extends Seed {

    public Pumpkin(){
        super();
        this.name = "Pumpkin";
        this.cost = 3000;
        this.sprite = Tools.tintImage(sprite, Configs.PUMPKIN);
        attemptRandomMutation();
    }

    public Pumpkin(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
