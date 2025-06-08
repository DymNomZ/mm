package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Daffodil extends Seed {

    public Daffodil(){
        super();
        this.name = "Daffodil";
        this.cost = 1000;
        this.sprite = Tools.gradientImage(
                sprite,
                Configs.DAFFODIL_1, Configs.DAFFODIL_2
        );
        attemptRandomMutation();
    }

    public Daffodil(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
