package entities;

import core.*;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    private int trd = Configs.THIRD;
    private int pw = ts;
    private int ph = d;

    private int direction; //1 up 2 left 3 down 4 right

    private boolean isMoving;

    private int animationTick = 0;
    private int animationSpeed = 10;
    private int currentFrameIndex = 0;

    public Player() {
        this.keyHandler = Game.keyHandler;
        this.map = Game.mapConstructor;
        this.hitbox = new Rectangle(0, 0, h, trd);
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
                reach.y = y + ts;
                reach.height = ts;
            }
            case 3 -> {
                reach.x = x;
                reach.width = ts;
                reach.y = y + d;
                reach.height = h;
            }
            case 4 -> {
                reach.x = x + ts;
                reach.width = h;
                reach.y = y + ts;
                reach.height = ts;
            }
        }
    }

    private void updateSprite() {
        BufferedImage[] currentFrames = null;
        int frameLimit = 0;

        if (isMoving) {
            frameLimit = SpriteLoader.WALKING_LIMIT;
            switch (direction) {
                case 1 -> currentFrames = SpriteLoader.PLAYER_UP_WALKING_FRAMES;
                case 2 -> currentFrames = SpriteLoader.PLAYER_LEFT_WALKING_FRAMES;
                case 3 -> currentFrames = SpriteLoader.PLAYER_DOWN_WALKING_FRAMES;
                case 4 -> currentFrames = SpriteLoader.PLAYER_RIGHT_WALKING_FRAMES;
                default -> {
                    currentFrames = SpriteLoader.PLAYER_DOWN_IDLE_FRAMES;
                    frameLimit = SpriteLoader.IDLE_LIMIT;
                }
            }
        } else {
            frameLimit = SpriteLoader.IDLE_LIMIT;
            switch (direction) {
                case 1 -> currentFrames = SpriteLoader.PLAYER_UP_IDLE_FRAMES;
                case 2 -> currentFrames = SpriteLoader.PLAYER_LEFT_IDLE_FRAMES;
                case 3 -> currentFrames = SpriteLoader.PLAYER_DOWN_IDLE_FRAMES;
                case 4 -> currentFrames = SpriteLoader.PLAYER_RIGHT_IDLE_FRAMES;
                default -> currentFrames = SpriteLoader.PLAYER_DOWN_IDLE_FRAMES;
            }
        }

        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            currentFrameIndex++;
            if (currentFrameIndex >= frameLimit) {
                currentFrameIndex = 0;
            }
        }

        if (currentFrames != null && currentFrames.length > 0) {
            // Ensure currentFrameIndex is valid, especially if frameLimit somehow became 0
            if (currentFrameIndex < frameLimit) {
                this.sprite = currentFrames[currentFrameIndex];
            } else {
                this.sprite = currentFrames[0]; // Fallback if index is out of bounds
                currentFrameIndex = 0; // Reset index
            }
        } else if (SpriteLoader.PLAYER_DOWN_IDLE_FRAMES.length > 0) {
            // Fallback if something went very wrong with currentFrames selection
            this.sprite = SpriteLoader.PLAYER_DOWN_IDLE_FRAMES[0];
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
        int previousDirection = this.direction;

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

        // If not moving, player might still change facing direction if they just tapped a key
        // but currentFrameIndex should reset if they *stop* moving into a new direction
        isMoving = deltaX != 0 || deltaY != 0;

        // Reset animation frame if direction changes OR if movement stops/starts
        if (direction != previousDirection || (isMoving && currentFrameIndex == 0 && animationTick == 0 && deltaX == 0 && deltaY == 0)) {
            currentFrameIndex = 0;
            animationTick = 0;
        }

        if (isMoving) {

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

        updateSprite();

        // System.out.println("Player World X: " + x + ", World Y: " + y);
        // System.out.println("Hitbox World X: " + hitbox.x + ", World Y: " + hitbox.y);
    }

    public void render(Graphics2D g2){

        int rsx = reach.x - x + sx;
        int rsy = reach.y - y + sy;

        //debug draw reach
        g2.setColor(Color.GREEN);
        g2.drawRect(rsx, rsy, ts, ts);
        g2.fillRect(rsx, rsy, ts, ts);

        g2.drawImage(sprite, sx, sy, pw, ph, null);

        int hsx = Configs.CENTER_X + hitboxOffsetX;
        int hsy = Configs.CENTER_Y + hitboxOffsetY;

        //debug draw hitbox
        g2.setColor(Color.WHITE);
        g2.drawRect(hsx, hsy, hitbox.width, hitbox.height);
    }
}
