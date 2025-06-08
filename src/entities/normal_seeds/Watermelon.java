package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Watermelon extends Seed {

    public Watermelon(){
        super();
        this.name = "Watermelon";
        this.cost = 2500;
        this.sprite = Tools.tintImage(sprite, Configs.WATERMELON);
        attemptRandomMutation();
    }

    public Watermelon(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
