package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Cacao extends Seed {

    public Cacao(){
        super(
                "Cacao", 2500000, true,
                Configs.BROWN, Configs.SHOCKED_BROWN_1,
                Tools.gradientImage(
                        SpriteLoader.DEBUG_ITEM,
                        Configs.CACAO_1, Configs.CACAO_2
                )
        );
    }

    public Cacao(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
