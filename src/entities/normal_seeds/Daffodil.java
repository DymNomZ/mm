package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Daffodil extends Seed {

    public Daffodil(){
        super(
                "Daffodil", 1000, true,
                Configs.SHOCKED_YELLOW_1, Configs.SHOCKED_GREEN_2,
                Tools.gradientImage(
                        SpriteLoader.DEBUG_ITEM,
                        Configs.DAFFODIL_1, Configs.DAFFODIL_2
                )
        );
    }

    public Daffodil(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
