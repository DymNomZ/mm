package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class DragonFruit extends Seed {

    public DragonFruit(){
        super();
        this.name = "Dragon Fruit";
        this.cost = 45000;
        this.sprite = Tools.gradientImage(
                sprite,
                Configs.DRAGONFRUIT_1, Configs.DRAGONFRUIT_2
        );
        attemptRandomMutation();
    }

    public DragonFruit(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
