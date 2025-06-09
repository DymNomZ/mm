package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Strawberry extends Seed {

    public Strawberry(){
        super(
                "Strawberry", 50, false,
                Configs.SHOCKED_RED_2, Configs.SHOCKED_RED_2,
                Tools.tintImage(SpriteLoader.DEBUG_ITEM, Configs.STRAWBERRY)
        );
    }

    public Strawberry(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
