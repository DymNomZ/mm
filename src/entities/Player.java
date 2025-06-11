package entities;

import core.*;
import entities.normal_seeds.Carrot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

    private int direction; //1 up 2 left 3 down 4 right

    private boolean isMoving;

    private int animationTick = 0;
    private int animationSpeed = 10;
    private int currentFrameIndex = 0;

    public ArrayList<Seed> plantedSeeds = new ArrayList<>();

    public Player() {
        this.keyHandler = Game.keyHandler;
        this.map = Game.mapConstructor;
        this.ewidth = ts;
        this.eheight = d;
        this.hitbox = new Rectangle(0, 0, h, trd);
        this.hitboxOffsetX = q;
        this.hitboxOffsetY = h;
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

    private void orientReach() {

        // reach dimensions, width and depth or how far
        int rw = ts;
        int rd = ts;

        //gap between player and actual reach
        int reachOffset = -h;

        // Calculate the center of the player using its world coordinates and dimensions
        double playerCenterX = x + (double) ewidth / 2.0;
        double playerCenterY = y + (double) eheight / 2.0;

        switch (direction) {
            case 1:
                reach.width = rw;
                reach.height = rd;
                reach.x = (int) (playerCenterX - (double)reach.width / 2.0); // Center reach horizontally with player
                reach.y = (int) (y - reach.height - reachOffset); // Position above player's top edge
                break;
            case 2:
                reach.width = rd;
                reach.height = rw;
                reach.x = (int) (x - reach.width - reachOffset); // Position left of player's left edge
                reach.y = (int) (playerCenterY - (double)reach.height / 2.0); // Center reach vertically with player
                break;
            case 3:
                reach.width = rw;
                reach.height = rd;
                reach.x = (int) (playerCenterX - (double)reach.width / 2.0);
                reach.y = (int) (y + eheight + reachOffset);
                break;
            case 4:
                reach.width = rd;
                reach.height = rw;
                reach.x = (int) (x + ewidth + reachOffset);
                reach.y = (int) (playerCenterY - (double)reach.height / 2.0);
                break;
            default: // Fallback
                reach.x = (int)this.x;
                reach.y = (int)this.y;
                reach.width = 0;
                reach.height = 0;
                break;
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
                sprite = currentFrames[currentFrameIndex];
            } else {
                sprite = currentFrames[0]; // Fallback if index is out of bounds
                currentFrameIndex = 0; // Reset index
            }
        } else if (SpriteLoader.PLAYER_DOWN_IDLE_FRAMES.length > 0) {
            // Fallback if something went very wrong with currentFrames selection
            sprite = SpriteLoader.PLAYER_DOWN_IDLE_FRAMES[0];
        }
    }

    public void handleInteractions(){

        if(!keyHandler.ePressed) return;

//        if(map.isColliding(reach)){
//            //room for creativity!
//        }
    }

    public void plantSeed(int x, int y){
        plantedSeeds.add(
                Tools.giveRandomSeed(x, y)
        );
    }

    public void dropItem(){

        if(!keyHandler.qPressed) return;

        //to be changes to actual drop implementation
    }

    public void move() {
        double moveX = 0;
        double moveY = 0;
        int previousDirection = this.direction;

        // Determine input direction
        if (keyHandler.wPressed && !keyHandler.sPressed) {
            moveY = -1;
        } else if (keyHandler.sPressed && !keyHandler.wPressed) {
            moveY = 1;
        }

        if (keyHandler.aPressed && !keyHandler.dPressed) {
            moveX = -1;
        } else if (keyHandler.dPressed && !keyHandler.aPressed) {
            moveX = 1;
        }

        if (moveY < 0) direction = 1;
        else if (moveY > 0) direction = 3;
        if (moveX < 0 && (moveY == 0 || !keyHandler.wPressed && !keyHandler.sPressed)) direction = 2;
        else if (moveX > 0 && (moveY == 0 || !keyHandler.wPressed && !keyHandler.sPressed)) direction = 4;

        //Normalize the movement vector if moving diagonally
        if (moveX != 0 && moveY != 0) {
            double length = Math.sqrt(moveX * moveX + moveY * moveY);
            moveX = (moveX / length);
            moveY = (moveY / length);
        }

        double actualDeltaX = moveX * speed;
        double actualDeltaY = moveY * speed;

        //for animation
        if (actualDeltaX != 0 || actualDeltaY != 0) {
            isMoving = true;
        } else {
            isMoving = false;
        }

        // Reset animation frame if direction changes OR if movement stops/starts
        if (direction != previousDirection || (isMoving && currentFrameIndex == 0 && animationTick == 0 && moveX == 0 && moveY == 0)) {
            currentFrameIndex = 0;
            animationTick = 0;
        }

        if (isMoving) {

            orientReach();

            // Check horizontal collision
            if (isMoving) {
                orientReach();

                // Check horizontal collision
                if (actualDeltaX != 0) {
                    // Prospective player world X (double)
                    double prospectivePlayerWorldX = x + actualDeltaX;

                    // Update hitbox for collision check (using integer casting for simplicity with current rect)
                    // For more precision, hitbox could also use doubles, or collision check could handle doubles.
                    hitbox.x = (int) (prospectivePlayerWorldX + hitboxOffsetX);
                    hitbox.y = (int) (y + hitboxOffsetY); // Current Y for horizontal check

                    if (prospectivePlayerWorldX + hitboxOffsetX >= 0 &&
                            prospectivePlayerWorldX + hitboxOffsetX + hitbox.width <= map.mapWidth) {
                        if (!map.isColliding(hitbox)) {
                            x = prospectivePlayerWorldX; // Apply double movement
                        }
                    } else { // Clamp to map boundaries
                        if (actualDeltaX < 0) x = -hitboxOffsetX;
                        else x = map.mapWidth - hitbox.width - hitboxOffsetX;
                    }
                }

                // Check vertical collision
                if (actualDeltaY != 0) {
                    double prospectivePlayerWorldY = y + actualDeltaY;

                    hitbox.x = (int) (x + hitboxOffsetX); // Current X (or updated X)
                    hitbox.y = (int) (prospectivePlayerWorldY + hitboxOffsetY);

                    if (prospectivePlayerWorldY + hitboxOffsetY >= 0 &&
                            prospectivePlayerWorldY + hitboxOffsetY + hitbox.height <= map.mapHeight) {
                        if (!map.isColliding(hitbox)) {
                            y = prospectivePlayerWorldY; // Apply double movement
                        }
                    } else { // Clamp to map boundaries
                        if (actualDeltaY < 0) y = -hitboxOffsetY;
                        else y = map.mapHeight - hitbox.height - hitboxOffsetY;
                    }
                }
            }
        }
        // Final update of hitbox to current player world position (for rendering or other checks)
        hitbox.x = (int) (x + hitboxOffsetX);
        hitbox.y = (int) (y + hitboxOffsetY);

        updateSprite();

        // System.out.println("Player World X: " + x + ", World Y: " + y);
        // System.out.println("Hitbox World X: " + hitbox.x + ", World Y: " + hitbox.y);
    }

    public void render(Graphics2D g2){

        //debug draw reach
        if (this.reach != null) {
            int reachScreenX = this.reach.x - (int)x + sx;
            int reachScreenY = this.reach.y - (int)y + sy;

            g2.setColor(Color.GREEN);
            g2.drawRect(reachScreenX, reachScreenY, this.reach.width, this.reach.height);
        }

        //render player's planted seeds
        for(Seed s : plantedSeeds){
            if(s.hide) continue;
            s.render(g2);
        }

        g2.drawImage(sprite, sx, sy, ewidth, eheight, null);

        //debug draw hitbox
        int hitboxScreenX = hitbox.x - (int)x + sx;
        int hitboxScreenY = hitbox.y - (int)y + sy;

        g2.setColor(Color.WHITE);
        g2.drawRect(hitboxScreenX, hitboxScreenY, hitbox.width, hitbox.height);
    }
}
