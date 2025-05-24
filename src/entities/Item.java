package entities;

import core.Configs;
import core.Game;
import core.SpriteLoader;
import core.Tools;

import java.awt.*;

public class Item extends Entity {

    public Item(){
        this.x = Tools.getRandomX();
        this.y = Tools.getRandomY();
        this.sprite = SpriteLoader.DEBUG_ITEM;
    }

    public void render(Graphics2D g2){

        int ts = Configs.TILE_SIZE;
        int px = Game.player.x;
        int py = Game.player.y;
        int psx = Game.player.sx;
        int psy = Game.player.sy;

        sx = x - px + psx;
        sy = y - py + psy;

        if(
                x + ts > px - psx && x - ts < px + psx &&
                y + ts > py - psy && y - ts < py + psy
        ){
            g2.drawImage(
                    sprite, sx, sy,
                    Configs.TILE_SIZE, Configs.TILE_SIZE,
                    null
            );
        }
    }
}
