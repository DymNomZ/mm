package core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class SpriteLoader {

    public static BufferedImage PLAYER;
    public static BufferedImage REACH;

    static {
        try {
            PLAYER = ImageIO.read(SpriteLoader.class.getResourceAsStream("/textures/player/debug_player.png"));
            REACH = ImageIO.read(SpriteLoader.class.getResourceAsStream("/textures/player/debug_reach.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage DEBUG_ITEM;

    static {
        try{
            DEBUG_ITEM = ImageIO.read(SpriteLoader.class.getResourceAsStream("/textures/items/debug_item.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage DEBUG_DOOR;

    static{
        try{
            DEBUG_DOOR = ImageIO.read(SpriteLoader.class.getResourceAsStream("/textures/tiles/debug_door_open.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
