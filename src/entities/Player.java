package entities;

import core.Configs;
import core.KeyHandler;
import core.SpriteLoader;

import java.awt.*;

public class Player extends Entity{

    private KeyHandler keyHandler;

    public Player(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
        this.sprite = SpriteLoader.PLAYER;
        this.sx = Configs.CENTER_X;
        this.sy = Configs.CENTER_Y;
        reset();
    }

    public void reset(){
        x = 100;
        y = 100;
        speed = 8;
    }

    public void move(){

        if(keyHandler.wPressed == keyHandler.sPressed) y += 0;
        else if(keyHandler.wPressed) y -= speed;
        else if(keyHandler.sPressed) y += speed;

        if(keyHandler.aPressed == keyHandler.dPressed) x += 0;
        else if(keyHandler.aPressed) x -= speed;
        else if(keyHandler.dPressed) x += speed;

        //show coordinates here


    }

    public void render(Graphics2D g2){

        g2.drawImage(sprite, sx, sy, Configs.TILE_SIZE, Configs.TILE_SIZE, null);
    }
}
