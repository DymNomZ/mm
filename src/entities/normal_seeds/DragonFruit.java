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
        this.isGradient = true;
        this.shockedPrimary = Configs.SHOCKED_PINK_1;
        this.shockedSecondary = Configs.SHOCKED_GREEN_1;
        attemptRandomMutation();
    }

    public DragonFruit(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
