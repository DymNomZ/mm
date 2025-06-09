package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Pepper extends Seed {

    public Pepper(){
        super();
        this.name = "Pepper";
        this.cost = 1000000;
        this.sprite = Tools.gradientImage(
                sprite,
                Configs.PEPPER_1, Configs.PEPPER_2
        );
        this.isGradient = true;
        this.shockedPrimary = Configs.SHOCKED_RED_1;
        this.shockedSecondary = Configs.SHOCKED_RED_3;
    }

    public Pepper(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
