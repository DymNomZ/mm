package entities;

import core.Configs;
import core.KeyHandler;
import core.MapConstructor;
import core.SpriteLoader;

import java.awt.*;

public class Player extends Entity{

    private KeyHandler keyHandler;
    private MapConstructor map;

    public Rectangle hitbox;
    public int hitboxOffsetX;
    public int hitboxOffsetY;

    public Player(KeyHandler keyHandler, MapConstructor map) {
        this.keyHandler = keyHandler;
        this.map = map;
        this.hitbox = new Rectangle(0, 0, Configs.HALF, Configs.HALF);
        this.hitboxOffsetX = Configs.QUARTER;
        this.hitboxOffsetY = Configs.QUARTER;
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

    public void move() {
        int deltaX = 0;
        int deltaY = 0;

        if (keyHandler.wPressed && !keyHandler.sPressed) {
            deltaY = -speed;
        } else if (keyHandler.sPressed && !keyHandler.wPressed) {
            deltaY = speed;
        }

        if (keyHandler.aPressed && !keyHandler.dPressed) {
            deltaX = -speed;
        } else if (keyHandler.dPressed && !keyHandler.aPressed) {
            deltaX = speed;
        }

        if (deltaX != 0 || deltaY != 0) {
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

        g2.drawImage(sprite, sx, sy, Configs.TILE_SIZE, Configs.TILE_SIZE, null);
    }
}
