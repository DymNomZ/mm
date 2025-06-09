package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class OrangeTulip extends Seed {

    public OrangeTulip(){
        super(
                "Orange Tulip", 600, false,
                Configs.SHOCKED_ORANGE_1, Configs.SHOCKED_ORANGE_1,
                Tools.tintImage(SpriteLoader.DEBUG_ITEM, Configs.ORANGETULIP)
        );
    }

    public OrangeTulip(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
