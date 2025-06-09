package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Cacao extends Seed {

    public Cacao(){
        super();
        this.name = "Cacao";
        this.cost = 2500000;
        this.sprite = Tools.gradientImage(
                sprite,
                Configs.CACAO_1, Configs.CACAO_2
        );
        this.isGradient = true;
        this.shockedPrimary = Configs.BROWN;
        this.shockedSecondary = Configs.SHOCKED_BROWN_1;
    }

    public Cacao(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
