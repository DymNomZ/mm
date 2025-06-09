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
        this.isGradient = true;
        this.shockedPrimary = Configs.SHOCKED_YELLOW_1;
        this.shockedSecondary = Configs.SHOCKED_GREEN_2;
    }

    public Daffodil(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
