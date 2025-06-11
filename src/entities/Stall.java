package entities;

import core.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Stall extends Entity{

    private KeyHandler keyHandler;

    private int stSize = Configs.STALL_SIZE;
    private int stOff = Configs.STALL_OFFSET;

    private boolean playerIsNear = false;
    private boolean playerIsCommunicating = false;

    public String name;

    public Stall(
            BufferedImage sprite,
            int x, int y, String name
    ){
        this.keyHandler = Game.keyHandler;
        this.sprite = sprite;
        this.ewidth = stSize;
        this.eheight = stSize;
        this.x = x * stSize;
        this.y = y * stSize;
        this.name = name;
    }

    protected abstract void stallInteractSequence(Graphics2D g2);

    public void handleChecks(){

        if(Tools.isTouch(this)){

            playerIsNear = true;

            if(keyHandler.ePressed){
                playerIsCommunicating = true;
            }
            else{

            }
        }
        else{
            playerIsNear = false;
            playerIsCommunicating = false;
        }
    }

    public void render(Graphics2D g2){

        int px = (int) Game.player.x;
        int py = (int) Game.player.y;
        int psx = Game.player.sx;
        int psy = Game.player.sy;

        sx = (int) (x - px + psx);
        sy = (int) (y - py + psy);

        //I could use these to render it centered to its world pos but ehhh we'll see
//        sx -= sOff;
//        sy -= sOff;

        g2.drawImage(sprite, sx, sy, stSize, stSize, null);

        if(playerIsNear){
            if(playerIsCommunicating){
                stallInteractSequence(g2);
            }
            else{
                Tools.renderWordBox(
                        g2, this,
                        "Talk to " + name + " (Press E)"
                );
            }
        }

        //debug render bounding box
        g2.setColor(Color.ORANGE);
        g2.drawRect(sx, sy, stSize, stSize);

    }

    public static class Shop extends Stall{

        public Shop(int x, int y){
            super(
                SpriteLoader.STALL_SHOP,
                x, y, "Usagi"
            );
        }

        public void stallInteractSequence(Graphics2D g2){

            Tools.renderWordBox(
                    g2, this, Dialogues.USAGI
            );

            Tools.renderMultilineBox(g2, this, Dialogues.SELL_DIALOGUES);

        }
    }

    public static class Sell extends Stall{

        public Sell(int x, int y){
            super(
                    SpriteLoader.STALL_SELL,
                    x, y, "Hachiware"
            );
        }

        public void stallInteractSequence(Graphics2D g2){

            Tools.renderWordBox(
                    g2, this, Dialogues.HACHIWARE
            );

            Tools.renderMultilineBox(g2, this, Dialogues.SELL_DIALOGUES);

        }
    }

}
