package entities.normal_seeds;

import core.Configs;
import core.SpriteLoader;
import core.Tools;
import entities.Seed;

public class OrangeTulip extends Seed {

    public OrangeTulip(){
        super();
        this.name = "Orange Tulip";
        this.cost = 600;
        this.sprite = Tools.tintImage(sprite, Configs.ORANGETULIP);
        this.isGradient = false;
        this.shockedPrimary = Configs.SHOCKED_ORANGE_1;
        this.shockedSecondary = Configs.SHOCKED_ORANGE_1;
        attemptRandomMutation();
    }

    public OrangeTulip(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }
}
