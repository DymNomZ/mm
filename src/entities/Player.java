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
    private int d = Configs.DOUBLE;
    private int pw = ts;
    private int ph = d;

    private int direction; //1 up 2 left 3 down 4 right

    public Player() {
        this.keyHandler = Game.keyHandler;
        this.map = Game.mapConstructor;
        this.hitbox = new Rectangle(0, 0, h, h);
        this.hitboxOffsetX = q;
        this.hitboxOffsetY = q;
        this.reach = new Rectangle(0, 0, h, h);
        this.sx = Configs.CENTER_X;
        this.sy = Configs.CENTER_Y;
        reset();
    }

    public void reset(){
        x = 100;
        y = 100;
        speed = 8;
        direction = 3;
        updateSprite();
        orientReach();
    }

    private void orientReach(){

        //might extend it a little bit inside the body, but this is alright for now
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

    private void updateSprite(){

        switch(direction){
            case 1 -> {
                this.sprite = SpriteLoader.PLAYER_UP_IDLE_FRAMES[0];
            }
            case 2 -> {
                this.sprite = SpriteLoader.PLAYER_LEFT_IDLE_FRAMES[0];
            }
            case 3 -> {
                this.sprite = SpriteLoader.PLAYER_DOWN_IDLE_FRAMES[0];
            }
            case 4 -> {
                this.sprite = SpriteLoader.PLAYER_RIGHT_IDLE_FRAMES[0];
            }
        }
    }

    public void handleInteractions(){

        if(!keyHandler.fPressed) return;

        if(map.isColliding(reach)){
            //room for creativity!
        }
    }

    public void dropItem(){

        if(!keyHandler.qPressed) return;

        //to be changes to actual drop implementation
        Game.item.debugDrop(x, y);
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
            if (deltaY == 0) direction = 2;
        } else if (keyHandler.dPressed && !keyHandler.aPressed) {
            deltaX = speed;
            if (deltaY == 0) direction = 4;
        }

        updateSprite();

        if (deltaX != 0 || deltaY != 0) {

            //trigger switching of player's reach
            orientReach();

            // Update hitbox's world position BEFORE checking collision
            // This reflects where the hitbox WILL BE if movement is allowed

            // Check horizontal collision
            if (deltaX != 0) {
                int prospectivePlayerX = x + deltaX; // Player's potential new top-left X

                // Calculate prospective hitbox world X
                int prospectiveHitboxLeft = prospectivePlayerX + hitboxOffsetX;
                int prospectiveHitboxRight = prospectivePlayerX + hitboxOffsetX + hitbox.width;

                // 1. Check Map Boundaries for X
                if (prospectiveHitboxLeft >= 0 && prospectiveHitboxRight <= map.mapWidth) {
                    // Within map X bounds, now check for tile collision
                    hitbox.x = prospectivePlayerX + hitboxOffsetX; // Update hitbox for tile check
                    hitbox.y = y + hitboxOffsetY;                  // Current Y for this horizontal check
                    if (!map.isColliding(hitbox)) {
                        x = prospectivePlayerX; // Move if no tile collision
                    }
                } else {
                    // Trying to move out of X bounds. Optionally, clamp to edge.
                    if (deltaX < 0 && prospectiveHitboxLeft < 0) { // Moving left out of bounds
                        x = -hitboxOffsetX; // Clamp player so hitbox.x is 0
                    } else if (deltaX > 0 && prospectiveHitboxRight > map.mapWidth) { // Moving right out of bounds
                        x = map.mapWidth - hitbox.width - hitboxOffsetX; // Clamp player so hitbox.x + hitbox.width is mapWidth
                    }
                }
            }

            // Check vertical collision
            if (deltaY != 0) {
                int prospectivePlayerY = y + deltaY; // Player's potential new top-left Y

                // Calculate prospective hitbox world Y
                int prospectiveHitboxTop = prospectivePlayerY + hitboxOffsetY;
                int prospectiveHitboxBottom = prospectivePlayerY + hitboxOffsetY + hitbox.height;

                // 1. Check Map Boundaries for Y
                if (prospectiveHitboxTop >= 0 && prospectiveHitboxBottom <= map.mapHeight) {
                    // Within map Y bounds, now check for tile collision
                    hitbox.x = x + hitboxOffsetX;                  // Current X (or updated X from horizontal move)
                    hitbox.y = prospectivePlayerY + hitboxOffsetY; // Update hitbox for tile check
                    if (!map.isColliding(hitbox)) {
                        y = prospectivePlayerY; // Move if no tile collision
                    }
                } else {
                    // Trying to move out of Y bounds. Optionally, clamp to edge.
                    if (deltaY < 0 && prospectiveHitboxTop < 0) { // Moving up out of bounds
                        y = -hitboxOffsetY; // Clamp player so hitbox.y is 0
                    } else if (deltaY > 0 && prospectiveHitboxBottom > map.mapHeight) { // Moving down out of bounds
                        y = map.mapHeight - hitbox.height - hitboxOffsetY; // Clamp player so hitbox.y + hitbox.height is mapHeight
                    }
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

        g2.drawImage(sprite, sx, sy, pw, ph, null);
    }
}
