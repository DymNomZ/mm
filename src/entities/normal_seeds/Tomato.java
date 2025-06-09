package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class Tomato extends Seed {

    public Tomato(){
        super(
                "Tomato", 800, false,
                Configs.SHOCKED_RED_2, Configs.SHOCKED_RED_2,
                Tools.tintImage(SpriteLoader.DEBUG_ITEM, Configs.TOMATO)
        );
    }

    public Tomato(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
