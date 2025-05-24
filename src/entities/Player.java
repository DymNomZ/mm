package entities;

import core.*;

import java.awt.*;

public class Player extends Entity{

    private KeyHandler keyHandler;
    private MapConstructor map;

    private Rectangle hitbox;
    public Rectangle reach;
    private int hitboxOffsetX;
    private int hitboxOffsetY;

    private int ts = Configs.TILE_SIZE;
    private int h = Configs.HALF;
    private int q = Configs.QUARTER;

    private int direction; //1 up 2 left 3 down 4 right

    public Player() {
        this.keyHandler = Game.keyHandler;
        this.map = Game.mapConstructor;
        this.hitbox = new Rectangle(0, 0, h, h);
        this.hitboxOffsetX = q;
        this.hitboxOffsetY = q;
        this.reach = new Rectangle(0, 0, h, h);
        this.sprite = SpriteLoader.PLAYER;
        this.sx = Configs.CENTER_X;
        this.sy = Configs.CENTER_Y;
        reset();
    }

    public void reset(){
        x = 100;
        y = 100;
        speed = 8;
        direction = 3;
        orientReach();
    }

    private void orientReach(){

        switch(direction){
            case 1 -> {
                reach.x = x;
                reach.width = ts;
                reach.y = y - h;
                reach.height = h;
            }
            case 2 -> {
                reach.x = x - h;
                reach.width = h;
                reach.y = y;
                reach.height = ts;
            }
            case 3 -> {
                reach.x = x;
                reach.width = ts;
                reach.y = y + ts;
                reach.height = h;
            }
            case 4 -> {
                reach.x = x + ts;
                reach.width = h;
                reach.y = y;
                reach.height = ts;
            }
        }
    }

    public void handleInteractions(){

        if(!keyHandler.fPressed) return;

        if(map.isColliding(reach)){
            //room for creativity!
        }
    }

    public void move() {
        int deltaX = 0;
        int deltaY = 0;

        if (keyHandler.wPressed && !keyHandler.sPressed) {
            deltaY = -speed;
            direction = 1;
        } else if (keyHandler.sPressed && !keyHandler.wPressed) {
            deltaY = speed;
            direction = 3;
        }

        if (keyHandler.aPressed && !keyHandler.dPressed) {
            deltaX = -speed;
            direction = 2;
        } else if (keyHandler.dPressed && !keyHandler.aPressed) {
            deltaX = speed;
            direction = 4;
        }

        if (deltaX != 0 || deltaY != 0) {

            //trigger switching of player's reach
            orientReach();

            // Update hitbox's world position BEFORE checking collision
            // This reflects where the hitbox WILL BE if movement is allowed

            // Check horizontal collision
            if (deltaX != 0) {
                hitbox.x = x + hitboxOffsetX + deltaX; // Prospective X
                hitbox.y = y + hitboxOffsetY;          // Current Y for horizontal check
                if (!map.isColliding(hitbox)) {
                    x += deltaX; // Move if no collision
                }
            }

            // Check vertical collision
            if (deltaY != 0) {
                hitbox.x = x + hitboxOffsetX;          // Current X (or updated X if horizontal move was allowed)
                hitbox.y = y + hitboxOffsetY + deltaY; // Prospective Y
                if (!map.isColliding(hitbox)) {
                    y += deltaY; // Move if no collision
                }
            }
        }
        // Final update of hitbox to current player world position (for rendering or other checks)
        hitbox.x = x + hitboxOffsetX;
        hitbox.y = y + hitboxOffsetY;

        // System.out.println("Player World X: " + x + ", World Y: " + y);
        // System.out.println("Hitbox World X: " + hitbox.x + ", World Y: " + hitbox.y);
    }

    public void render(Graphics2D g2){

        int rsx = reach.x - x + sx;
        int rsy = reach.y - y + sy;

        //debug draw reach
        g2.drawImage(SpriteLoader.REACH, rsx, rsy, ts, ts, null);

        g2.drawImage(sprite, sx, sy, ts, ts, null);
    }
}
