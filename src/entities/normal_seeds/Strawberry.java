package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Strawberry extends Seed {

    public Strawberry(){
        super();
        this.name = "Strawberry";
        this.cost = 50;
        this.sprite = Tools.tintImage(sprite, Configs.STRAWBERRY);
        attemptRandomMutation();
    }

    public Strawberry(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
