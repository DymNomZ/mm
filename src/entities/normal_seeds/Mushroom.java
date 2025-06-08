package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Mushroom extends Seed {

    public Mushroom(){
        super();
        this.name = "Mushroom";
        this.cost = 150000;
        this.sprite = Tools.gradientImage(
                sprite,
                Configs.MUSHROOM_1, Configs.MUSHROOM_2
        );
        attemptRandomMutation();
    }

    public Mushroom(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
