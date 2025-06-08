package entities;

import core.*;

import java.awt.*;

public class Item extends Entity {

    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    public boolean hide;

    public int ts = Configs.TILE_SIZE;
    public int h = Configs.HALF;
    public int q = Configs.QUARTER;
    public int et = Configs.EIGHTH;

    public Item(){
        this.keyHandler = Game.keyHandler;
        this.mouseHandler = Game.mouseHandler;
        this.hide = false;
    }

    public void debugDrop(int x, int y) {
        hide = false;
        this.x = x;
        this.y = y;
    }

    public boolean isTouch(
            int il, int ir, int it, int ib,
            int pl, int pr, int pt, int pb
    ){

        return !(pl >= ir || pr <= il || pt >= ib || pb <= it);
    }

    public void itemTouch(Player p){

        int il = (int)x;
        int ir = (int)(x + ewidth);
        int it = (int)y;
        int ib = (int)(y + eheight);

        int pl = p.reach.x;
        int pr = p.reach.x + p.reach.width;
        int pt = p.reach.y;
        int pb = p.reach.y + p.reach.height;

        if(
            keyHandler.ePressed &&
            isTouch(il, ir, it, ib, pl, pr, pt, pb)
        ){
            hide = true;
        }
    }

    public void render(Graphics2D g2) {

        if (hide) return;

        int px = (int) Game.player.x;
        int py = (int) Game.player.y;
        int psx = Game.player.sx;
        int psy = Game.player.sy;

        this.sx = (int) (x - px + psx);
        this.sy = (int) (y - py + psy);

        boolean isHorizontallyVisible = (x + ts > px - psx) && (x < px - psx + Configs.SCREEN_WIDTH);
        boolean isVerticallyVisible = (y + ts > py - psy) && (y < py - psy + Configs.SCREEN_HEIGHT);

        if (isHorizontallyVisible && isVerticallyVisible) {
            g2.drawImage(sprite, sx, sy, ewidth, eheight, null);

            //Debug draw item's bounding box on screen
            g2.setColor(Color.ORANGE);
            g2.drawRect(sx, sy, ewidth, eheight);
        }
    }
}
