package entities;

import core.*;

import java.awt.*;

public class Item extends Entity {

    private KeyHandler keyHandler;
    private boolean hide;

    private int ts;

    public Item(){
        this.keyHandler = Game.keyHandler;
        this.x = Tools.getRandomX();
        this.y = Tools.getRandomY();
        this.sprite = SpriteLoader.DEBUG_ITEM;
        this.hide = false;
        //to be changed by actual player dimensions and item size
        this.ts = Configs.TILE_SIZE;
    }

    public boolean isTouch(
            int il, int ir, int it, int ib,
            int pl, int pr, int pt, int pb
    ){

        return !(pl >= ir || pr <= il || pt >= ib || pb <= it);
    }

    private void debugRelocate(){
        x = Tools.getRandomX();
        y = Tools.getRandomY();
    }

    public void debugTouch(Player p){

        int il = this.x;
        int ir = this.x + ts;
        int it = this.y;
        int ib = this.y + ts;

        int pl = p.x;
        int pr = p.x + ts;
        int pt = p.y;
        int pb = p.y + ts;

        if(isTouch(il, ir, it, ib, pl, pr, pt, pb)){
            debugRelocate();
        }
    }

    public void debugTouch2(Player p){

        int il = this.x;
        int ir = this.x + ts;
        int it = this.y;
        int ib = this.y + ts;

        int pl = p.reach.x;
        int pr = pl + p.reach.width;
        int pt = p.reach.y;
        int pb = pt + p.reach.height;

        if(
            keyHandler.fPressed &&
            isTouch(il, ir, it, ib, pl, pr, pt, pb)
        ){
            hide = true;
        }
    }

    public void render(Graphics2D g2){

        if(hide) return;

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
